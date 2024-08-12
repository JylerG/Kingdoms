package games.kingdoms.kingdoms.publiccmds.balance;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PayCommand implements CommandExecutor {

    private final Kingdoms plugin;

    public PayCommand(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            switch (args.length) {
                case 0:
                    player.sendMessage(ChatColor.GOLD + "Usage: /pay <player> <amount>");
                case 1:
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == player) {
                        MessageManager.playerBad(player, "You cannot send yourself money");
                        return true;
                    }
                    break;
                case 2:
                    target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        player.sendMessage(ChatColor.WHITE + args[0] + ChatColor.RED + " is not online");
                        return true;
                    }
                    if (target == player) {
                        MessageManager.playerBad(player, "You cannot send yourself money");
                        return true;
                    } else {
                        //player can't send 0 coins
                        if (Integer.parseInt(args[1]) < 1) {
                            player.sendMessage(ChatColor.RED + "You cannot send " + ChatColor.WHITE + args[1] + ChatColor.RED + " coins to a player");
                        }
                        //player is sending 1 coin
                        if (Integer.parseInt(args[1]) == 1 && Integer.parseInt(args[1]) <= plugin.getMoney().get(player.getUniqueId().toString())) {
                            plugin.getMoney().put(target.getUniqueId().toString(), plugin.getMoney().get(target.getUniqueId().toString()) + Integer.parseInt(args[1]));
                            plugin.getMoney().put(player.getUniqueId().toString(), plugin.getMoney().get(player.getUniqueId().toString()) - Integer.parseInt(args[1]));
                            player.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "[-] " + ChatColor.WHITE + Integer.parseInt(args[1]) + ChatColor.RED + " coins to " + ChatColor.WHITE + target.getName());
                            target.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "[+] " + ChatColor.WHITE + Integer.parseInt(args[1]) + ChatColor.GREEN + " coins from " + ChatColor.WHITE + player.getName());
                        }
                        //player is sending more than 1 coin
                        if (Integer.parseInt(args[1]) > 1 && Integer.parseInt(args[1]) < plugin.getMoney().get(player.getUniqueId().toString())) {
                            plugin.getMoney().put(target.getUniqueId().toString(),  plugin.getMoney().get(target.getUniqueId().toString()) + Integer.parseInt(args[1]));
                            plugin.getMoney().put(player.getUniqueId().toString(), plugin.getMoney().get(player.getUniqueId().toString()) - Integer.parseInt(args[1]));
                            player.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "[-] " + ChatColor.WHITE + Integer.parseInt(args[1]) + ChatColor.RED + " coins to " + ChatColor.WHITE + target.getName());
                            target.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "[+] " + ChatColor.WHITE + Integer.parseInt(args[1]) + ChatColor.GREEN + " coins from " + ChatColor.WHITE + player.getName());
                        }
                        if (Integer.parseInt(args[1]) >= 1 && Integer.parseInt(args[1]) > plugin.getMoney().get(player.getUniqueId().toString())) {
                            player.sendMessage(ChatColor.RED + "You do not have " + ChatColor.WHITE + Integer.parseInt(args[1]) + ChatColor.RED + " coins");
                        }
                    }
                    break;
            }

        } else {
            MessageManager.consoleBad("You must be a player to execute this command.");
        }
        return true;
    }
}
