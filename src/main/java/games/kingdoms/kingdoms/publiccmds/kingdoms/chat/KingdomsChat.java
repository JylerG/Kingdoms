package games.kingdoms.kingdoms.publiccmds.kingdoms.chat;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class KingdomsChat implements CommandExecutor {

    final Kingdoms plugin = Kingdoms.getPlugin(Kingdoms.class);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (!plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
                MessageManager.playerBad(player, "You are not in a kingdom");
                return true;
            }

            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "You must have at least one argument to send a message to " + ChatColor.WHITE + plugin.getKingdoms().get(player.getUniqueId().toString()) + ChatColor.RED + " 's chat");
                return true;
            }

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (plugin.getKingdoms().containsKey(p.getUniqueId().toString())) {
                    if (plugin.getKingdoms().get(p.getUniqueId().toString()).equals(plugin.getKingdoms().get(player.getUniqueId().toString()))) {

                        String message = String.join(" ", args); // Concatenate args into a single string

                        if (plugin.getOwner().containsKey(player.getUniqueId().toString())) {
                            String format = ChatColor.GOLD.toString() + ChatColor.BOLD + "[K] " + ChatColor.LIGHT_PURPLE + "King " + ChatColor.GOLD + player.getName() + ": " + message;
                            p.sendMessage(format);
                        } else if (plugin.getAdmin().containsKey(player.getUniqueId().toString())) {
                            String format = ChatColor.GOLD.toString() + ChatColor.BOLD + "[K] " + ChatColor.LIGHT_PURPLE + "Knight " + ChatColor.GOLD + player.getName() + ": " + message;
                            p.sendMessage(format);
                        } else if (plugin.getMember().containsKey(player.getUniqueId().toString()) && !plugin.getOwner().containsKey(player.getUniqueId().toString())) {
                            String format = ChatColor.GOLD.toString() + ChatColor.BOLD + "[K] " + ChatColor.LIGHT_PURPLE + "Citizen " + ChatColor.GOLD + player.getName() + ": " + message;
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
