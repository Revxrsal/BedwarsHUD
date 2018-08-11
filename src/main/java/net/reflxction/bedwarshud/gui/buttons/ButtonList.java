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
package net.reflxction.bedwarshud.gui.buttons;

import net.reflxction.bedwarshud.elements.resources.*;

/**
 * The list of all buttons
 */
public class ButtonList {

    // Resources buttons
    private ElementButton
            iron = new ElementButton(0, 50, 100, "&7Iron")
            .setHoverText("Displays the amount of &7Iron &fyou have")
            .setElement(new ElementIron()),
            gold = new ElementButton(1, 50, 120, "&6Gold")
                    .setHoverText("Displays the amount of &6gold &fyou have")
                    .setElement(new ElementGold()),
            diamond = new ElementButton(2, 50, 140, "&bDiamond")
                    .setHoverText("Displays the amount of &bdiamonds &fyou have")
                    .setElement(new ElementDiamonds()),
            emerald = new ElementButton(3, 50, 160, "&aEmeralds")
                    .setHoverText("Displays the amount of &aemeralds &fyou have")
                    .setElement(new ElementEmeralds()),
            arrows = new ElementButton(4, 50, 180, "&fArrows")
                    .setHoverText("Displays the amount of &darrows &fyou have")
                    .setElement(new ElementArrows()),
            wool = new ElementButton(5, 50, 200, "&cWool Blocks")
                    .setHoverText("Displays the amount of &cwool &fyou have")
                    .setElement(new ElementWool());

    // Statistics buttons
    private ElementButton
            kills = new ElementButton(0, 50, 100, "&cKills")
            .setHoverText("The amount of souls you have gathered &c(your kills)"),
            finalKills = new ElementButton(1, 50, 120, "&3Final Kills")
                    .setHoverText("Your final kills in the current game"),
            deaths = new ElementButton(2, 50, 140, "&bDeaths")
                    .setHoverText("Your deaths in the current game"),
            streak = new ElementButton(3, 50, 160, "&dKill Streak")
                    .setHoverText("Your current kill streak (resets on death)"),
            bedsDestroyed = new ElementButton(4, 50, 180, "&aBeds Destroyed")
                    .setHoverText("The amount of beds you've destroyed in the current game"),
            coinsEarnt = new ElementButton(5, 50, 200, "&6Coins earnt")
                    .setHoverText("The amount of &6Bedwars coins &fyou've gained in this game");

    // Upgrades buttons
    private ElementButton
            reinforcedArmor = new ElementButton(0, 50, 100, "&bArmor enchantment level")
            .setHoverText("Your armor's enchant level"),
            sharpenedSwords = new ElementButton(1, 50, 120, "&eSharpened swords")
                    .setHoverText("Whether you've bought &bSharpened Swords &for not"),
            trapsActivated = new ElementButton(2, 50, 140, "&8Activated traps")
                    .setHoverText("The current activated traps"),
            generatorForge = new ElementButton(3, 50, 160, "&6Generator Forge")
                    .setHoverText("Your island generator's forge level (speed)"),
            dragonBuff = new ElementButton(4, 50, 180, "&dDragon Buff")
                    .setHoverText("Whether you've bought &dDragon Buff &for not"),
            haste = new ElementButton(5, 50, 200, "&cHaste")
                    .setHoverText("Your current &6Miniac Miner &flevel");

    // Support buttons
    private ElementButton
            hypixelThread = new ElementButton(0, 50, 100, "&eOfficial Hypixel Thread")
            .setHoverText("The Hypixel thread explaining everything in the mod"),
            github = new ElementButton(1, 50, 120, "&7GitHub Page")
                    .setHoverText("Takes you to the &7GitHub page &fof the mod. It contains the mod source code"),
            discord = new ElementButton(2, 50, 140, "&bDiscord")
                    .setHoverText("&bClick to copy my Discord!");

    // Getters for all the buttons

    public ElementButton getIronButton() {
        return iron;
    }

    public ElementButton getGoldButton() {
        return gold;
    }

    public ElementButton getDiamondButton() {
        return diamond;
    }

    public ElementButton getEmeraldButton() {
        return emerald;
    }

    public ElementButton getArrowsButton() {
        return arrows;
    }

    public ElementButton getWoolButton() {
        return wool;
    }

    public ElementButton getKillsButton() {
        return kills;
    }

    public ElementButton getFinalKillsButton() {
        return finalKills;
    }

    public ElementButton getDeathsButton() {
        return deaths;
    }

    public ElementButton getStreakButton() {
        return streak;
    }

    public ElementButton getBedsDestroyedButton() {
        return bedsDestroyed;
    }

    public ElementButton getCoinsEarntButton() {
        return coinsEarnt;
    }

    public ElementButton getReinforcedArmorButton() {
        return reinforcedArmor;
    }

    public ElementButton getSharpenedSwordsButton() {
        return sharpenedSwords;
    }

    public ElementButton getTrapsActivatedButton() {
        return trapsActivated;
    }

    public ElementButton getGeneratorForgeButton() {
        return generatorForge;
    }

    public ElementButton getDragonBuffButton() {
        return dragonBuff;
    }

    public ElementButton getHasteButton() {
        return haste;
    }

    public ElementButton getHypixelThreadButton() {
        return hypixelThread;
    }

    public ElementButton getDiscordButton() {
        return discord;
    }

    public ElementButton getGithubButton() {
        return github;
    }
}
