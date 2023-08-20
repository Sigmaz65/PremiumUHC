package fr.infarium.premiumuhc;

import fr.infarium.premiumuhc.commands.UHCCommand;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.listeners.DeathListener;
import fr.infarium.premiumuhc.listeners.Listeners;
import fr.infarium.premiumuhc.manager.ConfigManager;
import fr.infarium.premiumuhc.manager.GameManager;
import fr.infarium.premiumuhc.manager.TeleportManager;
import fr.infarium.premiumuhc.tasks.ScoreBoardUpdate;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    private static Main instance;
    public static GameState state;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        ConfigManager.loadConfig(false);

        GameManager.setState(GameState.WAITING);

        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);

        System.out.println(getConfig().getString("messages.console.enable-message"));

        getCommand("uhc").setExecutor(new UHCCommand());

        World world = Bukkit.getWorlds().get(0);
        WorldBorder wb = world.getWorldBorder();
        wb.setCenter(ConfigManager.worldborder_center_x, ConfigManager.worldborder_center_y);
        wb.setSize(ConfigManager.worldborder_size);



        TeleportManager.loadSpawn();
        ScoreBoardUpdate.start();

    }

    @Override
    public void onDisable() {
        System.out.println(getConfig().getString("messages.console.disable-message"));
    }

    public static Main getInstance() {
        return instance;
    }
}
