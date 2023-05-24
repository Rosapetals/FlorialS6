package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import net.florial.menus.DailyRewardMenu;
import org.bukkit.entity.Player;

public class DailyRewardCommand extends BaseCommand {

    DailyRewardMenu rewardMenu = new DailyRewardMenu();

    @CommandAlias("dailyreward")
    public void dailyReward(Player p) {

        rewardMenu.dailyRewardMenu(p);

    }


}