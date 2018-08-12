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

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.reflxction.bedwarshud.events.bedwars.game.*;

/**
 * The tracker for tracking the statistics (kills, bed destroyed, etc.)
 */
public class StatisticsTracker implements Tracker {

    // The kills count, deaths count, coins earnt in the game, current kill streak, beds destroyed count
    private int killsCount = 0, finalKillsCount = 0, deaths = 0, coinsEarnt = 0, killStreak = 0, bedsDestroyed = 0;

    /**
     * The kills count
     */
    public int getKillsCount() {
        return killsCount;
    }

    /**
     * The final kills count
     */
    public int getFinalKillsCount() {
        return finalKillsCount;
    }

    /**
     * The deaths count
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * The total coins earnt in the game
     */
    public int getCoinsEarnt() {
        return coinsEarnt;
    }

    /**
     * The player's current kill streak
     */
    public int getKillStreak() {
        return killStreak;
    }

    /**
     * The destroyed beds count
     */
    public int getBedsDestroyed() {
        return bedsDestroyed;
    }

    /**
     * Sets the kill count
     *
     * @param killsCount New value to set
     */
    private void setKillsCount(int killsCount) {
        this.killsCount = killsCount;
    }

    /**
     * Sets the final kills count
     *
     * @param finalKillsCount New value to set
     */
    private void setFinalKillsCount(int finalKillsCount) {
        this.finalKillsCount = finalKillsCount;
    }

    /**
     * Sets the deaths count
     *
     * @param deaths New value to set
     */
    private void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    /**
     * Sets the amount of coins earnt in the game
     *
     * @param coinsEarnt New value to set
     */
    private void setCoinsEarnt(int coinsEarnt) {
        this.coinsEarnt = coinsEarnt;
    }

    /**
     * Sets the current kill streak
     *
     * @param killStreak New value to set
     */
    private void setKillStreak(int killStreak) {
        this.killStreak = killStreak;
    }

    /**
     * Sets the destroyed beds count
     *
     * @param bedsDestroyed New value to set
     */
    private void setBedsDestroyed(int bedsDestroyed) {
        this.bedsDestroyed = bedsDestroyed;
    }

    /**
     * Resets all the statistics. This is used on game ends
     */
    @Override
    public void resetAll() {
        setKillsCount(0);
        setFinalKillsCount(0);
        setKillStreak(0);
        setDeaths(0);
        setCoinsEarnt(0);
        setBedsDestroyed(0);
    }

    /**
     * Player kill listener
     */
    @SubscribeEvent
    public void onPlayerKill(PlayerKillEvent event) {
        setKillsCount(getKillsCount() + 1);
        setKillStreak(getKillStreak() + 1);
    }

    /**
     * Player final kill listener
     */
    @SubscribeEvent
    public void onPlayerFinalKill(PlayerFinalKillEvent event) {
        setFinalKillsCount(getFinalKillsCount() + 1);
        setKillsCount(getKillsCount() + 1);
        setKillStreak(getKillStreak() + 1);
    }

    /**
     * Player death listener
     */
    @SubscribeEvent
    public void onPlayerDie(PlayerDieEvent event) {
        setDeaths(getDeaths() + 1);
        setKillStreak(0);
    }

    /**
     * Destroy bed listener
     */
    @SubscribeEvent
    public void onPlayerDestroyBed(PlayerDestroyBedEvent event) {
        setBedsDestroyed(getBedsDestroyed() + 1);
    }

    /**
     * Player receive coins listener
     */
    @SubscribeEvent
    public void onPlayerReceiveCoins(PlayerReceiveCoinsEvent event) {
        setCoinsEarnt(getCoinsEarnt() + event.getAmount());
    }

    /**
     * Game end listener
     */
    @SubscribeEvent
    public void onGameEnd(GameEndEvent event) {
        resetAll();
    }
}
