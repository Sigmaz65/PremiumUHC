package fr.infarium.premiumuhc.tasks;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.manager.TabHandler;
import org.bukkit.Bukkit;

public class TabUpdate {
    public static void start(){
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
            Bukkit.getOnlinePlayers().forEach(TabHandler::setTab);
        }, 0L, 80L);
    }
}
