package games.kingdoms.kingdoms.publiccmds.kingdoms.listeners;

import com.github.sanctum.panther.file.Configurable;
import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import games.kingdoms.kingdoms.admin.configs.KingdomsConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.util.*;

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

        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta headMeta = head.getItemMeta();
        headMeta.setDisplayName(ChatColor.GOLD + "Click to go back to " + ChatColor.WHITE + kingdoms.get(player.getUniqueId().toString()) + ChatColor.GOLD + " info");
        head.setItemMeta(headMeta);

        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + kingdoms.get(player.getUniqueId().toString()))) {

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
                        // Create the inventory with 54 slots
                        Inventory rank = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + kingdoms.get(player.getUniqueId().toString()) + " -> Ranks");

                        // Set up borders for the inventory (this assumes `blackBorder` is an ItemStack representing the border)
                        for (int i : new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53}) {
                            rank.setItem(i, blackBorder);
                        }

                        rank.setItem(0, head);

                        // Get the kingdom of the player
                        String kingdom = kingdoms.get(player.getUniqueId().toString());
                        Configurable kc = KingdomsConfig.getInstance().getConfig();

                        // Create a map to store player UUID and their respective rank
                        Map<String, Integer> playerRanks = new HashMap<>();

                        // Fetch online players in the kingdom
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                            if (kingdom.equals(kingdoms.get(onlinePlayer.getUniqueId().toString()))) {
                                // Retrieve the rank from the configuration
                                int playerRank = getPlayerRank(onlinePlayer.getUniqueId());

                                // Store the player's UUID and rank in the map
                                playerRanks.put(onlinePlayer.getUniqueId().toString(), playerRank);
                            }
                        }

                        // Fetch offline players in the kingdom
                        kc.getNode(kingdom).getKeys(false).forEach(key -> {
                            UUID playerUUID = UUID.fromString(key);  // Convert key to UUID
                            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);

                            // Retrieve the rank from the configuration
                            int playerRank = getPlayerRank(offlinePlayer.getUniqueId());

                            // Store the player's UUID and rank in the map
                            playerRanks.put(offlinePlayer.getUniqueId().toString(), playerRank);
                        });

                        // Sort the player ranks based on the integer rank value
                        List<Map.Entry<String, Integer>> sortedRanks = new ArrayList<>(playerRanks.entrySet());
                        sortedRanks.sort(Comparator.comparingInt(Map.Entry::getValue));

                        // Create the inventory and place the player heads based on rank
                        int slot = 0;
                        for (Map.Entry<String, Integer> entry : sortedRanks) {
                            String playerUUID = entry.getKey();
                            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);

                            // Create the player head item
                            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
                            SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();

                            if (skullMeta != null) {
                                // For online players, set OwningPlayer using PlayerProfile
                                if (Bukkit.getPlayer(playerUUID) != null) {
                                    Player onlinePlayer = Bukkit.getPlayer(playerUUID);
                                    PlayerProfile profile = onlinePlayer.getPlayerProfile(); // Get PlayerProfile

                                    // Set the player's head using the profile
                                    skullMeta.setOwningPlayer(onlinePlayer);  // This uses the PlayerProfile internally
                                } else {
                                    // For offline players, use the OfflinePlayer object
                                    skullMeta.setOwningPlayer(offlinePlayer);  // Offline player handling
                                }
                                playerHead.setItemMeta(skullMeta); // Apply the meta to the skull item
                            }

                            // Place the skull in the next available slot
                            if (slot < rank.getSize()) {
                                rank.setItem(slot, playerHead);
                                slot++; // Increment the slot for the next player
                            }
                        }

                        // Send the inventory to the player
                        player.openInventory(rank);

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
        } else if (event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + kingdoms.get(player.getUniqueId().toString()) + " -> Ranks")) {

        }
    }

    // Method to get the player's rank (you'll need to implement the logic for fetching the rank)
    private int getPlayerRank(UUID playerUUID) {
        // Replace this with your logic to fetch the player's rank
        // Find the rank integer stored under the player's UUID
        int playerRank = -1;
        for (String key : KingdomsConfig.getInstance().getConfig().getNode(String.valueOf(playerUUID)).getKeys(false)) {
            try {
                playerRank = Integer.parseInt(key);
                MessageManager.consoleInfo("Key in getPlayerRank(UUID playerUUID): " + key + " (" + playerRank + ")");
                break; // Stop at the first found rank
            } catch (NumberFormatException ignored) {
                // Ignore non-numeric keys
            }
        }
        return playerRank;
    }
}
