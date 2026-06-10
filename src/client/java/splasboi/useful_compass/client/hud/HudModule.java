package splasboi.useful_compass.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.joml.Vector2f;
import org.joml.Vector2i;

public abstract class HudModule {
    protected final Vector2f renderPos = new Vector2f();
    protected final Vector2f targetPos = new Vector2f();

    protected float entryProgress = 0.0F;

    protected boolean targetVisible = false;
    protected boolean wasVisible = false;

    protected final float EXIT_OFFSET = -40.0F;

    public void setLayoutTarget(float x, float y) {
        targetPos.set(x, y);
    }

    public float easeOutCubic(float x) {
        return 1.0F - (float)Math.pow(1.0F - x, 3.0F);
    }

    public float easeInCubic(float x) {
        return x * x * x;
    }

    public Vector2i getRenderPos() {
        float progress;

        if (targetVisible) {
            progress = easeOutCubic(entryProgress);
        } else {
            progress = easeInCubic(entryProgress);
        }

        float offsetX = EXIT_OFFSET * (1.0F - progress);

        return new Vector2i(
                Math.round(renderPos.x + offsetX),
                Math.round(renderPos.y)
        );
    }

    public void updateAnimation(Minecraft client) {
        float delta = client.getDeltaTracker().getGameTimeDeltaTicks();

        final float moveSpeed = 0.25F;
        final float entrySpeed = 0.15F;

        final float factor = 1.0F - (float)Math.exp(-moveSpeed * delta);

        renderPos.x += (targetPos.x - renderPos.x) * factor;
        renderPos.y += (targetPos.y - renderPos.y) * factor;

        entryProgress += (targetVisible ? 1.0F : -1.0F) * entrySpeed * delta;
        entryProgress = Math.clamp(entryProgress, 0.0F, 1.0F);
    }

    public void updateVisibility(boolean visible) {
        if (!wasVisible && visible) {
            renderPos.set(targetPos);
        }

        targetVisible = visible;
        wasVisible = visible;
    }

    public boolean isAnimatingOrVisible() {
        return targetVisible || entryProgress > 0.001F;
    }

    public boolean isVisible() {
        return targetVisible;
    }

    public abstract boolean shouldRender(Minecraft client);
    public abstract void render(GuiGraphicsExtractor ctx, Minecraft client, Vector2i pos);
}
