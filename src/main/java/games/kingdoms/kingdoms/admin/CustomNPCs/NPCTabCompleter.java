package games.kingdoms.kingdoms.admin.CustomNPCs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NPCTabCompleter implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if (args.length == 1) {
            List<String> merchants = new ArrayList<>();
            merchants.add("nature_merchant");
            merchants.add("guide");
            merchants.add("guide_brother");
            merchants.add("postbox");
            merchants.add("merchant");
            merchants.add("glass_seller");
            merchants.add("schematic_seller");
            merchants.add("forsaken_forge");
            return merchants;
        }

        return null;
    }
}
