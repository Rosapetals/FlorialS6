package net.florial.features.duels;

import lombok.Getter;
import lombok.Setter;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.TargetedDisguise;
import net.florial.Florial;
import net.florial.utils.general.CC;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;
import java.util.stream.Collectors;

public class Duel {

    @Getter
    private final UUID opponentWhoInvited;
    @Getter
    private final UUID opponentWhoWasInvited;
    @Getter
    @Setter
    private boolean invitationAccepted;
    @Getter
    @Setter
    private boolean duelOnGoing;
    @Getter
    @Setter
    private ItemStack opponentWhoInvitedWageredItem;
    @Getter
    @Setter
    private ItemStack opponentWhoWasInvitedWageredItem;
    @Getter
    @Setter
    private int confirmations;
    @Getter
    @Setter
    private DuelLobby duelLobby;

    public Duel(UUID opponentWhoInvited, UUID opponentWhoWasInvited, boolean invitationAccepted, boolean duelOnGoing, ItemStack opponentWhoInvitedWageredItem, ItemStack opponentWhoWasInvitedWageredItem, int confirmations, DuelLobby duelLobby) {
        this.opponentWhoInvited = opponentWhoInvited;
        this.opponentWhoWasInvited = opponentWhoWasInvited;
        this.invitationAccepted = invitationAccepted;
        this.duelOnGoing = duelOnGoing;
        this.opponentWhoInvitedWageredItem = opponentWhoInvitedWageredItem;
        this.opponentWhoWasInvitedWageredItem = opponentWhoWasInvitedWageredItem;
        this.confirmations = confirmations;
        this.duelLobby = duelLobby;

    }


    public void delete() {
        Florial.getOngoingDuel().remove(opponentWhoInvited);
        Florial.getOngoingDuel().remove(opponentWhoWasInvited);
    }

    public boolean passNullCheck() {
        return Bukkit.getPlayer(opponentWhoInvited) != null && Bukkit.getPlayer(opponentWhoWasInvited) != null;
    }

    public Player getOpponent(Player p) {
        UUID opponentUUID = (opponentWhoInvited == p.getUniqueId()) ? opponentWhoWasInvited : opponentWhoInvited;
        return Bukkit.getPlayer(opponentUUID);
    }

    public ItemStack wageredItemOf(Player p) {
        return (opponentWhoInvited == p.getUniqueId()) ? getOpponentWhoInvitedWageredItem() : getOpponentWhoWasInvitedWageredItem();
    }

    public void update() {
        Florial.getOngoingDuel().put(opponentWhoInvited, this);
        Florial.getOngoingDuel().put(opponentWhoWasInvited, this);
    }


    public void confirm() {

        if (!(this.passNullCheck())) {
            return;
        }

        Player inviter = Bukkit.getPlayer(getOpponentWhoInvited());
        Player invitee = this.getOpponent(inviter);

        inviter.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Confirm you would like to play this duel by doing #ff5b70/duel confirm&f If #ff5b70" + inviter.getName() + " loses, #ff5b70" + invitee.getName() + "&f will receive #ff5b70&lITEM 1&f, However, if #ff5b70" + invitee.getName() + "&f loses, #ff5b70" + inviter.getName() + "&f will receive #ff5b70&lITEM 2&f&n Hover over the boxes below to see what each item is."));
        invitee.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Confirm you would like to play this duel by doing #ff5b70/duel confirm&f If #ff5b70" + inviter.getName() + " loses, #ff5b70" + invitee.getName() + "&f will receive #ff5b70&lITEM 1&f, However, if #ff5b70" + invitee.getName() + "&f loses, #ff5b70" + inviter.getName() + "&f will receive #ff5b70&lITEM 2&f&n Hover over the boxes below to see what each item is."));
        inviter.sendMessage(" ");
        invitee.sendMessage(" ");
        listWageredItems(getOpponentWhoInvitedWageredItem(), "ITEM 1", inviter, invitee);
        listWageredItems(getOpponentWhoWasInvitedWageredItem(), "ITEM 2", inviter, invitee);


    }


    public void start() {

        if (!(this.passNullCheck())) {
            return;
        }

        Player inviter = Bukkit.getPlayer(getOpponentWhoInvited());
        Player invitee = Bukkit.getPlayer(getOpponentWhoWasInvited());

        inviter.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Ready to duel."));
        invitee.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f Ready to duel."));


        inviter.playSound(inviter.getLocation(), Sound.BLOCK_ANVIL_USE, 2, 1);
        invitee.playSound(invitee.getLocation(), Sound.BLOCK_ANVIL_USE, 2, 1);

        setDuelOnGoing(true);
        alterDisguises(inviter, invitee, true);
        update();

        DuelLobby lobby = DuelLobby.find();

        if (lobby == null) {

            inviter.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c Couldn't find an open lobby. Terminated duel."));
            invitee.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c Couldn't find an open lobby. Terminated duel."));
            delete();

        } else {

            lobby.setInUse(true);
            setDuelLobby(lobby);
            inviter.teleport(lobby.getPlayer1Location());
            invitee.teleport(lobby.getPlayer2Location());


        }

    }


    private static void listWageredItems(ItemStack i, String name, Player inviter, Player invitee) {

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

    public void alterDisguises(Player player1, Player player2, boolean hiding) {

        TargetedDisguise disguise1 = (TargetedDisguise) DisguiseAPI.getDisguise(player1);
        TargetedDisguise disguise2 = (TargetedDisguise) DisguiseAPI.getDisguise(player2);

        if (disguise1 != null) {
            if (hiding) {
                disguise1.addPlayer(player2);
            } else {
                disguise1.removePlayer(player2);
            }
        }

        if (disguise2 != null) {
            if (hiding) {
                disguise2.addPlayer(player1);
            } else {
                disguise2.removePlayer(player1);
            }
        }
    }

    public void lose(Player p) {

        ItemStack wagered = wageredItemOf(p);
        p.getInventory().removeItem(wagered);
        p.getWorld().dropItem(p.getLocation(), wagered);
        Player opponent = getOpponent(p);

        opponent.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&f You won the duel! Go pick up the wagered item that has just dropped!"));
        opponent.playSound(opponent.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_1, 2, 1);

        p.sendMessage(CC.translate("#ffd7dc&l&nF#ffb8c1&l&nl#ff99a6&l&no#ff7a8b&l&nr#ff5b70&l&ni#ff3c55&l&na#ff1d3a&l&nl&r #ff3c55&l➤&c You lost the duel AND your wagered item! Oh well... better luck next time."));
        p.playSound(opponent.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_4, 2, 1);

        alterDisguises(p, opponent, false);
        getDuelLobby().setInUse(false);

        delete();
    }
}


