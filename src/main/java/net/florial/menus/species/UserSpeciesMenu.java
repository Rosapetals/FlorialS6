package net.florial.menus.species;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.species.Species;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.GetCustomSkull;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Stream;

public class UserSpeciesMenu {

    final net.florial.utils.general.GetCustomSkull GetCustomSkull = new GetCustomSkull();

    public void userSpeciesMenu(Player p) {

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE608"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        List<ItemStack> species = Stream.of(CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmYyOTQ2MWRiYTMyMTlhZDRiNzZlZjgyMTJkNTNlMzRhODNhNThlYWNiNDFlNDU1NzQ5ZjcyMjRjZWMyZTE2YiJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lBASSBEAST", "#ffa2c4&lLIGHTNING:#ffa2c4 Melee with a sword to strike", "your foes with lightning.",
                                        "#ffa2c4&lROAR:#ffa2c4 Left-Click with an empty hand", "to roar loudly.",
                                        "#ffa2c4&lFLIGHT:#ffa2c4 Shift+Left-Click to fly", "Be sure to look up!",
                                        "#ffa2c4&lLarge::#ffa2c4 +6 hearts.", "Make the most of it.",
                                        "#ffa2c4&lALLERGIES:#ffa2c4 Allergic to bees and starchy", "foods like potatoes and carrots.",
                                        "#ffa2c4&lDAMP INTOLERANCE:#ffa2c4 Takes more damage when", "it is raining.",
                                        "#ffa2c4&lPOWERFUL: Strength II. Speed 1.", "& Resistance I.",
                                        "#ffa2c4&lBy Felix_Seven_", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzg0NTg3NTg4ODg5NmFiNmI2ZTVmMjlkNjZhYzllZjZiNDFmMjI3ZTEyNjg1ZGU0Y2IxMzQ5ZTMwYzBlMzVjOCJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lDRACONIC", "#ffa2c4&lFLIGHT:#ffa2c4 Shift+Left-Click to fly!", "Be sure to look up!",
                                        "#ffa2c4&lFEARFUL ROAR:#ffa2c4 Hit with an empty hand to", "scratch nearby entities in a radius.",
                                        "#ffa2c4&lSTRONG:#ffa2c4 Strength II &&", "Absorption I.",
                                        "#ffa2c4&lLARGE:#ffa2c4 +4 hearts.", "Make the most of it.",
                                        "#ffa2c4&lCARNIVORE:#ffa2c4 Vegetables aren't all that", "healthy.",
                                        "#ffa2c4&lBy BraxyBoiiiiii", "", "", "", "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWY3ODMyZjk5YzFmMmZkYjZjM2JmZGJmNzNkMzdhOGY2ZWQ2NTg5MGIzODg1NDNkNjgzOGE1MWRmODYzZDNiZCJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lDRYAD", "#ffa2c4&lMOTHER NATURE:#ffa2c4 Right-Click a block to spawn a", "random sapling.",
                                        "#ffa2c4&lAURA:#ffa2c4 Shift+Right-Click a block to summon", "a healing aura nearby.",
                                        "#ffa2c4&lNURTURER:#ffa2c4 Right-Click a crop to", "grow it some.",
                                        "#ffa2c4&lVEGETARIAN:#ffa2c4 Meat is just...", "not it.",
                                        "#ffa2c4&lWEAK PLANT:#ffa2c4 You're weaker at night and", "more vulnerable to fire.",
                                        "#ffa2c4&lBEE FRIEND:#ffa2c4 Immune to bee", "stings.",
                                        "#ffa2c4&lBy Kyushuuuu", "", "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTA2NjgyYjJiMjBiY2U2NTE4NDBlZmQ0YjIxY2U4N2ZkM2M1OGQ3ZGM1ODgyODhjZDEyYTY3YTYzNTU3YSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lENDN", "#ffa2c4&lSILK TOUCH:#ffa2c4 Bounce back when close to death.", "Has a cooldown.",
                                        "#ffa2c4&lDRYAD", "#ffa2c4&lMOTHER NATURE:#ffa2c4 Right-Click a block to spawn a", "random sapling.",
                                        "#ffa2c4&lTELEPORTATION:#ffa2c4 Right-Click a block w/ an empty", "hand to randomly teleport.",
                                        "#ffa2c4&lFRENEMY:#ffa2c4 Other endermen can not", "damage you.",
                                        "#ffa2c4&lRESILIENT:#ffa2c4 +2 hearts.", "Make the most of it.",
                                        "#ffa2c4&lFEAR:#ffa2c4 Gain more damage from", "more vulnerable to fire.",
                                        "#ffa2c4&lBy bggvaulted", "", "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODBjNjU3NGQ3ZTJhZjBhZjRiMTFhZjUzZTY5NzdjMTM5MWJjMTI2MmQ5NjJlMGE5ZmQ5ZTlkYmYyODcyYTU1MSJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lLYNX", "#ffa2c4&lWILD:#ffa2c4 Strength I &", "Speed II.s",
                                        "#ffa2c4&lPRACTICAL:#ffa2c4 XP gain in the AFK + double", "DNA gain in the pool.",
                                        "#ffa2c4&lCARNIVORE:#ffa2c4 Vegetables aren't all that", "healthy.",
                                        "#ffa2c4&lFEAR:#ffa2c4 Fire deals more", "damage.",
                                        "#ffa2c4&lBy Illuminator4587", "", "", "", "", "", "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTU0N2VkZTA3ZmM2MjY1NTgxNTYzMjU2ODljOTU5YzdmZWEyODFmZjBjMDIzMDk0MjMwZTYyOWM2MGM0OWFhMiJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lMER", "#ffa2c4&lFISHER:#ffa2c4 Right-Click a block underwater for", "a chance to get fish.",
                                        "#ffa2c4&lADAPTER:#ffa2c4 Block breaks instantly underwater.", "Damage boost underwater.",
                                        "#ffa2c4&lDRY:#ffa2c4 You're much weaker on land.", "Oh well.",
                                        "#ffa2c4&lFRENEMIES:#ffa2c4 Pufferfish can not damage you.", "Make the most of it.",
                                        "#ffa2c4&lOCEAN ANIMAL:#ffa2c4 Dolphin's Grace &", "Water Breathing.",
                                        "#ffa2c4&lENHANCED EYES:#ffa2c4 Night Vision and perfect", "sight underwater.",
                                        "#ffa2c4&lCOMNIVORE?:#ffa2c4 Carnivore! Except you gain a", "lot of nutrition from dried kelp.",
                                        "#ffa2c4&lBy Kyushuuuu", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzYxNmVhZDg4NGYxMWUzNjZhMjgwNWJkODI2YjJlZTdlOTYxNmYwYjI5NTFiMGY2NzQ0ODAyNDg5NWM0NGZlNCJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lNekoRyu", "#ffa2c4&lFANGS:#ffa2c4 Right-Click an entity to suck", "its blood. Gives food. Cooldown.",
                                        "#ffa2c4&lFLORAL:#ffa2c4 Right-Click with Lily of the Valley, Dandelions,", "or poppies to summon animals based on your skill.",
                                        "#ffa2c4&lMAGICAL:#ffa2c4 Left-Click with any item to", "apply Fire Aspect II to it.",
                                        "#ffa2c4&lAPPEALABLE:#ffa2c4 Bees do not damage you.", "Make the most of it.",
                                        "#ffa2c4&lSENSITIVE:#ffa2c4 You take more damage in", "sunlight. Whoops.",
                                        "#ffa2c4&lCARNIVORE:#ffa2c4 Vegetables aren't all that", "healthy. Blood is, though!",
                                        "#ffa2c4&lBIG:#ffa2c4 +5 hearts.", "Make the most of it.",
                                        "#ffa2c4&lBy draco25812", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWQ1YTA5ZmYwNWVlZjg4YTA1MWM5MWE3Y2VjNDdiOTNlNTk4MTg2ZTBiM2U4OTg4NTZhZDgyZGYwZGE5ZGQ1NiJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lWINDOWSIAN", "#ffa2c4&lGOOD HARDWARE:#ffa2c4 Speed II. Haste I.", "Strength II. Night Vision.",
                                        "#ffa2c4&lBATTERY USAGE:#ffa2c4 Chance to lose hunger when", "breaking blocks or attacking.",
                                        "#ffa2c4&lRecycler:#ffa2c4 You eat everything!", "Make the most of it.",
                                        "#ffa2c4&lBy CleoTheKitten", "", "", "", "", "", "", "", "", "", "", "")), false),
                                CustomItem.MakeItem(GetCustomSkull.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTE5OTYzNjIzZGU0NDIzOTg3YzhkMWVhMmIwOGIwYTRiZWYyZmI2NDYxMGEwY2U0ODU1Yjc0NGU2YTc2OTI3NiJ9fX0"), "#ff79a1&l ┍━━━━━━━━━━━━━━━━━━┑", format(List.of(
                                        "#ffa2c4&lTHALLIDIAN", "#ffa2c4&lABSORBENT:#ffa2c4 Night Vision. Speed. Water", "Breathing. Jump. Lots of effects!",
                                        "#ffa2c4&lPOISONOUS:#ffa2c4 Melee attacks inflict", "poison.",
                                        "#ffa2c4&lGOOEY:#ffa2c4 You take extra knockback", "from enemies.",
                                        "#ffa2c4&lGREENTHUMB:#ffa2c4 Right-Click crops to grow them", "based on your unique skill.",
                                        "#ffa2c4&lREPLANTER:#ffa2c4 Crops are automatically", "replanted when you harvest them.",
                                        "#ffa2c4&lOMNINOMNOMVOROUS!:#ffa2c4 You eat everything!", "Make the most of it.",
                                        "#ffa2c4&lBy __Scythe__", "", "", "", "", "")), false),
                        CustomItem.MakeItem(new ItemStack(Material.BARRIER), "&c&lNo Species Here!", "&c&lWant to make your OWN species?" +
                                "\n&c Buy A Species at florial.tebex.io for\n&c ONLY &c&l&n$20!\n&c&lOr $30 AND get the top rank too!", false)).toList();


                        contents.set(List.of(9), IntelligentItem.of(species.get(0), event -> {Species.become(p, "BASSBEAST");}));
                        contents.set(List.of(10), IntelligentItem.of(species.get(1), event -> Species.become(p, "DRACONIC")));
                        contents.set(List.of(11), IntelligentItem.of(species.get(2), event -> Species.become(p, "DRYAD")));
                        contents.set(List.of(12), IntelligentItem.of(species.get(3), event -> Species.become(p, "ENDN")));
                        contents.set(List.of(13), IntelligentItem.of(species.get(4), event -> Species.become(p, "LYNX")));
                        contents.set(List.of(14), IntelligentItem.of(species.get(5), event -> Species.become(p, "MER")));
                        contents.set(List.of(15), IntelligentItem.of(species.get(6), event -> Species.become(p, "NEKORYU")));
                        contents.set(List.of(16), IntelligentItem.of(species.get(7), event -> Species.become(p, "WINDOWSIAN")));
                        contents.set(List.of(17), IntelligentItem.of(species.get(8), event -> Species.become(p, "THALLIDIAN")));

                        int index = 17;
                        for (int i = 0; i < 27; i++) {
                            index++;
                            contents.set(index, IntelligentItem.of(species.get(9), event -> p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 1)));
                        }



                    }
                })
                .build(Florial.getInstance())
                .open(p);

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
