package net.florial.features.quests;

import lombok.Getter;
import lombok.Setter;
import net.florial.Florial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class Quest {

    @Getter private final String name;
    @Getter private final QuestType type;
    @Getter private final int target;
    @Getter
    private Material blockType;
    @Getter private EntityType mobType;
   @Getter @Setter
   private Integer progress;

    public Quest(String name, QuestType type, int target, Material blockType, EntityType mobType, Integer progress) {
        this.name = name;
        this.type = type;
        this.target = target;
        this.blockType = blockType;
        this.mobType = mobType;
        this.progress = progress;

    }

    @Override
    public String toString() {
        return name + " (" + progress + "/" + target + ")";
    }


}
