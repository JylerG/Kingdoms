package games.kingdoms.kingdoms.publiccmds.kingdoms.listeners;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class KingdomUpgradeListener implements Listener {

    final Kingdoms plugin;

    public KingdomUpgradeListener(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + plugin.getKingdoms().get(player.getUniqueId().toString()) + " Upgrades")) {
            event.setCancelled(true);
            switch (event.getCurrentItem().getType()) {
                case GREEN_WOOL:
                    if (plugin.getMoney().get(player.getUniqueId().toString()) >= plugin.getClaimPrice().get(plugin.getKingdoms().get(player.getUniqueId().toString()))) {
                        plugin.getMaxClaims().put(plugin.getKingdoms().get(player.getUniqueId().toString()),
                                plugin.getMaxClaims().get(plugin.getKingdoms().get(player.getUniqueId().toString())) + 1);
                        plugin.getMoney().put(player.getUniqueId().toString(), plugin.getMoney().get(player.getUniqueId().toString()) -
                                plugin.getClaimPrice().get(plugin.getKingdoms().get(player.getUniqueId().toString())));
                        plugin.getClaimPrice().put(plugin.getKingdoms().get(player.getUniqueId().toString()), plugin.getClaimPrice().get(plugin.getKingdoms().get(player.getUniqueId().toString())) + 2000);
                        player.sendMessage(plugin.getKingdoms().get(player.getUniqueId().toString()) + ChatColor.GREEN + "'s claims increased to "
                                + ChatColor.WHITE + plugin.getMaxClaims().get(plugin.getKingdoms().get(player.getUniqueId().toString())));
                    } else {
                        MessageManager.playerBad(player, "You do not have enough money to upgrade this");
                    }
                    break;
                case LIME_WOOL:
                    if (plugin.getMoney().get(player.getUniqueId().toString()) >= plugin.getMemberPrice().get(plugin.getKingdoms().get(player.getUniqueId().toString()))) {
                        plugin.getMaxMembers().put(plugin.getKingdoms().get(player.getUniqueId().toString()),
                                plugin.getMaxMembers().get(plugin.getKingdoms().get(player.getUniqueId().toString())) + 1);
                        plugin.getMoney().put(player.getUniqueId().toString(), plugin.getMoney().get(player.getUniqueId().toString()) -
                                plugin.getMemberPrice().get(plugin.getKingdoms().get(player.getUniqueId().toString())));
                        plugin.getMemberPrice().put(plugin.getKingdoms().get(player.getUniqueId().toString()), plugin.getMemberPrice().get(plugin.getKingdoms().get(player.getUniqueId().toString())) + 2000);
                        player.sendMessage(plugin.getKingdoms().get(player.getUniqueId().toString()) + ChatColor.GREEN + "'s members increased to "
                                + ChatColor.WHITE + plugin.getMaxMembers().get(plugin.getKingdoms().get(player.getUniqueId().toString())));
                    } else {
                        MessageManager.playerBad(player, "You do not have enough money to upgrade this");
                    }
                    break;
            }
        }
    }
}
