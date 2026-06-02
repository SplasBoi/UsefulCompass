package splasboi.useful_compass.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.joml.Vector2i;
import splasboi.useful_compass.client.util.FormatUtils;
import splasboi.useful_compass.client.util.PlayerInventoryUtil;

import java.util.Optional;

public class RecoveryCompassModule extends HudModule {
    private GlobalPos cachedDeathPos;
    private String coordsText = "";

    @Override
    public boolean shouldRender(Minecraft client) {
        Player player = client.player;

        if (player == null) {
            return false;
        }

        boolean hasRecoveryCompass = PlayerInventoryUtil.hasItem(player, Items.RECOVERY_COMPASS);
        Optional<GlobalPos> deathPosOptional = player.getLastDeathLocation();

        if (!hasRecoveryCompass || deathPosOptional.isEmpty()) {
            return false;
        }

        cachedDeathPos = deathPosOptional.get();
        return true;
    }

    @Override
    public void render(GuiGraphicsExtractor ctx, Minecraft client, Vector2i pos) {
        if (client != null && client.player != null && cachedDeathPos != null) {
            Player player = client.player;

            boolean hasRecoveryCompass = PlayerInventoryUtil.hasItem(player, Items.RECOVERY_COMPASS);

            ResourceKey<Level> deathDimension = cachedDeathPos.dimension();
            boolean playerInDeathDimension = player.level().dimension().equals(deathDimension);

            int x = cachedDeathPos.pos().getX();
            int y = cachedDeathPos.pos().getY();
            int z = cachedDeathPos.pos().getZ();

            coordsText = FormatUtils.coords(x, y, z);

            if (!playerInDeathDimension) {
                coordsText += " (" + FormatUtils.dimension(deathDimension) + ")";
            }
        }

        HudRenderUtil.drawIconText(
                ctx,
                client,
                pos,
                Items.RECOVERY_COMPASS.getDefaultInstance(),
                coordsText
        );
    }
}
