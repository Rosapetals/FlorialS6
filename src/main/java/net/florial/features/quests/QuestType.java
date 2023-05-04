package net.florial.features.quests;

import lombok.Getter;

import java.util.List;
import java.util.Random;

public enum QuestType {
    CRAFT(64, 15),
    KILL(36, 10),
    EAT(35, 15),
    DELIVER(100, 25),
    FISH(10, 5),

    // specialQuests

    BURROW(70, 50),
    POUNCE(70, 50),
    HARVEST(70, 50),

    // guidanceQuests

    WILD(1, 1);
    @Getter private final int maxAmount;

    @Getter private final int minAmount;

    QuestType(int maxAmount, int minAmount) {
        this.maxAmount = maxAmount;
        this.minAmount = minAmount;
    }

    private static final List<QuestType> VALUES = List.of(values());
    private static final Random RANDOM = new Random();

    public static QuestType randomQuestType() {
        return VALUES.get(RANDOM.nextInt(VALUES.subList(0, 5).size()));
    }
}
