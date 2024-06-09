package games.kingdoms.kingdoms.publiccmds.kingdoms.chat;

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

public class KingdomsChat implements CommandExecutor {

    private final Kingdoms plugin = Kingdoms.getPlugin(Kingdoms.class);

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

                    String message = String.join(" ", args); // Concatenate args into a single string

                    String rank = plugin.getPlayerRank().get(player.getUniqueId().toString());

                    //Player has Default Rank
                    if (rank.equals(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                        String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[K] " + ChatColor.WHITE + player.getDisplayName() + ChatColor.AQUA + ": " + ChatColor.LIGHT_PURPLE + message;
                        p.sendMessage(format);
                    }
                    //Player has VIP Rank
                    if (rank.equals(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                        String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[K] " + ChatColor.GREEN + player.getDisplayName() + ChatColor.AQUA + ": " + ChatColor.LIGHT_PURPLE + message;
                        p.sendMessage(format);
                    }
                    //Player has Hero Rank
                    if (rank.equals(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                        String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[K] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.AQUA + ": " + ChatColor.LIGHT_PURPLE + message;
                        p.sendMessage(format);
                    }
                    //Player has JrMod Rank
                    if (rank.equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                        String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[K] " + ChatColor.DARK_AQUA + player.getDisplayName() + ChatColor.AQUA + ": " + ChatColor.LIGHT_PURPLE + message;
                        p.sendMessage(format);
                    }
                    //Player has Mod Rank
                    if (rank.equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                        String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[K] " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.AQUA + ": " + ChatColor.LIGHT_PURPLE + message;
                        p.sendMessage(format);
                    }
                    //Player has SrMod Rank
                    if (rank.equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                        String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[K] " + ChatColor.GOLD + player.getDisplayName() + ChatColor.AQUA + ": " + ChatColor.LIGHT_PURPLE + message;
                        p.sendMessage(format);
                    }
                    //Player has JrAdmin Rank
                    if (rank.equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                        String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[K] " + ChatColor.DARK_RED + player.getDisplayName() + ChatColor.AQUA + ": " + ChatColor.LIGHT_PURPLE + message;
                        p.sendMessage(format);
                    }
                    //Player has Admin Rank
                    if (rank.equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                        String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[K] " + ChatColor.DARK_RED + player.getDisplayName() + ChatColor.AQUA + ": " + ChatColor.LIGHT_PURPLE + message;
                        p.sendMessage(format);
                    }
                }
            }
        } else {
            MessageManager.consoleBad("You must be a player to execute this command.");
        }

        return true;
    }
}
