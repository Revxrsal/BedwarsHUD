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

import net.minecraft.client.gui.GuiButton;
import net.reflxction.bedwarshud.gui.buttons.ArrowButton.Direction;
import net.reflxction.bedwarshud.utils.ChatColor;

import java.util.List;

/**
 * The button which each page has on the top
 */
public class PageButton extends GuiButton {

    public PageButton(int buttonId, String buttonText) {
        super(buttonId, 205, 50, 200, 20, ChatColor.GREEN + buttonText);
    }

    public void addArrowButton(int prevID, int nextID, List<GuiButton> buttons, boolean addFirst, boolean addLast) {
        ArrowButton previous = new ArrowButton(prevID, 170, 50, Direction.PREVIOUS)
                .setHoverText("Previous page");
        ArrowButton next = new ArrowButton(nextID, 420, 50, Direction.NEXT)
                .setHoverText("Next page");
        if (addFirst) buttons.add(previous);
        if (addLast) buttons.add(next);
    }

}
