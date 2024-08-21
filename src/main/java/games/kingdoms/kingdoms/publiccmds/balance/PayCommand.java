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

import java.text.DecimalFormat;

public class PayCommand implements CommandExecutor {

    final Kingdoms plugin;

    public PayCommand(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

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
                        DecimalFormat formatter = new DecimalFormat("#,###.##");
                        String formattedMoney = formatter.format(args[1]);


                        if (Double.parseDouble(formattedMoney) > 1 && Double.parseDouble(formattedMoney) > plugin.getMoney().get(player.getUniqueId().toString())) {
                            player.sendMessage(ChatColor.RED + "You do not have " + ChatColor.WHITE + Integer.parseInt(args[1]) + ChatColor.RED + " coins");
                            return true;
                        }

                        plugin.getMoney().put(target.getUniqueId().toString(), Double.valueOf(formattedMoney));
                        player.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "[-] " + ChatColor.WHITE + Integer.parseInt(args[1]) + ChatColor.RED + " coins to " + ChatColor.WHITE + target.getName());
                        target.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "[+] " + ChatColor.WHITE + Integer.parseInt(args[1]) + ChatColor.GREEN + " coins from " + ChatColor.WHITE + player.getName());
                    }
                    break;
            }

        } else {
            MessageManager.consoleBad("You must be a player to execute this command.");
        }
        return true;
    }
}
