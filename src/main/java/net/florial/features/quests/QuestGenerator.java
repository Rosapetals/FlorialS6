package net.florial.features.quests;

import net.florial.utils.math.NumberGenerator;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestGenerator {

    private  List<Material> blockTypes;
    private List<EntityType> mobTypes;
    private Random random;

    public QuestGenerator(List<Material> blockTypes, List<EntityType> mobTypes) {
        this.blockTypes = blockTypes;
        this.mobTypes = mobTypes;
        this.random = new Random();
    }

    public Quest generateQuest() {
        QuestType type = QuestType.randomQuestType();
        int target = NumberGenerator.generate(type.getMinAmount(), type.getMaxAmount());

        switch (type) {
            case COLLECT -> {
                Material blockType = blockTypes.get(random.nextInt(blockTypes.size()));
                return new Quest("Collect " + target + " " + blockType.name().toLowerCase(), type, target, blockType, null, null);
            }
            case KILL -> {
                EntityType mobType = mobTypes.get(random.nextInt(mobTypes.size()));
                return new Quest("Kill " + target + " " + mobType.name().toLowerCase(), type, target, null, mobType, null);
            }
        }
        return null;
    }

}
