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
package net.reflxction.bedwarshud.utils;

import net.minecraft.client.gui.GuiButton;

public interface IGuiHelper {

    /**
     * Draws the screen and all its components
     *
     * @param mouseX       X of the current mouse
     * @param mouseY       Y of the current mouse
     * @param partialTicks Current ticks of the game
     */
    void drawScreen(int mouseX, int mouseY, float partialTicks);

    /**
     * Called when the GUI is starting to draw buttons
     */
    void initGui();

    /**
     * Called when a button is clicked
     *
     * @param button Button that was clicked
     */
    void actionPerformed(GuiButton button);

    /**
     * @return Whether the GUI acts like a pause menu when opened
     */
    boolean doesGuiPauseGame();

}
