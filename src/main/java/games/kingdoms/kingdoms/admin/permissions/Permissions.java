package games.kingdoms.kingdoms.admin.permissions;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Permissions implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (player.hasPermission("kingdoms.permissions.view")) {
                // /view <category>
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "——————" + ChatColor.YELLOW + " CATEGORIES " + ChatColor.RED + "——————");
                    player.sendMessage(ChatColor.GOLD + "NPCS");
                    player.sendMessage(ChatColor.GOLD + "ORES");
                    player.sendMessage(ChatColor.GOLD + "BANS");
                    player.sendMessage(ChatColor.GOLD + "RANK");
                    player.sendMessage(ChatColor.GOLD + "ADMIN");
                    player.sendMessage(ChatColor.GOLD + "RELOAD");
                    player.sendMessage(ChatColor.GOLD + "ECONOMY");
                    player.sendMessage(ChatColor.GOLD + "GAMEMODE");
                    player.sendMessage(ChatColor.GOLD + "NICKNAME");
                    player.sendMessage(ChatColor.RED + "/view <category> " +
                            ChatColor.LIGHT_PURPLE + "to view a category");
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("rank")) {
                        player.sendMessage(ChatColor.RED + "——————" + ChatColor.YELLOW + " PERMISSIONS " + ChatColor.RED + "——————");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.setrank.default");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.setrank.vip");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.setrank.hero");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.setrank.jrmod");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.setrank.mod");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.setrank.srmod");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.setrank.jradmin");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.setrank.admin");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.fly");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.feed");
                    } else if (args[0].equalsIgnoreCase("npcs")) {
                        player.sendMessage(ChatColor.RED + "——————" + ChatColor.YELLOW + " PERMISSIONS " + ChatColor.RED + "——————");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.npc.spawn");
                    } else if (args[0].equalsIgnoreCase("ores")) {
                        player.sendMessage(ChatColor.RED + "——————" + ChatColor.YELLOW + " PERMISSIONS " + ChatColor.RED + "——————");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.ore.give");
                    } else if (args[0].equalsIgnoreCase("bans")) {
                        player.sendMessage(ChatColor.RED + "——————" + ChatColor.YELLOW + " PERMISSIONS " + ChatColor.RED + "——————");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.ban");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.bans.view");
                    } else if (args[0].equalsIgnoreCase("admin")) {
                        player.sendMessage(ChatColor.RED + "——————" + ChatColor.YELLOW + " PERMISSIONS " + ChatColor.RED + "——————");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.staff.modmode");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.move");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.admin.join");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.admin.disband");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.admin.transfer");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.setrank.fake");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.jrmod.punish");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.mod.punish");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.srmod.punish");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.admin.punish");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.admin.punish.reset");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.password.reset");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.staffvault");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.vanish");
                    } else if (args[0].equalsIgnoreCase("reload")) {
                        player.sendMessage(ChatColor.RED + "——————" + ChatColor.YELLOW + " PERMISSIONS " + ChatColor.RED + "——————");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.staff.reload");
                        player.sendMessage(ChatColor.RED + "/view <perm> " +
                                ChatColor.LIGHT_PURPLE + "to view what a permission is for");
                    } else if (args[0].equalsIgnoreCase("economy")) {
                        player.sendMessage(ChatColor.RED + "——————" + ChatColor.YELLOW + " PERMISSIONS " + ChatColor.RED + "——————");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.economy");
                    } else if (args[0].equalsIgnoreCase("gamemode")) {
                        player.sendMessage(ChatColor.RED + "——————" + ChatColor.YELLOW + " PERMISSIONS " + ChatColor.RED + "——————");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.set.gamemode.survival");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.set.gamemode.creative");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.set.gamemode.spectator");
                        player.sendMessage(ChatColor.GOLD + "kingdoms.set.gamemode.adventure");
                    }

                } else if (args.length > 1) {
                    player.sendMessage(ChatColor.GOLD + "Usage: /view");
                }
            } else {
                player.sendMessage(ChatColor.RED + "You must have the " + ChatColor.GOLD + "kingdoms.permissions.view "
                        + ChatColor.RED + "permission to view all of " + ChatColor.WHITE + "Kingdoms" + ChatColor.RED + "'s permissions");
            }
        }
        return true;
    }
}
