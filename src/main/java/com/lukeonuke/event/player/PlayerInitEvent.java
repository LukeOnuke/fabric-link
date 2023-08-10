package com.lukeonuke.event.player;

import com.lukeonuke.web.DataModel;
import com.lukeonuke.web.DataService;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PlayerInitEvent implements ServerPlayConnectionEvents.Init {
    @Override
    public void onPlayInit(ServerPlayNetworkHandler handler, MinecraftServer server) {
        try {
            String uuid = handler.getPlayer().getUuidAsString();
            DataModel data = DataService.getInstance().getPlayerData(Files.readString(Path.of("guild.txt")), uuid);
            server.sendMessage(Text.of(handler.player.getName().getString() + " joined as " + data.getName()));
        } catch (IOException | InterruptedException e) {
            handler.disconnect(Text.of("Login failed, " + e.getMessage()));
        }
    }
}
