package games.kingdoms.kingdoms.admin.ranks;

import org.bukkit.ChatColor;

public enum Rank {

    ADMIN("ADMIN", ChatColor.DARK_RED, false, false, 9),
    JRADMIN("JRADMIN", ChatColor.DARK_RED, false, false, 8),
    SRMOD("SRMOD", ChatColor.GOLD, false, false, 7),
    MOD("MOD", ChatColor.YELLOW, false, false, 6),
    JRMOD("JRMOD", ChatColor.DARK_AQUA, false, false, 5),
    YOUTUBE("YT", ChatColor.RED, false, false, 4),
    PLEB("PLEB", ChatColor.DARK_PURPLE, false, false, 3),
    HERO("HERO", ChatColor.AQUA, false, false, 2),
    VIP("VIP", ChatColor.GREEN, false, false, 1),
    DEFAULT("DEFAULT", ChatColor.DARK_GRAY, false, false, 0);

    private final String primaryName;
    private final ChatColor primaryColor;
    private final boolean bold;
    private final boolean italicized;
    private final int level;

    Rank(String primaryName, ChatColor primaryColor, boolean bold, boolean italicized, int level) {
        this.primaryName = primaryName;
        this.primaryColor = primaryColor;
        this.bold = bold;
        this.italicized = italicized;
        this.level = level;
    }

    public String getRank() {
        return this.primaryName;
    }

    public String getName() {
        return primaryName;
    }

    public ChatColor getColor() {
        return primaryColor;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalicized() {
        return italicized;
    }

    public int getLevel() {
        return level;
    }
}
