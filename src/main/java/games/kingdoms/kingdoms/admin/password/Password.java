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

import java.util.UUID;
import java.util.regex.Pattern;

public class Password implements CommandExecutor, Listener {

    final Kingdoms plugin = Kingdoms.getPlugin(Kingdoms.class);

    static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\\$%\\^&\\*\\-\\_]).{5,}$");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();
        String playerUUID = player.getUniqueId().toString();

        plugin.restorePluginData(player);

        boolean isJrMod = plugin.getPlayerRank().get(playerUUID).equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
        boolean isMod = plugin.getPlayerRank().get(playerUUID).equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
        boolean isSrMod = plugin.getPlayerRank().get(playerUUID).equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
        boolean isJrAdmin = plugin.getPlayerRank().get(playerUUID).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
        boolean isAdmin = plugin.getPlayerRank().get(playerUUID).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN);

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

        boolean isJrMod = plugin.getPlayerRank().get(playerUUID).equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
        boolean isMod = plugin.getPlayerRank().get(playerUUID).equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
        boolean isSrMod = plugin.getPlayerRank().get(playerUUID).equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
        boolean isJrAdmin = plugin.getPlayerRank().get(playerUUID).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
        boolean isAdmin = plugin.getPlayerRank().get(playerUUID).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN);

        if ((!isJrMod && !isMod && !isSrMod && !isJrAdmin && !isAdmin) && player.hasPermission("kingdoms.move")) return;

        if ((isJrMod || isMod || isSrMod || isJrAdmin || isAdmin) && player.hasPermission("kingdoms.move")) {
            e.setCancelled(false);
            return;
        }

        String password = plugin.getStaffPasswords().get(playerUUID);
        if (password == null || password.isEmpty()) {
            if ((isJrMod || isMod || isSrMod || isJrAdmin || isAdmin)) {
                if (!player.hasPermission("kingdoms.move")) {
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
            }
        } else {
            player.sendMessage(ChatColor.GREEN + "Please enter your password. " + ChatColor.WHITE + "/password <your password>");
            e.setCancelled(true);
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            MessageManager.consoleBad("This command can only be executed by a player.");
            return true;
        }

        UUID playerUUID = player.getUniqueId();

        // Fetch the rank once and reuse it
        String rank = plugin.getPlayerRank().get(playerUUID.toString());

        if (rank == null) {
            player.sendMessage(ChatColor.RED + "Your rank could not be found.");
            return true;
        }

        // Check player's rank
        boolean isJrMod = rank.equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
        boolean isMod = rank.equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
        boolean isSrMod = rank.equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
        boolean isJrAdmin = rank.equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
        boolean isAdmin = rank.equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN);

        // Allow movement if the player has the permission "kingdoms.move"
        if ((!isJrMod && !isMod && !isSrMod && !isJrAdmin && !isAdmin) && player.hasPermission("kingdoms.move")) {
            return true;
        }

        // Handle password reset for other players
        if (args.length == 2 && args[0].equalsIgnoreCase("reset")) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                player.sendMessage(args[1] + ChatColor.RED + " is not online");
                return true;
            }

            // Reset the target's password
            plugin.getStaffPasswords().put(target.getUniqueId().toString(), "");
            player.sendMessage(ChatColor.GREEN + "Password for " + target.getName() + " has been reset");
            return true;
        }

        // Ensure at least one argument is provided (the password)
        if (args.length == 0) {
            MessageManager.playerBad(player, "Please provide a password");
            return true;
        }

        String password = args[0];
        String storedPassword = plugin.getStaffPasswords().get(playerUUID.toString());

        // If no password is set for the player, prompt them to set a new one
        if (storedPassword == null || storedPassword.isEmpty()) {
            if (!PASSWORD_PATTERN.matcher(password).matches() || password.length() < 5) {
                player.sendMessage(ChatColor.GREEN + "Please enter your new password\n" +
                        "Use " + ChatColor.WHITE + "/password <your new password>\n" +
                        ChatColor.GOLD + ChatColor.BOLD + "Password Parameters\n" +
                        ChatColor.YELLOW + "◼ At least 5 Characters\n" +
                        ChatColor.YELLOW + "◼ Contains Numbers\n" +
                        ChatColor.YELLOW + "◼ Contains Uppercase Letters\n" +
                        ChatColor.YELLOW + "◼ Contains Lowercase Letters\n" +
                        ChatColor.YELLOW + "◼ Contains Symbols\n" +
                        ChatColor.DARK_AQUA + "Please enter a valid password.");
                return true;
            }

            // Store the new password
            plugin.getStaffPasswords().put(playerUUID.toString(), password);
            MessageManager.playerGood(player, "You set your password successfully");
            return true;
        }

        // Check if the entered password matches the stored one
        if (!storedPassword.equals(password)) {
            MessageManager.playerBad(player, "Incorrect password");
            return true;
        }

        // If the password is correct, grant permission to move
        MessageManager.playerGood(player, "You entered the correct password");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " add kingdoms.move");

        return true;
    }
}
