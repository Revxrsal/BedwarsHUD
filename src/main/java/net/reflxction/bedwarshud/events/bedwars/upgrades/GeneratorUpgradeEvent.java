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
import net.reflxction.bedwarshud.utils.ChatColor;
import net.reflxction.bedwarshud.utils.HypixelDetectors;

/**
 * Fired when the island's generator forge is upgraded
 */
public class GeneratorUpgradeEvent extends Event {

    // The new generator level
    private GeneratorLevel newLevel;

    /**
     * Initiates a new event for posting
     *
     * @param newLevel The new generator level
     */
    public GeneratorUpgradeEvent(GeneratorLevel newLevel) {
        this.newLevel = newLevel;
    }

    /**
     * Constructor for registering the event
     */
    public GeneratorUpgradeEvent() {
    }

    /**
     * Trigger of the event
     */
    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent event) {
        if (!HypixelDetectors.INSTANCE.bedwars()) return;
        String text = event.message.getUnformattedText();
        if (!text.contains("purchased ")) return;
        String name = text.split(" ")[2] + " " + text.split(" ")[3];
        MinecraftForge.EVENT_BUS.post(new GeneratorUpgradeEvent(GeneratorLevel.getByName(name)));
    }

    /**
     * The new generator level
     *
     * @return The new generator level
     */
    public GeneratorLevel getNewLevel() {
        return newLevel;
    }

    /**
     * An enumeration for all generator levels
     */
    public enum GeneratorLevel {

        /**
         * Normal forge, this is the default
         */
        NORMAL("&7Normal", -1),

        /**
         * Iron forge, the first generator upgrade
         */
        IRON_FORGE("&fIron Forge", 0),

        /**
         * Golden forge, the second generator upgrade
         */
        GOLDEN_FORGE("&6Golden Forge", 1),

        /**
         * Emerald forge, the third generator upgrade
         */
        EMERALD_FORGE("&aEmerald Forge", 2),

        /**
         * Molten forge, the fourth and highest generator upgrade
         */
        MOLTEN_FORGE("&cMolten Forge", 3);

        // The name Hypixel uses
        private String hypixelName;

        // The level index of the upgrade
        private int levelIndex;

        /**
         * Represents a generator level
         *
         * @param hypixelName The name Hypixel uses
         * @param levelIndex  The level index of the upgrade
         */
        GeneratorLevel(String hypixelName, int levelIndex) {
            this.hypixelName = ChatColor.format(hypixelName);
            this.levelIndex = levelIndex;
        }

        /**
         * The name Hypixel uses
         *
         * @return The name Hypixel uses for the upgrade
         */
        public String getHypixelName() {
            return hypixelName;
        }

        /**
         * The level index of the upgrade
         *
         * @return The level index of the upgrade
         */
        public int getLevelIndex() {
            return levelIndex;
        }

        /**
         * Returns a generator level with the given index
         *
         * @param index Index to retreive from
         * @return The generator level with the index
         */
        private static GeneratorLevel getByIndex(int index) {
            for (GeneratorLevel level : values()) {
                if (level.getLevelIndex() == index) return level;
            }
            return GeneratorLevel.NORMAL;
        }

        /**
         * Retrieves a generator level from the name
         *
         * @param name Name to retrieve from
         * @return The level that represents the name
         */
        public static GeneratorLevel getByName(String name) {
            for (GeneratorLevel level : values()) {
                if (ChatColor.stripColor(level.getHypixelName()).equals(name)) return level;
            }
            return GeneratorLevel.NORMAL;
        }

        /**
         * Returns the generator level that comes after the given level
         *
         * @param level Level to get the one after
         * @return The generator level after it, would normally
         * return {@link GeneratorLevel#MOLTEN_FORGE} if there is no one available
         */
        public static GeneratorLevel getNext(GeneratorLevel level) {
            return getByIndex(level.getLevelIndex() + 1) == GeneratorLevel.NORMAL ? GeneratorLevel.MOLTEN_FORGE : getByIndex(level.getLevelIndex() + 1);
        }

    }

}
