package net.florial.utils.math;

public class AgeFormula {

    public static int get(int number, int chunkValue) {
        return (int) (number - ((number / Math.pow(10, Math.log10(number) * 0.5)) * chunkValue) < 0 ? 0 : number - ((number / Math.pow(10, Math.log10(number) * 0.5)) * chunkValue));
    }

}
