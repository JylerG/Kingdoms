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
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class PunishCommand implements CommandExecutor, Listener {

    private final Kingdoms plugin = Kingdoms.getPlugin();

    final Map<Integer, Runnable> map = new LinkedHashMap<>();
    final AtomicInteger nextId = new AtomicInteger();

    private TextComponent component = new TextComponent();

    int addPunishment(Runnable punishment) {
        // get the new id
        final int id = nextId.incrementAndGet();
        // add punishment to the map at new id
        map.put(id, punishment);
        // return the id
        return id;
    }

    public TextComponent getComponent() {
        return component;
    }

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

                    if (args[1].equalsIgnoreCase("reset") && player.hasPermission("kingdoms.admin.punish.reset")) {
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
                        return true;
                    }

                    //Add player to punish hashmap
                    plugin.getPlayerToPunish().put(player.getUniqueId().toString(), target.getUniqueId().toString());

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        //Report Abuse
                        if (args[1].equalsIgnoreCase("1")) {
                            Player finalTarget = target;
                            int count = plugin.getReportAbuse().get(finalTarget.getUniqueId().toString()) + 1;
                            if (!plugin.getReportAbuse().containsKey(finalTarget.getUniqueId().toString()) || count == 1) {

                                player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + finalTarget.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                player.sendMessage("Rule " + ChatColor.YELLOW + "Report Abuse " + ChatColor.GRAY + "(ID: 1)");
                                player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                                player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Warning #1");
                                TextComponent confirm = new TextComponent("[Click to confirm this punishment.]");
                                confirm.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                                confirm.setBold(true);
                                confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " 1"));
                                player.spigot().sendMessage(confirm);
                                player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                            } else {
                                if (count >= 1 && count < 5) {

                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + finalTarget.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                    player.sendMessage("Rule " + ChatColor.YELLOW + "Report Abuse " + ChatColor.GRAY + "(ID: 1)");
                                    player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Warning #" + count);
                                    TextComponent confirm = new TextComponent("[Click to confirm this punishment.]");
                                    confirm.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                                    confirm.setBold(true);
                                    confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " 1"));
                                    player.spigot().sendMessage(confirm);
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);

                                } else if (count >= 5) {

                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + finalTarget.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                    player.sendMessage("Rule " + ChatColor.YELLOW + "Report Abuse " + ChatColor.GRAY + "(ID: 1)");
                                    player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 30m");
                                    TextComponent confirm = new TextComponent("[Click to confirm this punishment.]");
                                    confirm.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                                    confirm.setBold(true);
                                    player.spigot().sendMessage(confirm);
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                    confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " 1"));
                                }
                            }
                        }
                        //Disrespect
                        else if (args[1].equalsIgnoreCase("2")) {
                            Player finalTarget = target;
                            int count = plugin.getDisrespect().get(finalTarget.getUniqueId().toString()) + 1;
                            if (!plugin.getDisrespect().containsKey(finalTarget.getUniqueId().toString()) || count == 1) {

                                player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + finalTarget.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                player.sendMessage("Rule " + ChatColor.YELLOW + "Disrespect " + ChatColor.GRAY + "(ID: 2)");
                                player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                                player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 30m");
                                TextComponent confirm = new TextComponent("[Click to confirm this punishment.]");
                                confirm.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                                confirm.setBold(true);
                                confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " 2"));
                                player.spigot().sendMessage(confirm);
                                player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);

                            } else {

                                if (plugin.getDisrespect().get(finalTarget.getUniqueId().toString()) > 1 && plugin.getDisrespect().get(finalTarget.getUniqueId().toString()) < 5) {

                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + finalTarget.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                    player.sendMessage("Rule " + ChatColor.YELLOW + "Disrespect " + ChatColor.GRAY + "(ID: 2)");
                                    player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 1h");
                                    TextComponent confirm = new TextComponent("[Click to confirm this punishment.]");
                                    confirm.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                                    confirm.setBold(true);
                                    confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " 2"));
                                    player.spigot().sendMessage(confirm);
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);

                                } else if (plugin.getDisrespect().get(finalTarget.getUniqueId().toString()) >= 5) {

                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + finalTarget.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                    player.sendMessage("Rule " + ChatColor.YELLOW + "Disrespect " + ChatColor.GRAY + "(ID: 2)");
                                    player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 1d");
                                    TextComponent confirm = new TextComponent("[Click to confirm this punishment.]");
                                    confirm.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                                    confirm.setBold(true);
                                    confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " 2"));
                                    player.spigot().sendMessage(confirm);
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                }
                            }
                        } //Inappropriate Language
                        else if (args[1].equalsIgnoreCase("3")) {
                            Player finalTarget = target;
                            int count = plugin.getLanguage().get(finalTarget.getUniqueId().toString()) + 1;
                            if (!plugin.getLanguage().containsKey(finalTarget.getUniqueId().toString()) || count == 1) {
                                player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + finalTarget.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                player.sendMessage("Rule " + ChatColor.YELLOW + "Inapp. Language " + ChatColor.GRAY + "(ID: 3)");
                                player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                                player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 15m");
                                TextComponent confirm = new TextComponent("[Click to confirm this punishment.]");
                                confirm.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                                confirm.setBold(true);
                                confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " 3"));
                                player.spigot().sendMessage(confirm);
                                player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                            } else {
                                if (count == 2) {
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + finalTarget.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                    player.sendMessage("Rule " + ChatColor.YELLOW + "Inapp. Language " + ChatColor.GRAY + "(ID: 3)");
                                    player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 30m");
                                    TextComponent confirm = new TextComponent("[Click to confirm this punishment.]");
                                    confirm.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                                    confirm.setBold(true);
                                    confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " 3"));
                                    player.spigot().sendMessage(confirm);
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                } else if (count == 3 || count == 4) {
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + finalTarget.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                    player.sendMessage("Rule " + ChatColor.YELLOW + "Inapp. Language " + ChatColor.GRAY + "(ID: 3)");
                                    player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 1h");
                                    TextComponent confirm = new TextComponent("[Click to confirm this punishment.]");
                                    confirm.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                                    confirm.setBold(true);
                                    confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " 3"));
                                    player.spigot().sendMessage(confirm);
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                } else if (plugin.getLanguage().get(finalTarget.getUniqueId().toString()) > 4) {
                                    player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                    player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + finalTarget.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                    player.sendMessage("Rule " + ChatColor.YELLOW + "Inapp. Language " + ChatColor.GRAY + "(ID: 3)");
                                    player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 1d");
                                    TextComponent confirm = new TextComponent("[Click to confirm this punishment.]");
                                    confirm.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                                    confirm.setBold(true);
                                    confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " 3"));
                                    player.spigot().sendMessage(confirm);
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
                    }
                }
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
}
