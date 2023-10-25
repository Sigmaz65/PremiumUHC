package fr.infarium.premiumuhc.listeners;

import fr.infarium.premiumuhc.commands.SaveInvCommands;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.manager.GameManager;
import fr.infarium.premiumuhc.mysql.HostData;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class DropsListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        if (!SaveInvCommands.playersInGm.containsKey(player)) {
            if (isTreeLeaves(block.getType())) {
                Random random = new Random();
                int randomNumber = random.nextInt(101);

                Integer drop_apple = Integer.valueOf(HostData.getInfoHostString("drop_apple"));

                if (randomNumber <= drop_apple) {
                    ItemStack apple = new ItemStack(Material.APPLE);


                    block.getWorld().dropItem(block.getLocation(), apple);
                }
            }
            if (block.getType().equals(Material.GRAVEL)) {
                Random random = new Random();
                int randomNumber = random.nextInt(101);

                Integer drop_silex = Integer.valueOf(HostData.getInfoHostString("drop_silex"));

                if (randomNumber <= drop_silex) {
                    ItemStack apple = new ItemStack(Material.FLINT);


                    block.getWorld().dropItem(block.getLocation(), apple);
                }
            }
        }
    }

    private boolean isTreeLeaves(Material material) {
        return material == Material.LEAVES || material == Material.LEAVES_2;
    }


    //drop waiting.. Starting.. false
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (GameManager.isState(GameState.WAITING) || GameManager.isState(GameState.STARTING)) {
            e.setCancelled(true);
        }
    }
}