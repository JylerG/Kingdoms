package games.kingdoms.kingdoms.admin.CustomNPCs.managers;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.admin.CustomNPCs.models.Item;
import games.kingdoms.kingdoms.admin.CustomNPCs.models.Quest;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class ForsakenForgeManager {

    public List<Quest> getForgeItems() {
        List<Quest> forgeItems = new ArrayList<>();

        Kingdoms.getPlugin().getConfig().getConfigurationSection("items.forge").getKeys(false).forEach(item -> {

            String name = Kingdoms.getPlugin().getConfig().getString("items.forge." + item + ".name");
            Material material = Material.valueOf(Kingdoms.getPlugin().getConfig().getString("items.forge." + item + ".type"));

            Quest quest = new Item(name, material);
            forgeItems.add(quest);
        });

        return forgeItems;
    }
}
