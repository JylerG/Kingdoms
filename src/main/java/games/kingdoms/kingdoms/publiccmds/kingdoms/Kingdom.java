package games.kingdoms.kingdoms.publiccmds.kingdoms;

import java.util.UUID;

public class Kingdom {

    String name;
    UUID id;
    public Kingdom(String kingdomName, UUID kingdomId) {
        this.name = kingdomName;
        this.id = kingdomId;
    }

    public String getName() {
        return name;
    }

    public UUID getId(){
        return id;
    }

}
