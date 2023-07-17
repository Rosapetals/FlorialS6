package net.florial.species.disguises;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.AgeableWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.CatWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.FoxWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.SlimeWatcher;
import net.florial.species.Species;
import net.florial.species.events.impl.SpeciesTablistEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Player;

import java.util.List;

public class Morph {

    public final List<String> skinTextures = List.of(
            "http://textures.minecraft.net/texture/7e3e03a9671840223a96a920914d1b87314044cc6d32aab7c27e1f640ecc21d5",
            "http://textures.minecraft.net/texture/f213961f49c1e3e6e45863bb11c781bf060f40167d508a101ec9a363f2147bdf",
            "http://textures.minecraft.net/texture/cca8a5f90689b806646be6625283a1bdf3a4f115f03ba2945550bb325249faa1",
            "http://textures.minecraft.net/texture/7f303675f4725105ff47cea69d7a4077f5ef0778bb3abf4efff66639df326d48",
            "http://textures.minecraft.net/texture/8e216e48547417173ae7389f9062a48e86203b57e3b2d9f784472327b474c5ae",
            "http://textures.minecraft.net/texture/dfebff4cf3f650d5bf852c27fbc9d9671fc3d1f2c954c620a859a01f9bc82352",
            "http://textures.minecraft.net/texture/b2011f08f1591e9f1020e374146d9ea7ddfced0beeb855a28ade3348098ccc3b",
            "http://textures.minecraft.net/texture/27f596afb869f806f61085a499b7e75148913d3e60601f166258dfbcb82a3bbf",
            "http://textures.minecraft.net/texture/fd3a837135c19cc756848fcf3596bb1d0aaccbb28526342fd572fac5d1bd5676",
            "http://textures.minecraft.net/texture/d66db92e9b1543a9f5d1d4cc3bbb3754d1de7c8d150b66a06961bb51fb5d224e",
            "http://textures.minecraft.net/texture/e5b678587b1c0c9c9f6a94d23ccfd6e5a77a57d1426086ca9a35adab7480519f"
    );

    public final List<String> textureNames = List.of(
            "CALICO",
            "BLACK",
            "RAGDOLL",
            "SIAMESE",
            "TABBY",
            "WHITE",
            "RED",
            "JELLIE",
            "PERSIAN",
            "ALL_BLACK",
            "BRITISH_SHORTHAIR");

    /**
    positions:

     1  = sitting
     2 = sleeping
     */
    public void activate(Player p, Integer pos, Boolean state, Boolean modification, Species species) {

        if (species.getMorph() == null) return;


        if (modification) {

            MobDisguise mobDisguise = (MobDisguise) DisguiseAPI.getDisguise(p);
            FlagWatcher watcher = mobDisguise.getWatcher();


            switch (pos) {
                case 1 -> {
                    if (species.getId() == 4 || species.getId() == 2) {
                        if (mobDisguise.getType() != DisguiseType.FOX) return;
                        ((FoxWatcher) watcher).setCrouching(state);
                    }
                }
                case 4 -> {
                    if (species.getId() == 4) {
                        if (mobDisguise.getType() == DisguiseType.FOX) {
                            ((FoxWatcher) watcher).setType(Fox.Type.SNOW);
                        } else {
                            ((CatWatcher) watcher).setType(Cat.Type.JELLIE);
                        }
                    } else {
                        ((FoxWatcher) watcher).setType(Fox.Type.RED);
                    }
                }
                case 5 -> ((AgeableWatcher)watcher).setAdult();
                case 6 -> ((AgeableWatcher)watcher).setBaby();
                case 7 -> ((CatWatcher)watcher).setType(Cat.Type.CALICO);
                case 8 -> ((CatWatcher)watcher).setType(Cat.Type.BLACK);
                case 9 -> ((CatWatcher)watcher).setType(Cat.Type.RAGDOLL);
                case 10 -> ((CatWatcher)watcher).setType(Cat.Type.SIAMESE);
                case 11 -> ((CatWatcher)watcher).setType(Cat.Type.TABBY);
                case 12 -> ((CatWatcher)watcher).setType(Cat.Type.WHITE);
                case 13 -> ((CatWatcher)watcher).setType(Cat.Type.RED);
                case 14 -> ((CatWatcher)watcher).setType(Cat.Type.JELLIE);
                case 15 -> ((CatWatcher)watcher).setType(Cat.Type.PERSIAN);
                case 16 -> ((CatWatcher)watcher).setType(Cat.Type.ALL_BLACK);
                case 17 -> ((CatWatcher)watcher).setType(Cat.Type.BRITISH_SHORTHAIR);
            }

        } else {

            MobDisguise mobDisguise = new MobDisguise(species.getMorph(), false);

            mobDisguise.setHearSelfDisguise(true);
            mobDisguise.setEntity(p);
            mobDisguise.startDisguise();
            FlagWatcher watcher = mobDisguise.getWatcher();

            if (species.getId() == 10) {
                ((CatWatcher) watcher).setType(Cat.Type.PERSIAN);
            } else {
                if (species.getId() == 13) ((SlimeWatcher) watcher).setSize(2);
            }


            if (watcher instanceof FoxWatcher fox) {
                if (species.getId() != 4) {
                    fox.setType(Fox.Type.RED);
                } else {
                    fox.setType(Fox.Type.SNOW);
                }

            }

            if (watcher instanceof CatWatcher cat) {
                if (cat.getType().equals(Cat.Type.JELLIE)) {
                    activate(p, 0, false, false, species);
                    return;
                }
            }

            SpeciesTablistEvent event = new SpeciesTablistEvent(
                    p
            );
            Bukkit.getPluginManager().callEvent(event);




        }

    }
}
