package games.kingdoms.kingdoms.publiccmds.teleports;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarzoneCMD implements CommandExecutor {

    private Kingdoms plugin;

    public WarzoneCMD(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.consoleBad("You must be a player to execute this command.");
            return true;
        }
        Player player = (Player) sender;
        WarzoneCommandListener warzoneCommandListener = new WarzoneCommandListener(plugin, player);
        warzoneCommandListener.start();
        return true;
    }
}
