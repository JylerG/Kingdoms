package games.kingdoms.kingdoms.publiccmds.kingdoms.related;

import com.github.sanctum.panther.file.Configurable;
import com.github.sanctum.panther.file.Node;

import java.util.HashMap;
import java.util.Map;

public class KingdomRankCache {

    private static final Map<String, Map<Integer, String>> kingdomRanks = new HashMap<>();

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

    public static String getRankName(String kingdom, int rankInt) {
        Map<Integer, String> ranks = kingdomRanks.get(kingdom);
        if (ranks != null) {
            return ranks.get(rankInt);
        }
        return null;
    }
}

