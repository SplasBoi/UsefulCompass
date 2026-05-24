package splasboi.better_compass.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import java.util.ArrayList;
import java.util.List;

public class HudManager {
    private static final List<HudModule> MODULES = new ArrayList<>();

    public static void register(HudModule module) {
        MODULES.add(module);
    }

    public static void renderAll(GuiGraphicsExtractor context, Minecraft client) {
        HudLayout layout = new HudLayout();

        for (HudModule module : MODULES) {
            boolean rendered = module.render(context, client, layout);

            if (rendered) {
                layout.gap(10);
            }
        }
    }
}
