package games.kingdoms.kingdoms.admin.punishCMD;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class PunishCommand implements CommandExecutor {

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

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String dash = "-";
        String ruleStart = ChatColor.GRAY.toString() + ChatColor.BOLD + "—";
                String sep = dash + dash + dash + dash + dash + dash + dash + dash + dash + dash
                + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash
                + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash
                + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash;
        if (sender instanceof Player player) {
            if (player.hasPermission("kingdoms.jrmod.punish")
                    || player.hasPermission("kingdoms.mod.punish")
                    || player.hasPermission("kingdoms.admin.punish")) {
                if (args.length == 0) {
                    //todo: list available punishments
                    player.sendMessage(ChatColor.AQUA.toString() + ChatColor.BOLD + "———" + ChatColor.WHITE + ChatColor.BOLD + " Server Rules " + ChatColor.AQUA + ChatColor.BOLD + "———");
                    player.sendMessage(ruleStart + ChatColor.WHITE + " Abusing /Report");
                    player.sendMessage(ruleStart + ChatColor.WHITE + " Disrespect");
                    player.sendMessage(ruleStart + ChatColor.WHITE + " Inapp. Language");
                    player.sendMessage(ruleStart + ChatColor.WHITE + " IP Adverts");
                    player.sendMessage(ruleStart + ChatColor.WHITE + " Media Adverts");
                    player.sendMessage(ruleStart + ChatColor.WHITE + " Soliciting");
                    player.sendMessage(ruleStart + ChatColor.WHITE + " Spam");
                    return true;
                } else if (args.length == 1) {
                    player.sendMessage(ChatColor.GOLD + "Usage: /punish <user> <reason>");
                    return true;
                } else if (args.length == 2) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                    if (target == null) {
                        player.sendMessage(args[0] + ChatColor.RED + " has never played on " + ChatColor.GOLD + "Kingdoms");
                        return true;
                    }
                    if ((spam.get(target.getUniqueId().toString()) == 0
                            && threats.get(target.getUniqueId().toString()) == 0
                            && language.get(target.getUniqueId().toString()) == 0
                            && disrespect.get(target.getUniqueId().toString()) == 0
                            && ipAdverts.get(target.getUniqueId().toString()) == 0
                            && soliciting.get(target.getUniqueId().toString()) == 0
                            && reportAbuse.get(target.getUniqueId().toString()) == 0
                            && mediaAdverts.get(target.getUniqueId().toString()) == 0
                            && discrimination.get(target.getUniqueId().toString()) == 0
                            //todo: Add additional hashmaps here
                    )) {
                        player.sendMessage(target.getName() + ChatColor.RED + " has no punishment logs");
                        return true;
                    }

                    if (args[1].equalsIgnoreCase("get")) {
                        //todo: add all punishments below here
                        player.sendMessage(ChatColor.DARK_GREEN + target.getName() + ChatColor.GREEN + "'s Punishments");
                        if (spam.get(target.getUniqueId().toString()) != 0) {
                            player.sendMessage(ChatColor.GRAY + "— " + ChatColor.WHITE + "Spam " + ChatColor.GOLD + spam.get(target.getUniqueId().toString()));
                        }
                        if (threats.get(target.getUniqueId().toString()) != 0) {
                            player.sendMessage(ChatColor.GRAY + "— " + ChatColor.WHITE + "Threats " + ChatColor.GOLD + threats.get(target.getUniqueId().toString()));
                        }
                        if (language.get(target.getUniqueId().toString()) != 0) {
                            player.sendMessage(ChatColor.GRAY + "— " + ChatColor.WHITE + "Language " + ChatColor.GOLD + language.get(target.getUniqueId().toString()));
                        }
                        if (disrespect.get(target.getUniqueId().toString()) != 0) {
                            player.sendMessage(ChatColor.GRAY + "— " + ChatColor.WHITE + "Disrespect " + ChatColor.GOLD + disrespect.get(target.getUniqueId().toString()));
                        }
                        if (ipAdverts.get(target.getUniqueId().toString()) != 0) {
                            player.sendMessage(ChatColor.GRAY + "— " + ChatColor.GOLD + "Ip Adverts " + ChatColor.WHITE + ipAdverts.get(target.getUniqueId().toString()));
                        }
                        if (soliciting.get(target.getUniqueId().toString()) != 0) {
                            player.sendMessage(ChatColor.GRAY + "— " + ChatColor.GOLD + "Soliciting " + ChatColor.WHITE + soliciting.get(target.getUniqueId().toString()));
                        }
                        if (reportAbuse.get(target.getUniqueId().toString()) != 0) {
                            player.sendMessage(ChatColor.GRAY + "— " + ChatColor.GOLD + "Report Abuse " + ChatColor.WHITE + reportAbuse.get(target.getUniqueId().toString()));
                        }
                        if (mediaAdverts.get(target.getUniqueId().toString()) != 0) {
                            player.sendMessage(ChatColor.GRAY + "— " + ChatColor.GOLD + "Media Adverts " + ChatColor.WHITE + mediaAdverts.get(target.getUniqueId().toString()));
                        }
                        if (discrimination.get(target.getUniqueId().toString()) != 0) {
                            player.sendMessage(ChatColor.GRAY + "— " + ChatColor.GOLD + "Discrimination " + ChatColor.WHITE + discrimination.get(target.getUniqueId().toString()));
                        }
                        return true;
                    }

                    if (args[1].equalsIgnoreCase("reset") && target != null && player.hasPermission("kingdoms.admin.punish.reset")) {
                        reportAbuse.put(target.getUniqueId().toString(), 0);
                        discrimination.put(target.getUniqueId().toString(), 0);
                        disrespect.put(target.getUniqueId().toString(), 0);
                        ipAdverts.put(target.getUniqueId().toString(), 0);
                        mediaAdverts.put(target.getUniqueId().toString(), 0);
                        language.put(target.getUniqueId().toString(), 0);
                        soliciting.put(target.getUniqueId().toString(), 0);
                        spam.put(target.getUniqueId().toString(), 0);
                        threats.put(target.getUniqueId().toString(), 0);
                        player.sendMessage(ChatColor.GREEN + "You reset " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + "'s punishments to " + ChatColor.WHITE + 0);
                        return true;
                    }

                    if (player == target) {
                        MessageManager.playerBad(player, "You cannot punish yourself");
                        return true;
                    }

                    plugin.getPlayerToPunish().put(player.getUniqueId().toString(), target.getUniqueId().toString());

                    //Report Abuse
                    if (args[1].equalsIgnoreCase("report")
                            || args[1].equalsIgnoreCase("reportabuse")
                            || args[1].equalsIgnoreCase("report abuse")
                            || args[1].equalsIgnoreCase("abuse")
                            || args[1].equalsIgnoreCase("1")
                    ) {
                        OfflinePlayer finalTarget = target;
                        if (!plugin.getReportAbuse().containsKey(finalTarget.getUniqueId().toString())) {
                            plugin.getReportAbuse().put(finalTarget.getUniqueId().toString(), 0);
                        }
                        int count = plugin.getReportAbuse().get(finalTarget.getUniqueId().toString()) + 1;
                        if (count == 1) {

                            player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                            player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + finalTarget.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                            player.sendMessage("Rule " + ChatColor.YELLOW + "Report Abuse " + ChatColor.GRAY + "(ID: 1)");
                            player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                            player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Warning #1");
                            TextComponent confirm = new TextComponent("[Click to confirm this punishment.]");
                            confirm.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                            confirm.setBold(true);
                            confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " abuse"));
                            player.spigot().sendMessage(confirm);
                            player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                            return true;
                        } else {
                            if (count > 1 && count < 5) {

                                player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                                player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + finalTarget.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                                player.sendMessage("Rule " + ChatColor.YELLOW + "Report Abuse " + ChatColor.GRAY + "(ID: 1)");
                                player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                                player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Warning #" + count);
                                TextComponent confirm = new TextComponent("[Click to confirm this punishment.]");
                                confirm.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                                confirm.setBold(true);
                                confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " abuse"));
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
                                confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " abuse"));
                            }
                        }
                        return true;
                    }
                    //Disrespect
                    else if (args[1].equalsIgnoreCase("disrespect")
                            || args[1].equalsIgnoreCase("2")) {
                        OfflinePlayer finalTarget = target;
                        if (!plugin.getDisrespect().containsKey(finalTarget.getUniqueId().toString())) {
                            plugin.getDisrespect().put(finalTarget.getUniqueId().toString(), 0);
                        }
                        int count = plugin.getDisrespect().get(finalTarget.getUniqueId().toString()) + 1;
                        if (count == 1) {

                            player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                            player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + finalTarget.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                            player.sendMessage("Rule " + ChatColor.YELLOW + "Disrespect " + ChatColor.GRAY + "(ID: 2)");
                            player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                            player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 30m");
                            TextComponent confirm = new TextComponent("[Click to confirm this punishment.]");
                            confirm.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                            confirm.setBold(true);
                            confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " disrespect"));
                            player.spigot().sendMessage(confirm);
                            player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);

                        } else if (count > 1 && count < 5) {

                            player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                            player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + finalTarget.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                            player.sendMessage("Rule " + ChatColor.YELLOW + "Disrespect " + ChatColor.GRAY + "(ID: 2)");
                            player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                            player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 1h");
                            TextComponent confirm = new TextComponent("[Click to confirm this punishment.]");
                            confirm.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                            confirm.setBold(true);
                            confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " disrespect"));
                            player.spigot().sendMessage(confirm);
                            player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);

                        } else if (count >= 5) {

                            player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                            player.sendMessage(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Punishing " + ChatColor.WHITE + ChatColor.BOLD + finalTarget.getName() + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "...");
                            player.sendMessage("Rule " + ChatColor.YELLOW + "Disrespect " + ChatColor.GRAY + "(ID: 2)");
                            player.sendMessage("This is offense " + ChatColor.YELLOW + "#" + count + ChatColor.WHITE + " for this player, which will result in ");
                            player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "-> " + ChatColor.RED + "Mute for 1d");
                            TextComponent confirm = new TextComponent("[Click to confirm this punishment.]");
                            confirm.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                            confirm.setBold(true);
                            confirm.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/confirm " + finalTarget.getName() + " disrespect"));
                            player.spigot().sendMessage(confirm);
                            player.sendMessage(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + sep);
                        }
                        return true;
                    }
                    //Inappropriate Language
                    else if (args[1].equalsIgnoreCase("language")
                            || args[1].equalsIgnoreCase("lang")
                            || args[1].equalsIgnoreCase("3")
                    ) {
                        OfflinePlayer finalTarget = target;
                        if (!plugin.getLanguage().containsKey(finalTarget.getUniqueId().toString())) {
                            plugin.getLanguage().put(finalTarget.getUniqueId().toString(), 0);
                        }
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
                            } else if (count > 4) {
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
                        return true;
                    }
                    //IP Adverts
                    else if (args[1].equalsIgnoreCase("4")) {

                        return true;
                    } //Media Adverts
                    else if (args[1].equalsIgnoreCase("5")) {

                        return true;
                    } //Soliciting
                    else if (args[1].equalsIgnoreCase("6")) {

                        return true;
                    } //Spam
                    else if (args[1].equalsIgnoreCase("7")) {

                        return true;
                    }
                    //todo: put mod/srmod/admin punishments below here
                    if (player.hasPermission("kingdoms.mod.punish")
                            || player.hasPermission("kingdoms.admin.punish")) {
                        if (args.length == 0) {
                            //todo: list available punishments
                            player.sendMessage(ruleStart + ChatColor.WHITE + " Discrimination");
                            player.sendMessage(ruleStart + ChatColor.WHITE + " Threats");
                            player.sendMessage(ruleStart + ChatColor.WHITE + " DDOS Threats");
                            player.sendMessage(ruleStart + ChatColor.WHITE + " Dox Threats");
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
                        //todo: put admin punishments below here
                    } else if (player.hasPermission("kingdoms.admin.punish")) {
                        if (args.length == 0) {
                            //todo: list available punishments for admins
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
                    }
                }
            }
        }
        return true;
    }

    private static final int CHAT_WIDTH = 600; // Adjust this value as needed to fit your server's chat width

    public String centerMessage(String message) {
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
