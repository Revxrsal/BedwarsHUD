package net.reflxction.bedwarshud.statistics;

import net.reflxction.bedwarshud.elements.Element;

import java.awt.*;

public class ElementDeaths extends Element {

    /**
     * Initiates a new Element.
     */
    public ElementDeaths() {
        super(8);
    }
    /**
     * A simple name for the hud. This is used to save the hud information in the config
     */
    @Override
    public String getSimpleName() {
        return "deaths";
    }
    /**
     * Draws the HUD to the screen
     */
    @Override
    public void draw() {
        drawString("Deaths: &b" + tracker.getDeaths(), Color.RED);
    }
}
