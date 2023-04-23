package net.florial.features.quests;

import net.florial.Florial;
import net.florial.features.age.Age;
import net.florial.features.quests.events.impl.QuestProgressEvent;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import org.bukkit.Sound;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class QuestProgressManager implements Listener {

    @EventHandler
    public void onQuestProgress(QuestProgressEvent event) {

        Quest quest = event.getQuest();

        if (quest.getType() != event.getType()) return;

        completionChecker(event.getPlayer(), quest);

    }

    private static void completionChecker(Player p, Quest quest){

        UUID u = p.getUniqueId();
        BossBar questBar = Florial.getQuestBar().get(u);

        quest.setProgress(quest.getProgress() + 1);
        Florial.getQuest().put(u, quest);

        questBar.setProgress((double) quest.getProgress() / (double) quest.getTarget());
        questBar.setTitle("Quest Progress: " + quest);

        if (!(quest.getProgress() >= quest.getTarget())) return;

        PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Completed quest!"));
        p.playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 2);

        Florial.getQuestBar().get(u).removePlayer(p);
        Florial.getQuestBar().remove(u);

        Florial.getQuest().remove(p.getUniqueId());

        data.setGrowth(data.getGrowth() + 1);
        data.setDna(data.getDna() + (data.getAge() == Age.ELDER ? 2 : 1));
        p.setLevel(p.getLevel() + 3);

    }
}
