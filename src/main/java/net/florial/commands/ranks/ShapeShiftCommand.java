package net.florial.commands.ranks;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.CatWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.FoxWatcher;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Player;

public class ShapeShiftCommand extends BaseCommand {


    @CommandAlias("shapeshift")
    @CommandPermission("pearlite")
    public void pearLiteShapeShift(Player p, String s) {

        if (s.contains("fox")) {
            shapeShift(p, DisguiseType.FOX);
        } else if (s.contains("cat")) {
            shapeShift(p, DisguiseType.CAT);
        } else if (s.contains("human")) {
            shapeShift(p, null);
        }

    }


    private static void shapeShift(Player p, DisguiseType d) {

        if (d == null) {
            if (DisguiseAPI.getDisguise(p) != null) DisguiseAPI.getDisguise(p).stopDisguise();
            return;
        }

        MobDisguise mobDisguise = new MobDisguise(d, false);

        mobDisguise.setHearSelfDisguise(true);
        mobDisguise.setEntity(p);
        mobDisguise.startDisguise();

        FlagWatcher watcher = mobDisguise.getWatcher();

        switch (d) {
            case FOX -> ((FoxWatcher) watcher).setType(Fox.Type.SNOW);
            case CAT -> ((CatWatcher) watcher).setType(Cat.Type.JELLIE);
        }


    }
}

