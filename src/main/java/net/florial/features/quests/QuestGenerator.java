package net.florial.features.quests;

import net.florial.utils.math.NumberGenerator;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.List;
import java.util.Random;

public class QuestGenerator {

    private static final List<Material> craftTypes = List.of(Material.SAND);
    private static final List<EntityType> mobTypes = List.of(EntityType.SHEEP);
    private static final List<Material> itemTypes = List.of(Material.GOLD_INGOT);
    private static final List<Material> foodTypes = List.of(Material.BEEF);

    private static final Random random = new Random();

    public QuestGenerator() {}

    public Quest generateQuest() {
        QuestType type = QuestType.randomQuestType();
        int target = NumberGenerator.generate(type.getMinAmount(), type.getMaxAmount());

        switch (type) {
            case CRAFT -> {
                Material craftType = craftTypes.get(random.nextInt(craftTypes.size()));
                return new Quest("Craft " + target + " " + craftType.name().toLowerCase(), type, target, craftType, null, null, 0);
            }
             case KILL -> {
                 EntityType mobType = mobTypes.get(random.nextInt(mobTypes.size()));
                 return new Quest("Kill " + target + " " + mobType.name().toLowerCase(), type, target, null,  mobType, null, 0);
             }
            case EAT -> {
                Material itemType = foodTypes.get(random.nextInt(itemTypes.size()));
                return new Quest("Collect and Eat " + target + " " + itemType.name().toLowerCase(), type, target, null, null, itemType, 0);
            }
            case DELIVER -> {
                Material itemType = itemTypes.get(random.nextInt(itemTypes.size()));
                return new Quest("Bring " + target + " " + itemType.name().toLowerCase() + " to the Collection Bin at /warp bin", type, target, null, null, itemType, 0);
            }
        }
        return null;
    }

}
