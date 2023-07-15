package net.florial.features.duels;

import lombok.Getter;
import lombok.Setter;
import net.florial.species.SpecieType;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum DuelLobby {

    ARENA1(new Location (Bukkit.getWorld("world"), 0, 0, 0),
            new Location (Bukkit.getWorld("world"), 0, 0, 0),
            false),
    ARENA2(new Location (Bukkit.getWorld("world"), 0, 0, 0),
            new Location (Bukkit.getWorld("world"), 0, 0, 0),
            false),
    ARENA3(new Location (Bukkit.getWorld("world"), 0, 0, 0),
            new Location (Bukkit.getWorld("world"), 0, 0, 0),
            false);

    @Getter
    private final Location player1Location;

    @Getter
    private final Location player2Location;

    @Getter @Setter private boolean inUse;


    DuelLobby(Location player1Location, Location player2Location, boolean inUse) {
        this.player2Location = player2Location;
        this.player1Location = player1Location;
        this.inUse = inUse;
    }


    public static DuelLobby find() {

        for (DuelLobby lobby : values()) {

            if (!(lobby.isInUse())) {
                return lobby;
            }

        }
        return null;
    }

}


