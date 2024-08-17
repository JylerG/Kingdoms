package games.kingdoms.kingdoms.publiccmds.whisper;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import games.kingdoms.kingdoms.admin.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WhisperCommand implements CommandExecutor {

    final Kingdoms plugin = Kingdoms.getPlugin();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                MessageManager.playerBad(player, target + " is not online");
                return true;
            }

            if (target == player) {
                MessageManager.playerBad(player, "You can not send yourself a DM");
                return true;
            }

            if (args.length < 2) {
                player.sendMessage(ChatColor.GOLD + "Usage: /whisper <player> <msg>");
                return true;
            }

            StringBuilder message = new StringBuilder();

            // Append args from the second element onwards into the StringBuilder
            for (int i = 1; i < args.length; i++) {
                message.append(args[i]);
                if (i < args.length - 1) {
                    message.append(" ");
                }
            }

            if (plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ": " + message);
                    target.sendMessage(player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.GREEN + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.AQUA + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.GOLD + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                }
            } else if (plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ": " + message);
                    target.sendMessage(ChatColor.GREEN + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.GREEN + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.GREEN + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.AQUA + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.GREEN + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.GREEN + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.GREEN + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.GOLD + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.GREEN + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.GREEN + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.GREEN + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                }
            } else if (plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ": " + message);
                    target.sendMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.GREEN + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.AQUA + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.GOLD + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                }
            } else if (plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ": " + message);
                    target.sendMessage(ChatColor.DARK_AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.GREEN + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.AQUA + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.GOLD + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_AQUA + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                }
            } else if (plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ": " + message);
                    target.sendMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.GREEN + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.AQUA + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.GOLD + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                }
            } else if (plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ": " + message);
                    target.sendMessage(ChatColor.GOLD + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.GOLD + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.GOLD + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.GOLD + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.GOLD + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.GOLD + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.GOLD + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.GOLD + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                }
            } else if (plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                }
            } else if (plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                //TODO: populate this
                if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.WHITE + target.getDisplayName() + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.GREEN + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.AQUA + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_AQUA + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.YELLOW + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.GOLD + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                } else if (plugin.getPlayerRank().get(target.getUniqueId().toString())
                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                    player.sendMessage(ChatColor.GRAY.toString() + ChatColor.BOLD + "You > " + ChatColor.DARK_RED + target.getDisplayName() + ChatColor.WHITE + ": " + message);
                    target.sendMessage(ChatColor.DARK_RED + player.getDisplayName() + ChatColor.GRAY.toString() + ChatColor.BOLD + " > You" + ChatColor.WHITE + ": " + message);
                }
            }
        }
        return true;
    }
}