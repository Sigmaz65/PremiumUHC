package fr.infarium.premiumuhc.tasks;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.manager.*;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AutoStart extends BukkitRunnable {
    public static Integer timer = 5;
    @Override
    public void run() {
        if (timer == 0){
            for (Player pls : GameManager.getPlayers()){
                pls.sendMessage(TextManager.formatString(ConfigManager.end_timer_message));
                pls.sendTitle(TextManager.formatString(ConfigManager.final_second_timer), null);
            }

            int num = 0;
            GameManager.setState(GameState.MINING);

            PotionEffect effetResistance = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, ((ConfigManager.minage_minute * 60) + ConfigManager.minage_second + 10) * 20, 255, true, false);

            for (Player pls : GameManager.getPlayers()){
                pls.addPotionEffect(effetResistance);

                ScoreManager.setScoreboard(pls, GameState.MINING);

                pls.teleport(TeleportManager.getSpawns().get(num));
                num++;
            }
            StartMining mining = new StartMining();
            mining.runTaskTimer(Main.getInstance(), 0, 20);

            StartTimeInGame startTimeInGame = new StartTimeInGame();
            startTimeInGame.runTaskTimer(Main.getInstance(), 0, 20);

            cancel();
        }
        if (timer == 1){
            for (Player pls : GameManager.getPlayers()){
                pls.sendTitle(TextManager.formatString(ConfigManager.one_second_timer), null);
            }
        }
        if (timer == 2){
            for (Player pls : GameManager.getPlayers()){
                pls.sendTitle(TextManager.formatString(ConfigManager.two_second_timer), null);
            }
        }
        if (timer == 3){
            for (Player pls : GameManager.getPlayers()){
                pls.sendTitle(TextManager.formatString(ConfigManager.three_second_timer), null);
            }
        }
        if (timer == 4){
            for (Player pls : GameManager.getPlayers()){
                pls.sendTitle(TextManager.formatString(ConfigManager.four_second_timer), null);
            }
        }
        if (timer == 5){
            for (Player pls : GameManager.getPlayers()){
                pls.sendTitle(TextManager.formatString(ConfigManager.five_second_timer), null);
            }
        }

        for (Player pls : GameManager.getPlayers()){
            if (timer != 0) {
                pls.sendMessage(TextManager.formatString(ConfigManager.timer_message
                        .replace("%time%", String.valueOf(timer))
                        .replace("%second%", (timer == 1) ? String.valueOf(ConfigManager.one_second) : String.valueOf(ConfigManager.several_seconds))
                ));
            }
        }

        timer--;
    }

}
