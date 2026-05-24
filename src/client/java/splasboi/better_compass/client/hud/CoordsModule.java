package splasboi.better_compass.client.hud;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.item.Items;
import net.minecraft.client.Minecraft;


public class CoordsModule implements HudModule{
    static final int TEXT_COLOR = 0xFFFFFFFF;

    @Override
    public boolean render(GuiGraphicsExtractor context, Minecraft client, HudLayout layout) {
        if (client.player == null)
            return false;

        boolean hasCompass = client.player.getInventory().contains(Items.COMPASS.getDefaultInstance());

        if (!hasCompass)
            return false;

        int x = (int)Math.floor(client.player.getX());
        int y = (int)Math.floor(client.player.getY());
        int z = (int)Math.floor(client.player.getZ());

        String coordsText = String.format("X: %d Y: %d Z: %d", x, y, z);

        HudRenderUtil.drawIconText(
                context,
                client,
                layout,
                Items.COMPASS.getDefaultInstance(),
                coordsText
        );

        return true;
    }
}
