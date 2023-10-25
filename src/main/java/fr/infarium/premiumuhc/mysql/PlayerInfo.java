package fr.infarium.premiumuhc.mysql;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerInfo {
    private static Map<Player, PlayerInfo> playerInfo = new HashMap<Player, PlayerInfo>();
    private Player player;
    private PlayerData playerData;

    public PlayerInfo(Player player){
        this.player = player;
        this.playerData = new PlayerData(player.getUniqueId());
        this.playerInfo.put(player, this);

    }

    public static PlayerInfo getInfosPlayer(Player player){
        return playerInfo.get(player);
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public Float getUhcXP(){
        return playerData.getUhcXP();
    }

    public void addUhcXp(float amount){
        playerData.addUhcXp(amount);
    }

    public void removeUhcXP(float amount){
        playerData.removeUhcXP(amount);
    }
}
