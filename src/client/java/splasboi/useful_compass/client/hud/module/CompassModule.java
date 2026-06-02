package splasboi.useful_compass.client.hud.module;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.client.Minecraft;
import org.joml.Vector2i;
import splasboi.useful_compass.client.hud.HudModule;
import splasboi.useful_compass.client.hud.HudRenderUtil;
import splasboi.useful_compass.client.util.FormatUtils;
import splasboi.useful_compass.client.util.PlayerInventoryUtil;


public class CompassModule extends HudModule {
    private String coordsText = "";

    @Override
    public boolean shouldRender(Minecraft client) {
        if (client == null || client.player == null) {
            return false;
        }

        return PlayerInventoryUtil.hasItem(client.player, Items.COMPASS);
    }

    @Override
    public void render(GuiGraphicsExtractor ctx, Minecraft client, Vector2i pos) {
        Player player = client.player;

        if (player != null) {
            int x = (int)Math.floor(player.getX());
            int y = (int)Math.floor(player.getY());
            int z = (int)Math.floor(player.getZ());

            coordsText = FormatUtils.coords(x, y, z);
        }

        HudRenderUtil.drawIconText(
                ctx,
                client,
                pos,
                Items.COMPASS.getDefaultInstance(),
                coordsText
        );
    }
}
