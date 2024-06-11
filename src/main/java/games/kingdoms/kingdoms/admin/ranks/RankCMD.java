package games.kingdoms.kingdoms.admin.ranks;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
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
                    sender.sendMessage(ChatColor.GOLD + "Usage: /rank set <player> <rank>");
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
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        if (args[0].equalsIgnoreCase("set")) {

                            if (!plugin.getPlayerRank().containsKey(target.getUniqueId().toString())) {
                                plugin.getPlayerRank().put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                            }

                            if (args[2].equalsIgnoreCase("default")) {
                                if (sender.hasPermission("kingdoms.setrank.default")) {
                                    if (!plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                                        Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set default");
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);

                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                            return true;
                                        }
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                        return true;
                                    }
                                }
                            }

                            if (args[2].equalsIgnoreCase("vip")) {
                                if (sender.hasPermission("kingdoms.setrank.vip")) {
                                    if (!plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                                        Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set vip");
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                            return true;
                                        }
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                        return true;
                                    }
                                }
                            }

                            if (args[2].equalsIgnoreCase("hero")) {
                                if (sender.hasPermission("kingdoms.setrank.hero")) {
                                    if (!plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                                        Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set hero");
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                            return true;
                                        }
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                        return true;
                                    }

                                }
                            }

                            if (args[2].equalsIgnoreCase("youtube")) {
                                if (sender.hasPermission("kingdoms.setrank.youtube")) {
                                    if (!plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.RED.toString() + ChatColor.BOLD + Rank.YOUTUBE)) {
                                        Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set youtube");
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                            return true;
                                        }
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.RED + ChatColor.BOLD + "YOUTUBE");
                                        return true;
                                    }
                                }
                            }


                            if (args[2].equalsIgnoreCase("jrmod")) {
                                if (sender.hasPermission("kingdoms.setrank.jrmod")) {
                                    if (!plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                        Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set jrmod");
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                            return true;
                                        }
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                    }
                                }
                            }

                            if (args[2].equalsIgnoreCase("mod")) {
                                if (sender.hasPermission("kingdoms.setrank.mod")) {
                                    if (!plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                        Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set mod");
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "MOD");
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                                            plugin.getStaff().remove(target.getUniqueId().toString());
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "MOD");
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                            return true;
                                        }
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                        return true;
                                    }
                                }
                            }

                            if (args[2].equalsIgnoreCase("srmod")) {
                                if (sender.hasPermission("kingdom.setrank.srmod")) {
                                    if (!plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                        Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set srmod");
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD);
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "SRMOD");
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.SRMOD);
                                            return true;
                                        }
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "SRMOD");
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.SRMOD);
                                            return true;
                                        }
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD);
                                        return true;
                                    }
                                }
                            }


                            if (args[2].equalsIgnoreCase("jradmin")) {
                                if (sender.hasPermission("kingdoms.setrank.jradmin")) {
                                    if (!plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                        Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set jradmin");
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                        if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "JRADMIN");
                                            target.setOp(false);
                                            sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName() + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                            return true;
                                        }
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                        return true;
                                    }
                                }
                            }

                            if (args[2].equalsIgnoreCase("admin")) {
                                if (sender.hasPermission("kingdoms.setrank.admin")) {
                                    if (!plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                        Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set admin");
                                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.ADMIN);
                                        if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN);
                                            plugin.getStaff().put(target.getUniqueId().toString(), "ADMIN");
                                            target.setOp(false);
                                        }
                                    } else {
                                        sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.ADMIN);
                                        return true;
                                    }
                                    plugin.savePluginData();
                                }
                            }
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
