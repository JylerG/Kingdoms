package games.kingdoms.kingdoms.admin.CustomNPCs.managers;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.admin.CustomNPCs.models.Item;
import games.kingdoms.kingdoms.admin.CustomNPCs.models.Quest;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class GlassManager {

    public List<Quest> getGlassItems() {
        List<Quest> glassItems = new ArrayList<>();

        Kingdoms.getPlugin().getConfig().getConfigurationSection("items.glass").getKeys(false).forEach(item -> {

            String name = Kingdoms.getPlugin().getConfig().getString("items.glass." + item + ".name");
            String description = Kingdoms.getPlugin().getConfig().getString("items.glass." + item + ".description");
            Material material = Material.valueOf(Kingdoms.getPlugin().getConfig().getString("items.glass." + item + ".type"));

            Quest quest = new Item(name, material);
            glassItems.add(quest);
        });

        return glassItems;
    }
}
