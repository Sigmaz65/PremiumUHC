package fr.infarium.premiumuhc.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;

public class AdvancementListener implements Listener {
    @EventHandler
    public void onPlayerAchievement(PlayerAchievementAwardedEvent e){
        e.setCancelled(true);
    }
}
