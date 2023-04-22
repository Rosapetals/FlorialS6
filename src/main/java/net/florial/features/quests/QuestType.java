package net.florial.features.quests;

import lombok.Getter;

import java.util.List;
import java.util.Random;

public enum QuestType {
    CRAFT(35, 80),
    KILL(10, 36),
    EAT(35, 80),
    DELIVER(35, 80);

    @Getter private final int maxAmount;

    @Getter private final int minAmount;

    QuestType(int maxAmount, int minAmount) {
        this.maxAmount = maxAmount;
        this.minAmount = minAmount;
    }

    private static final List<QuestType> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static QuestType randomQuestType() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
