package games.kingdoms.kingdoms.admin.balance;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EconomyTabCompleter implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        switch (args.length) {
            case 0:
                List<String> eco = new ArrayList<>();
                eco.add("eco");
                return eco;
            case 1:
                List<String> options = new ArrayList<>();
                options.add("give");
                options.add("set");
                options.add("remove");
                return options;
            case 2:
                List<String> player = new ArrayList<>();
                player.add("*");
                for (Player p : Bukkit.getOnlinePlayers()) {
                    player.add(p.getName());
                }
                return player;
            case 3:
                List<String> amount = new ArrayList<>();
                amount.add("<amount>");
                return amount;

        }
        return null;
    }
}
