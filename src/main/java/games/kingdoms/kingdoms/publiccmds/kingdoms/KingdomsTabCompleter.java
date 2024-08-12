package games.kingdoms.kingdoms.publiccmds.kingdoms;

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

public class KingdomsTabCompleter implements TabCompleter {

    private final Kingdoms plugin;

    public KingdomsTabCompleter(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return null;
        }
        Player player = (Player) sender;
        if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
            List<String> invites = new ArrayList<>();

            // Get all invites for the player
            Map<String, String> inviteList = plugin.getInviteList();
            for (Map.Entry<String, String> entry : inviteList.entrySet()) {
                if (entry.getKey().equals(player.getUniqueId().toString())) {
                    invites.add(entry.getValue());
                }
            }

            return invites;
        }

        return null;
    }
}

