package net.florial.features.quests;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import net.florial.Florial;
import net.florial.features.age.Age;
import net.florial.features.quests.events.impl.QuestProgressEvent;
import net.florial.features.upgrades.Upgrade;
import net.florial.models.PlayerData;
import net.florial.utils.game.FireWorkSpawner;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.VaultHandler;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class QuestProgressManager implements Listener {

    @EventHandler
    public void onQuestProgress(QuestProgressEvent event) {

        Quest quest = event.getQuest();

        if (quest == null || quest.getType() != event.getType()) return;

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

        QuestType type = quest.getType();
        Florial.getQuest().remove(p.getUniqueId());

        data.setGrowth(data.getGrowth() + 1);
        data.setDna(data.getDna() + (data.getAge() == Age.ELDER ? 2 : data.getUpgrades().get(Upgrade.DNA) != null ? 3 : 1));

        FireWorkSpawner.spawn(3, Color.WHITE, Color.WHITE, p);
        ItemStack key1 = NBTEditor.set(CustomItem.MakeItem(new ItemStack(Material.GLISTERING_MELON_SLICE), "#ff7a8b&lTulip Crate Key", "", false), 1, "CustomModelData");
        key1= NBTEditor.set(key1, 1, "Crate");
        p.getInventory().addItem(key1);

        if (type.getMinAmount() == 50) p.setLevel(p.getLevel() + 5);

        if (type == QuestType.WILD) {
            VaultHandler.addMoney(p, 1000);
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Life wasn't kind to you, you're only a #ff3c55&lKIT"));
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Take quests from #ff3c55&l/grow&f to age up by pressing the #ff3c55&l+&f button in /grow. Then, age up by pressing the #ff3c55&l'GROW'&f button in /grow."));
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Do #ff3c55&l/skills&f and upgrade each skill to gain more benefits"));
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Don't understand? That's okay! Just ask for help or take your time."));
        }

    }
}
