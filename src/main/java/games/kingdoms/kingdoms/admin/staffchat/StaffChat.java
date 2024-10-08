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

    final Kingdoms plugin;

    public StaffChat(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (!plugin.getStaff().containsKey(player.getUniqueId().toString())) {
                return true;
            }

            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "You must have at least one argument to send a message to " + ChatColor.AQUA + " Staff Chat");
                return true;
            }

            String message = String.join(" ", args); // Concatenate args into a single string

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (plugin.getStaff().containsKey(player.getUniqueId().toString())) {
                    if (plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("JRMOD")
                            || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("MOD")
                            || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("SRMOD")
                            || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("JRADMIN")
                            || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                        String staff = plugin.getStaff().get(player.getUniqueId().toString());

                        //Player has JrMod Rank
                        if (staff.equalsIgnoreCase("JRMOD")) {
                            String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + ChatColor.DARK_GREEN + ChatColor.BOLD + Rank.JRMOD + " " +
                                    ChatColor.DARK_GREEN + player.getName() + ChatColor.AQUA + ": " + message;
                            p.sendMessage(format);
                        }
                        //Player has Mod Rank
                        if (staff.equalsIgnoreCase("MOD")) {
                            String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD + " " +
                                    ChatColor.YELLOW + player.getName() + ChatColor.AQUA + ": " + message;
                            p.sendMessage(format);
                        }
                        //Player has SrMod Rank
                        if (staff.equalsIgnoreCase("SRMOD")) {
                            String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD + " " +
                                    ChatColor.GOLD + player.getName() + ChatColor.AQUA + ": " + message;
                            p.sendMessage(format);
                        }
                        //Player has JrAdmin Rank
                        if (staff.equalsIgnoreCase("JRADMIN")) {
                            String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + ChatColor.RED + ChatColor.BOLD + Rank.JRADMIN + " " +
                                    ChatColor.RED + player.getName() + ChatColor.AQUA + ": " + message;
                            p.sendMessage(format);
                        }
                        //Player has Admin Rank
                        else if (staff.equalsIgnoreCase("ADMIN")) {
                            String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + ChatColor.RED + ChatColor.BOLD + Rank.ADMIN + " " +
                                    ChatColor.RED + player.getName() + ChatColor.AQUA + ": " + message;
                            p.sendMessage(format);
                        }
                    }
                }
            }
        } else {
            MessageManager.consoleBad("You must be a player to execute this command.");
        }

        return true;
    }
}
