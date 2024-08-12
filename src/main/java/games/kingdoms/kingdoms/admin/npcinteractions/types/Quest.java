package games.kingdoms.kingdoms.admin.npcinteractions.types;

public abstract class Quest {

    private String name;
    private String description;
    private Long reward;

    public Quest(String name, String description, Long reward) {
        this.name = name;
        this.description = description;
        this.reward = reward;
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

    public Long getReward() {
        return reward;
    }

    public void setReward(Long reward) {
        this.reward = reward;
    }
}
