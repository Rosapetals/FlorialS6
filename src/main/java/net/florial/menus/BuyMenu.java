package net.florial.menus;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.GetCustomSkull;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Stream;

public class BuyMenu {

    private static final GetCustomSkull heads = new GetCustomSkull();

    public void buyMenu(Player p) {

        RyseInventory.builder()
                .title(CC.translate(""))
                .rows(3)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {


                        List<ItemStack> entries = Stream.of(CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTY3OGYyNzliZTgwNThjMzNiOTVkMjBlMjJiYWEyZDI2OTdlMTNmNDQwMTQ5N2MyOTdiYmJmNzVlYmRkMjIzNCJ9fX0"), "#3cb122&lVIP", format(List.of(
                                "#3cb122", "Cheap but handy!", "#3cb122", "#5acc40",
                                "&l&n$5.30",
                                "/nick",
                                "/kit VIP - Full iron",
                                "/weather",
                                "/skull - get any skull",
                                "/enderchest - open enderchest anywhere",
                                "&l&o$15,000",
                                "&l&o200 DNA"
                                )), false),
                                        CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTlhZDM0ZGMwZmU4ZDJlODFlNjc4YjAxNzI2OTFjMmRhOTU0ZTdkZGUxZTQ0ZWJkMTY4NmE4MTE1ZmUxOWRlYyJ9fX0"), "#3ad7e1&lD#4cdde6&li#5de3eb&la#6fe9f0&lm#81eef5&lo#92f4fa&ln#a4faff&ld", format(List.of(
                                                "#5de3eb", "Juust Right!", "#3ad7e1", "#92f4fa",
                                                "&l&n$9.99",
                                                "/kit Diamond - Full Diamond",
                                                "/kit VIP - Full iron",
                                                "/ptime - change the time for yourself",
                                                "/fly - fly around",
                                                "/diamondfixwater - fix nearby water to stand still",
                                                "/book - edit signed books",
                                                "/wb & /anvil - open anywhere",
                                                "/itemrename - rename any item with colors",
                                                "/itemlore - add colored lore to any item",
                                                "/beezooka & /kittycannon",
                                                "&l&oEverything VIP has",
                                                "&l&o500 DNA",
                                                "&l&o$25,000",
                                                "&l&o10 Crate Keys"
                                        )), false),
                                        CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWM2NDlhMGJiMjY1OWQ4ZjAzNzBjOWRhMWQwMGIzMmI1NmQwOTg5Nzg4ZWJmZGY5ODQwMmNjYmYzZTk2OGE3NSJ9fX0"), "#41116cI#4a1678r#531b84i#5c2190d#64269ci#6d2ba8u#7630b4m", format(List.of(
                                                "#5c2190", "Adequate", "#41116c", "#7630b4",
                                                "&l&n$14.99",
                                                "/kit Iridium - Full diamond, netherite tools",
                                                "/iridiumpumpkins - generate pumpkins nearby",
                                                "/time - change the server time",
                                                "/rankflyspeed - adjust your flyspeed",
                                                "/iridiumforestgen - generate forests",
                                                "/heal - heal yourself with a cooldown",
                                                "/ice - ice people with a cooldown",
                                                "&l&oEverything Diamond has",
                                                "&l&o&nGradiant Chat!",
                                                "&l&o$100,000",
                                                "&l&o20 Crate Keys",
                                                "&l&o600 DNA"
                                        )), false),
                                        CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzZhOWU0ODFmMDMxMWRjYjU0M2VlZjllNjQ3YzkyYWY5Zjk0MjE0MjAyMDU3YTcyMGIzZDliYzhkY2NhMDlmZCJ9fX0"), "#41116cI#4a1678r#531b84i#5c2190d#64269ci#6d2ba8u#7630b4m++", format(List.of(
                                                "#5c2190", "Super worth it!", "#41116c", "#7630b4",
                                                "&l&n$16.99",
                                                "/kit Iridiumplus - Full netherite",
                                                "/prefix - change your prefix to anything with colors",
                                                "/iridiumannounce - announce something to the server",
                                                "/irdiumkeyall - give crate keys to the whole server once a day",
                                                "/iridiumforestgen - generate forests",
                                                "/feed - feed yourself",
                                                "/iridiumfly - grant other players flight for 5 minutes",
                                                "/hat - wear anything on your head",
                                                "&l&oEverything Iridium has",
                                                "&l&o&nDoubled perks in AFK pool",
                                                "&l&o$150,000",
                                                "&l&o25 Crate Keys",
                                                "&l&o700 DNA"
                                        )), false),
                                        CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhmY2EzNDVlNGZkOWM0MjJiNzNjNGMxYzUwNTZmMzc5ZGU5MjUxMGZjOTRiNGNjOTA3ZmIyMGNlNzUwZGM5MCJ9fX0"), "#ffa737F#ffb043l#ffb84eo#ffc15au#ffca66r#ffd372i#ffdb7dt#ffe489e", format(List.of(
                                                "#ffca66", "PERFECT to buy!", "#ffa737", "#ffe489",
                                                "&l&n$19.99",
                                                "/kit flourite - Netherite Tools Efficiency & Sharp",
                                                "/invsee - see into other player's inventories",
                                                "/gift - give yourself a random item in minecraft",
                                                "/condense - condense items into a stack",
                                                "/switchspecies - send a request to change a user's species",
                                                "&l&oEverything Iridium++ has",
                                                "&l&o&nTripled perks in AFK pool",
                                                "&l&o$400,000",
                                                "&l&o50 Crate Keys",
                                                "&l&o800 DNA",
                                                "&l&o20+ sethomes"
                                        )), false),
                                        CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE0N2MyMGM0ZjM1YmQyN2QzZDQxOTEyNzkyYTc5OGU5ZjRmOWJiZmUwNGYwZDMyNTVkOWJjYWRmOGE0MWFhZSJ9fX0"), "#efd3acP#f1d9b8e#f4e0c4a#f6e6d0r#f8ecdbl#faf2e7i#fdf9f3t#ffffffe", format(List.of(
                                                "#f4e0c4", "*harp sounds* Buy it!", "#efd3ac", "#fdf9f3",
                                                "&l&n$21.99",
                                                "/kit pearlite Netherite Prot 5, Eff 5",
                                                "/shapeshift - shapeshift between cat,fox,human",
                                                "/pearlite - 15k, 5 DNA, crate keys daily",
                                                "/nickname <user> - request to switch a user's nick",
                                                "&l&oEverything Flourite has",
                                                "&l&o&nQuadrupled perks in AFK pool",
                                                "&l&o$1,000,000",
                                                "&l&o64 Crate Keys",
                                                "&l&o10000 DNA",
                                                "&l&o20+ sethomes",
                                                "&l&o&nUnlock Pearlite Species",
                                                "&oOnly $2 more!"
                                        )), false),
                                        CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmExNmI1Y2ExMDE0ZTE4NDYzYThlNzVjNjEwZDYzZWM3NzRjZGUzM2E5OTM2NjkyYjllMzc3NDM3NSJ9fX0"), "#FFB62E&l200 Flories", format(List.of(
                                                "#F8BB48", "Perfect for 1-2 items.", "#E2970A", "#FFD78C",
                                                "&l&n$4.99",
                                                "Gives 200 Flories"
                                        )), false),
                                        CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmExNmI1Y2ExMDE0ZTE4NDYzYThlNzVjNjEwZDYzZWM3NzRjZGUzM2E5OTM2NjkyYjllMzc3NDM3NSJ9fX0"), "#FFB62E&l650 Flories", format(List.of(
                                                "#F8BB48", "Perfect for a handful of items.", "#E2970A", "#FFD78C",
                                                "&l&n$9.99",
                                                "Gives 650 Flories"
                                        )), false),
                                        CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmExNmI1Y2ExMDE0ZTE4NDYzYThlNzVjNjEwZDYzZWM3NzRjZGUzM2E5OTM2NjkyYjllMzc3NDM3NSJ9fX0"), "#FFB62E&l1,000 Flories", format(List.of(
                                                "#F8BB48", "Perfect for multiple items.", "#E2970A", "#FFD78C",
                                                "&l&n$11.99",
                                                "Gives 1,000 Flories",
                                                "&oOnly $2 more!"
                                        )), false),
                                        CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U0ZjJmOTY5OGMzZjE4NmZlNDRjYzYzZDJmM2M0ZjlhMjQxMjIzYWNmMDU4MTc3NWQ5Y2VjZDcwNzUifX19"), "#FFB62E&lPEARLITE + MAKE-A-SPECIES", format(List.of(
                                                "#F8BB48", "Heavenly Purchase!", "#E2970A", "#FFD78C",
                                                "&l&n$30.99",
                                                "Make your OWN custom species in",
                                                "Florial. You will be emailed!",
                                                "PLUS get PEARLITE!"
                                        )), false),
                                        CustomItem.MakeItem(heads.getCustomSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U0ZjJmOTY5OGMzZjE4NmZlNDRjYzYzZDJmM2M0ZjlhMjQxMjIzYWNmMDU4MTc3NWQ5Y2VjZDcwNzUifX19"), "#FFB62E&lMAKE-A-SPECIES", format(List.of(
                                                "#F8BB48", "Totally buy it!", "#E2970A", "#FFD78C",
                                                "&l&n$19.99",
                                                "Make your OWN custom species in",
                                                "Florial. You will be emailed!"
                                        )), false)
                                ).map(i -> NBTEditor.set(i, 1010, "CustomModelData"))
                                .toList();

                        contents.set(2, IntelligentItem.of(entries.get(0), event -> redirect(p, "VIP")));
                        contents.set(3, IntelligentItem.of(entries.get(1), event -> redirect(p, "DIAMOND")));
                        contents.set(4, IntelligentItem.of(entries.get(2), event -> redirect(p, "IRIDIUM")));
                        contents.set(5, IntelligentItem.of(entries.get(3), event -> redirect(p, "IRIDIUM++")));
                        contents.set(6, IntelligentItem.of(entries.get(4), event -> redirect(p, "FLOURITE")));
                        contents.set(13, IntelligentItem.of(entries.get(5), event -> redirect(p, "PEARLITE")));
                        contents.set(12, IntelligentItem.of(entries.get(9), event -> redirect(p, "PEARLITE+MAKE-A-SPECIES")));
                        contents.set(14, IntelligentItem.of(entries.get(10), event -> redirect(p, "MAKE-A-SPECIES")));
                        contents.set(21, IntelligentItem.of(entries.get(6), event -> redirect(p, "200-FLORIES")));
                        contents.set(22, IntelligentItem.of(entries.get(7), event -> redirect(p, "650-FLORIES")));
                        contents.set(23, IntelligentItem.of(entries.get(8), event -> redirect(p, "1000-FLORIES")));





                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }

    private static void redirect(Player p, String packageType) {

        p.closeInventory();

        String link = "";

        switch (packageType) {
            case "VIP", "DIAMOND", "IRIDIUM", "IRIDIUM++", "FLOURITE", "PEARLITE" -> link = "https://florial.tebex.io/category/1955383";
            case "200-FLORIES", "650-FLORIES", "1000-FLORIES" -> link = "https://florial.tebex.io/category/flories";
            case "PEARLITE+MAKE-A-SPECIES" -> link = "https://florial.tebex.io/category/bundles";
            case "MAKE-A-SPECIES" -> link = "https://florial.tebex.io/category/chat-tags";



        }

        p.sendMessage(" ");

        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r"));

        p.sendMessage(" ");

        p.sendMessage((new ComponentBuilder(CC.translate("&f&l&oClick here to purchase the " + packageType + " package!"))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(CC.translate("Click here!")).create()))
                .event(new ClickEvent(ClickEvent.Action.OPEN_URL, link)).create()));
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, (float) 2);


    }


    private static String format(List<String> iterations) {
        StringBuilder result = new StringBuilder();

        result.append(iterations.get(0)).append("&o").append(iterations.get(1)).append("\n");

        String bulletPoint = iterations.get(2) + "•" + iterations.get(3) + " ";

        for (int i = 4; i < iterations.size(); i++) {
            result.append(bulletPoint).append(iterations.get(i)).append("\n");
        }

        return result.toString();
    }

}
