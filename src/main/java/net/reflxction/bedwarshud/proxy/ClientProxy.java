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
package net.reflxction.bedwarshud.proxy;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.*;
import net.reflxction.bedwarshud.BedwarsHUD;
import net.reflxction.bedwarshud.commands.BHUDCommand;
import net.reflxction.bedwarshud.elements.ElementManager;
import net.reflxction.bedwarshud.elements.resources.*;
import net.reflxction.bedwarshud.events.bedwars.game.*;
import net.reflxction.bedwarshud.events.bedwars.upgrades.TrapEvent;
import net.reflxction.bedwarshud.events.mod.RenderHUDEvent;
import net.reflxction.bedwarshud.gui.menus.InformationMenu;
import net.reflxction.bedwarshud.gui.menus.ResourcesMenu;
import net.reflxction.bedwarshud.gui.menus.StatisticsMenu;
import net.reflxction.bedwarshud.gui.menus.UpgradesMenu;
import net.reflxction.bedwarshud.gui.page.PageRegistry;
import net.reflxction.bedwarshud.statistics.*;
import net.reflxction.bedwarshud.trackers.StatisticsTracker;
import net.reflxction.bedwarshud.updater.NotificationSender;
import net.reflxction.bedwarshud.updater.VersionChecker;
import net.reflxction.bedwarshud.utils.HypixelDetectors;
import net.reflxction.bedwarshud.utils.Multithreading;

public class ClientProxy implements IProxy {

    // Instance of the version checker
    private static VersionChecker checker = new VersionChecker();

    /**
     * Called before the mod is fully initialized
     * <p>
     * Registries: Initiate variables and client command registries
     *
     * @param event Forge's pre-init event
     */
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if (BedwarsHUD.getSettings().sendNotification()) {
            new Multithreading<>().schedule((o) -> checker.updateState());
        }
        ClientCommandHandler.instance.registerCommand(new BHUDCommand());
    }

    /**
     * Called when the mod has been fully initialized
     * <p>
     * Registries: Events and client-server command registries
     *
     * @param event Forge's init event
     */
    @Override
    public void init(FMLInitializationEvent event) {
        /*
          Register listeners
        */
        MinecraftForge.EVENT_BUS.register(new NotificationSender());
        MinecraftForge.EVENT_BUS.register(new StatisticsTracker());
        MinecraftForge.EVENT_BUS.register(HypixelDetectors.INSTANCE);

        /*
          Register event posts
         */
        // Mod events
        MinecraftForge.EVENT_BUS.register(new RenderHUDEvent());

        // Bedwars events
        MinecraftForge.EVENT_BUS.register(new PlayerKillEvent());
        MinecraftForge.EVENT_BUS.register(new PlayerFinalKillEvent());
        MinecraftForge.EVENT_BUS.register(new PlayerDestroyBedEvent());
        MinecraftForge.EVENT_BUS.register(new PlayerReceiveCoinsEvent());
        MinecraftForge.EVENT_BUS.register(new PlayerDieEvent());
        MinecraftForge.EVENT_BUS.register(new PlayerJoinGameEvent());
        MinecraftForge.EVENT_BUS.register(new GameEndEvent());
        MinecraftForge.EVENT_BUS.register(new TrapEvent());
    }

    /**
     * Called after the mod has been successfully initialized
     * <p>
     * Registries: Mod stuff
     *
     * @param event Forge's post init event
     */
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        PageRegistry.registerPages(
                new InformationMenu(),
                new ResourcesMenu(),
                new UpgradesMenu(),
                new StatisticsMenu());
        ElementManager.registerElements(
                new ElementDiamonds(),
                new ElementGold(),
                new ElementIron(),
                new ElementEmeralds(),
                new ElementArrows(),
                new ElementWool(),
                new ElementCoinsEarned(),
                new ElementDeaths(),
                new ElementFinalKills(),
                new ElementKills(),
                new ElementKillStreak());
    }


    /**
     * Called after {@link FMLServerAboutToStartEvent} and before {@link FMLServerStartedEvent}.
     * <p>
     * Registries: Server commands
     *
     * @param event Forge's server starting event
     */
    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new BHUDCommand());
    }

    public static VersionChecker getChecker() {
        return checker;
    }
}
