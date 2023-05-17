package net.florial.utils.math;

public class GetChance {

    public static boolean chanceOf(int chance) {
        return Math.random() < chance / 100.0;

    }

}
