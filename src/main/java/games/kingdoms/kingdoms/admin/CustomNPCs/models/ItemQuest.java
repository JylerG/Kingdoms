package games.kingdoms.kingdoms.admin.CustomNPCs.models;

import org.bukkit.Material;

public class ItemQuest extends Quest {

    private Material material;
    private int amount;

    public ItemQuest(String name, String description, double reward, Material material, int amount) {
        super(name, description, reward);
        this.amount = amount;
        this.material = material;
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
