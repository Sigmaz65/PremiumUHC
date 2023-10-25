package fr.infarium.premiumuhc.commands;

import fr.infarium.premiumuhc.menu.TeamsGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamsCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player) new TeamsGUI(((Player) s).getUniqueId()).open(true);
        return false;
    }
}
