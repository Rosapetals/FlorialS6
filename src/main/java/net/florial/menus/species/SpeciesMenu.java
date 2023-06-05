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

    private static final RebirthMenu rebirthMenu = new RebirthMenu();

    private static final net.florial.menus.species.GrowthMenu GrowthMenu = new GrowthMenu();

    public void speciesMenu(Player p) {

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE238"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(new ItemStack(Material.MAP), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                "#5a372c&lSKILLS\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                "#5a372c&lINSTINCTS\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MAP), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑",
                                                "#5a372c&lGROWTH\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false),
                                        CustomItem.MakeItem(new ItemStack(Material.MUSIC_DISC_CAT), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", "  #5a372c&l︳ " +
                                                "CASH TO DNA\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙\n #6e4837&l︳ • YOUR CASH: #6e4837 "
                                                + VaultHandler.getBalance(p) + "\n #5a372c&l︳  CASH NEEDED:\n #6e4837&l︳ •#6e4837 $10,000 per DNA\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙\n #6e4837&l︳#5a372c&l INFORMATION\n #5a372c&l︳ • INFO:" +
                                                "\n #6e4837&l︳#6e4837 • Get cash by selling items in /sell" +
                                                "\n #6e4837&l︳#6e4837 • Use DNA to buy Instincts, upgrade skills," +
                                                "\n #6e4837&l︳#6e4837 • or to age up. The choice is yours."
                                                + "\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙", false)).map(i -> NBTEditor.set(i, 1010, "CustomModelData"))
                                .toList();

                        List<ItemStack> species = Stream.of(CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkyNGJlNWY3NGI2NDMxNjYwZmQ1YzRjYzAzMzRkOTFlNzdlNzdmZGQ4OGQyNGVhODVlYjBmMzgzODRjN2YxYSJ9fX0"), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                                "#6e4837&lFOX", "#6e4837&lBITE:#6e4837 Melee with an empty hand.", "You will bite heavy.",
                                                "#6e4837&lHEAT ACCLIMATION:#6e4837 Your Lavakit", "heritage shows. Get Fire Resistance when hurt by lava/fire.",
                                                "#6e4837&lSPEED II:#6e4837 You're faster.", "Make the most of it.",
                                                "#6e4837&lNIGHT VISION:#6e4837 You can see in dark.", "Make the most of it.",
                                                "#6e4837&lBURROWER:#6e4837 Upgrade your burrow skill to", "break blocks faster and get more materials",
                                                "#6e4837&lWEAK:#6e4837 Live with 7 hearts", "Make the most of it.",
                                                "#6e4837&lCARNIVORE:#6e4837 Vegetables aren't all that", "healthy. Except Sweet Berries!",
                                                "", "", "", "")), false),
                                        CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2UzZTAzYTk2NzE4NDAyMjNhOTZhOTIwOTE0ZDFiODczMTQwNDRjYzZkMzJhYWI3YzI3ZTFmNjQwZWNjMjFkNSJ9fX0"), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                                "#6e4837&lCAT", "#6e4837&lNINE LIVES:#6e4837 Bounce back when close to death.", "Has a cooldown.",
                                                "#6e4837&lSCRATCH:#6e4837 Hit with an empty hand to", "scratch nearby entities in a radius.",
                                                "#6e4837&lPURR:#6e4837 Shift+Right-Click anywhere to give", "yourself & those around you Regen II.",
                                                "#6e4837&lNIGHT VISION:#6e4837 You can see in dark.", "Make the most of it.",
                                                "#6e4837&lQUICKFEET:#6e4837 Land on your feet.", "No fall damage! + Speed I.",
                                                "#6e4837&lSMALL:#6e4837 Live with 6 hearts", "Make the most of it.",
                                                "#6e4837&lCARNIVORE:#6e4837 Vegetables aren't all that", "healthy.",
                                                "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE0N2MyMGM0ZjM1YmQyN2QzZDQxOTEyNzkyYTc5OGU5ZjRmOWJiZmUwNGYwZDMyNTVkOWJjYWRmOGE0MWFhZSJ9fX0"), "#5a372c&l ┍━━━━━━━━━&cPEARLITE-EXCLUSIVE#ff79a1&l━━━━━━━━━┑", format(List.of(
                                        "#6e4837&lPEARLITE", "#6e4837&lSHAPESHIFTER:#6e4837 /shapeshift fox/cat ", "to switch forms.",
                                        "#6e4837&lHEALER:#6e4837 Right-Click any entity to heal", "them depending on your heal skill",
                                        "#6e4837&lFLIGHT:#6e4837 Shift+Left-Click while looking", "up to fly!",
                                        "#6e4837&lNIGHT VISION:#6e4837 You can see in dark.", "Make the most of it.",
                                        "#6e4837&lQUICKFEET:#6e4837 Land on your feet.", "No fall damage! + Speed I.",
                                        "#6e4837&lRESILIENT:#6e4837 Live with 16 hearts", "Make the most of it.",
                                        "#6e4837&lOMNIVOROUS:#6e4837 Everything looks delectably", "healthy.",
                                        "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGQ5NmY1YmUxNzIzNGE5N2I2MDMzZGMxM2FkZjRjMzliYTNjMWFkYzAxMjRmYzlkMmNiZjIxYjE4ZDI1NTNjMSJ9fX0"), "#5a372c&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#6e4837&lHUMAN", "&c&lWait a minute... ", "&cwhat're you doing in the wilderness?",
                                        "#6e4837&lMEDICAL AID:#6e4837 Upgrade your Medical skill to heal", "yourself fast by left-clicking with paper",
                                        "", "",
                                        "", "",
                                        "", "",
                                        "#6e4837&lWEAK STOMACH:#6e4837 Raw meat is.. simply", "sickening..",
                                        "#6e4837&lOMNIVOROUS:#6e4837 Everything looks delectably", "healthy.",
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
            case 2 -> rebirthMenu.rebirthMenu(p);
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
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You need at least $10,000 to get 1 DNA."));

        }
    }


        private static String format(List<String> iterations){
        return "  #5a372c&l︳ " + iterations.get(0) +
                "\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙\n #6e4837&l︳ •  #6e4837 "
                + iterations.get(1) + "\n#6e4837 "
                + iterations.get(2) + "\n #6e4837&l︳ •  #6e4837 "
                + iterations.get(3) + "\n#6e4837 "
                + iterations.get(4) + "\n #6e4837&l︳ •  #6e4837 "
                + iterations.get(5) + "\n#6e4837 "
                + iterations.get(6) + "\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙\n"
                + "#6e4837&l︳ • #6e4837 "
                + iterations.get(7) + "\n#6e4837 "
                + iterations.get(8) + "\n #6e4837&l︳ • #6e4837 "
                + iterations.get(9) + "\n#6e4837 "
                + iterations.get(10) + "\n #6e4837&l︳ • #6e4837 "
                + iterations.get(11) + "\n#6e4837 "
                + iterations.get(12) + "\n #6e4837&l︳ • #6e4837 "
                + iterations.get(13) + "\n#6e4837 "
                + iterations.get(14) + "\n #6e4837&l︳ • #6e4837 "
                + iterations.get(15) + "\n#6e4837 "
                + iterations.get(16) + "\n #6e4837&l︳ • #6e4837 "
                + iterations.get(17) + "\n#6e4837 "
                + iterations.get(18) + "\n #6e4837&l︳ • [CLICK HERE]" +
                "\n #5a372c&l┕━━━━━━━━━━━━━━━━━━┙";}

}
