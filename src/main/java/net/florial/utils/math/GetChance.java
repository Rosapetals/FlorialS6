package net.florial.utils.math;

import java.util.Random;

public class GetChance {

    public static boolean chanceOf(int chance) {
        return Math.random() < chance / 100.0;

    }

}
