package games.kingdoms.kingdoms;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static org.bukkit.ChatColor.*;

public class PlayerJoinListener {

    final Kingdoms plugin = Kingdoms.getPlugin();

    public void updateTabListWithScoreboard(Player player) {
        String prefix = getPrefixForPlayer(player);

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam(player.getUniqueId().toString());

        if (team == null) {
            team = scoreboard.registerNewTeam(player.getDisplayName());
        }

        team.setPrefix(prefix);
        team.addEntry(player.getDisplayName());
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.setScoreboard(scoreboard);
        }
        player.setScoreboard(scoreboard);
    }


    public void updateTabList(Player player) {
        String prefix = getPrefixForPlayer(player); // Method to get the prefix for the player

        // Update the player's tab list name
        player.setPlayerListName(prefix + ChatColor.WHITE + player.getDisplayName());
        player.sendMessage("Your name has been set to " + player.getPlayerListName());
    }

    public String getPrefixForPlayer(Player player) {
        String playerUUID = player.getUniqueId().toString();
        String rank = plugin.getPlayerRank().get(playerUUID);

        if (rank == null) return "";


        return switch (rank) {
            case "§a§lVIP" -> GREEN.toString() + BOLD + "VIP ";
            case "§b§lHERO" -> AQUA.toString() + BOLD + "HERO ";
            case "§c§lYT" -> RED.toString() + BOLD + "YT ";
            case "§3§lJRMOD" -> DARK_AQUA.toString() + BOLD + "JRMOD ";
            case "§e§lMOD" -> YELLOW.toString() + BOLD + "MOD ";
            case "§6§lSRMOD" -> GOLD.toString() + BOLD + "SRMOD ";
            case "§4§lJRADMIN" -> DARK_RED.toString() + BOLD + "JRADMIN ";
            case "§4§lADMIN" -> DARK_RED.toString() + BOLD + "ADMIN ";
            default -> "";
        };
    }
}
