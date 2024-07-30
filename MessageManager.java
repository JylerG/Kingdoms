package games.kingdoms.kingdoms;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageManager {

    public static void consoleGood(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + message);
    }
    public static void consoleBad(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + message);
    }
    public static void consoleInfo(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + message);
    }
    public static void playerGood(Player player, String message) {
        player.sendMessage(ChatColor.GREEN + message);
    }
    public static void playerBad(Player player, String message) {
        player.sendMessage(ChatColor.RED + message);
    }
    public static void playerInfo(Player player, String message) {
        player.sendMessage(ChatColor.YELLOW + message);
    }
}
