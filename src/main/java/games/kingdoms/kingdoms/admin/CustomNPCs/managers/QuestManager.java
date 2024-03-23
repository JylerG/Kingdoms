package games.kingdoms.kingdoms.admin.CustomNPCs.managers;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.admin.CustomNPCs.models.KillQuest;
import games.kingdoms.kingdoms.admin.CustomNPCs.models.Quest;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class QuestManager {

    public List<Quest> getAvailableQuests() {
        List<Quest> availableQuests = new ArrayList<>();

        Kingdoms.getPlugin().getConfig().getConfigurationSection("quests.kill").getKeys(false).forEach(questName -> {

            String name = Kingdoms.getPlugin().getConfig().getString("quests.kill." + questName + ".name");
            String description = Kingdoms.getPlugin().getConfig().getString("quests.kill." + questName + ".description");
            double reward = Kingdoms.getPlugin().getConfig().getDouble("quests.kill." + questName + ".reward");
            String entityType = Kingdoms.getPlugin().getConfig().getString("quests.kill." + questName + ".target.type");
            int count = Kingdoms.getPlugin().getConfig().getInt("quests.kill." + questName + ".target.count");

            EntityType entityType1 = EntityType.valueOf(entityType);

            Quest quest = new KillQuest(name, description, reward, entityType1, count);
            availableQuests.add(quest);

        });


        return availableQuests;
    }
}
