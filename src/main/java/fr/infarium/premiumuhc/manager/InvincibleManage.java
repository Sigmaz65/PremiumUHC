package fr.infarium.premiumuhc.manager;

public class InvincibleManage {
    public static boolean invincibleState = true;

    public static boolean isInvincibleState(){
        return invincibleState;
    }

    public static void setInvincibleState(boolean aState){
        invincibleState = aState;
    }
}
