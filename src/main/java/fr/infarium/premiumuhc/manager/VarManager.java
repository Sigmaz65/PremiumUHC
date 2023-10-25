package fr.infarium.premiumuhc.manager;

import fr.infarium.premiumuhc.mysql.HostData;
import fr.infarium.premiumuhc.mysql.PlayerData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class VarManager {
    public static HashMap<Player, Integer> map_total_played_party = new HashMap<>();
    public static HashMap<Player, Integer> map_total_kills = new HashMap<>();
    public static HashMap<Player, Integer> map_total_victory = new HashMap<>();
    public static Integer var_total_played_party;
    public static Integer var_total_kills;
    public static Integer var_total_victory;
    public static Integer var_kills;
    public static Integer var_player_ingame;
    public static Integer var_playermax_ingame;
    public static String var_player_host;
    public static String var_size_teams;
    public static Boolean setNameGame = false;
    public static void initializeVar(Player player){
        var_total_played_party = PlayerManager.getTotalParty(player);
        var_total_kills = PlayerManager.getTotalKills(player);
        var_total_victory = PlayerManager.getTotalVictory(player);


        map_total_played_party.put(player, var_total_played_party);
        map_total_kills.put(player, var_total_kills);
        map_total_victory.put(player, var_total_victory);

    }

    public static void unInitializeVar(Player player){
        map_total_played_party.remove(player);
        map_total_kills.remove(player);
        map_total_victory.remove(player);
    }

    public static void initializeServerVar(){
        var_player_ingame = GameManager.getPlayers().size();
        var_playermax_ingame = GameManager.getTotalMaxPlayer();
        var_player_host = GameManager.getHostOwner();
        var_size_teams = HostData.getInfoHostString("size_teams");
    }
}
