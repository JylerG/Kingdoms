package games.kingdoms.kingdoms.admin.npcinteractions.quests;

import games.kingdoms.kingdoms.admin.npcinteractions.types.Quest;
import org.bukkit.Material;

public class ItemQuest extends Quest {

    private Material material;
    private int amount;

    public ItemQuest(String name, String description, Long reward, Material material, int amount) {
        super(name, description, reward);
        this.material = material;
        this.amount = amount;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
