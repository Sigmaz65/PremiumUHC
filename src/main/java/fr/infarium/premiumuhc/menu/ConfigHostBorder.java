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

public class ConfigHostBorder {
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





        ItemStack stained_glass = new ItemBuilder(Material.STAINED_GLASS).setDisplayName("§aTemps de la bordure").setLoreWithList(Arrays.asList(
                        "§7Changer le temps avant que la",
                        "§7bordure commence à rétrécir.",
                        "§7",
                        "§7Temps: §a" + border_time,
                        "§7",
                        "§eClique gauche: §a+1 minute",
                        "§eClique droit: §c-1 minute"
                ))
                .build(false);

        ItemStack feather = new ItemBuilder(Material.FEATHER).setDisplayName("§aVitesse de la bordure").setLoreWithList(Arrays.asList(
                        "§7Changer la vitesse en blocs par",
                        "§7seconde de la bordure lorsqu'elle",
                        "§7rétrécit.",
                        "§7",
                        "§7Vitesse: §a" + border_speed + " §ablocs / sec",
                        "§7",
                        "§eClique gauche: §a+0.5",
                        "§eClique droit: §c-0.5"
                ))
                .build(false);

        ItemStack sign1 = new ItemBuilder(Material.SIGN).setDisplayName("§aTaille au début").setLoreWithList(Arrays.asList(
                        "§7Changer la taille de la bordure",
                        "§7lors du démarrage de la partie !",
                        "§7",
                        "§7Taille de départ: §a" + border_start,
                        "§7",
                        "§eClique gauche: §a+100",
                        "§eClique droit: §c-100"
                ))
                .build(false);

        ItemStack sign2 = new ItemBuilder(Material.SIGN).setDisplayName("§aTaille à la fin").setLoreWithList(Arrays.asList(
                        "§7Changer la taille de la bordure",
                        "§7minimum, elle s'arrêtera de rétrécir",
                        "§7Lorsqu'elle aura atteint cette taille !",
                        "§7",
                        "§7Taille de fin: §a" + border_end,
                        "§7",
                        "§eClique gauche: §a+10",
                        "§eClique droit: §c-10"
                ))
                .build(false);


        ItemStack back = new ItemBuilder(Material.ARROW).setDisplayName("§aRetour").build(false);



        // set items
        Inventory inv = Bukkit.createInventory(null, 9 * 4, "§e§lMENU §7| §8Gestion de la bordure");


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

        inv.setItem(10, stained_glass);
        inv.setItem(12, feather);
        inv.setItem(14, sign1);
        inv.setItem(16, sign2);

        inv.setItem(31, back);


        p.openInventory(inv);
    }


    public static void clickValue(Player player, int slot, InventoryClickEvent e) {

        switch (slot) {
            case 10:
                if (e.getClickedInventory() == null) {
                    break;
                }
                if (e.isLeftClick()) {
                    if (!HostData.getInfoHostString("border_time").equals("60:00")){
                        String[] valuesBorderTime = HostData.getInfoHostString("border_time").split(":");


                        Integer newM = Integer.valueOf(valuesBorderTime[0]) + 1;



                        String new_border_time = newM + ":00";

                        HostData.setInfoHostString("border_time",  new_border_time);
                        ConfigHostBorder.open(player);
                    }
                }
                if (e.isRightClick()){
                    if (!HostData.getInfoHostString("border_time").equals("1:00")){
                        String[] valuesBorderTime = HostData.getInfoHostString("border_time").split(":");


                        Integer newM = Integer.valueOf(valuesBorderTime[0]) - 1;



                        String new_border_time = newM + ":00";

                        HostData.setInfoHostString("border_time",  new_border_time);
                        ConfigHostBorder.open(player);
                    }
                }
                break;
            case 12:
                if (e.getClickedInventory() == null) {
                    break;
                }
                if (e.isLeftClick()) {
                    if (!HostData.getInfoHostString("border_speed").equals("10")){


                        Float newValue = (float) (Float.valueOf(HostData.getInfoHostString("border_speed")) + 0.5);


                        HostData.setInfoHostString("border_speed",  String.valueOf(newValue));
                        ConfigHostBorder.open(player);
                    }
                }
                if (e.isRightClick()){
                    if (!HostData.getInfoHostString("border_speed").equals("0.5")){


                        Float newValue = (float) (Float.valueOf(HostData.getInfoHostString("border_speed")) - 0.5);


                        HostData.setInfoHostString("border_speed", String.valueOf(newValue));
                        ConfigHostBorder.open(player);
                    }
                }
                break;
            case 14:
                if (e.getClickedInventory() == null) {
                    break;
                }
                if (e.isLeftClick()) {
                    if (!HostData.getInfoHostString("border_start").equals("1500")){


                        Integer newValue = Integer.valueOf(HostData.getInfoHostString("border_start")) + 100;


                        HostData.setInfoHostString("border_start",  String.valueOf(newValue));

                        World world = Bukkit.getWorld("world");
                        WorldBorder wb = world.getWorldBorder();
                        wb.setSize(newValue * 2);

                        ConfigHostBorder.open(player);
                    }
                }
                if (e.isRightClick()){
                    if (!HostData.getInfoHostString("border_start").equals("300")){


                        Integer newValue = Integer.valueOf(HostData.getInfoHostString("border_start")) - 100;



                        HostData.setInfoHostString("border_start", String.valueOf(newValue));

                        World world = Bukkit.getWorld("world");
                        WorldBorder wb = world.getWorldBorder();
                        wb.setSize(newValue * 2);

                        ConfigHostBorder.open(player);
                    }
                }
                return;
            case 16:
                if (e.getClickedInventory() == null) {
                    break;
                }
                if (e.isLeftClick()) {
                    if (!HostData.getInfoHostString("border_end").equals("150")){


                        Integer newValue = Integer.valueOf(HostData.getInfoHostString("border_end")) + 10;


                        HostData.setInfoHostString("border_end",  String.valueOf(newValue));
                        ConfigHostBorder.open(player);
                    }
                }
                if (e.isRightClick()){
                    if (!HostData.getInfoHostString("border_end").equals("10")){


                        Integer newValue = Integer.valueOf(HostData.getInfoHostString("border_end")) - 10;


                        HostData.setInfoHostString("border_end", String.valueOf(newValue));
                        ConfigHostBorder.open(player);
                    }
                }
                break;
            case 31:
                ConfigHostRules.open(player);
                break;
            default:
                break;
        }
    }
}
