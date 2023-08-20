package fr.infarium.premiumuhc.tasks;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.manager.ConfigManager;
import fr.infarium.premiumuhc.manager.GameManager;
import fr.infarium.premiumuhc.manager.ScoreManager;
import fr.infarium.premiumuhc.manager.TeleportManager;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartMining extends BukkitRunnable {
    public static Integer second = ConfigManager.minage_second;
    public static Integer minute = ConfigManager.minage_minute;
    @Override
    public void run() {
        if (minute == 0 && second == 0){
            GameManager.setState(GameState.PLAYING);
            int num = 0;
            for (Player pls : GameManager.getPlayers()){
                ScoreManager.setScoreboard(pls, GameState.PLAYING);
                pls.teleport(TeleportManager.getSpawns().get(num));
                num++;
            }
            StartWB startWB = new StartWB();
            startWB.runTaskTimer(Main.getInstance(), 0, 20);
            cancel();
        }
        if (second == 0){
            second = 59;
            minute--;
        }
        second--;
    }
}
