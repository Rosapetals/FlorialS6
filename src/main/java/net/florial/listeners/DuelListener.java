package net.florial.listeners;

import net.florial.Florial;
import net.florial.features.duels.Duel;
import net.florial.utils.general.CC;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;
import java.util.stream.Collectors;

public class DuelListener implements Listener {


    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player p = event.getPlayer();

        UUID u = p.getUniqueId();

        if (Florial.getOngoingDuel().get(u) != null) {
            Duel duel = Florial.getOngoingDuel().get(u);

            if (!(duel.passNullCheck())) {
                return;
            }

            if (duel.isDuelOnGoing()) {
                p.setHealth(0);
            }
        }
    }


    @EventHandler
    public void setItemForWager(PlayerInteractEvent event) {

        if (event.getAction() != Action.LEFT_CLICK_AIR
                || event.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR
                || Florial.getOngoingDuel().get(event.getPlayer().getUniqueId()) == null) return;

        Player p = event.getPlayer();

        UUID u = p.getUniqueId();

        Duel duel = Florial.getOngoingDuel().get(u);

        if (duel.getOpponentWhoWasInvitedWageredItem() == null) return;

        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Successfully put up this item for wager. If you put up the wrong item, it's OK. Just do #ff3c55/duel cancel"));

        if (duel.getOpponentWhoInvited() == u) {
            duel.setOpponentWhoInvitedWageredItem(p.getInventory().getItemInMainHand());
            duel.update();
            if (duel.getOpponentWhoWasInvitedWageredItem().getType() != Material.AIR) {
                confirmDuel(duel);
            }
        } else {
            duel.setOpponentWhoWasInvitedWageredItem(p.getInventory().getItemInMainHand());
            duel.update();
            if (duel.getOpponentWhoInvitedWageredItem().getType() != Material.AIR) {
                confirmDuel(duel);

            }
        }
    }




    private static void confirmDuel(Duel duel) {

        if (!(duel.passNullCheck())) {
            return;
        }

        Player inviter = Bukkit.getPlayer(duel.getOpponentWhoInvited());
        Player invitee = duel.getOpponent(inviter);
        inviter.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Confirm you would like to play this duel. If #ff5b70" + inviter.getName() + " loses, #ff5b70" + invitee.getName() + "&f will receive #ff5b70&lITEM 1&f, However, if #ff5b70" + invitee.getName() + "&f loses, #ff5b70" + inviter.getName() + "&f will receive #ff5b70&lITEM 2&f&n Hover over the boxes below to see what each item is."));
        invitee.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Confirm you would like to play this duel. If #ff5b70" + inviter.getName() + " loses, #ff5b70" + invitee.getName() + "&f will receive #ff5b70&lITEM 1&f, However, if #ff5b70" + invitee.getName() + "&f loses, #ff5b70" + inviter.getName() + "&f will receive #ff5b70&lITEM 2&f&n Hover over the boxes below to see what each item is."));
        listItems(duel.getOpponentWhoInvitedWageredItem(), "ITEM 1", inviter, invitee);
        listItems(duel.getOpponentWhoWasInvitedWageredItem(), "ITEM 2", inviter, invitee);


    }


    private static void listItems(ItemStack i, String name, Player inviter, Player invitee) {

        ItemMeta meta = i.getItemMeta();

        StringBuilder itemBuilder = new StringBuilder()
                .append(CC.translate((!(meta.getDisplayName().isBlank()) ? meta.getDisplayName() : "&9" + i.getType()) + "\n"))
                .append(CC.translate("&b&o" + i.getType() + "\n"))
                .append(CC.translate("&9Lore:\n"))
                .append(meta.getLore() != null ? meta.getLore().stream().map(line -> "&f" + line + "\n").collect(Collectors.joining()) : "")
                .append(CC.translate("&9Enchants:\n"))
                .append(meta.hasEnchants() ? i.getEnchantments().entrySet().stream().map(entry -> CC.translate("&b") + entry.getKey().getKey() + CC.translate(": &f") + entry.getValue() + "\n").collect(Collectors.joining()) : "");


        String itemDescription = itemBuilder.toString();

        inviter.sendMessage((new ComponentBuilder(CC.translate("&c&l" + name))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(CC.translate(itemDescription)).create()))
                .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "i")).create()));
        invitee.sendMessage((new ComponentBuilder(CC.translate("&c&l" + name))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(CC.translate(itemDescription)).create()))
                .event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "i")).create()));

    }
}
