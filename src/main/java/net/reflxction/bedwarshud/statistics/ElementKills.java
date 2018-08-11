package net.reflxction.bedwarshud.statistics;

import net.reflxction.bedwarshud.elements.Element;
import net.reflxction.bedwarshud.trackers.StatisticsTracker;

import java.awt.*;

public class ElementKills extends Element {

    /**
     * Initiates a new Element.
     */
    public ElementKills() {
        super(10);
    }
    /**
     * A simple name for the hud. This is used to save the hud information in the config
     */
    @Override
    public String getSimpleName() {
        return "kills";
    }
    /**
     * Draws the HUD to the screen
     */
    @Override
    public void draw() {
        drawString("Kills: &b" + tracker.getKillsCount(), Color.RED);
    }
}
