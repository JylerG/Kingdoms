package games.kingdoms.kingdoms.admin.vanish.commands;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VanishCMD implements CommandExecutor {

    Kingdoms plugin;

    public VanishCMD(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("kingdoms.vanish")) {
                if (plugin.getInvisiblePlayers().contains(player)) {
                    for (Player online_players : Bukkit.getOnlinePlayers()) {
                        online_players.showPlayer(plugin, player);
                    }
                    plugin.getInvisiblePlayers().remove(player);
                    player.sendMessage("§7Vanish §cDisabled");
                } else if (!plugin.getInvisiblePlayers().contains(player)) {
                    for (Player online_players : Bukkit.getOnlinePlayers()) {
                        if (online_players.hasPermission("kingdoms.vanish")) {
                            online_players.showPlayer(plugin, player);
                        } else {
                            online_players.hidePlayer(plugin, player);
                        }
                    }
                    plugin.getInvisiblePlayers().add(player);
                    player.sendMessage("§7Vanish §aEnabled");
                }
            }
        }
        return true;
    }
}
