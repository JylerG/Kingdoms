package games.kingdoms.kingdoms.publiccmds.kingdoms.command;

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

public class KingdomInviteList implements TabCompleter {

    private final Kingdoms plugin = Kingdoms.getPlugin();

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("join")) {
                    List<String> invites = new ArrayList<>();
                    if (plugin.getInviteList().containsKey(player.getUniqueId().toString())) {
                        for (Map.Entry<String, String> invitedPlayer : plugin.getInviteList().entrySet()) {
                            invites.add(plugin.getInviteList().get(player.getUniqueId().toString()));
                        }
                        return invites;
                    }
                }
            }
        }

        return null;
    }
}
