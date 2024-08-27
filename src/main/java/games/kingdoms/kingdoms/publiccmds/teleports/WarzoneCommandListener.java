package games.kingdoms.kingdoms.publiccmds.teleports;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WarzoneCommandListener implements Listener {

    final Kingdoms plugin;
    private Player player;

    public WarzoneCommandListener(Kingdoms plugin) {
        this.plugin = plugin;
    }

    public WarzoneCommandListener(Kingdoms plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) throws InterruptedException {
        throw new InterruptedException();
    }

    public void start() {
        try {
            for (int i = 10; i > -1; i--) {
                if (i > 0) {
                    plugin.sendTitle(player, ChatColor.YELLOW.toString() + ChatColor.BOLD + i + "s", ChatColor.GREEN.toString() + ChatColor.BOLD + "Teleporting in...", 10, 20, 10);
                    Thread.sleep(1000);
                } else {
                    plugin.sendTitle(player, ChatColor.YELLOW.toString() + ChatColor.BOLD + "Now", ChatColor.GREEN.toString() + ChatColor.BOLD + "Teleporting in...", 10, 20, 10);
                    Thread.sleep(1000);
                }
            }
            World world = Bukkit.getWorld("warzone");
            Location loc = new Location(world, -940.5, 115, -607.5);
            player.teleport(loc);
        } catch (InterruptedException e) {
            plugin.sendTitle(player, " ", ChatColor.RED + "Countdown canceled", 0, 20, 10);
        }
    }
}
