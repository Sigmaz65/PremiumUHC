package fr.infarium.premiumuhc.manager;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.mysql.HostData;
import fr.infarium.premiumuhc.tasks.AutoPvp;
import fr.infarium.premiumuhc.tasks.AutoStart;
import fr.infarium.premiumuhc.tasks.StartMining;
import fr.infarium.premiumuhc.tasks.StartTimeInGame;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScoreManager {
    private static String winnerName;

    public static void setScoreboard(Player player, GameState scoreBoardType){
        String display_name = HostData.getInfoHostString("display_name");

        if(scoreBoardType.equals(GameState.WAITING)) {
            FastBoard fastBoard = Main.getInstance().scoreBoardPlayers.get(player);
            fastBoard.updateTitle(formatScoreBoard(player, display_name));


            List<String> formattedLines = new ArrayList<>();
            for (String str : ConfigManager.scoreboard_waiting){
                formattedLines.add(formatScoreBoard(player, str));
            }

            fastBoard.updateLines(formattedLines);
            return;
        }
        if(scoreBoardType.equals(GameState.STARTING)) {
            FastBoard fastBoard = Main.getInstance().scoreBoardPlayers.get(player);
            fastBoard.updateTitle(formatScoreBoard(player, display_name));


            List<String> formattedLines = new ArrayList<>();
            for (String str : ConfigManager.scoreboard_starting){
                formattedLines.add(formatScoreBoard(player, str));
            }

            fastBoard.updateLines(formattedLines);
            return;
        }
        if(scoreBoardType.equals(GameState.MINING)) {
            FastBoard fastBoard = Main.getInstance().scoreBoardPlayers.get(player);
            fastBoard.updateTitle(formatScoreBoard(player, display_name));


            List<String> formattedLines = new ArrayList<>();
            for (String str : ConfigManager.scoreboard_mining){
                formattedLines.add(formatScoreBoard(player, str));
            }

            fastBoard.updateLines(formattedLines);
            return;
        }
        if(scoreBoardType.equals(GameState.PLAYING)) {
            FastBoard fastBoard = Main.getInstance().scoreBoardPlayers.get(player);
            fastBoard.updateTitle(formatScoreBoard(player, display_name));


            List<String> formattedLines = new ArrayList<>();
            for (String str : ConfigManager.scoreboard_playing){
                formattedLines.add(formatScoreBoard(player, str));
            }

            fastBoard.updateLines(formattedLines);
            return;
        }
        if(scoreBoardType.equals(GameState.END)) {
            FastBoard fastBoard = Main.getInstance().scoreBoardPlayers.get(player);
            fastBoard.updateTitle(formatScoreBoard(player, display_name));


            List<String> formattedLines = new ArrayList<>();
            for (String str : ConfigManager.scoreboard_end){
                formattedLines.add(formatScoreBoard(player, str));
            }

            fastBoard.updateLines(formattedLines);
            return;
        }
    }

    public static GameState getPlayerScoreBoard(Player p) {
        return GameManager.State();
    }

    public static String formatScoreBoard(Player player, String text) {
        List<Player> players = GameManager.getPlayers();
        if (!players.isEmpty()) {
            winnerName = players.get(0).getName();
        } else {
            winnerName = "Personne";
        }
        World world = Bukkit.getWorld("world");
        WorldBorder wb = world.getWorldBorder();
        int timerr = AutoStart.timer + 1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDate = dateFormat.format(new Date());
        return TextManager.formatString(text
                .replace("%date%", currentDate)
                .replace("%player%", player.getName())
                .replace("%server-ip%", ConfigManager.ip)
                .replace("%total-party%", String.valueOf(VarManager.map_total_played_party.get(player)))
                .replace("%total-kills%", String.valueOf(VarManager.map_total_kills.get(player)))
                .replace("%total-victory%", String.valueOf(VarManager.map_total_victory.get(player)))
                .replace("%player-ingame%", String.valueOf(VarManager.var_player_ingame))
                .replace("%player-max%", String.valueOf(VarManager.var_playermax_ingame))
                .replace("%host-owner%", String.valueOf(VarManager.var_player_host))
                .replace("%time%", String.valueOf(timerr))
                .replace("%border-time%", StartMining.minute + ":" + ((StartMining.second <= 9) ? "0" + StartMining.second : StartMining.second))
                .replace("%pvp-time%", (PvpManager.hasPvp()) ? "§aActif" : AutoPvp.minute + ":" + ((AutoPvp.second <= 9) ? "0" + AutoPvp.second : AutoPvp.second))
                .replace("%minute-ingame%", String.valueOf(StartTimeInGame.minute))
                .replace("%second-ingame%", String.valueOf((StartTimeInGame.second <= 9) ? "0" + StartTimeInGame.second : StartTimeInGame.second))
                .replace("%players-left%", String.valueOf(VarManager.var_player_ingame))
                .replace("%border%", String.valueOf(wb.getSize()))
                .replace("%winner%", winnerName)
                .replace("%total-kill%", String.valueOf(PlayerManager.getPlayerKills(player.getName())))
        );
    }
}

