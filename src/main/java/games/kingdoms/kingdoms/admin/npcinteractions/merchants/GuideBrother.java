package games.kingdoms.kingdoms.admin.npcinteractions.merchants;

import games.kingdoms.kingdoms.admin.npcinteractions.types.BrotherItems;
import org.bukkit.Material;

public class GuideBrother extends BrotherItems {

    private Material material;
    private int amount;

    public GuideBrother(String name, String description, Material material, int amount) {
        super(name, description);
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
