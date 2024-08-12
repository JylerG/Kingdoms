package games.kingdoms.kingdoms.publiccmds.whisper;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WhisperCommandTabCompleter implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1) {
            List<String> player = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                player.add(p.getDisplayName());
            }
            return player;
        }
        if (args.length > 1) {
            List<String> message = new ArrayList<>();
            message.add("<msg>");
            return message;
        }

        return null;
    }
}
