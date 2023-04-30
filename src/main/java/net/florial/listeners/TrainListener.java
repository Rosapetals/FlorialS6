package net.florial.listeners;

import de.netzkronehd.wgregionevents.events.RegionEnteredEvent;
import net.florial.Florial;
import net.florial.features.quests.Quest;
import net.florial.features.quests.QuestType;
import net.florial.utils.math.NumberGenerator;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TrainListener implements Listener {



    @EventHandler
    public void onRegionEntered(RegionEnteredEvent e) {

        if (!(e.getRegion().getId().contains("train"))
        || Florial.getQuest().containsKey(e.getPlayer().getUniqueId())) return;

        Player p = e.getPlayer();

        switch (Florial.getPlayerData().get(p.getUniqueId()).getSpecieId()) {

            case 1 -> Quest.give(p, true, new Quest("Pounce on the rocks", QuestType.POUNCE, NumberGenerator.generate(QuestType.POUNCE.getMinAmount(), QuestType.POUNCE.getMaxAmount()), null, null, null, 0));
            case 2 -> Quest.give(p, true, new Quest("Burrow by Right-Clicking dirt", QuestType.BURROW, NumberGenerator.generate(QuestType.BURROW.getMinAmount(), QuestType.BURROW.getMaxAmount()), null, null, null, 0));
            case 3 -> Quest.give(p, true, new Quest("Harvest crops", QuestType.HARVEST, NumberGenerator.generate(QuestType.HARVEST.getMinAmount(), QuestType.HARVEST.getMaxAmount()), null, null, null, 0));

        }

        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1, 1);


    }
}
