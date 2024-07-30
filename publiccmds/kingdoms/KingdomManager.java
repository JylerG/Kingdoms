package games.kingdoms.kingdoms.publiccmds.kingdoms;

import com.github.sanctum.panther.container.PantherEntryMap;
import com.github.sanctum.panther.container.PantherMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class KingdomManager {

    final PantherMap<String, Kingdom> map = new PantherEntryMap<>();

    public void load(@NotNull Kingdom kingdom) {
        map.put(kingdom.getName(), kingdom);
    }

    public @Nullable Kingdom getKingdom(@NotNull String name) {
        return map.get(name);
    }

}
