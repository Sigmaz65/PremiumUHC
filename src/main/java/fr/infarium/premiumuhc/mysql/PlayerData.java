package fr.infarium.premiumuhc.mysql;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerData {
    private static UUID uuid;

    public PlayerData(UUID uuid){
        this.uuid = uuid;
    }

    public static void addUhcXp(float count){
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("UPDATE players SET uhc_xp = uhc_xp + ? WHERE uuid_player = ?");
            preparedStatement.setFloat(1, count);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void removeUhcXP(float count){
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("UPDATE players SET uhc_xp = uhc_xp - ? WHERE uuid_player = ?");
            preparedStatement.setFloat(1, count);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Float getUhcXP(){
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("SELECT uhc_xp FROM players WHERE uuid_player = ?");
            preparedStatement.setString(1, uuid.toString());

            ResultSet rs = preparedStatement.executeQuery();
            float uhc_xp = 0f;

            while (rs.next()){
                uhc_xp = rs.getFloat("uhc_xp");
            }

            preparedStatement.close();
            return  uhc_xp;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0.0f;
    }

    public static String getInfoPlayerString(String col, Player player){
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("SELECT " + col + " FROM players WHERE pseudo_player = ?");
            preparedStatement.setString(1, player.getName());

            ResultSet rs = preparedStatement.executeQuery();
            String getedString = "null";

            while (rs.next()){
                getedString = rs.getString(col);
            }

            preparedStatement.close();
            return getedString;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return "null";
    }

    public static void setInfoPlayerString(String info, String value, Player player){
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("UPDATE players SET " + info + " = ? WHERE pseudo_player = ?");
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, player.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
