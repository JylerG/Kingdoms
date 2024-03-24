package games.kingdoms.kingdoms.admin.CustomNPCs.menus;

import games.kingdoms.kingdoms.Kingdoms;
import games.kingdoms.kingdoms.admin.CustomNPCs.managers.ForsakenForgeManager;
import games.kingdoms.kingdoms.admin.CustomNPCs.models.Item;
import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ForgeMenu extends Menu {

    private final ForsakenForgeManager forgeManager;
    public ForgeMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.forgeManager = Kingdoms.getPlugin().getForgeManager();
    }

    @Override
    public String getMenuName() {
        return "Forge Menu";
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

        forgeManager.getForgeItems().forEach(quest -> {
            ItemStack item;

            if (quest instanceof Item itemQuest) {

                boolean isOnQuest = false;

                item = makeItem(Material.DIAMOND_SWORD,
                        ColorTranslator.translateColorCodes("&6&l" + itemQuest.getName()),
                        ColorTranslator.translateColorCodes("&5" + itemQuest.getDescription()),
                        " ",
                        "&7Reward: &6" + itemQuest.getReward() + "&a coins",
                        " ",
                        (isOnQuest ? "&cYou are currently doing this quest!" : "&aClick to accept!"));

                inventory.addItem(item);
            }
        });
    }
}
