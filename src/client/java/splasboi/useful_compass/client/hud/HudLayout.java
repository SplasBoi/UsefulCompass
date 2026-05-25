package splasboi.useful_compass.client.hud;

public class HudLayout {
    public int x = 10;
    public int y = 10;

    public final int lineHeight = 9;

    public void nextLine() {
        y += lineHeight;
    }

    public void gap(int pixels) {
        y += pixels;
    }
}
