package fr.infarium.premiumuhc.manager;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.mysql.HostData;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    HostData hostData = new HostData(ConfigManager.server_host);
    public static String HostOwner = null;
    public static Player HostOwnerPlayer = null;
    public static List<Player> players = new ArrayList<>();
    public static int getTotalInGamePlayer() {
        return getPlayers().size();
    }

    public static int getTotalMaxPlayer() {
        return DataManager.getMaxPlayer();
    }

    public static String getHostOwner() {
        return HostData.getInfoHostString("player_host");
    }
    public static Player getHostOwnerPlayer() {
        return HostOwnerPlayer;
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
    public static void setHostOwner(Player player){
        if (player == null) {
            HostData.setInfoHostString("player_host", "§cEn attente...");
        }else {
            HostOwnerPlayer = player;
            HostData.setInfoHostString("player_host", player.getName());
        }
    }
}
