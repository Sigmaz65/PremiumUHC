package fr.infarium.premiumuhc.manager;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.mysql.PlayerData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    final private static Map<String, Integer> playerKills = new HashMap<>();

    public static int getPlayerKills(String playerName) {
        return playerKills.getOrDefault(playerName, 0);
    }

    public static void incrementPlayerKills(String playerName) {
        int currentKills = playerKills.getOrDefault(playerName, 0);
        playerKills.put(playerName, currentKills + 1);
    }

    public static int getTotalParty(Player p) {
        return Integer.valueOf(PlayerData.getInfoPlayerString("total_played_party", p));
    }

    public static int getTotalKills(Player p) {
        return Integer.valueOf(PlayerData.getInfoPlayerString("total_kills", p));
    }

    public static int getTotalVictory(Player p) {
        return Integer.valueOf(PlayerData.getInfoPlayerString("total_victory", p));
    }
    public static void addTotalParty(Player p, Integer count) {
        PlayerData.setInfoPlayerString("total_played_party", String.valueOf(getTotalParty(p) + count), p);
    }

    public static void addTotalKills(Player p, Integer count) {
        PlayerData.setInfoPlayerString("total_kills", String.valueOf(getTotalKills(p) + count), p);
    }

    public static void addTotalVictory(Player p, Integer count) {
        PlayerData.setInfoPlayerString("total_victory", String.valueOf(getTotalVictory(p) + count), p);
    }
}
