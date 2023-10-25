package fr.infarium.premiumuhc.menu;

import fr.infarium.premiumuhc.manager.ConfigManager;
import fr.infarium.premiumuhc.manager.DataManager;
import fr.infarium.premiumuhc.manager.InvManager;
import fr.infarium.premiumuhc.manager.TextManager;
import fr.infarium.premiumuhc.mysql.HostData;
import fr.infarium.premiumuhc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

import static fr.infarium.premiumuhc.commands.SaveInvCommands.*;

public class ConfigHostRules {

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





        ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 5).setDisplayName("§7---").build(false);


        //items


        // skull host-1

        //string
        String player_host = HostData.getInfoHostString("player_host");
        String server_status = HostData.getInfoHostString("server_status");
        String border_time = HostData.getInfoHostString("border_time");


        String time_pvp = HostData.getInfoHostString("time_pvp");

        //int
        String num_player = HostData.getInfoHostString("num_player");
        String max_player = String.valueOf(DataManager.getMaxPlayer());

        String border_start = HostData.getInfoHostString("border_start");
        String border_end = HostData.getInfoHostString("border_end");
        String border_speed = HostData.getInfoHostString("border_speed");

        String rule_time = HostData.getInfoHostString("rule_time");
        String rule_gm = HostData.getInfoHostString("rule_gm");
        String rule_nether = HostData.getInfoHostString("rule_nether");





        ItemStack iron_sword = new ItemBuilder(Material.IRON_SWORD).setDisplayName("§aPhase PVP").setLoreWithList(Arrays.asList(
                "§7Changer le temps avant la phase",
                "§7PvP !",
                "§7",
                "§7Phase PvP: §a" + time_pvp,
                "§7",
                "§eClique gauche: §a+1 minute",
                "§eClique droit: §c-1 minute"


                ))
                .build(false);

        ItemStack stained_glass = new ItemBuilder(Material.STAINED_GLASS).setDisplayName("§aGestion de la bordure").setLoreWithList(Arrays.asList(
                "§7Changer les paramètres de la bordure",
                "§7de la partie !",
                "§7",
                "§7Temps: §a" + border_time,
                "§7Vitesse: §a" + border_speed + " blocs / sec",
                "§7Taille Max/min: §a" + border_end + " / " + border_start,
                "§7",
                "§6§l» §eCliquez pour modifier !"))
                .build(false);

        ItemStack chest = new ItemBuilder(Material.CHEST).setDisplayName("§aInventaire de départ").setLoreWithList(Arrays.asList(
                "§7Modifiez l'inventaire des joueurs",
                "§7lors du démarrage de la partie !",
                "§7",
                "§b/finish §7- Pour terminer l'édition.",
                "§b/enchant §7- Avec un objet dans la",
                "§7main pour l'enchanter.",
                "§7",
                "§6§l» §eCliquez pour éditer !"))
                .build(false);

        ItemStack beef = new ItemBuilder(Material.RAW_BEEF).setDisplayName("§aGestion des Drops").setLoreWithList(Arrays.asList(
                "§7Modifiez les paramètres des drops",
                "§7durant la partie !",
                "§7",
                "§6§l» §eCliquez pour éditer !"))
                .build(false);

        ItemStack bone = new ItemBuilder(Material.WATCH).setDisplayName("§aGestion du Temps").setLoreWithList(Arrays.asList(
                "§7Modifiez le temps de votre partie !",
                "§7",
                "§7Réglage: §a" + rule_time,
                "§7",
                "§6§l» §eCliquez pour modifier !"))
                .build(false);

        ItemStack skull_simple = new ItemBuilder(Material.SKULL_ITEM).setDisplayName("§aGameMode par défaut").setLoreWithList(Arrays.asList(
                "§7Modifiez le gamemode par défaut.",
                "§7",
                "§7Réglage: §a" + rule_gm,
                "§7",
                "§6§l» §eCliquez pour modifier !"))
                .build(false);

        ItemStack nether = new ItemBuilder(Material.NETHERRACK).setDisplayName("§cActiver le Nether").setLoreWithList(Arrays.asList(
                "§7Activer ou désactiver les portails",
                "§7permettant de se rendre dans le",
                "§7Nether pendant votre partie !",
                "§7",
                "§7Réglage: " + rule_nether,
                "§7",
                "§6§l» §eCliquez pour modifier !"))
                .build(false);

        ItemStack back = new ItemBuilder(Material.ARROW).setDisplayName("§aRetour").build(false);



        // set items
        Inventory inv = Bukkit.createInventory(null, 9 * 6, "§e§lMENU §7| §8Règles de la partie");


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

        inv.setItem(11, iron_sword);
        inv.setItem(13, stained_glass);
        inv.setItem(15, chest);
        inv.setItem(28, beef);
        inv.setItem(30, bone);
        inv.setItem(32, skull_simple);
        inv.setItem(34, nether);

        inv.setItem(49, back);


        p.openInventory(inv);
    }


    public static void clickValue(Player player, int slot, InventoryClickEvent e) {

        switch (slot) {
            case 11:
                if (e.getClickedInventory() == null) {
                    break;
                }
                if (e.isLeftClick()) {
                    if (!HostData.getInfoHostString("time_pvp").equals("60:00")){
                        String[] valuesTimePvp = HostData.getInfoHostString("time_pvp").split(":");


                        Integer newM = Integer.valueOf(valuesTimePvp[0]) + 1;



                        String new_time_pvp = newM + ":00";

                        HostData.setInfoHostString("time_pvp",  new_time_pvp);
                        ConfigHostRules.open(player);
                    }
                }
                if (e.isRightClick()){
                    if (!HostData.getInfoHostString("time_pvp").equals("1:00")){
                        String[] valuesTimePvp = HostData.getInfoHostString("time_pvp").split(":");


                        Integer newM = Integer.valueOf(valuesTimePvp[0]) - 1;



                        String new_time_pvp = newM + ":00";

                        HostData.setInfoHostString("time_pvp",  new_time_pvp);
                        ConfigHostRules.open(player);
                    }
                }
                break;
            case 13:

                ConfigHostBorder.open(player);

                break;
            case 15:
                playersInGm.put(player, player.getGameMode());
                player.setGameMode(GameMode.CREATIVE);
                InvManager.clearInv(player);
                if (savedInventories.containsKey(player)) {
                    player.getInventory().setContents(savedInventories.get(player));
                    player.getInventory().setArmorContents(savedArmors.get(player));
                    player.updateInventory();

                }
                player.sendMessage("§c");
                player.sendMessage("§a§l         Vous entrez en mode édition");
                player.sendMessage("§7§l» §b/finish §7- §ePour sauvegarder l'édition");
                player.sendMessage("§7§l» §b/enchant §7- §eAvec un objet dans la main pour l'enchanter");
                player.sendMessage("§c");

                player.closeInventory();
                break;
            case 28:
                ConfigHostDrops.open(player);
                break;
            case 30:
                World world = player.getServer().getWorld("world");
                if (HostData.getInfoHostString("rule_time").equals("§aNormal")){
                    HostData.setInfoHostString("rule_time", "§aJour éternel");
                    world.setGameRuleValue("doDaylightCycle", "false");
                    world.setTime(6000);
                    ConfigHostRules.open(player);
                    break;
                }
                if (HostData.getInfoHostString("rule_time").equals("§aJour éternel")){
                    HostData.setInfoHostString("rule_time", "§aNuit éternel");
                    world.setGameRuleValue("doDaylightCycle", "false");
                    world.setTime(18000);
                    ConfigHostRules.open(player);
                    break;
                }
                if (HostData.getInfoHostString("rule_time").equals("§aNuit éternel")){
                    HostData.setInfoHostString("rule_time", "§aNormal");
                    world.setGameRuleValue("doDaylightCycle", "true");
                    world.setTime(1000);
                    ConfigHostRules.open(player);
                    break;
                }
                break;
            case 32:
                if (HostData.getInfoHostString("rule_gm").equals("§aSurvival")){
                    HostData.setInfoHostString("rule_gm", "§aAdventure");
                    ConfigHostRules.open(player);
                    break;
                }
                if (HostData.getInfoHostString("rule_gm").equals("§aAdventure")){
                    HostData.setInfoHostString("rule_gm", "§aSurvival");
                    ConfigHostRules.open(player);
                    break;
                }
                break;
            case 34:
                if (HostData.getInfoHostString("rule_nether").equals("§cDésactivé")){
                    HostData.setInfoHostString("rule_nether", "§aActivé");
                    ConfigHostRules.open(player);
                    break;
                }
                if (HostData.getInfoHostString("rule_nether").equals("§aActivé")){
                    HostData.setInfoHostString("rule_nether", "§cDésactivé");
                    ConfigHostRules.open(player);
                    break;
                }
                break;
            case 49:
                ConfigHost.open(player);
                break;
            default:
                break;
        }
    }
}
