package splasboi.useful_compass.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector2i;

public class HudRenderUtil {
    public static void drawIconText(
            GuiGraphicsExtractor context,
            Minecraft client,
            Vector2i pos,
            ItemStack icon,
            String text
    ) {
        if (client == null) {
            return;
        }

        int iconX = pos.x;
        int iconY = pos.y;
        float iconScale = 1.0F;

        int iconSize = Math.round(16 * iconScale);
        int iconCenterY = iconY + (iconSize / 2);

        context.pose().pushMatrix();
        context.pose().translate(iconX, iconY);
        context.pose().scale(iconScale, iconScale);
        context.item(icon, 0, 0);
        context.pose().popMatrix();

        int spacing = 4;

        int textX = iconX + iconSize + spacing;
        int textY = iconCenterY - (client.font.lineHeight / 2);

        context.text(client.font, text, textX, textY,  0xFFFFFFFF, true);
    }
}
