package splasboi.useful_compass.client.hud;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.client.Minecraft;


public class CompassModule implements HudModule{
    @Override
    public boolean tryRender(GuiGraphicsExtractor context, Minecraft client, HudLayout layout) {
        Player player = client.player;

        if (player == null)
            return false;

        boolean hasCompass = player.getInventory().contains(Items.COMPASS.getDefaultInstance());

        if (!hasCompass)
            return false;

        int x = (int)Math.floor(player.getX());
        int y = (int)Math.floor(player.getY());
        int z = (int)Math.floor(player.getZ());

        String coordsText = formatCoords(x, y, z);

        HudRenderUtil.drawIconText(
                context,
                client,
                layout,
                Items.COMPASS.getDefaultInstance(),
                coordsText
        );

        return true;
    }

    private String formatCoords(int x, int y, int z) {
        return String.format("X: %d Y: %d Z: %d", x, y, z);
    }
}
