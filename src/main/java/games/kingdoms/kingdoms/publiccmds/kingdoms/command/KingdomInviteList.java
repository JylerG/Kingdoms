package games.kingdoms.kingdoms.publiccmds.kingdoms.command;

import com.github.sanctum.panther.file.Configurable;
import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.admin.configs.KingdomsConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class KingdomInviteList implements TabCompleter {

    final Kingdoms plugin = Kingdoms.getPlugin();

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("join")) {
                    List<String> invites = new ArrayList<>();
                    if (!player.hasPermission("kingdoms.admin.join")) {
                        if (plugin.getInviteList().containsKey(player.getUniqueId().toString())) {
                            for (Map.Entry<String, String> invitedPlayer : plugin.getInviteList().entrySet()) {
                                invites.add(plugin.getInviteList().get(player.getUniqueId().toString()));
                            }
                        }
                        return null;
                    }

                    Configurable kc = KingdomsConfig.getInstance().getConfig();

                    // Process online players
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (plugin.getKingdoms().containsKey(p.getUniqueId().toString())) {
                            String kingdom = kc.getNode("kingdoms." + p.getUniqueId().toString()).toPrimitive().getString();
                            if (kingdom != null && !kingdom.isEmpty()) {
                                invites.add(kingdom);
                            } else {
                                invites.remove(kingdom);
                            }
                        }
                    }

                    // Process offline players
                    for (OfflinePlayer offline : Bukkit.getOfflinePlayers()) {
                        // Ensure the section is not null
                        if (plugin.getKingdoms().containsKey(offline.getUniqueId().toString())) {
                            String kingdom = kc.getNode("kingdoms." + offline.getUniqueId().toString()).toPrimitive().getString();
                            if (kingdom != null && !kingdom.isEmpty()) {
                                invites.add(kingdom);
                            } else {
                                invites.remove(kingdom);
                            }
                        }
                    }
                    return invites;
                }
            }
        }
        return null;
    }
}
