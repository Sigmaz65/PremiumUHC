package fr.infarium.premiumuhc.menu;

import fr.infarium.premiumuhc.manager.ConfigManager;
import fr.infarium.premiumuhc.manager.DataManager;
import fr.infarium.premiumuhc.mysql.HostData;
import fr.infarium.premiumuhc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ConfigHostSave {
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





        ItemStack config = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 7).setDisplayName("§7Emplacement vide").build(false);

        ItemStack plus = new ItemBuilder(Material.EMERALD).setDisplayName("§aSauvegarder la configuration actuelle").setLoreWithList(Arrays.asList(
                        "§7Créez une sauvegarde de la configuration",
                        "§7de partie actuelle afin de pouvoir la",
                        "§7charger directement une prochaine fois !",
                        "§7",
                        "§6§l» §eCliquez pour créer une sauvegarde !"
                ))
                .build(false);


        ItemStack back = new ItemBuilder(Material.DARK_OAK_DOOR_ITEM).setDisplayName("§a§lRetour").build(false);



        // set items
        Inventory inv = Bukkit.createInventory(null, 9 * 4, "§e§lMENU §7| §8Mes configurations");


        inv.setItem(0, glass);
        inv.setItem(1, glass);
        inv.setItem(9, glass);

        inv.setItem(7, glass);
        inv.setItem(8, glass);
        inv.setItem(17, glass);



        inv.setItem(18, glass);
        inv.setItem(27, glass);
        inv.setItem(28, glass);

        inv.setItem(34, glass);
        inv.setItem(35, glass);
        inv.setItem(26, glass);

        inv.setItem(10, config);
        inv.setItem(11, config);
        inv.setItem(12, config);
        inv.setItem(13, config);
        inv.setItem(14, config);
        inv.setItem(15, config);
        inv.setItem(16, config);

        inv.setItem(31, back);
        inv.setItem(32, plus);


        p.openInventory(inv);
    }


    public static void clickValue(Player player, int slot, InventoryClickEvent e) {

        switch (slot) {
            case 10:
                if (e.getClickedInventory() == null) {
                    break;
                }
                if (e.isLeftClick()) {
                    player.sendMessage("§cClique gauche");
                }
                if (e.isRightClick()){
                    player.sendMessage("§cClique droit");
                }
                break;
            case 31:
                ConfigHost.open(player);
                break;
            default:
                break;
        }
    }
}
