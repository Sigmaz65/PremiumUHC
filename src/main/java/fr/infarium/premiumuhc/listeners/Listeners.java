package fr.infarium.premiumuhc.listeners;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.commands.SaveInvCommands;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.manager.*;
import fr.infarium.premiumuhc.menu.*;
import fr.infarium.premiumuhc.mysql.HostData;
import fr.infarium.premiumuhc.mysql.PlayerInfo;
import fr.infarium.premiumuhc.tasks.StartLeaveHost;
import fr.infarium.premiumuhc.tasks.StartMining;
import fr.infarium.premiumuhc.utils.ItemBuilder;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Team;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Listeners implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        VarManager.initializeVar(p);
        Main.getInstance().scoreBoardPlayers.put(p, new FastBoard(p));
        TabHandler.setTab(p);

        HostData hostData = new HostData(ConfigManager.server_host);
        e.setJoinMessage("");
        ScoreManager.setScoreboard(p, ScoreManager.getPlayerScoreBoard(p));

        if (GameManager.isState(GameState.WAITING) || GameManager.isState(GameState.STARTING)){
            InvManager.clearInv(p);
            TeleportManager.teleportSpawn(p);
            ItemManager.giveNormalWaitingItem(p);

            Bukkit.broadcastMessage(TextManager.formatString(ConfigManager.join_message.replace("%player%", p.getName())));

            if (p.hasPermission("premiumuhc.host")){
                if (GameManager.getHostOwner().equals("§cEn attente...")){
                    p.sendMessage(TextManager.formatString(ConfigManager.be_host_message));
                    GameManager.setHostOwner(p);
                    ItemManager.giveHostWaitingItem(p);
                }
                if (VarManager.var_player_host.equals(p.getName())){
                    ItemManager.giveHostWaitingItem(p);
                }
            }
            if (!GameManager.getPlayers().contains(p)) GameManager.getPlayers().add(p);

            p.setGameMode(GameMode.ADVENTURE);
            SaveInvCommands.playersInGm.remove(p);
        }
        if (GameManager.isState(GameState.MINING) || GameManager.isState(GameState.PLAYING)){
            //pvp
            String[] timePvp = HostData.getInfoHostString("time_pvp").split(":");
            Integer minutePvp = Integer.valueOf(timePvp[0]);

            //bordure
            String[] timeBorder = HostData.getInfoHostString("border_time").split(":");
            Integer minuteBorder = Integer.valueOf(timeBorder[0]);
            if (HostData.getInfoHostString("reco_auto").equals("§aAutorisée")){
                if (minutePvp > 0){
                    if (minuteBorder > 0){
                        if (!GameManager.getPlayers().contains(p)) GameManager.getPlayers().add(p);
                        p.sendMessage(TextManager.formatString("%prefix% §fVous avez été reconnecté étant donné que le PvP ou la bordure n'ont pas été activés."));

                        Bukkit.broadcastMessage(TextManager.formatString(ConfigManager.join_message.replace("%player%", p.getName())));
                    } else {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(TextManager.formatString((ConfigManager.be_spectator_message)));
                        return;
                    }
                }
            }
            else{
                p.setGameMode(GameMode.SPECTATOR);
                p.sendMessage(TextManager.formatString((ConfigManager.be_spectator_message)));
            }
        }

        Integer num_player = GameManager.getPlayers().size();
        HostData.setInfoHostString("num_player", String.valueOf(num_player));
        VarManager.initializeServerVar();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        VarManager.unInitializeVar(p);
        Main.getInstance().scoreBoardPlayers.remove(p);
        if (GameManager.getPlayers().contains(p)) {

            GameManager.getPlayers().remove(p);

            e.setQuitMessage("");
            Bukkit.broadcastMessage(TextManager.formatString(ConfigManager.quit_message.replace("%player%", p.getName())));
            if (GameManager.getHostOwner().equals(p.getName())){

                if (GameManager.isState(GameState.STARTING) || GameManager.isState(GameState.WAITING))
                {
                    Bukkit.broadcastMessage(TextManager.formatString("%prefix% &fL'hôte de cette partie à quitter la partie, il a 2 minutes pour se reconnecter avant la suppression de celle-ci."));
                    StartLeaveHost leaveHost = new StartLeaveHost();
                    leaveHost.runTaskTimer(Main.getInstance(), 0, 20);
                }


            }
        }
        Integer num_player = GameManager.getPlayers().size();
        HostData.setInfoHostString("num_player", String.valueOf(num_player));
        VarManager.initializeServerVar();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if(GameManager.isState(GameState.WAITING) || GameManager.isState(GameState.STARTING)) {
            e.setCancelled(!e.getWhoClicked().getGameMode().equals(GameMode.CREATIVE));
            if (e.getClickedInventory() != null && e.getClickedInventory().getName().equalsIgnoreCase("§e§lMENU §7| §8Configuration Host")) {
                ConfigHost.clickValue((Player) e.getWhoClicked(), e.getSlot(), e);
                e.setCancelled(true);
                return;
            }
            if (e.getClickedInventory() != null && e.getClickedInventory().getName().equalsIgnoreCase("§e§lMENU §7| §8Règles de la partie")) {
                ConfigHostRules.clickValue((Player) e.getWhoClicked(), e.getSlot(), e);
                e.setCancelled(true);
                return;
            }
            if (e.getClickedInventory() != null && e.getClickedInventory().getName().equalsIgnoreCase("§e§lMENU §7| §8Gestion de la bordure")) {
                ConfigHostBorder.clickValue((Player) e.getWhoClicked(), e.getSlot(), e);
                e.setCancelled(true);
                return;
            }
            if (e.getClickedInventory() != null && e.getClickedInventory().getName().equalsIgnoreCase("§e§lMENU §7| §8Gestion des drops")) {
                ConfigHostDrops.clickValue((Player) e.getWhoClicked(), e.getSlot(), e);
                e.setCancelled(true);
                return;
            }
            if (e.getClickedInventory() != null && e.getClickedInventory().getName().equalsIgnoreCase("§e§lMENU §7| §8Scénarios")) {
                ConfigHostSc.clickValue((Player) e.getWhoClicked(), e.getSlot(), e);
                e.setCancelled(true);
                return;
            }
            if (e.getClickedInventory() != null && e.getClickedInventory().getName().equalsIgnoreCase("§e§lMENU §7| §8Réglages de la partie")) {
                ConfigHostAuto.clickValue((Player) e.getWhoClicked(), e.getSlot(), e);
                e.setCancelled(true);
                return;
            }
            if (e.getClickedInventory() != null && e.getClickedInventory().getName().equalsIgnoreCase("§e§lMENU §7| §8Mes configurations")) {
                ConfigHostSave.clickValue((Player) e.getWhoClicked(), e.getSlot(), e);
                e.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack it = e.getItem();

        if (!SaveInvCommands.playersInGm.containsKey(player)) {
            if (it != null) {
                if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                    if (it.getType() == Material.REDSTONE_BLOCK) {

                        if (GameManager.isState(GameState.STARTING) || GameManager.isState(GameState.WAITING))
                            ConfigHost.open(player);
                    }
                    if (it.getType() == Material.DARK_OAK_DOOR_ITEM) {
                        if (GameManager.isState(GameState.STARTING) || GameManager.isState(GameState.WAITING)) {
                            //tp lobby
                            ByteArrayOutputStream b = new ByteArrayOutputStream();
                            DataOutputStream out = new DataOutputStream(b);

                            try {
                                out.writeUTF("Connect");
                                out.writeUTF(ConfigManager.server_lobby);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                            }

                            player.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
                        }
                    }
                    if (it.getType() == Material.NETHER_STAR){
                        if (GameManager.isState(GameState.STARTING) || GameManager.isState(GameState.WAITING)) {
                            new TeamsGUI(player.getUniqueId()).open(true);
                        }
                    }
                }
            }
        }else{
            if (action == Action.RIGHT_CLICK_AIR ||
                    action == Action.RIGHT_CLICK_BLOCK ||
                    action == Action.LEFT_CLICK_AIR ||
                    action == Action.LEFT_CLICK_BLOCK)
            {
                e.setCancelled(true);
            }
        }
    }
}
