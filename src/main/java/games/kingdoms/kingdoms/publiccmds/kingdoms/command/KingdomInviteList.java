package games.kingdoms.kingdoms.publiccmds.kingdoms.command;

import com.github.sanctum.panther.file.Configurable;
import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.admin.configs.KingdomsConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
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
                        processPlayerInvites(kc, p.getUniqueId());
                    }

                    // Process offline players
                    for (OfflinePlayer offline : Bukkit.getOfflinePlayers()) {
                        processPlayerInvites(kc, offline.getUniqueId());
                    }
                    return invites;
                }
            }
        }
        return null;
    }

    // Function to process players
    private void processPlayerInvites(Configurable kc, UUID playerUUID) {
        String nodePath = "kingdoms." + playerUUID.toString();
        ConfigurationSection section = kc.getNode(nodePath).get(ConfigurationSection.class);

        Set<String> invites = new HashSet<>();

        // Ensure the section is not null
        if (section != null) {
            section.getKeys(false).forEach(key -> {
                String kingdom = kc.getNode(nodePath).toPrimitive().getString();
                if (kingdom != null && !kingdom.isEmpty()) {
                    invites.add(key);
                } else {
                    invites.remove(key);
                }
            });
        }
    }
}
