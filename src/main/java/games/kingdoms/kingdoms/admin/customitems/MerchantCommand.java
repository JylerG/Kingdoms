package games.kingdoms.kingdoms.admin.customitems;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MerchantCommand implements CommandExecutor, Listener {

    private final Kingdoms plugin;

    public MerchantCommand(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            MessageManager.consoleBad("You must be a player to execute this command.");
        } else {
            Player player = (Player) sender;
            if (args.length == 0) {
                Inventory gui = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Merchant (Page 1)");

                ItemStack border = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                ItemStack heartOfWar = new ItemStack(Material.BEETROOT);
                ItemStack warzoneCrate = new ItemStack(Material.CHEST);
                ItemStack enchantedFlesh = new ItemStack(Material.ROTTEN_FLESH);
                ItemStack gemstoneOfChivalry = new ItemStack(Material.IRON_NUGGET);
                ItemStack enchantedLeather = new ItemStack(Material.LEATHER);
                ItemStack refinedCoal = new ItemStack(Material.COAL);
                ItemStack refinedCoalBlock = new ItemStack(Material.COAL_BLOCK);
                ItemStack refinedIron = new ItemStack(Material.IRON_INGOT);
                ItemStack refinedIronBlock = new ItemStack(Material.IRON_BLOCK);
                ItemStack refinedGold = new ItemStack(Material.GOLD_INGOT);
                ItemStack refinedGoldBlock = new ItemStack(Material.GOLD_BLOCK);
                ItemStack refinedDiamond = new ItemStack(Material.DIAMOND);
                ItemStack refinedDiamondBlock = new ItemStack(Material.DIAMOND_BLOCK);
                ItemStack warzoneCoal = new ItemStack(Material.COAL);
                ItemStack warzoneIron = new ItemStack(Material.IRON_INGOT);
                ItemStack warzoneGold = new ItemStack(Material.GOLD_INGOT);
                ItemStack warzoneDiamond = new ItemStack(Material.DIAMOND);
                ItemStack warzoneEmerald = new ItemStack(Material.EMERALD);
                ItemStack goblinSkin = new ItemStack(Material.LILY_PAD);

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

                ItemMeta refinedGoldBlockMeta = refinedGoldBlock.getItemMeta();
                refinedGoldBlockMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Refined Block of Gold");
                ArrayList<String> refinedGoldBlockLore = new ArrayList<>();
                refinedGoldBlockLore.add(" ");
                refinedGoldBlockLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "150 " + ChatColor.GREEN + "coins");
                refinedGoldBlockMeta.setLore(refinedGoldBlockLore);
                refinedGoldBlock.setItemMeta(refinedGoldBlockMeta);

                ItemMeta refinedIronMeta = refinedIron.getItemMeta();
                refinedIronMeta.setDisplayName(ChatColor.GRAY.toString() + ChatColor.BOLD + "Refined Iron");
                ArrayList<String> refinedIronLore = new ArrayList<>();
                refinedIronLore.add(" ");
                refinedIronLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "6 " + ChatColor.GREEN + "coins");
                refinedIronMeta.setLore(refinedIronLore);
                refinedIron.setItemMeta(refinedIronMeta);

                ItemMeta refinedIronBlockMeta = refinedIronBlock.getItemMeta();
                refinedIronBlockMeta.setDisplayName(ChatColor.GRAY.toString() + ChatColor.BOLD + "Refined Block of Iron");
                ArrayList<String> refinedIronBlockLore = new ArrayList<>();
                refinedIronBlockLore.add(" ");
                refinedIronBlockLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "65 " + ChatColor.GREEN + "coins");
                refinedIronBlockMeta.setLore(refinedIronBlockLore);
                refinedIronBlock.setItemMeta(refinedIronBlockMeta);

                ItemMeta refinedDiamondMeta = refinedDiamond.getItemMeta();
                refinedDiamondMeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Refined Diamond");
                ArrayList<String> refinedDiamondLore = new ArrayList<>();
                refinedDiamondLore.add(" ");
                refinedDiamondLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "25 " + ChatColor.GREEN + "coins");
                refinedDiamondMeta.setLore(refinedDiamondLore);
                refinedDiamond.setItemMeta(refinedDiamondMeta);

                ItemMeta refinedDiamondBlockMeta = refinedDiamondBlock.getItemMeta();
                refinedDiamondBlockMeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Refined Block of Diamond");
                ArrayList<String> refinedDiamondBlockLore = new ArrayList<>();
                refinedDiamondBlockLore.add(" ");
                refinedDiamondBlockLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "275 " + ChatColor.GREEN + "coins");
                refinedDiamondBlockMeta.setLore(refinedDiamondBlockLore);
                refinedDiamondBlock.setItemMeta(refinedDiamondBlockMeta);

                ItemMeta refinedCoalBlockMeta = refinedCoalBlock.getItemMeta();
                refinedCoalBlockMeta.setDisplayName(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "Refined Block of Coal");
                ArrayList<String> refinedCoalBlockLore = new ArrayList<>();
                refinedCoalBlockLore.add(" ");
                refinedCoalBlockLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "12 " + ChatColor.GREEN + "coins");
                refinedCoalBlockMeta.setLore(refinedCoalBlockLore);
                refinedCoalBlock.setItemMeta(refinedCoalBlockMeta);

                ItemMeta enchantedLeatherMeta = enchantedLeather.getItemMeta();
                enchantedLeatherMeta.setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Enchanted Leather");
                ArrayList<String> leatherLore = new ArrayList<>();
                leatherLore.add(" ");
                leatherLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "1000 " + ChatColor.GREEN + "coins");
                enchantedLeatherMeta.setLore(leatherLore);
                enchantedLeather.setItemMeta(enchantedLeatherMeta);

                ItemMeta refinedCoalMeta = refinedCoal.getItemMeta();
                refinedCoalMeta.setDisplayName(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "Refined Coal");
                ArrayList<String> refinedCoalLore = new ArrayList<>();
                refinedCoalLore.add(" ");
                refinedCoalLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "1 " + ChatColor.GREEN + "coins");
                refinedCoalMeta.setLore(refinedCoalLore);
                refinedCoal.setItemMeta(refinedCoalMeta);

                ItemMeta heartItemMeta = heartOfWar.getItemMeta();
                heartItemMeta.setDisplayName(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Heart of War");
                ArrayList<String> heartLore = new ArrayList<>();
                heartLore.add(ChatColor.GRAY + "A " + ChatColor.LIGHT_PURPLE + "Special " + ChatColor.GRAY + "item that can be sold");
                heartLore.add(ChatColor.GRAY + "or used at the Forsaken Forge");
                heartLore.add(" ");
                heartLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "5000 " + ChatColor.GREEN + "coins");
                heartItemMeta.setLore(heartLore);
                heartOfWar.setItemMeta(heartItemMeta);

                ItemMeta warzoneCrateItemMeta = warzoneCrate.getItemMeta();
                warzoneCrateItemMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Warzone Crate");
                ArrayList<String> crateLore = new ArrayList<>();
                crateLore.add(" ");
                crateLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Place " + ChatColor.WHITE + "to get " + ChatColor.GREEN + ChatColor.BOLD + "3 " + ChatColor.WHITE + "Warzone items");
                warzoneCrateItemMeta.setLore(crateLore);
                warzoneCrate.setItemMeta(warzoneCrateItemMeta);

                ItemMeta fleshItemMeta = enchantedFlesh.getItemMeta();
                fleshItemMeta.setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "Enchanted Flesh");
                ArrayList<String> fleshLore = new ArrayList<>();
                fleshLore.add(" ");
                fleshLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "1500 " + ChatColor.GREEN + "coins");
                fleshItemMeta.setLore(fleshLore);
                enchantedFlesh.setItemMeta(fleshItemMeta);

                ItemMeta gemstoneItemMeta = gemstoneOfChivalry.getItemMeta();
                gemstoneItemMeta.setDisplayName(ChatColor.GRAY.toString() + ChatColor.BOLD + "Gemstone of Chivalry");
                ArrayList<String> gemstoneLore = new ArrayList<>();
                gemstoneLore.add(ChatColor.GRAY + "A " + ChatColor.LIGHT_PURPLE + "Special " + ChatColor.GRAY + "item that can be sold");
                gemstoneLore.add(ChatColor.GRAY + "or used at the Forsaken Forge");
                gemstoneLore.add(" ");
                gemstoneLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "2500 " + ChatColor.GREEN + "coins");
                gemstoneItemMeta.setLore(gemstoneLore);
                gemstoneOfChivalry.setItemMeta(gemstoneItemMeta);

                ItemMeta goblinSkinMeta = goblinSkin.getItemMeta();
                goblinSkinMeta.setDisplayName(ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Goblin Skin");
                ArrayList<String> goblinSkinLore = new ArrayList<>();
                goblinSkinLore.add(ChatColor.GRAY + "A " + ChatColor.LIGHT_PURPLE + "Special " + ChatColor.GRAY + "item that can be sold");
                goblinSkinLore.add(ChatColor.GRAY + "or used at the Forsaken Forge");
                goblinSkinLore.add(" ");
                goblinSkinLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "2500 " + ChatColor.GREEN + "coins");
                goblinSkinMeta.setLore(goblinSkinLore);
                goblinSkin.setItemMeta(goblinSkinMeta);

                ItemMeta borderMeta = border.getItemMeta();
                borderMeta.setDisplayName(" ");
                border.setItemMeta(borderMeta);

                gui.setItem(0, border);
                gui.setItem(1, border);
                gui.setItem(2, border);
                gui.setItem(3, border);
                gui.setItem(4, border);
                gui.setItem(5, border);
                gui.setItem(6, border);
                gui.setItem(7, border);
                gui.setItem(8, border);
                gui.setItem(9, border);
                gui.setItem(17, border);
                gui.setItem(18, border);
                gui.setItem(26, border);
                gui.setItem(27, border);
                gui.setItem(35, border);
                gui.setItem(36, border);
                gui.setItem(44, border);
                gui.setItem(45, border);
                gui.setItem(46, border);
                gui.setItem(47, border);
                gui.setItem(48, border);
                gui.setItem(49, border);
                gui.setItem(50, border);
                gui.setItem(51, border);
                gui.setItem(52, border);
                gui.setItem(53, border);
                gui.setItem(15, heartOfWar);
                gui.setItem(19, refinedCoalBlock);
                gui.setItem(20, refinedGold);
                gui.setItem(21, refinedGoldBlock);
                gui.setItem(22, refinedIron);
                gui.setItem(23, refinedIronBlock);
                gui.setItem(24, refinedDiamond);
                gui.setItem(25, refinedDiamondBlock);
                gui.setItem(16, refinedCoal);
                gui.setItem(12, enchantedLeather);
                gui.setItem(11, enchantedFlesh);
                gui.setItem(13, gemstoneOfChivalry);
                gui.setItem(14, goblinSkin);
                gui.setItem(28, warzoneCoal);
                gui.setItem(29, warzoneGold);
                gui.setItem(30, warzoneIron);
                gui.setItem(31, warzoneDiamond);
                gui.setItem(32, warzoneEmerald);
                player.openInventory(gui);
            }
        }
        return true;
    }
}
