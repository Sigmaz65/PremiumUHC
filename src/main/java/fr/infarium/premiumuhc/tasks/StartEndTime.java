package fr.infarium.premiumuhc.tasks;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.manager.*;
import fr.infarium.premiumuhc.mysql.HostData;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static org.bukkit.Bukkit.getLogger;

public class StartEndTime extends BukkitRunnable {
    Integer timer = 0;
    @Override
    public void run() {
        if (timer == 0){
            if (!GameManager.getPlayers().isEmpty() && GameManager.getPlayers().get(0) != null) {
                Player winner = GameManager.getPlayers().get(0);
                PlayerManager.addTotalVictory(winner, 1);
                GameManager.getPlayers().get(0).sendTitle(TextManager.formatString(ConfigManager.victory_title), null);
                Bukkit.broadcastMessage(TextManager.formatString(ConfigManager.end_game_message.replace("%winner%", String.valueOf(winner.getName()))));
            }
            else {
                Bukkit.broadcastMessage(TextManager.formatString("%prefix% &fMalheureusement, &6personne &fn'a gagné. &eTéléportation au Lobby..."));
            }
        }
        if (timer == 15){
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);

            try {
                out.writeUTF("Connect");
                out.writeUTF(ConfigManager.server_lobby);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (Player pls : Bukkit.getOnlinePlayers()){
                pls.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
            }


        }

        if (timer == 16){

            Bukkit.shutdown();
            cancel();

        }

        timer++;
    }
}


