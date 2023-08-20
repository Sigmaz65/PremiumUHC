package fr.infarium.premiumuhc.manager;

import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.tasks.AutoStart;
import fr.infarium.premiumuhc.tasks.StartMining;
import fr.infarium.premiumuhc.tasks.StartTimeInGame;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScoreManager {
    public static void setScoreboard(Player player, GameState scoreBoardType){
        if(scoreBoardType.equals(GameState.WAITING)) {
            final ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
            final Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
            final Objective objective = scoreboard.registerNewObjective("waiting", "dummy");

            objective.setDisplayName(formatScoreBoard(player, ConfigManager.scoreboard_name));
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            int ScoreNum = 15;
            for(String scoreString : ConfigManager.scoreboard_waiting) {
                if(ScoreNum == -1) continue;

                scoreString = formatScoreBoard(player, scoreString);

                final Score score = objective.getScore(scoreString);
                score.setScore(ScoreNum);
                ScoreNum--;
            }

            player.setScoreboard(scoreboard);
            return;
        }
        if(scoreBoardType.equals(GameState.STARTING)) {
            final ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
            final Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
            final Objective objective = scoreboard.registerNewObjective("starting", "dummy");

            objective.setDisplayName(formatScoreBoard(player, ConfigManager.scoreboard_name));
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            int ScoreNum = 15;
            for(String scoreString : ConfigManager.scoreboard_starting) {
                if(ScoreNum == -1) continue;

                scoreString = formatScoreBoard(player, scoreString);

                final Score score = objective.getScore(scoreString);
                score.setScore(ScoreNum);
                ScoreNum--;
            }

            player.setScoreboard(scoreboard);
            return;
        }
        if(scoreBoardType.equals(GameState.MINING)) {
            final ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
            final Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
            final Objective objective = scoreboard.registerNewObjective("mining", "dummy");

            objective.setDisplayName(formatScoreBoard(player, ConfigManager.scoreboard_name));
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            int ScoreNum = 15;
            for(String scoreString : ConfigManager.scoreboard_mining) {
                if(ScoreNum == -1) continue;

                scoreString = formatScoreBoard(player, scoreString);

                final Score score = objective.getScore(scoreString);
                score.setScore(ScoreNum);
                ScoreNum--;
            }

            player.setScoreboard(scoreboard);
            return;
        }
        if(scoreBoardType.equals(GameState.PLAYING)) {
            final ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
            final Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
            final Objective objective = scoreboard.registerNewObjective("playing", "dummy");

            objective.setDisplayName(formatScoreBoard(player, ConfigManager.scoreboard_name));
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            int ScoreNum = 15;
            for(String scoreString : ConfigManager.scoreboard_playing) {
                if(ScoreNum == -1) continue;

                scoreString = formatScoreBoard(player, scoreString);

                final Score score = objective.getScore(scoreString);
                score.setScore(ScoreNum);
                ScoreNum--;
            }

            player.setScoreboard(scoreboard);
            return;
        }
        if(scoreBoardType.equals(GameState.END)) {
            final ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
            final Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
            final Objective objective = scoreboard.registerNewObjective("end", "dummy");

            objective.setDisplayName(formatScoreBoard(player, ConfigManager.scoreboard_name));
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            int ScoreNum = 15;
            for(String scoreString : ConfigManager.scoreboard_end) {
                if(ScoreNum == -1) continue;

                scoreString = formatScoreBoard(player, scoreString);

                final Score score = objective.getScore(scoreString);
                score.setScore(ScoreNum);
                ScoreNum--;
            }

            player.setScoreboard(scoreboard);
            return;
        }
    }

    public static GameState getPlayerScoreBoard(Player p) {
        return GameManager.State();
    }

    public static String formatScoreBoard(Player player, String text) {
        World world = Bukkit.getWorlds().get(0);
        WorldBorder wb = world.getWorldBorder();
        int timerr = AutoStart.timer + 1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDate = dateFormat.format(new Date());
        return TextManager.formatString(text
                .replace("%date%", currentDate)
                .replace("%player%", player.getName())
                .replace("%server-ip%", ConfigManager.ip)
                .replace("%total-party%", String.valueOf(PlayerManager.getTotalParty(player)))
                .replace("%total-kills%", String.valueOf(PlayerManager.getTotalKills(player)))
                .replace("%total-victory%", String.valueOf(PlayerManager.getTotalVictory(player)))
                .replace("%player-ingame%", String.valueOf(GameManager.getTotalInGamePlayer()))
                .replace("%player-max%", String.valueOf(GameManager.getTotalMaxPlayer()))
                .replace("%host-owner%", String.valueOf(GameManager.getHostOwner()))
                .replace("%time%", String.valueOf(timerr))
                .replace("%minute-tp%", String.valueOf(StartMining.minute))
                .replace("%second-tp%", String.valueOf((StartMining.second <= 9) ? "0" + StartMining.second : StartMining.second))
                .replace("%minute-ingame%", String.valueOf(StartTimeInGame.minute))
                .replace("%second-ingame%", String.valueOf((StartTimeInGame.second <= 9) ? "0" + StartTimeInGame.second : StartTimeInGame.second))
                .replace("%players-left%", String.valueOf(GameManager.getPlayers().size()))
                .replace("%border%", String.valueOf(wb.getSize()))
                .replace("%total-kill%", String.valueOf(PlayerManager.getPlayerKills(player.getName())))
        );
    }
}

