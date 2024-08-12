package games.kingdoms.kingdoms.rankedcmds.fly;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Fly implements CommandExecutor {

    private final ArrayList<Player> list_of_flying_people = new ArrayList<>();
    public final Kingdoms plugin;

    public Fly(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (!(sender instanceof Player)) {
            MessageManager.consoleBad("You must be a player to execute this command.");
            return true;
        }
        Player player = (Player) sender;
        if (player.hasPermission("kingdoms.fly")) {
            if (list_of_flying_people.contains(player)) {
                list_of_flying_people.remove(player);
                player.sendMessage(ChatColor.GRAY + "Flight " + ChatColor.RED + "Disabled");
                player.setAllowFlight(false);
            } else if (!list_of_flying_people.contains(player)) {
                list_of_flying_people.add(player);
                player.sendMessage(ChatColor.GRAY + "Flight " + ChatColor.GREEN + "Enabled");
                player.setAllowFlight(true);
            }
        } else {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
        }
        return true;
    }
}