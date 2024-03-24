package games.kingdoms.kingdoms.admin.CustomNPCs.menus;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.admin.CustomNPCs.managers.GuideManager;
import games.kingdoms.kingdoms.admin.CustomNPCs.models.Item;
import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GuideMenu extends Menu {

    private final GuideManager guideManager;

    public GuideMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.guideManager = Kingdoms.getPlugin().getGuideManager();
    }

    @Override
    public String getMenuName() {
        return "Guide Menu";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent inventoryClickEvent) throws MenuManagerNotSetupException, MenuManagerException {

    }

    @Override
    public void setMenuItems() {

        guideManager.getGuideItems().forEach(item -> {
            if (item instanceof Item items) {

                ItemStack blueprints;
                ItemStack resourcePack;
                ItemStack warzone;
                ItemStack merchants;
                ItemStack blackGlassPane;
                ItemStack orangeGlassPane;

                blackGlassPane = makeItem(Material.BLACK_STAINED_GLASS_PANE, " ", " ");
                orangeGlassPane = makeItem(Material.ORANGE_STAINED_GLASS_PANE, " ", " ");

                merchants = makeItem(Material.GOLD_NUGGET, ColorTranslator.translateColorCodes("&e&lMerchants"),
                        ColorTranslator.translateColorCodes("&e‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑"), " ",
                        " ",
                        ColorTranslator.translateColorCodes("&fThere are several merchants located"),
                        ColorTranslator.translateColorCodes("&around spawn and in the &6Kingdom Market&f."),
                        " ",
                        ColorTranslator.translateColorCodes("&fClick on them to buy or sell &cWarzone Resources"),
                        ColorTranslator.translateColorCodes("&bBlueprints&f,&e Resources&f, etc..."),
                        " ",
                        ColorTranslator.translateColorCodes("&e‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑"));

                resourcePack = makeItem(Material.DIAMOND_AXE,
                        ColorTranslator.translateColorCodes("&e&lResource Pack"),
                        ColorTranslator.translateColorCodes("&e‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑"), " ",
                        " ",
                        ColorTranslator.translateColorCodes("&fThis server is best played using"),
                        ColorTranslator.translateColorCodes("&four custom"),
                        ColorTranslator.translateColorCodes("&c&lResource Pack&f."),
                        " ",
                        ColorTranslator.translateColorCodes("&fIt &c&n&ldoes not &fmodify standard item looks,"),
                        ColorTranslator.translateColorCodes("&fand only adds new armor/item models."),
                        ColorTranslator.translateColorCodes("&fThe experience is also best with &bOptifine."),
                        " ",
                        ColorTranslator.translateColorCodes("&fIf you see a &a&n&lBerserker Axe&f, then it is on!"),
                        " ",
                        ColorTranslator.translateColorCodes("&6&lClick&f to apply or download."),
                        " ",
                        ColorTranslator.translateColorCodes("&e‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑")
                );

                blueprints = makeItem(Material.PAPER,
                        ColorTranslator.translateColorCodes("&e&lBlueprints"),
                        ColorTranslator.translateColorCodes("&e‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑"), " ",
                        ColorTranslator.translateColorCodes("&bBlueprints &fare a spcial asset unique to &6Kingdoms&f."),
                        ColorTranslator.translateColorCodes("&7Blueprint Types:"),
                        ColorTranslator.translateColorCodes("&f • Passive Resource Generators"),
                        ColorTranslator.translateColorCodes("&7    Generate resources even when you're not logged on"),
                        ColorTranslator.translateColorCodes("&f • Active Resource Generators"),
                        ColorTranslator.translateColorCodes("&7    Mineable resources that will regenerate"),
                        ColorTranslator.translateColorCodes("&f • Item Shops"),
                        ColorTranslator.translateColorCodes("&7    Upgrade and obtain better gear"),
                        " ",
                        ColorTranslator.translateColorCodes("&fYou can collect them through &cWarzone Frenzy"),
                        ColorTranslator.translateColorCodes("&fand various quests."),
                        " ",
                        ColorTranslator.translateColorCodes("&e‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑")
                );

                warzone = makeItem(Material.DIAMOND_SWORD,
                        ColorTranslator.translateColorCodes("&e&lWarzone"),
                        ColorTranslator.translateColorCodes("&e‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑"), " ",
                        ColorTranslator.translateColorCodes("&fThe warzone is the location located"),
                        ColorTranslator.translateColorCodes("&funderneath your feet, it is home to"),
                        ColorTranslator.translateColorCodes("&fall sorts of Dastardly Events!"),
                        " ",
                        ColorTranslator.translateColorCodes("&fUsing a &aPortal &fto &dTeleport &f will take"),
                        ColorTranslator.translateColorCodes("&fyou to a random location off in that section!(&cN,S,E,W)&f)"),
                        " ",
                        ColorTranslator.translateColorCodes("&eLoot Frenzies &foccur every hour and hold extremely special loot."),
                        " ",
                        ColorTranslator.translateColorCodes("&cWarzone Ores &f can be mined and sold."),
                        " ",
                        ColorTranslator.translateColorCodes("&e‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑‑")
                );

                inventory.setItem(0, blackGlassPane);
                inventory.setItem(1, blackGlassPane);
                inventory.setItem(2, orangeGlassPane);
                inventory.setItem(3, orangeGlassPane);
                inventory.setItem(4, orangeGlassPane);
                inventory.setItem(5, orangeGlassPane);
                inventory.setItem(6, orangeGlassPane);
                inventory.setItem(7, blackGlassPane);
                inventory.setItem(8, blackGlassPane);
                inventory.setItem(9, blackGlassPane);
                inventory.setItem(10, orangeGlassPane);
                inventory.setItem(11, orangeGlassPane);
                inventory.setItem(12, orangeGlassPane);
                inventory.setItem(13, new ItemStack(Material.NETHER_STAR));
                inventory.setItem(14, orangeGlassPane);
                inventory.setItem(15, orangeGlassPane);
                inventory.setItem(16, orangeGlassPane);
                inventory.setItem(17, blackGlassPane);
                inventory.setItem(18, orangeGlassPane);
                inventory.setItem(19, orangeGlassPane);
                inventory.setItem(20, orangeGlassPane);
                inventory.setItem(21, orangeGlassPane);
                inventory.setItem(22, orangeGlassPane);
                inventory.setItem(23, orangeGlassPane);
                inventory.setItem(24, orangeGlassPane);
                inventory.setItem(25, orangeGlassPane);
                inventory.setItem(26, orangeGlassPane);
                inventory.setItem(27, orangeGlassPane);
                inventory.setItem(28, new ItemStack(Material.GOLDEN_HELMET));
                inventory.setItem(29, orangeGlassPane);
                inventory.setItem(30, warzone);
                inventory.setItem(31, orangeGlassPane);
                inventory.setItem(32, merchants);
                inventory.setItem(33, orangeGlassPane);
                inventory.setItem(34, blueprints);
                inventory.setItem(35, blackGlassPane);
                inventory.setItem(36, orangeGlassPane);
                inventory.setItem(37, orangeGlassPane);
                inventory.setItem(38, orangeGlassPane);
                inventory.setItem(39, orangeGlassPane);
                inventory.setItem(40, orangeGlassPane);
                inventory.setItem(41, orangeGlassPane);
                inventory.setItem(42, orangeGlassPane);
                inventory.setItem(43, blackGlassPane);
                inventory.setItem(44, blackGlassPane);
                inventory.setItem(45, blackGlassPane);
                inventory.setItem(46, orangeGlassPane);
                inventory.setItem(47, orangeGlassPane);
                inventory.setItem(48, resourcePack);
                inventory.setItem(49, orangeGlassPane);
                inventory.setItem(50, orangeGlassPane);
                inventory.setItem(51, orangeGlassPane);
                inventory.setItem(52, blackGlassPane);
                inventory.setItem(53, blackGlassPane);
            }
        });
    }
}
