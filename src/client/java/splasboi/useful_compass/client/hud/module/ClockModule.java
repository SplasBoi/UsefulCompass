package splasboi.useful_compass.client.hud.module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.Items;
import org.joml.Vector2i;
import splasboi.useful_compass.client.hud.HudModule;
import splasboi.useful_compass.client.hud.HudRenderUtil;
import splasboi.useful_compass.client.util.FormatUtils;
import splasboi.useful_compass.client.util.PlayerInventoryUtil;

public class ClockModule extends HudModule {
    static final boolean use24HourClock = true;
    private String timeString = "";

    @Override
    public boolean shouldRender(Minecraft client) {
        if (client == null) {
            return false;
        }

        if (client.level == null) {
            return false;
        }

        if (client.player == null) {
            return false;
        }

        return PlayerInventoryUtil.hasItem(client.player, Items.CLOCK);
    }

    @Override
    public void render(GuiGraphicsExtractor ctx, Minecraft client, Vector2i pos) {
        if (client != null && client.level != null) {
            long time = client.level.getDefaultClockTime() % 24000;

            int hour = (int)((time / 1000 + 6) % 24);
            int minute = (int)((time % 1000) * 60 / 1000);

            timeString = FormatUtils.time(hour, minute, use24HourClock);
        }

        HudRenderUtil.drawIconText(
                ctx,
                client,
                pos,
                Items.CLOCK.getDefaultInstance(),
                timeString
        );
    }
}
