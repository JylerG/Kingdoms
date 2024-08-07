package games.kingdoms.kingdoms.publiccmds.kingdoms.command;

import com.github.sanctum.panther.file.Configurable;
import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import games.kingdoms.kingdoms.publiccmds.kingdoms.configs.KingdomsConfig;
import games.kingdoms.kingdoms.publiccmds.kingdoms.configs.MoneyConfig;
import games.kingdoms.kingdoms.publiccmds.kingdoms.configs.StaffConfig;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class KingdomsCommands implements CommandExecutor {

    private Kingdoms plugin = Kingdoms.getPlugin(Kingdoms.class);
    private final KingdomsConfig kingdomsConfig = new KingdomsConfig(plugin);
    private final StaffConfig staffConfig = new StaffConfig(plugin);
    private final MoneyConfig moneyConfig = new MoneyConfig(plugin);

    public KingdomsCommands(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            MessageManager.consoleBad("You must be a player to execute this command.");
            return true;
        }
        switch (args.length) {
            case 0:
                //TODO: RANK, RAID, SET, INFO, WALLS, LIST, MAP
                commandList(player);
                break;

            case 1:
                World world = Bukkit.getWorld("kingdoms");
                Chunk chunk = player.getLocation().getChunk();
                String kingdom = plugin.getKingdoms().get(player.getUniqueId().toString());
                String chunkID = chunk.getX() + "," + chunk.getZ();
                Location spawn = player.getLocation();

                if (args[0].equalsIgnoreCase("spawn")) {
                    teleportToSpawn(player, kingdom);
                }

                if (args[0].equalsIgnoreCase("upgrade")) {
                    upgradeKingdom(player, kingdom);
                }

                if (args[0].equalsIgnoreCase("claim")) {
                    claimChunk(player, kingdom, chunkID);
                }

                if (args[0].equalsIgnoreCase("bans")) {
                    bannedKingdomsList(player);
                }

                if (args[0].equalsIgnoreCase("unclaim")) {
                    unclaimChunk(player, kingdom, chunkID);
                }

                if (args[0].equalsIgnoreCase("info")) {
                    //TODO: implement this
                    kingdomInfo(player, kingdom, chunkID);
                }
                if (args[0].equalsIgnoreCase("map")) {
                    //TODO: implement this
                    kingdomMap(player, kingdom, chunkID);
                }
                plugin.savePluginData(player);
                break;
            case 2:
                chunk = player.getLocation().getChunk();
                Player target = Bukkit.getPlayer(args[1]);
                world = Bukkit.getWorld("kingdoms");
                chunkID = chunk.getX() + "," + chunk.getZ();
                kingdom = plugin.getKingdoms().get(player.getUniqueId().toString());
                spawn = new Location(world, player.getLocation().getX(), player.getLocation().getBlockY(), player.getLocation().getZ());

                if (args[0].equalsIgnoreCase("create")) {
                    createKingdom(player, args[1]);
                }

                if (args[0].equalsIgnoreCase("set")) {
                    //TODO: add attributes that can be used below here
                    if (args[1].equalsIgnoreCase("spawn")) {
                        setSpawn(player, kingdom, spawn);
                    }
                }

                if (args[0].equalsIgnoreCase("ban")) {
                    banKingdomName(player, args[1]);
                }

                if (args[0].equalsIgnoreCase("unban")) {
                    unbanKingdomName(player, args[1]);
                }

                if (args[0].equalsIgnoreCase("disband")) {
                    disbandKingdom(player, args[1], args, chunkID);
                }

                if (args[0].equalsIgnoreCase("invite")) {
                    invitePlayerToKingdom(player, target, kingdom, args);
                }

                if (args[0].equalsIgnoreCase("uninvite")) {
                    uninvitePlayerFromKingdom(player, target, kingdom, args);
                }

                if (args[0].equalsIgnoreCase("join")) {
                    joinKingdom(player, kingdom, args);
                }

                if (args[0].equalsIgnoreCase("leave")) {
                    leaveKingdom(player, kingdom, args);
                }

                if (args[0].equalsIgnoreCase("promote")) {
                    promotePlayer(player, target, args);
                }

                if (args[0].equalsIgnoreCase("demote")) {
                    demotePlayer(player, target, args);
                }

                if (args[0].equalsIgnoreCase("kick")) {
                    kickPlayerFromKingdom(player, target, kingdom, args);
                }
                plugin.savePluginData(player);
                break;

            case 3:
                kingdom = args[1];
                target = Bukkit.getPlayer(args[2]);
                if (args[0].equalsIgnoreCase("transfer")) {
                    transferKingdom(player, target, kingdom, args);
                }
                plugin.savePluginData(player);
                break;

        }
        return true;
    }

    private void kingdomMap(Player player, String kingdom, String chunkID) {
        //TODO make this display a map of nearby kingdoms
    }

    private void kingdomInfo(Player player, String kingdom, String chunkID) {
        //TODO: make this display info about the requested kingdom
    }

    private void bannedKingdomsList(Player player) {

        Configurable kc = KingdomsConfig.getInstance().getConfig();

        if (!player.hasPermission("kingdoms.bans.view")) {
            return;
        }
        player.sendMessage(ChatColor.RED + "—————— " + ChatColor.YELLOW + "Banned Kingdoms" + ChatColor.RED + " ——————");

        try {
            kc.getNode("bannedNames").getKeys(false).forEach(key -> player.sendMessage(ChatColor.GOLD + key));
        } catch (NullPointerException e) {
            player.sendMessage(ChatColor.LIGHT_PURPLE + "No Banned Names");
        }
    }

    private void unbanKingdomName(Player player, String kingdom) {
        if (!player.hasPermission("kingdoms.unban")) {
            return;
        }
        if (plugin.getBannedNames().containsKey(kingdom) && plugin.getBannedNames().containsValue(kingdom)) {
            plugin.getBannedNames().put(kingdom, null);
            plugin.savePluginData();
            player.sendMessage(ChatColor.GREEN + "You unbanned " + ChatColor.WHITE + kingdom + ChatColor.GREEN + " from being created");
        } else {
            player.sendMessage(kingdom + ChatColor.RED + " is not currently banned");
        }
    }

    private void banKingdomName(Player player, String kingdom) {
        if (!player.hasPermission("kingdoms.ban")) {
            return;
        }
        if ((!plugin.getBannedNames().containsKey(kingdom)) && !plugin.getBannedNames().containsValue(kingdom)) {
            plugin.getBannedNames().put(kingdom, kingdom);
            plugin.savePluginData();
            player.sendMessage(ChatColor.GREEN + "You banned " + ChatColor.WHITE + kingdom + ChatColor.GREEN + " from being created");
        } else {
            player.sendMessage(kingdom + ChatColor.RED + " is already banned");
        }
    }

    private void transferKingdom(Player player, Player target, String kingdom, String[] args) {
        String playerUUID = player.getUniqueId().toString();
        String targetUUID = target.getUniqueId().toString();

        try {
            // Check if the player has the necessary permission for admin transfers
            if (!player.hasPermission("kingdoms.admin.transfer")) {
                // Check for the correct number of arguments
                if (args.length != 3) {
                    player.sendMessage(ChatColor.GOLD + "Usage: /kingdom transfer NAME <player>");
                    return;
                }
                //Check if the target is online
                if (target == null) {
                    player.sendMessage(args[2] + ChatColor.RED + " is not online");
                }
                //Check if the kingdom exists
                if (!plugin.getKingdoms().containsValue(kingdom)) {
                    player.sendMessage(kingdom + ChatColor.RED + " doesn't exist");
                    return;
                }
                // Check if the player is in a kingdom
                if (!plugin.getKingdoms().containsKey(playerUUID)) {
                    player.sendMessage(target.getName() + ChatColor.RED + " is not in a kingdom");
                    return;
                }

                // Check if the player is the owner of the specified kingdom
                if (!plugin.getOwner().get(playerUUID).equals(plugin.getKingdoms().get(playerUUID))) {
                    player.sendMessage(ChatColor.RED + "You are not the owner of " + ChatColor.GOLD + plugin.getKingdoms().get(playerUUID));
                    return;
                }

                // Check if the target player is a member of the specified kingdom
                if (!plugin.getKingdoms().get(targetUUID).equals(kingdom)) {
                    player.sendMessage(target.getName() + ChatColor.RED + " is not a member of " + ChatColor.WHITE + kingdom);
                    return;
                }

                // Transfer kingdom ownership
                plugin.getOwner().remove(player.getUniqueId().toString());
                plugin.getOwner().put(target.getUniqueId().toString(), kingdom);

                // Set ranks just in case the user doesn't have them
                plugin.getAdmin().put(playerUUID, kingdom);
                plugin.getMember().put(playerUUID, kingdom);
                plugin.getMember().put(targetUUID, kingdom);
                player.sendMessage(ChatColor.GREEN + "You transferred " + ChatColor.WHITE + kingdom + ChatColor.GREEN + " to " + ChatColor.WHITE + target.getName());
                target.sendMessage(player.getName() + ChatColor.GREEN + " transferred " + ChatColor.WHITE + kingdom + ChatColor.GREEN + " to you");
                return;
            }

            // Admin transfer logic
            if (args.length != 3) {
                player.sendMessage(ChatColor.GOLD + "Usage: /kingdom transfer NAME <player>");
                return;
            }

            if (!plugin.getKingdoms().containsValue(kingdom)) {
                player.sendMessage(kingdom + ChatColor.RED + " doesn't exist");
                return;
            }

            // Check if the player is in a kingdom
            if (!plugin.getKingdoms().containsKey(playerUUID)) {
                player.sendMessage(target.getName() + ChatColor.RED + " is not in a kingdom");
                return;
            }

            // Check if the target player is in the specified kingdom
            if (!plugin.getKingdoms().get(targetUUID).equals(kingdom)) {
                player.sendMessage(target.getName() + ChatColor.RED + " is not in " + ChatColor.WHITE + kingdom);
                return;
            }

            // Transfer kingdom ownership
            plugin.getOwner().remove(plugin.getKingdoms().get(kingdom));
            plugin.getOwner().put(targetUUID, kingdom);

            // Inform players about the transfer
            player.sendMessage(ChatColor.GREEN + "You transferred " + ChatColor.WHITE + kingdom + ChatColor.GREEN + " to " + ChatColor.WHITE + target.getName());
            target.sendMessage(player.getName() + ChatColor.GREEN + " transferred " + ChatColor.WHITE + kingdom + ChatColor.GREEN + " to you");
        } catch (NullPointerException e) {
            player.sendMessage(target.getName() + " is not a member of " + ChatColor.WHITE + kingdom);
        }
    }

    private void kickPlayerFromKingdom(Player player, Player target, String kingdom, String[] args) {
        String commandSender = player.getUniqueId().toString();
        String commandTarget = target.getUniqueId().toString();
        try {
            if (target == null) {
                player.sendMessage(args[1] + "is not online");
                return;
            }
            if (plugin.getKingdoms().get(commandTarget).equals(plugin.getKingdoms().get(commandTarget))) {
                if (plugin.getOwner().get(commandSender).equals(plugin.getKingdoms().get(commandSender)) || plugin.getAdmin().get(commandSender).equals(plugin.getKingdoms().get(commandSender))) {
                    plugin.getKingdoms().remove(commandTarget, plugin.getKingdoms().get(commandSender));
                    plugin.getMember().remove(commandTarget, plugin.getKingdoms().get(commandSender));
                    plugin.getAdmin().remove(commandTarget, plugin.getKingdoms().get(commandSender));
                    player.sendMessage(ChatColor.GREEN + "You kicked " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + " from " + ChatColor.WHITE + plugin.getKingdoms().get(commandSender));
                    target.sendMessage(ChatColor.RED + "You were kicked from " + ChatColor.WHITE + plugin.getKingdoms().get(commandSender));
                } else {
                    player.sendMessage(ChatColor.GREEN + "You do not have permission to kick players from " + ChatColor.WHITE + plugin.getKingdoms().get(commandSender));
                }
            } else {
                player.sendMessage("You are not in a kingdom");
            }
        } catch (NullPointerException e) {
            player.sendMessage(target.getName() + ChatColor.RED + " is not a member of " + kingdom);
        }
    }

    private void demotePlayer(Player player, Player target, String[] args) {

        try {
            String kingdom = plugin.getKingdoms().get(player.getUniqueId().toString());
            if (target == null) {
                player.sendMessage(args[1] + ChatColor.RED + " is not online");
                return;
            }

            if (!plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
                player.sendMessage(ChatColor.RED + "You are not in a kingdom");
                return;
            }

            if (!plugin.getOwner().get(player.getUniqueId().toString()).equals(plugin.getKingdoms().get(player.getUniqueId().toString()))) {
                player.sendMessage(ChatColor.RED + "You must be the owner of " + ChatColor.WHITE + kingdom + ChatColor.RED + " to demote members");
                return;
            }

            if (plugin.getKingdoms().get(target.getUniqueId().toString()).equalsIgnoreCase(kingdom) && !plugin.getAdmin().containsKey(target.getUniqueId().toString())) {
                player.sendMessage(target.getName() + ChatColor.RED + " is already a " + ChatColor.WHITE + "member");
                return;
            }

            plugin.getAdmin().remove(target.getUniqueId().toString(), plugin.getKingdoms().get(player.getUniqueId().toString()));
            plugin.getMember().put(target.getUniqueId().toString(), plugin.getKingdoms().get(player.getUniqueId().toString()));

            player.sendMessage(ChatColor.GREEN + "You demoted " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + " to " + ChatColor.WHITE + "member");
            target.sendMessage(ChatColor.RED + "You were demoted to " + ChatColor.WHITE + "member" + ChatColor.RED + " in " + ChatColor.WHITE + kingdom);
        } catch (NullPointerException e) {
            player.sendMessage(ChatColor.RED + "You must be the owner of " + ChatColor.WHITE + plugin.getKingdoms().get(player.getUniqueId().toString()) + ChatColor.RED + " to demote players");
        }
    }

    private void promotePlayer(Player player, Player target, String[] args) {

        try {
            String kingdom = plugin.getKingdoms().get(player.getUniqueId().toString());

            if (target == null) {
                player.sendMessage(args[1] + ChatColor.RED + " is not online");
                return;
            }

            if (!plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
                player.sendMessage(ChatColor.RED + "You are not in a kingdom");
                return;
            }

            if (!plugin.getOwner().get(player.getUniqueId().toString()).equals(plugin.getKingdoms().get(player.getUniqueId().toString()))) {
                player.sendMessage(ChatColor.RED + "You must be the owner of " + ChatColor.WHITE + kingdom + ChatColor.RED + " to promote members");
                return;
            }

            if (plugin.getAdmin().containsKey(target.getUniqueId().toString()) && plugin.getAdmin().get(target.getUniqueId().toString()).equalsIgnoreCase(kingdom)) {
                player.sendMessage(target.getName() + ChatColor.RED + " is already an " + ChatColor.WHITE + "admin");
                return;
            }

            plugin.getAdmin().put(target.getUniqueId().toString(), kingdom);

            player.sendMessage(ChatColor.GREEN + "You promoted " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + " to " + ChatColor.WHITE + "admin");
            target.sendMessage(ChatColor.GREEN + "You were promoted to " + ChatColor.WHITE + "admin" + ChatColor.GREEN + " in " + ChatColor.WHITE + kingdom);
        } catch (NullPointerException e) {
            player.sendMessage(ChatColor.RED + "You must be the owner of " + ChatColor.WHITE + plugin.getKingdoms().get(player.getUniqueId().toString()) + ChatColor.RED + " to promote players");
        }
    }

    private void leaveKingdom(Player player, String kingdom, String[] args) {
        String playerUUID = player.getUniqueId().toString();

        if (!plugin.getKingdoms().containsKey(playerUUID)) {
            player.sendMessage(ChatColor.RED + "You are not in a kingdom");
            return;
        }

        if (!plugin.getKingdoms().containsValue(args[1])) {
            player.sendMessage(args[1] + ChatColor.RED + " doesn't exist");
            return;
        }

        if (!plugin.getKingdoms().get(playerUUID).equals(args[1])) {
            player.sendMessage(ChatColor.RED + "You are not a member of " + ChatColor.WHITE + args[1]);
            return;
        }

        if (plugin.getOwner().getOrDefault(playerUUID, "").equals(args[1])) {
            player.sendMessage(ChatColor.RED + "You must transfer " + ChatColor.GOLD + args[1] + ChatColor.RED + " to another member before you can leave or " + ChatColor.GOLD + "/k disband " + args[1] + ChatColor.RED + " if you wish to delete the kingdom completely");
            return;
        }

        plugin.getKingdoms().remove(playerUUID, kingdom);
        plugin.getAdmin().remove(playerUUID, kingdom);
        plugin.getMember().remove(playerUUID, kingdom);
        plugin.getCanClaim().remove(playerUUID, kingdom);
        plugin.getCanUnclaim().remove(playerUUID, kingdom);

        player.sendMessage(ChatColor.GREEN + "You left " + ChatColor.WHITE + args[1]);
    }

    private void joinKingdom(Player player, String kingdom, String[] args) {
        try {
            // Check if the player is an owner of a kingdom
            if (plugin.getOwner().containsKey(player.getUniqueId().toString())) {
                player.sendMessage(ChatColor.RED + "You are a kingdom owner. If you wish to join a different kingdom, " +
                        "you must disband your current kingdom " + ChatColor.GOLD + "/k disband " + plugin.getKingdoms().get(player.getUniqueId().toString())
                        + ChatColor.RED + " or transfer it " + ChatColor.GOLD + "/k transfer " + plugin.getKingdoms().get(player.getUniqueId().toString()));
                return;
            }

            // Check if the kingdom exists
            if (!plugin.getKingdomExists().containsKey(args[1])) {
                player.sendMessage(args[1] + ChatColor.RED + " doesn't exist");
                return;
            }

            // Check if the player is invited to the kingdom
            String invitedKingdom = plugin.getInviteList().get(player.getUniqueId().toString());

            if (invitedKingdom != null && invitedKingdom.equalsIgnoreCase(args[1])) {
                // Count current members in the kingdom
                int memberCount = 0;
                for (String memberID : plugin.getKingdoms().keySet()) {
                    if (plugin.getKingdoms().get(memberID).equals(args[1])) {
                        memberCount++;
                    }
                }

                // Check if the kingdom has room for new members
                if (plugin.getMaxMembers().get(args[1]) > memberCount) {
                    plugin.getInviteList().remove(player.getUniqueId().toString());
                    plugin.getKingdoms().put(player.getUniqueId().toString(), args[1]);
                    plugin.getMember().put(player.getUniqueId().toString(), args[1]);

                    player.sendMessage(ChatColor.GREEN + "You joined " + ChatColor.WHITE + args[1]);
                    return;
                } else {
                    player.sendMessage(args[1] + ChatColor.RED + " is at maximum capacity");
                }
            } else {
                // Player not invited and doesn't have admin join permission
                if (!plugin.getKingdoms().containsKey(player.getUniqueId().toString()) && !player.hasPermission("kingdoms.admin.join")) {
                    player.sendMessage(ChatColor.RED + "You have not been invited to " + ChatColor.WHITE + args[1]);
                }
            }

            // Check if the player is already in the kingdom
            String currentKingdom = plugin.getKingdoms().get(player.getUniqueId().toString());

            if (currentKingdom != null && currentKingdom.equalsIgnoreCase(args[1])) {
                player.sendMessage(ChatColor.RED + "You are already a member of " + ChatColor.WHITE + args[1]);
                return;
            }

            // Join the kingdom as admin (if they have permission)
            if (player.hasPermission("kingdoms.admin.join")) {
                plugin.getKingdoms().put(player.getUniqueId().toString(), args[1]);
                plugin.getMember().put(player.getUniqueId().toString(), args[1]);

                player.sendMessage(ChatColor.GREEN + "You joined " + ChatColor.WHITE + args[1]);
            }
        } catch (NullPointerException e) {
            player.sendMessage(args[1] + ChatColor.RED + " doesn't exist");
        }
    }

    private void uninvitePlayerFromKingdom(Player player, Player target, String kingdom, String[] args) {

        try {
            if (!plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
                player.sendMessage(ChatColor.RED + "You are not in a kingdom");
            }

            if (!plugin.getOwner().containsKey(player.getUniqueId().toString()) && !plugin.getAdmin().containsKey(player.getUniqueId().toString())) {
                player.sendMessage(ChatColor.RED + "You are not an owner or admin of " + ChatColor.WHITE + kingdom);
                return;
            }

            plugin.getInviteList().remove(target.getUniqueId().toString(), plugin.getKingdoms().get(player.getUniqueId().toString()));

            player.sendMessage(ChatColor.GREEN + "You uninvited " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + " from " + ChatColor.WHITE + kingdom);
            target.sendMessage(ChatColor.RED + "You were uninvited from " + ChatColor.WHITE + kingdom);
        } catch (NullPointerException e) {
            player.sendMessage(target.getName() + ChatColor.RED + " hasn't been invited to " + ChatColor.WHITE + kingdom);
        }
    }

    private void disbandKingdom(Player player, String kingdom, String[] args, String chunkID) {
        try {
            // If the player is not an admin, check if they are trying to disband their own kingdom
            if (!player.hasPermission("kingdoms.admin.disband")) {
                if (!plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
                    player.sendMessage(ChatColor.RED + "You are not in a kingdom");
                    return;
                }

                String playerKingdom = plugin.getKingdoms().get(player.getUniqueId().toString());
                if (!kingdom.equalsIgnoreCase(playerKingdom)) {
                    player.sendMessage(ChatColor.RED + "You do not have permission to disband " + ChatColor.WHITE + kingdom);
                    return;
                }
            }

            // Check if the specified kingdom exists
            if (!plugin.getKingdomExists().containsKey(kingdom)) {
                player.sendMessage(kingdom + ChatColor.RED + " doesn't exist");
                return;
            }

            // Iterate over kingdoms and disband the specified one
            Iterator<Map.Entry<String, String>> iterator = plugin.getKingdoms().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                if (entry.getValue().equals(kingdom)) {
                    String playerObj = entry.getKey();
                    Player kingdomOwner = Bukkit.getPlayer(UUID.fromString(playerObj));
                    if (kingdomOwner != null) {
                        kingdomOwner.sendMessage(kingdom + ChatColor.GREEN + " disbanded");
                    }

                    // Notify all members of the disbandment
                    for (Map.Entry<String, String> memberEntry : plugin.getMember().entrySet()) {
                        if (memberEntry.getValue().equals(kingdom)) {
                            Player member = Bukkit.getPlayer(UUID.fromString(memberEntry.getKey()));
                            if (member != null) {
                                member.sendMessage(ChatColor.GREEN + "Your kingdom " + ChatColor.WHITE + kingdom + ChatColor.GREEN + " has been disbanded.");
                            }
                        }
                    }

                    // Remove all associated entries from other maps
                    plugin.getKingdomExists().remove(kingdom);
                    plugin.getKingdomSpawn().remove(kingdom);
                    plugin.getOwner().remove(playerObj);
                    plugin.getAdmin().remove(playerObj);
                    plugin.getMember().remove(playerObj);
                    plugin.getCanClaim().remove(playerObj);
                    plugin.getCanUnclaim().remove(playerObj);

                    iterator.remove(); // Remove the kingdom entry

                    // Remove claimed chunks associated with the kingdom
                    Iterator<Map.Entry<String, String>> chunkIterator = plugin.getClaimedChunks().entrySet().iterator();
                    while (chunkIterator.hasNext()) {
                        Map.Entry<String, String> chunkEntry = chunkIterator.next();
                        if (chunkEntry.getValue().equals(kingdom)) {
                            chunkIterator.remove(); // Remove the chunk entry
                        }
                    }
                    // Stop iteration after finding and disbanding the kingdom
                    break;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            player.sendMessage(ChatColor.RED + "An error occurred while trying to disband the kingdom. Please contact an administrator.");
        }
    }


    private void createKingdom(Player player, String kingdom) {
        if (plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
            player.sendMessage(ChatColor.RED + "You are already in a kingdom");
            return;
        }
        if (plugin.getKingdoms().containsValue(kingdom)) {
            player.sendMessage(kingdom + ChatColor.RED + " already exists");
            return;
        }
        if (plugin.getBannedNames().containsValue(kingdom)) {
            player.sendMessage(ChatColor.RED + "You cannot create a kingdom called " + ChatColor.WHITE + kingdom);
            return;
        }

        plugin.getKingdomExists().put(kingdom, kingdom);
        plugin.getKingdoms().put(player.getUniqueId().toString(), kingdom);
        plugin.getOwner().put(player.getUniqueId().toString(), kingdom);
        plugin.getMember().put(player.getUniqueId().toString(), kingdom);
        plugin.getCanClaim().put(player.getUniqueId().toString(), kingdom);
        plugin.getCanUnclaim().put(player.getUniqueId().toString(), kingdom);
        plugin.getClaimPrice().put(kingdom, 10_000L);
        plugin.getMemberPrice().put(kingdom, 10_000L);
        plugin.getMaxClaims().put(kingdom, 10);
        plugin.getMaxMembers().put(kingdom, 6);
        player.sendMessage(kingdom + ChatColor.GREEN + " created");
    }

    private void commandList(Player player) {
        player.sendMessage(ChatColor.RED + "—————— " + ChatColor.YELLOW + "SUGGESTED COMMANDS " + ChatColor.RED + "——————");
        player.sendMessage(ChatColor.YELLOW + "/kingdom create <kingdom>");
        player.sendMessage(ChatColor.YELLOW + "/kingdom transfer <kingdom> <player>");
        player.sendMessage(ChatColor.YELLOW + "/kingdom disband <kingdom>");
        player.sendMessage(ChatColor.YELLOW + "/kingdom invite <player>");
        player.sendMessage(ChatColor.YELLOW + "/kingdom uninvite <player>");
        player.sendMessage(ChatColor.YELLOW + "/kingdom join <kingdom>");
        player.sendMessage(ChatColor.YELLOW + "/kingdom spawn");
        player.sendMessage(ChatColor.YELLOW + "/kingdom leave <kingdom>");
        player.sendMessage(ChatColor.YELLOW + "/kingdom kick <player>");
        player.sendMessage(ChatColor.YELLOW + "/kingdom map");
        player.sendMessage(ChatColor.YELLOW + "/kingdom claim");
        player.sendMessage(ChatColor.YELLOW + "/kingdom unclaim");
        player.sendMessage(ChatColor.YELLOW + "/kingdom promote <player>");
        player.sendMessage(ChatColor.YELLOW + "/kingdom demote <player>");
        player.sendMessage(ChatColor.YELLOW + "/kingdom rank [command] [args]");
        player.sendMessage(ChatColor.YELLOW + "/kingdom raid [start:end:logs]");
        player.sendMessage(ChatColor.YELLOW + "/kingdom set <attribute> [args]");
        player.sendMessage(ChatColor.YELLOW + "/kingdom info <kingdom>");
        player.sendMessage(ChatColor.YELLOW + "/kingdom walls <show:hide:upgrade>");
        player.sendMessage(ChatColor.YELLOW + "/kingdom list [page]");
    }

    //TODO: figure out why this doesn't work
    private void setSpawn(Player player, String kingdom, Location spawn) {
        try {
            String playerId = player.getUniqueId().toString();
            String adminKingdom = plugin.getAdmin().get(playerId);
            String ownerKingdom = plugin.getOwner().get(playerId);

            if ((adminKingdom != null && adminKingdom.equals(kingdom)) ||
                    (ownerKingdom != null && ownerKingdom.equals(kingdom))) {
                plugin.getKingdomSpawn().put(kingdom, spawn);
                player.sendMessage(ChatColor.GREEN + "You set " + ChatColor.WHITE + kingdom + ChatColor.GREEN + "'s spawn");
            } else {
                player.sendMessage(ChatColor.RED + "You do not have permission to set " + ChatColor.WHITE + kingdom + ChatColor.RED + "'s spawn");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            player.sendMessage(ChatColor.RED + "An error occurred while setting the spawn.");
        }
    }

    private void teleportToSpawn(Player player, String kingdom) {
        if (!plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
            player.sendMessage(ChatColor.RED + "You are not in a kingdom");
            return;
        }
        if (!plugin.getKingdomSpawn().containsKey(kingdom)) {
            player.sendMessage(kingdom + ChatColor.RED + "'s spawn has not been set");
            return;
        }

        player.teleport(plugin.getKingdomSpawn().get(kingdom));
        player.sendMessage(ChatColor.GREEN + "Teleported to " + ChatColor.WHITE + kingdom + ChatColor.GREEN + "'s spawn");
    }

    private void upgradeKingdom(Player player, String kingdom) {

        if (!plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
            MessageManager.playerBad(player, "You are not in a kingdom");
            return;
        }

        try {
            Inventory upgrades = Bukkit.createInventory(player, 27, ChatColor.DARK_AQUA + kingdom + " Upgrades");

            ItemStack border = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            // claims, members
            ItemStack claims = new ItemStack(Material.GREEN_WOOL);
            ItemStack members = new ItemStack(Material.LIME_WOOL);

            ItemMeta borderMeta = border.getItemMeta();
            borderMeta.setDisplayName(" ");
            border.setItemMeta(borderMeta);

            ItemMeta claimMeta = claims.getItemMeta();
            // Calculate the new price based on the current maximum claims
            for (String chunkID : plugin.getClaimedChunks().keySet()) {
                if (plugin.getClaimedChunks().get(chunkID).equals(plugin.getKingdoms().get(player.getUniqueId().toString()))) {
                    // This chunk is claimed by the player's kingdom
                    // You can increment the claim count and update the scoreboard here
                    int claimCount = 0;
                    for (String otherChunkID : plugin.getClaimedChunks().keySet()) {
                        if (plugin.getClaimedChunks().get(otherChunkID).equals(plugin.getKingdoms().get(player.getUniqueId().toString()))) {
                            claimCount++;
                        }
                    }
                    claimMeta.setDisplayName(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Increase Claim Limit");
                    ArrayList<String> claimsLore = new ArrayList<>();
                    claimsLore.add(" ");
                    claimsLore.add(ChatColor.GREEN + "Cost: " + ChatColor.GOLD + plugin.getClaimPrice().get(plugin.getKingdoms().get(player.getUniqueId().toString())) + ChatColor.GREEN + " coins");
                    claimsLore.add(ChatColor.LIGHT_PURPLE + "Current Max: " + ChatColor.WHITE + plugin.getMaxClaims().get(plugin.getKingdoms().get(player.getUniqueId().toString())));
                    claimsLore.add(ChatColor.RED.toString() + ChatColor.BOLD + "NOTE: USE THIS COMMAND AGAIN");
                    claimsLore.add(ChatColor.RED.toString() + ChatColor.BOLD + "TO REFRESH THE VALUES");
                    claimMeta.setLore(claimsLore);
                    claimMeta.addEnchant(Enchantment.DURABILITY, 1, false);
                    claimMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    claims.setItemMeta(claimMeta);
                    claimCount++;
                }
            }

            ItemMeta memberMeta = members.getItemMeta();
            memberMeta.setDisplayName(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Increase Member Limit");

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (plugin.getClaimedChunks().get(player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ())
                        .equalsIgnoreCase(plugin.getKingdoms().get(player.getUniqueId().toString()))) {
                    int memberCount = 0;

                    if (plugin.getKingdoms().get(p.getUniqueId().toString()).equalsIgnoreCase
                            (plugin.getClaimedChunks().get(player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ()))) {
                        memberCount++;
                    }
                    ArrayList<String> memberLore = new ArrayList<>();
                    memberLore.add(" ");
                    memberLore.add(ChatColor.GREEN + "Cost: " + ChatColor.GOLD + plugin.getMemberPrice().get(plugin.getKingdoms().get(player.getUniqueId().toString())) + ChatColor.GREEN + " coins");
                    memberLore.add(ChatColor.LIGHT_PURPLE + "Current Max: " + ChatColor.WHITE + plugin.getMaxMembers().get(plugin.getKingdoms().get(player.getUniqueId().toString())));
                    memberLore.add(ChatColor.RED.toString() + ChatColor.BOLD + "NOTE: USE THIS COMMAND AGAIN");
                    memberLore.add(ChatColor.RED.toString() + ChatColor.BOLD + "TO REFRESH THE VALUES");
                    memberMeta.setLore(memberLore);
                    memberMeta.addEnchant(Enchantment.DURABILITY, 1, false);
                    memberMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    members.setItemMeta(memberMeta);
                    memberCount++;
                }
            }

            upgrades.setItem(0, border);
            upgrades.setItem(1, border);
            upgrades.setItem(2, border);
            upgrades.setItem(3, border);
            upgrades.setItem(4, border);
            upgrades.setItem(5, border);
            upgrades.setItem(6, border);
            upgrades.setItem(7, border);
            upgrades.setItem(8, border);
            upgrades.setItem(9, border);
            upgrades.setItem(10, border);
            upgrades.setItem(11, claims);
            upgrades.setItem(12, border);
            upgrades.setItem(13, border);
            upgrades.setItem(14, border);
            upgrades.setItem(15, members);
            upgrades.setItem(16, border);
            upgrades.setItem(17, border);
            upgrades.setItem(18, border);
            upgrades.setItem(19, border);
            upgrades.setItem(20, border);
            upgrades.setItem(21, border);
            upgrades.setItem(22, border);
            upgrades.setItem(23, border);
            upgrades.setItem(24, border);
            upgrades.setItem(25, border);
            upgrades.setItem(26, border);

            player.openInventory(upgrades);
        } catch (NullPointerException e) {
            player.sendMessage(ChatColor.RED + "You must be in a " + ChatColor.WHITE + kingdom + ChatColor.RED + " claim to execute this command");
        }
    }

    private void claimChunk(Player player, String kingdom, String chunkID) {

        if (!plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
            player.sendMessage(ChatColor.RED + "You are not in a kingdom");
            return;
        }

        if (!plugin.getCanClaim().containsKey(player.getUniqueId().toString())) {
            player.sendMessage(ChatColor.RED + "You do not have permission to claim chunks for " + ChatColor.WHITE + kingdom);
            return;
        }

        if (plugin.getClaimedChunks().containsKey(chunkID)) {
            String ownerKingdom = plugin.getClaimedChunks().get(chunkID);
            if (ownerKingdom.equals(kingdom)) {
                player.sendMessage(ChatColor.RED + "Chunk is already claimed by your kingdom");
            } else {
                player.sendMessage(ChatColor.RED + "Chunk is claimed by " + ChatColor.WHITE + ownerKingdom);
            }
            return;
        }

        int claimCount = 0;
        for (String ChunkID : plugin.getClaimedChunks().keySet()) {
            if (plugin.getClaimedChunks().get(ChunkID).equals(plugin.getKingdoms().get(player.getUniqueId().toString()))) {
                claimCount++;
            }
        }
        if (plugin.getMaxClaims().get(plugin.getKingdoms().get(player.getUniqueId().toString())) > claimCount) {
            plugin.getClaims().put(kingdom, chunkID);
            plugin.getClaimedChunks().put(chunkID, kingdom);
            player.sendMessage(ChatColor.GREEN + "Chunk claimed");
        } else {
            player.sendMessage(kingdom + ChatColor.RED + " has claimed " +
                    ChatColor.YELLOW + claimCount + "/" + plugin.getMaxClaims().get(plugin.getKingdoms().get(player.getUniqueId().toString()))
                    + ChatColor.RED + " chunks. " + ChatColor.GOLD + "/k upgrade " + ChatColor.RED + "to increase this amount");
        }
    }

    private void unclaimChunk(Player player, String kingdom, String chunkID) {
        try {
            if (!plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
                player.sendMessage(ChatColor.RED + "You are not in a kingdom");
                return;
            }

            if (!plugin.getClaimedChunks().get(chunkID).equals(kingdom)) {
                player.sendMessage(ChatColor.RED + "You are not a member of " + ChatColor.WHITE + plugin.getClaimedChunks().get(chunkID));
                return;
            }

            if (!plugin.getCanUnclaim().containsKey(player.getUniqueId().toString())) {
                player.sendMessage(ChatColor.RED + "You do not have permission to unclaim chunks for " + ChatColor.WHITE + plugin.getKingdoms().get(player.getUniqueId().toString()));
                return;
            }

            plugin.getClaims().remove(kingdom, chunkID);
            plugin.getClaimedChunks().remove(chunkID, kingdom);
            player.sendMessage(ChatColor.GREEN + "Chunk unclaimed");

        } catch (NullPointerException e) {
            player.sendMessage(ChatColor.RED + "This chunk is not claimed");
        }
    }

    private void invitePlayerToKingdom(Player player, Player target, String kingdom, String[] args) {

        if (target == null) {
            player.sendMessage(args[1] + ChatColor.RED + " must be online for you to invite them.");
            return;
        }

        if (target == player) {
            MessageManager.playerBad(player, "You cannot invite yourself to a kingdom");
            return;
        }

        if (plugin.getKingdoms().containsValue(target.getUniqueId().toString())) {
            player.sendMessage(target.getName() + ChatColor.RED + " is already in a kingdom");
            return;
        }

        String playerUUID = player.getUniqueId().toString();

        if (!plugin.getOwner().containsKey(playerUUID) && !plugin.getAdmin().containsKey(playerUUID)) {
            player.sendMessage(ChatColor.RED + "You are not an owner or admin of " + ChatColor.WHITE + kingdom);
            return;
        }

        if (plugin.getInviteList().containsKey(target.getUniqueId().toString()) && plugin.getInviteList().get(target.getUniqueId().toString()).equals(kingdom)) {
            player.sendMessage(target.getName() + ChatColor.RED + " has already been invited to " + ChatColor.WHITE + kingdom);
            return;
        }

        int memberCount = 0;
        for (String member : plugin.getMember().keySet()) {
            if (plugin.getMember().get(member).equals(plugin.getKingdoms().get(player.getUniqueId().toString()))) {
                memberCount++;
            }
        }
        if (plugin.getMaxMembers().get(plugin.getKingdoms().get(player.getUniqueId().toString())) > memberCount) {
            // Add the invitation to the inviteList
            plugin.getInviteList().put(target.getUniqueId().toString(), kingdom);
            player.sendMessage(ChatColor.GREEN + "You invited " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + " to " + ChatColor.WHITE + kingdom);
            target.sendMessage(ChatColor.GREEN + "You were invited to " + ChatColor.WHITE + kingdom + ChatColor.GREEN + " by " + ChatColor.WHITE + player.getName());
        } else {
            player.sendMessage(kingdom + ChatColor.RED + " has " +
                    ChatColor.YELLOW + memberCount + "/" + plugin.getMaxMembers().get(plugin.getKingdoms().get(player.getUniqueId().toString()))
                    + ChatColor.RED + " members. " + ChatColor.GOLD + "/k upgrade " + ChatColor.RED + "to increase this amount");
        }
    }
}