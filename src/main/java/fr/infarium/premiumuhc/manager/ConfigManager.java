package fr.infarium.premiumuhc.manager;

import fr.infarium.premiumuhc.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigManager {

    public static String prefix = null;
    public static String ip = null;
    public static String scoreboard_name = null;
    public static String quit_message = null;
    public static String join_message = null;
    public static String be_spectator_message = null;
    public static String be_host_message = null;
    public static String info_host_start_message = null;
    public static String not_enough_players_message = null;
    public static String no_host_permission_message = null;

    //timer
    public static String one_second = null;
    public static String several_seconds = null;
    public static String timer_message = null;
    public static String end_timer_message = null;
    //timer title
    public static String five_second_timer = null;
    public static String four_second_timer = null;
    public static String three_second_timer = null;
    public static String two_second_timer = null;
    public static String one_second_timer = null;
    public static String final_second_timer = null;


    public static Integer max_player = null;
    public static Integer minage_second = null;
    public static Integer minage_minute = null;
    public static Integer worldborder_center_x = null;
    public static Integer worldborder_center_y = null;
    public static Integer worldborder_size = null;
    public static Double worldborder_speed = null;
    public static Integer worldborder_lastsize = null;

    public static List<String> scoreboard_waiting = null;
    public static List<String> scoreboard_starting = null;
    public static List<String> scoreboard_mining = null;
    public static List<String> scoreboard_playing = null;
    public static List<String> scoreboard_end = null;



    public static void loadConfig(boolean isReloadTotal) {
        if(isReloadTotal) Main.getInstance().saveConfig();

        FileConfiguration config = Main.getInstance().getConfig();

// String
        prefix = config.getString("options.prefix");
        ip = config.getString("options.ip");
        scoreboard_name = config.getString("options.scoreboard-name");
        quit_message = config.getString("messages.quit-message");
        join_message = config.getString("messages.join-message");
        be_spectator_message = config.getString("messages.be-spectator-message");
        be_host_message = config.getString("messages.host.be-host-message");
        info_host_start_message = config.getString("messages.host.info-host-start-message");
        not_enough_players_message = config.getString("messages.host.not-enough-players-message");
        no_host_permission_message = config.getString("messages.host.no-host-permission-message");
        //timer
        one_second = config.getString("messages.timer.one-second");
        several_seconds = config.getString("messages.timer.several-seconds");

        timer_message = config.getString("messages.timer.timer-message");
        end_timer_message = config.getString("messages.timer.end-timer-message");
        //timer title
        five_second_timer = config.getString("title.timer.five-second-title");
        four_second_timer = config.getString("title.timer.four-second-title");
        three_second_timer = config.getString("title.timer.three-second-title");
        two_second_timer = config.getString("title.timer.two-second-title");
        one_second_timer = config.getString("title.timer.one-second-title");
        final_second_timer = config.getString("title.timer.final-second-title");

// int
        max_player = Integer.valueOf(config.getString("options.game.max-player"));
        minage_second = Integer.valueOf(config.getString("options.game.time-mining.second"));
        minage_minute = Integer.valueOf(config.getString("options.game.time-mining.minute"));

        worldborder_center_x = Integer.valueOf(config.getString("options.game.world-border.center.x"));
        worldborder_center_y = Integer.valueOf(config.getString("options.game.world-border.center.y"));
        worldborder_size = Integer.valueOf(config.getString("options.game.world-border.size"));
        worldborder_speed = Double.valueOf(config.getString("options.game.world-border.speed-in-block"));
        worldborder_lastsize = Integer.valueOf(config.getString("options.game.world-border.last-size"));


//scoreboard
        scoreboard_waiting = config.getStringList("scoreboard-waiting");
        scoreboard_starting = config.getStringList("scoreboard-starting");
        scoreboard_mining = config.getStringList("scoreboard-mining");
        scoreboard_playing = config.getStringList("scoreboard-playing");
        scoreboard_end = config.getStringList("scoreboard-end");
    }
}