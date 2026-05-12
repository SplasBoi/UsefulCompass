package splasboi.better_compass.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public interface HudModule {
    void render(GuiGraphicsExtractor context, Minecraft client, HudLayout layout);
}
