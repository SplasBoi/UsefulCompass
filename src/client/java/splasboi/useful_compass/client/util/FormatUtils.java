package splasboi.useful_compass.client.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class FormatUtils {
    public static String coords(int x, int y, int z) {
        return String.format("X: %d Y: %d Z: %d", x, y, z);
    }

    public static String time(int hours, int minutes, boolean use24HourClock) {
        if (use24HourClock) {
            return String.format("%02d:%02d", hours, minutes);
        }

        String period = hours >= 12 ? "PM" : "AM";
        int hour12 = hours % 12;
        if (hour12 == 0) {
            hour12 = 12;
        }

        return String.format("%02d:%02d %s", hour12, minutes, period);
    }

    public static String dimension(ResourceKey<Level> levelKey) {
        String levelId = levelKey.identifier().toString();
        if (levelId.startsWith("minecraft:")) {
            levelId = levelId.substring("minecraft:".length());
        }

        return switch (levelId) {
            case "overworld" -> "Overworld";
            case "the_nether" -> "Nether";
            case "the_end" -> "End";
            default -> levelId;
        };
    }
}
