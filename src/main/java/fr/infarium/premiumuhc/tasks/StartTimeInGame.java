package fr.infarium.premiumuhc.tasks;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.manager.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartTimeInGame extends BukkitRunnable {
    public static Integer second = 0;
    public static Integer minute = 0;
    @Override
    public void run() {
        if (GameManager.getPlayers().size() <= 1){
            StartEndTime startTimeEndTime = new StartEndTime();
            startTimeEndTime.runTaskTimer(Main.getInstance(), 0, 20);
            for (Player pls : Bukkit.getOnlinePlayers()){
                GameManager.setState(GameState.END);
                ScoreManager.setScoreboard(pls, GameState.END);
            }
            cancel();
        }
        if (second == 10 && minute == 0){
            InvincibleManage.setInvincibleState(false);
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &cFin de l'invincibilité."));
        }
        if(second == 60){
            second = 0;
            minute++;
        }
        second++;
    }
}
