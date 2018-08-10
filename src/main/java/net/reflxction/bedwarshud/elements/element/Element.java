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
package net.reflxction.bedwarshud.elements.element;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.reflxction.bedwarshud.BedwarsHUD;
import net.reflxction.bedwarshud.elements.gui.MoveElementGUI;
import net.reflxction.bedwarshud.events.mod.RenderHUDEvent;
import net.reflxction.bedwarshud.trackers.ResourcesTracker;
import net.reflxction.bedwarshud.utils.ChatColor;

import java.awt.*;

/**
 * Represents a HUD element
 */
public abstract class Element extends GuiButton {

    // An instance of Minecraft
    private Minecraft mc = Minecraft.getMinecraft();

    // Resources tracker
    protected ResourcesTracker resourceTracker = new ResourcesTracker();

    // Whether the element is currently being moved or not
    private boolean beingMoved;

    // Whether the element is enabled or not
    private boolean enabled = true;

    // Local positions for the element. Those are used to update positions of the element when being mvoed
    public int x;
    public int y;

    /**
     * Initiates a new Element.
     *
     * @param buttonId ID of the element.
     *                 The bigger the ID was, the better. This is to avoid conflicting with other buttons.
     */
    protected Element(int buttonId) {
        super(buttonId, 100, 100, "");
        xPosition = x = BedwarsHUD.getConfig().get(getSimpleName(), "X", 50).getInt();
        yPosition = y = BedwarsHUD.getConfig().get(getSimpleName(), "Y", 50).getInt();
    }

    /**
     * Whether the element is being moved or not
     *
     * @return True if the element is being moved or not
     */
    public boolean isBeingMoved() {
        return beingMoved;
    }

    /**
     * Sets the {@link #beingMoved} state
     *
     * @param beingMoved New state
     */
    public void setBeingMoved(boolean beingMoved) {
        this.beingMoved = beingMoved;
    }

    /**
     * Whether the HUD element is enabled
     */
    private boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets whether the element should render or not
     *
     * @param enabled New value of {@link Element#enabled}
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        BedwarsHUD.getConfig().get(getSimpleName(), "Enabled", false).set(enabled);
        BedwarsHUD.getConfig().save();
    }

    /**
     * Saves all the element information to the config file
     */
    public void saveToConfig() {
        BedwarsHUD.getConfig().get(getSimpleName(), "X", 50)
                .set(xPosition);
        BedwarsHUD.getConfig().get(getSimpleName(), "Y", 50)
                .set(yPosition);
        BedwarsHUD.getConfig().get(getSimpleName(), "Enabled", true)
                .set(isEnabled());
        BedwarsHUD.getConfig().save();
        updatePosition();
    }

    /**
     * Updates the {@link #x} and {@link #y} variables from the config
     */
    public void updatePosition() {
        x = xPosition = BedwarsHUD.getConfig().get(getSimpleName(), "X", 50).getInt();
        y = yPosition = BedwarsHUD.getConfig().get(getSimpleName(), "Y", 50).getInt();
    }

    /**
     * Draws a string to the screen, using the positions of the element
     *
     * @param text  Text to draw
     * @param color Color of the text
     */
    protected void drawString(String text, Color color) {
        if (BedwarsHUD.getSettings().shadow()) {
            //mc.fontRendererObj.drawStringWithShadow(ChatColor.format(text), xPosition, yPosition, color.getRGB());
            drawCenteredString(mc.fontRendererObj, ChatColor.format(text), x + width / 2, y + (height - 8) / 2,
                    color.getRGB());
        } else {
            //mc.fontRendererObj.drawString(ChatColor.format(text), x, y, color.getRGB());
            drawCenteredString(mc.fontRendererObj, ChatColor.format(text), x + width / 2, y + (height - 8) / 2,
                    color.getRGB());
        }
    }

    /**
     * Draws an empty rectangle with borders
     */
    private void drawBorderedRect(int x, int y, int x1, int y1, int borderC) {
        drawRect(x + 1, y + 1, x1, y, borderC);
        drawRect(x, y, x + 1, y1, borderC);
        drawRect(x1, y1, x1 - 1, y + 1, borderC);
        drawRect(x, y1 - 1, x1, y1, borderC);
    }

    /**
     * Draws an outline for the element when it's being moved
     *
     * @param color Color to draw the outline with
     */
    private void drawOutline(Color color) {
        drawBorderedRect(x, y, x + width, y + height, color.getRGB());
    }

    /**
     * Opens the {@link MoveElementGUI} menu for the element so it can be moved across the screen.
     */
    public void openMoveGui(Element instance) {
        Minecraft.getMinecraft().displayGuiScreen(new MoveElementGUI(instance));
        setEnabled(true);
    }

    /**
     * A simple name for the hud. This is used to save the hud information in the config
     */
    public abstract String getSimpleName();

    /**
     * Draws the HUD to the screen
     */
    public abstract void draw();

    /**
     * Override the draw button method, as the method doesn't physically exist.
     */
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.isBeingMoved()) drawOutline(new Color(98, 245, 255));
    }

    /**
     * Play no sound when the button is clicked
     */
    @Override
    public void playPressSound(SoundHandler soundHandlerIn) {
    }

    @SubscribeEvent
    public void onRenderHUD(RenderHUDEvent event) {
        if (!enabled) return;
        if (Minecraft.getMinecraft().gameSettings.showDebugInfo) return;
        draw();
    }
}
