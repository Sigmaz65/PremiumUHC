package fr.infarium.premiumuhc.commands;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.manager.ConfigManager;
import fr.infarium.premiumuhc.manager.GameManager;
import fr.infarium.premiumuhc.manager.ScoreManager;
import fr.infarium.premiumuhc.manager.TextManager;
import fr.infarium.premiumuhc.tasks.AutoStart;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.xml.soap.Text;

public class UHCCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
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
            default:
                s.sendMessage(help());
                break;
        }
        return false;
    }

    public static String help() {
        return Main.getInstance().getConfig().getString("messages.help");
    }
}
