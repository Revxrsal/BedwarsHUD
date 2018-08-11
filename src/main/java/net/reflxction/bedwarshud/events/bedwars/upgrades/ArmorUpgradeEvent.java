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

import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * Fired every time the player upgrades the reinforced armor
 */
public class ArmorUpgradeEvent extends Event {

    // The new armor level
    private ArmorLevel newLevel;

    /**
     * Initiates a new event
     *
     * @param newLevel The new level
     */
    public ArmorUpgradeEvent(ArmorLevel newLevel) {
        this.newLevel = newLevel;
    }

    /**
     * The new level after upgrading
     *
     * @return The new level after upgrading
     */
    public ArmorLevel getNewLevel() {
        return newLevel;
    }

    /**
     * Represents an armor level
     */
    public enum ArmorLevel {

        /**
         * None, this is the default one when no upgrade is bought
         */
        NONE(0),

        /**
         * Reinforced Armor I: The first upgrade level
         */
        I(1),

        /**
         * Reinforced Armor II: The second upgrade level
         */
        II(2),

        /**
         * Reinforced Armor III: The third upgrade level
         */
        III(3),

        /**
         * Reinforced Armor IV: The fourth and max upgrade level
         */
        IV(4);

        // The level index
        private int levelIndex;

        /**
         * Represents an armor level
         *
         * @param levelIndex Index level of the upgrade
         */
        ArmorLevel(int levelIndex) {
            this.levelIndex = levelIndex;
        }

        /**
         * The integer of the level index
         *
         * @return The integer of the level index
         */
        public int getLevelIndex() {
            return levelIndex;
        }
    }
}
