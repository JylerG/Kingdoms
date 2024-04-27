package games.kingdoms.kingdoms.admin.gamemodes;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Survival implements CommandExecutor {

    private final Kingdoms plugin;

    public Survival(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("kingdoms.set.gamemode")) {
                if (args.length == 0) {
                    if (!player.getGameMode().equals(GameMode.SURVIVAL)) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(ChatColor.GRAY + "Gamemode set to " + ChatColor.LIGHT_PURPLE + "Survival");
                    } else {
                        player.sendMessage(ChatColor.RED + "You are already in gamemode " + ChatColor.LIGHT_PURPLE + "Survival");
                    }
                }
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (!target.getGameMode().equals(GameMode.SURVIVAL)) {
                            target.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(ChatColor.GRAY + "You set " + ChatColor.LIGHT_PURPLE + target.getName() + ChatColor.GRAY + "'s gamemode to " + ChatColor.LIGHT_PURPLE + "Survival");
                            target.sendMessage(ChatColor.GRAY + "Your gamemode was set to " + ChatColor.LIGHT_PURPLE + "Survival ");
                        } else {
                            player.sendMessage(ChatColor.LIGHT_PURPLE + target.getName() + ChatColor.RED + " is already in gamemode " + ChatColor.LIGHT_PURPLE + "Survival");
                        }
                    } else {
                        player.sendMessage(ChatColor.LIGHT_PURPLE + args[0] + ChatColor.GRAY + " is not online");
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
            }
        } else {
            MessageManager.consoleBad("You must be a player to execute this command.");        }
        return true;
    }
}
