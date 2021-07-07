package me.aglerr.playerprofiles.manager.profile;

public class Profile {

    private final String uuid;
    private boolean locked = false;

    public Profile(String uuid){
        this.uuid = uuid;
    }

    public Profile(String uuid, boolean lockedStatus){
        this.uuid = uuid;
        this.locked = lockedStatus;
    }

    public String getUUID(){
        return uuid;
    }

    public boolean isLocked(){
        return locked;
    }

    public void setLocked(boolean status){
        this.locked = status;
    }

}
