package splasboi.useful_compass.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.Items;

public class ClockModule implements HudModule {
    boolean use24HourClock = true;

    @Override
    public boolean tryRender (GuiGraphicsExtractor context, Minecraft client, HudLayout layout) {
        if (client.level == null)
            return false;

        if (client.player == null)
            return false;

        boolean hasClock = client.player.getInventory().contains(Items.CLOCK.getDefaultInstance());

        if (!hasClock)
            return false;

        long time = client.level.getDefaultClockTime() % 24000;

        int hours = (int)((time / 1000 + 6) % 24);
        int minutes = (int)((time % 1000) * 60 / 1000);

        String timeString;

        if (use24HourClock) {
            timeString = String.format("%02d:%02d", hours, minutes);
        } else {
            String period = hours >= 12 ? "PM" : "AM";
            int hour12 = hours % 12;
            if (hour12 == 0)
                hour12 = 12;

            timeString = String.format("%02d:%02d %s", hour12, minutes, period
            );
        }

        HudRenderUtil.drawIconText(
                context,
                client,
                layout,
                Items.CLOCK.getDefaultInstance(),
                timeString
        );

        return true;
    }
}
