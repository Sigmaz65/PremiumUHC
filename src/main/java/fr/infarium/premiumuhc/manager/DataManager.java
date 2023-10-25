package fr.infarium.premiumuhc.manager;

import fr.infarium.premiumuhc.mysql.HostData;
import fr.infarium.premiumuhc.mysql.ScenariosData;
import org.bukkit.World;

import static fr.infarium.premiumuhc.commands.SaveInvCommands.*;
import static org.bukkit.Bukkit.getServer;

public class DataManager {
    HostData hostData = new HostData(ConfigManager.server_host);
    public static Integer getMaxPlayer(){


        Integer num_teams = Integer.valueOf(HostData.getInfoHostString("num_teams"));
        Integer size_teams = Integer.valueOf(HostData.getInfoHostString("size_teams"));

        Integer max_player = num_teams * size_teams;

        return max_player;
    }

    public static void resetConfigHost(){
        World world = getServer().getWorld("world");

        HostData.setInfoHostString("border_time", "35:00");
        HostData.setInfoHostString("border_start", "500");
        HostData.setInfoHostString("border_end", "50");
        HostData.setInfoHostString("border_speed", "1");
        HostData.setInfoHostString("time_pvp", "15:00");
        HostData.setInfoHostString("player_host", "§cEn attente...");

        //normal
        HostData.setInfoHostString("rule_time", "§aNormal");
        world.setGameRuleValue("doDaylightCycle", "true");
        world.setTime(1000);

        HostData.setInfoHostString("rule_gm", "§aSurvival");
        HostData.setInfoHostString("rule_nether", "§cDésactivé");
        HostData.setInfoHostString("drop_apple", "20");
        HostData.setInfoHostString("drop_silex", "20");

        HostData.setInfoHostString("display_name", "§eUHC Classique");
        HostData.setInfoHostString("reco_auto", "§aAutorisée");
        HostData.setInfoHostString("spec_auto", "§aAutorisés");

        HostData.setInfoHostString("num_teams", "8");
        HostData.setInfoHostString("size_teams", "1");

        //sc
        ScenariosData.setInfoScString("activated", "0", "0");
        ScenariosData.setInfoScString("activated", "0", "1");
        ScenariosData.setInfoScString("activated", "0", "2");
        ScenariosData.setInfoScString("activated", "0", "3");
        ScenariosData.setInfoScString("activated", "0", "4");
        ScenariosData.setInfoScString("activated", "0", "5");
        ScenariosData.setInfoScString("activated", "0", "6");
        ScenariosData.setInfoScString("activated", "0", "7");

        if (!savedInventories.isEmpty()) savedInventories.clear();
        if (!savedArmors.isEmpty()) savedArmors.clear();
        if (!playersInGm.isEmpty()) playersInGm.clear();
    }
}
