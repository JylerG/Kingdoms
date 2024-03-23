package games.kingdoms.kingdoms.publiccmds.nightvision;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Commands implements CommandExecutor {

    public Kingdoms plugin;

    public Commands(Kingdoms pl) {
        this.plugin = pl;
    }

    String Prefix = ChatColor.translateAlternateColorCodes('&', "&8[&2NV&8] ");
    String Enabled = ChatColor.translateAlternateColorCodes('&', Prefix + "&2Night Vision&7 has been &2Enabled!");
    String Disabled = ChatColor.translateAlternateColorCodes('&', Prefix + "&2Night Vision&7 has been &2Disabled!");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("nv")) {

            if (!(sender instanceof Player)) {
                MessageManager.consoleBad("You must be a player to execute this command.");
                return true;
            }

            Player p = (Player) sender;

            if (args.length == 0) {

                if (p.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
                    p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                    p.sendMessage(Disabled);
                } else {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 1));
                    p.sendMessage(Enabled);

                    return true;
                }
            }
            if (args.length >= 1) {
                p.sendMessage(Prefix + ChatColor.GRAY + "Usage: /nv");
            }
        }
        return true;
    }
}
