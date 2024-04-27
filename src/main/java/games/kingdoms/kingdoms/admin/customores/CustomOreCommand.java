package games.kingdoms.kingdoms.admin.customores;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomOreCommand implements CommandExecutor, Listener {

    private final Kingdoms plugin;

    private final Map<Block, Material> originalBlockTypes = new HashMap<>();
    private final long restoreTime = 600 * 20; // 600 seconds in ticks

    public CustomOreCommand(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (!(sender instanceof Player)) {
            MessageManager.consoleBad("You must be a player to execute this command.");
        } else {
            Player player = (Player) sender;
            if (player.hasPermission("kingdoms.give"))
                switch (args.length) {
                    case 0:
                    case 1:
                        player.sendMessage(ChatColor.GOLD + "Usage: /ore give <block> [amount]");
                        break;
                    case 2:
                        if (args[1].equalsIgnoreCase("refined_coal_ore")) {
                            ItemStack refinedCoalOre = new ItemStack(Material.COAL_ORE, 1);
                            ItemMeta refinedCoalOreMeta = refinedCoalOre.getItemMeta();
                            refinedCoalOreMeta.setDisplayName(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "Refined Coal Ore");
                            refinedCoalOre.setItemMeta(refinedCoalOreMeta);
                            player.getInventory().addItem(refinedCoalOre);
                            player.sendMessage("You gave yourself " + ChatColor.DARK_GRAY + ChatColor.BOLD + "Refined Coal Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("refined_gold_ore")) {
                            ItemStack refinedGoldOre = new ItemStack(Material.GOLD_ORE);
                            ItemMeta refinedGoldMeta = refinedGoldOre.getItemMeta();
                            refinedGoldMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Refined Gold Ore");
                            refinedGoldOre.setItemMeta(refinedGoldMeta);
                            player.getInventory().addItem(refinedGoldOre);
                            player.sendMessage("You gave yourself " + ChatColor.GOLD + ChatColor.BOLD + "Refined Gold Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("refined_iron_ore")) {
                            ItemStack refinedIronOre = new ItemStack(Material.IRON_ORE);
                            ItemMeta refinedIronMeta = refinedIronOre.getItemMeta();
                            refinedIronMeta.setDisplayName(ChatColor.GRAY.toString() + ChatColor.BOLD + "Refined Iron Ore");
                            refinedIronOre.setItemMeta(refinedIronMeta);
                            player.getInventory().addItem(refinedIronOre);
                            player.sendMessage("You gave yourself " + ChatColor.GRAY + ChatColor.BOLD + "Refined Iron Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("refined_diamond_ore")) {
                            ItemStack refinedDiamondOre = new ItemStack(Material.DIAMOND_ORE);
                            ItemMeta refinedDiamondMeta = refinedDiamondOre.getItemMeta();
                            refinedDiamondMeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Refined Diamond Ore");
                            refinedDiamondOre.setItemMeta(refinedDiamondMeta);
                            player.getInventory().addItem(refinedDiamondOre);
                            player.sendMessage("You gave yourself " + ChatColor.AQUA + ChatColor.BOLD + "Refined Diamond Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("refined_emerald_ore")) {
                            ItemStack refinedEmeraldOre = new ItemStack(Material.EMERALD_ORE);
                            ItemMeta refinedEmeraldMeta = refinedEmeraldOre.getItemMeta();
                            refinedEmeraldMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Refined Emerald Ore");
                            refinedEmeraldOre.setItemMeta(refinedEmeraldMeta);
                            player.getInventory().addItem(refinedEmeraldOre);
                            player.sendMessage("You gave yourself " + ChatColor.GREEN + ChatColor.BOLD + "Refined Emerald Ore");
                        }
                        if (args[1].equalsIgnoreCase("warzone_coal_ore")) {
                            ItemStack warzoneCoalOre = new ItemStack(Material.DEEPSLATE_COAL_ORE);
                            ItemMeta warzoneCoalMeta = warzoneCoalOre.getItemMeta();
                            warzoneCoalMeta.setDisplayName(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "Warzone Coal Ore");
                            warzoneCoalOre.setItemMeta(warzoneCoalMeta);
                            player.getInventory().addItem(warzoneCoalOre);
                            player.sendMessage("You gave yourself " + ChatColor.DARK_GRAY + ChatColor.BOLD + "Warzone Coal Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("warzone_iron_ore")) {
                            ItemStack warzoneIronOre = new ItemStack(Material.DEEPSLATE_IRON_ORE);
                            ItemMeta warzoneIronMeta = warzoneIronOre.getItemMeta();
                            warzoneIronMeta.setDisplayName(ChatColor.GRAY.toString() + ChatColor.BOLD + "Warzone Iron Ore");
                            warzoneIronOre.setItemMeta(warzoneIronMeta);
                            player.getInventory().addItem(warzoneIronOre);
                            player.sendMessage("You gave yourself " + ChatColor.DARK_GRAY + ChatColor.BOLD + "Warzone Coal Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("warzone_gold_ore")) {
                            ItemStack warzoneGoldOre = new ItemStack(Material.DEEPSLATE_GOLD_ORE);
                            ItemMeta warzoneGoldMeta = warzoneGoldOre.getItemMeta();
                            warzoneGoldMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Warzone Gold Ore");
                            warzoneGoldOre.setItemMeta(warzoneGoldMeta);
                            player.getInventory().addItem(warzoneGoldOre);
                            player.sendMessage("You gave yourself " + ChatColor.GOLD + ChatColor.BOLD + "Warzone Gold Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("warzone_diamond_ore")) {
                            ItemStack warzoneDiamondOre = new ItemStack(Material.DEEPSLATE_DIAMOND_ORE);
                            ItemMeta warzoneDiamondMeta = warzoneDiamondOre.getItemMeta();
                            warzoneDiamondMeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Warzone Diamond Ore");
                            warzoneDiamondOre.setItemMeta(warzoneDiamondMeta);
                            player.getInventory().addItem(warzoneDiamondOre);
                            player.sendMessage("You gave yourself " + ChatColor.AQUA + ChatColor.BOLD + "Warzone Diamond Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("warzone_emerald_ore")) {
                            ItemStack warzoneEmeraldOre = new ItemStack(Material.DEEPSLATE_EMERALD_ORE);
                            ItemMeta warzoneEmeraldMeta = warzoneEmeraldOre.getItemMeta();
                            warzoneEmeraldMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Warzone Emerald Ore");
                            warzoneEmeraldOre.setItemMeta(warzoneEmeraldMeta);
                            player.getInventory().addItem(warzoneEmeraldOre);
                            player.sendMessage("You gave yourself " + ChatColor.GREEN + ChatColor.BOLD + "Warzone Emerald Ore");
                            return true;
                        }
                        break;
                    case 3:
                        int amount = (int) Long.parseLong(args[2]);
                        if (args[1].equalsIgnoreCase("refined_coal_ore")) {
                            ItemStack refinedCoalOre = new ItemStack(Material.COAL_ORE, amount);
                            ItemMeta refinedCoalOreMeta = refinedCoalOre.getItemMeta();
                            refinedCoalOreMeta.setDisplayName(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "Refined Coal Ore");
                            refinedCoalOre.setItemMeta(refinedCoalOreMeta);
                            player.getInventory().addItem(refinedCoalOre);
                            player.sendMessage("You gave yourself " + ChatColor.GOLD + amount + ChatColor.DARK_GRAY + ChatColor.BOLD + " Refined Coal Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("refined_gold_ore")) {
                            ItemStack refinedGoldOre = new ItemStack(Material.GOLD_ORE, amount);
                            ItemMeta refinedGoldMeta = refinedGoldOre.getItemMeta();
                            refinedGoldMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Refined Gold Ore");
                            refinedGoldOre.setItemMeta(refinedGoldMeta);
                            player.getInventory().addItem(refinedGoldOre);
                            player.sendMessage("You gave yourself " + ChatColor.GOLD + amount + ChatColor.BOLD + " Refined Gold Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("refined_iron_ore")) {
                            ItemStack refinedIronOre = new ItemStack(Material.IRON_ORE, amount);
                            ItemMeta refinedIronMeta = refinedIronOre.getItemMeta();
                            refinedIronMeta.setDisplayName(ChatColor.GRAY.toString() + ChatColor.BOLD + "Refined Iron Ore");
                            refinedIronOre.setItemMeta(refinedIronMeta);
                            player.getInventory().addItem(refinedIronOre);
                            player.sendMessage("You gave yourself " + ChatColor.GOLD + amount + ChatColor.GRAY + ChatColor.BOLD + " Refined Iron Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("refined_diamond_ore")) {
                            ItemStack refinedDiamondOre = new ItemStack(Material.DIAMOND_ORE, amount);
                            ItemMeta refinedDiamondMeta = refinedDiamondOre.getItemMeta();
                            refinedDiamondMeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Refined Diamond Ore");
                            refinedDiamondOre.setItemMeta(refinedDiamondMeta);
                            player.getInventory().addItem(refinedDiamondOre);
                            player.sendMessage("You gave yourself " + ChatColor.GOLD + amount + ChatColor.AQUA + ChatColor.BOLD + " Refined Diamond Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("refined_emerald_ore")) {
                            ItemStack refinedEmeraldOre = new ItemStack(Material.EMERALD_ORE, amount);
                            ItemMeta refinedEmeraldMeta = refinedEmeraldOre.getItemMeta();
                            refinedEmeraldMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Refined Emerald Ore");
                            refinedEmeraldOre.setItemMeta(refinedEmeraldMeta);
                            player.getInventory().addItem(refinedEmeraldOre);
                            player.sendMessage("You gave yourself " + ChatColor.GREEN + ChatColor.BOLD + " Refined Emerald Ore");
                        }
                        if (args[1].equalsIgnoreCase("warzone_coal_ore")) {
                            ItemStack warzoneCoalOre = new ItemStack(Material.DEEPSLATE_COAL_ORE, amount);
                            ItemMeta warzoneCoalMeta = warzoneCoalOre.getItemMeta();
                            warzoneCoalMeta.setDisplayName(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "Warzone Coal Ore");
                            warzoneCoalOre.setItemMeta(warzoneCoalMeta);
                            player.getInventory().addItem(warzoneCoalOre);
                            player.sendMessage("You gave yourself " + ChatColor.GOLD + amount + ChatColor.DARK_GRAY + ChatColor.BOLD + " Warzone Coal Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("warzone_iron_ore")) {
                            ItemStack warzoneIronOre = new ItemStack(Material.DEEPSLATE_IRON_ORE, amount);
                            ItemMeta warzoneIronMeta = warzoneIronOre.getItemMeta();
                            warzoneIronMeta.setDisplayName(ChatColor.GRAY.toString() + ChatColor.BOLD + "Warzone Iron Ore");
                            warzoneIronOre.setItemMeta(warzoneIronMeta);
                            player.getInventory().addItem(warzoneIronOre);
                            player.sendMessage("You gave yourself " + ChatColor.GOLD + amount + ChatColor.GRAY + ChatColor.BOLD + " Warzone Iron Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("warzone_gold_ore")) {
                            ItemStack warzoneGoldOre = new ItemStack(Material.DEEPSLATE_GOLD_ORE, amount);
                            ItemMeta warzoneGoldMeta = warzoneGoldOre.getItemMeta();
                            warzoneGoldMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Warzone Gold Ore");
                            warzoneGoldOre.setItemMeta(warzoneGoldMeta);
                            player.getInventory().addItem(warzoneGoldOre);
                            player.sendMessage("You gave yourself " + ChatColor.GOLD + amount + ChatColor.BOLD + " Warzone Gold Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("warzone_diamond_ore")) {
                            ItemStack warzoneDiamondOre = new ItemStack(Material.DEEPSLATE_DIAMOND_ORE, amount);
                            ItemMeta warzoneDiamondMeta = warzoneDiamondOre.getItemMeta();
                            warzoneDiamondMeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Warzone Diamond Ore");
                            warzoneDiamondOre.setItemMeta(warzoneDiamondMeta);
                            player.getInventory().addItem(warzoneDiamondOre);
                            player.sendMessage("You gave yourself " + ChatColor.GOLD + amount + ChatColor.AQUA + ChatColor.BOLD + " Warzone Diamond Ore");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("warzone_emerald_ore")) {
                            ItemStack warzoneEmeraldOre = new ItemStack(Material.DEEPSLATE_EMERALD_ORE, amount);
                            ItemMeta warzoneEmeraldMeta = warzoneEmeraldOre.getItemMeta();
                            warzoneEmeraldMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Warzone Emerald Ore");
                            warzoneEmeraldOre.setItemMeta(warzoneEmeraldMeta);
                            player.getInventory().addItem(warzoneEmeraldOre);
                            player.sendMessage("You gave yourself " + ChatColor.GOLD + amount + ChatColor.GREEN + ChatColor.BOLD + " Warzone Emerald Ore");
                            return true;
                        }
                        break;
                }
        }
        return true;
    }

    @EventHandler
    public void onCustomOreBreak(BlockBreakEvent event) {
        ItemStack refinedCoal = new ItemStack(Material.COAL);
        ItemStack refinedIron = new ItemStack(Material.IRON_INGOT);
        ItemStack refinedGold = new ItemStack(Material.GOLD_INGOT);
        ItemStack refinedDiamond = new ItemStack(Material.DIAMOND);
        ItemStack refinedEmerald = new ItemStack(Material.EMERALD);
        ItemStack warzoneCoal = new ItemStack(Material.COAL);
        ItemStack warzoneIron = new ItemStack(Material.IRON_INGOT);
        ItemStack warzoneGold = new ItemStack(Material.GOLD_INGOT);
        ItemStack warzoneDiamond = new ItemStack(Material.DIAMOND);
        ItemStack warzoneEmerald = new ItemStack(Material.EMERALD);

        ItemMeta warzoneCoalMeta = warzoneCoal.getItemMeta();
        warzoneCoalMeta.setDisplayName(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "Warzone Coal");
        ArrayList<String> warzoneCoalLore = new ArrayList<>();
        warzoneCoalLore.add(" ");
        warzoneCoalLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "3 " + ChatColor.GREEN + "coins");
        warzoneCoalMeta.setLore(warzoneCoalLore);
        warzoneCoal.setItemMeta(warzoneCoalMeta);

        ItemMeta warzoneGoldMeta = warzoneGold.getItemMeta();
        warzoneGoldMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Warzone Gold");
        ArrayList<String> warzoneGoldLore = new ArrayList<>();
        warzoneGoldLore.add(" ");
        warzoneGoldLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "5 " + ChatColor.GREEN + "coins");
        warzoneGoldMeta.setLore(warzoneGoldLore);
        warzoneGold.setItemMeta(warzoneGoldMeta);

        ItemMeta warzoneIronMeta = warzoneIron.getItemMeta();
        warzoneIronMeta.setDisplayName(ChatColor.GRAY.toString() + ChatColor.BOLD + "Warzone Iron");
        ArrayList<String> warzoneIronLore = new ArrayList<>();
        warzoneIronLore.add(" ");
        warzoneIronLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "7 " + ChatColor.GREEN + "coins");
        warzoneIronMeta.setLore(warzoneIronLore);
        warzoneIron.setItemMeta(warzoneIronMeta);

        ItemMeta warzoneDiamondMeta = warzoneDiamond.getItemMeta();
        warzoneDiamondMeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Warzone Diamond");
        ArrayList<String> warzoneDiamondLore = new ArrayList<>();
        warzoneDiamondLore.add(" ");
        warzoneDiamondLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "10 " + ChatColor.GREEN + "coins");
        warzoneDiamondMeta.setLore(warzoneDiamondLore);
        warzoneDiamond.setItemMeta(warzoneDiamondMeta);

        ItemMeta warzoneEmeraldMeta = warzoneEmerald.getItemMeta();
        warzoneEmeraldMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Warzone Emerald");
        ArrayList<String> warzoneEmeraldLore = new ArrayList<>();
        warzoneEmeraldLore.add(" ");
        warzoneEmeraldLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "25 " + ChatColor.GREEN + "coins");
        warzoneEmeraldMeta.setLore(warzoneEmeraldLore);
        warzoneEmerald.setItemMeta(warzoneEmeraldMeta);

        ItemMeta refinedGoldMeta = refinedGold.getItemMeta();
        refinedGoldMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Refined Gold");
        ArrayList<String> refinedGoldLore = new ArrayList<>();
        refinedGoldLore.add(" ");
        refinedGoldLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "14 " + ChatColor.GREEN + "coins");
        refinedGoldMeta.setLore(refinedGoldLore);
        refinedGold.setItemMeta(refinedGoldMeta);

        ItemMeta refinedIronMeta = refinedIron.getItemMeta();
        refinedIronMeta.setDisplayName(ChatColor.GRAY.toString() + ChatColor.BOLD + "Refined Iron");
        ArrayList<String> refinedIronLore = new ArrayList<>();
        refinedIronLore.add(" ");
        refinedIronLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "6 " + ChatColor.GREEN + "coins");
        refinedIronMeta.setLore(refinedIronLore);
        refinedIron.setItemMeta(refinedIronMeta);

        ItemMeta refinedDiamondMeta = refinedDiamond.getItemMeta();
        refinedDiamondMeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Refined Diamond");
        ArrayList<String> refinedDiamondLore = new ArrayList<>();
        refinedDiamondLore.add(" ");
        refinedDiamondLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "25 " + ChatColor.GREEN + "coins");
        refinedDiamondMeta.setLore(refinedDiamondLore);
        refinedDiamond.setItemMeta(refinedDiamondMeta);

        ItemMeta refinedCoalMeta = refinedCoal.getItemMeta();
        refinedCoalMeta.setDisplayName(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "Refined Coal");
        ArrayList<String> refinedCoalLore = new ArrayList<>();
        refinedCoalLore.add(" ");
        refinedCoalLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "1 " + ChatColor.GREEN + "coins");
        refinedCoalMeta.setLore(refinedCoalLore);
        refinedCoal.setItemMeta(refinedCoalMeta);

        ItemMeta refinedEmeraldMeta = refinedEmerald.getItemMeta();
        refinedEmeraldMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Refined Emerald");
        ArrayList<String> refinedEmeraldLore = new ArrayList<>();
        refinedEmeraldLore.add(" ");
        refinedEmeraldLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "40 " + ChatColor.GREEN + "coins");
        refinedEmeraldMeta.setLore(refinedEmeraldLore);
        refinedEmerald.setItemMeta(refinedEmeraldMeta);

        if (event.getBlock().getType() == Material.COAL_ORE) {
            event.getPlayer().getInventory().addItem(refinedCoal);
            event.setCancelled(true);

            Block block = event.getBlock();

            // Store the original block type
            originalBlockTypes.put(block, block.getType());

            // Replace the block with bedrock after a delay
            replaceWithBedrock(block);

            // Schedule the block restoration after a specified time
            scheduleBlockRestoration(block);
        }
        if (event.getBlock().getType() == Material.IRON_ORE) {
            event.getPlayer().getInventory().addItem(refinedIron);
            event.setCancelled(true);

            Block block = event.getBlock();

            // Store the original block type
            originalBlockTypes.put(block, block.getType());

            // Replace the block with bedrock after a delay
            replaceWithBedrock(block);

            // Schedule the block restoration after a specified time
            scheduleBlockRestoration(block);
        }
        if (event.getBlock().getType() == Material.GOLD_ORE) {
            Block block = event.getBlock();
            event.getPlayer().getInventory().addItem(refinedGold);
            event.setCancelled(true);

            // Store the original block type
            originalBlockTypes.put(block, block.getType());

            // Replace the block with bedrock after a delay
            replaceWithBedrock(block);

            // Schedule the block restoration after a specified time
            scheduleBlockRestoration(block);
        }
        if (event.getBlock().getType() == Material.DIAMOND_ORE) {
            event.getPlayer().getInventory().addItem(refinedDiamond);
            event.setCancelled(true);

            Block block = event.getBlock();

            // Store the original block type
            originalBlockTypes.put(block, block.getType());

            // Replace the block with bedrock after a delay
            replaceWithBedrock(block);

            // Schedule the block restoration after a specified time
            scheduleBlockRestoration(block);
        }
        if (event.getBlock().getType() == Material.EMERALD_ORE) {
            event.getPlayer().getInventory().addItem(refinedEmerald);
            event.setCancelled(true);

            Block block = event.getBlock();

            // Store the original block type
            originalBlockTypes.put(block, block.getType());

            // Replace the block with bedrock after a delay
            replaceWithBedrock(block);

            // Schedule the block restoration after a specified time
            scheduleBlockRestoration(block);
        }
        if (event.getBlock().getType() == Material.DEEPSLATE_COAL_ORE) {
            event.getPlayer().getInventory().addItem(warzoneCoal);
            event.setCancelled(true);

            Block block = event.getBlock();

            // Store the original block type
            originalBlockTypes.put(block, block.getType());

            // Replace the block with bedrock after a delay
            replaceWithBedrock(block);

            // Schedule the block restoration after a specified time
            scheduleBlockRestoration(block);
        }
        if (event.getBlock().getType() == Material.DEEPSLATE_IRON_ORE) {
            event.getPlayer().getInventory().addItem(warzoneIron);
            event.setCancelled(true);

            Block block = event.getBlock();

            // Store the original block type
            originalBlockTypes.put(block, block.getType());

            // Replace the block with bedrock after a delay
            replaceWithBedrock(block);

            // Schedule the block restoration after a specified time
            scheduleBlockRestoration(block);
        }
        if (event.getBlock().getType() == Material.DEEPSLATE_GOLD_ORE) {
            event.getPlayer().getInventory().addItem(warzoneGold);
            event.setCancelled(true);

            Block block = event.getBlock();

            // Store the original block type
            originalBlockTypes.put(block, block.getType());

            // Replace the block with bedrock after a delay
            replaceWithBedrock(block);

            // Schedule the block restoration after a specified time
            scheduleBlockRestoration(block);
        }
        if (event.getBlock().getType() == Material.DEEPSLATE_DIAMOND_ORE) {
            event.getPlayer().getInventory().addItem(warzoneDiamond);
            event.setCancelled(true);

            Block block = event.getBlock();

            // Store the original block type
            originalBlockTypes.put(block, block.getType());

            // Replace the block with bedrock after a delay
            replaceWithBedrock(block);

            // Schedule the block restoration after a specified time
            scheduleBlockRestoration(block);
        }
        if (event.getBlock().getType() == Material.DEEPSLATE_EMERALD_ORE) {
            event.getPlayer().getInventory().addItem(warzoneEmerald);
            event.setCancelled(true);

            Block block = event.getBlock();

            // Store the original block type
            originalBlockTypes.put(block, block.getType());

            // Replace the block with bedrock after a delay
            replaceWithBedrock(block);

            // Schedule the block restoration after a specified time
            scheduleBlockRestoration(block);
        }
    }

    private void replaceWithBedrock(Block block) {
        new BukkitRunnable() {
            @Override
            public void run() {
                block.setType(Material.BEDROCK);
            }
        }.runTaskLater(plugin, 20 * 0); // Replace with bedrock after specified time (20 ticks per second)
    }

    private void scheduleBlockRestoration(Block block) {
        new BukkitRunnable() {
            @Override
            public void run() {
                // Check if the block is still bedrock (it might have been broken or modified)
                if (block.getType() == Material.BEDROCK) {
                    // Restore the original block type
                    block.setType(originalBlockTypes.getOrDefault(block, Material.AIR));
                }

                // Remove the stored original block type
                originalBlockTypes.remove(block);
            }
        }.runTaskLater(plugin, restoreTime); // Restore after specified time (20 ticks per second)
    }
}
