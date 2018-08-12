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
package net.reflxction.bedwarshud.trackers;

import net.reflxction.bedwarshud.events.bedwars.game.GameEndEvent;

/**
 * The tracker for tracking game upgrades (sharpened swords, generator forge, etc.)
 */
public class UpgradesTracker implements Tracker {

    // If sharpened swords is bought, if dragon buff is bought, if heal pool is bought, if a trap is activated
    private boolean sharpenedSwords, dragonBuff, healPool, trapActivated;

    // The reinforced armor enchantment level, the haste level
    private int reinforcedArmor, miniacMiner;

    /**
     * Whether the player has bought sharpened swords or not
     *
     * @return Whether the player has bought sharpened swords or not
     */
    public boolean isSharpenedSwords() {
        return sharpenedSwords;
    }

    /**
     * Whether dragon buff is bought or not
     *
     * @return Whether dragon buff is bought or not
     */
    public boolean isDragonBuff() {
        return dragonBuff;
    }

    /**
     * Whether heal pool is bought or not
     *
     * @return Whether heal pool is bought or not
     */
    public boolean isHealPool() {
        return healPool;
    }

    /**
     * Whether a trap is active or not
     *
     * @return Whether a trap is activated or not
     */
    public boolean isTrapActivated() {
        return trapActivated;
    }

    /**
     * The current reinforced armor level
     *
     * @return The current reinforced armor level
     */
    public int getReinforcedArmor() {
        return reinforcedArmor;
    }

    /**
     * The current miniac miner level
     *
     * @return The current miniac miner level
     */
    public int getMiniacMiner() {
        return miniacMiner;
    }

    /**
     * {@inheritDoc}
     */
    private void setSharpenedSwords(boolean sharpenedSwords) {
        this.sharpenedSwords = sharpenedSwords;
    }

    /**
     * {@inheritDoc}
     */
    private void setDragonBuff(boolean dragonBuff) {
        this.dragonBuff = dragonBuff;
    }

    /**
     * {@inheritDoc}
     */
    private void setHealPool(boolean healPool) {
        this.healPool = healPool;
    }

    /**
     * {@inheritDoc}
     */
    private void setTrapActivated(boolean trapActivated) {
        this.trapActivated = trapActivated;
    }

    /**
     * {@inheritDoc}
     */
    private void setReinforcedArmor(int reinforcedArmor) {
        this.reinforcedArmor = reinforcedArmor;
    }

    /**
     * {@inheritDoc}
     */
    private void setMiniacMiner(int miniacMiner) {
        this.miniacMiner = miniacMiner;
    }

    /**
     * Resets all the elements in the HUD. This should be called in {@link GameEndEvent}.
     */
    @Override
    public void resetAll() {

    }
}