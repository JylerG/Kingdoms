package games.kingdoms.kingdoms.publiccmds.teleports;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class WarzoneCommandListener implements Listener {

    private final Kingdoms plugin;
    private Player player;

    public WarzoneCommandListener(Kingdoms plugin) {
        this.plugin = plugin;
    }

    public WarzoneCommandListener(Kingdoms plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    public void start() {
        try {
            for (int i = 10; i > -1; i--) {
                plugin.sendTitle(player, " ", ChatColor.GREEN.toString() + ChatColor.BOLD + "Teleporting to Warzone in " + ChatColor.WHITE + ChatColor.BOLD + i, 10, 20, 10);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            player.sendTitle("", ChatColor.RED + "Countdown canceled", 0, 20, 10);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getPlayer().equals(player)) {
            player.sendTitle(" ", ChatColor.RED + "Countdown canceled", 0, 20, 10);
        }
    }
}
