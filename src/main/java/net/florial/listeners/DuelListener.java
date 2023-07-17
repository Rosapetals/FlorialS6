package net.florial.listeners;

import net.florial.Florial;
import net.florial.features.duels.Duel;
import net.florial.utils.general.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class DuelListener implements Listener {


    @EventHandler
    public void onDuelQuit(PlayerQuitEvent event) {

        Player p = event.getPlayer();

        UUID u = p.getUniqueId();

        Duel duel = Florial.getOngoingDuel().get(u);

        if (duel == null
        || (!(duel.isDuelOnGoing()))) return;


        if (!(duel.passNullCheck())) {
            return;
        }

        Player opponent = duel.getOpponent(p);

        if (duel.isDuelOnGoing()) {
            p.setHealth(0);
            duel.lose(p);

        }
        opponent.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c The opponent has left so the duel was cancelled. Any wagered items were dropped."));
    }

    @EventHandler
    public void onDuelDefeat(PlayerDeathEvent event) {

        Player p = event.getPlayer();

        UUID u = p.getUniqueId();
        Duel duel = Florial.getOngoingDuel().get(u);

        if (duel == null || (!(duel.isDuelOnGoing()))) return;

        duel.lose(p);
        duel.delete();



    }


    @EventHandler
    public void setItemForWager(PlayerInteractEvent event) {

        Player p = event.getPlayer();
        UUID u = p.getUniqueId();

        if (event.getAction() != Action.LEFT_CLICK_AIR
                || p.getInventory().getItemInMainHand().getType() == Material.AIR
                || Florial.getOngoingDuel().get(u) == null
                || Florial.getOngoingDuel().get(u).wageredItemOf(p).getType() != Material.AIR) return;

        Duel duel = Florial.getOngoingDuel().get(u);

        if (duel.getOpponentWhoWasInvitedWageredItem() == null) return;

        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully put up this item for wager. If you put up the wrong item, it's OK. Just do #ff3c55/duel cancel"));

        ItemStack itemInHand = p.getInventory().getItemInMainHand();
        boolean isOpponentWhoInvited = duel.getOpponentWhoInvited() == u;

        if (isOpponentWhoInvited) {
            duel.setOpponentWhoInvitedWageredItem(itemInHand);
        } else {
            duel.setOpponentWhoWasInvitedWageredItem(itemInHand);
        }

        duel.update();

        ItemStack opponentWageredItem = isOpponentWhoInvited ? duel.getOpponentWhoWasInvitedWageredItem() : duel.getOpponentWhoInvitedWageredItem();
        if (opponentWageredItem.getType() != Material.AIR) {
            duel.confirm();
        }
    }


    @EventHandler
    public void cancelDuelCommands(PlayerCommandPreprocessEvent e) {

        Player p = e.getPlayer();
        UUID u = p.getUniqueId();

        if (Florial.getOngoingDuel().get(u) == null
        || (!(Florial.getOngoingDuel().get(u).isDuelOnGoing())
        || (p.hasPermission("staff")
        || e.getMessage().contains("duel")))) return;

        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c No commands during a duel."));
        e.setCancelled(true);

    }



}
