package net.reflxction.bedwarshud.statistics;

import net.reflxction.bedwarshud.elements.Element;

import java.awt.*;

public class ElementCoinsEarned extends Element {


    /**
     * Initiates a new Element.
     */
    public ElementCoinsEarned() {
        super(7);
    }

    @Override
    public String getSimpleName() {
        return "coinsEarned";
    }
    /**
     * Draws the HUD to the screen
     */
    @Override
    public void draw() {
        drawString("Coins Earned: &b" + tracker.getCoinsEarnt(), Color.RED);
    }

}
