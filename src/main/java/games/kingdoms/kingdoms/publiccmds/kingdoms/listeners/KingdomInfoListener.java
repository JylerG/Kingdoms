package games.kingdoms.kingdoms.publiccmds.kingdoms.listeners;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class KingdomInfoListener implements Listener {

    final Kingdoms plugin = Kingdoms.getPlugin();
    final HashMap<String, String> kingdoms = plugin.getKingdoms();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        ClickType clickType = event.getClick();
        ItemStack item = event.getCurrentItem();
        event.setCancelled(true);

        ItemStack blackBorder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta blackBorderMeta = blackBorder.getItemMeta();
        blackBorderMeta.setDisplayName(" ");
        blackBorder.setItemMeta(blackBorderMeta);

        switch (item.getType()) {


            //TODO: Make a skull with a wood/question mark on it to go back to the main page
            case IRON_SWORD:
                //TODO: Implement this when ready to add raids into the game
                break;
            case WHITE_BED:
                //TODO: kingdom set (right click)/goto (left click) spawn
                if (clickType == ClickType.LEFT) {
                    Bukkit.dispatchCommand(player, "kingdom spawn");
                } else if (clickType == ClickType.RIGHT) {
                    Bukkit.dispatchCommand(player, "kingdom set spawn");
            }
                break;
            case GOLD_BLOCK:
                //TODO: go to rank page
                if (clickType == ClickType.LEFT) {

                }
                break;
            case WITHER_SKELETON_SKULL:
                //TODO: figure out what this is
                break;
            case COBBLESTONE_WALL:
                //TODO: figure out what this is
                break;
            case TNT:
                //TODO: figure out what this is
                break;
        }
    }
}
