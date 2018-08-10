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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.reflxction.bedwarshud.utils.ChatColor;

import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText;

/**
 * Represents an arrow button
 */
public class ArrowButton extends GuiButton {

    /**
     * Initiates a new ArrowButton
     *
     * @param buttonID  ID of the button
     * @param xPos      X position of the button
     * @param yPos      Y position of the button
     * @param direction Direction of the button
     * @see Direction
     */
    ArrowButton(int buttonID, int xPos, int yPos, Direction direction) {
        super(buttonID, xPos, yPos, 20, 20, direction.toString());
    }

    // Lines of the hover text
    private List<String> hoverText = new ArrayList<>();

    /**
     * Draws this button to the screen.
     */
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        super.drawButton(mc, mouseX, mouseY);
        drawHover(mouseX, mouseY);
    }

    /**
     * Draws a hover box
     *
     * @param mouseX Mouse X position
     * @param mouseY Mouse Y position
     */
    private void drawHover(int mouseX, int mouseY) {
        if (this.isMouseOver()) {
            Minecraft mc = Minecraft.getMinecraft();
            ScaledResolution resolution = new ScaledResolution(mc);
            drawHoveringText(hoverText, mouseX, mouseY, resolution.getScaledWidth(), resolution.getScaledHeight(), 350, mc.fontRendererObj);
        }
    }

    /**
     * Sets the hover text
     *
     * @param lines Lines to show when hovering
     */
    ArrowButton setHoverText(String... lines) {
        for (String line : lines) {
            line = ChatColor.format(line);
            hoverText.add(line);
        }
        return this;
    }

    /**
     * Represents an arrow direction
     */
    public enum Direction {

        /**
         * The "previous" arrow direction
         */
        PREVIOUS(ChatColor.format("&l<")),

        /**
         * The "next" arrow direction
         */
        NEXT(ChatColor.format("&l>"));

        // The text of the button
        private String text;

        /**
         * Initiates a new button direction
         *
         * @param s String of the direction
         */
        Direction(String s) {
            text = s;
        }

        /**
         * As this method is called everytime the object is required to be
         * a string, it's being overrided to include the arrow text
         *
         * @return The arrow text
         */
        @Override
        public String toString() {
            return text;
        }

    }

}
