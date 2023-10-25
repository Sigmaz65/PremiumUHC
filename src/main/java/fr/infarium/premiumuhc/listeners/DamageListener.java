package fr.infarium.premiumuhc.listeners;

import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.manager.GameManager;
import fr.infarium.premiumuhc.manager.InvincibleManage;
import fr.infarium.premiumuhc.manager.PvpManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;

public class DamageListener implements Listener {
    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e){
        if (GameManager.isState(GameState.WAITING) || GameManager.isState(GameState.STARTING) || GameManager.isState(GameState.END)){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (GameManager.isState(GameState.WAITING) || GameManager.isState(GameState.STARTING) || GameManager.isState(GameState.END)){
            e.setCancelled(true);
        }
        if (InvincibleManage.isInvincibleState()){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onFood(FoodLevelChangeEvent e){
        if (GameManager.isState(GameState.WAITING) || GameManager.isState(GameState.STARTING) || GameManager.isState(GameState.END)){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void onMobBlockBreak(EntityChangeBlockEvent event) {
        if (GameManager.isState(GameState.WAITING) || GameManager.isState(GameState.STARTING) || GameManager.isState(GameState.END)) {
            if (event.getEntityType().isAlive()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent event) {
        if (GameManager.isState(GameState.WAITING) || GameManager.isState(GameState.STARTING) || GameManager.isState(GameState.END)) {
            if (event.getEntityType().toString().equals("CREEPER")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPvp(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player){
            if (!PvpManager.hasPvp()){
                e.setCancelled(true);
            }
        }
    }
}
