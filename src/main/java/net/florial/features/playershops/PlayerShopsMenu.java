package net.florial.features.playershops;

import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.species.impl.Human;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.GetCustomSkull;
import net.florial.utils.general.VaultHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerShopsMenu {

    private static final GetCustomSkull GetCustomSkull = new GetCustomSkull();


    public static final List<Location> playerShops = List.of(
            //floor 1
            new Location (Bukkit.getWorld("world"), 6971, 78, 7209),
            new Location (Bukkit.getWorld("world"), 6971, 78, 7201),
            new Location (Bukkit.getWorld("world"), 6970, 78, 7196),
            new Location (Bukkit.getWorld("world"), 6971, 78, 7195),
            new Location (Bukkit.getWorld("world"), 6976, 78, 7195),
            new Location (Bukkit.getWorld("world"), 6978, 78, 7197),
            new Location (Bukkit.getWorld("world"), 6978, 78, 7205),
            new Location (Bukkit.getWorld("world"), 6979, 78, 7210),
            new Location (Bukkit.getWorld("world"), 6976, 78, 7211),
            new Location (Bukkit.getWorld("world"), 6971, 78, 7211),
            //floor 2
            new Location (Bukkit.getWorld("world"), 6971, 85, 7209),
            new Location (Bukkit.getWorld("world"), 6968, 86, 7203),
            new Location (Bukkit.getWorld("world"), 6971, 85, 7201),
            new Location (Bukkit.getWorld("world"), 6970, 85, 7196),
            new Location (Bukkit.getWorld("world"), 6971, 85, 7195),
            new Location (Bukkit.getWorld("world"), 6976, 85, 7195),
            new Location (Bukkit.getWorld("world"), 6978, 85, 7197),
            new Location (Bukkit.getWorld("world"), 6978, 85, 7205),
            new Location (Bukkit.getWorld("world"), 6979, 85, 7210),
            new Location (Bukkit.getWorld("world"), 6976, 85, 7211),
            new Location (Bukkit.getWorld("world"), 6971, 85, 7211),
            //floor 3
            new Location (Bukkit.getWorld("world"), 6971, 92, 7201),
            new Location (Bukkit.getWorld("world"), 6976, 92, 7201),
            new Location (Bukkit.getWorld("world"), 6978, 92, 7205),
            new Location (Bukkit.getWorld("world"), 6971, 92, 7205),
            new Location (Bukkit.getWorld("world"), 6968, 93, 7203)



    );


    public void open(Player p) {

        p.closeInventory();

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE630"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {

                        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_STEP, 1, 2);


                        int i = 8;
                        for (Location shopLocation : playerShops) {
                            i++;
                            Sign sign = (Sign) shopLocation.getBlock().getState();
                            String[] lines = sign.getLines();
                            String occupant = lines[1];
                            String headString = occupant.contains("unoccupied") ? "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIzZDdlNjU1ZGViZGQ1OTFkMDk5ZDc2ZmYwMDBkNzU1NWJlNGFlMTFiMWUyNmI5YWRmMjQ0YWUwMjJiMjljOCJ9fX0" : "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTE4OWYzNDdmNDI0NTBjZDJhMmU5YjhhNTM5ODgwN2QyOGM3ZjQyNTRiZDk5YThhNDk5Y2U1NDM1MzIwOTU1In19fQ";
                            contents.set(i, IntelligentItem.of(CustomItem.MakeItem(GetCustomSkull.getCustomSkull(headString), "#5a372c&lSHOP LOT " + (i-8), "#5a372c&lCOST:#5a372c $100,000\n#5a372c&lOCCUPANT:#5a372c " + occupant + "\n#5a372c&lTAGLINE:#5a372c " + lines[3], false), event -> purchaseShop(p, sign)));

                        }

                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }


    private static void purchaseShop(Player p, Sign sign){

        p.closeInventory();

        if (!(sign.getLine(1).contains("unoccupied"))) {
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 2);
            p.sendMessage(CC.translate("#fdd8d8&lTULIP&f ➤&c Sorry! That shop lot is reserved."));
            return;
        }

        for (Location shopLocation : playerShops) {

            Sign sign2 = (Sign) shopLocation.getBlock().getState();
            String[] lines = sign2.getLines();


            if (lines[1].contains(p.getName())) {
                PlayerData data = Florial.getPlayerData().get(p.getUniqueId());
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 2);
                p.sendMessage(CC.translate("#fdd8d8&lTULIP&f ➤&c Erm.. You know, you already have a shop. You own " + lines[0] + " one per animal, sorry! " + (data.getSpecies() instanceof Human ? "Oh you're a human? Well one per human, I guess!" : "")));
                return;
            }


        }

        if (VaultHandler.getBalance(p) >= 100000) {

            sign.setLine(1, p.getName());
            sign.update();

            VaultHandler.removeMoney(p, 100000);

            String[] lines = sign.getLines();

            String regionName = lines[0].replaceAll(" ", "");

            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg addmember " + regionName + " " + p.getName() + " -w world");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg flag " + regionName + " -w world block-break -g members allow");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg flag " + regionName + " -w world block-place -g members allow");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "rg flag " + regionName + " -w world chest-access -g members allow");



            p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 1, 2);
            p.sendMessage(CC.translate("#fdd8d8&lTULIP&f ➤ Congratulations on your new purchase! Remember, your shop is located at " + lines[0] + " Punch your sign at anytime to enter the panel where you can add/remove people to your shop or give it a tagline! Oh! You can also decorate it however you like."));

        } else {

            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 2);
            p.sendMessage(CC.translate("#fdd8d8&lTULIP&f➤&c You need at least $100,000 to purchase a shop! I know it's a lot.. But you have it FOREVER. Also you can decorate it however you like... add ANY items you want in the WORLD.  to sell! And.. MOST IMPORTANTLY IF YOU BUY IT I GET MONEY! Did I sell you on it yet?"));

        }

    }
}
