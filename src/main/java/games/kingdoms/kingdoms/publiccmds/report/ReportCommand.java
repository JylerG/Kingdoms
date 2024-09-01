package games.kingdoms.kingdoms.publiccmds.report;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class ReportCommand implements CommandExecutor {

    final Kingdoms plugin = Kingdoms.getPlugin();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (args.length < 2) {
                player.sendMessage(ChatColor.GOLD + "Usage: /report <user> <reason>");
                return true;
            }

            if (args.length >= 2) {
                Player target = Bukkit.getPlayer(args[0]);
                String report = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

                if (target == null) {
                    player.sendMessage(args[0] + ChatColor.RED + " is not online");
                    return true;
                }

                if (!(target.getWorld() == player.getWorld())) {
                    player.sendMessage(ChatColor.RED + "You must be in the same world as " + ChatColor.WHITE + target.getName() + ChatColor.RED + " to report them");
                    return true;
                }

                player.sendMessage(ChatColor.GREEN + "Staff have been notified of your report against " + ChatColor.WHITE + target.getName());

                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("JRMOD")
                            || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("MOD")
                            || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("SRMOD")
                            || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("JRADMIN")
                            || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {

                        if (player.getWorld() == p.getWorld()) {
                            p.sendMessage(ChatColor.DARK_RED + "[REPORT] " + ChatColor.WHITE + target.getName() + ChatColor.RED
                                    + " for " + ChatColor.WHITE + report + ChatColor.GRAY + " (from " + player.getName() + ")");
                        } else {
                            if (p.getWorld().equals(Bukkit.getWorld("kingdoms"))) {
                                p.sendMessage(ChatColor.DARK_RED + "[REPORT] " + ChatColor.AQUA + "[Warzone] " + ChatColor.WHITE + target.getName() + ChatColor.RED
                                        + " for " + ChatColor.WHITE + report + ChatColor.GRAY + " (from " + player.getName() + ")");
                            } else if (p.getWorld().equals(Bukkit.getWorld("warzone"))) {
                                p.sendMessage(ChatColor.DARK_RED + "[REPORT] " + ChatColor.AQUA + "[Kingdoms] " + ChatColor.WHITE + target.getName() + ChatColor.RED
                                        + " for " + ChatColor.WHITE + report + ChatColor.GRAY + " (from " + player.getName() + ")");
                            }
                        }
                    }
                }
            }

        }
        return true;
    }
}
