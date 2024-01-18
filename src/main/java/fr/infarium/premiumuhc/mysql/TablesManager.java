package fr.infarium.premiumuhc.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TablesManager {
    public static void testTables(){
        if (!tableExists("scenarios")){
            createScenariosTable();
        }
        if (!tableExists("servers_uhc_classique")){
            createServers_Uhc_Classique();
        }
    }
    public static boolean tableExists(String tableName) {
        try {
            String checkTableQuery = "SHOW TABLES LIKE ?";
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement(checkTableQuery);
            preparedStatement.setString(1, tableName);
            boolean result = preparedStatement.executeQuery().next();
            preparedStatement.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void createScenariosTable() {
        try {
            String createTableQuery = "CREATE TABLE scenarios (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "scenario_id INT NOT NULL, " +
                    "host_server VARCHAR(255) NOT NULL, " +
                    "activated INT NOT NULL)";
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement(createTableQuery);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createServers_Uhc_Classique(){
        try {
            String createTableQuery = "CREATE TABLE `servers_uhc_classique` (" +
                    " `id` int NOT NULL AUTO_INCREMENT," +
                    " `server_name` varchar(255) NOT NULL," +
                    " `num_player` int NOT NULL," +
                    " `num_teams` int NOT NULL," +
                    " `size_teams` int NOT NULL," +
                    " `max_player` int NOT NULL," +
                    " `player_host` varchar(255) NOT NULL," +
                    " `server_status` varchar(255) NOT NULL," +
                    " `border_time` varchar(255) NOT NULL," +
                    " `border_start` int NOT NULL," +
                    " `border_end` int NOT NULL," +
                    " `border_speed` float NOT NULL," +
                    " `time_pvp` varchar(255) NOT NULL," +
                    " `rule_time` varchar(255) NOT NULL," +
                    " `rule_gm` varchar(255) NOT NULL," +
                    " `rule_nether` varchar(255) NOT NULL," +
                    " `drop_apple` int NOT NULL," +
                    " `drop_silex` int NOT NULL," +
                    " `reco_auto` varchar(255) NOT NULL," +
                    " `spec_auto` varchar(255) NOT NULL," +
                    " `scenarios` varchar(255) NOT NULL," +
                    " PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement(createTableQuery);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

