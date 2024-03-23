package games.kingdoms.kingdoms.admin.staffvault;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;


public class StaffVault implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("kingdoms.staffvault")) {

                Inventory staffVault = Bukkit.createInventory(player, 18, ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Staff Vault");

                ItemStack diamondHelmet = new ItemStack(Material.DIAMOND_HELMET);
                diamondHelmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                diamondHelmet.addUnsafeEnchantment(Enchantment.MENDING, 10);
                diamondHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
                diamondHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10);
                diamondHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
                diamondHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10);
                diamondHelmet.addUnsafeEnchantment(Enchantment.THORNS, 10);
                diamondHelmet.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemStack diamondChestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
                diamondChestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                diamondChestplate.addUnsafeEnchantment(Enchantment.MENDING, 10);
                diamondChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
                diamondChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10);
                diamondChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
                diamondChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10);
                diamondChestplate.addUnsafeEnchantment(Enchantment.THORNS, 10);
                diamondChestplate.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemStack diamondLeggings = new ItemStack(Material.DIAMOND_LEGGINGS);
                diamondLeggings.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                diamondLeggings.addUnsafeEnchantment(Enchantment.MENDING, 10);
                diamondLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
                diamondLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10);
                diamondLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
                diamondLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10);
                diamondLeggings.addUnsafeEnchantment(Enchantment.THORNS, 10);
                diamondLeggings.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemStack diamondBoots = new ItemStack(Material.DIAMOND_BOOTS);
                diamondBoots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                diamondBoots.addUnsafeEnchantment(Enchantment.MENDING, 10);
                diamondBoots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
                diamondBoots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10);
                diamondBoots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
                diamondBoots.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10);
                diamondBoots.addUnsafeEnchantment(Enchantment.THORNS, 10);
                diamondBoots.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 30);
                diamondBoots.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemStack netheriteHelmet = new ItemStack(Material.NETHERITE_HELMET);
                netheriteHelmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                netheriteHelmet.addUnsafeEnchantment(Enchantment.MENDING, 10);
                netheriteHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
                netheriteHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10);
                netheriteHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
                netheriteHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10);
                netheriteHelmet.addUnsafeEnchantment(Enchantment.THORNS, 10);
                netheriteHelmet.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemStack netheriteChestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
                netheriteChestplate.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                netheriteChestplate.addUnsafeEnchantment(Enchantment.MENDING, 10);
                netheriteChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
                netheriteChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10);
                netheriteChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
                netheriteChestplate.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10);
                netheriteChestplate.addUnsafeEnchantment(Enchantment.THORNS, 10);
                netheriteChestplate.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemStack netheriteLeggings = new ItemStack(Material.NETHERITE_LEGGINGS);
                netheriteLeggings.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                netheriteLeggings.addUnsafeEnchantment(Enchantment.MENDING, 10);
                netheriteLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
                netheriteLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10);
                netheriteLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
                netheriteLeggings.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10);
                netheriteLeggings.addUnsafeEnchantment(Enchantment.THORNS, 10);
                netheriteLeggings.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemStack netheriteBoots = new ItemStack(Material.NETHERITE_BOOTS);
                netheriteBoots.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                netheriteBoots.addUnsafeEnchantment(Enchantment.MENDING, 10);
                netheriteBoots.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
                netheriteBoots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10);
                netheriteBoots.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
                netheriteBoots.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10);
                netheriteBoots.addUnsafeEnchantment(Enchantment.THORNS, 10);
                netheriteBoots.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 30);
                netheriteBoots.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemStack border = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                ItemMeta borderMeta = border.getItemMeta();
                borderMeta.setDisplayName(" ");
                border.setItemMeta(borderMeta);

                ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
                pickaxe.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                pickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 100);
                pickaxe.addUnsafeEnchantment(Enchantment.MENDING, 20);
                pickaxe.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
                axe.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
                axe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 100);
                axe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 20);
                axe.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 10);
                axe.addUnsafeEnchantment(Enchantment.MENDING, 20);
                axe.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemStack shovel = new ItemStack(Material.DIAMOND_SHOVEL);
                shovel.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
                shovel.addUnsafeEnchantment(Enchantment.DIG_SPEED, 100);
                shovel.addUnsafeEnchantment(Enchantment.MENDING, 20);
                shovel.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
                sword.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
                sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 20);
                sword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 10);
                sword.addUnsafeEnchantment(Enchantment.MENDING, 20);
                sword.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemStack netheritePickaxe = new ItemStack(Material.NETHERITE_PICKAXE);
                netheritePickaxe.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                netheritePickaxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 100);
                netheritePickaxe.addUnsafeEnchantment(Enchantment.MENDING, 20);
                netheritePickaxe.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemStack netheriteAxe = new ItemStack(Material.NETHERITE_AXE);
                netheriteAxe.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
                netheriteAxe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 100);
                netheriteAxe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 20);
                netheriteAxe.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 10);
                netheriteAxe.addUnsafeEnchantment(Enchantment.MENDING, 20);
                netheriteAxe.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemStack netheriteShovel = new ItemStack(Material.NETHERITE_SHOVEL);
                netheriteShovel.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
                netheriteShovel.addUnsafeEnchantment(Enchantment.DIG_SPEED, 100);
                netheriteShovel.addUnsafeEnchantment(Enchantment.MENDING, 20);
                netheriteShovel.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemStack netheriteSword = new ItemStack(Material.NETHERITE_SWORD);
                netheriteSword.addUnsafeEnchantment(Enchantment.DURABILITY, 20);
                netheriteSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 20);
                netheriteSword.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 10);
                netheriteSword.addUnsafeEnchantment(Enchantment.MENDING, 20);
                netheriteSword.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);

                ItemMeta diamondSwordMeta = sword.getItemMeta();
                diamondSwordMeta.setDisplayName(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Smite of Hera");
                sword.setItemMeta(diamondSwordMeta);

                ItemMeta netheriteSwordMeta = netheriteSword.getItemMeta();
                netheriteSwordMeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Smite of Zeus");
                netheriteSword.setItemMeta(netheriteSwordMeta);

                ItemMeta diamondPickaxeMeta = pickaxe.getItemMeta();
                diamondPickaxeMeta.setDisplayName(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Hephaestus' Pickaxe");
                pickaxe.setItemMeta(diamondPickaxeMeta);

                ItemMeta netheritePickaxeMeta = netheritePickaxe.getItemMeta();
                netheritePickaxeMeta.setDisplayName(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "Aether's Pickaxe");
                netheritePickaxe.setItemMeta(netheritePickaxeMeta);

                ItemMeta diamondAxeMeta = axe.getItemMeta();
                diamondAxeMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Amphitrite's Axe");
                axe.setItemMeta(diamondAxeMeta);

                ItemMeta netheriteAxeMeta = netheriteAxe.getItemMeta();
                netheriteAxeMeta.setDisplayName(ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Pan's Axe");
                netheriteAxe.setItemMeta(netheriteAxeMeta);

                ItemMeta diamondShovelMeta = shovel.getItemMeta();
                diamondShovelMeta.setDisplayName(ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Hesperus' Trowel");
                shovel.setItemMeta(diamondShovelMeta);

                ItemMeta netheriteShovelMeta = netheriteShovel.getItemMeta();
                netheriteShovelMeta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Demeter's Trowel");
                netheriteShovel.setItemMeta(netheriteShovelMeta);

                ItemMeta diamondHelmetMeta = diamondHelmet.getItemMeta();
                diamondHelmetMeta.setDisplayName(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Hera's Kepi");
                diamondHelmet.setItemMeta(diamondHelmetMeta);

                ItemMeta netheriteHelmetMeta = netheriteHelmet.getItemMeta();
                netheriteHelmetMeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Zeus' Busby");
                netheriteHelmet.setItemMeta(netheriteHelmetMeta);

                ItemMeta diamondChestplateMeta = diamondChestplate.getItemMeta();
                diamondChestplateMeta.setDisplayName(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Hera's Cuirass");
                diamondChestplate.setItemMeta(diamondChestplateMeta);

                ItemMeta netheriteChestplateMeta = netheriteChestplate.getItemMeta();
                netheriteChestplateMeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Cuirass of Zeus");
                netheriteChestplate.setItemMeta(netheriteChestplateMeta);

                ItemMeta diamondLeggingsMeta = diamondLeggings.getItemMeta();
                diamondLeggingsMeta.setDisplayName(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Hera's Chaps");
                diamondLeggings.setItemMeta(diamondLeggingsMeta);

                ItemMeta netheriteLeggingsMeta = netheriteLeggings.getItemMeta();
                netheriteLeggingsMeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Zeus' Stockings");
                netheriteLeggings.setItemMeta(netheriteLeggingsMeta);

                ItemMeta diamondBootsMeta = diamondBoots.getItemMeta();
                diamondBootsMeta.setDisplayName(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Hera's Galoshes");
                diamondBoots.setItemMeta(diamondBootsMeta);

                ItemMeta netheriteBootsMeta = netheriteBoots.getItemMeta();
                netheriteBootsMeta.setDisplayName(ChatColor.AQUA.toString() + ChatColor.BOLD + "Zeus' Brogan");
                netheriteBoots.setItemMeta(netheriteBootsMeta);
                /*

                ItemMeta warzoneCoalMeta = warzoneCoal.getItemMeta();
                warzoneCoalMeta.setDisplayName(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "Warzone Coal");
                ArrayList<String> warzoneCoalLore = new ArrayList<>();
                warzoneCoalLore.add(" ");
                warzoneCoalLore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Value" + ChatColor.WHITE + ": " + ChatColor.GOLD + "3 " + ChatColor.GREEN + "coins");
                warzoneCoalMeta.setLore(warzoneCoalLore);
                warzoneCoal.setItemMeta(warzoneCoalMeta);

                **/
                staffVault.setItem(0, pickaxe);
                staffVault.setItem(1, axe);
                staffVault.setItem(2, shovel);
                staffVault.setItem(3, sword);
                staffVault.setItem(4, border);
                staffVault.setItem(5, netheritePickaxe);
                staffVault.setItem(6, netheriteAxe);
                staffVault.setItem(7, netheriteShovel);
                staffVault.setItem(8, netheriteSword);
                staffVault.setItem(9, diamondHelmet);
                staffVault.setItem(10, diamondChestplate);
                staffVault.setItem(11, diamondLeggings);
                staffVault.setItem(12, diamondBoots);
                staffVault.setItem(13, border);
                staffVault.setItem(14, netheriteHelmet);
                staffVault.setItem(15, netheriteChestplate);
                staffVault.setItem(16, netheriteLeggings);
                staffVault.setItem(17, netheriteBoots);
                player.openInventory(staffVault);
            }
        }
        return true;
    }
}
