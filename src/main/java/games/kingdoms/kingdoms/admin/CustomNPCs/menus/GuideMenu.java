package games.kingdoms.kingdoms.admin.CustomNPCs.menus;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.admin.CustomNPCs.managers.QuestManager;
import games.kingdoms.kingdoms.admin.CustomNPCs.models.KillQuest;
import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GuideMenu extends Menu {

    private final QuestManager questManager;

    public GuideMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.questManager = Kingdoms.getPlugin().getQuestManager();
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

        inventory.addItem(blackGlassPane);
        inventory.addItem(blackGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(blackGlassPane);
        inventory.addItem(blackGlassPane);
        inventory.addItem(blackGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(new ItemStack(Material.NETHER_STAR));
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(blackGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(new ItemStack(Material.GOLDEN_HELMET));
        inventory.addItem(orangeGlassPane);
        inventory.addItem(warzone);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(merchants);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(blueprints);
        inventory.addItem(blackGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(blackGlassPane);
        inventory.addItem(blackGlassPane);
        inventory.addItem(blackGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(resourcePack);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(orangeGlassPane);
        inventory.addItem(blackGlassPane);
        inventory.addItem(blackGlassPane);
    }
}
