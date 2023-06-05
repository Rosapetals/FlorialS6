package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import net.florial.menus.QuestMenu;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class QuestCommand extends BaseCommand {

    private static final QuestMenu questMenu = new QuestMenu();

    @CommandAlias("quest")
    @Default
    public static void openQuestMenu(Player p) {

        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 2, 1);
        questMenu.questsMenu(p);
    }

}
