package games.kingdoms.kingdoms.publiccmds.kingdoms.configs;

import com.github.sanctum.labyrinth.data.FileList;
import com.github.sanctum.labyrinth.data.YamlExtension;
import com.github.sanctum.panther.file.Configurable;
import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MoneyConfig {

    static MoneyConfig INSTANCE;
    final FileList list;
    final Kingdoms plugin;
    String args;
    Map<String, Configurable> configMap = new HashMap<>();

    public MoneyConfig(Kingdoms plugin) {
        this.plugin = plugin;
        this.list = FileList.search(plugin);
    }

    public static @NotNull MoneyConfig getInstance() {
        return INSTANCE != null ? INSTANCE : (INSTANCE = new MoneyConfig(Kingdoms.getPlugin(Kingdoms.class)));
    }

    public Configurable getConfig() {
        return list.get("Money", "Data", YamlExtension.INSTANCE).getRoot();
    }

    public Configurable getConfig(String key) {
        // Get or create a Configurable object based on the key
        Configurable config = configMap.get(key);
        this.args = key;
        if (config == null) {
            config = list.get(key, null, YamlExtension.INSTANCE).getRoot();
            configMap.put(key, config);
        }
        return config;
    }

    public void save() {
        Configurable config = configMap.get("Data/Money.yml");
        if (config != null) {
            for (Map.Entry<String, Long> money : plugin.getMoney().entrySet()) {
                config.set("money." + money.getKey(), money.getValue());
            }
        }
        config.save();
    }

    public Configurable getConfig(@NotNull String fileName, @Nullable String fileDirectory) {
        return list.get(fileName, fileDirectory, YamlExtension.INSTANCE).getRoot();
    }

    public void setup() {
        Configurable mainConfig = getConfig();
        if (!mainConfig.exists()) {
            try {
                mainConfig.create();
            } catch (IOException e) {
                e.printStackTrace(); // handle what happens when for whatever reason it doesn't have access to write the file.
            }
        }
    }

    public void reload(Player player) {
        plugin.getKingdoms().put("money." + player.getUniqueId().toString(), plugin.getKingdoms().get(player.getUniqueId().toString()));
    }

    public void reload() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (plugin.getKingdoms().containsKey(player.getUniqueId().toString())) {
                for (Map.Entry<String, String> kingdoms : plugin.getKingdoms().entrySet()) {
                    plugin.getKingdoms().put("money." + kingdoms.getKey(), kingdoms.getValue());
                }
            }
        }
    }

    public void delete(String args) {
        Configurable mainConfig = getConfig(args);
        mainConfig.delete();
    }

    public FileList getList() {
        return list;
    }
}
