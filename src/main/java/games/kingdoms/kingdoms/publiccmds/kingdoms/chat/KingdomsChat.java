package games.kingdoms.kingdoms.publiccmds.kingdoms.chat;

import com.github.sanctum.panther.file.Configurable;
import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import games.kingdoms.kingdoms.admin.configs.KingdomsConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class KingdomsChat implements CommandExecutor {

    final Kingdoms plugin = Kingdoms.getPlugin();
    final HashMap<String, String> kingdoms = plugin.getKingdoms();
    final HashMap<String, Integer> kingdomRank = plugin.getKingdomRank();
    final HashMap<String, String> spyOnKingdom = plugin.getSpyOnKingdom();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {

            if (!plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
                MessageManager.playerBad(player, "You are not in a kingdom");
                return true;
            }

            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "You must have at least one argument to send a message to " + ChatColor.WHITE + kingdoms.get(player.getUniqueId().toString()) + ChatColor.RED + " 's chat");
                return true;
            }

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (kingdoms.containsKey(p.getUniqueId().toString())) {
                    if (kingdoms.get(p.getUniqueId().toString()).equals(kingdoms.get(player.getUniqueId().toString()))
                            || (spyOnKingdom.containsKey(p.getUniqueId().toString())
                            && spyOnKingdom.get(p.getUniqueId().toString()).equalsIgnoreCase(kingdoms.get(player.getUniqueId().toString())))) {
                        Configurable kc = KingdomsConfig.getInstance().getConfig();
                        String message = String.join(" ", args); // Concatenate args into a single string
                        kc.getNode("ranksInKingdom").getKeys(false).forEach(kingdom ->
                                kc.getNode("ranksInKingdom." + kingdom).getKeys(false).forEach(rankInt -> {

                            String playerRank = kc.getNode("players." + player.getUniqueId().toString()).toPrimitive().getString();

                            String format = ChatColor.GOLD.toString() + ChatColor.BOLD + "[K] " + ChatColor.LIGHT_PURPLE + playerRank + ChatColor.GOLD + player.getDisplayName() + ": " + message;
                            p.sendMessage(format);
                        }));

                    }
                }
            }
        } else {
            MessageManager.consoleBad("You must be a player to execute this command.");
        }

        return true;
    }
}
