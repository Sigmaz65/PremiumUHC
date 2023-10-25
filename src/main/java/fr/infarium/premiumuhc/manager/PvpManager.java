package fr.infarium.premiumuhc.manager;

public class PvpManager {
    static boolean statepvp = false;
    public static void setPvp(boolean statePvp){
        statepvp = statePvp;
    }
    public static boolean hasPvp(){
        return statepvp;
    }
}
