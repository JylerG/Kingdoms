package games.kingdoms.kingdoms.admin.staffchat;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StaffChatTabCompleter implements TabCompleter {

    private final Kingdoms plugin = Kingdoms.getPlugin(Kingdoms.class);

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        List<String> message = new ArrayList<>();
        Player player = (Player) sender;
        if (args.length >= 1) {
            if (plugin.getStaff().containsKey(player.getUniqueId().toString())) {
                message.add("<msg>");
            }
        }

        return message;
    }
}
