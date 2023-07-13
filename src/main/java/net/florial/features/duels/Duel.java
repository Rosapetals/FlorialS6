package net.florial.features.duels;

import lombok.Getter;
import lombok.Setter;
import net.florial.Florial;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Duel {

    @Getter
    private final UUID opponentWhoInvited;
    @Getter
    private final UUID opponentWhoWasInvited;
    @Getter @Setter
    private boolean invitationAccepted;
    @Getter @Setter private boolean duelOnGoing;
    @Getter @Setter private ItemStack opponentWhoInvitedWageredItem;
    @Getter @Setter private ItemStack opponentWhoWasInvitedWageredItem;
    @Getter @Setter private int confirmations;

    public Duel(UUID opponentWhoInvited, UUID opponentWhoWasInvited, boolean invitationAccepted, boolean duelOnGoing, ItemStack opponentWhoInvitedWageredItem, ItemStack opponentWhoWasInvitedWageredItem, int confirmations) {
        this.opponentWhoInvited = opponentWhoInvited;
        this.opponentWhoWasInvited = opponentWhoWasInvited;
        this.invitationAccepted = invitationAccepted;
        this.duelOnGoing = duelOnGoing;
        this.opponentWhoInvitedWageredItem = opponentWhoInvitedWageredItem;
        this.opponentWhoWasInvitedWageredItem = opponentWhoWasInvitedWageredItem;
        this.confirmations = confirmations;

    }


    public void delete() {
        Florial.getOngoingDuel().remove(opponentWhoInvited);
        Florial.getOngoingDuel().remove(opponentWhoWasInvited);
    }

    public boolean passNullCheck() {return Bukkit.getPlayer(opponentWhoInvited) != null && Bukkit.getPlayer(opponentWhoWasInvited) != null;}

    public Player getOpponent(Player p) {

        if (opponentWhoInvited == p.getUniqueId()) {
            return Bukkit.getPlayer(opponentWhoWasInvited);
        } else {
            return Bukkit.getPlayer(opponentWhoInvited);
        }

    }

    public void update() {
        Florial.getOngoingDuel().put(opponentWhoInvited, this);
        Florial.getOngoingDuel().put(opponentWhoWasInvited, this);
    }

}
