package fr.infarium.premiumuhc.listeners;

import fr.infarium.premiumuhc.manager.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player killer = e.getEntity().getKiller();
        if (killer != null) {
            String killerName = killer.getName();
            PlayerManager.incrementPlayerKills(killerName);
        }
    }
}
