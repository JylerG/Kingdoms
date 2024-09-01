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

public class BalanceCommand implements CommandExecutor {

    final Kingdoms plugin;

    public BalanceCommand(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            switch (args.length) {
                case 0:
                    DecimalFormat formatter = new DecimalFormat("#,###.##");
                    if (!plugin.getMoney().containsKey(player.getUniqueId().toString())) {
                        plugin.getMoney().put(player.getUniqueId().toString(), 0L);
                    }
                    String formattedMoney = formatter.format(plugin.getMoney().get(player.getUniqueId().toString()));

                    player.sendMessage(ChatColor.GREEN + "Your balance is " + ChatColor.WHITE + formattedMoney + ChatColor.GREEN + " coins");

                    break;

                case 1:
                    Player target = Bukkit.getPlayer(args[0]);
                    formatter = new DecimalFormat("#,###.##");
                    if (!plugin.getMoney().containsKey(player.getUniqueId().toString())) {
                        plugin.getMoney().put(player.getUniqueId().toString(), 0L);
                    }
                    formattedMoney = formatter.format(plugin.getMoney().get(target.getUniqueId().toString()));

                    player.sendMessage(ChatColor.YELLOW + target.getName() + "'s balance: " + ChatColor.AQUA + formattedMoney + " coins");
                    break;
            }
        } else {
            MessageManager.consoleBad("You must be a player to execute this command.");
        }

        return true;
    }
}
