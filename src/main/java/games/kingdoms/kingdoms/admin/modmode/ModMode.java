package games.kingdoms.kingdoms.admin.modmode;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ModMode implements CommandExecutor {

    private final Kingdoms plugin = Kingdoms.getPlugin(Kingdoms.class);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            //TODO: make this work
            if (player.hasPermission("kingdoms.modmode")) {

            }
        }
        return true;
    }
}
