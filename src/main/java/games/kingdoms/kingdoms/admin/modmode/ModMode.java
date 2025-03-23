package games.kingdoms.kingdoms.admin.modmode;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.admin.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ModMode implements CommandExecutor {

    final Kingdoms plugin = Kingdoms.getPlugin(Kingdoms.class);
    final HashMap<String, String> staff = plugin.getStaff();
    final Map<String, String> playerRank = plugin.getPlayerRank();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (player.hasPermission("kingdoms.staff.modmode")) {
                if (!plugin.getModModePlayers().contains(player)) {
                    plugin.getModModePlayers().add(player);
                    player.setGameMode(GameMode.SPECTATOR);
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (staff.get(p.getUniqueId().toString()).equalsIgnoreCase("JRMOD")
                                || staff.get(p.getUniqueId().toString()).equalsIgnoreCase("MOD")
                                || staff.get(p.getUniqueId().toString()).equalsIgnoreCase("SRMOD")
                                || staff.get(p.getUniqueId().toString()).equalsIgnoreCase("JRADMIN")
                                || staff.get(p.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                            if (playerRank.get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                p.sendMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD + " " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " has entered modmode. They can now " +
                                        "interact with other players in spectator mode");
                            } else if (playerRank.get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                p.sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD + " " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " has entered modmode. They can now " +
                                        "interact with other players in spectator mode");
                            } else if (playerRank.get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                p.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD + " " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " has entered modmode. They can now " +
                                        "interact with other players in spectator mode");
                            } else if (playerRank.get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN + " " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " has entered modmode. They can now " +
                                        "interact with other players in spectator mode");
                            } else if (playerRank.get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN + " " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " has entered modmode. They can now " +
                                        "interact with other players in spectator mode");
                            }
                        }
                    }
                } else {
                    plugin.getModModePlayers().remove(player);
                    player.setGameMode(GameMode.SURVIVAL);
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (staff.get(p.getUniqueId().toString()).equalsIgnoreCase("JRMOD")
                                || staff.get(p.getUniqueId().toString()).equalsIgnoreCase("MOD")
                                || staff.get(p.getUniqueId().toString()).equalsIgnoreCase("SRMOD")
                                || staff.get(p.getUniqueId().toString()).equalsIgnoreCase("JRADMIN")
                                || staff.get(p.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                            if (playerRank.get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                p.sendMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD + " " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " has left modmode. They can no longer " +
                                        "interact with other players in spectator mode");
                            } else if (playerRank.get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                p.sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD + " " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " has left modmode. They can no longer " +
                                        "interact with other players in spectator mode");
                            } else if (playerRank.get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                p.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD + " " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " has left modmode. They can no longer " +
                                        "interact with other players in spectator mode");
                            } else if (playerRank.get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN + " " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " has left modmode. They can no longer " +
                                        "interact with other players in spectator mode");
                            } else if (playerRank.get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN + " " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " has left modmode. They can no longer " +
                                        "interact with other players in spectator mode");
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
