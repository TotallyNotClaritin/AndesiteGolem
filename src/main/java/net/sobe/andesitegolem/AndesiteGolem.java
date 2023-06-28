package net.sobe.andesitegolem;

import net.fabricmc.api.ModInitializer;
import net.sobe.util.ModRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class AndesiteGolem implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("andesitegolem");
	public static final String MOD_ID = "andesitegolem";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModRegistries.registerMods();
		GeckoLib.initialize();
		LOGGER.info("It's the boy, the andesite boy!");
	}
}
