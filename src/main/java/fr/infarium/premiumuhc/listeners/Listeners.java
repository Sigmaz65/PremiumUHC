package fr.infarium.premiumuhc.listeners;

import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.manager.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        e.setJoinMessage("");


        ScoreManager.setScoreboard(p, ScoreManager.getPlayerScoreBoard(p));

        if (!GameManager.isState(GameState.WAITING)){
            p.setGameMode(GameMode.SPECTATOR);
            p.sendMessage(TextManager.formatString((ConfigManager.be_spectator_message)));
            return;
        }
        Bukkit.broadcastMessage(TextManager.formatString(ConfigManager.join_message.replace("%player%", p.getName())));
        if (p.hasPermission("premiumuhc.host")){
            if (GameManager.HostOwner == null){
                p.sendMessage(TextManager.formatString(ConfigManager.be_host_message));
                GameManager.setHostOwner(p.getName());
            }
        }
        if (!GameManager.getPlayers().contains(p)) GameManager.getPlayers().add(p);
        p.setGameMode(GameMode.ADVENTURE);
        InvManager.clearInv(p);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if (GameManager.getPlayers().contains(p)) {

            GameManager.getPlayers().remove(p);

            e.setQuitMessage("");
            Bukkit.broadcastMessage(TextManager.formatString(ConfigManager.quit_message.replace("%player%", p.getName())));
            if (GameManager.getHostOwner().equals(p.getName())){
                GameManager.setHostOwner(null);
            }
        }
    }
}
