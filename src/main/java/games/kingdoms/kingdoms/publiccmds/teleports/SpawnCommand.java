package games.kingdoms.kingdoms.publiccmds.teleports;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import games.kingdoms.kingdoms.admin.punishCMD.PunishCommand;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {

    final Kingdoms plugin;

    public SpawnCommand(Kingdoms plugin, KingdomsCommandListener kcl) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            MessageManager.consoleBad("You must be a player to execute this command.");
            return true;
        }
        if (args.length == 0) {
            PunishCommand pu = new PunishCommand(plugin);
            TextComponent kingdoms = new TextComponent("Kingdoms");
            kingdoms.setColor(ChatColor.AQUA);
            kingdoms.setBold(true);
            kingdoms.setUnderlined(true);
            kingdoms.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/spawn kingdoms"));
            TextComponent warzone = new TextComponent("Warzone");
            warzone.setColor(ChatColor.RED);
            warzone.setBold(true);
            warzone.setUnderlined(true);
            warzone.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/spawn warzone"));
            TextComponent openArrow = new TextComponent(">> ");
            openArrow.setColor(ChatColor.GRAY);
            openArrow.setBold(true);
            TextComponent closeArrow = new TextComponent(" <<");
            closeArrow.setColor(ChatColor.GRAY);
            closeArrow.setBold(true);

            player.sendMessage(org.bukkit.ChatColor.GOLD + pu.sep);
            player.sendMessage(org.bukkit.ChatColor.DARK_GREEN.toString() + org.bukkit.ChatColor.BOLD + "Spawn Teleport");
            player.sendMessage(org.bukkit.ChatColor.GREEN + "Which spawn would you like to go to?");
            player.sendMessage(" ");
            player.sendMessage(pu.centerMessage(openArrow.toString() + warzone + closeArrow + "     " + openArrow + kingdoms + closeArrow));
            player.sendMessage(org.bukkit.ChatColor.GOLD + pu.sep);
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("warzone") || args[0].equalsIgnoreCase("wz") || args[0].equalsIgnoreCase("w")) {
                WarzoneCommandListener warzoneCommandListener = new WarzoneCommandListener(plugin, player);
                //TODO: Make this cancel the event if the player moves
                warzoneCommandListener.start();
            }
            if (args[0].equalsIgnoreCase("kingdoms") || args[0].equalsIgnoreCase("k")) {
                KingdomsCommandListener kingdomsCommandListener = new KingdomsCommandListener(plugin, player);
                //TODO: Make this cancel the event if the player moves
                kingdomsCommandListener.start();
            }
        }
        return true;

    }
}