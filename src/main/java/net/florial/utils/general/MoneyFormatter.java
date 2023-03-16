package net.florial.utils.general;

public class MoneyFormatter {

    public static String put(long number) {
        if (number >= 1000000000) {
            return String.format("%.1fB", (double) number / 1_000_000_000);
        } else if (number >= 1000000) {
            return String.format("%.1fM", (double) number / 1_000_000);
        } else if (number >= 1000) {
            return String.format("%.1fK", (double) number / 1000);
        } else {
            return Long.toString(number);
        }
    }
}
