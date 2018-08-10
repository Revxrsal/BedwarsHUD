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
package net.reflxction.bedwarshud.gui.menus;

import net.minecraft.client.gui.GuiButton;
import net.reflxction.bedwarshud.gui.page.Page;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class InformationMenu extends Page {

    /**
     * The name of the page
     *
     * @return The name of the page
     */
    @Override
    public String getPageName() {
        return "Information";
    }

    /**
     * The ID of the "<" button
     *
     * @return ^
     */
    @Override
    public int previousButtonId() {
        return 9;
    }

    /**
     * The index of the page, just like a book
     *
     * @return ^
     */
    @Override
    public int getIndex() {
        return 3;
    }

    /**
     * The ID of the ">" button
     *
     * @return ^
     */
    @Override
    public int nextButtonId() {
        return 10;
    }

    /**
     * Whether the current page is the last page
     * This is false by default, and is to be overrided by the last page
     *
     * @return True if the current page is the last, false if otherwise
     */
    @Override
    protected boolean last() {
        return true;
    }

    /**
     * Called when the GUI is starting to draw buttons
     */
    @Override
    public void initGui() {
        addButtons(
                buttons.getHypixelThreadButton(),
                buttons.getGithubButton(),
                buttons.getDiscordButton());
        super.initGui();
    }

    /**
     * Called when a button is clicked
     *
     * @param button Button that was clicked
     */
    @Override
    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                openURL("https://github.com/ReflxctionDev/BedwarsHUD");
                break;
            case 1:
                openURL("https://github.com/ReflxctionDev/BedwarsHUD");
                break;
            case 2:
                setClipboard("Reflxction v2.7#6780");
                break;
        }
        super.managePages(button);
        // TODO add button actions
    }

    /**
     * @return Whether the GUI acts like a pause menu when opened
     */
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    /**
     * Opens a URL to the browser
     *
     * @param url URL to open
     */
    private void openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the given string as the clipboard
     *
     * @param text Text to add to the clipboard
     */
    private void setClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

}