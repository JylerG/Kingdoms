package games.kingdoms.kingdoms.admin.ranks;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RankTabCompleter implements TabCompleter {

    private final Kingdoms plugin;

    public RankTabCompleter(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("kingdoms.setrank.default") || sender.hasPermission("kingdoms.setrank.vip") || sender.hasPermission("kingdoms.setrank.hero") || sender.hasPermission("kingdoms.setrank.jrmod") || sender.hasPermission("kingdoms.setrank.mod") || sender.hasPermission("kingdoms.setrank.srmod") || sender.hasPermission("kingdoms.setrank.jradmin") || sender.hasPermission("kingdoms.setrank.admin")) {

                if (args.length == 1) {
                    List<String> set = new ArrayList<>();
                    set.add("set");
                    return set;
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
