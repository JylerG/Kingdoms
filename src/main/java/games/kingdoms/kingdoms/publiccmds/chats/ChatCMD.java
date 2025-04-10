package games.kingdoms.kingdoms.publiccmds.chats;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChatCMD implements CommandExecutor {

    final Kingdoms plugin = Kingdoms.getPlugin();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (!plugin.getChatFocus().containsKey(player.getUniqueId().toString())) {
                plugin.getChatFocus().put(player.getUniqueId().toString(), "GLOBAL");
            } else {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.GOLD + "Usage: /<chat> <type|msg>");
                    return true;
                }
                if (args.length == 1) {
                    //Global Chat
                    if (args[0].equalsIgnoreCase("global") || args[0].equalsIgnoreCase("g")) {
                        if (!plugin.getChatFocus().get(player.getUniqueId().toString()).equalsIgnoreCase("GLOBAL")) {
                            plugin.getChatFocus().put(player.getUniqueId().toString(), "GLOBAL");
                            player.sendMessage(ChatColor.GREEN + "Chat focus set to " + ChatColor.GOLD + "Global");
                        } else {
                            player.sendMessage(ChatColor.RED + "You are already focused on " + ChatColor.GOLD + "Global " + ChatColor.RED + "chat");
                        }
                    }
                    //Staff Chat
                    else if (args[0].equalsIgnoreCase("staff") || args[0].equalsIgnoreCase("s")) {
                        if (plugin.getStaff().containsKey(player.getUniqueId().toString())) {
                            if (!plugin.getChatFocus().get(player.getUniqueId().toString()).equalsIgnoreCase("STAFF")) {
                                plugin.getChatFocus().put(player.getUniqueId().toString(), "STAFF");
                                player.sendMessage(ChatColor.GREEN + "Chat focus set to " + ChatColor.AQUA + "Staff");
                            } else {
                                player.sendMessage(ChatColor.RED + "You are already focused on " + ChatColor.AQUA + "Staff " + ChatColor.RED + "chat");
                            }
                        }
                    }
                    //Kingdom Chat
                    else if (args[0].equalsIgnoreCase("kingdom") || args[0].equalsIgnoreCase("k")) {
                        if (!plugin.getKingdoms().get(player.getUniqueId().toString()).isEmpty()) {
                            if (!plugin.getChatFocus().get(player.getUniqueId().toString()).equalsIgnoreCase("KINGDOM")) {
                                plugin.getChatFocus().put(player.getUniqueId().toString(), "KINGDOM");
                                player.sendMessage(ChatColor.GREEN + "Chat focus set to " + ChatColor.DARK_GREEN + "Kingdom");
                            } else {
                                player.sendMessage(ChatColor.RED + "You are already focused on " + ChatColor.DARK_GREEN + "Kingdom " + ChatColor.RED + "chat");
                            }
                        } else {
                            MessageManager.playerBad(player, "You are not in a kingdom");
                        }
                    }
                    plugin.savePluginData(player);
                }
            }
        }
        return true;
    }
}
