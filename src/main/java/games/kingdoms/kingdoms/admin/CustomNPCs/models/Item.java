package games.kingdoms.kingdoms.admin.CustomNPCs.models;

import org.bukkit.Material;

public class Item extends Quest {

    private Material material;

    public Item(String name, Material material) {
        super(name, material);
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
