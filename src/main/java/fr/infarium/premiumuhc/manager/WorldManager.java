package fr.infarium.premiumuhc.manager;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.util.Random;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getServer;

public class WorldManager {

    public static void setWorldGameRule(World world) {
        world.setGameRuleValue("naturalRegeneration", "false");
        world.setGameRuleValue("doDaylightCycle", "true");
        world.setGameRuleValue("doWeatherCycle", "false");
        world.setGameRuleValue("doFireTick", "false");
        world.setGameRuleValue("keepInventory", "false");
        world.setGameRuleValue("mobGriefing", "true");
        world.setGameRuleValue("randomTickSpeed", "3");
        world.setGameRuleValue("showDeathMessages", "false");
    }

    public static void deleteWorld(String worldName) {
        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            Bukkit.unloadWorld(world, true);

            File worldFolder = world.getWorldFolder();
            if (deleteFolder(worldFolder)) {
                getLogger().info("World deleted successfully.");
            } else {
                getLogger().warning("Failed to delete world folder.");
            }
        } else {
            getLogger().warning("World not found.");
        }
    }

    public static boolean deleteFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file);
                    } else {
                        file.delete();
                    }
                }
            }
        }
        return folder.delete();
    }

    // Méthode pour créer un monde spécial avec un biome Dark Forest au centre

}
