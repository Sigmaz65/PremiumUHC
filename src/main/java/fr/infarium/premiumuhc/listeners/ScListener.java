package fr.infarium.premiumuhc.listeners;

import fr.infarium.premiumuhc.commands.SaveInvCommands;
import fr.infarium.premiumuhc.manager.TextManager;
import fr.infarium.premiumuhc.mysql.ScenariosData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ScListener implements Listener {


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material blockType = event.getBlock().getType();
        if (!SaveInvCommands.playersInGm.containsKey(player)) {
            //timber
            if (ScenariosData.getInfoScInt(0) == 1) {

                if (blockType == Material.LOG || blockType == Material.LOG_2) {
                    event.setCancelled(true); // Cancel the original block break event
                    breakTree(block);
                }

            }

            //CutClean minerais
            if (ScenariosData.getInfoScInt(2) == 1) {
                if (event.isCancelled()) {
                    return;
                }
                ItemStack drop = null;

                // Vérifier le type de bloc cassé
                if (blockType == Material.IRON_ORE) {
                    drop = new ItemStack(Material.IRON_INGOT);
                } else if (blockType == Material.GOLD_ORE) {
                    drop = new ItemStack(Material.GOLD_INGOT);
                }

                if (drop != null) {
                    event.setCancelled(true); // Annuler la destruction normale
                    event.getBlock().setType(Material.AIR); // Supprimer le bloc cassé

                    // Lâcher l'objet traité
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), drop);

                    // Donner de l'expérience au joueur
                    event.getPlayer().giveExp(1);
                }
            }

            if (ScenariosData.getInfoScInt(3) == 1) {
                if(blockType == Material.DIAMOND_ORE){
                    player.setHealth(player.getHealth() - 1);
                }
            }
        }
    }

    //Cut clean bouffe
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Cow || entity instanceof Pig || entity instanceof Chicken || entity instanceof Sheep) {
            Iterator<ItemStack> iterator = event.getDrops().iterator();
            while (iterator.hasNext()) {
                ItemStack drop = iterator.next();
                if (drop.getType() == getNotCookedMaterial(entity.getType())) {
                    iterator.remove(); // Supprimez l'élément non cuit de la liste des gouttes.
                }
            }

            // Ajoutez la viande cuite à la liste des gouttes.
            ItemStack cookedMeat = new ItemStack(getCookedMaterial(entity.getType()), 2); // Obtenez l'item cuit approprié. x2
            event.getDrops().add(cookedMeat);
        }
    }

    private Material getCookedMaterial(EntityType entityType) {
        switch (entityType) {
            case COW:
                return Material.COOKED_BEEF;
            case PIG:
                return Material.GRILLED_PORK;
            case CHICKEN:
                return Material.COOKED_CHICKEN;
            case SHEEP:
                return Material.COOKED_MUTTON;
            default:
                return Material.AIR;
        }
    }

    private Material getNotCookedMaterial(EntityType entityType) {
        switch (entityType) {
            case COW:
                return Material.RAW_BEEF;
            case PIG:
                return Material.PORK;
            case CHICKEN:
                return Material.RAW_CHICKEN;
            case SHEEP:
                return Material.MUTTON;
            default:
                return Material.AIR;
        }
    }

    //NoBow & HasteyBoys
    @EventHandler
    public void onCraft(CraftItemEvent e){
        if (e.getWhoClicked() instanceof Player) {
            Player player = (Player) e.getWhoClicked();
            if (e.getCurrentItem() != null){
                //bow
                if (e.getRecipe().getResult().getType() == Material.BOW) {
                    if (ScenariosData.getInfoScInt(4) == 1) {
                        e.setCancelled(true);
                        player.sendMessage(TextManager.formatString("%prefix% &cLe craft de l'arc est désactivé dans cette partie."));
                    }
                }
                //HasteyBoys
                if (e.getRecipe().getResult().getType() == Material.DIAMOND_PICKAXE || e.getRecipe().getResult().getType() == Material.IRON_PICKAXE || e.getRecipe().getResult().getType() == Material.STONE_PICKAXE || e.getRecipe().getResult().getType() == Material.WOOD_PICKAXE || e.getRecipe().getResult().getType() == Material.GOLD_PICKAXE || e.getRecipe().getResult().getType() == Material.DIAMOND_AXE || e.getRecipe().getResult().getType() == Material.IRON_AXE || e.getRecipe().getResult().getType() == Material.STONE_AXE || e.getRecipe().getResult().getType() == Material.WOOD_AXE || e.getRecipe().getResult().getType() == Material.GOLD_AXE || e.getRecipe().getResult().getType() == Material.DIAMOND_SPADE || e.getRecipe().getResult().getType() == Material.IRON_SPADE || e.getRecipe().getResult().getType() == Material.STONE_SPADE || e.getRecipe().getResult().getType() == Material.WOOD_SPADE || e.getRecipe().getResult().getType() == Material.GOLD_SPADE) {
                    if (ScenariosData.getInfoScInt(5) == 1) {
                        // Créez un nouvel ItemStack avec l'enchantement Efficacité 3
                        ItemStack enchantedPickaxe = e.getRecipe().getResult();
                        enchantedPickaxe.addEnchantment(Enchantment.DIG_SPEED, 3);
                        enchantedPickaxe.addEnchantment(Enchantment.DURABILITY, 3);

                        // Remplacez le résultat du craft par le nouvel ItemStack enchanté
                        e.setCurrentItem(enchantedPickaxe);
                    }
                }
            }
        }
    }

    //NoClean Up -> DeathListener


    //NoFall
    @EventHandler
    public void onFall(EntityDamageEvent e){
        if (e.getEntity() instanceof Player){
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL){
                if (ScenariosData.getInfoScInt(7) == 1){
                    e.setCancelled(true);
                }
            }
        }
    }

    //timber
    private void breakTree(Block startBlock) {
        Queue<Block> queue = new LinkedList<>();
        queue.add(startBlock);

        while (!queue.isEmpty()) {
            Block block = queue.poll();

            if (block.getType() == Material.LOG || block.getType() == Material.LOG_2) {
                // Drop wood items
                ItemStack woodItem = new ItemStack(block.getType(), 1);
                block.getWorld().dropItemNaturally(block.getLocation(), woodItem);

                block.setType(Material.AIR);

                // Add adjacent blocks to the queue
                for (int xOffset = -1; xOffset <= 1; xOffset++) {
                    for (int yOffset = -1; yOffset <= 1; yOffset++) {
                        for (int zOffset = -1; zOffset <= 1; zOffset++) {
                            if (xOffset == 0 && yOffset == 0 && zOffset == 0) {
                                continue;
                            }

                            Block adjacentBlock = block.getLocation().add(xOffset, yOffset, zOffset).getBlock();
                            if (!queue.contains(adjacentBlock)) {
                                queue.add(adjacentBlock);
                            }
                        }
                    }
                }
            }
        }
    }
}
