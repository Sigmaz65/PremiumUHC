package fr.infarium.premiumuhc.mysql;

import com.sun.org.apache.xpath.internal.operations.Bool;
import fr.infarium.premiumuhc.manager.ConfigManager;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScenariosData {
    private static String serverName = ConfigManager.server_host;
    public static Integer intvalue = 0;


    public static Integer getInfoScInt(Integer scenario_id){
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("SELECT activated FROM scenarios WHERE host_server = ? AND scenario_id = ?");
            preparedStatement.setString(1, serverName);
            preparedStatement.setInt(2, scenario_id);

            ResultSet rs = preparedStatement.executeQuery();
            int getedInt = -2;

            while (rs.next()){
                getedInt = rs.getInt("activated");
            }

            preparedStatement.close();
            return getedInt;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static String getScString(String scenario_id){
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("SELECT activated FROM scenarios WHERE host_server = ? AND scenario_id = ?");
            preparedStatement.setString(1, serverName);
            preparedStatement.setString(2, scenario_id);

            ResultSet rs = preparedStatement.executeQuery();
            String getedString = "0";

            while (rs.next()){
                getedString = rs.getString("activated");
            }

            preparedStatement.close();
            return getedString;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return "0";
    }

    public static void setInfoScString(String info, String value, String scenario_id){
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("UPDATE scenarios SET " + info + " = ? WHERE host_server = ? AND scenario_id = ?");
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, serverName);
            preparedStatement.setString(3, scenario_id);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void removeAllScenarios(){
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("UPDATE scenarios SET activated = ? WHERE host_server = ?");
            preparedStatement.setString(1, "0");
            preparedStatement.setString(2, serverName);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void SelectAllScenarios(){
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("UPDATE scenarios SET activated = ? WHERE host_server = ?");
            preparedStatement.setString(1, "1");
            preparedStatement.setString(2, serverName);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void insertMissingScenarios() {
        try {
            for (int i = 0; i <= 7; i++) {
                if (!scenarioExists(i)) {
                    insertScenario(i);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean scenarioExists(int scenarioId) throws SQLException {
        String checkScenarioQuery = "SELECT COUNT(*) FROM scenarios WHERE scenario_id = ? AND host_server = ?";
        PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement(checkScenarioQuery);
        preparedStatement.setInt(1, scenarioId);
        preparedStatement.setString(2, ConfigManager.server_host);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        resultSet.close();
        preparedStatement.close();
        return count > 0;
    }

    public static void insertScenario(int scenarioId) throws SQLException {
        String insertScenarioQuery = "INSERT INTO scenarios (scenario_id, host_server, activated) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement(insertScenarioQuery);
        preparedStatement.setInt(1, scenarioId);
        preparedStatement.setString(2, ConfigManager.server_host); // server host
        preparedStatement.setInt(3, 0); // scenario enable ou non
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public static int CountScenarios() {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) FROM scenarios WHERE host_server = ?";
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement(query);
            preparedStatement.setString(1, ConfigManager.server_host);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
