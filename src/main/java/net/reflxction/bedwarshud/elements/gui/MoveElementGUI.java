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
package net.reflxction.bedwarshud.elements.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.reflxction.bedwarshud.elements.element.Element;
import net.reflxction.bedwarshud.gui.menus.ResourcesMenu;
import net.reflxction.bedwarshud.utils.ChatColor;

import java.io.IOException;

/**
 * The GUI for moving elements
 */
public class MoveElementGUI extends GuiScreen {

    // The element that should be moved in the GUI
    private Element element;

    // The save button
    private GuiButton buttonSave = new GuiButton(100, 125, 15, 80, 20, "Save");

    // The back button
    private GuiButton buttonBack = new GuiButton(101, 15, 15, 80, 20, "Back");

    // The delete button
    private GuiButton buttonDelete = new GuiButton(102, 235, 15, 80, 20, ChatColor.RED + "Delete");

    /**
     * Opens a new GUI to move an element
     *
     * @param element Element to open for and move
     */
    public MoveElementGUI(Element element) {
        this.element = element;
    }

    /**
     * Draws the screen and all its components
     *
     * @param mouseX       X of the current mouse
     * @param mouseY       Y of the current mouse
     * @param partialTicks Current ticks of the game
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    /**
     * Called when the GUI is starting to draw buttons
     */
    @Override
    public void initGui() {
        super.initGui();
        buttonList.add(buttonBack);
        buttonList.add(buttonSave);
        buttonList.add(buttonDelete);
        buttonList.add(element);
    }

    /**
     * Called when a button is clicked
     *
     * @param button Button that was clicked
     */
    @Override
    public void actionPerformed(GuiButton button) throws IOException {
        element.setBeingMoved(button.id == element.id);
        switch (button.id) {
            case 100:
                element.saveToConfig();
                buttonSave.displayString = ChatColor.format("&aSaved!");
                break;
            case 101:
                mc.displayGuiScreen(new ResourcesMenu());
                break;
            case 102:
                element.setEnabled(false);
                mc.displayGuiScreen(new ResourcesMenu());
                break;
        }
        element.setBeingMoved(button.id == element.id || element.isMouseOver());

        super.actionPerformed(button);
    }

    /**
     * @return Whether the GUI acts like a pause menu when opened
     */
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int clickedMouseButton) throws IOException {
        if (element.isBeingMoved() && !buttonSave.isMouseOver() && !buttonBack.isMouseOver() && !buttonDelete.isMouseOver()) {
            buttonSave.displayString = "Save";
            element.xPosition = mouseX;
            element.yPosition = mouseY;
            element.x = mouseX;
            element.y = mouseY;
            element.saveToConfig();
            element.updatePosition();
        }
        super.mouseClicked(mouseX, mouseY, clickedMouseButton);
    }
}
