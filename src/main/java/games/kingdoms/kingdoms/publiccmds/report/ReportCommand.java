package games.kingdoms.kingdoms.publiccmds.report;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReportCommand implements CommandExecutor {

    final Kingdoms plugin = Kingdoms.getPlugin();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.GOLD + "Usage: /report <user> <reason>");
                return true;
            }

            if (args.length == 1) {
                player.sendMessage(ChatColor.GOLD + "Usage: /report <user> <reason>");
                return true;
            }

            if (args.length > 1) {
                Player target = Bukkit.getPlayer(args[0]);
                String message = String.join(" ", args);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (plugin.getStaff().containsKey(p.getUniqueId().toString())) {
                        //todo: Make this show a report to all online staff members
                        p.sendMessage();
                    }
                }
            }

        }
        return true;
    }
}
