package games.kingdoms.kingdoms.admin.ranks;

import games.kingdoms.kingdoms.Kingdoms;
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
                    } else {
                        if (args[0].equalsIgnoreCase("set")) {

                            if (!plugin.getPlayerRank().containsKey(target.getUniqueId().toString())) {
                                plugin.getPlayerRank().put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                            }

                            if (args[2].equalsIgnoreCase("default")) {
                                if (sender.hasPermission("kingdoms.setrank.default")) {
                                    if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                                        plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                        plugin.getStaff().remove(target.getUniqueId().toString());
                                        target.setOp(false);
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                        return true;
                                    }
                                }
                            }

                            if (args[2].equalsIgnoreCase("vip")) {
                                if (sender.hasPermission("kingdoms.setrank.vip")) {
                                    if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                                        plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                        plugin.getStaff().remove(target.getUniqueId().toString());
                                        target.setOp(false);
                                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                        return true;
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
                                }
                            }

                            if (args[2].equalsIgnoreCase("hero")) {
                                if (sender.hasPermission("kingdoms.setrank.hero")) {
                                    if (!target.hasPermission("kingdoms.setrank.default") || !target.hasPermission("kingdoms.setrank.vip") || !target.hasPermission("kingdoms.setrank.hero") || !target.hasPermission("kingdoms.setrank.jrmod") || !target.hasPermission("kingdoms.setrank.mod") || !target.hasPermission("kingdoms.setrank.srmod") || !target.hasPermission("kingdoms.setrank.jradmin") || !target.hasPermission("kingdoms.setrank.admin")) {
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                        } else {
                                            sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                        }
                                    } else {
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO + ChatColor.LIGHT_PURPLE + " by " + ChatColor.DARK_RED + sender.getName());
                                        } else {
                                            sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                        }
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
                                }
                            }

                            if (args[2].equalsIgnoreCase("youtube")) {
                                if (sender.hasPermission("kingdoms.setrank.youtube")) {
                                    if (!target.hasPermission("kingdoms.setrank.default") || !target.hasPermission("kingdoms.setrank.vip") || !target.hasPermission("kingdoms.setrank.hero") || !target.hasPermission("kingdoms.setrank.jrmod") || !target.hasPermission("kingdoms.setrank.mod") || !target.hasPermission("kingdoms.setrank.srmod") || !target.hasPermission("kingdoms.setrank.jradmin") || !target.hasPermission("kingdoms.setrank.admin")) {
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(Rank.YOUTUBE.getColor().toString() + ChatColor.BOLD + Rank.YOUTUBE.getName() + Rank.YOUTUBE.getSecondaryColor() + ChatColor.BOLD + Rank.YOUTUBE.getSecondaryName())) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), Rank.YOUTUBE.getColor().toString() + ChatColor.BOLD + Rank.YOUTUBE.getName() + Rank.YOUTUBE.getSecondaryColor() + ChatColor.BOLD + Rank.YOUTUBE.getSecondaryName());
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getDisplayName() + ChatColor.LIGHT_PURPLE + "'s rank to " + Rank.YOUTUBE.getColor() + ChatColor.BOLD + Rank.YOUTUBE.getName() + Rank.YOUTUBE.getSecondaryColor() + ChatColor.BOLD + Rank.YOUTUBE.getSecondaryName());
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + Rank.YOUTUBE.getColor() + ChatColor.BOLD + Rank.YOUTUBE.getName() + Rank.YOUTUBE.getSecondaryColor() + ChatColor.BOLD + Rank.YOUTUBE.getSecondaryName());
                                        } else {
                                            sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + Rank.YOUTUBE.getColor() + ChatColor.BOLD + Rank.YOUTUBE.getName() + Rank.YOUTUBE.getSecondaryColor() + ChatColor.BOLD + Rank.YOUTUBE.getSecondaryName());
                                        }
                                    } else {
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(Rank.YOUTUBE.getColor().toString() + ChatColor.BOLD + Rank.YOUTUBE.getName() + Rank.YOUTUBE.getSecondaryColor() + ChatColor.BOLD + Rank.YOUTUBE.getSecondaryName())) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), Rank.YOUTUBE.getColor().toString() + ChatColor.BOLD + Rank.YOUTUBE.getName() + Rank.YOUTUBE.getSecondaryColor() + ChatColor.BOLD + Rank.YOUTUBE.getSecondaryName());
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getDisplayName() + ChatColor.LIGHT_PURPLE + "'s rank to " + Rank.YOUTUBE.getColor() + ChatColor.BOLD + Rank.YOUTUBE.getName() + Rank.YOUTUBE.getSecondaryColor() + ChatColor.BOLD + Rank.YOUTUBE.getSecondaryName());
                                            target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + Rank.YOUTUBE.getColor() + ChatColor.BOLD + Rank.YOUTUBE.getName() + Rank.YOUTUBE.getSecondaryColor() + ChatColor.BOLD + Rank.YOUTUBE.getSecondaryName() + ChatColor.LIGHT_PURPLE + " by " + ChatColor.DARK_RED + sender.getName());
                                        } else {
                                            sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + Rank.YOUTUBE.getColor() + ChatColor.BOLD + Rank.YOUTUBE.getName() + Rank.YOUTUBE.getSecondaryColor() + ChatColor.BOLD + Rank.YOUTUBE.getSecondaryName());
                                        }
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
                                }
                            }

                            if (args[2].equalsIgnoreCase("jrmod")) {
                                if (sender.hasPermission("kingdoms.setrank.jrmod")) {
                                    if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                        plugin.getStaff().put(target.getUniqueId().toString(), "jrmod");
                                        plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                        target.setOp(false);
                                        player.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getDisplayName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD + ChatColor.LIGHT_PURPLE + " by " + ChatColor.DARK_RED + sender.getName());
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already a " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                        return true;
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
                                }
                            }

                            if (args[2].equalsIgnoreCase("mod")) {
                                if (sender.hasPermission("kingdoms.setrank.mod")) {
                                    if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                        plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                                        plugin.getStaff().put(target.getUniqueId().toString(), "mod");
                                        target.setOp(false);
                                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getDisplayName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already a " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                        return true;
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
                                }
                            }

                            if (args[2].equalsIgnoreCase("srmod")) {
                                if (sender.hasPermission("kingdom.setrank.srmod")) {
                                    if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                        plugin.getStaff().put(target.getUniqueId().toString(), "srmod");
                                        plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
                                        target.setOp(false);
                                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getDisplayName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD);
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD);
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already a " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD);
                                        return true;
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
                                }
                            }

                            if (args[2].equalsIgnoreCase("jradmin")) {
                                if (sender.hasPermission("kingdoms.setrank.jradmin")) {
                                    if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                        plugin.getStaff().put(target.getUniqueId().toString(), "jradmin");
                                        plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
                                        target.setOp(false);
                                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getDisplayName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already a " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                        return true;
                                    }
                                }
                            }

                            if (args[2].equalsIgnoreCase("admin")) {
                                if (sender.hasPermission("kingdoms.setrank.admin")) {
                                    if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                        plugin.getStaff().put(target.getUniqueId().toString(), "admin");
                                        plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN);
                                        target.setOp(true);
                                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getDisplayName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.ADMIN);
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.ADMIN);
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already an " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.ADMIN);
                                        return true;
                                    }
                                } else {
                                    player.sendMessage(ChatColor.RED + "You do not have permission to use this command");
                                }
                            }
                        }
                    }
                }
                break;
        }
        return true;
    }
}