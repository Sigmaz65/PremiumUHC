package fr.infarium.premiumuhc.menu;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.manager.*;
import fr.infarium.premiumuhc.mysql.HostData;
import fr.infarium.premiumuhc.tasks.AutoStart;
import fr.infarium.premiumuhc.team.UpdateTeams;
import fr.infarium.premiumuhc.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import javax.swing.*;
import java.util.Arrays;

public class ConfigHost {

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
        String num_teams = HostData.getInfoHostString("num_teams");
         String size_teams = HostData.getInfoHostString("size_teams");
         String host_status = HostData.getInfoHostString("server_status");
         String display_name = TextManager.formatString(HostData.getInfoHostString("display_name"));

         ItemStack wool = new ItemBuilder(Material.WOOL).setDisplayName("§aNombre d'équipes").setLoreWithList(Arrays.asList("§7Modifier le nombre d'équipe dans", "§7la partie.", "§7", "§7Nombre d'équipes: §b" + num_teams, "§7", "§eClique gauche: §a+1 équipe", "§eClique droit: §c-1 équipe")).build(false);
         ItemStack skull_simple = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setDisplayName("§aTailles des équipes").setLoreWithList(Arrays.asList("§7Modifier le nombre de joueurs", "§7par équipes.", "§7", "§7Taille des équipes: §b" + size_teams, "§7", "§eClique gauche: §a+1 joueur", "§EClique droit: §c-1 joueur")).build(false);
         ItemStack nether_star = new ItemBuilder(Material.NETHER_STAR).setDisplayName("§aRègles de la partie").setLoreWithList(Arrays.asList("§7Modifier les règles de votre", "§7partie afin de la rendre la plus", "§7originale possible ;)", "§7", "§6§l» §eCliquez pour accéder !")).build(false);
         ItemStack writable_book = new ItemBuilder(Material.WRITTEN_BOOK).setDisplayName("§aGérer les scénarios").setLoreWithList(Arrays.asList("§7Activer / Désactiver les Scénarios", "§7de votre partie !", "§7", "§6§l» §eClique pour gérer les Scénarios !")).build(false);

         ItemStack emerald = new ItemBuilder(Material.EMERALD_BLOCK).setDisplayName("§aDémarrer la partie").setLoreWithList(Arrays.asList("§7Permet de lancer le compte à rebours", "§7permettant de démarrer la partie ! Vous", "§7pouvez re-cliquer dessus pour annuler", "§7le départ.", "§7", "§6§l» §eClique pour démarrer la partie !")).build(false);
         ItemStack noteblock = new ItemBuilder(Material.NOTE_BLOCK).setDisplayName("§6Annonce dans les lobbys").setLoreWithList(Arrays.asList("§7Cela enverra un message dans tous", "§7les lobbys pour promouvoir votre partie !", "§7", "§6§l» §eCliquez pour lancer l'annonce !")).build(false);
         ItemStack iron_door = new ItemBuilder(Material.IRON_DOOR).setDisplayName("§aStatut de la partie").setLoreWithList(Arrays.asList("§7Cela permet aux joueurs de", "§7rejoindre la partie lorsqu'elle", "§7est ouverte, ou d'empêcher les", "§7joueurs de se connecter.", "§7", "§7Status actuel: " + host_status, "§7", "§6§l» §eCliquez pour modifier !")).build(false);
         ItemStack comparator = new ItemBuilder(Material.REDSTONE_COMPARATOR).setDisplayName("§cRéglages des autorisations").setLoreWithList(Arrays.asList("§7Autorisez les spectateurs ou changez", "§7la règle pour les reconnexions en", "§7cours de partie.", "§7", "§6§l» §eCliquez pour accéder !")).build(false);
         ItemStack hopper = new ItemBuilder(Material.HOPPER).setDisplayName("§aSauvegardes de mes configurations").setLoreWithList(Arrays.asList("§7Chargez ou sauvegarder votre configuration", "§7de partie actuelle afin de pouvoir", "§7recharger tous vos paramètres la", "§7prochaine fois !", "§7", "§6§l» §eCliquez pour accéder !")).build(false);
         ItemStack anvil = new ItemBuilder(Material.ANVIL).setDisplayName("§aNommer la partie").setLoreWithList(Arrays.asList("§7Changer le nom de la partie", "§7qui apparaîtra dans le lobby.", "§7", "§7Nom actuel:", display_name, "§c", "§6§l» §eCliquez pour le modifier !")).build(false);
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





        ItemStack skull = new ItemStack(Material.SKULL_ITEM, Integer.parseInt(num_player), (byte)3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(player_host);
        meta.setDisplayName("§a§lHOST-1 §7| " + display_name);
        meta.setLore(Arrays.asList(
                "§c",
                "§7Hôte: §b" + player_host,
                "§7Joueurs: §a" + num_player + "§7/" + max_player,
                "§7Status: " + server_status,
                "",
                "§7Taille Bordure:",
                " §7§7• §7Début: §a" + border_start,
                " §7§7• §7Fin: §a" + border_end,
                "§7Vitesse Bordure: §a " + border_speed + " blocs/s",
                "",
                "§7Temps:",
                " §7§7• §7PVP: §e" + time_pvp,
                " §7§7• §7Bordure: §e" + border_time,
                "",
                "§6§l↪ §eClique pour rejoindre"));

        skull.setItemMeta(meta);



        // set items
        Inventory inv = Bukkit.createInventory(null, 9 * 6, "§e§lMENU §7| §8Configuration Host");

        inv.setItem(4, skull);

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

        inv.setItem(10, wool);
        inv.setItem(12, skull_simple);
        inv.setItem(14, nether_star);
        inv.setItem(16, writable_book);
        inv.setItem(28, iron_door);
        inv.setItem(30, noteblock);
        inv.setItem(32, comparator);
        inv.setItem(34, hopper);
        inv.setItem(49, emerald);
        inv.setItem(22, anvil);


        p.openInventory(inv);
    }


    public static void clickValue(Player player, int slot, InventoryClickEvent e) {

        switch (slot) {
            case 10:
                // Num Teams
                if (e.getClickedInventory() == null) {
                    break;
                }
                if (e.isLeftClick()) {
                    if (HostData.getInfoHostString("num_teams").equalsIgnoreCase("16")){
                        player.sendMessage(TextManager.formatString(ConfigManager.team_limit_message));
                    }else{
                        Integer num_teams_actual = Integer.valueOf(HostData.getInfoHostString("num_teams"));
                        Integer new_num_teams = num_teams_actual + 1;

                        HostData.setInfoHostString("num_teams", String.valueOf(new_num_teams));
                        open(player);
                    }
                    VarManager.initializeServerVar();
                    UpdateTeams.updateTeams();
                    break;
                } else if (e.isRightClick()) {
                    if (HostData.getInfoHostString("num_teams").equalsIgnoreCase("2")){
                        player.sendMessage(TextManager.formatString(ConfigManager.min_team_message));
                    }else{
                        Integer num_teams_actual = Integer.valueOf(HostData.getInfoHostString("num_teams"));
                        Integer new_num_teams = num_teams_actual - 1;

                        HostData.setInfoHostString("num_teams", String.valueOf(new_num_teams));
                        open(player);
                    }
                    VarManager.initializeServerVar();
                    UpdateTeams.updateTeams();
                    break;
                }
                VarManager.initializeServerVar();
                break;
            case 12:
                // size Teams
                if (e.getClickedInventory() == null) {
                    break;
                }
                if (e.isLeftClick()) {
                    if (HostData.getInfoHostString("size_teams").equalsIgnoreCase("4")){
                        player.sendMessage(TextManager.formatString(ConfigManager.player_limit_per_team_message));
                    }else{
                        Integer size_teams_actual = Integer.valueOf(HostData.getInfoHostString("size_teams"));
                        Integer new_size_team = size_teams_actual + 1;

                        HostData.setInfoHostString("size_teams", String.valueOf(new_size_team));
                        open(player);
                    }
                    VarManager.initializeServerVar();
                    UpdateTeams.updateTeams();
                    break;
                } else if (e.isRightClick()) {
                    if (HostData.getInfoHostString("size_teams").equalsIgnoreCase("1")){
                        player.sendMessage(TextManager.formatString(ConfigManager.min_player_per_team_message));
                    }else{
                        Integer size_teams_actual = Integer.valueOf(HostData.getInfoHostString("size_teams"));
                        Integer new_size_team = size_teams_actual - 1;

                        HostData.setInfoHostString("size_teams", String.valueOf(new_size_team));
                        open(player);
                    }
                    VarManager.initializeServerVar();
                    UpdateTeams.updateTeams();
                    break;
                }
                VarManager.initializeServerVar();
                break;
            case 14:
                ConfigHostRules.open(player);
                return;
            case 16:
                ConfigHostSc.open(player);
                break;
            case 21:
                player.sendMessage("ok");
                break;
            case 22:
                player.closeInventory();
                player.sendMessage("§c");
                player.sendMessage("§a§lEntrez le nom de la partie (code couleurs acceptés):");
                player.sendMessage("§7(Entrez 'Annuler' pour revenir au tchat)");

                VarManager.setNameGame = true;
                break;
            case 28:
                if (HostData.getInfoHostString("server_status").equalsIgnoreCase("§dEn attente de joueurs...")){
                    HostData.setInfoHostString("server_status", "§cFermé");
                } else if (HostData.getInfoHostString("server_status").equalsIgnoreCase("§cFermé")) {
                    HostData.setInfoHostString("server_status", "§bDemander pour rejoindre");
                } else if (HostData.getInfoHostString("server_status").equalsIgnoreCase("§bDemander pour rejoindre")) {
                    HostData.setInfoHostString("server_status", "§dEn attente de joueurs...");
                }
                ConfigHost.open(player);
                break;
            case 32:
                ConfigHostAuto.open(player);
                break;
            case 34:
                ConfigHostSave.open(player);
                break;
            case 49:
                if (GameManager.getPlayers().size() >= 2) {
                    player.sendMessage(TextManager.formatString(ConfigManager.info_host_start_message));
                    GameManager.setState(GameState.STARTING);
                    for (Player pls : GameManager.getPlayers()) {
                        ScoreManager.setScoreboard(pls, GameState.STARTING);
                    }
                    AutoStart start = new AutoStart();
                    start.runTaskTimer(Main.getInstance(), 0, 20);
                }
                else{
                    player.sendMessage(TextManager.formatString(ConfigManager.not_enough_players_message));
                }
                break;
            default:
                break;
        }
    }
}
