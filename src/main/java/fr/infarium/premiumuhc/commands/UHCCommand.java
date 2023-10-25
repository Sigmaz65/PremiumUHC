package fr.infarium.premiumuhc.commands;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.manager.*;
import fr.infarium.premiumuhc.menu.ConfigHost;
import fr.infarium.premiumuhc.menu.ConfigHostAuto;
import fr.infarium.premiumuhc.mysql.HostData;
import fr.infarium.premiumuhc.mysql.ScenariosData;
import fr.infarium.premiumuhc.tasks.AutoStart;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static fr.infarium.premiumuhc.commands.SaveInvCommands.*;

public class UHCCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (!(s instanceof Player)) return false;

        Player player = (Player) s;
        if(args.length == 0) {
            s.sendMessage(help());
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "start":
                if (GameManager.getHostOwner().equals(player.getName())){
                    if (GameManager.getPlayers().size() >= 2) {
                        player.sendMessage(TextManager.formatString(ConfigManager.info_host_start_message));
                        GameManager.setState(GameState.STARTING);
                        for (Player pls : GameManager.getPlayers()) {
                            ScoreManager.setScoreboard(pls, GameState.STARTING);
                        }
                        AutoStart start = new AutoStart();
                        start.runTaskTimer(Main.getInstance(), 0, 20);
                    }
                    else{
                        player.sendMessage(TextManager.formatString(ConfigManager.not_enough_players_message));
                    }
                }
                else {
                    player.sendMessage(TextManager.formatString(ConfigManager.no_host_permission_message));
                }
                break;
            case "forcestart":
                if (player.isOp()){
                    player.sendMessage(TextManager.formatString(ConfigManager.info_host_start_message));
                    GameManager.setState(GameState.STARTING);
                    for (Player pls : GameManager.getPlayers()) {
                        ScoreManager.setScoreboard(pls, GameState.STARTING);
                    }
                    AutoStart start = new AutoStart();
                    start.runTaskTimer(Main.getInstance(), 0, 20);
                }
                else{
                    player.sendMessage(TextManager.formatString(ConfigManager.no_host_permission_message));
                }
                break;
            case "stats":
                player.sendMessage(TextManager.formatString(ConfigManager.stats
                        .replace("%total-party%", String.valueOf(PlayerManager.getTotalParty(player)))
                        .replace("%total-kills%", String.valueOf(PlayerManager.getTotalKills(player)))
                        .replace("%total-victory%", String.valueOf(PlayerManager.getTotalVictory(player)))));
                break;
            case "leave":
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);

                try {
                    out.writeUTF("Connect");
                    out.writeUTF(ConfigManager.server_lobby);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                player.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
                break;
            case "kick":
                if (GameManager.getHostOwner().equals(player.getName())){
                    if (args.length == 1){
                        player.sendMessage(TextManager.formatString(ConfigManager.no_pseudo_message));
                    }
                    else {
                        Boolean playerFound = false;
                        for (Player pls : GameManager.getPlayers()) {
                            if (pls.getName().equals(args[1])) {
                                ByteArrayOutputStream b2 = new ByteArrayOutputStream();
                                DataOutputStream out2 = new DataOutputStream(b2);

                                try {
                                    out2.writeUTF("Connect");
                                    out2.writeUTF(ConfigManager.server_lobby);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                pls.sendPluginMessage(Main.getInstance(), "BungeeCord", b2.toByteArray());


                                pls.sendMessage(TextManager.formatString(ConfigManager.be_kick_message));
                                player.sendMessage(TextManager.formatString(ConfigManager.successfully_kick_message.replace("%player%", pls.getName())));

                                playerFound = true;
                                break;
                            }

                        }
                        if (!playerFound) {
                            player.sendMessage(TextManager.formatString(ConfigManager.player_not_online));
                        }

                    }
                }
                else{
                    player.sendMessage(TextManager.formatString(ConfigManager.no_host_permission_message));
                }
                break;
            case "options":
                if (GameManager.isState(GameState.WAITING) || GameManager.isState(GameState.STARTING)) {
                    if (GameManager.getHostOwner().equals(player.getName())) {
                        if (playersInGm.isEmpty()) {
                            ConfigHost.open(player);
                        } else {
                            player.sendMessage(TextManager.formatString("%prefix% &cErreur: Vous ne pouvez pas faire cela car vous êtes en mode édition"));
                        }
                    } else {
                        player.sendMessage(TextManager.formatString(ConfigManager.no_host_permission_message));
                    }
                }else{
                    player.sendMessage(TextManager.formatString("%prefix% &cVous ne pouvez par faire cela durant la partie."));
                }
                break;
            case "info":
            case "infos":
                //string
                String player_host = HostData.getInfoHostString("player_host");
                String border_time = HostData.getInfoHostString("border_time");

                String time_pvp = HostData.getInfoHostString("time_pvp");

                //int
                String display_name = HostData.getInfoHostString("display_name");
                String rule_nether = HostData.getInfoHostString("rule_nether");
                String num_team = HostData.getInfoHostString("num_teams");

                String border_start = HostData.getInfoHostString("border_start");
                String border_end = HostData.getInfoHostString("border_end");
                String border_speed = HostData.getInfoHostString("border_speed");

                String auto_spec = HostData.getInfoHostString("spec_auto");
                String auto_reco = HostData.getInfoHostString("reco_auto");

                String size_teams = VarManager.var_size_teams;

                String mode;
                if (size_teams.equals("1")){
                    mode = "Solo";
                }else {
                    mode = size_teams + "vs" + size_teams;
                }

                player.sendMessage("§e§lInformations sur la partie:");
                player.sendMessage("§e§l✦ §ePseudo de l'hôte: §a" + player_host);
                player.sendMessage("§e§l✦ §eNom: §a" + display_name);
                player.sendMessage("§e§l✦ §eMode: §a" + mode);
                player.sendMessage("§e§l✦ §eNombre d'équipes: §a" + num_team);
                player.sendMessage("§e§l✦ §eTaille Bordure: §a" + border_start + " §7§l»»» §a" + border_end);
                player.sendMessage("§e§l✦ §eVitesse Bordure: §a" + border_speed + " blocs/s");
                player.sendMessage("§e§l✦ §eNether: " + rule_nether);
                player.sendMessage("§e§l✦ §eSpectateurs: " + auto_spec);
                player.sendMessage("§e§l✦ §eReconnexion: " + auto_reco);
                player.sendMessage("§e§lTemps:");
                player.sendMessage("§e§l ✦ §ePvP: §a" + time_pvp);
                player.sendMessage("§e§l ✦ §eBordure: §a" + border_time);
                player.sendMessage("§7§oPour voir la liste des scénarios: /uhc scenarios");
                player.sendMessage("§7§oPour voir l'inventaire de départ: /uhc inventaire");

                break;
            case "scenarios":
            case "sc":
                player.sendMessage("§e§lListe des scénarios activés:");
                int numScena = ScenariosData.CountScenarios();

                for (int i = 0; i < numScena; i++) {
                    if (ScenariosData.getScString(String.valueOf(i)).equals("1")){
                        player.sendMessage("§e§l ✦ §a" + ScenariosManager.GetNameScenario(i));
                    }
                }
                break;
            case "inventaire":
            case "inv":
                if (GameManager.isState(GameState.WAITING) || GameManager.isState(GameState.STARTING)){
                    playersInGm.put(player, player.getGameMode());
                    player.setGameMode(GameMode.CREATIVE);
                    InvManager.clearInv(player);
                    if (savedInventories.containsKey(player)) {
                        player.getInventory().setContents(savedInventories.get(player));
                        player.getInventory().setArmorContents(savedArmors.get(player));
                        player.updateInventory();

                    }
                    player.sendMessage("§c");
                    player.sendMessage("§a§l  Vous entrez en mode inspection de l'inventaire de départ");
                    player.sendMessage("§7§l          » §b/finish §7- §ePour quitter le mode");
                    player.sendMessage("§c");
                }else{
                    player.sendMessage(TextManager.formatString("%prefix% &cVous ne pouvez par faire cela durant la partie."));
                }
                break;
            case "heal":
                if (VarManager.var_player_host.equals(player.getName())) {
                    if (!GameManager.isState(GameState.WAITING) && !GameManager.isState(GameState.STARTING)) {
                        for (Player pls : Bukkit.getOnlinePlayers()) {
                            pls.setHealth(pls.getMaxHealth());
                        }
                        Bukkit.broadcastMessage(TextManager.formatString("%prefix% &dL'hôte de cette partie a soigné tous les joueurs."));
                    } else {
                        player.sendMessage(TextManager.formatString("%prefix% &cVous ne pouvez pas faire cela, la partie n'est pas démarrée"));
                    }
                }else{
                    player.sendMessage(TextManager.formatString(ConfigManager.no_host_permission_message));
                }
                break;
            case "feed":
                if (VarManager.var_player_host.equals(player.getName())) {
                    if (!GameManager.isState(GameState.WAITING) && !GameManager.isState(GameState.STARTING)) {
                        for (Player pls : Bukkit.getOnlinePlayers()) {
                            pls.setFoodLevel(Integer.MAX_VALUE);
                        }
                        Bukkit.broadcastMessage(TextManager.formatString("%prefix% &6L'hôte de cette partie a rassasié tous les joueurs"));
                    } else {
                        player.sendMessage(TextManager.formatString("%prefix% &cVous ne pouvez pas faire cela, la partie n'est pas démarrée"));
                    }
                }else{
                    player.sendMessage(TextManager.formatString(ConfigManager.no_host_permission_message));
                }
                break;
            case "bc":
            case "broadcast":
                if (VarManager.var_player_host.equals(player.getName())) {
                    if (args.length == 1) {
                        player.sendMessage(TextManager.formatString("%prefix% &cUsage : /broadcast <message>"));
                    }else{
                        StringBuilder bc = new StringBuilder();

                        for (String part : args){
                            bc.append(part + " ");
                        }

                        Bukkit.broadcastMessage(TextManager.formatString("&6&l[BROADCAST] &7" + player.getName() + "&6:" + bc.toString().replace("bc", "")));
                    }
                }else{
                    player.sendMessage(TextManager.formatString(ConfigManager.no_host_permission_message));
                }
                break;
            default:
                s.sendMessage(help());
                break;
        }
        return false;
    }

    public static String help() {
        return TextManager.formatString(Main.getInstance().getConfig().getString("messages.help"));
    }
}
