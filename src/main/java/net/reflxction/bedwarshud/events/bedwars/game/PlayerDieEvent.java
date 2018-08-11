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
package net.reflxction.bedwarshud.events.bedwars.game;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.reflxction.bedwarshud.utils.BedwarsMessages;

/**
 * Fired everytime the player dies
 */
public class PlayerDieEvent extends Event {

    // An instance of the bedwars messages
    private BedwarsMessages messages = new BedwarsMessages();

    /**
     * Trigger of the event
     */
    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent event) {
        String text = event.message.getUnformattedText();
        if (getName() == null) return;
        if (!text.contains(getName())) return;
        if (!text.startsWith(getName())) return; // Means it wasn't the player's death
        for (String kill : messages.getKillMessages()) {
            if (text.contains(getName() + " " + kill)){
                MinecraftForge.EVENT_BUS.post(new PlayerDieEvent());
                break;
            }
        }
    }

    /**
     * A shortcut method to {@link EntityPlayer#getName()}
     *
     * @return The player name
     */
    private String getName() {
        return Minecraft.getMinecraft().thePlayer == null ? null : Minecraft.getMinecraft().thePlayer.getName();
    }


}
