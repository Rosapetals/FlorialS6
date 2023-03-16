package net.florial.utils.game;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

public class LineOfSight {


    public static boolean get(Player p, Material target, Integer radius){
        List<Block> lineOfSight = p.getLineOfSight(null, radius);
        for (Block b : lineOfSight) {if (b.getType() == target) return true;}
        return false;
    }
}
