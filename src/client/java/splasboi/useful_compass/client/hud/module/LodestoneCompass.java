package splasboi.useful_compass.client.hud.module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.level.Level;
import org.joml.Vector2i;
import splasboi.useful_compass.client.hud.HudModule;
import splasboi.useful_compass.client.util.HudRenderUtil;
import splasboi.useful_compass.client.util.FormatUtils;

public class LodestoneCompass extends HudModule {
    private GlobalPos cachedGlobalPos;
    private String coordsText = "";
    private ItemStack compassIcon;

    public static ItemStack createLodestoneCompassIcon(LodestoneTracker tracker) {
        ItemStack compass;
        compass = Items.COMPASS.getDefaultInstance();
        compass.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);

        if (tracker != null) {
            compass.set(DataComponents.LODESTONE_TRACKER, tracker);
        }

        return compass;
    }

    private boolean findAndSetLodestoneTracker(ItemStack stack) {
        if (stack == null) {
            return false;
        }

        LodestoneTracker tracker = stack.get(DataComponents.LODESTONE_TRACKER);
        compassIcon = createLodestoneCompassIcon(tracker);

        if (tracker == null || tracker.target().isEmpty()) {
            return false;
        }

        cachedGlobalPos = tracker.target().get();
        return true;
    }

    @Override
    public boolean shouldRender(Minecraft client) {
        if (client == null || client.player == null) {
            return false;
        }

        Player player = client.player;

        if (findAndSetLodestoneTracker(player.containerMenu.getCarried())) {
            return true;
        }

        for (ItemStack stack : player.getInventory()) {
            if (findAndSetLodestoneTracker(stack)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void render(GuiGraphicsExtractor ctx, Minecraft client, Vector2i pos) {
        if (cachedGlobalPos != null && client.player != null && client.level != null) {
            BlockPos blockPos = cachedGlobalPos.pos();
            ResourceKey<Level> lodestoneDimension = cachedGlobalPos.dimension();
            boolean playerInDeathDimension = client.player.level().dimension().equals(lodestoneDimension);

            int x = (int)Math.floor(blockPos.getX());
            int y = (int)Math.floor(blockPos.getY());
            int z = (int)Math.floor(blockPos.getZ());

            coordsText = FormatUtils.coords(x, y, z);

            if (!playerInDeathDimension) {
                coordsText += " (" + FormatUtils.dimension(lodestoneDimension) + ")";
            }
        }

        final ItemStack icon = compassIcon != null ? compassIcon : Items.COMPASS.getDefaultInstance();

        HudRenderUtil.drawIconText(
                ctx,
                client,
                pos,
                icon,
                coordsText
        );
    }
}
