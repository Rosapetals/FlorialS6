package net.florial.features.duels;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public enum DuelLobby {

    ARENA1(new Location (Bukkit.getWorld("world"), 7186, 188, 7401),
            new Location (Bukkit.getWorld("world"), 7209, 188, 7399),
            false),
    ARENA2(new Location (Bukkit.getWorld("world"), 7177, 188, 7401),
            new Location (Bukkit.getWorld("world"), 7140, 188, 7397),
            false),
    ARENA3(new Location (Bukkit.getWorld("world"), 7129, 186, 7330),
            new Location (Bukkit.getWorld("world"), 7150, 186, 7327),
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


