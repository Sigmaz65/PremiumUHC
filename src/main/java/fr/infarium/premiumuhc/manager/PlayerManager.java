package fr.infarium.premiumuhc.manager;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {
    private static Map<String, Integer> playerKills = new HashMap<>();

    public static Map<String, Integer> getMapPlayerKills() {
        return playerKills;
    }

    public static int getPlayerKills(String playerName) {
        return playerKills.getOrDefault(playerName, 0);
    }

    public static void incrementPlayerKills(String playerName) {
        int currentKills = playerKills.getOrDefault(playerName, 0);
        playerKills.put(playerName, currentKills + 1);
    }

    public static int getTotalParty(Player p) {
        return 0;
    }

    public static int getTotalKills(Player p) {
        return 0;
    }

    public static int getTotalVictory(Player p) {
        return 0;
    }
}
