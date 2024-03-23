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

public class QuestMenu extends Menu {
    private final QuestManager questManager;

    public QuestMenu(PlayerMenuUtility playerMenuUtility) {
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

        questManager.getAvailableQuests().forEach(quest -> {
            ItemStack item;

            if (quest instanceof KillQuest killQuest) {

                boolean isOnQuest = false;

                item = makeItem(Material.DIAMOND_SWORD,
                        ColorTranslator.translateColorCodes("&6&l" + killQuest.getName()),
                        ColorTranslator.translateColorCodes("&5" + killQuest.getDescription()),
                        " ",
                        "&7Reward: &6" + killQuest.getReward() + "&a coins",
                        " ",
                        (isOnQuest ? "&cYou are currently doing this quest!" : "&aClick to accept!"));

                inventory.addItem(item);
            }
        });
    }
}
