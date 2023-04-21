package net.florial.species.disguises;

import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.CatWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.FoxWatcher;
import me.libraryaddict.disguise.disguisetypes.watchers.SlimeWatcher;
import net.florial.species.Species;
import net.florial.species.events.impl.SpeciesTablistEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;

import java.util.List;

public class Morph {

    public final List<String> skinTextures = List.of(
            "http://textures.minecraft.net/texture/7e3e03a9671840223a96a920914d1b87314044cc6d32aab7c27e1f640ecc21d5",
            "http://textures.minecraft.net/texture/490becff2f313999df856ee4b9cd4b90273df46c1867aac23b7a2395226652fe",
            "http://textures.minecraft.net/texture/7197946165027d6e8c21353443e3b103eef4bee1a1c238984fe0afb066859d45",
            "http://textures.minecraft.net/texture/29f16992f3a92c2e62fd38d5d851fd661ceb3e8a810200536ea4b12eb05f341e",
            "http://textures.minecraft.net/texture/3b355ba38901ee86a23d61e9ce101658f2417d6b9b4c3812a904ade581cb4415",
            "http://textures.minecraft.net/texture/24a8a7bf5e750f808e4fbd40894dfe746a07245dc16a113743ae35feb5b0f771",
            "http://textures.minecraft.net/texture/2cbc0bf3bcb2c51ed4453b20e741a80dc5f065550b1e351c0eafadc502257af5",
            "http://textures.minecraft.net/texture/27f596afb869f806f61085a499b7e75148913d3e60601f166258dfbcb82a3bbf",
            "http://textures.minecraft.net/texture/6d62b4a45c5fa233a4639af6fb910afc1795eb0011e46266a0865d173ebde9ab",
            "http://textures.minecraft.net/texture/5f539293ebeaba4c35f089357e5457f838fa4a5db4da83afbfc295ad2d6d6ea6",
            "http://textures.minecraft.net/texture/1fcd2e0c2b5df9482ef45f5e1d3cf07778febdd8455d3f02d326029ad79f41cf"
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
                case 1 -> ((Sittable) watcher).setSitting(state);
                case 2 -> watcher.setSleeping(state);
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
                case 5 -> ((Ageable) mobDisguise).setAdult();
            }

        } else {

            MobDisguise mobDisguise = new MobDisguise(species.getMorph(), false);

            mobDisguise.setHearSelfDisguise(true);
            mobDisguise.setEntity(p);
            mobDisguise.startDisguise();
            FlagWatcher watcher = mobDisguise.getWatcher();

            if (species.getId() == 10) ((CatWatcher) watcher).setType(Cat.Type.PERSIAN);
            if (species.getId() == 13) ((SlimeWatcher) watcher).setSize(2);


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
