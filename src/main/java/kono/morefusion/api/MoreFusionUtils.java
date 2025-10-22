package kono.morefusion.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreFusionUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(MoreFusionValues.NAME);

    /**
     * Returns a positive long value in the format x.xx to xxx with a prefix.
     * Decimals are truncated.
     * Less than 1,000,000 are returned as-is without a prefix.
     */
    public static String formatWithSIPrefix(long n) {
        if (n <= 0) throw new IllegalArgumentException("n must be positive");
        if (n < 1_000_000L) return Long.toString(n);

        final String[] PREFIX = { "M", "G", "T", "P", "E", "Z" };
        final long[] FACTOR = {
                1_000_000L,                      // M
                1_000_000_000L,                  // G
                1_000_000_000_000L,              // T
                1_000_000_000_000_000L,          // P
                1_000_000_000_000_000_000L,      // E
        };

        int idx = FACTOR.length - 1;
        while (idx > 0 && n < FACTOR[idx]) idx--;
        long base = FACTOR[idx];
        String prefix = PREFIX[idx];

        double scaled = (double) n / base;
        String format;
        if (scaled < 10) {
            format = "%.2f%s";
        } else if (scaled < 100) {
            format = "%.1f%s";
        } else {
            format = "%.0f%s";
        }

        return String.format(format,
                Math.floor(scaled * Math.pow(10, format.contains(".1") ? 1 : format.contains(".2") ? 2 : 0)) /
                        Math.pow(10, format.contains(".1") ? 1 : format.contains(".2") ? 2 : 0),
                prefix);
    }
}
