package games.kingdoms.kingdoms.admin.punishCMD;

import games.kingdoms.kingdoms.Kingdoms;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
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

public class PunishCommand implements CommandExecutor, Listener {

    private final Kingdoms plugin = Kingdoms.getPlugin();

    private Map<ClickEvent, TextComponent> punish = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String dash = "-";
        String sep = dash + dash + dash + dash + dash + dash + dash + dash + dash + dash
                + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash
                + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash
                + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash;
        if (sender instanceof Player player) {
            if (player.hasPermission("kingdoms.jrmod.punish")
                    || player.hasPermission("kingdoms.mod.punish")
                    || player.hasPermission("kingdoms.srmod.punish")
                    || player.hasPermission("kingdoms.admin.punish")) {
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
                        player.sendMessage(args[0] + ChatColor.RED + " is not online");
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("reset") && player.hasPermission("kingdoms.admin.punish")) {
                        plugin.getReportAbuse().put(target.getUniqueId().toString(), 0);
                        plugin.getDiscrimination().put(target.getUniqueId().toString(), 0);
                        plugin.getDisrespect().put(target.getUniqueId().toString(), 0);
                        plugin.getIpAdverts().put(target.getUniqueId().toString(), 0);
                        plugin.getMediaAdverts().put(target.getUniqueId().toString(), 0);
                        plugin.getLanguage().put(target.getUniqueId().toString(), 0);
                        plugin.getSoliciting().put(target.getUniqueId().toString(), 0);
                        plugin.getSpam().put(target.getUniqueId().toString(), 0);
                        plugin.getThreats().put(target.getUniqueId().toString(), 0);
                        player.sendMessage(ChatColor.GREEN + "You reset " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + "'s punishments to " + ChatColor.WHITE + 0);
                    }
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        //Report Abuse
                        if (args[1].equalsIgnoreCase("1")) {
                            if (!plugin.getReportAbuse().containsKey(target.getUniqueId().toString()) || plugin.getReportAbuse().get(target.getUniqueId().toString()) < 1) {

                                player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + target.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                player.sendMessage("Rule " + ChatColor.YELLOW + "Report Abuse " + ChatColor.GRAY + "(ID: 1)");
                                player.sendMessage("This is offense " + ChatColor.YELLOW + "#1" + ChatColor.WHITE + " for this player, which will result in ");
                                player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Warning #1");
                                player.sendMessage(ChatColor.WHITE.toString() + ChatColor.BOLD + "/punish " + target.getName() + " 1 confirm " + ChatColor.AQUA + ChatColor.BOLD + "to confirm this punishment");
                                player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                            } else {
                                int count = plugin.getReportAbuse().get(target.getUniqueId().toString()) + 1;
                                if (count >= 1 && count < 5) {

                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + target.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                    player.sendMessage("Rule " + ChatColor.YELLOW + "Report Abuse " + ChatColor.GRAY + "(ID: 1)");
                                    player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Warning #" + count);
                                    player.sendMessage(ChatColor.WHITE.toString() + ChatColor.BOLD + "/punish " + target.getName() + " 1 confirm " + ChatColor.AQUA + ChatColor.BOLD + "to confirm this punishment");
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);

                                } else if (count >= 5) {

                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + target.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                    player.sendMessage("Rule " + ChatColor.YELLOW + "Report Abuse " + ChatColor.GRAY + "(ID: 1)");
                                    player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 30m");
                                    player.sendMessage(ChatColor.WHITE.toString() + ChatColor.BOLD + "/punish " + target.getName() + " 1 confirm " + ChatColor.AQUA + ChatColor.BOLD + "to confirm this punishment");
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                }
                            }
                        }//Disrespect
                        else if (args[1].equalsIgnoreCase("2")) {
                            if (!plugin.getDisrespect().containsKey(target.getUniqueId().toString())) {

                                player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + target.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                player.sendMessage("Rule " + ChatColor.YELLOW + "Disrespect " + ChatColor.GRAY + "(ID: 2)");
                                player.sendMessage("This is offense " + ChatColor.YELLOW + "#1" + ChatColor.WHITE + " for this player, which will result in ");
                                player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 30m");
                                player.sendMessage(ChatColor.WHITE.toString() + ChatColor.BOLD + "/punish " + target.getName() + " 2 confirm " + ChatColor.AQUA + ChatColor.BOLD + "to confirm this punishment");
                                player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                            } else {

                                if (plugin.getDisrespect().get(target.getUniqueId().toString()) < 5) {

                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + target.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                    player.sendMessage("Rule " + ChatColor.YELLOW + "Disrespect " + ChatColor.GRAY + "(ID: 2)");
                                    int count = plugin.getDisrespect().get(target.getUniqueId().toString()) + 1;
                                    player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 1h");
                                    player.sendMessage(ChatColor.WHITE.toString() + ChatColor.BOLD + "/punish " + target.getName() + " 2 confirm " + ChatColor.AQUA + ChatColor.BOLD + "to confirm this punishment");
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);

                                } else if (plugin.getReportAbuse().get(target.getUniqueId().toString()) >= 5) {

                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + target.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                    player.sendMessage("Rule " + ChatColor.YELLOW + "Disrespect " + ChatColor.GRAY + "(ID: 2)");
                                    int count = plugin.getDisrespect().get(target.getUniqueId().toString()) + 1;
                                    player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 1d");
                                    player.sendMessage(ChatColor.WHITE.toString() + ChatColor.BOLD + "/punish " + target.getName() + " 1 confirm " + ChatColor.AQUA + ChatColor.BOLD + "to confirm this punishment");
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                }
                            }
                        } //Inappropriate Language
                        else if (args[1].equalsIgnoreCase("3")) {
                            if (!plugin.getDisrespect().containsKey(target.getUniqueId().toString())) {
                                player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + target.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                player.sendMessage("Rule " + ChatColor.YELLOW + "Inapp. Language " + ChatColor.GRAY + "(ID: 3)");
                                player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + plugin.getDisrespect().get(target.getUniqueId().toString()) + ChatColor.WHITE + " for this player, which will result in ");
                                player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 15m");
                                player.sendMessage(ChatColor.WHITE.toString() + ChatColor.BOLD + "/punish " + target.getName() + " 3 confirm " + ChatColor.AQUA + ChatColor.BOLD + "to confirm this punishment");
                                player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                            } else {
                                if (plugin.getDisrespect().get(target.getUniqueId().toString()) == 2) {
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + target.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                    player.sendMessage("Rule " + ChatColor.YELLOW + "Inapp. Language " + ChatColor.GRAY + "(ID: 3)");
                                    player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + plugin.getDisrespect().get(target.getUniqueId().toString()) + ChatColor.WHITE + " for this player, which will result in ");
                                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 30m");
                                    player.sendMessage(ChatColor.WHITE.toString() + ChatColor.BOLD + "/punish " + target.getName() + " 3 confirm " + ChatColor.AQUA + ChatColor.BOLD + "to confirm this punishment");
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                } else if (plugin.getDisrespect().get(target.getUniqueId().toString()) == 3 || plugin.getDisrespect().get(target.getUniqueId().toString()) == 4) {
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + target.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                    player.sendMessage("Rule " + ChatColor.YELLOW + "Inapp. Language " + ChatColor.GRAY + "(ID: 3)");
                                    player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + plugin.getDisrespect().get(target.getUniqueId().toString()) + ChatColor.WHITE + " for this player, which will result in ");
                                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 1h");
                                    player.sendMessage(ChatColor.WHITE.toString() + ChatColor.BOLD + "/punish " + target.getName() + " 3 confirm " + ChatColor.AQUA + ChatColor.BOLD + "to confirm this punishment");
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                } else if (plugin.getDisrespect().get(target.getUniqueId().toString()) > 4) {
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + target.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                    player.sendMessage("Rule " + ChatColor.YELLOW + "Inapp. Language " + ChatColor.GRAY + "(ID: 3)");
                                    player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + plugin.getDisrespect().get(target.getUniqueId().toString()) + ChatColor.WHITE + " for this player, which will result in ");
                                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 1d");
                                    player.sendMessage(ChatColor.WHITE.toString() + ChatColor.BOLD + "/punish " + target.getName() + " 3 confirm " + ChatColor.AQUA + ChatColor.BOLD + "to confirm this punishment");
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                }
                            }
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
                    if (player.hasPermission("kingdoms.mod.punish")
                            || player.hasPermission("kingdoms.srmod.punish")
                            || player.hasPermission("kingdoms.admin.punish")) {
                        if (args.length == 0) {
                            //todo: list available punishments
                            player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "8" + ChatColor.WHITE + " Discrimination");
                            player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "9" + ChatColor.WHITE + " Threats");
                            player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "10" + ChatColor.WHITE + " DDOS Threats");
                            player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "11" + ChatColor.WHITE + " Dox Threats");
                        } else if (args.length == 1) {
                            player.sendMessage(ChatColor.GOLD + "Usage: /punish <user> <reason>");
                        } else if (args.length == 2) {
                            target = Bukkit.getPlayer(args[0]);
                            if (target == null) {
                                player.sendMessage(args[0] + " is not online");
                                return true;
                            }
                        } else if (args.length > 2) {
                            player.sendMessage(ChatColor.GOLD + "Usage: /punish <user> <reason>");
                        }
                    } else if (player.hasPermission("kingdoms.srmod.punish")
                            || player.hasPermission("kingdoms.admin.punish")) {
                        //TODO: put srmod/admin punishments here

                    }
                } else if (args.length == 3) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        player.sendMessage(args[0] + " is not online");
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("confirm")) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (args[1].equalsIgnoreCase("1")) {
                                int count = plugin.getReportAbuse().get(target.getUniqueId().toString()) + 1;
                                //todo: make this work for all cases/punishments
//                                if (plugin.getStaff().containsKey(p.getUniqueId().toString())) {
//                                    p.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "[ENF] " + ChatColor.RED + ChatColor.BOLD + "[!] "
//                                            + ChatColor.WHITE + target.getName() + ChatColor.GRAY + " was muted by " + ChatColor.WHITE + player.getName()
//                                            + ChatColor.GRAY + " for " + ChatColor.GOLD + "Report Abuse #" + count + ChatColor.RED + "(Expires: 30m)");
//                                }
                                if (!plugin.getReportAbuse().containsKey(target.getUniqueId().toString())) {
                                    plugin.getReportAbuse().put(target.getUniqueId().toString(), 0);
                                } else {
                                    plugin.getReportAbuse().put(target.getUniqueId().toString(), plugin.getReportAbuse().get(target.getUniqueId().toString()) + 1);
                                }
                                if (plugin.getReportAbuse().get(target.getUniqueId().toString()) < 5) {
                                    p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                                    p.sendMessage(centerMessage(target.getName() + ChatColor.YELLOW + " was warned"));
                                    p.sendMessage(centerMessage(ChatColor.YELLOW + "for " + ChatColor.WHITE + "Report Abuse - Violation #" + plugin.getReportAbuse().get(target.getUniqueId().toString())));
                                    p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                                } else if (plugin.getReportAbuse().get(target.getUniqueId().toString()) >= 5) {
                                    p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                                    p.sendMessage(centerMessage(target.getName() + ChatColor.YELLOW + " was muted"));
                                    p.sendMessage(centerMessage(ChatColor.YELLOW + "for " + ChatColor.WHITE + "Report Abuse - Violation #" + plugin.getReportAbuse().get(target.getUniqueId().toString())));
                                    p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                                }
                            }
                            if (args[1].equalsIgnoreCase("2")) {

                                if (!plugin.getDisrespect().containsKey(target.getUniqueId().toString())) {
                                    plugin.getDisrespect().put(target.getUniqueId().toString(), 0);
                                } else {
                                    plugin.getDisrespect().put(target.getUniqueId().toString(), plugin.getDisrespect().get(target.getUniqueId().toString()) + 1);
                                }
                                if (plugin.getReportAbuse().get(target.getUniqueId().toString()) > 0) {
                                    p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                                    p.sendMessage(centerMessage(target.getName() + ChatColor.YELLOW + " was muted"));
                                    p.sendMessage(centerMessage(ChatColor.YELLOW + "for " + ChatColor.WHITE + "Disrespect - Violation #" + plugin.getDisrespect().get(target.getUniqueId().toString())));
                                    p.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + sep);
                                }
                            }
                        }
                    }
                } else if (args.length > 3) {
                    player.sendMessage(ChatColor.GOLD + "Usage: /punish <user> <reason>");
                }

                plugin.savePluginData();
            }
        }
        return true;
    }

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
        switch (c) {
            case 'I':
            case 'i':
            case 'l':
            case '.':
            case ':':
                return 2;
            case 't':
            case 'k':
            case ',':
                return 3;
            case 'f':
            case 'r':
                return 4;
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'g':
            case 'h':
            case 'j':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 's':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
            case 'z':
            case '-':
            case '_':
            case '1':
            case '7':
                return 5;
            case ' ':
                return 6; // Adjust as necessary to better fit space width
            default:
                return 6; // Default width for other characters
        }
    }

    @EventHandler
    public void onClick(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();


    }
}
