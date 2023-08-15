package fr.infarium.premiumuhc;

import fr.infarium.premiumuhc.commands.UHCCommand;
import fr.infarium.premiumuhc.listeners.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        Bukkit.getPluginManager().registerEvents(new Listeners(), this);

        System.out.println(getConfig().getString("messages.console.enable-message"));

        getCommand("uhc").setExecutor(new UHCCommand());

    }

    @Override
    public void onDisable() {
        System.out.println(getConfig().getString("messages.console.disable-message"));
    }

    public static Main getInstance() {
        return instance;
    }
}
