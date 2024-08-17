package games.kingdoms.kingdoms.admin.ranks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RankTabCompleter implements TabCompleter {

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

                    if (sender.hasPermission("kingdoms.setrank.fake") && sender instanceof Player) {
                        List<String> fake = new ArrayList<>();
                        fake.add("fake");
                        fake.add("set");

                        return fake;
                    }

                    List<String> set = new ArrayList<>();
                    set.add("set");
                    return set;
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
