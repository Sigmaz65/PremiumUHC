package fr.infarium.premiumuhc.manager;

import fr.infarium.premiumuhc.mysql.HostData;
import fr.infarium.premiumuhc.mysql.PlayerData;
import fr.infarium.premiumuhc.mysql.ScenariosData;
import javafx.scene.effect.Effect;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static org.bukkit.Bukkit.getServer;

public class ScenariosManager {
    public static void StartGameScenarios(Player player){
        if (ScenariosData.getInfoScInt(1) == 1){
            PotionEffect night_vision = new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE,255, true);
            player.addPotionEffect(night_vision);
        }
        if (HostData.getInfoHostString("rule_time").equals("§aNormal")){
            World world = Bukkit.getWorld("world");

            world.setTime(1000);
        }
    }

    public static String GetNameScenario(Integer id_scenario){
        if (id_scenario == 0){
            return "Timber";
        }if (id_scenario == 1){
            return "CatEyes";
        }if (id_scenario == 2){
            return "CutClean";
        }if (id_scenario == 3){
            return "BloodDiamond";
        }if (id_scenario == 4){
            return "Nobow";
        }if (id_scenario == 5){
            return "HasteuBoys";
        }if (id_scenario == 6){
            return "noCleanUp";
        }if (id_scenario == 7){
            return "NoFall";
        }
        return "null";
    }
}
