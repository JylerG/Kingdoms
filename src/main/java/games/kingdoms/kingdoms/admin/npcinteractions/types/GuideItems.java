package games.kingdoms.kingdoms.admin.npcinteractions.types;

public abstract class GuideItems {

    private String name;
    private String description;
    private Long cost;

    public GuideItems(String name, String description, Long cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

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
}
