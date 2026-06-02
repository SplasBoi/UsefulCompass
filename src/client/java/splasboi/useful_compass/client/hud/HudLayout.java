package splasboi.useful_compass.client.hud;

import org.joml.Vector2i;

public class HudLayout {
    public Vector2i pos = new Vector2i(10, 10);

    public final int lineHeight = 20;

    public void nextLine() {
        pos.y += lineHeight;
    }
}
