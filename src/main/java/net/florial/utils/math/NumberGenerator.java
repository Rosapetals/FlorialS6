package net.florial.utils.math;

public class NumberGenerator {

    public static int generate(Integer i, Integer i2) {
        int i1 = ((int) (Math.random() * (i2 - i))) + i;
        return i1;
    }
}

