package fr.infarium.premiumuhc.tasks;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.manager.ItemManager;
import fr.infarium.premiumuhc.manager.*;
import fr.infarium.premiumuhc.mysql.HostData;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

import static fr.infarium.premiumuhc.commands.SaveInvCommands.playersInGm;

public class AutoStart extends BukkitRunnable {
    public static Integer timer = 5;

    @Override
    public void run() {
        if (timer == 0){
            if (!playersInGm.isEmpty()) playersInGm.clear();
            for (Player pls : GameManager.getPlayers()){
                pls.sendMessage(TextManager.formatString(ConfigManager.end_timer_message));
                ItemManager.giveStartItem(pls);
                pls.sendTitle(TextManager.formatString(ConfigManager.final_second_timer), null);
            }

            GameManager.setState(GameState.MINING);


            //GM
            GameMode Gm = GameMode.SURVIVAL;
            if (HostData.getInfoHostString("rule_gm").equals("§aAdventure")){
                Gm = GameMode.ADVENTURE;
            }

            Random rand = new Random();
            for (Player pls : GameManager.getPlayers()){

                /* // ----- Random Equipes ------
                if (PlayerTeamHandler.getInstance().getTeamByPlayer(pls) == null){
                    int teamSize = Integer.parseInt(HostData.getInfoHostString("size_teams"));

                    byte teamIndex = 0;
                    int teamFound = 0;

                    while (teamFound == 0){
                        TeamList teamTest = TeamList.getTeamByColor((byte) teamIndex);
                        if (PlayerTeamHandler.getInstance().getPlayersInTeam(teamTest).size() != teamSize){
                            PlayerTeamHandler.getInstance().setPlayerToTeam(pls, teamTest);
                            teamFound = 1;
                        }

                        teamIndex++;
                    }
                } */

                // ----- Scénarios ------
                ScenariosManager.StartGameScenarios(pls);

                // ----- Set Scoreboard ------
                ScoreManager.setScoreboard(pls, GameState.MINING);

                // ----- Set Gamemode ------
                pls.setGameMode(Gm);

                // -------- teleportation --------
                Integer border_start = Integer.parseInt(HostData.getInfoHostString("border_start"));

                // Générer le premier nombre aléatoire entre -500 et 500
                int num1 = rand.nextInt((border_start * 2) + 1) - (border_start - 10);

                // Générer le deuxième nombre aléatoire entre -500 et 500
                int num2 = rand.nextInt((border_start * 2) + 1) - (border_start - 10);

                // Vérifier si les nombres sont dans la plage interdite
                while (num1 >= -150 && num1 <= 150) {
                    num1 = rand.nextInt((border_start * 2) + 1) - (border_start - 10);
                }

                while (num2 >= -150 && num2 <= 150) {
                    num2 = rand.nextInt((border_start * 2) + 1) - (border_start - 10);
                }


                World world = Bukkit.getWorld("world");
                Location loc = new Location(world, num1, 100, num2, 90f, 0f);

                pls.teleport(loc);
                PlayerManager.addTotalParty(pls, 1);
            }

            //border
            StartMining mining = new StartMining();
            mining.runTaskTimer(Main.getInstance(), 0, 20);

            //pvp
            AutoPvp autopvp = new AutoPvp();
            autopvp.runTaskTimer(Main.getInstance(), 0, 20);

            //start time in game
            StartTimeInGame startTimeInGame = new StartTimeInGame();
            startTimeInGame.runTaskTimer(Main.getInstance(), 0, 20);

            cancel();
        }
        if (timer == 1){
            for (Player pls : GameManager.getPlayers()){
                pls.sendTitle(TextManager.formatString(ConfigManager.one_second_timer), null);
            }
        }
        if (timer == 2){
            for (Player pls : GameManager.getPlayers()){
                pls.sendTitle(TextManager.formatString(ConfigManager.two_second_timer), null);
            }
        }
        if (timer == 3){
            for (Player pls : GameManager.getPlayers()){
                pls.sendTitle(TextManager.formatString(ConfigManager.three_second_timer), null);
            }
        }
        if (timer == 4){
            for (Player pls : GameManager.getPlayers()){
                pls.sendTitle(TextManager.formatString(ConfigManager.four_second_timer), null);
            }
        }
        if (timer == 5){
            for (Player pls : GameManager.getPlayers()){
                pls.sendTitle(TextManager.formatString(ConfigManager.five_second_timer), null);
            }
        }

        for (Player pls : GameManager.getPlayers()){
            if (timer != 0) {
                pls.sendMessage(TextManager.formatString(ConfigManager.timer_message
                        .replace("%time%", String.valueOf(timer))
                        .replace("%second%", (timer == 1) ? String.valueOf(ConfigManager.one_second) : String.valueOf(ConfigManager.several_seconds))
                ));
            }
        }

        timer--;
    }

}
