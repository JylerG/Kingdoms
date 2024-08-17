package games.kingdoms.kingdoms.publiccmds.chats;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ChatTabCompleter implements TabCompleter {

    final Kingdoms plugin = Kingdoms.getPlugin();

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            if (args.length == 1) {
                List<String> chats = new ArrayList<>();
                chats.add("global");
                if (!plugin.getKingdoms().get(player.getUniqueId().toString()).isEmpty()) {
                    chats.add("kingdom");
                }
                if (plugin.getStaff().get(player.getUniqueId().toString()).equalsIgnoreCase("JRMOD")
                        || plugin.getStaff().get(player.getUniqueId().toString()).equalsIgnoreCase("MOD")
                        || plugin.getStaff().get(player.getUniqueId().toString()).equalsIgnoreCase("SRMOD")
                        || plugin.getStaff().get(player.getUniqueId().toString()).equalsIgnoreCase("JRADMIN")
                        || plugin.getStaff().get(player.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                    chats.add("staff");
                }
                return chats;
            }
        }
        return null;
    }
}
