package games.kingdoms.kingdoms.admin.punishCMDs;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SrModPunish implements CommandExecutor {

    private final Kingdoms plugin = Kingdoms.getPlugin();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String dash = "-";
        String sep = ChatColor.DARK_RED.toString() + ChatColor.BOLD + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash
                + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash
                + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash
                + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash;
        if (sender instanceof Player player) {
            if (player.hasPermission("kingdoms.srmod.punish")) {
                if (args.length == 0) {
                    //todo: list available punishments
                    player.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + dash + dash + dash + ChatColor.WHITE + ChatColor.BOLD + " Server Rules " + ChatColor.AQUA + ChatColor.BOLD + dash + dash + dash);
                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "1" + ChatColor.WHITE + " Abusing /Report");
                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "2" + ChatColor.WHITE + " Disrespect");
                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "3" + ChatColor.WHITE + " Inapp. Language");
                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "4" + ChatColor.WHITE + " IP Adverts");
                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "5" + ChatColor.WHITE + " Media Adverts");
                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "6" + ChatColor.WHITE + " Soliciting");
                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "7" + ChatColor.WHITE + " Spam");
                } else if (args.length == 1) {
                    player.sendMessage(ChatColor.GOLD + "Usage: /punish <user> <reason>");
                } else if (args.length == 2) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        player.sendMessage(args[0] + " is not online");
                        return true;
                    }
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        //Report Abuse
                        if (args[1].equalsIgnoreCase("1")) {
                            if (!plugin.getReportAbuse().containsKey(target.getUniqueId().toString())) {
//                                plugin.getReportAbuse().put(target.getUniqueId().toString(), 1);
                                centerMessage(target.getName() + ChatColor.YELLOW + " was warned");
                                centerMessage(ChatColor.YELLOW + "for " + ChatColor.WHITE + "Report Abuse - Violation #1");
                            } else if (plugin.getReportAbuse().get(target.getUniqueId().toString()).equals(1)) {

                            } else if (plugin.getReportAbuse().get(target.getUniqueId().toString()).equals(2)) {

                            } else if (plugin.getReportAbuse().get(target.getUniqueId().toString()).equals(3)) {

                            } else if (plugin.getReportAbuse().get(target.getUniqueId().toString()).equals(4)) {

                            } else if (plugin.getReportAbuse().get(target.getUniqueId().toString()).equals(5)) {

                            } else if (plugin.getReportAbuse().get(target.getUniqueId().toString()).equals(6)) {

                            } else if (plugin.getReportAbuse().get(target.getUniqueId().toString()).equals(7)) {

                            } else if (plugin.getReportAbuse().get(target.getUniqueId().toString()).equals(8)) {

                            }
                        } //Disrespect
                        else if (args[1].equalsIgnoreCase("2")) {

                        } //Inappropriate Language
                        else if (args[1].equalsIgnoreCase("3")) {

                        } //IP Adverts
                        else if (args[1].equalsIgnoreCase("4")) {

                        } //Media Adverts
                        else if (args[1].equalsIgnoreCase("5")) {

                        } //Soliciting
                        else if (args[1].equalsIgnoreCase("6")) {

                        } //Spam
                        else if (args[1].equalsIgnoreCase("7")) {

                        }
                    }
                } else if (args.length > 2) {
                    player.sendMessage(ChatColor.GOLD + "Usage: /punish <user> <reason>");
                }

            }
        }
        return true;
    }

    private static final int CHAT_WIDTH = 320; // Adjust this value as needed to fit your server's chat width

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
        switch (c) {
            case 'I': case 'i': case 'l': case '.': case ':': return 2;
            case 't': case 'k': case ',': return 3;
            case 'f': case 'r': return 4;
            case 'a': case 'b': case 'c': case 'd': case 'e': case 'g': case 'h':
            case 'j': case 'm': case 'n': case 'o': case 'p': case 'q': case 's':
            case 'u': case 'v': case 'w': case 'x': case 'y': case 'z': case '-':
            case '_': case '1': case '7': return 5;
            case ' ': return 6; // Adjust as necessary to better fit space width
            default: return 6; // Default width for other characters
        }
    }
}
