package fr.infarium.premiumuhc.tasks;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.manager.ConfigManager;
import fr.infarium.premiumuhc.manager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class StartTimeInGame extends BukkitRunnable {
    public static Integer second = 0;
    public static Integer minute = 0;
    @Override
    public void run() {
        if (GameManager.getPlayers().size() <= 1){
            Bukkit.broadcastMessage("§c§lPLUS DE JOUEURS");
            StartEndTime startTimeEndTime = new StartEndTime();
            startTimeEndTime.runTaskTimer(Main.getInstance(), 0, 20);
            cancel();
        }
        if(second == 60){
            second = 0;
            minute++;
        }
        second++;
    }
}
