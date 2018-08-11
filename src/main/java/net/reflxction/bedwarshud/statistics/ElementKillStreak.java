package net.reflxction.bedwarshud.statistics;

import net.reflxction.bedwarshud.elements.Element;

import java.awt.*;

public class ElementKillStreak extends Element {

    /**
     * Initiates a new Element.
     */
    public ElementKillStreak() {
        super(11);
    }
    /**
     * A simple name for the hud. This is used to save the hud information in the config
     */
    @Override
    public String getSimpleName() {
        return "killStreak";
    }
    /**
     * Draws the HUD to the screen
     */
    @Override
    public void draw() {
        drawString("Kill Streak: &b" + tracker.getKillStreak(), Color.RED);
    }

}
