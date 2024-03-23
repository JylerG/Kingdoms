package games.kingdoms.kingdoms.publiccmds.kingdoms;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class KingdomsListener implements Listener {

    private final Kingdoms plugin;

    public KingdomsListener(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            Chunk chunk = event.getClickedBlock().getChunk();
            String chunkID = chunk.getX() + "," + chunk.getZ();

            if (plugin.getClaimedChunks().containsKey(chunkID)) {
                Player player = event.getPlayer();
                if (!plugin.getClaimedChunks().get(chunkID).equals(plugin.getKingdoms().get(player.getUniqueId().toString()))) {
                    if (!player.hasPermission("kingdoms.claim.bypass")) {
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.RED + "You do not have permission to do that");
                    }
                }
            }
        }
    }
}
