package splasboi.useful_compass.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import splasboi.useful_compass.UsefulCompass;
import splasboi.useful_compass.client.hud.module.ClockModule;
import splasboi.useful_compass.client.hud.module.CompassModule;
import splasboi.useful_compass.client.hud.module.RecoveryCompassModule;
import splasboi.useful_compass.client.hud.HudManager;


public class UsefulCompassClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HudManager.register(new CompassModule());
		HudManager.register(new RecoveryCompassModule());
		HudManager.register(new ClockModule());

		HudElementRegistry.addLast(
				Identifier.fromNamespaceAndPath(UsefulCompass.MOD_ID, "hud"),
				(context, tickCounter) -> {
					Minecraft client = Minecraft.getInstance();
					HudManager.renderAll(context, client);
				}
		);
	}
}