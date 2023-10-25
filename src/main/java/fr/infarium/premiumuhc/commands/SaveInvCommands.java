package fr.infarium.premiumuhc.commands;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.manager.*;
import fr.infarium.premiumuhc.menu.ConfigHost;
import fr.infarium.premiumuhc.menu.ConfigHostAnvil;
import fr.infarium.premiumuhc.utils.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class SaveInvCommands implements CommandExecutor {
    public static Map<Player, ItemStack[]> savedInventories = new HashMap<>();
    public static Map<Player, ItemStack[]> savedArmors = new HashMap<>();
    public static Map<Player, GameMode> playersInGm = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (!(s instanceof Player)) {
            return true;
        }

        Player player = (Player) s;

        if (cmd.getName().equalsIgnoreCase("finish")) {
            if (player.getName().equals(GameManager.getHostOwner())) {
                if (playersInGm.containsKey(player)) {
                    savedInventories.put(player, player.getInventory().getContents());
                    savedArmors.put(player, player.getInventory().getArmorContents());

                    //clear
                    player.getInventory().clear();
                    player.getInventory().setArmorContents(null);

                    ItemStack redstone = new ItemBuilder(Material.REDSTONE_BLOCK, 1).setDisplayName("§c§lConfiguration Host §f| Clique").build(false);
                    ItemStack doorleave = new ItemBuilder(Material.DARK_OAK_DOOR_ITEM, 1).setDisplayName("§c§lRetour au lobby §f| Clique").build(false);

                    player.getInventory().setItem(4, redstone);
                    player.getInventory().setItem(8, doorleave);

                    player.updateInventory();

                    player.sendMessage("§e§lHOST §7§l» §aInventaire de départ sauvegardé avec succès !");
                    // player.sendMessage(Arrays.toString(player.getInventory().getContents()) + Arrays.toString(player.getInventory().getArmorContents()));


                    player.setGameMode(GameMode.ADVENTURE);
                    playersInGm.remove(player);

                    ConfigHost.open(player);
                } else {
                    player.sendMessage("§e§lHOST §7§l» §cVous devez être en mode édition pour sauvegarder l'inventaire");
                }
            }
            else{
                if (playersInGm.containsKey(player)) {
                    player.getInventory().clear();
                    player.setGameMode(GameMode.ADVENTURE);
                    ItemManager.giveNormalWaitingItem(player);

                    player.updateInventory();
                }else{
                    player.sendMessage(TextManager.formatString("%prefix% &cVous devez être en mode inspection de l'inventaire de départ pour faire cela."));
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("enchant")) {
            if(GameManager.isState(GameState.WAITING)) {
                if (player.getName().equals(GameManager.getHostOwner())) {
                    if (playersInGm.containsKey(player)) {
                        ConfigHostAnvil.openAnvilInventory(player);
                    } else {
                        player.sendMessage("§e§lHOST §7§l» §cVous devez être en mode édition pour enchanter un objet");
                    }
                } else {
                    player.sendMessage(TextManager.formatString(ConfigManager.no_host_permission_message));
                }
            }else{
                player.sendMessage(TextManager.formatString("%prefix% &cVous ne pouvez pas faire cela, la partie a déjà commencé."));
            }
        }

        return true;
    }
}

