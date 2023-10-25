package fr.infarium.premiumuhc.listeners;

import fr.infarium.premiumuhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.*;

public class BungeeListener implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
        try {
            String subChannel = in.readUTF();
            if (subChannel.equals("ConnectOther")) {
                String playerName = in.readUTF(); // Lire le nom du joueur
                String serverName = in.readUTF(); // Lire le nom du serveur de destination

                // Vérifiez si le joueur est en ligne sur ce serveur Bukkit
                Player targetPlayer = Bukkit.getPlayerExact(playerName);
                if (targetPlayer != null) {
                    // Utilisez le code approprié pour envoyer le joueur vers le serveur cible via BungeeCord
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(byteArray);
                    out.writeUTF("Connect");
                    out.writeUTF(serverName);
                    targetPlayer.sendPluginMessage(Main.getInstance(), "BungeeCord", byteArray.toByteArray());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

