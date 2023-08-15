package fr.infarium.premiumuhc.manager;

import org.bukkit.World;

public class WorldManager {

    public static void setWorldGameRule(World world) {
        world.setGameRuleValue("naturalRegeneration", "false");
        world.setGameRuleValue("doDaylightCycle", "true");
        world.setGameRuleValue("doFireTick", "false");
        world.setGameRuleValue("keepInventory", "false");
        world.setGameRuleValue("mobGriefing", "true");
        world.setGameRuleValue("randomTickSpeed", "3");
        world.setGameRuleValue("showDeathMessages", "false");
    }
}
