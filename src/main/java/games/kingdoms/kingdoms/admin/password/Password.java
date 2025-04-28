package games.kingdoms.kingdoms.admin.password;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import games.kingdoms.kingdoms.admin.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

public class Password implements CommandExecutor, Listener {

    final Kingdoms plugin = Kingdoms.getPlugin(Kingdoms.class);
    final Map<String, String> playersRank = plugin.getPlayerRank();
    boolean passwordEntered;

    private static final Pattern DIGIT = Pattern.compile(".*[0-9].*");
    private static final Pattern LOWERCASE = Pattern.compile(".*[a-z].*");
    private static final Pattern UPPERCASE = Pattern.compile(".*[A-Z].*");
    private static final Pattern SYMBOL = Pattern.compile(".*[!@#\\$%\\^&\\*\\-\\_].*");
    private static final int MIN_LENGTH = 5;

    public void promptPasswordRequirements(Player player, String attemptedPassword) {
        boolean validPassword = attemptedPassword.length() >= MIN_LENGTH
                && DIGIT.matcher(attemptedPassword).matches()
                && UPPERCASE.matcher(attemptedPassword).matches()
                && LOWERCASE.matcher(attemptedPassword).matches()
                && SYMBOL.matcher(attemptedPassword).matches();

        String msg = ChatColor.GREEN + "Please enter your new password\n" +
                "Use " + ChatColor.WHITE + "/password <your new password>\n" +
                ChatColor.GOLD + ChatColor.BOLD + "Password Parameters\n" +
                formatRequirement(attemptedPassword.length() >= MIN_LENGTH, "At least 5 Characters") +
                formatRequirement(DIGIT.matcher(attemptedPassword).matches(), "Contains Numbers") +
                formatRequirement(UPPERCASE.matcher(attemptedPassword).matches(), "Contains Uppercase Letters") +
                formatRequirement(LOWERCASE.matcher(attemptedPassword).matches(), "Contains Lowercase Letters") +
                formatRequirement(SYMBOL.matcher(attemptedPassword).matches(), "Contains Symbols");

        player.sendMessage(msg);
        player.sendMessage(ChatColor.RED + "Please enter a valid password");
    }

    private String formatRequirement(boolean met, String requirement) {
        return (met ? ChatColor.GREEN + "✔ " : ChatColor.RED + "✘ ") + requirement + "\n";
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();
        String playerUUID = player.getUniqueId().toString();

        String playerRank = plugin.getPlayerRank().get(playerUUID);
        plugin.restorePluginData(player);

        boolean isJrMod = playerRank != null && playerRank.equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
        boolean isMod = playerRank != null && playerRank.equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
        boolean isSrMod = playerRank != null && playerRank.equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
        boolean isJrAdmin = playerRank != null && playerRank.equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
        boolean isAdmin = playerRank != null && playerRank.equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN);




        if ((!isJrMod && !isMod && !isSrMod && !isJrAdmin && !isAdmin) && player.hasPermission("kingdoms.move")) return;

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " remove kingdoms.move");
        String password = plugin.getStaffPasswords().get(playerUUID);
        if (password == null || password.isEmpty()) {
            player.sendMessage(ChatColor.GREEN + "Please enter your new password\n" +
                    "Use " + ChatColor.WHITE + "/password <your new password>\n" +
                    ChatColor.GOLD + ChatColor.BOLD + "Password Parameters\n" +
                    ChatColor.YELLOW + "◼ At least 5 Characters\n" +
                    ChatColor.YELLOW + "◼ Contains Numbers\n" +
                    ChatColor.YELLOW + "◼ Contains Uppercase Letters\n" +
                    ChatColor.YELLOW + "◼ Contains Lowercase Letters\n" +
                    ChatColor.YELLOW + "◼ Contains Symbols\n" +
                    ChatColor.DARK_AQUA + "Please enter a valid password");
        } else {
            // Password Entered = false
            player.sendMessage(ChatColor.GREEN + "Please enter your password. " + ChatColor.WHITE + "/password <your password>");
        }
    }

    //todo: figure out why this throws a NullPointerException on reload if the player doesn't have the "kingdoms.move" permission
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        String playerUUID = player.getUniqueId().toString();

        // Retrieve player's rank safely
        String playerRank = plugin.getPlayerRank().get(playerUUID);

        if (playerRank == null) {
            // Assign default rank to player if none exists
            playerRank = ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT;
            plugin.getPlayerRank().put(playerUUID, playerRank);
        }

        // Check player rank safely
        boolean isJrMod = playerRank.equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
        boolean isMod = playerRank.equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
        boolean isSrMod = playerRank.equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
        boolean isJrAdmin = playerRank.equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
        boolean isAdmin = playerRank.equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN);

        boolean isStaff = isJrMod || isMod || isSrMod || isJrAdmin || isAdmin;

        // Return if player is not staff
        if (!isStaff) {
            return;
        }

        passwordEntered = true;

        // If player is staff and has permission, remove it
        if (isStaff && !passwordEntered) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " remove kingdoms.move");
        }

        // Password handling for staff members
        if (!player.hasPermission("kingdoms.move")) {
            String password = plugin.getStaffPasswords().get(playerUUID);

            if (password == null || password.isEmpty()) {
                if (isStaff) {
                    player.sendMessage(ChatColor.GREEN + "Please enter your new password\n" +
                            "Use " + ChatColor.WHITE + "/password <your new password>\n" +
                            ChatColor.GOLD + ChatColor.BOLD + "Password Parameters\n" +
                            ChatColor.YELLOW + "◼ At least 5 Characters\n" +
                            ChatColor.YELLOW + "◼ Contains Numbers\n" +
                            ChatColor.YELLOW + "◼ Contains Uppercase Letters\n" +
                            ChatColor.YELLOW + "◼ Contains Lowercase Letters\n" +
                            ChatColor.YELLOW + "◼ Contains Symbols\n" +
                            ChatColor.DARK_AQUA + "Please enter a valid password");
                    e.setCancelled(true);
                }
            } else {
                player.sendMessage(ChatColor.GREEN + "Please enter your password. " + ChatColor.WHITE + "/password <your password>");
                e.setCancelled(true);
            }
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            MessageManager.consoleBad("This command can only be executed by a player.");
            return true;
        }

        UUID playerUUID = player.getUniqueId();
        String rank = plugin.getPlayerRank().get(playerUUID.toString());

        if (rank == null) {
            player.sendMessage(ChatColor.RED + "Your rank could not be found.");
            return true;
        }

        boolean isJrMod = rank.equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
        boolean isMod = rank.equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
        boolean isSrMod = rank.equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
        boolean isJrAdmin = rank.equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
        boolean isAdmin = rank.equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN);

        // Allow movement if player already has permission
        if ((!isJrMod && !isMod && !isSrMod && !isJrAdmin && !isAdmin) && player.hasPermission("kingdoms.move")) {
            return true;
        }

        // Handle password reset
        if (args.length == 2 && args[0].equalsIgnoreCase("reset")) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(ChatColor.RED + args[1] + " is not online");
                return true;
            }

            plugin.getStaffPasswords().put(target.getUniqueId().toString(), "");
            player.sendMessage(ChatColor.GREEN + "Password for " + target.getName() + " has been reset");
            return true;
        }

        // Require a password input
        if (args.length == 0) {
            MessageManager.playerBad(player, "Please provide a password");
            return true;
        }

        String password = args[0];
        String storedPassword = plugin.getStaffPasswords().get(playerUUID.toString());

        // If no password is stored yet
        if (storedPassword == null || storedPassword.isEmpty()) {
            if (!isValidPassword(password)) {
                promptPasswordRequirements(player, password); // Show which parts are missing
                return true;
            }

            // All checks passed
            plugin.getStaffPasswords().put(playerUUID.toString(), password);
            MessageManager.playerGood(player, "You set your password successfully");
            return true;
        }

        // Compare password with stored one
        if (!storedPassword.equals(password)) {
            MessageManager.playerBad(player, "Incorrect password");
            passwordEntered = false;
            return true;
        }

        // Grant movement permission
        MessageManager.playerGood(player, "You entered the correct password");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " add kingdoms.move");
        passwordEntered = true;
        return true;
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 5 &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[!@#\\$%\\^&\\*\\-_].*");
    }

}
