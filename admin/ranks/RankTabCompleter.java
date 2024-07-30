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

        if (sender instanceof Player player) {
            if (player.hasPermission("kingdoms.setrank.default")
                    || player.hasPermission("kingdoms.setrank.vip")
                    || player.hasPermission("kingdoms.setrank.hero")
                    || player.hasPermission("kingdoms.setrank.jrmod")
                    || player.hasPermission("kingdoms.setrank.mod")
                    || player.hasPermission("kingdoms.setrank.srmod")
                    || player.hasPermission("kingdoms.setrank.jradmin")
                    || player.hasPermission("kingdoms.setrank.admin")) {

                if (args.length == 1) {

                    if (player.hasPermission("kingdoms.setrank.fake")) {
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
                    if (player.hasPermission("kingdoms.setrank.fake")) {
                        if (args[0].equalsIgnoreCase("fake")) {
                            List<String> rank = new ArrayList<>();
                            rank.add("<rank>");
                            return rank;
                        }
                    }
                }

                if (args.length == 3) {
                    List<String> ranks = new ArrayList<>();
                    ranks.add("<rank>");

                    return ranks;
                }
            }
        }
        return null;
    }
}
