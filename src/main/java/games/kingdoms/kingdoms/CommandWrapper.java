package games.kingdoms.kingdoms;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandWrapper extends Command {

    private final CommandExecutor executor;

    protected CommandWrapper(String name, CommandExecutor executor) {
        super(name);
        this.executor = executor;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        if (executor != null) {
            return executor.onCommand(sender, this, label, args);
        }
        return true;
    }
}
