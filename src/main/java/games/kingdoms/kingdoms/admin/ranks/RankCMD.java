package games.kingdoms.kingdoms.admin.ranks;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RankCMD implements CommandExecutor {

    private final Kingdoms plugin;

    public RankCMD(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        switch (args.length) {
            case 0:
            case 1:
            case 2:
                if (sender.hasPermission("kingdoms.setrank.default") || sender.hasPermission("kingdoms.setrank.vip") || sender.hasPermission("kingdoms.setrank.hero") || sender.hasPermission("kingdoms.setrank.jrmod") || sender.hasPermission("kingdoms.setrank.mod") || sender.hasPermission("kingdoms.setrank.srmod") || sender.hasPermission("kingdoms.setrank.jradmin") || sender.hasPermission("kingdoms.setrank.admin")) {
                    player.sendMessage(ChatColor.GOLD + "Usage: /rank set <player> <rank>");
                    return true;
                }
                break;
            case 3:
                Player target = Bukkit.getPlayer(args[1]);
                if (sender.hasPermission("kingdoms.setrank.default") || sender.hasPermission("kingdoms.setrank.vip") || sender.hasPermission("kingdoms.setrank.hero") || sender.hasPermission("kingdoms.setrank.jrmod") || sender.hasPermission("kingdoms.setrank.mod") || sender.hasPermission("kingdoms.setrank.srmod") || sender.hasPermission("kingdoms.setrank.jradmin") || sender.hasPermission("kingdoms.setrank.admin")) {
                    if (target == null) {
                        sender.sendMessage(args[1] + ChatColor.RED + " is not online");
                        return true;
                    } else {
                        if (args[0].equalsIgnoreCase("set")) {

                            if (!plugin.getPlayerRank().containsKey(target.getUniqueId().toString())) {
                                plugin.getPlayerRank().put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                            }

                            if (args[2].equalsIgnoreCase("default")) {
                                if (sender.hasPermission("kingdoms.setrank.default")) {
                                    if (!target.hasPermission("kingdoms.rank.kick.bypass")) {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to DEFAULT rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);

                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to DEFAULT rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to DEFAULT rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to DEFAULT rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to DEFAULT rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                    } else {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);

                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                    }
                                }
                            }

                            if (args[2].equalsIgnoreCase("vip")) {
                                if (sender.hasPermission("kingdoms.setrank.vip")) {
                                    if (!target.hasPermission("kingdoms.rank.kick.bypass")) {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to VIP rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to VIP rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to VIP rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to VIP rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to VIP rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            return true;
                                        } else if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                        }
                                    } else {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            return true;
                                        } else if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                        }
                                    }
                                }
                            }

                            if (args[2].equalsIgnoreCase("hero")) {
                                if (sender.hasPermission("kingdoms.setrank.hero")) {
                                    if (!target.hasPermission("kingdoms.rank.kick.bypass")) {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to HERO rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to HERO rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to HERO rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to HERO rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to HERO rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            return true;
                                        }
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                        }
                                    } else {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            return true;
                                        }
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                        }
                                    }
                                }
                            }

                            if (args[2].equalsIgnoreCase("youtube")) {
                                if (sender.hasPermission("kingdoms.setrank.youtube")) {
                                    if (!target.hasPermission("kingdoms.rank.kick.bypass")) {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to YOUTUBE rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to YOUTUBE rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to YOUTUBE rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to YOUTUBE rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to YOUTUBE rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.RED.toString() + ChatColor.BOLD + "YT")) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                    } else {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.RED.toString() + ChatColor.BOLD + "YT")) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                    }
                                }
                            }

                            if (args[2].equalsIgnoreCase("jrmod")) {
                                if (sender.hasPermission("kingdoms.setrank.jrmod")) {
                                    if (!target.hasPermission("kingdoms.rank.kick.bypass")) {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to JRMOD rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to JRMOD rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to JRMOD rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to JRMOD rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                            return true;
                                        }
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                        }
                                    } else {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                            return true;
                                        }
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                        }
                                    }
                                }
                            }

                            if (args[2].equalsIgnoreCase("mod")) {
                                if (sender.hasPermission("kingdoms.setrank.mod")) {
                                    if (!target.hasPermission("kingdoms.rank.kick.bypass")) {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "MOD");
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to MOD rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to MOD rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "MOD");
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to MOD rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                            return true;
                                        }
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "MOD");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                            return true;
                                        }
                                    } else {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "MOD");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "MOD");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                            return true;
                                        }
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "MOD");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                            return true;
                                        }
                                    }
                                }
                            }

                            if (args[2].equalsIgnoreCase("srmod")) {
                                if (sender.hasPermission("kingdom.setrank.srmod")) {
                                    if (!target.hasPermission("kingdoms.rank.kick.bypass")) {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "SRMOD");
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to SRMOD rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.SRMOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "SRMOD");
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to SRMOD rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.SRMOD);
                                            return true;
                                        }
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "SRMOD");
                                            target.kickPlayer("You were demoted to SRMOD rank");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD);
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD);
                                            return true;
                                        }
                                    } else {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "SRMOD");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.SRMOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "SRMOD");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.SRMOD);
                                            return true;
                                        }
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "SRMOD");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD);
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD);
                                            return true;
                                        }
                                    }
                                }
                            }

                            if (args[2].equalsIgnoreCase("jradmin")) {
                                if (sender.hasPermission("kingdoms.setrank.jradmin")) {
                                    if (!target.hasPermission("kingdoms.rank.kick.bypass")) {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRADMIN");
                                            target.setOp(false);
                                            target.kickPlayer("You were demoted to JRADMIN rank");
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                            return true;
                                        }
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                            return true;
                                        }
                                    } else {
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRADMIN");
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                            return true;
                                        }
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                        } else {
                                            player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                            return true;
                                        }
                                    }
                                }
                            }

                            if (args[2].equalsIgnoreCase("admin")) {
                                if (sender.hasPermission("kingdoms.setrank.admin")) {
                                    if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                        plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN);
                                        plugin.getStaff().put(target.getUniqueId().toString(), "ADMIN");
                                        target.setOp(false);
                                        player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.ADMIN);
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.ADMIN);
                                    } else {
                                        player.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.ADMIN);
                                        return true;
                                    }
                                }
                            }
                            plugin.savePluginData();
                        }
                    }

                } else {
                    MessageManager.playerBad(player, "You do not have permission to execute this command");
                }
                break;
        }
        return true;
    }
}