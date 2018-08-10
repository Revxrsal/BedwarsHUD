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

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Fired everytime the player receives coins in the game
 */
public class PlayerReceiveCoinsEvent extends Event {

    // The amount of coins received
    private int amount;

    /**
     * Initiates a new event for <strong>posting</strong>
     *
     * @param amount The amount of gold recevied
     */
    private PlayerReceiveCoinsEvent(int amount) {
        this.amount = amount;
    }

    /**
     * An empty constructor, used for <strong>registering</strong>
     */
    public PlayerReceiveCoinsEvent() {
    }

    /**
     * Trigger of the event
     */
    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent event) {
        String text = event.message.getUnformattedText();
        if (!text.startsWith("+")) return;
        if (!text.contains("coins!")) return;
        int amount = detectAmount(text);
        MinecraftForge.EVENT_BUS.post(new PlayerReceiveCoinsEvent(amount));
    }

    /**
     * Detects the amount of coins received using regex
     *
     * @param text Text to detect from
     * @return An int representing the amount of gold received
     */
    private int detectAmount(String text) {
        int coins = 0;
        Pattern p = Pattern.compile("-?\\d+(,\\d{3})*");
        Matcher m = p.matcher(text);
        while (m.find()) {
            coins += Integer.parseInt(m.group());
        }
        return coins;
    }

    /**
     * The amount of coins received
     *
     * @return The amount of coins received
     */
    public int getAmount() {
        return amount;
    }

}
