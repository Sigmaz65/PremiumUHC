package fr.infarium.premiumuhc.team;

import fr.infarium.premiumuhc.Main;
import fr.infarium.premiumuhc.mysql.HostData;

public class UpdateTeams {

    public static void updateTeams(){
        Main.getInstance().activesTeams.clear();

        int num_teams = Integer.valueOf(HostData.getInfoHostString("num_teams"));

        byte currentIndex = 0;

        while (currentIndex < num_teams) {
            TeamList team = TeamList.getTeamByColor(currentIndex);
            Main.getInstance().activesTeams.add(team);
            currentIndex++;
        }

        TeamList.registerTeams();
        PlayerTeamHandler.getInstance().clearTeams();
    }

}
