package games.kingdoms.kingdoms.publiccmds.kingdoms.related;

import com.github.sanctum.panther.file.Configurable;
import com.github.sanctum.panther.file.Node;
import games.kingdoms.kingdoms.admin.configs.KingdomsConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KingdomRankCache {

    private static final Map<String, Map<Integer, String>> kingdomRanks = new HashMap<>();
    private static final Map<UUID, Integer> playerRanks = new HashMap<>();

    public static void loadRanks(Configurable kc) {
        kingdomRanks.clear();
        Node ranksInKingdoms = kc.getNode("ranksInKingdoms");
        if (ranksInKingdoms != null) {
            for (String kingdomName : ranksInKingdoms.getKeys(false)) {
                Map<Integer, String> ranks = new HashMap<>();
                Node kingdomNode = ranksInKingdoms.getNode(kingdomName);
                if (kingdomNode != null) {
                    for (String rankIntString : kingdomNode.getKeys(false)) {
                        try {
                            int rankInt = Integer.parseInt(rankIntString);
                            String rankName = kingdomNode.getNode(rankIntString).get(String.class);
                            if (rankName != null) {
                                ranks.put(rankInt, rankName);
                            }
                        } catch (NumberFormatException ignored) {
                            // Ignore non-integer rank nodes
                        }
                    }
                }
                kingdomRanks.put(kingdomName, ranks);
            }
        }
    }

    public static void loadPlayerRanks(Configurable kc) {
        playerRanks.clear();
        Node playersNode = kc.getNode("players");
        if (playersNode != null) {
            for (String uuidString : playersNode.getKeys(false)) {
                try {
                    UUID uuid = UUID.fromString(uuidString);
                    Node playerNode = playersNode.getNode(uuidString);
                    Integer rankInt = playerNode.getNode("rankInt").get(Integer.class);
                    if (rankInt != null) {
                        playerRanks.put(uuid, rankInt);
                    }
                } catch (IllegalArgumentException ignored) {
                    // Ignore invalid UUIDs
                }
            }
        }
    }

    public static Integer getPlayerRank(UUID uuid) {
        return playerRanks.get(uuid);
    }

    public static void setPlayerRank(UUID uuid, int rank) {
        playerRanks.put(uuid, rank);
    }

    public static String getRankName(String kingdom, int rankInt) {
        Map<Integer, String> ranks = kingdomRanks.get(kingdom);
        if (ranks != null) {
            return ranks.get(rankInt);
        }
        return null;
    }
}

