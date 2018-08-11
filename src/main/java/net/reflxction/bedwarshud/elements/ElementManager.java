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
package net.reflxction.bedwarshud.elements;

import net.minecraftforge.common.MinecraftForge;
import net.reflxction.bedwarshud.utils.Multithreading;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class which registers elements
 */
public class ElementManager {

    /**
     * Registers the given elements. This is used to register listeners
     * and update the element position all the way on the game
     *
     * @param elements Elements to register
     */
    public static void registerElements(Element... elements) {
        for (Element element : elements) {
            MinecraftForge.EVENT_BUS.register(element);
        }
        new Multithreading<>().schedule((o) -> {
            for (Element element : elements) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        element.updatePosition();
                    }
                }, 0, 50);
            }
        });
    }

}
