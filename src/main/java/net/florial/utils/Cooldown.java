package net.florial.utils;

import org.bukkit.entity.Player;

import java.util.*;

public class Cooldown {
    public static Set<String> cooldown = new HashSet<>();

    private Cooldown() {}

    private static String createKey(String key, Player player) {
        return key + "_" + player.getUniqueId();
    }

    /**
     * Adds a player to a cooldown
     * @param name The name of the cooldown (must be unique)
     * @param player The player to add
     * @param TTL The time to live for this cooldown
     */
    public static void createCooldown(String name, Player player, int TTL) {
        String key = createKey(name, player);

        if (cooldown.contains(key))
            throw new IllegalStateException("The player " + player.getName() + " is already on cooldown on " + name);

        cooldown.add(key);

        if (TTL < 0) return;
        new Timer().schedule(new TimerTask() {
            @Override public void run() {
                cooldown.remove(key);
            }
        }, TTL * 1000L);
    }

    /**
     * Adds a player to a cooldown indefinitely
     * @param name The name of the cooldown (must be unique)
     * @param player The player to add
     */
    public static void createCooldown(String name, Player player) {
        createCooldown(name, player, -1);
    }

    /**
     * Checks if the player is on the cooldown list
     * @param name The name of the cooldown (must be unique)
     * @param player The player to check
     * @return Weather the player is in the cooldown or not
     */
    public static boolean isOnCooldown(String name, Player player) {
        return cooldown.contains(createKey(name, player));
    }

    /**
     * Removes a player from a cooldown
     * @param name The name of the cooldown
     * @param player The player to remove
     */
    public static void removeCooldown(String name, Player player) {
        cooldown.remove(createKey(name, player));
    }
}
