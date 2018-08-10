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
package net.reflxction.bedwarshud.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.reflxction.bedwarshud.BedwarsHUD;
import net.reflxction.bedwarshud.gui.menus.ResourcesMenu;
import net.reflxction.bedwarshud.proxy.ClientProxy;
import net.reflxction.bedwarshud.utils.Multithreading;
import net.reflxction.bedwarshud.utils.message.SimpleSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class which handles command input for "/bedwarshud"
 */
public class BHUDCommand implements ICommand {

    /**
     * Gets the name of the command
     */
    @Override
    public String getCommandName() {
        return "bedwarshud";
    }

    /**
     * Gets the usage string for the command.
     *
     * @param sender The command sender that executed the command
     */
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/bedwarshud <update / check / shadow>";
    }

    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("bwhud");
        return aliases;
    }

    /**
     * Callback when the command is invoked
     *
     * @param sender The command sender that executed the command
     * @param args   The arguments that were passed
     */
    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        switch (args.length) {
            case 0:
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Minecraft.getMinecraft().displayGuiScreen(new ResourcesMenu());
                    }
                }, 50);

                break;
            case 1:
                switch (args[0]) {
                    case "shadow":
                        BedwarsHUD.getSettings().setShadow(!BedwarsHUD.getSettings().shadow());
                        SimpleSender.send(BedwarsHUD.getSettings().shadow() ? "&aShadows have been enabled" : "&cShadows have been disabled");
                        break;
                    case "update":
                        if (ClientProxy.getChecker().isUpdateAvailable()) {
                            new Multithreading<>().schedule((foo) -> {
                                if (BedwarsHUD.getUpdateManager().updateMod()) {
                                    SimpleSender.send("&aSuccessfully updated the mod! Restart your game to see changes.");
                                } else {
                                    SimpleSender.send("&cFailed to update mod! To avoid any issues, delete the mod jar and install it manually again.");
                                }
                            });
                        } else {
                            SimpleSender.send("&cNo updates found. You're up to date!");
                        }
                        break;
                    case "check":
                        BedwarsHUD.getSettings().setSendNotification(!BedwarsHUD.getSettings().sendNotification());
                        SimpleSender.send(BedwarsHUD.getSettings().sendNotification() ? "&aYou will be notified on updates" : "&cYou will no longer be notified on updates");
                        break;
                    default:
                        SimpleSender.send("&cIncorrect command usage. Try " + getCommandUsage(sender));
                        break;
                }
                break;
        }
    }

    /**
     * Returns true if the given command sender is allowed to use this command.
     *
     * @param sender The command sender that executed the command
     */
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }


    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        List<String> tab = new ArrayList<>();
        tab.add("toggle");
        tab.add("update");
        tab.add("check");
        return tab;
    }

    /**
     * Return whether the specified command parameter index is a username parameter.
     *
     * @param args  The arguments that were passed
     * @param index
     */
    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
