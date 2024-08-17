package games.kingdoms.kingdoms.admin.npcinteractions.managers;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.admin.npcinteractions.quests.ItemQuest;
import games.kingdoms.kingdoms.admin.npcinteractions.quests.KillQuest;
import games.kingdoms.kingdoms.admin.npcinteractions.types.Quest;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class QuestManager {

    final Kingdoms plugin = Kingdoms.getPlugin(Kingdoms.class);

    public List<Quest> KillQuests() {
        List<Quest> availableQuests = new ArrayList<>();

        plugin.getConfig().getConfigurationSection("quests.kill").getKeys(false).forEach(quests -> {

            String name = plugin.getConfig().getString("quests.kill." + quests + ".name");
            String description = plugin.getConfig().getString("quests.kill." + quests + ".description");
            Long reward = plugin.getConfig().getLong("quests.kill." + quests + ".reward");
            String entityType = plugin.getConfig().getString("quests.kill." + quests + ".target.type");
            int count = plugin.getConfig().getInt("quests.kill." + quests + ".target.count");

            EntityType entity = EntityType.valueOf(entityType);

            Quest quest = new KillQuest(name, description, reward, entity, count);

            availableQuests.add(quest);
        });

        return availableQuests;
    }

    public List<Quest> ItemQuests() {
        List<Quest> availableQuests = new ArrayList<>();

        plugin.getConfig().getConfigurationSection("quests.item").getKeys(false).forEach(quests -> {

            String name = plugin.getConfig().getString("quests.item." + quests + ".name");
            String description = plugin.getConfig().getString("quests.item." + quests + ".description");
            Long reward = plugin.getConfig().getLong("quests.item." + quests + ".reward");
            String material = plugin.getConfig().getString("quests.item." + quests + ".block.type");
            int count = plugin.getConfig().getInt("quests.item." + quests + ".block.count");

            Material block = Material.valueOf(material);

            Quest quest = new ItemQuest(name, description, reward, block, count);

            availableQuests.add(quest);
        });

        return availableQuests;
    }
}
