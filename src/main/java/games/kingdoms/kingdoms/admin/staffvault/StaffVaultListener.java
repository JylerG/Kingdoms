package games.kingdoms.kingdoms.admin.staffvault;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class StaffVaultListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Staff Vault")) {
            event.setCancelled(event.getSlot() == 4 || event.getSlot() == 13);
        }
    }
}
