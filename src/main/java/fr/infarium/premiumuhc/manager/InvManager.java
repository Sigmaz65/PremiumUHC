package fr.infarium.premiumuhc.manager;

import org.bukkit.entity.Player;

public class InvManager {
    public static void clearInv(Player player){
        player.getInventory().clear();
        player.getInventory().setBoots(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setHelmet(null);
        player.setFoodLevel(20);
        player.setHealth(20);
    }
}
