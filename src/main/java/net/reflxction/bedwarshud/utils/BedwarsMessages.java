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
package net.reflxction.bedwarshud.utils;

/**
 * A utility class containing all the messages the mod needs from bedwars
 */
public class BedwarsMessages {

    /**
     * An array consisting of all the kill messages
     */
    private String[] killMessages = new String[]{
            "was struck down by",
            "was turned to dust by",
            "was turned to ash by",
            "was melted by",
            "was fried by",

            "was filled full of lead by",
            "met their end by",
            "lost a drinking contest with",
            "was killed with dynamite by",
            "lost the drew to",

            "died in close combat to",
            "fought to the edge with",
            "stumbled off a ledge with help by",
            "fell to the great marksmanship of",
            "tangoed with",

            "was given the cold shoulder by",
            "was hit off a love bomb from",
            "was out of the league of",
            "was struck with Cupid's arrow by",
            "was no match for",

            "was glazed in BBQ sauce by",
            "slipped in BBQ sauce off the edge spilled by",
            "was not spicy enough for",
            "was thrown chilli powder at by",
            "was sliced up by",

            "was wrapped into a gift by",
            "hit the hard-wood floor because of",
            "was pushed down a slope by",
            "was put on the naughty list by",
            "was turned to gingerbread by",

            "was bitten by",
            "howled into void for",
            "was distracted by a puppy placed by",
            "caught the ball thrown by",
            "played to rough with",

            "be sent to Davy Jones' locker by",
            "be cannon balled to death by",
            "be killed with magic by",
            "be shot and killed by",
            "be killed with metal by",

            "was spooked by",
            "was spooked off the map by",
            "was totally spooked by",
            "was remotely spooked by",
            "was spooked by",

            "got rekt by",
            "took the L to",
            "got roasted by",
            "got smacked by",
            "got bamboozled by",
    };

    /**
     * An array containing all bed destroys messages
     */
    private String[] bedDestroysMessages = new String[]{
            "was incinerated by",
            "was iced by",
            "had to raise the white flag to",
            "was dismantled by",
            "was deep fried by",
            "was traded in for milk and cookies by",
            "was ripped apart by",
            "be shot with cannon by",
            "was spooked by",
            "got memed by"
    };

    public String[] getKillMessages() {
        return killMessages;
    }

    public String[] getBedDestroysMessages() {
        return bedDestroysMessages;
    }
}
