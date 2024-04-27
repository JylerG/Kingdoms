package games.kingdoms.kingdoms.admin.vanish.events;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private Kingdoms plugin;

    public JoinEvent(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        for (int i = 0; i < plugin.getInvisiblePlayers().size(); i++) {
            p.hidePlayer(plugin, plugin.getInvisiblePlayers().get(i));
        }
    }
}
