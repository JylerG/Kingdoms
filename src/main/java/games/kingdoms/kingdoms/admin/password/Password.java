package games.kingdoms.kingdoms.admin.password;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import games.kingdoms.kingdoms.admin.ranks.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

//TODO: Work on making this a thing
public class Password /*implements CommandExecutor, Listener*/ {

//    private final Kingdoms plugin = Kingdoms.getPlugin(Kingdoms.class);
//
//    private static final Pattern PASSWORD_PATTERN =
//            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\\$%\\^&\\*\\-\\_]).{5,}$");
//
//
//    @EventHandler
//    public void onPlayerJoin(PlayerJoinEvent e) {
//        Player player = e.getPlayer();
//        if (plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_AQUA.toString() +
//                ChatColor.BOLD + Rank.JRMOD) ||
//                plugin.getPlayerRank().get(player.getUniqueId().toString())
//                        .equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD) ||
//                plugin.getPlayerRank().get(player.getUniqueId().toString())
//                        .equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD) ||
//                plugin.getPlayerRank().get(player.getUniqueId().toString())
//                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN) ||
//                plugin.getPlayerRank().get(player.getUniqueId().toString())
//                        .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
//            if (!plugin.getStaffPasswords().containsKey(player.getUniqueId().toString())) {
//                plugin.getPasswordExists().put(player.getUniqueId().toString(), false);
//                plugin.getPasswordStatus().put(player.getUniqueId().toString(), false);
//                player.sendMessage(ChatColor.GREEN + "Please enter your new password\n" +
//                        "Use " + ChatColor.WHITE + "/password <your new password>\n" +
//                        ChatColor.GOLD + ChatColor.BOLD + "Password Parameters\n" +
//                        ChatColor.YELLOW + "◼ At least 5 Characters\n" +
//                        ChatColor.YELLOW + "◼ Contains Numbers\n" +
//                        ChatColor.YELLOW + "◼ Contains Uppercase Letters\n" +
//                        ChatColor.YELLOW + "◼ Contains Lowercase Letters\n" +
//                        ChatColor.YELLOW + "◼ Contains Symbols\n" +
//                        ChatColor.DARK_AQUA + "Please enter a valid password");
//                return;
//            }
//
//            player.sendMessage(ChatColor.GREEN + "Please enter your password. " + ChatColor.WHITE + "/password <your password>");
//            plugin.getPasswordStatus().put(player.getUniqueId().toString(), false);
//        }
//    }
//
//    @EventHandler
//    public void onPlayerMove(PlayerMoveEvent e) {
//        Player player = e.getPlayer();
//
//        if (plugin.getPasswordStatus().get(player.getUniqueId().toString())) {
//            e.setCancelled(false);
//            return;
//        }
//
//        if (!plugin.getPasswordExists().get(player.getUniqueId().toString())) {
//            player.sendMessage(ChatColor.GREEN + "Please enter your new password\n" +
//                    "Use " + ChatColor.WHITE + "/password <your new password>\n" +
//                    ChatColor.GOLD + ChatColor.BOLD + "Password Parameters\n" +
//                    ChatColor.YELLOW + "◼ At least 5 Characters\n" +
//                    ChatColor.YELLOW + "◼ Contains Numbers\n" +
//                    ChatColor.YELLOW + "◼ Contains Uppercase Letters\n" +
//                    ChatColor.YELLOW + "◼ Contains Lowercase Letters\n" +
//                    ChatColor.YELLOW + "◼ Contains Symbols\n" +
//                    ChatColor.DARK_AQUA + "Please enter a valid password");
//            e.setCancelled(true);
//            return;
//        }
//        if (plugin.getPasswordExists().get(player.getUniqueId().toString()).equals(true)) {
//            player.sendMessage(ChatColor.GREEN + "Please enter your password. " + ChatColor.WHITE + "/password <your password>");
//            e.setCancelled(true);
//            return;
//        }
//    }
//
//    @Override
//    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
//
//        if (sender instanceof Player player) {
//            if (args.length == 1) {
//                if (plugin.getPlayerRank().get(player.getUniqueId().toString()).equalsIgnoreCase(ChatColor.DARK_AQUA.toString() +
//                        ChatColor.BOLD + Rank.JRMOD) ||
//                        plugin.getPlayerRank().get(player.getUniqueId().toString())
//                                .equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD) ||
//                        plugin.getPlayerRank().get(player.getUniqueId().toString())
//                                .equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD) ||
//                        plugin.getPlayerRank().get(player.getUniqueId().toString())
//                                .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN) ||
//                        plugin.getPlayerRank().get(player.getUniqueId().toString())
//                                .equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
//                    if (!plugin.getPasswordExists().containsKey(player.getUniqueId().toString())) {
//
//                        String password = args[0];
//
//                        if (!PASSWORD_PATTERN.matcher(password).matches() || password.length() < 5) {
//                            player.sendMessage(ChatColor.GREEN + "Please enter your new password\n" +
//                                    "Use " + ChatColor.WHITE + "/password <your new password>\n" +
//                                    ChatColor.GOLD + ChatColor.BOLD + "Password Parameters\n" +
//                                    ChatColor.YELLOW + "◼ At least 5 Characters\n" +
//                                    ChatColor.YELLOW + "◼ Contains Numbers\n" +
//                                    ChatColor.YELLOW + "◼ Contains Uppercase Letters\n" +
//                                    ChatColor.YELLOW + "◼ Contains Lowercase Letters\n" +
//                                    ChatColor.YELLOW + "◼ Contains Symbols\n" +
//                                    ChatColor.DARK_AQUA + "Please enter a valid password");
//                            return true;
//
//                        }
//
//                        plugin.getPasswordExists().put(player.getUniqueId().toString(), true);
//                        plugin.getStaffPasswords().put(player.getUniqueId().toString(), password);
//                        plugin.getPasswordStatus().put(player.getUniqueId().toString(), true);
//                        MessageManager.playerGood(player, "You set your password successfully");
//                        if (plugin.getPasswordExists().get(player.getUniqueId().toString())) {
//                            player.sendMessage(ChatColor.GREEN + "Please enter your password. " + ChatColor.WHITE + "/password <your password>");
//                        }
//
//                        if (plugin.getStaffPasswords().get(player.getUniqueId().toString()).equals(args[0])) {
//                            plugin.getPasswordStatus().put(player.getUniqueId().toString(), true);
//                        }
//                    }
//                }
//            }
//        }
//        return true;
//    }
}
