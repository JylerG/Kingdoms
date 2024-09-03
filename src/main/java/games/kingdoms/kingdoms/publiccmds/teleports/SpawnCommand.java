package games.kingdoms.kingdoms.publiccmds.teleports;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {

    final Kingdoms plugin;

    public SpawnCommand(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            MessageManager.consoleBad("You must be a player to execute this command.");
            return true;
        }
        String dash = "-";
        String sep = dash + dash + dash + dash + dash + dash + dash + dash + dash + dash
                + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash
                + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash
                + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash + dash;
        if (args.length == 0) {

            TextComponent kingdoms = new TextComponent("Kingdoms");
            kingdoms.setColor(ChatColor.AQUA);
            kingdoms.setBold(true);
            kingdoms.setUnderlined(true);
            kingdoms.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/spawn kingdoms"));

            TextComponent warzone = new TextComponent("Warzone");
            warzone.setColor(ChatColor.RED);
            warzone.setBold(true);
            warzone.setUnderlined(true);
            warzone.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/spawn warzone"));

            TextComponent openArrow = new TextComponent(">> ");
            openArrow.setColor(ChatColor.GRAY);
            openArrow.setBold(true);
            openArrow.setUnderlined(false);

            TextComponent closeArrow = new TextComponent(" <<");
            closeArrow.setColor(ChatColor.GRAY);
            closeArrow.setBold(true);
            closeArrow.setUnderlined(false);

            TextComponent spawn = new TextComponent("Spawn Teleport");
            spawn.setColor(ChatColor.DARK_GREEN);
            spawn.setBold(true);

            TextComponent whereTo = new TextComponent("Which spawn would you like to go to?");
            whereTo.setColor(ChatColor.GREEN);
            whereTo.setBold(true);

            player.sendMessage(org.bukkit.ChatColor.GOLD + sep);
            player.spigot().sendMessage(centerMessage(spawn));
            player.spigot().sendMessage(centerMessage(whereTo));
            player.sendMessage(" ");
            sendCenteredMessage(player, warzone, kingdoms, openArrow, closeArrow);
            player.sendMessage(org.bukkit.ChatColor.GOLD + sep);
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("warzone") || args[0].equalsIgnoreCase("wz") || args[0].equalsIgnoreCase("w")) {
                WarzoneCommandListener warzoneCommandListener = new WarzoneCommandListener(plugin, player);
                //TODO: Make this cancel the event if the player moves
                warzoneCommandListener.start();
            }
            if (args[0].equalsIgnoreCase("kingdoms") || args[0].equalsIgnoreCase("k")) {
                KingdomsCommandListener kingdomsCommandListener = new KingdomsCommandListener(plugin, player);
                //TODO: Make this cancel the event if the player moves
                kingdomsCommandListener.start();
            }
        }
        return true;

    }

    private static final int CENTER_PX = 140; // The center point in Minecraft chat

    public static TextComponent centerMessage(TextComponent messageComponent) {
        String message = messageComponent.toLegacyText(); // Get the legacy text version of the TextComponent
        if (message == null || message.isEmpty()) return new TextComponent("");

        message = ChatColor.translateAlternateColorCodes('&', message); // Translate color codes
        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '§') { // Color code indicator
                previousCode = true;
                continue;
            } else if (previousCode) {
                previousCode = false;
                if (c == 'l' || c == 'L') { // Bold code
                    isBold = true;
                    continue;
                } else {
                    isBold = false;
                }
            }

            FontInfo fontInfo = FontInfo.getFontInfo(c);
            messagePxSize += isBold ? fontInfo.getBoldLength() : fontInfo.getLength();
            messagePxSize++;
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = FontInfo.SPACE.getLength() + 1;
        int compensated = 0;

        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }

        TextComponent centeredMessage = new TextComponent(sb.toString());
        centeredMessage.addExtra(messageComponent); // Append the original message after the spaces
        return centeredMessage;
    }

    private void sendCenteredMessage(Player player, TextComponent warzone, TextComponent kingdoms, TextComponent openArrow, TextComponent closeArrow) {
        // Create the base components
        BaseComponent[] message = new ComponentBuilder()
                .append(openArrow)
                .append(warzone)
                .append(closeArrow)
                .append(ChatColor.stripColor("    "))
                .append(openArrow)
                .append(kingdoms)
                .append(closeArrow)
                .create();

        // Center the message
        BaseComponent[] centeredMessage = centerMessage(message);

        // Send the centered message
        player.spigot().sendMessage(centeredMessage);
    }

    public BaseComponent[] centerMessage(BaseComponent[] messageComponents) {
        String plainText = TextComponent.toLegacyText(messageComponents);
        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : plainText.toCharArray()) {
            if (c == '§') {
                previousCode = true;
            } else if (previousCode) {
                previousCode = false;
                if (c == 'l' || c == 'L') {
                    isBold = true;
                } else isBold = false;
            } else {
                FontInfo dFI = FontInfo.getFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = FontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }

        return new ComponentBuilder(sb.toString()).append(messageComponents).create();
    }

}