package net.florial.scoreboard;

import net.florial.Florial;
import net.florial.models.PlayerData;
import net.florial.utils.general.CC;
import net.florial.utils.general.MoneyFormatter;
import net.florial.utils.general.VaultHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class Scoreboard implements Listener {

    @EventHandler
    public void applyScoreboard(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        UUID u = p.getUniqueId();

        createBoard(p);
        boardRunnable(u, p);

    }

    public static void createBoard(Player p) {

        UUID u = p.getUniqueId();

        PlayerData data = Florial.getPlayerData().get(u);

        String name = (data.getSpecieType().getSpecie() == null) ? "/species" : data.getSpecies().getName();

        String text1 = CC.translate(" #ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff1d3a&l| S6");
        String text3 = CC.translate("");
        String text4 = CC.translate("    #ffd7dc┍━━━━━━━━━━━━━━━━━━┑");
        String text5 = CC.translate("   #ffb8c1 ︳ #ff5b70&lSPECIES:&f " + name);
        String text6 = CC.translate("   #ff99a6 ︳ #ffd7dc&lBALANCE:&f ￠" + MoneyFormatter.put((long) VaultHandler.getBalance(p)));
        String text7 = CC.translate("   #ff7a8b ︳ #ff5b70&lFLORIES:&f " + data.getFlories());
        String text12 = CC.translate("   #ff5b70 ︳ #ffd7dc&lDNA&f: " + data.getDna());
        String text13 = CC.translate("   #ff3c55 ︳ #ff5b70&lAGE&f: " + data.getAge());
        String text10 = CC.translate("    #ff1d3a┕━━━━━━━━━━━━━━━━━━┙");
        String text8 = CC.translate("   #ff3c55 ︳ #ff5b70&lplay.florial.us");
        String text9 = CC.translate("   #ff5b70 ︳ #ffb5b7&lflorial.us/shop");
        String text11 = CC.translate("    #ff7a8b┕━━━━━━━━━━━━━━━━━━┙");
        FastBoard board = new FastBoard(p);
        board.updateTitle("" + text1);
        Florial.getBoards().put(u, board);
        board.updateLines(
                "" + text3,
                "" + text4,
                "" + text5,
                "" + text6,
                "" + text7,
                "" + text12,
                "" + text13,
                "" + text10,
                "" + text8,
                "" + text9,
                "" + text11);
        Florial.getBoards().putIfAbsent(u, board);
    }

    public void updateBoard(FastBoard board, Player p) {

        UUID u = p.getUniqueId();

        PlayerData data = Florial.getPlayerData().get(u);

        String name = (data.getSpecieType().getSpecie() == null) ? "/species" : data.getSpecies().getName();

        String text1 = CC.translate(" #ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl #ff1d3a&l| S6");
        String text3 = CC.translate("");
        String text4 = CC.translate("    #ffd7dc┍━━━━━━━━━━━━━━━━━━┑");
        String text5 = CC.translate("   #ffb8c1 ︳ #ff5b70&lSPECIES:&f " + name);
        String text6 = CC.translate("   #ff99a6 ︳ #ffd7dc&lBALANCE:&f ￠" + MoneyFormatter.put((long) VaultHandler.getBalance(p)));
        String text7 = CC.translate("   #ff7a8b ︳ #ff5b70&lFLORIES:&f " + data.getFlories());
        String text12 = CC.translate("   #ff5b70 ︳ #ffd7dc&lDNA&f: " + data.getDna());
        String text13 = CC.translate("   #ff3c55 ︳ #ff5b70&lAGE:&f: " + data.getAge());
        String text10 = CC.translate("    #ff1d3a┕━━━━━━━━━━━━━━━━━━┙");
        String text8 = CC.translate("   #ff3c55 ︳ #ff5b70&lplay.florial.us");
        String text9 = CC.translate("   #ff5b70 ︳ #ffb5b7&lflorial.us/shop");
        String text11 = CC.translate("    #ff7a8b┕━━━━━━━━━━━━━━━━━━┙");
        board.updateTitle("" + text1);
        Florial.getBoards().put(u, board);
        board.updateLines(
                "" + text3,
                "" + text4,
                "" + text5,
                "" + text6,
                "" + text7,
                "" + text12,
                "" + text13,
                "" + text10,
                "" + text8,
                "" + text9,
                "" + text11);
    }

    public void boardRunnable(UUID u, Player p){

        Bukkit.getScheduler().runTaskTimerAsynchronously(Florial.getInstance(), () -> {
            if (!p.isOnline()) return;
            updateBoard(Florial.getBoards().get(u), p);
        }, 400L, 400);
    }
}
