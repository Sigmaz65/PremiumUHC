package fr.infarium.premiumuhc.tasks;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.manager.*;
import fr.infarium.premiumuhc.mysql.DatabaseManager;
import fr.infarium.premiumuhc.mysql.HostData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static fr.infarium.premiumuhc.tasks.StartTimeInGame.minute;
import static fr.infarium.premiumuhc.tasks.StartTimeInGame.second;

public class StartMining extends BukkitRunnable {
    public static int second;
    public static int minute;
    Integer timer = 0;
    @Override
    public void run() {

        if (timer == 0){
            String[] border_time = HostData.getInfoHostString("border_time").split(":");


            second = Integer.valueOf(border_time[1]);
            minute = Integer.valueOf(border_time[0]);

            timer++;
        }

        if (minute == 0 && second == 30){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLa bordure s'active dans &c30 &esecondes !"));
        }        if (minute == 0 && second == 20){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLa bordure s'active dans &c20 &esecondes !"));
        }        if (minute == 0 && second == 10){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLa bordure s'active dans &c10 &esecondes !"));
        }        if (minute == 0 && second == 5){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLa bordure s'active dans &c5 &esecondes !"));
        }        if (minute == 0 && second == 4){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLa bordure s'active dans &c4 &esecondes !"));
        }        if (minute == 0 && second == 3){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLa bordure s'active dans &c3 &esecondes !"));
        }        if (minute == 0 && second == 2){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLa bordure s'active dans &c2 &esecondes !"));
        }        if (minute == 0 && second == 1){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLa bordure s'active dans &c1 &eseconde !"));
        }

        if (minute == 0 && second == 0){
            GameManager.setState(GameState.PLAYING);
            int num = 0;
            for (Player pls : GameManager.getPlayers()){
                ScoreManager.setScoreboard(pls, GameState.PLAYING);

                num++;
            }
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &cLa bordure commence à avancer, soyez attentif à vos coordonnés..."));
            StartWB startWB = new StartWB();
            startWB.runTaskTimer(Main.getInstance(), 0, 20);
            cancel();
        }
        if (second == 0){
            second = 60;
            minute--;
        }
        second--;
    }
}
