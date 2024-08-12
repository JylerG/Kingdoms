package games.kingdoms.kingdoms.admin.npcinteractions.types;

public abstract class MerchantItems {

    private String name;
    private String description;
    private Long cost;

    public MerchantItems(String name, String description, Long cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getCost() {
        return cost;
    }
}
