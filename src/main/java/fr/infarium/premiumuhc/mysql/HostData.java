package fr.infarium.premiumuhc.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HostData {
    private static String serverName;

    public HostData(String serverName){
        this.serverName = serverName;
    }
    public static String getInfoHostString(String col){
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("SELECT " + col + " FROM servers_uhc_classique WHERE server_name = ?");
            preparedStatement.setString(1, serverName);

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

    public static void setInfoHostString(String info, String value){
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("UPDATE servers_uhc_classique SET " + info + " = ? WHERE server_name = ?");
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, serverName);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
