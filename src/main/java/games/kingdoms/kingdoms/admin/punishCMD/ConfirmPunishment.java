package games.kingdoms.kingdoms.admin.punishCMD;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ConfirmPunishment implements CommandExecutor {

    final Kingdoms plugin = Kingdoms.getPlugin();
    final HashMap<String, Integer> spam = plugin.getSpam();
    final HashMap<String, Integer> threats = plugin.getThreats();
    final HashMap<String, Integer> language = plugin.getLanguage();
    final HashMap<String, Integer> disrespect = plugin.getDisrespect();
    final HashMap<String, Integer> ipAdverts = plugin.getIpAdverts();
    final HashMap<String, Integer> soliciting = plugin.getSoliciting();
    final HashMap<String, Integer> reportAbuse = plugin.getReportAbuse();
    final HashMap<String, Integer> mediaAdverts = plugin.getMediaAdverts();
    final HashMap<String, Integer> discrimination = plugin.getDiscrimination();
    final HashMap<String, String> staff = plugin.getStaff();
    final HashMap<String, String> playerToPunish = plugin.getPlayerToPunish();

    private static final int CHAT_WIDTH = 600; // Adjust this value as needed to fit your server's chat width

    private String centerMessage(String message) {
        int messagePxSize = getMessagePixelSize(message);
        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CHAT_WIDTH / 2 - halvedMessageSize;
        int spaceLength = getMessagePixelSize(" ");
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        return sb.toString() + message;
    }

    private int getMessagePixelSize(String message) {
        int size = 0;
        boolean bold = false;
        for (char c : message.toCharArray()) {
            if (c == ChatColor.COLOR_CHAR) {
                bold = false;
                continue;
            } else if (bold) {
                size += getWidth(c) + 1;
            } else {
                size += getWidth(c);
            }
            if (c == ' ') {
                size += 3; // Adjust as necessary to better fit space width
            }
        }
        return size;
    }

    private int getWidth(char c) {
        return switch (c) {
            case 'I', 'i', 'l', '.', ':' -> 2;
            case 't', 'k', ',' -> 3;
            case 'f', 'r' -> 4;
            case 'a', 'b', 'c', 'd', 'e', 'g', 'h', 'j', 'm', 'n', 'o', 'p', 'q', 's', 'u', 'v', 'w', 'x', 'y', 'z', '-', '_', '1', '7' ->
                    5;
            case ' ' -> 6; // Adjust as necessary to better fit space width
            default -> 6; // Default width for other characters
        };
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        String dash = "-";
        String sep = dash + dash + dash + dash + dash + dash + dash + dash + dash + dash
                + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash
                + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash
                + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash;

        if (sender instanceof Player player) {
            if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                if (!playerToPunish.containsKey(player.getUniqueId().toString())) {
                    MessageManager.playerBad(player, "You do not have anyone selected to punish");
                    return true;
                }

                //todo: increment below this
                //Report Abuse
                if (args[1].equalsIgnoreCase("abuse")) {
                    // Ensure the target's record exists and increment
                    reportAbuse.putIfAbsent(target.getUniqueId().toString(), 0);
                    reportAbuse.put(target.getUniqueId().toString(), reportAbuse.get(target.getUniqueId().toString()) + 1);
                } else if (args[1].equalsIgnoreCase("disrespect")) {
                    // Ensure the target's record exists and increment
                    disrespect.putIfAbsent(target.getUniqueId().toString(), 0);
                    disrespect.put(target.getUniqueId().toString(), disrespect.get(target.getUniqueId().toString()) + 1);
                } else if (args[1].equalsIgnoreCase("language")) {
                    // Ensure the target's record exists and increment
                    language.putIfAbsent(target.getUniqueId().toString(), 0);
                    language.put(target.getUniqueId().toString(), language.get(target.getUniqueId().toString()) + 1);
                }

                //todo: send message to players in here
                for (Player p : Bukkit.getOnlinePlayers()) {
                    boolean isStaff = staff.get(p.getUniqueId().toString()).equalsIgnoreCase("JRMOD")
                            || staff.get(p.getUniqueId().toString()).equalsIgnoreCase("MOD")
                            || staff.get(p.getUniqueId().toString()).equalsIgnoreCase("SRMOD")
                            || staff.get(p.getUniqueId().toString()).equalsIgnoreCase("JRADMIN")
                            || staff.get(p.getUniqueId().toString()).equalsIgnoreCase("ADMIN");
                    //Report Abuse
                    if (args[1].equalsIgnoreCase("1")) {

                        // Fetch the updated count
                        int count = plugin.getReportAbuse().get(target.getUniqueId().toString());

                        // Determine warning or mute based on the count
                        if (count > 0 && count < 5) {
                            if (isStaff) {
                                p.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "[ENF] " + ChatColor.RED + ChatColor.BOLD + "[!] "
                                        + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " was warned by " + ChatColor.WHITE + player.getName()
                                        + ChatColor.GRAY + " for " + ChatColor.GOLD + "Report Abuse Offense #" + count);
                            }
                            p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                            p.sendMessage(centerMessage(target.getName() + ChatColor.YELLOW + " was warned"));
                            p.sendMessage(centerMessage(ChatColor.YELLOW + "for " + ChatColor.WHITE + "Report Abuse - Violation #" + count));
                            p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                        } else if (count >= 5) {
                            if (staff.containsKey(p.getUniqueId().toString()) || staff.containsKey(player.getUniqueId().toString())) {
                                p.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "[ENF] " + ChatColor.RED + ChatColor.BOLD + "[!] "
                                        + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " was muted by " + ChatColor.WHITE + player.getName()
                                        + ChatColor.GRAY + " for " + ChatColor.GOLD + "Report Abuse Offense #" + count);
                            }
                            p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                            p.sendMessage(centerMessage(target.getName() + ChatColor.YELLOW + " was muted"));
                            p.sendMessage(centerMessage(ChatColor.YELLOW + "for " + ChatColor.WHITE + "Report Abuse - Violation #" + count));
                            p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                        }
                    }
                    //Disrespect
                    else if (args[1].equalsIgnoreCase("2")) {

                        int count = plugin.getDisrespect().get(target.getUniqueId().toString());
                        if (isStaff) {
                            p.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "[ENF] " + ChatColor.RED + ChatColor.BOLD + "[!] "
                                    + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " was muted by " + ChatColor.WHITE + player.getName()
                                    + ChatColor.GRAY + " for " + ChatColor.GOLD + "Disrespect Offense #" + count);
                        }
                        if (plugin.getDisrespect().get(target.getUniqueId().toString()) > 0) {
                            p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                            p.sendMessage(centerMessage(target.getName() + ChatColor.YELLOW + " was muted"));
                            p.sendMessage(centerMessage(ChatColor.YELLOW + "for " + ChatColor.WHITE + "Disrespect - Violation #" + count));
                            p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                        }
                    }
                    //Inappropriate Language
                    else if (args[1].equalsIgnoreCase("3")) {

                        int count = plugin.getLanguage().get(target.getUniqueId().toString());

                        if (isStaff) {
                            p.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "[ENF] " + ChatColor.RED + ChatColor.BOLD + "[!] "
                                    + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " was muted by " + ChatColor.WHITE + player.getName()
                                    + ChatColor.GRAY + " for " + ChatColor.GOLD + "Inapp. Language Offense #" + count);
                        }
                        if (plugin.getLanguage().get(target.getUniqueId().toString()) > 0 && plugin.getLanguage().get(target.getUniqueId().toString()) <= 5) {
                            p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                            p.sendMessage(centerMessage(target.getName() + ChatColor.YELLOW + " was muted"));
                            p.sendMessage(centerMessage(ChatColor.YELLOW + "for " + ChatColor.WHITE + "Inapp. Language - Violation #" + count));
                            p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                        } else if (plugin.getLanguage().get(target.getUniqueId().toString()) > 5) {
                            p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                            p.sendMessage(centerMessage(target.getName() + ChatColor.YELLOW + " was muted"));
                            p.sendMessage(centerMessage(ChatColor.YELLOW + "for " + ChatColor.WHITE + "Inapp. Language - Violation #" + count));
                            p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                        }
                    }
                    //TODO: put the rest below here
                    playerToPunish.remove(player.getUniqueId().toString(), target.getUniqueId().toString());
                }
            }
        }
        return true;
    }
}
