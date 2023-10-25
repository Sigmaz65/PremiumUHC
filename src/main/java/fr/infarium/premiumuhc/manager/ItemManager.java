package fr.infarium.premiumuhc.manager;

import fr.infarium.premiumuhc.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static fr.infarium.premiumuhc.commands.SaveInvCommands.savedArmors;
import static fr.infarium.premiumuhc.commands.SaveInvCommands.savedInventories;

public class ItemManager {
    public static void giveStartItem(Player player){
        Player owner = GameManager.getHostOwnerPlayer();
        InvManager.clearInv(player);
        if (owner != null) {
            if (!savedInventories.isEmpty()) {
                player.getInventory().setContents(savedInventories.get(owner));
                if (!savedArmors.isEmpty()){
                    ItemStack[] armorContents = savedArmors.get(owner);
                    player.getInventory().setHelmet(armorContents[3]);
                    player.getInventory().setChestplate(armorContents[2]);
                    player.getInventory().setLeggings(armorContents[1]);
                    player.getInventory().setBoots(armorContents[0]);
                }
            }
            player.updateInventory();
        }

        /*
        ItemStack Axe = new ItemBuilder(Material.IRON_AXE, 1).addEnchant(Enchantment.DIG_SPEED, 3).build(false);
        ItemStack Food = new ItemBuilder(Material.COOKED_BEEF, 64).build(false);
        ItemStack Book = new ItemBuilder(Material.BOOK, 7).build(false);
        ItemStack Water = new ItemBuilder(Material.WATER_BUCKET, 1).build(false);

        player.getInventory().addItem(Axe);
        player.getInventory().addItem(Food);
        player.getInventory().addItem(Book);
        player.getInventory().addItem(Water);

         */
    }

    public static void giveNormalWaitingItem(Player player){
        ItemStack star = new ItemBuilder(Material.NETHER_STAR, 1).setDisplayName("§d§lSélecteur d'équipe §f| Clique").build(false);
        ItemStack doorleave = new ItemBuilder(Material.DARK_OAK_DOOR_ITEM, 1).setDisplayName("§c§lRetour au lobby §f| Clique").build(false);

        player.getInventory().setItem(8, doorleave);
        player.getInventory().setItem(0,star);
    }

    public static void giveHostWaitingItem(Player player){
        ItemStack redstone = new ItemBuilder(Material.REDSTONE_BLOCK, 1).setDisplayName("§c§lConfiguration Host §f| Clique").build(false);
        player.getInventory().setItem(4, redstone);
    }
}
