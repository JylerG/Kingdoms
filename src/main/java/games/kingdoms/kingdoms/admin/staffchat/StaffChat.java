package games.kingdoms.kingdoms.admin.staffchat;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import games.kingdoms.kingdoms.admin.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StaffChat implements CommandExecutor {

    private final Kingdoms plugin;

    public StaffChat(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (plugin.getStaff().containsKey(player.getUniqueId().toString())) {
                String message = String.join(" ", args); // Concatenate args into a single string

                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (plugin.getStaff().containsKey(p.getUniqueId().toString())) {
                        String rank = plugin.getPlayerRank().get(player.getUniqueId().toString());

                        //Player has JrMod Rank
                        if (rank.equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD.toString().toUpperCase())) {
                            String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + rank + ChatColor.DARK_AQUA + " " + player.getDisplayName() + ChatColor.AQUA + ": " + ChatColor.YELLOW + message;
                            p.sendMessage(format);
                        }
                        //Player has Mod Rank
                        if (rank.equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD.toString().toUpperCase())) {
                            String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + rank + ChatColor.YELLOW + " " + player.getDisplayName() + ChatColor.AQUA + ": " + ChatColor.YELLOW + message;
                            p.sendMessage(format);
                        }
                        //Player has SrMod Rank
                        if (rank.equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD.toString().toUpperCase())) {
                            String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + rank + ChatColor.GOLD + " " + player.getDisplayName() + ChatColor.AQUA + ": " + ChatColor.YELLOW + message;
                            p.sendMessage(format);
                        }
                        //Player has JrAdmin Rank
                        if (rank.equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN.toString().toUpperCase())) {
                            String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + rank + ChatColor.LIGHT_PURPLE + " " + player.getDisplayName() + ChatColor.AQUA + ": " + ChatColor.YELLOW + message;
                            p.sendMessage(format);
                        }
                        //Player has Admin Rank
                        if (rank.equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN.toString().toUpperCase())) {
                            String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + rank + ChatColor.LIGHT_PURPLE + " " + player.getDisplayName() + ChatColor.AQUA + ": " + ChatColor.YELLOW + message;
                            p.sendMessage(format);
                        }
                    }
                }
            }
            else {
                MessageManager.playerBad(player, "You do not have permission to use this command.");
            }

        } else {
            MessageManager.consoleBad("You must be a player to execute this command.");
        }

        return true;
    }
}
