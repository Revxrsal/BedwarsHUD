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
package net.reflxction.bedwarshud.elements.resources;

import net.minecraft.init.Items;
import net.reflxction.bedwarshud.elements.Element;

import java.awt.*;

public class ElementGold extends Element {

    /**
     * Initiates a new Element.
     */
    public ElementGold() {
        super(2);
    }

    /**
     * A simple name for the hud. This is used to save the hud information in the config
     */
    @Override
    public String getSimpleName() {
        return "Gold";
    }

    /**
     * Draws the HUD to the screen
     */
    @Override
    public void draw() {
        drawString("Gold: &cx" + resourceTracker.getAmountOf(Items.gold_ingot), new Color(255, 160, 0));
    }
}
