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
package net.reflxction.bedwarshud.events.bedwars.upgrades;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.reflxction.bedwarshud.utils.HypixelDetectors;

/**
 * Represents a trap event (whether activated/bought or triggered)
 */
public class TrapEvent extends Event {

    /**
     * An array consisting of all the trap messages
     */
    private String[] triggerMessages = new String[]{
            "Counter-Offensiev Trap was set off!",
            "Alarm trap set off by",
            "Miner Fatigue Trap was set off!"
    };

    /**
     * An array consisting of messages sent when a trap is bought
     */
    private String[] names = new String[]{
            "It's a trap!",
            "Counter-Offensive Trap",
            "Miner Fatigue Trap",
            "Alarm Trap"
    };

    // Event type
    private Type type;

    /**
     * Initiates a new trap event (for posting)
     *
     * @param type Event type
     */
    private TrapEvent(Type type) {
        this.type = type;
    }

    /**
     * Constructor for registering the event listener
     */
    public TrapEvent() {
    }

    /**
     * The event type
     *
     * @return The event type
     */
    public Type getType() {
        return type;
    }

    /**
     * First trigger of the event
     */
    @SubscribeEvent
    public void onTrapTriggered(ClientChatReceivedEvent event) {
        if (!HypixelDetectors.INSTANCE.bedwars()) return;
        String text = event.message.getUnformattedText().toLowerCase();
        if (!text.contains("trap")) return;
        for (String trap : triggerMessages) {
            if (text.startsWith(trap)) {
                MinecraftForge.EVENT_BUS.post(new TrapEvent(Type.TRIGGERED));
                break;
            }
        }
    }

    /**
     * Second trigger of the event
     */
    @SubscribeEvent
    public void onTrapBought(ClientChatReceivedEvent event) {
        if (!HypixelDetectors.INSTANCE.bedwars()) return;
        String text = event.message.getUnformattedText().toLowerCase();
        if (!text.contains("trap")) return;
        for (String trap : names) {
            if (text.contains("purchased " + trap)) {
                MinecraftForge.EVENT_BUS.post(new TrapEvent(Type.BOUGHT));
                break;
            }
        }
    }

    /**
     * Event type
     */
    public enum Type {

        /**
         * A trap was bought
         */
        BOUGHT,

        /**
         * A trap was triggered
         */
        TRIGGERED

    }

}
