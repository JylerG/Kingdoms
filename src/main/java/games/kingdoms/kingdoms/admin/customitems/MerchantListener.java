package games.kingdoms.kingdoms.admin.customitems;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MerchantListener implements Listener {

    private final Kingdoms plugin;

    public MerchantListener(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Merchant (Page 1)")) {
            event.setCancelled(true);
        }
    }

}
