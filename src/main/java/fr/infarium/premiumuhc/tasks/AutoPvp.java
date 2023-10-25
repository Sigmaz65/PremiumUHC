package fr.infarium.premiumuhc.tasks;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.manager.*;
import fr.infarium.premiumuhc.mysql.HostData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoPvp extends BukkitRunnable {

    Integer timer = 0;
    public static int minute;
    public static int second;

    @Override
    public void run() {
        if (timer == 0){
            String[] values_time_pvp = HostData.getInfoHostString("time_pvp").split(":");

            second = Integer.valueOf(values_time_pvp[1]);
            minute = Integer.valueOf(values_time_pvp[0]);



            timer++;
        }

        if (minute == 0 && second == 30){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLe PvP s'active dans &c30 &esecondes !"));
        }        if (minute == 0 && second == 20){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLe PvP s'active dans &c20 &esecondes !"));
        }        if (minute == 0 && second == 10){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLe PvP s'active dans &c10 &esecondes !"));
        }        if (minute == 0 && second == 5){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLe PvP s'active dans &c5 &esecondes !"));
        }        if (minute == 0 && second == 4){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLe PvP s'active dans &c4 &esecondes !"));
        }        if (minute == 0 && second == 3){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLe PvP s'active dans &c3 &esecondes !"));
        }        if (minute == 0 && second == 2){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLe PvP s'active dans &c2 &esecondes !"));
        }        if (minute == 0 && second == 1){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &eLe PvP s'active dans &c1 &eseconde !"));
        }

        if (minute == 0 && second == 0){
            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &cLe PvP est désormais actif, attention à vous !"));
            PvpManager.setPvp(true);

            cancel();
        }
        if (second == 0){
            second = 60;
            minute--;
        }
        second--;
    }
}
