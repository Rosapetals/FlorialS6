package net.florial.models;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

public class ShiftData {

    @Getter private long shiftStart;
    @Getter private int playersOnStart;
    @Getter @Setter
    private int highestPlayerCount;

    public ShiftData() {
        shiftStart = System.currentTimeMillis();
        playersOnStart = Bukkit.getOnlinePlayers().size();
        highestPlayerCount = Bukkit.getOnlinePlayers().size();
    }
}
