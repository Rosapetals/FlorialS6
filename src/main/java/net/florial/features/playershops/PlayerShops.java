package net.florial.features.playershops;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import net.florial.Florial;
import net.florial.menus.shop.ShopMenu;
import net.florial.utils.general.CC;
import net.florial.utils.general.CustomItem;
import net.florial.utils.general.GetCustomSkull;
import net.florial.utils.general.VaultHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;
import java.util.List;

public class PlayerShops {

    private static final GetCustomSkull GetCustomSkull = new GetCustomSkull();


    public static final List<Location> playerShops = List.of(
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5),
            new Location (Bukkit.getWorld("world"), -529.5, 146.5, 391.5)

    );


    public void playerShops(Player p, int type) {

        p.closeInventory();

        RyseInventory.builder()
                .title(CC.translate("&f七七七七七七七七七七七七七七七七\uE630"))
                .rows(6)
                .provider(new InventoryProvider() {
                    @Override
                    public void init(Player player, InventoryContents contents) {


                        int i = 8;
                        for (Location shopLocation : playerShops) {
                            i++;
                            Sign sign = (Sign) shopLocation.getBlock().getState();
                            String[] lines = sign.getLines();
                            String occupant = lines[1];
                            String headString = occupant.contains("unoccupied") ? "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIzZDdlNjU1ZGViZGQ1OTFkMDk5ZDc2ZmYwMDBkNzU1NWJlNGFlMTFiMWUyNmI5YWRmMjQ0YWUwMjJiMjljOCJ9fX0" : "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTE4OWYzNDdmNDI0NTBjZDJhMmU5YjhhNTM5ODgwN2QyOGM3ZjQyNTRiZDk5YThhNDk5Y2U1NDM1MzIwOTU1In19fQ";
                            contents.set(i, IntelligentItem.of(CustomItem.MakeItem(GetCustomSkull.getCustomSkull(headString), "#5a372c&lSHOP LOT " + i, "#5a372c&lCOST:#5a372c $1,000,000\n#5a372c&lOCCUPANT:#5a372c " + occupant + "\n#5a372c&lTAGLINE:#5a372c " + lines[3], false), event -> purchaseShop(p, sign)));

                        }

                    }
                })
                .build(Florial.getInstance())
                .open(p);

    }


    private static void purchaseShop(Player p, Sign sign){


        for (Location shopLocation : playerShops) {

            Sign sign2 = (Sign) shopLocation.getBlock().getState();
            String[] lines = sign2.getLines();

            if (lines[1].contains(p.getName())) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 2);
                p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You already own a shop! You own " + lines[0]));
                return;
            }


        }

        if (VaultHandler.getBalance(p) >= 1000000) {

            sign.setLine(1, p.getName());

            VaultHandler.removeMoney(p, 1000000);


        } else {

            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 2);
            p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You need at least $1,000,000 to purchase a shop!"));

        }

    }
}
