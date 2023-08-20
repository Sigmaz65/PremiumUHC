package fr.infarium.premiumuhc.manager;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.enums.GameState;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    public static String HostOwner = null;
    public static List<Player> players = new ArrayList<>();
    public static int getTotalInGamePlayer() {
        return getPlayers().size();
    }

    public static int getTotalMaxPlayer() {
        return ConfigManager.max_player;
    }

    public static String getHostOwner() {
        return HostOwner;
    }
    public static void setState(GameState state){
        Main.state = state;
    }
    public static boolean isState(GameState state){
        return Main.state == state;
    }
    public static GameState State(){
        return Main.state;
    }

    public static List<Player> getPlayers(){
        return players;
    }
    public static void setHostOwner(String player){
        HostOwner = player;
    }
}
