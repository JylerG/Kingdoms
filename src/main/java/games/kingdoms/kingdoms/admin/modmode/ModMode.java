package games.kingdoms.kingdoms.admin.modmode;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ModMode implements CommandExecutor {

    final Kingdoms plugin = Kingdoms.getPlugin(Kingdoms.class);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (player.hasPermission("kingdoms.staff.modmode")) {
                if (!plugin.getModModePlayers().contains(player)) {
                    plugin.getModModePlayers().add(player);
                    player.setGameMode(GameMode.SPECTATOR);
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (plugin.getStaff().containsKey(p.getUniqueId().toString())) {
                            p.sendMessage(player.getName() + ChatColor.GRAY + " has entered modmode. They can now " +
                                    "interact with other players in spectator mode");
                        }
                    }
                } else {
                    plugin.getModModePlayers().remove(player);
                    player.setGameMode(GameMode.SURVIVAL);
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (plugin.getStaff().containsKey(p.getUniqueId().toString())) {
                            p.sendMessage(player.getName() + ChatColor.GRAY + " has left modmode");
                        }
                    }
                }
            }
        }
        return true;
    }
}
