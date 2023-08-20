package fr.infarium.premiumuhc.tasks;

import fr.infarium.premiumuhc.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.scheduler.BukkitRunnable;

public class StartWB extends BukkitRunnable {
    @Override
    public void run() {
        World world = Bukkit.getWorlds().get(0);
        WorldBorder wb = world.getWorldBorder();

        wb.setSize(wb.getSize() - ConfigManager.worldborder_speed);
        if (wb.getSize() <= ConfigManager.worldborder_lastsize){
            cancel();
        }
    }
}
