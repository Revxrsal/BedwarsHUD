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
package net.reflxction.bedwarshud.events.bedwars;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.reflxction.bedwarshud.utils.HypixelDetectors;

/**
 * Fired when the player joins a bedwars game
 */
public class PlayerJoinGameEvent extends Event {

    /**
     * Trigger of the event
     */
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent e) {
        if (!HypixelDetectors.INSTANCE.hypixel()) return;
        try {
            // We do special checks (one using UUID and one using profile name) in order to achieve the event, as the GameProfile may change or screw things up.
            if (e.entity instanceof EntityPlayerSP) {
                EntityPlayerSP player = ((EntityPlayerSP) e.entity);
                if (player.getName().equals(Minecraft.getMinecraft().thePlayer.getName())) {
                    if ((e.world.getScoreboard().getObjectiveInDisplaySlot(1).getDisplayName() != null && e.world.getScoreboard().getObjectiveInDisplaySlot(1).getDisplayName().contains("BED ")) || HypixelDetectors.INSTANCE.bedwars()) {
                        PlayerJoinGameEvent event = new PlayerJoinGameEvent();
                        MinecraftForge.EVENT_BUS.post(event);
                    }
                }
            } else if (e.entity instanceof EntityPlayerMP) {
                EntityPlayerMP player = (EntityPlayerMP) e.entity;
                if (player.getPersistentID().equals(Minecraft.getMinecraft().thePlayer.getUniqueID())) {
                    if ((e.world.getScoreboard().getObjectiveInDisplaySlot(1).getDisplayName() != null && e.world.getScoreboard().getObjectiveInDisplaySlot(1).getDisplayName().contains("BED ")) || HypixelDetectors.INSTANCE.bedwars()) {
                        PlayerJoinGameEvent event = new PlayerJoinGameEvent();
                        MinecraftForge.EVENT_BUS.post(event);
                    }
                }
            }
            // Ignore the exception caused by the player's game profile
        } catch (NullPointerException ignored) {
        }
    }
}
