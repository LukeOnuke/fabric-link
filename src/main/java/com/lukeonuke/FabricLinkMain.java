package com.lukeonuke;

import com.lukeonuke.event.player.PlayerInitEvent;
import com.lukeonuke.event.player.PlayerJoinEvent;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricLinkMain implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("fabric-link");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Initialize mod");
		ServerPlayConnectionEvents.JOIN.register(new PlayerJoinEvent());
		ServerPlayConnectionEvents.INIT.register(new PlayerInitEvent());
	}
}