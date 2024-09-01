package games.kingdoms.kingdoms.admin.balance;

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

public class EconomyCommand implements CommandExecutor {

    final Kingdoms plugin;

    public EconomyCommand(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            // /eco give <player> <amount>
            if (player.hasPermission("kingdoms.economy")) {
                switch (args.length) {
                    case 0:
                    case 1:
                    case 2:
                        player.sendMessage(ChatColor.GOLD + "Usage: /eco <command> <player> <amount>");
                        break;
                    case 3:

                        //TODO: Figure out why give and set display the message three times upon command execution
                        //TODO: Figure out why remove command doesn't display anything
                        //TODO: Figure out how to use commands with letters (ie K for thousand/M for million)
                        Player target = Bukkit.getPlayer(args[1]);
                        if (!args[1].equalsIgnoreCase("*") && target == null) {
                            player.sendMessage(args[1] + ChatColor.RED + " is not online");
                        } else {
                            DecimalFormat formatter = new DecimalFormat("#,###.##");
                            try {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (!plugin.getMoney().containsKey(p.getUniqueId().toString())) {
                                        plugin.getMoney().put(p.getUniqueId().toString(), 0L);
                                    }
                                }

                                String formattedMoney = formatter.format(Long.parseLong(args[2]));
                                if (args[0].equalsIgnoreCase("give")) {


                                    if (args[1].equalsIgnoreCase("*")) {
                                        for (Player p : Bukkit.getOnlinePlayers()) {

                                            p.sendMessage(ChatColor.GREEN + "[+] " + ChatColor.WHITE + formattedMoney + " coins");
                                            plugin.getMoney().put(p.getUniqueId().toString(), plugin.getMoney().get(p.getUniqueId().toString()) + Long.parseLong(args[2]));
                                        }
                                    } else {
                                        target.sendMessage(ChatColor.GREEN + "[+] " + ChatColor.WHITE + formattedMoney + ChatColor.GREEN + " coins");
                                    }
                                    return true;
                                }

                                if (args[0].equalsIgnoreCase("set")) {
                                    if (args[1].equalsIgnoreCase("*")) {
                                        for (Player p : Bukkit.getOnlinePlayers()) {
                                            p.sendMessage(ChatColor.YELLOW + "Your balance was set to " + ChatColor.WHITE + formattedMoney + " coins");
                                            plugin.getMoney().put(p.getUniqueId().toString(), Long.parseLong(args[2]));
                                        }
                                    } else {

                                        target.sendMessage(ChatColor.YELLOW + "Your balance was set to " + ChatColor.WHITE + formattedMoney + " coins");

                                        plugin.getMoney().put(target.getUniqueId().toString(), Long.parseLong(args[2]));
                                    }
                                    return true;
                                }

                                if (args[0].equalsIgnoreCase("remove")) {
                                    if (args[1].equalsIgnoreCase("*")) {
                                        for (Player p : Bukkit.getOnlinePlayers()) {

                                            p.sendMessage(ChatColor.RED + "[-] " + ChatColor.WHITE + formattedMoney + " coins");
                                            player.sendMessage(ChatColor.RED + "[-] " + ChatColor.WHITE + formattedMoney + " coins");

                                            plugin.getMoney().put(p.getUniqueId().toString(), plugin.getMoney().get(p.getUniqueId().toString()) - Long.parseLong(args[2]));

                                        }

                                    } else {

                                        target.sendMessage(ChatColor.RED + "[-] " + ChatColor.WHITE + formattedMoney + " coins");

                                        plugin.getMoney().put(target.getUniqueId().toString(), plugin.getMoney().get(target.getUniqueId().toString()) - Long.parseLong(args[2]));
                                    }
                                }
                            } catch (NumberFormatException e) {
                                player.sendMessage(ChatColor.RED + "Amount must be" + ChatColor.WHITE + ChatColor.BOLD + " <= " + ChatColor.GOLD + formatter.format(Long.MAX_VALUE));
                            }
                                break;
                        }
                }
            } else {
                MessageManager.playerBad(player, "You do not have permission to use this command");
            }
        } else {
            MessageManager.consoleBad("You must be a player to execute this command.");
        }
        return true;
    }
}
