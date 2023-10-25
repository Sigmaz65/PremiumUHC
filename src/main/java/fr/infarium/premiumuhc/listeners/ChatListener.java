package fr.infarium.premiumuhc.listeners;

import fr.infarium.premiumuhc.manager.TextManager;
import fr.infarium.premiumuhc.manager.VarManager;
import fr.infarium.premiumuhc.mysql.HostData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        e.setCancelled(true);
        if (VarManager.var_player_host.equals(p.getName())){
            if (VarManager.setNameGame){
                String display_name = e.getMessage();
                if (!display_name.equalsIgnoreCase("annuler")){
                    if (!(display_name.length() < 3)){
                        if (!(display_name.length() > 20)){
                            p.sendMessage(TextManager.formatString("%prefix% &aLe nom a bien été modifié !"));
                            HostData.setInfoHostString("display_name", display_name);
                            VarManager.setNameGame = false;
                        }else{
                            p.sendMessage(TextManager.formatString("%prefix% &cErreur: Le nom doit avoir une longueur comprise entre 3 et 20"));
                        }
                    } else{
                        p.sendMessage(TextManager.formatString("%prefix% &cErreur: Le nom doit avoir une longueur comprise entre 3 et 20"));
                    }
                }
                else{
                    p.sendMessage(TextManager.formatString("%prefix% &aVous êtes sortie du mode édition !"));
                    VarManager.setNameGame = false;
                }
            } else {
                Bukkit.broadcastMessage("§c§l[HOTE] §7%player%§f: %chat%".replace("%player%", p.getName()).replace("%chat%", e.getMessage()));
            }
        }else{
            Bukkit.broadcastMessage("§7%player%§f: %chat%".replace("%player%", p.getName()).replace("%chat%", e.getMessage()));
        }
    }
}
