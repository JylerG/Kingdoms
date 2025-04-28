package games.kingdoms.kingdoms.publiccmds.kingdoms.listeners;

import com.github.sanctum.panther.file.Configurable;
import com.github.sanctum.panther.file.Node;
import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.admin.configs.KingdomsConfig;
import games.kingdoms.kingdoms.publiccmds.kingdoms.related.KingdomRankCache;
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
    final HashMap<String, Integer> playerRanks = plugin.getPlayerRanks();

    public ItemStack getOfflinePlayerHead(String uuid) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        if (skullMeta != null) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
            skullMeta.setOwningPlayer(offlinePlayer); // <-- This will auto-fetch their skin
            skullMeta.setDisplayName(ChatColor.GOLD + uuid + "'s Head");
            skull.setItemMeta(skullMeta);
        }
        return skull;
    }


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

        if (item == null) return;

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
                    //todo: rank page
                    if (clickType == ClickType.LEFT) {
                        String kingdom = kingdoms.get(player.getUniqueId().toString());
                        Configurable kc = KingdomsConfig.getInstance().getConfig();

                        Inventory rankInventory = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + kingdom + " -> Ranks");

                        // Set up borders automatically
                        for (int i = 0; i < rankInventory.getSize(); i++) {
                            if (i < 9 || i >= 45 || i % 9 == 0 || i % 9 == 8) {
                                rankInventory.setItem(i, blackBorder);
                            }
                        }

                        // Set the back button or "head" at slot 0
                        rankInventory.setItem(0, head);

                        // Get all ranks for this kingdom
                        Map<Integer, String> ranks = new HashMap<>();

                        Node ranksNode = kc.getNode("ranksInKingdoms").getNode(kingdom);
                        if (ranksNode != null && ranksNode.getKeys(false) != null) {
                            for (String key : ranksNode.getKeys(false)) {
                                try {
                                    int rankNumber = Integer.parseInt(key); // Convert "1" -> 1
                                    String rankName = ranksNode.getNode(key).get(String.class); // get rank name
                                    if (rankName != null) {
                                        ranks.put(rankNumber, rankName);
                                    }
                                } catch (NumberFormatException e) {
                                    Bukkit.getLogger().warning("Invalid rank key '" + key + "' in kingdom '" + kingdom + "'");
                                }
                            }
                        }

                        // Sort ranks from lowest to highest
                        List<Map.Entry<Integer, String>> sortedRanks = new ArrayList<>(ranks.entrySet());
                        sortedRanks.sort(Comparator.comparingInt(Map.Entry::getKey));

                        // Now fill the inventory with beacons
                        int slot = 10;
                        for (Map.Entry<Integer, String> entry : sortedRanks) {
                            while (slot < rankInventory.getSize() && (slot % 9 == 0 || slot % 9 == 8)) {
                                slot++; // Skip border columns
                            }

                            ItemStack beacon = new ItemStack(Material.BEACON);
                            ItemMeta meta = beacon.getItemMeta();
                            if (meta != null) {
                                meta.setDisplayName(ChatColor.GOLD + entry.getValue()); // Set rank name as title
                                beacon.setItemMeta(meta);
                            }

                            if (slot < rankInventory.getSize()) {
                                rankInventory.setItem(slot, beacon);
                                slot++;
                            }
                        }

                        player.openInventory(rankInventory);
                    }
                    break;
                case WITHER_SKELETON_SKULL:
                    //TODO: Players page
                    if (clickType == ClickType.LEFT) {
                        // Create the inventory
                        Inventory players = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + kingdoms.get(player.getUniqueId().toString()) + " -> Ranks");

                        // Set borders
                        for (int i : new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53}) {
                            players.setItem(i, blackBorder);
                        }
                        players.setItem(0, head);

                        // Load player's kingdom
                        String kingdom = kingdoms.get(player.getUniqueId().toString());

                        // Use preexisting playerRanks
                        HashMap<String, Integer> playerRanks = plugin.getPlayerRanks(); // <--- your existing map!

                        // Build a new map of only players from this kingdom
                        Map<String, Integer> kingdomPlayers = new HashMap<>();

                        for (Map.Entry<String, Integer> entry : playerRanks.entrySet()) {
                            String uuidString = entry.getKey();
                            UUID uuid = UUID.fromString(uuidString);

                            if (kingdom.equals(kingdoms.get(uuidString))) {
                                kingdomPlayers.put(uuidString, entry.getValue());
                            }
                        }

                        // Sort by rank (lowest to highest)
                        List<Map.Entry<String, Integer>> sortedRanks = new ArrayList<>(kingdomPlayers.entrySet());
                        sortedRanks.sort(Comparator.comparingInt(Map.Entry::getValue));

                        // Fill the inventory
                        int slot = 10;
                        for (Map.Entry<String, Integer> entry : sortedRanks) {
                            String playerKey = entry.getKey();
                            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(UUID.fromString(playerKey));

                            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
                            SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();

                            if (skullMeta != null) {
                                PlayerProfile profile;
                                Player onlinePlayer = Bukkit.getPlayer(UUID.fromString(playerKey));
                                if (onlinePlayer != null) {
                                    profile = onlinePlayer.getPlayerProfile();
                                } else {
                                    profile = Bukkit.createPlayerProfile(offlinePlayer.getUniqueId(), offlinePlayer.getName());
                                }

                                // Fetch the player's rank integer
                                Integer playerRankInt = KingdomRankCache.getPlayerRank(offlinePlayer.getUniqueId());
                                if (playerRankInt == null) {
                                    playerRankInt = 0; // fallback if needed
                                }

                                // Fetch the rank name from config (even if not used directly here)
                                Configurable kc = KingdomsConfig.getInstance().getConfig();
                                String rankName = kc.getNode("ranksInKingdoms." +
                                        kingdoms.get(player.getUniqueId().toString())
                                        + "." + playerRankInt).toPrimitive().getString();

                                // Set profile
                                skullMeta.setOwnerProfile(profile);

                                // Set display name: player name in YELLOW + BOLD
                                String playerName = (offlinePlayer.getName() != null) ? offlinePlayer.getName() : "Unknown";
                                skullMeta.setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + playerName);

                                // Set lore: "Rank: X"
                                List<String> skullLore = new ArrayList<>();
                                skullLore.add(ChatColor.WHITE + "Rank " + ChatColor.YELLOW + rankName);
                                skullLore.add(" ");
                                skullLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Click " + ChatColor.WHITE + "to manage player");

                                skullMeta.setLore(skullLore);

                                playerHead.setItemMeta(skullMeta);
                            }

                            if (slot < players.getSize()) {
                                players.setItem(slot, playerHead);
                                slot++;
                                if ((slot + 1) % 9 == 0) slot++; // Skip border slots
                            }
                        }

                        // Open the inventory
                        player.openInventory(players);
                    }
                    break;
                case COBBLESTONE_WALL:
                    //TODO: figure out what this is
                    break;
                case TNT:
                    //TODO: figure out what this is
                    break;

            }

            // Go to default menu if player clicks on Player Head in first slot
        } else if (event.getView().getTitle().startsWith(ChatColor.DARK_GRAY + kingdoms.get(player.getUniqueId().toString()))) {
            if (item.getType() == Material.PLAYER_HEAD && event.getSlot() == 0) {
                Bukkit.dispatchCommand(player, "k info");
            }
        }
    }
}