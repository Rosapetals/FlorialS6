package net.florial.features.quests.events.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.florial.features.quests.Quest;
import net.florial.features.quests.QuestType;
import net.florial.features.quests.events.QuestEvent;
import org.bukkit.entity.Player;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestProgressEvent extends QuestEvent {

    Player player;
    Quest quest;

    QuestType type;

    public QuestProgressEvent(Player player, Quest quest, QuestType type) {
        this.player = player;
        this.quest = quest;
        this.type = type;
    }

}
