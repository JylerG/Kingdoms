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
        UUID playerUUID = player.getUniqueId();

        String rank = plugin.getPlayerRank().get(playerUUID.toString());

        if ((rank.equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD) ||
                rank.equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD) ||
                rank.equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD) ||
                rank.equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN) ||
                rank.equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN))) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " remove kingdoms.move");
            if (plugin.getStaffPasswords().get(playerUUID.toString()).isEmpty()) {

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
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (plugin.getPlayerRank().containsKey(playerUUID.toString())
                && !player.hasPermission("kingdoms.move")) {

            if (plugin.getStaffPasswords().get(player.getUniqueId().toString()).isEmpty()) {
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
                player.sendMessage(ChatColor.GREEN + "Please enter your password. " + ChatColor.WHITE + "/password <your password>");
            }
            e.setCancelled(true);
        } else if (plugin.getPlayerRank().containsKey(playerUUID.toString())
                && player.hasPermission("kingdoms.move")) {
            e.setCancelled(false);
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
            return true;
        }

        String password = args[0];

        if (plugin.getStaffPasswords().get(player.getUniqueId().toString()).isEmpty()) {
            if (!PASSWORD_PATTERN.matcher(password).matches() || password.length() < 5) {
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
                if (PASSWORD_PATTERN.matcher(password).matches() && password.length() >= 5) {
                    plugin.getStaffPasswords().put(playerUUID.toString(), password);
                    player.sendMessage(ChatColor.GREEN + "You set your password successfully");
                }
            }
            return true;
        } else {
            if (!plugin.getStaffPasswords().get(playerUUID.toString()).equals(password)) {
                player.sendMessage(ChatColor.GREEN + "Incorrect password. Please try again");
            } else {
                player.sendMessage(ChatColor.GREEN + "You entered the correct password");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " add kingdoms.move");
            }
        }
        return true;
    }
}
