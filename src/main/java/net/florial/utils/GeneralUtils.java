package net.florial.utils;

import net.florial.Florial;
import org.bukkit.scheduler.BukkitRunnable;

public class GeneralUtils {

    public static void runAsync(BukkitRunnable code) {
        code.runTaskAsynchronously(Florial.getInstance());
    }

    public static void runAsync(Runnable code) {
        new BukkitRunnable() {
            @Override
            public void run() {
                code.run();
            }
        }.runTaskAsynchronously(Florial.getInstance());
    }

}
