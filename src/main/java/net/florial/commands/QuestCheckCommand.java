package net.florial.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import net.florial.Florial;
import org.bukkit.entity.Player;

public class QuestCheckCommand extends BaseCommand {

    @CommandAlias("checkquest")
    public static void questCheck(Player player) {

        player.sendMessage("" + Florial.getQuest().get(player.getUniqueId()).toString());
        player.sendMessage("" + Florial.getQuest().get(player.getUniqueId()).getProgress());
    }
}
