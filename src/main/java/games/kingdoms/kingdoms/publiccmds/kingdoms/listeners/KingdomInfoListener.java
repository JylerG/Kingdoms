package games.kingdoms.kingdoms.publiccmds.kingdoms.listeners;

import games.kingdoms.kingdoms.Kingdoms;
import org.bukkit.event.Listener;

import java.util.HashMap;

public class KingdomInfoListener implements Listener {

    final Kingdoms plugin = Kingdoms.getPlugin();
    final HashMap<String, String> kingdoms = plugin.getKingdoms();
}
