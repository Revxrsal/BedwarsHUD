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

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.reflxction.bedwarshud.elements.Element;

import java.awt.*;

public class ElementWool extends Element {

    /**
     * Initiates a new Element.
     */
    public ElementWool() {
        super(6);
    }

    /**
     * A simple name for the hud. This is used to save the hud information in the config
     */
    @Override
    public String getSimpleName() {
        return "Wool";
    }

    /**
     * Draws the HUD to the screen
     */
    @Override
    public void draw() {
        drawString("Wool blocks: &cx" + resourceTracker.getAmountOf(new ItemStack(Blocks.wool).getItem()), new Color(68, 63, 255));
    }
}
