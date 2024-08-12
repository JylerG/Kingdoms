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

public class BalanceCommand implements CommandExecutor {

    private final Kingdoms plugin;

    public BalanceCommand(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            switch (args.length) {
                case 0:
                    long moneyValue = plugin.getMoney().get(player.getUniqueId().toString());

                    String formattedMoney;

                    if (moneyValue == 0) {
                        formattedMoney = "0";
                    } else if (moneyValue == 1) {
                        formattedMoney = "1";
                    } else if (moneyValue < 1_000.0) {
                        formattedMoney = String.valueOf(moneyValue);
                    } else if (moneyValue < 1_000_000.0) {
                        formattedMoney = String.format("%.3fK", moneyValue / 1_000.0);
                    } else if (moneyValue < 1_000_000_000.0) {
                        formattedMoney = String.format("%.3fM", moneyValue / 1_000_000.0);
                    } else if (moneyValue < 1_000_000_000_000L) {
                        formattedMoney = String.format("%.3fB", moneyValue / 1_000_000_000.0);
                    } else if (moneyValue < 1_000_000_000_000_000L) {
                        formattedMoney = String.format("%.3fT", moneyValue / 1_000_000_000_000.0);
                    } else if (moneyValue < 1_000_000_000_000_000_000L) {
                        formattedMoney = String.format("%.3fQ", moneyValue / 1_000_000_000_000_000.0);
                    } else if (moneyValue < 1_000_000_000_000_000_000_000.0) {
                        formattedMoney = String.format("%.3fQU", moneyValue / 1_000_000_000_000_000_000.0);
                    } else {
                        formattedMoney = String.format("%.3fS", moneyValue / 1_000_000_000_000_000_000_000.0);
                    }

                    player.sendMessage(ChatColor.GREEN + "Your balance is " + ChatColor.WHITE + formattedMoney + ChatColor.GREEN + " coins");

                    break;

                case 1:
                    Player target = Bukkit.getPlayer(args[0]);
                    moneyValue = plugin.getMoney().get(target.getUniqueId().toString());

                    if (moneyValue == 0) {
                        formattedMoney = "0";
                    } else if (moneyValue == 1) {
                        formattedMoney = "1";
                    } else if (moneyValue < 1_000.0) {
                        formattedMoney = String.valueOf(moneyValue);
                    } else if (moneyValue < 1_000_000.0) {
                        formattedMoney = String.format("%.3fK", moneyValue / 1_000.0);
                    } else if (moneyValue < 1_000_000_000.0) {
                        formattedMoney = String.format("%.3fM", moneyValue / 1_000_000.0);
                    } else if (moneyValue < 1_000_000_000_000L) {
                        formattedMoney = String.format("%.3fB", moneyValue / 1_000_000_000.0);
                    } else if (moneyValue < 1_000_000_000_000_000L) {
                        formattedMoney = String.format("%.3fT", moneyValue / 1_000_000_000_000.0);
                    } else if (moneyValue < 1_000_000_000_000_000_000L) {
                        formattedMoney = String.format("%.3fQ", moneyValue / 1_000_000_000_000_000.0);
                    } else if (moneyValue < 1_000_000_000_000_000_000_000.0) {
                        formattedMoney = String.format("%.3fQU", moneyValue / 1_000_000_000_000_000_000.0);
                    } else {
                        formattedMoney = String.format("%.3fS", moneyValue / 1_000_000_000_000_000_000_000.0);
                    }

                    player.sendMessage(target.getName() + ChatColor.GREEN + "'s balance is " + ChatColor.WHITE + formattedMoney + ChatColor.GREEN + " coins");
                    break;
            }
        } else {
            MessageManager.consoleBad("You must be a player to execute this command.");
        }

        return true;
    }
}
