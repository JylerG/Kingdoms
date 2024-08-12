package games.kingdoms.kingdoms.admin.npcinteractions.types;

public abstract class NatureItems {

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    private Long cost;

    public NatureItems(String name, String description, Long cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }
}
