package games.kingdoms.kingdoms.publiccmds.kingdoms.listeners;

import com.github.sanctum.panther.file.Configurable;
import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import games.kingdoms.kingdoms.admin.configs.KingdomsConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;

public class KingdomUpgradeListener implements Listener {

    final Kingdoms plugin = Kingdoms.getPlugin();
     HashMap<String, String> kingdoms = plugin.getKingdoms();
    final Configurable kc = KingdomsConfig.getInstance().getConfig();

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
        } else if (event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY.toString() + kingdoms.get(player.getUniqueId().toString()))) {
            event.setCancelled(true);
            switch (event.getCurrentItem().getType()) {
                //TODO: Make a skull with a wood/question mark on it to go back to the main page
                case IRON_SWORD:
                    //TODO: Implement this when ready to add raids into the game
                    break;
                case WHITE_BED:
                    //TODO: kingdom set (right click)/goto (left click) spawn
                    break;
                case GOLD_BLOCK:
                    //TODO: go to rank page
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
}
