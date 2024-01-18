package fr.infarium.premiumuhc.menu;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.manager.TextManager;
import fr.infarium.premiumuhc.mysql.HostData;
import fr.infarium.premiumuhc.utils.ItemBuilder;
import fr.kotlini.supragui.bases.MultiGUI;
import fr.kotlini.supragui.classes.Filler;
import fr.kotlini.supragui.classes.SlotPosition;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TeamsGUI extends MultiGUI {
    public TeamsGUI(UUID uuid) {
        super(uuid, "§e§lHOST §7| §8Sélecteur d'équipe", 9 * 6, 3, new Filler(new SlotPosition(1, 1), new SlotPosition(7 ,4), 9*6, true),
                false);
    }

    @Override
    public void putItems() {

        int teamSize = Integer.parseInt(HostData.getInfoHostString("size_teams"));

        /* for(TeamList team : TeamList.getActivesTeams()) {

            List<String> lore = new ArrayList<>();
            lore.add("");
            // lore.add("§7Joueurs :");
            int place = teamSize;
            for(Player player : PlayerTeamHandler.getInstance().getPlayersInTeam(team)) {
                lore.add(" §7● " + player.getDisplayName());
                place--;
            }
            for(int i = 0; i < place; i++) {
                lore.add(" §7● §8Place libre");
            }
            lore.add("");
            lore.add("§6§l» §eCliquez pour rejoindre");

            addFillerItem(new ItemBuilder(Material.WOOL, 1, TeamList.getColor(team)).setDisplayName(TeamList.getDisplayName(team) + " §7(" + PlayerTeamHandler.getInstance().getPlayersInTeam(team).size() + "/" + teamSize + ")")

                    .setLoreWithList(lore).build(false), e -> {

                if(team == null || !Main.getInstance().activesTeams.contains(team)) {
                    new TeamsGUI(getPlayer().getUniqueId()).open(true);
                    return;
                }

                if(PlayerTeamHandler.getInstance().getTeamByPlayer(getPlayer()) == team) {
                    getPlayer().sendMessage(TextManager.formatString("%prefix% &cErreur: Vous êtes déjà dans cette équipe, un seul " + getPlayer().getName() + " suffit amplement à celle-ci."));
                    return;
                }

                if(PlayerTeamHandler.getInstance().getPlayersInTeam(team).size() == teamSize) {
                    getPlayer().sendMessage(TextManager.formatString("%prefix% &cErreur: L'équipe que vous essayez de rejoindre est déjà complète, rejoignez en une autre ;)"));
                    return;
                }

                getPlayer().sendMessage(TextManager.formatString("%prefix% &fVous avez rejoint l'équipe ") + TeamList.getDisplayName(team));
                PlayerTeamHandler.getInstance().setPlayerToTeam(getPlayer(), team);
                new TeamsGUI(getPlayer().getUniqueId()).open(true);
            });
        }

        setPatternItem(new SlotPosition(4, 5).toSlot(), new ItemBuilder(Material.DROPPER).setDisplayName("§aAléatoire").build(false), e -> {
            if(PlayerTeamHandler.getInstance().getTeamByPlayer(getPlayer()) != null) {
                PlayerTeamHandler.getInstance().setPlayerToRandom(getPlayer());
                getPlayer().sendMessage(TextManager.formatString("%prefix% &aMode aléatoire sélectionné."));
                new TeamsGUI(getPlayer().getUniqueId()).open(true);
            } else {
                getPlayer().sendMessage(TextManager.formatString("%prefix% &cVous êtes déjà en mode aléatoire."));
            }
        });

        setPatternItems(getCorners(), new ItemBuilder(Material.STAINED_GLASS_PANE, 1,
                PlayerTeamHandler.getInstance().playersTeam.containsKey(getPlayer()) ? TeamList.getColor(PlayerTeamHandler.getInstance().playersTeam.get(getPlayer()))
                        : 0
                ).setDisplayName("§r").build(false), e -> {});
        */
    }
}