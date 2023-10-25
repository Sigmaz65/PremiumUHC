package fr.infarium.premiumuhc.tasks;

import fr.infarium.premiumuhc.mysql.HostData;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.scheduler.BukkitRunnable;

public class StartWB extends BukkitRunnable {
    Float border_speed = (float) (Integer.parseInt(HostData.getInfoHostString("border_speed")) * 2);
    Integer border_end = Integer.parseInt(HostData.getInfoHostString("border_end")) * 2;
    @Override
    public void run() {
        World world = Bukkit.getWorlds().get(0);
        WorldBorder wb = world.getWorldBorder();

        wb.setSize(wb.getSize() - border_speed);
        if (wb.getSize() <= border_end){
            cancel();
        }
    }
}
