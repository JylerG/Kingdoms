package games.kingdoms.kingdoms.admin.npcs;

import games.kingdoms.kingdoms.MessageManager;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CreateNPCCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            MessageManager.consoleBad("You must be a player to execute this command.");
            return true;
        }

        if (!player.hasPermission("kingdoms.npc.spawn")) {
            return true;
        }

        if (!label.equalsIgnoreCase("npcs")) {
            player.sendMessage(ChatColor.GOLD + "Usage: /npcs " + ChatColor.WHITE + "NPC");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(ChatColor.GOLD + "Usage: /npcs " + ChatColor.WHITE + "NPC");
            return true;
        }

        if (args[0].equalsIgnoreCase("guide")) {
            NPC guide = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GOLD.toString() + ChatColor.BOLD + "Guide");
            guide.spawn(player.getLocation());
            MessageManager.playerGood(player, "You spawned the guide");
        }
        if (args[0].equalsIgnoreCase("brother")) {
            NPC challenges = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GOLD.toString() + ChatColor.BOLD + "Challenge Rewards");
            challenges.spawn(player.getLocation());
            MessageManager.playerGood(player, "You spawned the guide's brother");
        }
        if (args[0].equalsIgnoreCase("nature")) {
            NPC nature = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Nature");
            nature.spawn(player.getLocation());
            MessageManager.playerGood(player, "You spawned a nature merchant");
        }
        if (args[0].equalsIgnoreCase("postbox")) {
            NPC postbox = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.BOLD + "Post" + ChatColor.BLUE + ChatColor.BOLD + "Box");
            postbox.spawn(player.getLocation());
            MessageManager.playerGood(player, "You spawned the postbox NPC");
        }
        if (args[0].equalsIgnoreCase("merchant")) {
            NPC merchant = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GOLD.toString() + ChatColor.BOLD + "Merchant");
            merchant.spawn(player.getLocation());
            MessageManager.playerGood(player, "You spawned a merchant");
        }
        if (args[0].equalsIgnoreCase("glass")) {
            NPC glass = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GOLD.toString() + ChatColor.BOLD + "Glass");
            glass.spawn(player.getLocation());
            MessageManager.playerGood(player, "You spawned a glass seller");
        }
        if (args[0].equalsIgnoreCase("schematics")) {
            NPC schematics = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GOLD.toString() + ChatColor.BOLD + "Blueprints");
            schematics.spawn(player.getLocation());
            MessageManager.playerGood(player, "You spawned a schematic merchant");
        }
        if (args[0].equalsIgnoreCase("forge")) {
            NPC forge = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, ChatColor.GOLD.toString() + ChatColor.BOLD + "The Forsaken");
            forge.spawn(player.getLocation());
            MessageManager.playerGood(player, "You spawned a forsaken forge NPC");
        }
        return true;
    }
}
