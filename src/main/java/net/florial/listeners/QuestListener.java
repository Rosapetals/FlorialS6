package net.florial.listeners;

import net.florial.Florial;
import net.florial.features.quests.Quest;
import net.florial.features.quests.QuestType;
import net.florial.models.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class QuestListener implements Listener {

    @EventHandler
    public void questCollectChecker(BlockBreakEvent event) {

        if (!(Florial.getQuest().containsKey(event.getPlayer().getUniqueId()))) return;

        Quest quest = Florial.getQuest().get(event.getPlayer().getUniqueId());

        if (quest.getType() != QuestType.COLLECT || quest.getBlockType() != event.getBlock().getType()) return;

        completionChecker(event.getPlayer(), quest);
        event.getPlayer().sendMessage("yes");
    }

    @EventHandler
    public void questMobKill(EntityDeathEvent event) {

        Player killer = event.getEntity().getKiller();
        if (killer == null || !Florial.getQuest().containsKey(killer.getUniqueId())) return;

        Quest quest = Florial.getQuest().get(killer.getUniqueId());

        if (quest.getType() != QuestType.KILL || quest.getMobType() != event.getEntityType()) return;

        completionChecker(killer, quest);


    }

    private void completionChecker(Player p, Quest quest){

        p.sendMessage("" + quest.getProgress());

        quest.setProgress(quest.getProgress() + 1);

        p.sendMessage("" + quest.getProgress());

        Florial.getQuest().put(p.getUniqueId(), quest);

        if (!(quest.getProgress() >= quest.getTarget())) return;

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        p.sendMessage("complete 2");

        Florial.getQuest().remove(p.getUniqueId());
        data.setGrowth(data.getGrowth() + 1);
    }
}
