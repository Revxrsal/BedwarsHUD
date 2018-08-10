
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
package net.reflxction.bedwarshud.gui.page;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.reflxction.bedwarshud.gui.buttons.ButtonList;
import net.reflxction.bedwarshud.gui.buttons.ElementButton;
import net.reflxction.bedwarshud.gui.buttons.PageButton;

import java.util.Arrays;

/**
 * A GUI Page
 */
public abstract class Page extends GuiScreen {

    // List of all the GUI buttons
    protected ButtonList buttons = new ButtonList();

    // Instance of the page manager
    private PageManager pageManager = new PageManager();

    /**
     * The name of the page
     *
     * @return The name of the page
     */
    public abstract String getPageName();

    /**
     * The ID of the "<" button
     *
     * @return ^
     */
    public abstract int previousButtonId();

    /**
     * The index of the page, just like a book
     *
     * @return ^
     */
    public abstract int getIndex();

    /**
     * The ID of the ">" button
     *
     * @return ^
     */
    public abstract int nextButtonId();

    // The button that manages pages
    private PageButton button = new PageButton(10, getPageName());

    /**
     * Whether the current page is the first page
     * This is false by default, and is to be overrided by the first page
     *
     * @return True if the current page is the first, false if otherwise
     */
    protected boolean first() {
        return false;
    }

    /**
     * Whether the current page is the last page
     * This is false by default, and is to be overrided by the last page
     *
     * @return True if the current page is the last, false if otherwise
     */
    protected boolean last() {
        return false;
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     *
     * @param mouseX       The current mouse X
     * @param mouseY       The current mouse Y
     * @param partialTicks The game ticks loop
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }


    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    @Override
    public void initGui() {
        buttonList.add(button);
        button.addArrowButton(previousButtonId(), nextButtonId(), buttonList, !first(), !last());
    }

    /**
     * Adds a button to the GUI
     *
     * @param buttons Buttons to add
     */
    protected void addButtons(ElementButton... buttons) {
        buttonList.addAll(Arrays.asList(buttons));
    }

    /**
     * Handles the pages and arrow buttons.
     * This method should be called from the superclass, in the {@link GuiScreen#actionPerformed(GuiButton)}
     *
     * @param button Button that was clicked.
     */
    protected void managePages(GuiButton button) {
        if (button.id == previousButtonId()) {
            if (first()) return; // Since it's the first page, no page is before it
            Page previousPage = pageManager.getPageByIndex(getIndex() - 1);
            Minecraft.getMinecraft().displayGuiScreen(previousPage);
        } else if (button.id == nextButtonId()) {
            if (last()) return; // Since it's the last page, no page is after it
            Page nextPage = pageManager.getPageByIndex(getIndex() + 1);
            Minecraft.getMinecraft().displayGuiScreen(nextPage);
        }
    }

    /**
     * Handles the actions of the element buttons
     */
    protected void handleElementButtonActions(GuiButton button) {
        for (GuiButton b : buttonList) {
            if (button.id == b.id) {
                if (b instanceof ElementButton) {
                    ElementButton e = ((ElementButton) b);
                    e.getElement().openMoveGui(e.getElement());
                }
            }
        }
    }

}