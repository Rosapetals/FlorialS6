package net.florial.features.duels;

import lombok.Getter;
import lombok.Setter;
import net.florial.Florial;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Duel {

    @Getter
    private final UUID opponentWhoInvited;
    @Getter
    private final UUID opponentWhoWasInvited;
    @Getter @Setter private boolean duelIsOnGoing;
    @Getter @Setter private ItemStack opponentWhoInvitedWageredItem;
    @Getter @Setter private ItemStack opponentWhoWasInvitedWageredItem;

    public Duel(UUID opponentWhoInvited, UUID opponentWhoWasInvited, boolean duelIsOnGoing, ItemStack opponentWhoInvitedWageredItem, ItemStack opponentWhoWasInvitedWageredItem) {
        this.opponentWhoInvited = opponentWhoInvited;
        this.opponentWhoWasInvited = opponentWhoWasInvited;
        this.duelIsOnGoing = duelIsOnGoing;
        this.opponentWhoInvitedWageredItem = opponentWhoInvitedWageredItem;
        this.opponentWhoWasInvitedWageredItem = opponentWhoWasInvitedWageredItem;

    }


    public void delete() {
        Florial.getOngoingDuel().remove(opponentWhoInvited);
        Florial.getOngoingDuel().remove(opponentWhoWasInvited);
    }

    public boolean passNullCheck() {return Bukkit.getPlayer(opponentWhoInvited) != null && Bukkit.getPlayer(opponentWhoWasInvited) != null;}


}
