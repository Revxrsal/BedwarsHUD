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
package net.reflxction.bedwarshud.events.bedwars.game;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Fired on every game end
 */
public class GameEndEvent extends Event {

    // Whether the event is posted or not. This is to avoid repeated event calls
    private boolean posted = false;

    /**
     * Titles that the player may encounter on game ends
     */
    private String[] titles = new String[]{
            "VICTORY",
            "GAME OVER",
            "GAME END"
    };

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (posted) return;
        if (event.type == ElementType.TEXT) {
            String title = getCurrentTitle();
            if (title.equals("null")) return;
            for (String gameTitle : titles) {
                if (title.contains(gameTitle)) {
                    MinecraftForge.EVENT_BUS.post(new GameEndEvent());
                    posted = true;
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            posted = false;
                        }
                    }, 10000);
                }
            }
        }
    }

    /**
     * The current game title. This method uses reflection since {@link GuiIngame#field_175201_x} has protected access
     *
     * @return The current game title on screen
     */
    private String getCurrentTitle() {
        try {
            return (String) ReflectionHelper.findField(GuiIngame.class, "displayedTitle", "field_175201_x").get(Minecraft.getMinecraft().ingameGUI);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldError e) {
            try {
                return (String) ReflectionHelper.findField(GuiIngame.class, "field_73838_g", "field_175201_x").get(Minecraft.getMinecraft().ingameGUI);
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
        }
        return "null";
    }
}
