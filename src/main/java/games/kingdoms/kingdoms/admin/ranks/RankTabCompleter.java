package games.kingdoms.kingdoms.admin.ranks;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RankTabCompleter implements TabCompleter {

    final Kingdoms plugin = Kingdoms.getPlugin();

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {

            if (sender.hasPermission("kingdoms.setrank.default")
                    || sender.hasPermission("kingdoms.setrank.vip")
                    || sender.hasPermission("kingdoms.setrank.hero")
                    || sender.hasPermission("kingdoms.setrank.jrmod")
                    || sender.hasPermission("kingdoms.setrank.mod")
                    || sender.hasPermission("kingdoms.setrank.srmod")
                    || sender.hasPermission("kingdoms.setrank.jradmin")
                    || sender.hasPermission("kingdoms.setrank.admin")) {

                if (args.length == 1) {

                    if (sender.hasPermission("kingdoms.setrank.fake") && sender instanceof Player player) {
                        List<String> reset = new ArrayList<>();
                        if ((plugin.getStaff().get(player.getUniqueId().toString()).equalsIgnoreCase("SRMOD")
                                && !plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD))
                                || (plugin.getStaff().get(player.getUniqueId().toString()).equalsIgnoreCase("JRADMIN")
                                && !plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN))
                                || (plugin.getStaff().get(player.getUniqueId().toString()).equalsIgnoreCase("ADMIN")
                                && !plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN))) {
                            reset.add("reset");

                            return reset;
                        }
                    }
                }

                if (args.length == 2) {
                    if (sender.hasPermission("kingdoms.setrank.fake") && sender instanceof Player) {
                        if (args[0].equalsIgnoreCase("fake")) {
                            List<String> rank = new ArrayList<>();
                            rank.add("<rank>");
                            return rank;
                        }
                    }
                }

                if (args.length == 3) {
                    List<String> ranks = new ArrayList<>();
                    if (sender instanceof Player player) {
                        ranks.add("<rank>");
                    } else {
                        ranks.add("default");
                        ranks.add("vip");
                        ranks.add("hero");
                        ranks.add("youtube");
                        ranks.add("jrmod");
                        ranks.add("mod");
                        ranks.add("srmod");
                        ranks.add("jradmin");
                        ranks.add("admin");
                    }

                    return ranks;
                }
            }
        return null;
    }
}
