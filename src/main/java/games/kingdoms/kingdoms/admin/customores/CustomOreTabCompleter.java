package games.kingdoms.kingdoms.admin.customores;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CustomOreTabCompleter implements TabCompleter {

    private final Kingdoms plugin;

    public CustomOreTabCompleter(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {

        if (args.length == 1) {
            List<String> give = new ArrayList<>();
            give.add("give");
            return give;
        }
        if (args.length == 2) {
            List<String> ores = new ArrayList<>();
            ores.add("refined_coal_ore");
            ores.add("refined_iron_ore");
            ores.add("refined_diamond_ore");
            ores.add("refined_gold_ore");
            ores.add("warzone_diamond_ore");
            ores.add("warzone_emerald_ore");
            ores.add("warzone_gold_ore");
            ores.add("warzone_iron_ore");
            ores.add("warzone_coal_ore");
            ores.add("refined_emerald_ore");
            return ores;
        }

        if (args.length == 3) {
            List<String> amount = new ArrayList<>();
            amount.add("<amount>");
            return amount;
        }
        return null;
    }
}
