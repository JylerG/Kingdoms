package games.kingdoms.kingdoms.publiccmds.chats;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import games.kingdoms.kingdoms.admin.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ChatCMD implements Listener, CommandExecutor {

    private final Kingdoms plugin = Kingdoms.getPlugin();

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
                        if (plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
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
                }
            }
        }
        return true;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        HashMap<String, String> kingdoms = plugin.getKingdoms();
        Map<String, String> playerRank = plugin.getPlayerRank();
        String eventMessage = event.getMessage();
        Player player = event.getPlayer();
        if (plugin.getChatFocus().get(player.getUniqueId().toString()).equalsIgnoreCase("GLOBAL")) {
            if (kingdoms.containsKey(player.getUniqueId().toString())) {
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + ChatColor.WHITE + player.getDisplayName() + ": " + ChatColor.WHITE + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + ChatColor.GREEN + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.RED.toString() + ChatColor.BOLD + "YT")) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + ChatColor.RED + ChatColor.BOLD + "YT " + ChatColor.WHITE + player.getDisplayName() + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.LIGHT_PURPLE + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.LIGHT_PURPLE + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
            } else {
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.GREEN + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.RED.toString() + ChatColor.BOLD + "YT")) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.RED + ChatColor.BOLD + "YT " + ChatColor.WHITE + player.getDisplayName() + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.LIGHT_PURPLE + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + playerRank.get(player.getUniqueId().toString()) + ChatColor.LIGHT_PURPLE + " " + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
            }
            event.setMessage(eventMessage);
        } else if (plugin.getChatFocus().get(player.getUniqueId().toString()).equalsIgnoreCase("STAFF")) {

            playerRank = plugin.getStaff();
            for (Player p : Bukkit.getOnlinePlayers()) {

                if (playerRank.get(player.getUniqueId().toString()).equals("JRMOD")) {
                    String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + ChatColor.DARK_GREEN + ChatColor.BOLD + Rank.JRMOD + " " + ChatColor.DARK_GREEN + player.getDisplayName() + ChatColor.AQUA + ": " + eventMessage;
                    event.setFormat(format);
                } else if (playerRank.get(player.getUniqueId().toString()).equals("MOD")) {
                    String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.AQUA + ": " + eventMessage;
                    event.setFormat(format);
                } else if (playerRank.get(player.getUniqueId().toString()).equals("SRMOD")) {
                    String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD + " " + ChatColor.GOLD + player.getDisplayName() + ChatColor.AQUA + ": " + eventMessage;
                    event.setFormat(format);
                } else if (playerRank.get(player.getUniqueId().toString()).equals("JRADMIN")) {
                    String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN + " " + ChatColor.LIGHT_PURPLE + player.getDisplayName() + ChatColor.AQUA + ": " + eventMessage;
                    event.setFormat(format);
                } else if (playerRank.get(player.getUniqueId().toString()).equals("ADMIN")) {
                    String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.ADMIN + " " + ChatColor.LIGHT_PURPLE + player.getDisplayName() + ChatColor.AQUA + ": " + eventMessage;
                    event.setFormat(format);
                }
                event.setMessage(eventMessage);

                //todo: figure out why this doesn't work, but kingdom chat does
                if (plugin.getStaff().containsKey(p.getUniqueId().toString())) {
                    if (plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("JRMOD")
                    || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("MOD")
                    || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("SRMOD")
                    || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("JRADMIN")
                    || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                        p.sendMessage(event.getFormat());
                    }
                }
                event.setCancelled(true);
            }
        } else if (plugin.getChatFocus().get(player.getUniqueId().toString()).equalsIgnoreCase("KINGDOM")) {
            if (plugin.getOwner().containsKey(player.getUniqueId().toString())) {
                String format = ChatColor.GOLD.toString() + ChatColor.BOLD + "[K] " + ChatColor.LIGHT_PURPLE + "King " + ChatColor.GOLD + player.getDisplayName() + ": " + eventMessage;
                event.setFormat(format);
            } else if (plugin.getAdmin().containsKey(player.getUniqueId().toString())) {
                String format = ChatColor.GOLD.toString() + ChatColor.BOLD + "[K] " + ChatColor.LIGHT_PURPLE + "Knight " + ChatColor.GOLD + player.getDisplayName() + ": " + eventMessage;
                event.setFormat(format);
            } else if (plugin.getMember().containsKey(player.getUniqueId().toString()) && !plugin.getOwner().containsKey(player.getUniqueId().toString())) {
                String format = ChatColor.GOLD.toString() + ChatColor.BOLD + "[K] " + ChatColor.LIGHT_PURPLE + "Citizen " + ChatColor.GOLD + player.getDisplayName() + ": " + eventMessage;
                event.setFormat(format);
            }
            event.setMessage(eventMessage);

            for (Player p : Bukkit.getOnlinePlayers()) {
                String onlinePlayerKingdom = plugin.getKingdoms().get(p.getUniqueId().toString());
                String playerKingdom = plugin.getKingdoms().get(player.getUniqueId().toString());

                if (plugin.getKingdoms().containsKey(p.getUniqueId().toString())) {
                    if (onlinePlayerKingdom.equals(playerKingdom)) {
                        p.sendMessage(event.getFormat());
                    }
                }
                event.setCancelled(true);
            }
        }
    }
}
