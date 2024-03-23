package games.kingdoms.kingdoms.publiccmds.kingdoms;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import games.kingdoms.kingdoms.publiccmds.kingdoms.configs.KingdomsConfig;
import games.kingdoms.kingdoms.publiccmds.kingdoms.configs.MoneyConfig;
import games.kingdoms.kingdoms.publiccmds.kingdoms.configs.StaffConfig;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class KingdomsCommands implements CommandExecutor {

    private Kingdoms plugin = Kingdoms.getPlugin(Kingdoms.class);
    private KingdomsConfig kingdomsConfig = new KingdomsConfig(plugin);
    private StaffConfig staffConfig = new StaffConfig(plugin);
    private MoneyConfig moneyConfig = new MoneyConfig(plugin);

    public KingdomsCommands(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.consoleBad("You must be a player to execute this command.");
            return true;
        }
        Player player = (Player) sender;
//        System.out.println("Args length: " + args.length);
//        getLogger().warning("Args: " + Arrays.toString(args));
//        Thread.dumpStack();
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
                Location spawn = new Location(world, player.getLocation().getX(), player.getLocation().getBlockY(), player.getLocation().getZ());
                if (args[0].equalsIgnoreCase("setspawn")) {
                    setSpawn(player, kingdom, spawn);
                }
                if (args[0].equalsIgnoreCase("spawn")) {
                    teleportToSpawn(player, kingdom);
                }
                if (args[0].equalsIgnoreCase("claim")) {
                    claimChunk(player, kingdom, chunkID);
                }

                if (args[0].equalsIgnoreCase("unclaim")) {
                    unclaimChunk(player, kingdom, chunkID);
                }
                if (args[0].equalsIgnoreCase("info")) {
                    kingdomInfo(player, kingdom, chunkID);
                }
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
                break;

            case 3:
                kingdom = args[1];
                target = Bukkit.getPlayer(args[2]);
                if (args[0].equalsIgnoreCase("transfer")) {
                    transferKingdom(player, target, kingdom, args);
                }
                break;

        }
        return true;
    }

    private void kingdomInfo(Player player, String kingdom, String chunkID) {

        System.out.println("Command sender: " + player.getUniqueId().toString());
        System.out.println("Spawn Var Check: " + kingdomsConfig.getConfig().get("spawns"));
        System.out.println("Get Spawn Location Check: " + kingdomsConfig.getConfig().get("spawns." + plugin.getKingdoms().get(player.getUniqueId().toString())));
        System.out.println("Get Player Kingdom: " + kingdomsConfig.getConfig().get("kingdoms." + player.getUniqueId().toString()));
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
            plugin.getOwner().remove(playerUUID);
            plugin.getOwner().put(targetUUID, kingdom);
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
    }

    private void kickPlayerFromKingdom(Player player, Player target, String kingdom, String[] args) {
        String commandSender = player.getUniqueId().toString();
        String commandTarget = target.getUniqueId().toString();
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
    }

    private void demotePlayer(Player player, Player target, String[] args) {

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
    }

    private void promotePlayer(Player player, Player target, String[] args) {
        String kingdom = plugin.getKingdoms().get(player.getUniqueId().toString());

        if (target == null) {
            player.sendMessage(args[1] + ChatColor.RED + " is not online");
            return;
        }

        if (!plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
            player.sendMessage(ChatColor.RED + "You are not in a kingdom");
            return;
        }

        if (!plugin.getOwner().get(player.getUniqueId().toString()).equals(kingdom)) {
            player.sendMessage(ChatColor.RED + "You must be the owner of " + ChatColor.WHITE + kingdom + ChatColor.RED + " to promote members");
            return;
        }

        if (plugin.getAdmin().containsKey(target.getUniqueId().toString()) && plugin.getAdmin().get(target.getUniqueId().toString()).equalsIgnoreCase(kingdom)) {
            player.sendMessage(target.getName() + ChatColor.RED + " is already an " + ChatColor.WHITE + "admin");
            return;
        }

        plugin.getAdmin().put(target.getUniqueId().toString(), kingdom);
        plugin.getMember().remove(target.getUniqueId().toString());

        player.sendMessage(ChatColor.GREEN + "You promoted " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + " to " + ChatColor.WHITE + "admin");
        target.sendMessage(ChatColor.GREEN + "You were promoted to " + ChatColor.WHITE + "admin" + ChatColor.GREEN + " in " + ChatColor.WHITE + kingdom);
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

        plugin.getKingdoms().remove(playerUUID, args[1]);
        plugin.getAdmin().remove(playerUUID, args[1]);
        plugin.getMember().remove(playerUUID, args[1]);
        plugin.getCanClaim().remove(playerUUID, args[1]);
        plugin.getCanUnclaim().remove(playerUUID, args[1]);

        player.sendMessage(ChatColor.GREEN + "You left " + ChatColor.WHITE + args[1]);
    }

    private void joinKingdom(Player player, String kingdom, String[] args) {

        try {
            String invitedKingdom = plugin.getInviteList().get(player.getUniqueId().toString());

            if (invitedKingdom != null && invitedKingdom.equalsIgnoreCase(args[1])) {
                plugin.getInviteList().remove(player.getUniqueId().toString());
                plugin.getKingdoms().put(player.getUniqueId().toString(), args[1]);
                plugin.getMember().put(player.getUniqueId().toString(), args[1]);


                player.sendMessage(ChatColor.GREEN + "You joined " + ChatColor.WHITE + args[1]);
            } else {
                if (!plugin.getKingdoms().containsKey(player.getUniqueId().toString()) && !player.hasPermission("kingdoms.admin.join")) {
                    player.sendMessage(ChatColor.RED + "You have not been invited to " + ChatColor.WHITE + args[1]);
                }
            }

            if (!player.hasPermission("kingdoms.admin.join")) return;

            String currentKingdom = plugin.getKingdoms().get(player.getUniqueId().toString());

            if (currentKingdom != null && currentKingdom.equalsIgnoreCase(args[1])) {
                player.sendMessage(ChatColor.RED + "You are already a member of " + ChatColor.WHITE + args[1]);
                return;
            }

            if (plugin.getOwner().containsKey(player.getUniqueId().toString())) {
                player.sendMessage(ChatColor.RED + "You are a kingdom owner. If you wish to join a different kingdom, " +
                        "you must disband your current kingdom " + ChatColor.GOLD + "/k disband " + plugin.getKingdoms().get(player.getUniqueId().toString())
                        + ChatColor.RED + " or transfer it " + ChatColor.GOLD + "/k transfer " + plugin.getKingdoms().get(player.getUniqueId().toString()));
                return;
            }

            plugin.getKingdoms().put(player.getUniqueId().toString(), args[1]);
            plugin.getMember().put(player.getUniqueId().toString(), args[1]);

            player.sendMessage(ChatColor.GREEN + "You joined " + ChatColor.WHITE + args[1]);
        } catch (NullPointerException e) {
            player.sendMessage(args[1] + ChatColor.RED + " doesn't exist");
        }

    }

    private void uninvitePlayerFromKingdom(Player player, Player target, String kingdom, String[] args) {

        if (!plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
            player.sendMessage(ChatColor.RED + "You are not in a kingdom");
        }

        if (!plugin.getInviteList().get(target.getUniqueId().toString()).equals(plugin.getKingdoms().get(player.getUniqueId().toString()))) {
            player.sendMessage(target.getName() + ChatColor.RED + " hasn't been invited to " + ChatColor.WHITE + plugin.getKingdoms().get(player.getUniqueId().toString()));
            return;
        }

        if (!plugin.getOwner().containsValue(plugin.getKingdoms().get(player.getUniqueId().toString())) || !plugin.getAdmin().containsValue(plugin.getKingdoms().get(player.getUniqueId().toString()))) {
            player.sendMessage(ChatColor.RED + "You are not an owner or admin of " + ChatColor.WHITE + plugin.getKingdoms().get(player.getUniqueId().toString()));
            return;
        }

        plugin.getInviteList().remove(target.getUniqueId().toString(), plugin.getKingdoms().get(player.getUniqueId().toString()));

        player.sendMessage(ChatColor.GREEN + "You uninvited " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + " from " + ChatColor.WHITE + plugin.getKingdoms().get(player.getUniqueId().toString()));
        target.sendMessage(ChatColor.RED + "You were uninvited from " + ChatColor.WHITE + plugin.getKingdoms().get(player.getUniqueId().toString()));

    }

    //TODO: Figure out how to make this actually disband the kingdom and remove all data for it
    private void disbandKingdom(Player player, String kingdom, String[] args, String chunkID) {
        if (!player.hasPermission("kingdoms.disband.admin")) {
            if (!plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
                player.sendMessage(ChatColor.RED + "You are not in a kingdom");
                return;
            }
            if (!plugin.getKingdoms().get(player.getUniqueId().toString()).equals(args[1])) {
                player.sendMessage(ChatColor.RED + "You are not a member of " + ChatColor.WHITE + kingdom);
                return;
            }
            if (!plugin.getOwner().get(player.getUniqueId().toString()).equals(args[1])) {
                player.sendMessage(ChatColor.RED + "You are not the owner of " + ChatColor.WHITE + kingdom);
                return;
            }
        }
        if (plugin.getKingdoms().containsValue(kingdom)) {
            for (Map.Entry<String, String> kingdoms : plugin.getKingdoms().entrySet()) {
                if (kingdoms.getValue().equals(kingdom)) {
                    String playerObj = kingdoms.getKey();
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (playerObj.equals(p.getUniqueId().toString())) {
                            plugin.getKingdoms().remove(playerObj, kingdom);
                            plugin.getOwner().remove(playerObj, kingdom);
                            plugin.getAdmin().remove(playerObj, kingdom);
                            plugin.getMember().remove(playerObj, kingdom);
                            plugin.getCanClaim().remove(playerObj, kingdom);
                            plugin.getCanUnclaim().remove(playerObj, kingdom);
                            plugin.getKingdomSpawn().remove(kingdom);
                            for (Map.Entry<String, String> chunk : plugin.getClaimedChunks().entrySet()) {
                                plugin.getClaimedChunks().remove(kingdom, chunk.getValue());
                            }
                            for (Map.Entry<String, String> claim : plugin.getClaims().entrySet()) {
                                plugin.getClaims().remove(kingdom, claim.getValue());
                            }
                        }
                    }
                }
            }
            player.sendMessage(kingdom + ChatColor.GREEN + " disbanded");
        } else {
            player.sendMessage(kingdom + ChatColor.RED + " doesn't exist");
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
        plugin.getKingdoms().put(player.getUniqueId().toString(), kingdom);
        plugin.getOwner().put(player.getUniqueId().toString(), kingdom);
        plugin.getAdmin().put(player.getUniqueId().toString(), kingdom);
        plugin.getMember().put(player.getUniqueId().toString(), kingdom);
        plugin.getCanClaim().put(player.getUniqueId().toString(), kingdom);
        plugin.getCanUnclaim().put(player.getUniqueId().toString(), kingdom);
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
        player.sendMessage(ChatColor.YELLOW + "/kingdom setspawn");
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

    private void setSpawn(Player player, String kingdom, Location spawn) {
        if (plugin.getAdmin().get(player.getUniqueId().toString()).equals(kingdom) || plugin.getOwner().get(player.getUniqueId().toString()).equals(kingdom)) {
            plugin.getKingdomSpawn().put(kingdom, spawn);
            player.sendMessage(ChatColor.GREEN + "You set " + ChatColor.WHITE + kingdom + ChatColor.GREEN + "'s spawn");
        } else {
            player.sendMessage(ChatColor.RED + "You do not have permission to set " + ChatColor.WHITE + kingdom + ChatColor.RED + "'s spawn");
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

        plugin.getClaims().put(kingdom, chunkID);
        plugin.getClaimedChunks().put(chunkID, kingdom);
        player.sendMessage(ChatColor.GREEN + "Chunk claimed");
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
            player.sendMessage(ChatColor.RED + "The target must be online for you to invite them.");
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
            player.sendMessage("You are not an owner or admin of " + ChatColor.WHITE + kingdom);
            return;
        }

        if (plugin.getInviteList().containsKey(target.getUniqueId().toString()) && plugin.getInviteList().get(target.getUniqueId().toString()).equals(kingdom)) {
            player.sendMessage(target.getName() + ChatColor.RED + " has already been invited to " + ChatColor.WHITE + kingdom);
            return;
        }

        // Add the invitation to the inviteList
        plugin.getInviteList().put(target.getUniqueId().toString(), kingdom);

        player.sendMessage(ChatColor.GREEN + "You invited " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + " to " + ChatColor.WHITE + kingdom);
        target.sendMessage(ChatColor.GREEN + "You were invited to " + ChatColor.WHITE + kingdom + ChatColor.GREEN + " by " + ChatColor.WHITE + player.getName());
    }
}