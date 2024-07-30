package games.kingdoms.kingdoms.admin.reload;

import games.kingdoms.kingdoms.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StaffReload implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            MessageManager.consoleBad("You must be a player to execute this command.");
        } else {

            Player player = (Player) sender;

            if (player.hasPermission("kingdoms.staff.reload")) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage("§6====================================\n\n§fKingdoms §6was reloaded. If you encounter \nany errors, log out and then back in.\n\n====================================");
                }
            } else {
                MessageManager.playerBad(player, "You do not have permission to execute this command");
            }
        }

        return true;
    }
}
