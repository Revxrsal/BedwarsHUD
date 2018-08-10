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
import net.reflxction.bedwarshud.elements.element.Element;
import net.reflxction.bedwarshud.utils.ChatColor;

import java.util.ArrayList;
import java.util.List;

import static net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText;

/**
 * Represents an element button
 */
public class ElementButton extends GuiButton {

    // Lines of the hover text
    private List<String> hoverText = new ArrayList<>();

    // Element of the button
    private Element element;

    /**
     * Initiates a new element button
     *
     * @param buttonId ID of the button
     * @param x        X axis position of the button
     * @param y        Y axis position of the button
     */
    ElementButton(int buttonId, int x, int y, String text) {
        super(buttonId, x, y, 170, 20, ChatColor.format(text));
    }

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
    ElementButton setHoverText(String... lines) {
        for (String line : lines) {
            line = ChatColor.format(line);
            hoverText.add(line);
        }
        return this;
    }

    /**
     * Sets the element
     *
     * @param element Element to set
     */
    public ElementButton setElement(Element element) {
        this.element = element;
        return this;
    }

    /**
     * @return The button element
     */
    public Element getElement() {
        return element;
    }
}
