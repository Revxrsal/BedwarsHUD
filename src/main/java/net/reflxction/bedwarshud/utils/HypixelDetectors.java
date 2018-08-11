/*
 * * Copyright 2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.reflxction.bedwarshud.utils;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;
import net.reflxction.bedwarshud.events.bedwars.PlayerJoinGameEvent;

/**
 * Utility class for doing Hypixel checks
 */
public class HypixelDetectors {

    // An updated instance of the detector
    public static final HypixelDetectors INSTANCE = new HypixelDetectors();

    // Minecraft singleton instance
    private Minecraft mc = Minecraft.getMinecraft();

    // Whether the current server is Hypixel or not
    private boolean hypixel = false;

    // Whether the player is playing bedwars right now
    private boolean bedwars = false;

    /**
     * Whether the player is on hypixel or not
     *
     * @return Whether the player is on hypixel or not
     */
    public boolean hypixel() {
        return hypixel;
    }

    /**
     * Whether the player is in a bedwars game or not
     *
     * @return Whether the player is in a bedwars game or not
     */
    public boolean bedwars() {
        return bedwars;
    }

    /**
     * Updates the boolean values when connecting to a server
     */
    @SubscribeEvent
    public void onFMLNetworkClientConnectedToServer(ClientConnectedToServerEvent event) {
        if (mc.getCurrentServerData() != null && mc.getCurrentServerData().serverIP.contains("hypixe")) {
            hypixel = true;
            bedwars = false; // As the player hasn't joined a Bedwars game yet
        }
    }

    /**
     * Updates the boolean values when disconnecting from a server
     */
    @SubscribeEvent
    public void onFMLNetworkClientDisconnectionFromServer(ClientDisconnectionFromServerEvent event) {
        hypixel = false;
        bedwars = false;
    }

    /**
     * Used to update the bedwars value by checking the player scoreboard
     */
    @SubscribeEvent
    public void onTickServerTick(ClientTickEvent event) {
        if (!hypixel()) return;
        if (mc.theWorld != null && mc.theWorld.getScoreboard() != null && mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1).getDisplayName() != null) {
            String firstLine = mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1).getDisplayName();
            bedwars = firstLine.contains("BED ");
        }
    }

    @SubscribeEvent
    public void onPlayerJoinGame(PlayerJoinGameEvent event) {
        bedwars = true;
    }
}
