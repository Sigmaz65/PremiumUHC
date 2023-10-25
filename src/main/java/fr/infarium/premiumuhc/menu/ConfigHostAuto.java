package fr.infarium.premiumuhc.menu;

import fr.infarium.premiumuhc.manager.ConfigManager;
import fr.infarium.premiumuhc.manager.DataManager;
import fr.infarium.premiumuhc.mysql.HostData;
import fr.infarium.premiumuhc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ConfigHostAuto {
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


        //string
        String spec_auto = HostData.getInfoHostString("spec_auto");
        String reco_auto = HostData.getInfoHostString("reco_auto");

        //items
        ItemStack spec;
        if (spec_auto.equals("§aAutorisés")){
            spec = new ItemBuilder(Material.EMERALD_BLOCK).setDisplayName("§aSpectateurs").setLoreWithList(Arrays.asList(
                            "§7Autorisez ou non les spectateurs",
                            "§7durant la partie",
                            "§7Réglage: " + spec_auto,
                            "§7",
                            "§6§l» §eCliquez pour changer !"
                    ))
                    .build(false);
        } else{
            spec = new ItemBuilder(Material.REDSTONE_BLOCK).setDisplayName("§aSpectateurs").setLoreWithList(Arrays.asList(
                            "§7Autorisez ou non les spectateurs",
                            "§7durant la partie",
                            "§7Réglage: §cInterdits",
                            "§7",
                            "§6§l» §eCliquez pour changer !"
                    ))
                    .build(false);
        }

        ItemStack reco;
        if (reco_auto.equals("§aAutorisée")){
            reco = new ItemBuilder(Material.EMERALD_BLOCK).setDisplayName("§aReconnexions").setLoreWithList(Arrays.asList(
                            "§7Autorisez ou non la reconnexion",
                            "§7à la partie (si un joueur crash par",
                            "§7exemple) tant que le pvp ou la bordure",
                            "§7n'est pas activée !",
                            "§7Réglage: " + reco_auto,
                            "§7",
                            "§6§l» §eCliquez pour changer !"
                    ))
                    .build(false);
        } else{
            reco = new ItemBuilder(Material.REDSTONE_BLOCK).setDisplayName("§aReconnexions").setLoreWithList(Arrays.asList(
                            "§7Autorisez ou non la reconnexion",
                            "§7à la partie (si un joueur crash par",
                            "§7exemple) tant que le pvp ou la bordure",
                            "§7n'est pas activée !",
                            "§7Réglage: §cInterdite",
                            "§7",
                            "§6§l» §eCliquez pour changer !"
                    ))
                    .build(false);
        }



        ItemStack back = new ItemBuilder(Material.ARROW).setDisplayName("§aRetour").build(false);



        // set items
        Inventory inv = Bukkit.createInventory(null, 9 * 4, "§e§lMENU §7| §8Réglages de la partie");


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


        inv.setItem(12, spec);
        inv.setItem(14, reco);


        inv.setItem(31, back);


        p.openInventory(inv);
    }


    public static void clickValue(Player player, int slot, InventoryClickEvent e) {

        switch (slot) {
            case 12:
                if (HostData.getInfoHostString("spec_auto").equals("§aAutorisés")){
                    HostData.setInfoHostString("spec_auto", "§cInterdits");
                } else{
                    HostData.setInfoHostString("spec_auto", "§aAutorisés");
                }
                ConfigHostAuto.open(player);
                break;
            case 14:
                if (HostData.getInfoHostString("reco_auto").equals("§aAutorisée")){
                    HostData.setInfoHostString("reco_auto", "§cInterdite");
                } else{
                    HostData.setInfoHostString("reco_auto", "§aAutorisée");
                }
                ConfigHostAuto.open(player);
                break;
            case 31:
                ConfigHost.open(player);
                break;
            default:
                break;
        }
    }
}
