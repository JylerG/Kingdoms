package games.kingdoms.kingdoms;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class PlayerJoinListener {

    final Kingdoms plugin = Kingdoms.getPlugin();

    public void updateTabListWithScoreboard(Player player) {
        ChatColor color = getColorForPlayer(player); // Get color based on rank
        String playerName = player.getName(); // Use actual username for stability

        // Get or create the main scoreboard
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager == null) return; // Safety check
        Scoreboard scoreboard = manager.getMainScoreboard();

        // Get or create the player's team
        Team team = scoreboard.getTeam(player.getUniqueId().toString());
        if (team == null) {
            team = scoreboard.registerNewTeam(player.getUniqueId().toString());
        }

        // Set name color in the tab list
        team.setColor(color); // Set team color for tab list
        team.addEntry(playerName); // Use player's actual name

        // Ensure name tag visibility is always on
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);

        // Apply scoreboard to all players
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.setScoreboard(scoreboard);
        }
        player.setScoreboard(scoreboard);

        // Update the player's tab list name separately
        player.setPlayerListName(color + playerName);
    }

    public void updateTabList(Player player) {
        ChatColor color = getColorForPlayer(player); // Get color based on rank

        // Update the player's tab list name with the color and their display name
        player.setPlayerListName(color + player.getDisplayName());
        player.sendMessage("Your name color in the tab list has been updated.");
    }

    public ChatColor getColorForPlayer(Player player) {
        String playerUUID = player.getUniqueId().toString();
        String rank = plugin.getPlayerRank().get(playerUUID);

        if (rank == null) return ChatColor.WHITE; // Default to white if no rank

        return switch (rank) {
            case "§a§lVIP" -> ChatColor.GREEN;
            case "§b§lHERO" -> ChatColor.AQUA;
            case "§c§lYT" -> ChatColor.RED;
            case "§3§lJRMOD" -> ChatColor.DARK_AQUA;
            case "§e§lMOD" -> ChatColor.YELLOW;
            case "§6§lSRMOD" -> ChatColor.GOLD;
            case "§4§lJRADMIN" -> ChatColor.DARK_RED;
            case "§4§lADMIN" -> ChatColor.DARK_RED;
            default -> ChatColor.WHITE;
        };
    }

}
