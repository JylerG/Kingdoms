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

    final Kingdoms plugin;

    public RankCMD(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            boolean isDefault = plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)
                    && player.hasPermission("kingdoms.setrank.fake");
            boolean isVIP = plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)
                    && player.hasPermission("kingdoms.setrank.fake");
            boolean isHero = plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)
                    && player.hasPermission("kingdoms.setrank.fake");
            boolean isYoutube = plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.RED.toString() + ChatColor.BOLD + Rank.YOUTUBE)
                    && player.hasPermission("kingdoms.setrank.fake");
            boolean isJrMod = plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)
                    && player.hasPermission("kingdoms.setrank.fake");
            boolean isMod = plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)
                    && player.hasPermission("kingdoms.setrank.fake");
            boolean isSrMod = plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)
                    && player.hasPermission("kingdoms.setrank.fake");
            boolean isJrAdmin = plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)
                    && player.hasPermission("kingdoms.setrank.fake");
            boolean isAdmin = plugin.getPlayerRank().get(player.getUniqueId().toString())
                    .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)
                    && player.hasPermission("kingdoms.setrank.fake");
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reset")) {
                    if (plugin.getStaff().get(player.getUniqueId().toString()).equalsIgnoreCase("SRMOD")
                            && !plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                        plugin.getPlayerRank().put(player.getUniqueId().toString(), ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "You reset your rank to " + ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
                    } else {
                        player.sendMessage(ChatColor.RED + "You are already " + ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD + ChatColor.RED + " rank");
                    }
                    if (plugin.getStaff().get(player.getUniqueId().toString()).equalsIgnoreCase("SRMOD")
                            && !plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                        plugin.getPlayerRank().put(player.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "You reset your rank to " + ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
                    } else {
                        player.sendMessage(ChatColor.RED + "You are already " + ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN + ChatColor.RED + " rank");
                    }
                    if (plugin.getStaff().get(player.getUniqueId().toString()).equalsIgnoreCase("SRMOD")
                            && !plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                        plugin.getPlayerRank().put(player.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN);
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "You reset your rank to " + ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN);
                    } else {
                        player.sendMessage(ChatColor.RED + "You are already " + ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN + ChatColor.RED + " rank");
                    }
                }
                plugin.savePluginData();
            }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("fake") && args[1].equalsIgnoreCase("default")) {
                if (!isDefault) {
                    plugin.getPlayerRank().put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "You set your rank to appear as " + ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                } else {
                    player.sendMessage(ChatColor.RED + "You are already " + ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT + ChatColor.RED + " rank");
                }
            } else if (args[0].equalsIgnoreCase("fake") && args[1].equalsIgnoreCase("vip")) {
                if (!isVIP) {
                    plugin.getPlayerRank().put(player.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "You set your rank to appear as " + ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                } else {
                    player.sendMessage(ChatColor.RED + "You are already " + ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP + ChatColor.RED + " rank");
                }
            } else if (args[0].equalsIgnoreCase("fake") && args[1].equalsIgnoreCase("hero")) {
                if (!isHero) {
                    plugin.getPlayerRank().put(player.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "You set your rank to appear as " + ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                } else {
                    player.sendMessage(ChatColor.RED + "You are already " + ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO + ChatColor.RED + " rank");
                }
            } else if (args[0].equalsIgnoreCase("fake") && args[1].equalsIgnoreCase("youtube")) {
                if (!isYoutube) {
                    plugin.getPlayerRank().put(player.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + Rank.YOUTUBE);
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "You set your rank to appear as " + ChatColor.RED.toString() + ChatColor.BOLD + Rank.YOUTUBE);
                } else {
                    player.sendMessage(ChatColor.RED + "You are already " + ChatColor.RED.toString() + ChatColor.BOLD + Rank.YOUTUBE + ChatColor.RED + " rank");
                }
            } else if (args[0].equalsIgnoreCase("fake") && args[1].equalsIgnoreCase("jrmod")) {
                if (!isJrMod) {
                    plugin.getPlayerRank().put(player.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "You set your rank to appear as " + ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                } else {
                    player.sendMessage(ChatColor.RED + "You are already " + ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD + ChatColor.RED + " rank");
                }
            } else if (args[0].equalsIgnoreCase("fake") && args[1].equalsIgnoreCase("mod")) {
                if (!isMod) {
                    plugin.getPlayerRank().put(player.getUniqueId().toString(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "You set your rank to appear as " + ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                } else {
                    player.sendMessage(ChatColor.RED + "You are already " + ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD + ChatColor.RED + " rank");
                }
            } else if (args[0].equalsIgnoreCase("fake") && args[1].equalsIgnoreCase("srmod")) {
                if (!isSrMod) {
                    plugin.getPlayerRank().put(player.getUniqueId().toString(), ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "You set your rank to appear as " + ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
                } else {
                    player.sendMessage(ChatColor.RED + "You are already " + ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD + ChatColor.RED + " rank");
                }
            } else if (args[0].equalsIgnoreCase("fake") && args[1].equalsIgnoreCase("jradmin")) {
                if (!isJrAdmin) {
                    plugin.getPlayerRank().put(player.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "You set your rank to appear as " + ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
                } else {
                    player.sendMessage(ChatColor.RED + "You are already " + ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN + ChatColor.RED + " rank");
                }
            } else if (args[0].equalsIgnoreCase("fake") && args[1].equalsIgnoreCase("admin")) {
                if (!isAdmin) {
                    plugin.getPlayerRank().put(player.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN);
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "You set your rank to appear as " + ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN);
                } else {
                    player.sendMessage(ChatColor.RED + "You are already " + ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN + ChatColor.RED + " rank");
                }
            }
            plugin.savePluginData();
        } else if (args.length == 3) {
            Player target = Bukkit.getPlayer(args[1]);
            if (sender.hasPermission("kingdoms.setrank.default") || sender.hasPermission("kingdoms.setrank.vip") || sender.hasPermission("kingdoms.setrank.hero") || sender.hasPermission("kingdoms.setrank.jrmod") || sender.hasPermission("kingdoms.setrank.mod") || sender.hasPermission("kingdoms.setrank.srmod") || sender.hasPermission("kingdoms.setrank.jradmin") || sender.hasPermission("kingdoms.setrank.admin")) {
                if (target == null) {
                    sender.sendMessage(args[1] + ChatColor.RED + " is not online");
                    return true;
                } else {
                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                    if (args[0].equalsIgnoreCase("set")) {

                        if (!plugin.getPlayerRank().containsKey(target.getUniqueId().toString())) {
                            plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                        }

                        if (args[2].equalsIgnoreCase("default")) {
                            if (sender.hasPermission("kingdoms.setrank.default")) {
                                if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                                    sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                    return true;
                                }
                                Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set default");
                                target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT
                                        + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + player.getName());
                                plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                                plugin.getStaff().put(target.getUniqueId().toString(), "DEFAULT");
                                sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName()
                                        + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT);
                                if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                                    if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                                        plugin.getStaff().remove(target.getUniqueId().toString());
                                        plugin.savePluginData(target);
                                    }
                                }
                            }
                        }

                        if (args[2].equalsIgnoreCase("vip")) {
                            if (sender.hasPermission("kingdoms.setrank.vip")) {
                                if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                                    sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                    return true;
                                }
                                Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set vip");
                                target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP
                                        + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + player.getName());
                                plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                                plugin.getStaff().put(target.getUniqueId().toString(), "VIP");
                                sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName()
                                        + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP);
                                if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                                    if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                                        plugin.getStaff().remove(target.getUniqueId().toString());
                                        plugin.savePluginData(target);
                                    }
                                }
                            }
                        }

                        if (args[2].equalsIgnoreCase("hero")) {
                            if (sender.hasPermission("kingdoms.setrank.hero")) {
                                if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                                    sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                    return true;
                                }
                                Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set hero");
                                target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO
                                        + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + player.getName());
                                plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                                plugin.getStaff().put(target.getUniqueId().toString(), "HERO");
                                sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName()
                                        + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO);
                                if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                                    if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                                        plugin.getStaff().remove(target.getUniqueId().toString());
                                        plugin.savePluginData(target);
                                    }
                                }
                            }
                        }

                        if (args[2].equalsIgnoreCase("youtube")) {
                            if (sender.hasPermission("kingdoms.setrank.youtube")) {
                                if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.RED.toString() + ChatColor.BOLD + Rank.YOUTUBE)) {
                                    sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.RED + ChatColor.BOLD + Rank.YOUTUBE);
                                    return true;
                                }
                                Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set youtube");
                                target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.RED + ChatColor.BOLD + "YT");
                                plugin.getStaff().put(target.getUniqueId().toString(), "YOUTUBE");
                                plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + "YT");
                                sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName()
                                        + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.RED + ChatColor.BOLD + "YT");
                                if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                                    if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                                        plugin.getStaff().remove(target.getUniqueId().toString());
                                        plugin.savePluginData(target);
                                    }
                                }
                            }
                        }

                        if (args[2].equalsIgnoreCase("jrmod")) {
                            if (sender.hasPermission("kingdoms.setrank.jrmod")) {
                                if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                                    sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                    return true;
                                }
                                Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set jrmod");
                                plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                                plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                                sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName()
                                        + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD);
                                if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                                    if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                                        plugin.savePluginData(target);
                                    }
                                }
                            }
                        }

                        if (args[2].equalsIgnoreCase("mod")) {
                            if (sender.hasPermission("kingdoms.setrank.mod")) {
                                if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                                    sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                    return true;
                                }
                                Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set mod");
                                target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD
                                        + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + player.getName());
                                plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                                plugin.getStaff().put(target.getUniqueId().toString(), "MOD");
                                sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName()
                                        + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD);
                                if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                                    if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                                        plugin.savePluginData(target);
                                    }
                                }
                            }
                        }

                        if (args[2].equalsIgnoreCase("srmod")) {
                            if (sender.hasPermission("kingdom.setrank.srmod")) {
                                if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                                    sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD);
                                    return true;
                                }
                                Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set srmod");
                                target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD
                                        + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + player.getName());
                                plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
                                plugin.getStaff().put(target.getUniqueId().toString(), "SRMOD");
                                sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName()
                                        + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD);
                                if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                                    if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                                        plugin.savePluginData(target);
                                    }
                                }
                            }
                        }


                        if (args[2].equalsIgnoreCase("jradmin")) {
                            if (sender.hasPermission("kingdoms.setrank.jradmin")) {
                                if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                                    Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set jradmin");
                                    target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN
                                            + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + player.getName());
                                    plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
                                    plugin.getStaff().put(target.getUniqueId().toString(), "JRADMIN");
                                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName()
                                            + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                } else {
                                    sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN);
                                    return true;
                                }
                                if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                                    if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                                        plugin.savePluginData(target);
                                    }
                                }
                            }
                        }

                        if (args[2].equalsIgnoreCase("admin")) {
                            if (sender.hasPermission("kingdoms.setrank.admin") || player.isOp()) {

                                if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                                    sender.sendMessage(target.getName() + ChatColor.RED + " is already rank " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.ADMIN);
                                    return true;
                                }
                                plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN);
                                plugin.getStaff().put(target.getUniqueId().toString(), "ADMIN");
                                Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set admin");
                                target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.ADMIN
                                        + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + player.getName());
                                sender.sendMessage(ChatColor.LIGHT_PURPLE + "You set " + ChatColor.WHITE + target.getName()
                                        + ChatColor.LIGHT_PURPLE + "'s rank to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.ADMIN);
                                if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                                    if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                            plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                                        plugin.savePluginData(target);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    } else if(sender instanceof ConsoleCommandSender)

    {
        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            sender.sendMessage(args[1] + ChatColor.RED + " is not online");
            return true;
        } else {
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            if (args[0].equalsIgnoreCase("set")) {

                if (!plugin.getPlayerRank().containsKey(target.getUniqueId().toString())) {
                    plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                }

                if (args[2].equalsIgnoreCase("default")) {
                    if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
                        MessageManager.consoleBad(target.getName() + " is already rank " + Rank.DEFAULT);
                        return true;
                    }

                    MessageManager.consoleGood("You set " + target.getName() + "'s rank to " + Rank.DEFAULT);
                    Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set default");
                    target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_GRAY + ChatColor.BOLD + Rank.DEFAULT
                            + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + "CONSOLE");
                    plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                    plugin.getStaff().put(target.getUniqueId().toString(), "DEFAULT");
                    if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                        if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                            plugin.getStaff().remove(target.getUniqueId().toString());
                            plugin.savePluginData(target);
                        }
                    }
                }

                if (args[2].equalsIgnoreCase("vip")) {

                    if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
                        MessageManager.consoleBad(target.getName() + " is already rank " + Rank.VIP);
                        return true;
                    }

                    MessageManager.consoleGood("You set " + target.getName() + "'s rank to " + Rank.VIP);
                    Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set vip");
                    target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.GREEN + ChatColor.BOLD + Rank.VIP
                            + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + "CONSOLE");
                    plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP);
                    plugin.getStaff().put(target.getUniqueId().toString(), "VIP");
                    if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                        if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                            plugin.getStaff().remove(target.getUniqueId().toString());
                            plugin.savePluginData(target);
                        }
                    }
                }

                if (args[2].equalsIgnoreCase("hero")) {
                    if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
                        MessageManager.consoleBad(target.getName() + " is already rank " + Rank.HERO);
                        return true;
                    }

                    MessageManager.consoleGood("You set " + target.getName() + "'s rank to " + Rank.HERO);
                    Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set hero");
                    target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.AQUA + ChatColor.BOLD + Rank.HERO
                            + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + "CONSOLE");
                    plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO);
                    plugin.getStaff().put(target.getUniqueId().toString(), "HERO");
                    if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                        if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                            plugin.getStaff().remove(target.getUniqueId().toString());
                            plugin.savePluginData(target);
                        }
                    }
                }

                if (args[2].equalsIgnoreCase("youtube")) {
                    if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.RED.toString() + ChatColor.BOLD + Rank.YOUTUBE)) {
                        MessageManager.consoleBad(target.getName() + " is already rank " + Rank.YOUTUBE);
                        return true;
                    }

                    MessageManager.consoleGood("You set " + target.getName() + "'s rank to " + Rank.YOUTUBE);
                    Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set youtube");
                    target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.RED + ChatColor.BOLD + Rank.YOUTUBE
                            + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + "CONSOLE");
                    plugin.getStaff().put(target.getUniqueId().toString(), "YOUTUBE");
                    plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.RED.toString() + ChatColor.BOLD + Rank.YOUTUBE);
                    if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                        if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                            plugin.getStaff().remove(target.getUniqueId().toString());
                            plugin.savePluginData(target);
                        }
                    }
                }

                if (args[2].equalsIgnoreCase("jrmod")) {
                    if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
                        MessageManager.consoleBad(target.getName() + " is already rank " + Rank.JRMOD);
                        return true;
                    }

                    MessageManager.consoleGood("You set " + target.getName() + "'s rank to " + Rank.JRMOD);
                    Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set jrmod");
                    plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD);
                    plugin.getStaff().put(target.getUniqueId().toString(), "JRMOD");
                    target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_AQUA + ChatColor.BOLD + Rank.JRMOD
                            + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + "CONSOLE");
                    if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                        if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                            plugin.savePluginData(target);
                        }
                    }
                }

                if (args[2].equalsIgnoreCase("mod")) {
                    if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
                        MessageManager.consoleBad(target.getName() + " is already rank " + Rank.MOD);
                        return true;
                    }

                    MessageManager.consoleGood("You set " + target.getName() + "'s rank to " + Rank.MOD);
                    Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set mod");
                    target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.YELLOW + ChatColor.BOLD + Rank.MOD
                            + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + "CONSOLE");
                    plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD);
                    plugin.getStaff().put(target.getUniqueId().toString(), "MOD");
                    if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                        if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                            plugin.savePluginData(target);
                        }
                    }
                }

                if (args[2].equalsIgnoreCase("srmod")) {
                    if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
                        MessageManager.consoleBad(target.getName() + " is already rank " + Rank.SRMOD);
                        return true;
                    }

                    MessageManager.consoleGood("You set " + target.getName() + "'s rank to " + Rank.SRMOD);
                    Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set srmod");
                    target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.GOLD + ChatColor.BOLD + Rank.SRMOD
                            + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + "CONSOLE");
                    plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD);
                    plugin.getStaff().put(target.getUniqueId().toString(), "SRMOD");
                    if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                        if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                            plugin.savePluginData(target);
                        }
                    }
                }


                if (args[2].equalsIgnoreCase("jradmin")) {
                    if (!plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
                        MessageManager.consoleGood("You set " + target.getName() + "'s rank to " + Rank.JRADMIN);
                        Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set jradmin");
                        target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.JRADMIN
                                + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + "CONSOLE");
                        plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN);
                        plugin.getStaff().put(target.getUniqueId().toString(), "JRADMIN");

                    } else {
                        MessageManager.consoleBad(target.getName() + " is already rank " + Rank.JRADMIN);
                        return true;
                    }
                    if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                        if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                            plugin.savePluginData(target);
                        }
                    }
                }

                if (args[2].equalsIgnoreCase("admin")) {
                    if (plugin.getPlayerRank().get(target.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
                        MessageManager.consoleBad(target.getName() + " is already rank " + Rank.ADMIN);
                        return true;
                    }

                    plugin.getPlayerRank().put(target.getUniqueId().toString(), ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN);
                    plugin.getStaff().put(target.getUniqueId().toString(), "ADMIN");
                    MessageManager.consoleGood("You set " + target.getName() + "'s rank to " + Rank.ADMIN);
                    Bukkit.getServer().dispatchCommand(console, "pex user " + target.getName() + " group set admin");
                    target.sendMessage(ChatColor.LIGHT_PURPLE + "Your rank has been set to " + ChatColor.DARK_RED + ChatColor.BOLD + Rank.ADMIN
                            + ChatColor.LIGHT_PURPLE + " by " + ChatColor.WHITE + "CONSOLE");
                    if (plugin.getStaff().containsKey(target.getUniqueId().toString())) {
                        if (plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("MOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("SRMOD") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("JRADMIN") ||
                                plugin.getStaff().get(target.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                            plugin.savePluginData(target);
                        }
                    }
                }
            }
        }
    }
        return true;
}
}
