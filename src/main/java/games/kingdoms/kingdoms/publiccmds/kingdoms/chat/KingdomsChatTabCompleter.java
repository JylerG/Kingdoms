package games.kingdoms.kingdoms.publiccmds.kingdoms.chat;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KingdomsChatTabCompleter implements TabCompleter {

    private final Kingdoms plugin;

    public KingdomsChatTabCompleter(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return null;
        }

        if (args.length > 0) {
            List<String> message = new ArrayList<>();

            message.add("<msg>");

            return message;
        }

        return null;
    }
}

