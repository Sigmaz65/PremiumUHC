package fr.infarium.premiumuhc.manager;

import fr.infarium.premiumuhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;

public class TeleportManager {
    static ArrayList<Location> spawns = new ArrayList<>();
    public static void loadSpawn(){
        World world = Bukkit.getWorlds().get(0);
        for(String spawnSTR : Main.getInstance().getConfig().getStringList("spawns-coords")) {
            String[] values = spawnSTR.split(",");
            double x = Double.parseDouble(values[0]);
            double y = Double.parseDouble(values[1]);
            double z = Double.parseDouble(values[2]);
            float yaw = Float.parseFloat(values[3]);
            float pitch = Float.parseFloat(values[4]);

            Location loc = new Location(world, x, y, z, yaw, pitch);

            spawns.add(loc);
        }
    }

    public static ArrayList<Location> getSpawns(){
        return spawns;
    }
}
