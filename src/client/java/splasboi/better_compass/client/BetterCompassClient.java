package splasboi.better_compass.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import splasboi.better_compass.BetterCompass;
import splasboi.better_compass.client.hud.ClockModule;
import splasboi.better_compass.client.hud.CoordsModule;
import splasboi.better_compass.client.hud.HudLayout;
import splasboi.better_compass.client.hud.HudManager;

public class BetterCompassClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HudManager.register(new CoordsModule());
		HudManager.register(new ClockModule());

		HudElementRegistry.addLast(
				Identifier.fromNamespaceAndPath(BetterCompass.MOD_ID, "hud"),
				(context, tickCounter) -> {
					Minecraft client = Minecraft.getInstance();
					HudManager.renderAll(context, client);
				}
		);
	}
}