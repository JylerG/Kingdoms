package games.kingdoms.kingdoms.admin.npcinteractions.merchants;

import games.kingdoms.kingdoms.admin.npcinteractions.types.MerchantItems;
import org.bukkit.Material;

public class Merchant extends MerchantItems {

    private Material material;
    private int amount;

    public Merchant(String name, String description, Long cost, Material material, int amount) {
        super(name, description, cost);
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
