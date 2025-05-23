package games.kingdoms.kingdoms.publiccmds.chats;

import com.github.sanctum.panther.file.Configurable;
import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import games.kingdoms.kingdoms.admin.configs.KingdomsConfig;
import games.kingdoms.kingdoms.admin.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Map;

public class ChatListener implements Listener {

    final Kingdoms plugin = Kingdoms.getPlugin();
    final HashMap<String, Integer> kingdomRank = plugin.getKingdomRank();
    final HashMap<String, String> chatFocus = plugin.getChatFocus();
    final HashMap<String, String> kingdoms = plugin.getKingdoms();
    final Map<String, String> playerRank = plugin.getPlayerRank();
    final HashMap<String, String> staff = plugin.getStaff();
    final HashMap<String, String> spyOnKingdom = plugin.getSpyOnKingdom();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String eventMessage = event.getMessage();
        Player player = event.getPlayer();
        if (!player.hasPermission("kingdoms.move")) {
            MessageManager.playerBad(player, "You must enter your password before you can chat");
            event.setCancelled(true);
            return;
        }
        if (chatFocus.get(player.getUniqueId().toString()).equalsIgnoreCase("GLOBAL")) {
            if (!kingdoms.get(player.getUniqueId().toString()).isEmpty()) {
                String kingdom = plugin.getKingdoms().get(player.getUniqueId().toString());
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdom + ChatColor.WHITE + "] " + ChatColor.WHITE + player.getDisplayName() + ": " + ChatColor.WHITE + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdom + ChatColor.WHITE + "] " + ChatColor.GREEN + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdom + ChatColor.WHITE + "] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.RED.toString() + ChatColor.BOLD + "YT")) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdom + ChatColor.WHITE + "] " + ChatColor.RED + ChatColor.BOLD + "YT " + ChatColor.WHITE + player.getDisplayName() + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdom + ChatColor.WHITE + "] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdom + ChatColor.WHITE + "] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdom + ChatColor.WHITE + "] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdom + ChatColor.WHITE + "] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.LIGHT_PURPLE + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                    event.setFormat(format);
                }
                if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN.toString())) {
                    String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdom + ChatColor.WHITE + "] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.LIGHT_PURPLE + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
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
        } else if (chatFocus.get(player.getUniqueId().toString()).equalsIgnoreCase("STAFF")) {

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (plugin.getStaff().containsKey(player.getUniqueId().toString())) {
                    if (plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("JRMOD")
                            || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("MOD")
                            || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("SRMOD")
                            || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("JRADMIN")
                            || plugin.getStaff().get(p.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                        String staff = plugin.getStaff().get(player.getUniqueId().toString());

                        //Player has JrMod Rank
                        if (staff.equalsIgnoreCase("JRMOD")) {
                            String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + ChatColor.DARK_GREEN + ChatColor.BOLD + Rank.JRMOD + " " +
                                    ChatColor.DARK_GREEN + player.getName() + ChatColor.AQUA + ": " + eventMessage;
                            event.setFormat(format);
                        }
                        //Player has Mod Rank
                        if (staff.equalsIgnoreCase("MOD")) {
                            String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD + " " +
                                    ChatColor.YELLOW + player.getName() + ChatColor.AQUA + ": " + eventMessage;
                            event.setFormat(format);
                        }
                        //Player has SrMod Rank
                        if (staff.equalsIgnoreCase("SRMOD")) {
                            String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD + " " +
                                    ChatColor.GOLD + player.getName() + ChatColor.AQUA + ": " + eventMessage;
                            event.setFormat(format);
                        }
                        //Player has JrAdmin Rank
                        if (staff.equalsIgnoreCase("JRADMIN")) {
                            String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + ChatColor.RED + ChatColor.BOLD + Rank.JRADMIN + " " +
                                    ChatColor.RED + player.getName() + ChatColor.AQUA + ": " + eventMessage;
                            event.setFormat(format);
                        }
                        //Player has Admin Rank
                        else if (staff.equalsIgnoreCase("ADMIN")) {
                            String format = ChatColor.AQUA.toString() + ChatColor.BOLD + "[S] " + ChatColor.RED + ChatColor.BOLD + Rank.ADMIN + " " +
                                    ChatColor.RED + player.getName() + ChatColor.AQUA + ": " + eventMessage;
                            event.setFormat(format);
                        }
                    }
                }
                if (staff.containsKey(p.getUniqueId().toString())) {
                    p.sendMessage(event.getFormat());
                }
                event.setCancelled(true);
            }
        } else if (chatFocus.get(player.getUniqueId().toString()).equalsIgnoreCase("KINGDOM")) {
            String playerUUID = player.getUniqueId().toString();
            Configurable kc = KingdomsConfig.getInstance().getConfig();

            // Check if the player is in a kingdom
            if (!kingdoms.containsKey(playerUUID)) {
                return; // player isn't in a kingdom, skip formatting
            }

            String kingdomName = kingdoms.get(playerUUID);
            String rankKey = kc.getNode("players." + playerUUID).toPrimitive().getString();

            if (rankKey == null) {
                return; // player rank not found, skip formatting
            }

            // Get the actual rank name from ranksInKingdoms
            String playerRank = kc.getNode("ranksInKingdoms." + kingdomName + "." + rankKey).toPrimitive().getString();

            if (playerRank == null) {
                return; // no rank found, skip formatting
            }

            // Now format the chat message
            String format = ChatColor.GOLD.toString() + ChatColor.BOLD + "[K] " + ChatColor.LIGHT_PURPLE +
                    playerRank + " " + ChatColor.GOLD + player.getDisplayName() + ChatColor.WHITE + ": " + ChatColor.RESET + event.getMessage();
            event.setFormat(format);
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            String onlinePlayerKingdom = kingdoms.get(p.getUniqueId().toString());
            String playerKingdom = kingdoms.get(player.getUniqueId().toString());

            if (kingdoms.containsKey(p.getUniqueId().toString())) {
                if (onlinePlayerKingdom.equals(playerKingdom)
                        || (spyOnKingdom.containsKey(p.getUniqueId().toString())
                        && spyOnKingdom.get(p.getUniqueId().toString()).equalsIgnoreCase(kingdoms.get(player.getUniqueId().toString())))) {
                    p.sendMessage(event.getFormat());
                }
            }
            event.setCancelled(true);
        }
    }
}