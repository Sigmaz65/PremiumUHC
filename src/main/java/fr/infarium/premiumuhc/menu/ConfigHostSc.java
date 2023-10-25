package fr.infarium.premiumuhc.menu;

import fr.infarium.premiumuhc.manager.ConfigManager;
import fr.infarium.premiumuhc.manager.DataManager;
import fr.infarium.premiumuhc.manager.InvManager;
import fr.infarium.premiumuhc.mysql.HostData;
import fr.infarium.premiumuhc.mysql.ScenariosData;
import fr.infarium.premiumuhc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

import java.util.Arrays;

import static fr.infarium.premiumuhc.commands.SaveInvCommands.*;

public class ConfigHostSc {

    /*
[00] [01] [02] [03] [04] [05] [06] [07] [08]

[09] [10] [11] [12] [13] [14] [15] [16] [17]

[18] [19] [20] [21] [22] [23] [24] [25] [26]

[27] [28] [29] [30] [31] [32] [33] [34] [35]

[36] [37] [38] [39] [40] [41] [42] [43] [44]

[45] [46] [47] [48] [49] [50] [51] [52] [53]


*/
    HostData hostData = new HostData(ConfigManager.server_host);




    public static void open(Player p) {
        Integer scenario_timber = ScenariosData.getInfoScInt(0);
        Integer scenario_cat = ScenariosData.getInfoScInt(1);
        Integer scenario_cutclean = ScenariosData.getInfoScInt(2);
        Integer scenario_blooddiamond = ScenariosData.getInfoScInt(3);
        Integer scenario_nobow = ScenariosData.getInfoScInt(4);
        Integer scenario_hasteyboys = ScenariosData.getInfoScInt(5);
        Integer scenario_nocleanup = ScenariosData.getInfoScInt(6);
        Integer scenario_nofall = ScenariosData.getInfoScInt(7);




        ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 14).setDisplayName("§7---").build(false);


        //items




        //Timber
        ItemStack log1;
        if (scenario_timber == 0) {
            log1 = new ItemBuilder(Material.LOG).setDisplayName("§aTimber §8(Génération)").setLoreWithList(Arrays.asList(
                            "§7",
                            "§7Casser un arbre en un seul coup !",
                            "§7",
                            "§6§l» §eCliquez pour activer !"


                    ))
                    .build(false);
        } else {
            log1 = new ItemBuilder(Material.LOG).setDisplayName("§aTimber §8(Génération)").addEnchant(Enchantment.DIG_SPEED, 1).setLoreWithList(Arrays.asList(
                            "§7",
                            "§7Casser un arbre en un seul coup !",
                            "§7",
                            "§cCliquez pour désactiver"


                    ))
                    .build(false);
        }

        //CatEyes
        ItemStack cat;
        if (scenario_cat == 0) {
            cat = new ItemBuilder(Material.SPIDER_EYE).setDisplayName("§aCatEyes §8(Survie)").setLoreWithList(Arrays.asList(
                            "§7",
                            "§7Tel un chat dans l'obscurité",
                            "§7vous pourrez voir dans le noir.",
                            "§7",
                            "§6§l» §eCliquez pour activer !"


                    ))
                    .build(false);
        } else {
            cat = new ItemBuilder(Material.SPIDER_EYE).setDisplayName("§aCatEyes §8(Survie)").addEnchant(Enchantment.DIG_SPEED, 1).setLoreWithList(Arrays.asList(
                            "§7",
                            "§7Tel un chat dans l'obscurité",
                            "§7vous pourrez voir dans le noir.",
                            "§7",
                            "§cCliquez pour désactiver"


                    ))
                    .build(false);
        }

        //CutClean
        ItemStack cutclean;
        if (scenario_cutclean == 0) {
            cutclean = new ItemBuilder(Material.FURNACE).setDisplayName("§aCutClean §8(Survie)").setLoreWithList(Arrays.asList(
                            "§7",
                            "§7La nourriture et les minerais",
                            "§7sont automatiquement cuits.",
                            "§7",
                            "§6§l» §eCliquez pour activer !"


                    ))
                    .build(false);
        } else {
            cutclean = new ItemBuilder(Material.FURNACE).setDisplayName("§aCutClean §8(Survie)").addEnchant(Enchantment.DIG_SPEED, 1).setLoreWithList(Arrays.asList(
                            "§7",
                            "§7La nourriture et les minerais",
                            "§7sont automatiquement cuits.",
                            "§7",
                            "§cCliquez pour désactiver"


                    ))
                    .build(false);
        }


        //BloodDiamond
        ItemStack blooddiamond;
        if (scenario_blooddiamond == 0) {
            blooddiamond = new ItemBuilder(Material.REDSTONE_BLOCK).setDisplayName("§aBloodDiamond §8(Minerais)").setLoreWithList(Arrays.asList(
                            "§7",
                            "§7Miner du diamant infligera",
                            "§c1❤ §7de dégâts.",
                            "§7",
                            "§6§l» §eCliquez pour activer !"


                    ))
                    .build(false);
        } else {
            blooddiamond = new ItemBuilder(Material.REDSTONE_BLOCK).setDisplayName("§aBloodDiamond §8(Minerais)").addEnchant(Enchantment.DIG_SPEED, 1).setLoreWithList(Arrays.asList(
                            "§7",
                            "§7Miner du diamant infligera",
                            "§c1❤ §7de dégâts.",
                            "§7",
                            "§cCliquez pour désactiver"


                    ))
                    .build(false);
        }

        //NoBow
        ItemStack nobow;
        if (scenario_nobow == 0) {
            nobow = new ItemBuilder(Material.BOW).setDisplayName("§aNoBow §8(PvP)").setLoreWithList(Arrays.asList(
                            "§7",
                            "§7les arcs ne sont plus",
                            "§7réalisables.",
                            "§7",
                            "§6§l» §eCliquez pour activer !"


                    ))
                    .build(false);
        } else {
            nobow = new ItemBuilder(Material.BOW).setDisplayName("§aNoBow §8(PvP)").addEnchant(Enchantment.DIG_SPEED, 1).setLoreWithList(Arrays.asList(
                            "§7",
                            "§7les arcs ne sont plus",
                            "§7réalisables.",
                            "§7",
                            "§cCliquez pour désactiver"


                    ))
                    .build(false);
        }

        //HasteyBoys
        ItemStack hasteyboys;
        if (scenario_hasteyboys == 0) {
            hasteyboys = new ItemBuilder(Material.GOLD_PICKAXE).setDisplayName("§aHasteyBoys §8(Survie)").setLoreWithList(Arrays.asList(
                            "§7",
                            "§7Les outils (Pioche, Hache, Pelle)",
                            "§7sont directement enchantés Efficiency III",
                            "§7et Unbreaking III !",
                            "§7",
                            "§6§l» §eCliquez pour activer !"


                    ))
                    .build(false);
        } else {
            hasteyboys = new ItemBuilder(Material.GOLD_PICKAXE).setDisplayName("§aHasteyBoys §8(Survie)").addEnchant(Enchantment.DIG_SPEED, 1).setLoreWithList(Arrays.asList(
                            "§7",
                            "§7Les outils (Pioche, Hache, Pelle)",
                            "§7sont directement enchantés Efficiency III",
                            "§7et Unbreaking III !",
                            "§7",
                            "§cCliquez pour désactiver"


                    ))
                    .build(false);
        }

        //NoCleanUp
        ItemStack nocleanup;

        ItemStack potion = new ItemStack(Material.POTION, 1, (short) 8229);

        if (scenario_nocleanup == 0) {
            nocleanup = new ItemBuilder(potion).setDisplayName("§aNoCleanUp §8(PvP)").setLoreWithList(Arrays.asList(
                            "§7",
                            "§7Une fois un ennemi tué, vous",
                            "§7obtenez instantanément §c5❤§7.",
                            "§7",
                            "§6§l» §eCliquez pour activer !"


                    ))
                    .build(false);
        } else {
            nocleanup = new ItemBuilder(potion).setDisplayName("§aNoCleanUp §8(PvP)").addEnchant(Enchantment.DIG_SPEED, 1).setLoreWithList(Arrays.asList(
                            "§7",
                            "§7Une fois un ennemi tué, vous",
                            "§7obtenez instantanément §c5❤§7.",
                            "§7",
                            "§cCliquez pour désactiver"


                    ))
                    .build(false);
        }

        //No Fall
        ItemStack nofall;
        if (scenario_nofall == 0) {
            nofall = new ItemBuilder(Material.DIAMOND_BOOTS).setDisplayName("§aNoFall §8(Autre)").setLoreWithList(Arrays.asList(
                            "§7",
                            "§7Les dégâts de chute sont",
                            "§7désactivés.",
                            "§7",
                            "§6§l» §eCliquez pour activer !"


                    ))
                    .build(false);
        } else {
            nofall = new ItemBuilder(Material.DIAMOND_BOOTS).setDisplayName("§aNoFall §8(Autre)").addEnchant(Enchantment.DIG_SPEED, 1).setLoreWithList(Arrays.asList(
                            "§7",
                            "§7Les dégâts de chute sont",
                            "§7désactivés.",
                            "§7",
                            "§cCliquez pour désactiver"


                    ))
                    .build(false);
        }


        //autres
        ItemStack back = new ItemBuilder(Material.ARROW).setDisplayName("§aRetour").build(false);
        ItemStack reset = new ItemBuilder(Material.INK_SACK, 1, (short) 1).setDisplayName("§cTout désactivé").build(false);
        ItemStack select = new ItemBuilder(Material.INK_SACK, 1, (short) 10).setDisplayName("§cTout sélectionner").build(false);



        // set items
        Inventory inv = Bukkit.createInventory(null, 9 * 6, "§e§lMENU §7| §8Scénarios");


        inv.setItem(0, glass);
        inv.setItem(1, glass);
        inv.setItem(9, glass);

        inv.setItem(7, glass);
        inv.setItem(8, glass);
        inv.setItem(17, glass);

        inv.setItem(36, glass);
        inv.setItem(45, glass);
        inv.setItem(46, glass);

        inv.setItem(52, glass);
        inv.setItem(53, glass);
        inv.setItem(44, glass);

        inv.setItem(10, log1);
        inv.setItem(11, cat);
        inv.setItem(12, cutclean);
        inv.setItem(13, blooddiamond);
        inv.setItem(14, nobow);
        inv.setItem(15, hasteyboys);
        inv.setItem(16, nocleanup);
        inv.setItem(19, nofall);

        inv.setItem(49, back);
        inv.setItem(51, reset);
        inv.setItem(47, select);


        p.openInventory(inv);
    }


    public static void clickValue(Player player, int slot, InventoryClickEvent e) {
        Integer scenario_timber = ScenariosData.getInfoScInt(0);
        Integer scenario_cat = ScenariosData.getInfoScInt(1);
        Integer scenario_cutclean = ScenariosData.getInfoScInt(2);
        Integer scenario_blooddiamond = ScenariosData.getInfoScInt(3);
        Integer scenario_nobow = ScenariosData.getInfoScInt(4);
        Integer scenario_hasteyboys = ScenariosData.getInfoScInt(5);
        Integer scenario_nocleanup = ScenariosData.getInfoScInt(6);
        Integer scenario_nofall = ScenariosData.getInfoScInt(7);

        switch (slot) {
            case 10:
                if (scenario_timber == 0) {
                    ScenariosData.setInfoScString("activated", "1", "0");
                } else{
                    ScenariosData.setInfoScString("activated", "0", "0");

                }
                ConfigHostSc.open(player);
                break;
            case 11:
                if (scenario_cat == 0) {
                    ScenariosData.setInfoScString("activated", "1", "1");
                } else{
                    ScenariosData.setInfoScString("activated", "0", "1");

                }
                ConfigHostSc.open(player);
                break;
            case 12:
                if (scenario_cutclean == 0) {
                    ScenariosData.setInfoScString("activated", "1", "2");
                } else{
                    ScenariosData.setInfoScString("activated", "0", "2");

                }
                ConfigHostSc.open(player);
                break;
            case 13:
                if (scenario_blooddiamond == 0) {
                    ScenariosData.setInfoScString("activated", "1", "3");
                } else{
                    ScenariosData.setInfoScString("activated", "0", "3");

                }
                ConfigHostSc.open(player);
                break;
            case 14:
                if (scenario_nobow == 0) {
                    ScenariosData.setInfoScString("activated", "1", "4");
                } else{
                    ScenariosData.setInfoScString("activated", "0", "4");

                }
                ConfigHostSc.open(player);
                break;
            case 15:
                if (scenario_hasteyboys == 0) {
                    ScenariosData.setInfoScString("activated", "1", "5");
                } else{
                    ScenariosData.setInfoScString("activated", "0", "5");

                }
                ConfigHostSc.open(player);
                break;
            case 16:
                if (scenario_nocleanup == 0) {
                    ScenariosData.setInfoScString("activated", "1", "6");
                } else{
                    ScenariosData.setInfoScString("activated", "0", "6");

                }
                ConfigHostSc.open(player);
                break;
            case 19:
                if (scenario_nofall == 0) {
                    ScenariosData.setInfoScString("activated", "1", "7");
                } else{
                    ScenariosData.setInfoScString("activated", "0", "7");

                }
                ConfigHostSc.open(player);
                break;
            case 47:
                ScenariosData.SelectAllScenarios();
                ConfigHostSc.open(player);
                break;
            case 49:
                ConfigHost.open(player);
                break;
            case 51:
                ScenariosData.removeAllScenarios();
                ConfigHostSc.open(player);
                break;
            default:
                break;
        }
    }
}
