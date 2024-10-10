package games.kingdoms.kingdoms.publiccmds.kingdoms.command;

import com.github.sanctum.panther.file.Configurable;
import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import games.kingdoms.kingdoms.admin.configs.KingdomsConfig;
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

import java.util.*;

public class KingdomsCommands implements CommandExecutor {

    final Kingdoms plugin = Kingdoms.getPlugin();
    final HashMap<String, String> bannedNames= plugin.getBannedNames();
    final HashMap<String, String> kingdoms = plugin.getKingdoms();
    final HashMap<String, Integer> maxMembers = plugin.getMaxMembers();
    final HashMap<String, Integer> maxClaims = plugin.getMaxClaims();
    final HashMap<String, String> claims = plugin.getClaims();
    final HashMap<String, String> claimedChunks = plugin.getClaimedChunks();
    final HashMap<String, Integer> claimPrice = plugin.getClaimPrice();
    final HashMap<String, String> admin = plugin.getAdmin();
    final HashMap<String, String> owner = plugin.getOwner();
    final HashMap<String, String> member = plugin.getMember();
    final HashMap<String, String> canClaim = plugin.getCanClaim();
    final HashMap<String, String> canUnclaim = plugin.getCanUnclaim();
    final HashMap<String, String> inviteList = plugin.getInviteList();
    final HashMap<String, Integer> memberPrice = plugin.getMemberPrice();
    final HashMap<String, Location> kingdomSpawn = plugin.getKingdomSpawn();

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
                String kingdom = kingdoms.get(player.getUniqueId().toString());
                String chunkID = chunk.getX() + "," + chunk.getZ();

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
                    plugin.restoreOfflineData();
                    plugin.restorePluginData();
                    unclaimChunk(player, kingdom, chunkID);
                }

                if (args[0].equalsIgnoreCase("info")) {
                    kingdomInfo(player, kingdom, chunkID);
                }
                plugin.savePluginData();
                break;
            case 2:
                chunk = player.getLocation().getChunk();
                Player target = Bukkit.getPlayer(args[1]);
                world = Bukkit.getWorld("kingdoms");
                chunkID = chunk.getX() + "," + chunk.getZ();
                kingdom = kingdoms.get(player.getUniqueId().toString());
                Location spawn = new Location(world, player.getLocation().getX(), player.getLocation().getBlockY(), player.getLocation().getZ(), player.getLocation().getYaw(), player.getLocation().getPitch());


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
                    plugin.restoreOfflineData();
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
                plugin.savePluginData();
                break;

            case 3:
                kingdom = args[1];
                target = Bukkit.getPlayer(args[2]);
                if (args[0].equalsIgnoreCase("transfer")) {
                    transferKingdom(player, target, kingdom, args);
                }
                plugin.savePluginData();
                break;

        }
        return true;
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

    private void kingdomInfo(Player player, String kingdom, String chunkID) {
        //TODO: make this display info about the requested kingdom
    }

    private void bannedKingdomsList(Player player) {

        Configurable kc = KingdomsConfig.getInstance().getConfig();

        if (!player.hasPermission("kingdoms.bans.view")) {
            return;
        }
        player.sendMessage(ChatColor.RED + "—————— " + ChatColor.YELLOW + "Banned Kingdoms" + ChatColor.RED + " ——————");

        // Check if no banned names match
        boolean noBannedNames = kc.getNode("bannedNames").getKeys(false)
                .stream()
                .noneMatch(bannedNames::containsValue);

        if (noBannedNames) {
            player.sendMessage(ChatColor.LIGHT_PURPLE + "No Banned Names");
        }
    }

    private void unbanKingdomName(Player player, String kingdom) {
        if (!player.hasPermission("kingdoms.unban")) {
            return;
        }
        if (bannedNames.containsKey(kingdom) && bannedNames.containsValue(kingdom)) {
            bannedNames.put(kingdom, " ");
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
        if ((!bannedNames.containsKey(kingdom)) && !bannedNames.containsValue(kingdom)) {
            bannedNames.put(kingdom, kingdom);
            plugin.savePluginData();
            player.sendMessage(ChatColor.GREEN + "You banned " + ChatColor.WHITE + kingdom + ChatColor.GREEN + " from being created");
        } else {
            player.sendMessage(kingdom + ChatColor.RED + " is already banned");
        }
    }

    private void transferKingdom(Player player, Player target, String kingdom, String[] args) {
        String playerUUID = player.getUniqueId().toString();
        String targetUUID = target.getUniqueId().toString();

        // Check if the player has the necessary permission for admin transfers
        if (!player.hasPermission("kingdoms.admin.transfer")) {
            // Check for the correct number of arguments
            if (args.length != 3) {
                player.sendMessage(ChatColor.GOLD + "Usage: /kingdom transfer NAME <player>");
                return;
            }
            if (!bannedNames.containsValue(kingdom)) {
                player.sendMessage(kingdom + ChatColor.RED + " doesn't exist");
                return;
            }
            // Check if the player is in a kingdom
            if (!kingdoms.containsKey(playerUUID)) {
                player.sendMessage(target.getName() + ChatColor.RED + " is not in a kingdom");
                return;
            }

            if (!kingdoms.get(playerUUID).equals(args[1])) {
                player.sendMessage(ChatColor.RED + "You are not a member of " + ChatColor.WHITE + args[1]);
            }

            // Check if the player is the owner of the specified kingdom
            if (!owner.get(playerUUID).equals(kingdoms.get(playerUUID))) {
                player.sendMessage(ChatColor.RED + "You are not the owner of " + ChatColor.GOLD + kingdoms.get(playerUUID));
                return;
            }

            // Check if the target player is a member of the specified kingdom
            if (!kingdoms.get(targetUUID).equals(kingdom)) {
                player.sendMessage(target.getName() + ChatColor.RED + " is not a member of " + ChatColor.WHITE + kingdom);
                return;
            }

            // Transfer kingdom ownership
            owner.remove(playerUUID);
            owner.put(targetUUID, kingdom);
            player.sendMessage(ChatColor.GREEN + "You transferred " + ChatColor.WHITE + kingdom + ChatColor.GREEN + " to " + ChatColor.WHITE + target.getName());
            target.sendMessage(player.getName() + ChatColor.GREEN + " transferred " + ChatColor.WHITE + kingdom + ChatColor.GREEN + " to you");
            return;
        }

        // Admin transfer logic
        if (args.length != 3) {
            player.sendMessage(ChatColor.GOLD + "Usage: /kingdom transfer NAME <player>");
            return;
        }

        if (!kingdoms.containsValue(kingdom)) {
            player.sendMessage(kingdom + ChatColor.RED + " doesn't exist");
            return;
        }

        // Check if the player is in a kingdom
        if (!kingdoms.containsKey(playerUUID)) {
            player.sendMessage(target.getName() + ChatColor.RED + " is not in a kingdom");
            return;
        }

        // Check if the target player is in the specified kingdom
        if (!kingdoms.get(targetUUID).equals(kingdom)) {
            player.sendMessage(target.getName() + ChatColor.RED + " is not in " + ChatColor.WHITE + kingdom);
            return;
        }

        // Transfer kingdom ownership
        owner.remove(kingdoms.get(kingdom));
        owner.put(targetUUID, kingdom);

        // Inform players about the transfer
        player.sendMessage(ChatColor.GREEN + "You transferred " + ChatColor.WHITE + kingdom + ChatColor.GREEN + " to " + ChatColor.WHITE + target.getName());
        target.sendMessage(player.getName() + ChatColor.GREEN + " transferred " + ChatColor.WHITE + kingdom + ChatColor.GREEN + " to you");
    }

    private void kickPlayerFromKingdom(Player player, Player target, String kingdom, String[] args) {
        String commandSenderUUID = player.getUniqueId().toString();
        String commandTargetUUID = target.getUniqueId().toString();

        // Prevent the player from kicking themselves
        if (target == player) {
            player.sendMessage(ChatColor.RED + "You cannot kick yourself from " + ChatColor.WHITE + kingdom);
            return;
        }

        // Check if the player has the right to kick someone and if the target is in a kingdom
        if ((!admin.containsKey(commandSenderUUID) && !owner.containsKey(commandSenderUUID)) ||
                !admin.containsKey(commandTargetUUID)) {
            player.sendMessage(ChatColor.RED + "You do not have permission to kick players from " + ChatColor.WHITE + kingdom);
            return;
        }

        // Check if both the sender and the target are in the same kingdom
        String senderKingdom = kingdoms.get(commandSenderUUID);
        String targetKingdom = kingdoms.get(commandTargetUUID);

        if (!senderKingdom.equals(targetKingdom)) {
            player.sendMessage(target.getName() + ChatColor.RED + " is not a member of " + ChatColor.WHITE + kingdom);
            return;
        }

        // Ensure the sender is either an admin or owner in their kingdom
        if (!owner.get(commandSenderUUID).equals(senderKingdom) &&
                !admin.get(commandSenderUUID).equals(senderKingdom)) {
            player.sendMessage(ChatColor.RED + "You do not have permission to kick players from " + ChatColor.WHITE + senderKingdom);
            return;
        }

        // Kick the target player from the kingdom
        kingdoms.remove(commandTargetUUID);
        admin.remove(commandTargetUUID);
        member.remove(commandTargetUUID);
        canClaim.remove(commandTargetUUID);
        canUnclaim.remove(commandTargetUUID);

        player.sendMessage(ChatColor.GREEN + "You kicked " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + " from " + ChatColor.WHITE + senderKingdom);
        target.sendMessage(ChatColor.RED + "You were kicked from " + ChatColor.WHITE + senderKingdom);
    }


    private void demotePlayer(Player player, Player target, String[] args) {
        OfflinePlayer offlinePlayer = Bukkit.getPlayer(args[1]);
        String kingdom = kingdoms.get(player.getUniqueId().toString());

        if (offlinePlayer == null) {
            player.sendMessage(args[1] + ChatColor.RED + " has not played on " + ChatColor.YELLOW + "Kingdoms");
            return;
        }

        if (target == null) {
            player.sendMessage(args[1] + ChatColor.RED + " is not online");
            return;
        }

        if (!kingdoms.containsKey(player.getUniqueId().toString())) {
            player.sendMessage(ChatColor.RED + "You are not in a kingdom");
            return;
        }

        if (!kingdoms.containsKey(offlinePlayer.getUniqueId().toString())
                || kingdoms.get(offlinePlayer.getUniqueId().toString()).isEmpty()) {
            player.sendMessage(args[1] + ChatColor.RED + " is not in a kingdom");
            return;
        }

        if (!kingdoms.get(offlinePlayer.getUniqueId().toString()).isEmpty()
                && !kingdoms.get(offlinePlayer.getUniqueId().toString()).equals(kingdoms.get(player.getUniqueId().toString()))) {
            player.sendMessage(args[1] + ChatColor.RED + " is not in " + ChatColor.WHITE + kingdom);
            return;
        }

        if (!owner.get(player.getUniqueId().toString()).equals(kingdoms.get(player.getUniqueId().toString()))) {
            player.sendMessage(ChatColor.RED + "You must be the owner of " + ChatColor.WHITE + kingdom + ChatColor.RED + " to demote members");
            return;
        }

        if (kingdoms.get(target.getUniqueId().toString()).equalsIgnoreCase(kingdom) && member.containsKey(target.getUniqueId().toString())) {
            player.sendMessage(target.getName() + ChatColor.RED + " is already a " + ChatColor.WHITE + "member");
            return;
        }

        admin.remove(target.getUniqueId().toString(), kingdoms.get(player.getUniqueId().toString()));
        member.put(target.getUniqueId().toString(), kingdoms.get(player.getUniqueId().toString()));

        player.sendMessage(ChatColor.GREEN + "You demoted " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + " to " + ChatColor.WHITE + "member");
        target.sendMessage(ChatColor.RED + "You were demoted to " + ChatColor.WHITE + "member" + ChatColor.RED + " in " + ChatColor.WHITE + kingdom);
    }

    private void promotePlayer(Player player, Player target, String[] args) {
        OfflinePlayer offlinePlayer = Bukkit.getPlayer(args[1]);
        String kingdom = kingdoms.get(player.getUniqueId().toString());

        if (offlinePlayer == null) {
            player.sendMessage(args[1] + ChatColor.RED + " has not played on " + ChatColor.YELLOW + "Kingdoms");
            return;
        }

        if (target == null) {
            player.sendMessage(args[1] + ChatColor.RED + " is not online");
            return;
        }

        if (!kingdoms.containsKey(player.getUniqueId().toString())) {
            player.sendMessage(ChatColor.RED + "You are not in a kingdom");
            return;
        }

        if (!kingdoms.containsKey(offlinePlayer.getUniqueId().toString())
                || kingdoms.get(offlinePlayer.getUniqueId().toString()).isEmpty()) {
            player.sendMessage(args[1] + ChatColor.RED + " is not in a kingdom");
            return;
        }

        if (!kingdoms.get(offlinePlayer.getUniqueId().toString()).isEmpty()
                && !kingdoms.get(offlinePlayer.getUniqueId().toString()).equals(kingdoms.get(player.getUniqueId().toString()))) {
            player.sendMessage(args[1] + ChatColor.RED + " is not in " + ChatColor.WHITE + kingdom);
            return;
        }

        if (!owner.get(player.getUniqueId().toString()).equals(kingdoms.get(player.getUniqueId().toString()))) {
            player.sendMessage(ChatColor.RED + "You must be the owner of " + ChatColor.WHITE + kingdom + ChatColor.RED + " to demote members");
            return;
        }

        if (kingdoms.get(target.getUniqueId().toString()).equalsIgnoreCase(kingdom) && admin.containsKey(target.getUniqueId().toString())) {
            player.sendMessage(target.getName() + ChatColor.RED + " is already an " + ChatColor.WHITE + "admin");
            return;
        }

        admin.put(target.getUniqueId().toString(), kingdom);
        member.remove(target.getUniqueId().toString());

        player.sendMessage(ChatColor.GREEN + "You promoted " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + " to " + ChatColor.WHITE + "admin");
        target.sendMessage(ChatColor.GREEN + "You were promoted to " + ChatColor.WHITE + "admin" + ChatColor.GREEN + " in " + ChatColor.WHITE + kingdom);
    }

    private void leaveKingdom(Player player, String kingdom, String[] args) {
        String playerUUID = player.getUniqueId().toString();

        // Check if the player is in a kingdom
        if (!kingdoms.containsKey(playerUUID)) {
            player.sendMessage(ChatColor.RED + "You are not in a kingdom");
            return;
        }

        // Check if the player is a member of the specified kingdom
        String playerKingdom = kingdoms.get(playerUUID);
        if (!playerKingdom.equals(kingdom)) {
            player.sendMessage(ChatColor.RED + "You are not a member of " + ChatColor.WHITE + args[1]);
            return;
        }

        // Check if the player is the owner of the kingdom
        if (owner.containsKey(playerUUID) && owner.get(playerUUID).equals(kingdom)) {
            player.sendMessage(ChatColor.RED + "You must " + ChatColor.GOLD + "/k transfer " + kingdom + ChatColor.RED + " to another member before you can leave or " +
                    ChatColor.GOLD + "/k disband " + kingdom + ChatColor.RED + " if you wish to delete the kingdom completely");
            return;
        }

        // Clear the player's kingdom-related data
        kingdoms.remove(playerUUID);
        admin.remove(playerUUID);
        member.remove(playerUUID);
        canClaim.remove(playerUUID);
        canUnclaim.remove(playerUUID);

        player.sendMessage(ChatColor.GREEN + "You left " + ChatColor.WHITE + kingdom);
    }

    private void joinKingdom(Player player, String kingdom, String[] args) {
        Configurable kc = KingdomsConfig.getInstance().getConfig();

        if (!kingdoms.get(player.getUniqueId().toString()).isEmpty()) {
            MessageManager.playerBad(player, "You are already in a kingdom");
            return;
        }

        if (owner.containsKey(player.getUniqueId().toString()) && !kingdoms.get(player.getUniqueId().toString()).isEmpty()) {
            player.sendMessage(ChatColor.RED + "You are a kingdom owner. If you wish to join a different kingdom, " +
                    "you must disband your current kingdom " + ChatColor.GOLD + "/k disband " + kingdom
                    + ChatColor.RED + " or transfer it " + ChatColor.GOLD + "/k transfer " + kingdom);
            return;
        }
        if (!kingdoms.containsValue(args[1])) {
            player.sendMessage(args[1] + ChatColor.RED + " doesn't exist");
            return;
        }
        if (!player.hasPermission("kingdoms.admin.join")) {

            boolean invitedKingdom = inviteList.get(player.getUniqueId().toString()).equalsIgnoreCase(args[1]);

            if (!inviteList.containsKey(player.getUniqueId().toString())) {
                MessageManager.playerBad(player, "You have not been invited to any kingdoms");
                return;
            }
            if (!invitedKingdom) {
                player.sendMessage(ChatColor.RED + "You have not been invited to " + ChatColor.WHITE + args[1]);
                return;
            }
            int memberCount = 0;
            World kingdoms = Bukkit.getWorld("kingdoms");
            //todo: make this check if the kingdom args[1] exists for offline and online player and increment memberCount if it does
            for (OfflinePlayer offline : Bukkit.getOfflinePlayers()) {
                String offlineKingdom = kc.getNode("kingdoms." + offline.getUniqueId().toString()).toPrimitive().getString();
                if (offlineKingdom.equalsIgnoreCase(args[1])) {
                    memberCount++;
                }
            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                String playerKingdom = kc.getNode("kingdoms." + p.getUniqueId().toString()).toPrimitive().getString();
                if (playerKingdom.equalsIgnoreCase(args[1])) {
                    memberCount++;
                }
            }

            List<OfflinePlayer> allPlayers = new ArrayList<>();
            allPlayers.addAll(Arrays.asList(Bukkit.getOfflinePlayers()));
            allPlayers.addAll(Bukkit.getOnlinePlayers()); // Online players are also added

            for (OfflinePlayer p : allPlayers) {
                if (maxMembers.get(this.kingdoms.get(p.getUniqueId().toString())) > memberCount) {
                    this.kingdoms.put(player.getUniqueId().toString(), args[1]);
                } else {
                    player.sendMessage(args[1] + ChatColor.RED + " is at max capacity");
                }
            }

            return;
        }

        kingdom = kc.getNode("kingdoms").getNode(player.getUniqueId().toString()).toPrimitive().getString();
        if (kingdom != null && kingdom.equalsIgnoreCase(args[1])) {
            player.sendMessage(ChatColor.RED + "You are already a member of " + ChatColor.WHITE + args[1]);
            return;
        }

        kingdoms.put(player.getUniqueId().toString(), args[1]);
        member.put(player.getUniqueId().toString(), args[1]);

        player.sendMessage(ChatColor.GREEN + "You joined " + ChatColor.WHITE + args[1]);
    }

    private void uninvitePlayerFromKingdom(Player player, Player target, String kingdom, String[] args) {
        OfflinePlayer offlinePlayer = Bukkit.getPlayer(args[1]);

        if (!kingdoms.containsKey(player.getUniqueId().toString())) {
            player.sendMessage(ChatColor.RED + "You are not in a kingdom");
            return;
        }

        if (!kingdoms.containsKey(offlinePlayer.getUniqueId().toString())) {
            player.sendMessage(args[1] + ChatColor.RED + " has not played " + ChatColor.YELLOW + "Kingdoms");
        }

        if (!inviteList.get(target.getUniqueId().toString()).equals(kingdoms.get(player.getUniqueId().toString()))) {
            player.sendMessage(target.getName() + ChatColor.RED + " hasn't been invited to " + ChatColor.WHITE + kingdoms.get(player.getUniqueId().toString()));
            return;
        }

        if (!owner.containsValue(kingdoms.get(player.getUniqueId().toString()))
                || !admin.containsValue(kingdoms.get(player.getUniqueId().toString()))) {
            player.sendMessage(ChatColor.RED + "You are not an owner or admin of " + ChatColor.WHITE + kingdoms.get(player.getUniqueId().toString()));
            return;
        }

        inviteList.remove(target.getUniqueId().toString(), kingdoms.get(player.getUniqueId().toString()));

        player.sendMessage(ChatColor.GREEN + "You uninvited " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + " from " + ChatColor.WHITE + kingdoms.get(player.getUniqueId().toString()));
        target.sendMessage(ChatColor.RED + "You were uninvited from " + ChatColor.WHITE + kingdoms.get(player.getUniqueId().toString()));

    }

    private void disbandKingdom(Player player, String kingdom, String[] args, String chunkID) {
        // Check if the player has the necessary permission or if they are trying to disband their own kingdom
        if (!player.hasPermission("kingdoms.disband.admin")) {
            String playerKingdom = kingdoms.get(player.getUniqueId().toString());
            if (playerKingdom == null || playerKingdom.isEmpty()) {
                player.sendMessage(ChatColor.RED + "You are not in a kingdom");
                return;
            }

            if (!kingdom.equalsIgnoreCase(playerKingdom)) {
                player.sendMessage(ChatColor.RED + "You do not have permission to disband " + ChatColor.WHITE + kingdom);
                return;
            }
        }

        // Check if the specified kingdom exists
        if (kingdom == null || !kingdoms.containsValue(kingdom)) {
            player.sendMessage(kingdom + ChatColor.RED + " doesn't exist");
            return;
        }

        // Disband the kingdom and clean up associated data
        boolean kingdomFound = false;
        for (Map.Entry<String, String> entry : kingdoms.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(kingdom)) {
                String playerObj = entry.getKey();

                // Set all associated entries to an empty string
                owner.put(playerObj, "");
                admin.put(playerObj, "");
                member.put(playerObj, "");
                canClaim.put(playerObj, "");
                canUnclaim.put(playerObj, "");
                kingdoms.put(playerObj, "");
                inviteList.put(playerObj, "");

                // Update claimed chunks associated with the kingdom
                for (Map.Entry<String, String> chunk : claimedChunks.entrySet()) {
                    if (chunk.getValue().equalsIgnoreCase(kingdom)) {
                        claimedChunks.put(chunk.getKey(), ""); // Set the value to an empty string
                    }
                }

                kingdomFound = true;
                break; // Exit the loop once the kingdom is found and disbanded
            }
        }

        // Notify the player about the result
        if (kingdomFound) {
            player.sendMessage(ChatColor.WHITE + kingdom + ChatColor.GREEN + " disbanded");
        } else {
            player.sendMessage(kingdom + ChatColor.RED + " doesn't exist");
        }
    }

    private void createKingdom(Player player, String kingdom) {
        if (!kingdoms.get(player.getUniqueId().toString()).isEmpty()) {
            player.sendMessage(ChatColor.RED + "You are already in a kingdom");
            return;
        }
        if (kingdoms.containsValue(kingdom)) {
            player.sendMessage(kingdom + ChatColor.RED + " already exists");
            return;
        }
        if (bannedNames.containsValue(kingdom)) {
            player.sendMessage(ChatColor.RED + "You cannot create a kingdom called " + ChatColor.WHITE + kingdom);
            return;
        }

        kingdoms.put(player.getUniqueId().toString(), kingdom);
        owner.put(player.getUniqueId().toString(), kingdom);
        admin.put(player.getUniqueId().toString(), kingdom);
        member.put(player.getUniqueId().toString(), kingdom);
        canClaim.put(player.getUniqueId().toString(), kingdom);
        canUnclaim.put(player.getUniqueId().toString(), kingdom);
        claimPrice.put(kingdom, 10_000);
        memberPrice.put(kingdom, 10_000);
        maxClaims.put(kingdom, 10);
        maxMembers.put(kingdom, 6);
        player.sendMessage(kingdom + ChatColor.GREEN + " created");
    }

    private void setSpawn(Player player, String kingdom, Location spawn) {
        String playerUUID = player.getUniqueId().toString();

        // Ensure the player is in the "kingdoms" world
        if (!player.getWorld().getName().equalsIgnoreCase("kingdoms")) {
            player.sendMessage(ChatColor.RED + "You must be in the " + ChatColor.GOLD + "Kingdoms " + ChatColor.RED + "world to execute this command");
            return;
        }

        // Check if the player is in a kingdom
        if (!kingdoms.containsKey(playerUUID) || kingdoms.get(playerUUID).isEmpty()) {
            MessageManager.playerBad(player, "You are not in a kingdom");
            return;
        }

        // Check if the player is a member of the kingdom
        boolean isMember = member.get(playerUUID) != null && member.get(playerUUID).equals(kingdom);
        boolean isAdmin = admin.get(playerUUID) != null && admin.get(playerUUID).equals(kingdom);

        if (isMember && !isAdmin) {
            player.sendMessage(ChatColor.RED + "You do not have permission to set " + ChatColor.WHITE + kingdom + ChatColor.RED + "'s spawn");
            return;
        }

        // Set the spawn for the kingdom
        kingdomSpawn.put(kingdom, spawn);
        player.sendMessage(ChatColor.GREEN + "You set " + ChatColor.WHITE + kingdom + ChatColor.GREEN + "'s spawn");
    }


    private void teleportToSpawn(Player player, String kingdom) {
        String playerUUID = player.getUniqueId().toString();

        // Ensure the player is in the "kingdoms" world
        if (!player.getWorld().getName().equalsIgnoreCase("kingdoms")) {
            player.sendMessage(ChatColor.RED + "You must be in the " + ChatColor.GOLD + "Kingdoms " + ChatColor.RED + "world to execute this command");
            return;
        }

        // Check if the player is in a kingdom
        if (!kingdoms.containsKey(playerUUID) || kingdoms.get(playerUUID).isEmpty()) {
            player.sendMessage(ChatColor.RED + "You are not in a kingdom");
            return;
        }

        // Check if the kingdom's spawn is set
        Location spawnLocation = kingdomSpawn.get(kingdom);
        if (spawnLocation == null || !spawnLocation.getWorld().getName().equalsIgnoreCase("kingdoms")) {
            player.sendMessage(kingdom + ChatColor.RED + "'s spawn has not been set");
            return;
        }

        // Teleport the player to the kingdom's spawn location
        player.teleport(spawnLocation);
        player.sendMessage(ChatColor.GREEN + "Teleported to " + ChatColor.WHITE + kingdom + ChatColor.GREEN + "'s spawn");
    }


    private void upgradeKingdom(Player player, String kingdom) {

        if (!kingdoms.containsKey(player.getUniqueId().toString())) {
            MessageManager.playerBad(player, "You are not in a kingdom");
            return;
        }
        try {
            String playerChunk = player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ();
            if (claimedChunks.get(playerChunk).equals(kingdoms.get(player.getUniqueId().toString()))) {

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
                for (String chunkID : claimedChunks.keySet()) {
                    if (claimedChunks.get(chunkID).equals(kingdoms.get(player.getUniqueId().toString()))) {
                        // This chunk is claimed by the player's kingdom
                        // You can increment the claim count and update the scoreboard here
                        int claimCount = 0;
                        for (String otherChunkID : claimedChunks.keySet()) {
                            if (claimedChunks.get(otherChunkID).equals(kingdoms.get(player.getUniqueId().toString()))) {
                                claimCount++;
                            }
                        }
                        claimMeta.setDisplayName(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Increase Claim Limit");
                        ArrayList<String> claimsLore = new ArrayList<>();
                        claimsLore.add(" ");
                        claimsLore.add(ChatColor.GREEN + "Cost: " + ChatColor.GOLD + claimPrice.get(kingdoms.get(player.getUniqueId().toString())) + ChatColor.GREEN + " coins");
                        claimsLore.add(ChatColor.LIGHT_PURPLE + "Current Max: " + ChatColor.WHITE + maxClaims.get(kingdoms.get(player.getUniqueId().toString())));
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
                    if (claimedChunks.get(player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ())
                            .equalsIgnoreCase(kingdoms.get(player.getUniqueId().toString()))) {
                        int memberCount = 0;

                        if (kingdoms.get(p.getUniqueId().toString()).equalsIgnoreCase
                                (claimedChunks.get(player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ()))) {
                            memberCount++;
                        }
                        ArrayList<String> memberLore = new ArrayList<>();
                        memberLore.add(" ");
                        memberLore.add(ChatColor.GREEN + "Cost: " + ChatColor.GOLD + memberPrice.get(kingdoms.get(player.getUniqueId().toString())) + ChatColor.GREEN + " coins");
                        memberLore.add(ChatColor.LIGHT_PURPLE + "Current Max: " + ChatColor.WHITE + maxMembers.get(kingdoms.get(player.getUniqueId().toString())));
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
            }
        } catch (NullPointerException e) {
            if (claimedChunks.get(player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ()).equals(kingdoms.get(player.getUniqueId().toString()))) {
                player.sendMessage(ChatColor.RED + "You must be in a " + ChatColor.WHITE + kingdom + ChatColor.RED + " claim to execute this command");
            } else {
                MessageManager.playerBad(player, "You must be in a claim to execute this command");
            }
        }
    }

    //TODO: Figure out why claimChunk() and unclaimChunk() don't work
    private void claimChunk(Player player, String kingdom, String chunkID) {
        String playerUUID = player.getUniqueId().toString();
        String playerKingdom = kingdoms.get(playerUUID);

        // Check if the player is in a kingdom
        if ((playerKingdom == null || playerKingdom.isEmpty())) {
            player.sendMessage(ChatColor.RED + "You are not in a kingdom");
            return;
        }

        // Check if the player is a member of the kingdom
        boolean isMember = member.get(playerUUID) != null && member.get(playerUUID).equals(kingdom);
        boolean isAdmin = admin.get(playerUUID) != null && admin.get(playerUUID).equals(kingdom);

        // Check if the player has permission to claim chunks
        if (isMember && !isAdmin) {
            player.sendMessage(ChatColor.RED + "You do not have permission to claim chunks for " + ChatColor.WHITE + kingdom);
            return;
        }

        // Check if the chunk is already claimed
        if (claimedChunks.containsKey(chunkID) && !claimedChunks.get(chunkID).isEmpty()) {
            String ownerKingdom = claimedChunks.get(chunkID);
            if (ownerKingdom.equals(kingdom)) {
                player.sendMessage(ChatColor.RED + "Chunk is already claimed by your kingdom");
            } else if (!ownerKingdom.isEmpty()) {
                player.sendMessage(ChatColor.RED + "Chunk is claimed by " + ChatColor.WHITE + ownerKingdom);
            }
            return;
        }

        // Check the number of chunks claimed by the player's kingdom
        long claimCount = claimedChunks.values().stream()
                .filter(k -> k.equals(kingdom))
                .count();

        int maxClaims = this.maxClaims.getOrDefault(kingdom, 0);
        if (claimCount < maxClaims) {
            claims.put(kingdom, chunkID);
            claimedChunks.put(chunkID, kingdom);
            player.sendMessage(ChatColor.GREEN + "Chunk claimed");
        } else {
            player.sendMessage(kingdom + ChatColor.RED + " has claimed " +
                    ChatColor.YELLOW + claimCount + "/" + maxClaims +
                    ChatColor.RED + " chunks. " + ChatColor.GOLD + "/k upgrade " + ChatColor.RED + "to increase this amount");
        }
    }

    private void unclaimChunk(Player player, String kingdom, String chunkID) {
        String playerUUID = player.getUniqueId().toString();
        String playerKingdom = kingdoms.get(playerUUID);

        // Check if the player is in a kingdom
        if ((playerKingdom == null || playerKingdom.isEmpty())) {
            player.sendMessage(ChatColor.RED + "You are not in a kingdom");
            return;
        }

        // Check if the player is a member of the kingdom
        boolean isMember = member.get(playerUUID) != null && member.get(playerUUID).equals(kingdom);
        boolean isAdmin = admin.get(playerUUID) != null && admin.get(playerUUID).equals(kingdom);

        // Check if the player has permission to unclaim chunks
        if (isMember && !isAdmin) {
            player.sendMessage(ChatColor.RED + "You do not have permission to unclaim chunks for " + ChatColor.WHITE + playerKingdom);
            return;
        }
        // Check if the chunk is claimed by the player's kingdom
        if (!claimedChunks.containsKey(chunkID)) {
            player.sendMessage(ChatColor.RED + "This chunk is not claimed");
            return;
        }

        if (!claimedChunks.get(chunkID).equals(kingdom)) {
            player.sendMessage("This chunk is not claimed by " + kingdom);
        }

        claims.remove(kingdom, chunkID);
        claimedChunks.remove(chunkID);
        player.sendMessage(ChatColor.GREEN + "Chunk unclaimed");
    }

    private void invitePlayerToKingdom(Player player, Player target, String kingdom, String[] args) {
        OfflinePlayer offlinePlayer = Bukkit.getPlayer(args[1]);

        if (!kingdoms.containsKey(player.getUniqueId().toString())) {
            player.sendMessage(ChatColor.RED + "You are not in a kingdom");
            return;
        }

        if (!kingdoms.containsKey(offlinePlayer.getUniqueId().toString())) {
            player.sendMessage(args[1] + ChatColor.RED + " has not played " + ChatColor.YELLOW + "Kingdoms");
        }

        if (inviteList.get(target.getUniqueId().toString()).equals(kingdoms.get(player.getUniqueId().toString()))) {
            player.sendMessage(target.getName() + ChatColor.RED + " has already been invited to " + ChatColor.WHITE + kingdoms.get(player.getUniqueId().toString()));
            return;
        }

        if (!owner.containsValue(kingdoms.get(player.getUniqueId().toString()))
                || !admin.containsValue(kingdoms.get(player.getUniqueId().toString()))) {
            player.sendMessage(ChatColor.RED + "You are not an owner or admin of " + ChatColor.WHITE + kingdoms.get(player.getUniqueId().toString()));
            return;
        }

        // Add the invitation to the inviteList
        inviteList.put(target.getUniqueId().toString(), kingdom);

        player.sendMessage(ChatColor.GREEN + "You invited " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + " to " + ChatColor.WHITE + kingdom);
        target.sendMessage(ChatColor.GREEN + "You were invited to " + ChatColor.WHITE + kingdom + ChatColor.GREEN + " by " + ChatColor.WHITE + player.getName());
    }
}