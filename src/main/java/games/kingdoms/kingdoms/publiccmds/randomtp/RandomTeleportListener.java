package games.kingdoms.kingdoms.publiccmds.randomtp;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

public class RandomTeleportListener implements Listener {

    public static Kingdoms plugin;

    public static HashSet<Material> bad_blocks = new HashSet<>();

    static {
        bad_blocks.add(Material.LAVA);
        bad_blocks.add(Material.FIRE);
        bad_blocks.add(Material.CACTUS);
//        bad_blocks.add(Material.WATER);
    }

    public RandomTeleportListener(Kingdoms plugin) {
        RandomTeleportListener.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory == null || !clickedInventory.equals(event.getInventory()) || !event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Random TP")) {
            return;
        }

        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }

        ItemMeta itemMeta = clickedItem.getItemMeta();
        String displayName = itemMeta.getDisplayName();

        if (displayName != null) {
            if (displayName.equals(ChatColor.GREEN.toString() + ChatColor.BOLD + "North")) {
                teleportPlayer(player, "North");
                player.sendMessage(ChatColor.GREEN + "You have been teleported " + ChatColor.WHITE + "North");
            } else if (displayName.equals(ChatColor.GREEN.toString() + ChatColor.BOLD + "South")) {
                teleportPlayer(player, "South");
                player.sendMessage(ChatColor.GREEN + "You have been teleported " + ChatColor.WHITE + "South");
            } else if (displayName.equals(ChatColor.GREEN.toString() + ChatColor.BOLD + "West")) {
                teleportPlayer(player, "West");
                player.sendMessage(ChatColor.GREEN + "You have been teleported " + ChatColor.WHITE + "West");
            } else if (displayName.equals(ChatColor.GREEN.toString() + ChatColor.BOLD + "East")) {
                teleportPlayer(player, "East");
                player.sendMessage(ChatColor.GREEN + "You have been teleported " + ChatColor.WHITE + "East");
            } else if (displayName.equals(ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Randomly Teleport")) {
                player.teleport(generateLocation(player));
                player.sendMessage(ChatColor.GREEN + "You were randomly teleported");
            }
        }

        event.setCancelled(true);
    }


    private void teleportPlayer(Player player, String direction) {
        Random random = new Random();
            Location destination = generateLocation(player);

            // Check if the destination block is in the set of bad_blocks
            if (destination != null && !bad_blocks.contains(destination.getBlock().getType())) {
                // Find the highest solid block at the destination's X and Z coordinates
                int highestY = getHighestY(destination);
                destination.setY(highestY);

                player.teleport(destination);
            }
    }

    public static Location generateLocation(Player player) {
        Random random = new Random();

        int x = random.nextInt(100000) + 1;
        int y = 1;
        int z = random.nextInt(100000) + 1;

        Location randomLocation = new Location(player.getWorld(), x, y, z);

        y = Objects.requireNonNull(randomLocation.getWorld()).getHighestBlockYAt(randomLocation);
        randomLocation.setY(y + 1);

        player.teleport(randomLocation);

        while (!isLocationSafe(randomLocation)) {
            randomLocation = generateLocation(player);
        }
        return randomLocation;
    }

    public static boolean isLocationSafe(Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Block block = Objects.requireNonNull(location.getWorld()).getBlockAt(x, y, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);

        return !(bad_blocks.contains(block.getType()) || block.getType().isSolid() || above.getType().isSolid());
    }

    private int getHighestY(Location location) {
        int highestY = location.getWorld().getMaxHeight();

        for (int y = location.getWorld().getMaxHeight(); y >= 0; y--) {
            Location checkLocation = new Location(location.getWorld(), location.getX(), y, location.getZ());

            if (checkLocation.getBlock().getType().isSolid()) {
                highestY = y;
                break;
            }
        }

        return highestY;
    }

}
