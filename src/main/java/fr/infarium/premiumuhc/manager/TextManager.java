package fr.infarium.premiumuhc.manager;

import fr.infarium.premiumuhc.Main;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;

public class TextManager {

    public static String FormatString(String text){
        return text.replace("%prefix%", Main.getInstance().getConfig().getString("options.prefix").replace("%nl%", "\n"));
    }


    public static String centerText(String text) {
        int maxWidth = 60;
        int spaces = (int) Math.round((maxWidth - 1.4 * ChatColor.stripColor(text).length()) / 2);

        return StringUtils.repeat(" ", spaces) + text;
    }
}
