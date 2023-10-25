package fr.infarium.premiumuhc.manager;

import me.clip.placeholderapi.PlaceholderAPI;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

public class TabHandler {

    public static void setTab(Player player){
        sendTabList(player,

                "\n§6§l               Saiteuria UHC               " +
                        "\n§e§lServeur HOST Français" +
                        "\n",

                ("\n§6" + ConfigManager.server_host + " §e%ping%ms" +
                        "\n§e%online_total% §fjoueurs §7(%online_here% joueurs ici)" +
                        "\n§6play.Saiteuria.fr" +
                        "\n")
                        .replace("%ping%", String.valueOf(((CraftPlayer) player).getHandle().ping))
                        .replace("%online_total%", PlaceholderAPI.setPlaceholders(player, "%bungee_total%"))
                        .replace("%online_here%", String.valueOf(Bukkit.getOnlinePlayers().size())));
    }

    private static void sendTabList(Player p, String Title, String subTitle)
    {
        IChatBaseComponent tabTitle = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + Title+ "\"}");
        IChatBaseComponent tabSubTitle = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + subTitle + "\"}");

        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter(tabTitle);

        try {
            Field field = packet.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(packet, tabSubTitle);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
        }
    }
}
