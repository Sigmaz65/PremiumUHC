package fr.infarium.premiumuhc.tasks;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.manager.ConfigManager;
import fr.infarium.premiumuhc.manager.DataManager;
import fr.infarium.premiumuhc.manager.TextManager;
import fr.infarium.premiumuhc.manager.VarManager;
import fr.infarium.premiumuhc.mysql.HostData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class StartLeaveHost extends BukkitRunnable {
    Integer timer = 120;
    @Override
    public void run() {
        for (Player pls : Bukkit.getOnlinePlayers()){
            if (pls.getName().equals(VarManager.var_player_host)){
                Bukkit.broadcastMessage(TextManager.formatString("%prefix% &fL'hôte de la partie est revenu."));

                cancel();
            }
        }

        if (timer <= 0){
            cancel();
            DataManager.resetConfigHost();
            HostData.setInfoHostString("server_status", "§dEn attente de joueurs...");

            Bukkit.broadcastMessage(TextManager.formatString("%prefix% &fLes deux minutes sont passés, la partie va redémarrer."));

            for(Player pls : Bukkit.getOnlinePlayers()){
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);

                try {
                    out.writeUTF("Connect");
                    out.writeUTF(ConfigManager.server_lobby);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                pls.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
            }
        }
        timer--;
    }
}
