package games.kingdoms.kingdoms.publiccmds.randomtp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

public class rtp implements CommandExecutor {

    public static HashSet<Material> bad_blocks = new HashSet<>();

    static {
        bad_blocks.add(Material.LAVA);
        bad_blocks.add(Material.FIRE);
        bad_blocks.add(Material.CACTUS);

    }

    public static Location generateLocation(Player player) {

        Random random = new Random();


        int x = random.nextInt(10000);
        int y = 1;
        int z = random.nextInt(10000);

        Location randomLocation = new Location(player.getWorld(), x, y, z);

        y = Objects.requireNonNull(randomLocation.getWorld()).getHighestBlockYAt(randomLocation);
        randomLocation.setY(y);

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

        return !(bad_blocks.contains(block.getType()) || (block.getType().isSolid()) || (above.getType().isSolid()));

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {

                if (player.getWorld().equals(Bukkit.getWorld("Kingdoms"))) {
                    Inventory randomTeleportMenu = Bukkit.createInventory(player, 45, ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Random TP");

                    ItemStack border = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                    ItemStack center = new ItemStack(Material.COMPASS);
                    ItemStack north = new ItemStack(Material.REDSTONE);
                    ItemStack south = new ItemStack(Material.REDSTONE);
                    ItemStack west = new ItemStack(Material.REDSTONE);
                    ItemStack east = new ItemStack(Material.REDSTONE);

                    ItemMeta borderMeta = border.getItemMeta();
                    borderMeta.setDisplayName(" ");
                    border.setItemMeta(borderMeta);

                    ItemMeta northMeta = north.getItemMeta();
                    northMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "North");
                    north.setItemMeta(northMeta);

                    ItemMeta southMeta = south.getItemMeta();
                    southMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "South");
                    south.setItemMeta(southMeta);

                    ItemMeta westMeta = west.getItemMeta();
                    westMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "West");
                    west.setItemMeta(westMeta);

                    ItemMeta eastMeta = east.getItemMeta();
                    eastMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "East");
                    east.setItemMeta(eastMeta);

                    ItemMeta centerMeta = center.getItemMeta();
                    centerMeta.setDisplayName(ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Randomly Teleport");
                    center.setItemMeta(centerMeta);

                    randomTeleportMenu.setItem(0, border);
                    randomTeleportMenu.setItem(1, border);
                    randomTeleportMenu.setItem(2, border);
                    randomTeleportMenu.setItem(3, border);
                    randomTeleportMenu.setItem(4, border);
                    randomTeleportMenu.setItem(5, border);
                    randomTeleportMenu.setItem(6, border);
                    randomTeleportMenu.setItem(7, border);
                    randomTeleportMenu.setItem(8, border);
                    randomTeleportMenu.setItem(9, border);
                    randomTeleportMenu.setItem(10, border);
                    randomTeleportMenu.setItem(11, border);
                    randomTeleportMenu.setItem(12, border);
                    randomTeleportMenu.setItem(13, north);
                    randomTeleportMenu.setItem(14, border);
                    randomTeleportMenu.setItem(15, border);
                    randomTeleportMenu.setItem(16, border);
                    randomTeleportMenu.setItem(17, border);
                    randomTeleportMenu.setItem(18, border);
                    randomTeleportMenu.setItem(19, border);
                    randomTeleportMenu.setItem(20, border);
                    randomTeleportMenu.setItem(21, west);
                    randomTeleportMenu.setItem(22, center);
                    randomTeleportMenu.setItem(23, east);
                    randomTeleportMenu.setItem(24, border);
                    randomTeleportMenu.setItem(25, border);
                    randomTeleportMenu.setItem(26, border);
                    randomTeleportMenu.setItem(27, border);
                    randomTeleportMenu.setItem(28, border);
                    randomTeleportMenu.setItem(29, border);
                    randomTeleportMenu.setItem(30, border);
                    randomTeleportMenu.setItem(31, south);
                    randomTeleportMenu.setItem(32, border);
                    randomTeleportMenu.setItem(33, border);
                    randomTeleportMenu.setItem(34, border);
                    randomTeleportMenu.setItem(35, border);
                    randomTeleportMenu.setItem(36, border);
                    randomTeleportMenu.setItem(37, border);
                    randomTeleportMenu.setItem(38, border);
                    randomTeleportMenu.setItem(39, border);
                    randomTeleportMenu.setItem(40, border);
                    randomTeleportMenu.setItem(41, border);
                    randomTeleportMenu.setItem(42, border);
                    randomTeleportMenu.setItem(43, border);
                    randomTeleportMenu.setItem(44, border);
                    player.openInventory(randomTeleportMenu);
                } else {
                    player.sendMessage(ChatColor.RED + "You must be in the" + ChatColor.WHITE + " kingdoms " + ChatColor.RED + "world to use this command");
                }
            }
        }
        return true;
    }
}
