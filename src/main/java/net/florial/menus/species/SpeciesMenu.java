package net.florial.menus.species;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.features.age.Age;
import net.florial.features.skills.Skills;
import net.florial.models.PlayerData;
import net.florial.species.Species;
import net.florial.utils.Cooldown;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.GetCustomSkull;
import net.florial.utils.general.VaultHandler;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Stream;

public class SpeciesMenu {

    final net.florial.utils.general.GetCustomSkull GetCustomSkull = new GetCustomSkull();
    private static final Skills Skills = new Skills();

    private static final net.florial.menus.species.InstinctsMenu InstinctsMenu = new InstinctsMenu();

    private static final net.florial.menus.species.GrowthMenu GrowthMenu = new GrowthMenu();

    public void speciesMenu(Player p) {

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE238"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                "#ff79a1&lSKILLS\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                "#ff79a1&lINSTINCTS\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                "#ff79a1&lGROWTH\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MUSIC_DISC_CAT), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", "  #ff79a1&l︳ " +
                                                "CASH TO DNA\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳ • YOUR CASH: #ffa2c4 "
                                                + VaultHandler.getBalance(p) + "\n #ff79a1&l︳  CASH NEEDED:\n #ffa2c4&l︳ •#ffa2c4 $10,000 per DNA\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳#ff79a1&l INFORMATION\n #ffa2c4&l︳ • INFO:" +
                                                "\n #ffa2c4&l︳#ffa2c4 • Get cash by selling items in /sell" +
                                                "\n #ffa2c4&l︳#ffa2c4 • Use DNA to buy Instincts, upgrade skills," +
                                                "\n #ffa2c4&l︳#ffa2c4 • or to age up. The choice is yours."
                                                + "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙", false)).map(i -> NBTEditor.set(i, 1010, "CustomModelData"))
                                .toList();

                        List<ItemStack> species = Stream.of(CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkyNGJlNWY3NGI2NDMxNjYwZmQ1YzRjYzAzMzRkOTFlNzdlNzdmZGQ4OGQyNGVhODVlYjBmMzgzODRjN2YxYSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                                "#ffa2c4&lFOX", "#ffa2c4&lBITE:#ffa2c4 Melee with an empty hand.", "You will bite heavy.",
                                                "#ffa2c4&lHEAT ACCLIMATION:#ffa2c4 Your Lavakit", "heritage shows. Get Fire Resistance when hurt by lava/fire.",
                                                "#ffa2c4&lSPEED II:#ffa2c4 You're faster.", "Make the most of it.",
                                                "#ffa2c4&lNIGHT VISION:#ffa2c4 You can see in dark.", "Make the most of it.",
                                                "#ffa2c4&lBURROWER:#ffa2c4 Upgrade your burrow skill to", "break blocks faster and get more materials",
                                                "#ffa2c4&lWEAK:#ffa2c4 Live with 7 hearts", "Make the most of it.",
                                                "#ffa2c4&lCARNIVORE:#ffa2c4 Vegetables aren't all that", "healthy. Except Sweet Berries!",
                                                "", "", "", "")), false),
                                        CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UzZTAzYTk2NzE4NDAyMjNhOTZhOTIwOTE0ZDFiODczMTQwNDRjYzZkMzJhYWI3YzI3ZTFmNjQwZWNjMjFkNSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                                "#ffa2c4&lCAT", "#ffa2c4&lNINE LIVES:#ffa2c4 Bounce back when close to death.", "Has a cooldown.",
                                                "#ffa2c4&lSCRATCH:#ffa2c4 Hit with an empty hand to", "scratch nearby entities in a radius.",
                                                "#ffa2c4&lPURR:#ffa2c4 Shift+Right-Click anywhere to give", "yourself & those around you Regen II.",
                                                "#ffa2c4&lNIGHT VISION:#ffa2c4 You can see in dark.", "Make the most of it.",
                                                "#ffa2c4&lQUICKFEET:#ffa2c4 Land on your feet.", "No fall damage! + Speed I.",
                                                "#ffa2c4&lSMALL:#ffa2c4 Live with 6 hearts", "Make the most of it.",
                                                "#ffa2c4&lCARNIVORE:#ffa2c4 Vegetables aren't all that", "healthy.",
                                                "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE0N2MyMGM0ZjM1YmQyN2QzZDQxOTEyNzkyYTc5OGU5ZjRmOWJiZmUwNGYwZDMyNTVkOWJjYWRmOGE0MWFhZSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━&cPEARLITE-EXCLUSIVE#ff79a1&l━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lPEARLITE", "#ffa2c4&lSHAPESHIFTER:#ffa2c4 /shapeshift fox/cat ", "to switch forms.",
                                        "#ffa2c4&lHEALER:#ffa2c4 Right-Click any entity to heal", "them depending on your heal skill",
                                        "#ffa2c4&lFLIGHT:#ffa2c4 Shift+Left-Click while looking", "up to fly!",
                                        "#ffa2c4&lNIGHT VISION:#ffa2c4 You can see in dark.", "Make the most of it.",
                                        "#ffa2c4&lQUICKFEET:#ffa2c4 Land on your feet.", "No fall damage! + Speed I.",
                                        "#ffa2c4&lRESILIENT:#ffa2c4 Live with 16 hearts", "Make the most of it.",
                                        "#ffa2c4&lOMNIVOROUS:#ffa2c4 Everything looks delectably", "healthy.",
                                        "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGQ5NmY1YmUxNzIzNGE5N2I2MDMzZGMxM2FkZjRjMzliYTNjMWFkYzAxMjRmYzlkMmNiZjIxYjE4ZDI1NTNjMSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lHUMAN", "&c&lWait a minute... ", "&cwhat're you doing in the wilderness?",
                                        "#ffa2c4&lMEDICAL AID:#ffa2c4 Upgrade your Medical skill to heal", "yourself fast by left-clicking with paper",
                                        "", "",
                                        "", "",
                                        "", "",
                                        "#ffa2c4&lWEAK STOMACH:#ffa2c4 Raw meat is.. simply", "sickening..",
                                        "#ffa2c4&lOMNIVOROUS:#ffa2c4 Everything looks delectably", "healthy.",
                                        "", "", "", "")), false)).toList();


                        contents.set(List.of(23), IntelligentItem.of(species.get(1), event -> {Species.become(p, "CAT");}));
                        contents.set(List.of(21), IntelligentItem.of(species.get(0), event -> Species.become(p, "FOX")));
                        contents.set(List.of(39), IntelligentItem.of(species.get(2), event -> Species.become(p, "PEARLIN")));
                        contents.set(List.of(41), IntelligentItem.of(species.get(3), event -> Species.become(p, "HUMAN")));


                        //skills
                        contents.set(List.of(5, 4, 3), IntelligentItem.of(entries.get(0), event -> loadMenu(p, 1)));

                        //growth
                        contents.set(List.of(34, 35), IntelligentItem.of(entries.get(2), event -> loadMenu(p, 3)));

                        //instincts
                        contents.set(List.of(27, 28, 29), IntelligentItem.of(entries.get(1), event -> loadMenu(p, 2)));

                        //cash to dna
                        contents.set(List.of(22), IntelligentItem.of(entries.get(3), event -> cashConvert(p)));



                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }

    private static void loadMenu(Player p, int type) {
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1);
        p.closeInventory();

        switch (type) {
            case 1 -> Skills.skillMenu(p);
            case 2 -> InstinctsMenu.instinctMenu(p);
            case 3 -> GrowthMenu.growthMenu(p);

        }
    }

    private static void cashConvert(Player p){

        if (VaultHandler.getBalance(p) >= 10000) {

            if (Cooldown.isOnCooldown("c2", p)) return;

            PlayerData data = Florial.getPlayerData().get(p.getUniqueId());

            data.setDna(data.getDna() + (data.getAge() == Age.ELDER ? 2 : 1));

            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_FLUTE, 2, 1);

            VaultHandler.removeMoney(p, 10000);

            Cooldown.addCooldown("c2", p, 1);

        } else {
            p.closeInventory();
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 2, 1);
            p.sendMessage("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You need at least $10,000 to get 1 DNA.");

        }
    }


        private static String format(List<String> iterations){
        return "  #ff79a1&l︳ " + iterations.get(0) +
                "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n #ffa2c4&l︳ •  #ffa2c4 "
                + iterations.get(1) + "\n#ffa2c4 "
                + iterations.get(2) + "\n #ffa2c4&l︳ •  #ffa2c4 "
                + iterations.get(3) + "\n#ffa2c4 "
                + iterations.get(4) + "\n #ffa2c4&l︳ •  #ffa2c4 "
                + iterations.get(5) + "\n#ffa2c4 "
                + iterations.get(6) + "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙\n"
                + "#ffa2c4&l︳ • #ffa2c4 "
                + iterations.get(7) + "\n#ffa2c4 "
                + iterations.get(8) + "\n #ffa2c4&l︳ • #ffa2c4 "
                + iterations.get(9) + "\n#ffa2c4 "
                + iterations.get(10) + "\n #ffa2c4&l︳ • #ffa2c4 "
                + iterations.get(11) + "\n#ffa2c4 "
                + iterations.get(12) + "\n #ffa2c4&l︳ • #ffa2c4 "
                + iterations.get(13) + "\n#ffa2c4 "
                + iterations.get(14) + "\n #ffa2c4&l︳ • #ffa2c4 "
                + iterations.get(15) + "\n#ffa2c4 "
                + iterations.get(16) + "\n #ffa2c4&l︳ • #ffa2c4 "
                + iterations.get(17) + "\n#ffa2c4 "
                + iterations.get(18) + "\n #ffa2c4&l︳ • [CLICK HERE]" +
                "\n #ff79a1&l┕━━━━━━━━━━━━━━━━━━┙";}

}
