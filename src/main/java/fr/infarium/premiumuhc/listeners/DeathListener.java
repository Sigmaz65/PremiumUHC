package fr.infarium.premiumuhc.listeners;

import fr.infarium.premiumuhc.manager.ConfigManager;
import fr.infarium.premiumuhc.manager.GameManager;
import fr.infarium.premiumuhc.manager.PlayerManager;
import fr.infarium.premiumuhc.manager.TextManager;
import fr.infarium.premiumuhc.mysql.ScenariosData;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        e.setDeathMessage("");
        Player killer = e.getEntity().getKiller();
        Player killed = e.getEntity().getPlayer();

        if (GameManager.getPlayers().contains(killed)) {
            killed.setGameMode(GameMode.SPECTATOR);
            GameManager.getPlayers().remove(killed);
            if (killer != null) {
                //no clean up
                if (ScenariosData.getInfoScInt(6) == 1){
                    killer.setHealth(killer.getHealth() + 10);
                }


                killed.sendMessage(TextManager.formatString(ConfigManager.death_message_toplayer
                        .replace("%killer%", killer.getName()))
                );

                PlayerManager.addTotalKills(killer, 1);

                String killerName = killer.getName();
                PlayerManager.incrementPlayerKills(killerName);

                for (Player pls : GameManager.getPlayers()){
                    pls.sendMessage(TextManager.formatString(ConfigManager.death_message_byplayer
                            .replace("%player%", killed.getName())
                            .replace("%killer%", killer.getName())
                    ));
                }
            }
            else{
                for (Player pls : GameManager.getPlayers()){
                    pls.sendMessage(TextManager.formatString(ConfigManager.death_message_alone.replace("%player%", killed.getName())));
                }
            }

        }
    }
}
