package net.florial.features.quests;

import net.florial.utils.math.NumberGenerator;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.List;
import java.util.Random;

public class QuestGenerator {

    private final List<Material> blockTypes = List.of(Material.SAND);
    private final List<EntityType> mobTypes = List.of(EntityType.SHEEP);
    private final Random random = new Random();

    public QuestGenerator() {}

    public Quest generateQuest() {
        QuestType type = QuestType.randomQuestType();
        int target = NumberGenerator.generate(type.getMinAmount(), type.getMaxAmount());

        switch (type) {
            case COLLECT -> {
                Material blockType = blockTypes.get(random.nextInt(blockTypes.size()));
                return new Quest("Collect " + target + " " + blockType.name().toLowerCase(), type, target, blockType, null, 0);
            }
             case KILL -> {
                  EntityType mobType = mobTypes.get(random.nextInt(mobTypes.size()));
                return new Quest("Kill " + target + " " + mobType.name().toLowerCase(), type, target, null, mobType, 0);
            }
        }
        return null;
    }

}
