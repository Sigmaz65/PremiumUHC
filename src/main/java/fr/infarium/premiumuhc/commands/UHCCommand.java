package fr.infarium.premiumuhc.commands;

import fr.infarium.premiumuhc.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UHCCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(args.length == 0) {
            s.sendMessage(help());
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "join":

                break;
        }
        return false;
    }

    public static String help() {
        return Main.getInstance().getConfig().getString("messages.help");
    }
}
