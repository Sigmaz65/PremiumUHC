package fr.infarium.premiumuhc.tasks;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.manager.ScoreManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ScoreBoardUpdate {
    
    public static void start() {
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
            for(Player ps : Bukkit.getOnlinePlayers()) {
                ScoreManager.setScoreboard(ps, ScoreManager.getPlayerScoreBoard(ps));
            }
        },0L,10L);
    }
}
