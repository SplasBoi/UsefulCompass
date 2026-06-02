package splasboi.useful_compass.client.hud;

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
        final boolean gamePaused = client.isPaused();

        for (HudModule module : MODULES) {
            boolean visible = module.shouldRender(client);

            if (!gamePaused) {
                module.updateVisibility(visible);

                module.setLayoutTarget(layout.pos.x, layout.pos.y);

                module.updateAnimation(client);
            }

            if (!module.isAnimatingOrVisible()) {
                continue;
            }

            module.render(context, client, module.getRenderPos());

            if (module.isVisible()) {
                layout.nextLine();
            }
        }
    }
}
