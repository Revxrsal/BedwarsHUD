package net.reflxction.bedwarshud.statistics;

import net.reflxction.bedwarshud.elements.Element;

import java.awt.*;

public class ElementFinalKills extends Element {

    /**
     * Initiates a new Element.
     */
    public ElementFinalKills() {
        super(9);
    }
    /**
     * A simple name for the hud. This is used to save the hud information in the config
     */
    @Override
    public String getSimpleName() {
        return "finalKills";
    }
    /**
     * Draws the HUD to the screen
     */
    @Override
    public void draw() {
        drawString("Final Kills: &b" + tracker.getFinalKillsCount(), Color.RED);
    }

}
