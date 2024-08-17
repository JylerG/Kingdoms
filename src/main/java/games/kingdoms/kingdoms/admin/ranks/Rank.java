package games.kingdoms.kingdoms.admin.ranks;

import org.bukkit.ChatColor;

public enum Rank {

    ADMIN("ADMIN", ChatColor.DARK_RED, 9),
    JRADMIN("JRADMIN", ChatColor.DARK_RED, 8),
    SRMOD("SRMOD", ChatColor.GOLD, 7),
    MOD("MOD", ChatColor.YELLOW, 6),
    JRMOD("JRMOD", ChatColor.DARK_AQUA, 5),
    YOUTUBE("YT", ChatColor.RED, 4),
    PLEB("PLEB", ChatColor.DARK_PURPLE, 3),
    HERO("HERO", ChatColor.AQUA, 2),
    VIP("VIP", ChatColor.GREEN, 1),
    DEFAULT("DEFAULT", ChatColor.DARK_GRAY, 0);

    final String name;
    final ChatColor color;
    final int level;

    Rank(String name, ChatColor color, int level) {
        this.name = name;
        this.color = color;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }

    public int getLevel() {
        return level;
    }
}