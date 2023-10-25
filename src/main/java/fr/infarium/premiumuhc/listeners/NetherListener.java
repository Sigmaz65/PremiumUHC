package fr.infarium.premiumuhc.listeners;

import fr.infarium.premiumuhc.manager.TextManager;
import fr.infarium.premiumuhc.mysql.HostData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCreatePortalEvent;

public class NetherListener implements Listener {
    @EventHandler
    public void onNether(EntityCreatePortalEvent e){
        if (HostData.getInfoHostString("rule_nether").equals("§cDésactivé")){
            if (e.getEntity() instanceof Player){
                Player player = (Player) e.getEntity();
                player.sendMessage(TextManager.formatString("%prefix% Le nether est désactivé."));
            }
            e.setCancelled(true);

        }
    }
}
