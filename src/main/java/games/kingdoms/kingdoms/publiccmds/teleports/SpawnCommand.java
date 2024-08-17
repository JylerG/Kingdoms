package games.kingdoms.kingdoms.publiccmds.teleports;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
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
