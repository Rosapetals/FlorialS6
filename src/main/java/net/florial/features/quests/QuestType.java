package net.florial.features.quests;

import lombok.Getter;

import java.util.List;
import java.util.Random;

public enum QuestType {
    CRAFT(15, 64),
    KILL(10, 36),
    EAT(15, 35),
    DELIVER(25, 100),
    FISH(5, 10),

    // specialQuests

    BURROW(50, 250),
    POUNCE(50, 100),
    HARVEST(50, 100),

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
