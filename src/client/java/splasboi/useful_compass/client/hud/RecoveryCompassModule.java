package splasboi.useful_compass.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class RecoveryCompassModule implements HudModule {
    @Override
    public boolean tryRender(GuiGraphicsExtractor context, Minecraft client, HudLayout layout) {
        Player player = client.player;

        if (player == null)
            return false;

        boolean hasRecoveryCompass = player.getInventory().contains(Items.RECOVERY_COMPASS.getDefaultInstance());

        if (!hasRecoveryCompass)
            return false;

        Optional<GlobalPos> deathPosOptional = player.getLastDeathLocation();

        if (deathPosOptional.isEmpty())
            return false;

        GlobalPos deathPos = deathPosOptional.get();
        ResourceKey<Level> deathDimention = deathPos.dimension();

        boolean playerInDeathDimention = player.level().dimension().equals(deathDimention);

        int x = deathPos.pos().getX();
        int y = deathPos.pos().getY();
        int z = deathPos.pos().getZ();

        String coordsText = formatCoords(x, y, z);

        if (!playerInDeathDimention) {
            coordsText += String.format(" (%s)", getDimensionString(deathDimention));
        }

        HudRenderUtil.drawIconText(
                context,
                client,
                layout,
                Items.RECOVERY_COMPASS.getDefaultInstance(),
                coordsText
        );

        return true;
    }

    private String formatCoords(int x, int y, int z) {
        return String.format("X: %d Y: %d Z: %d", x, y, z);
    }

    private String getDimensionString(ResourceKey<Level> levelKey) {
        String levelId = levelKey.identifier().toString();
        if (levelId.startsWith("minecraft:")) {
            levelId = levelId.substring("minecraft:".length());
        }

        return switch (levelId) {
            case "overworld" -> "Overworld";
            case "the_nether" -> "Nether";
            case "the_end" -> "End";
            default -> levelId;
        };
    }
}
