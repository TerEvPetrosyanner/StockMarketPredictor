package Tradable;

import Owner.Owner;
import java.util.Arrays;
import java.util.UUID;


public abstract class Tradable {
    UUID ownerId;
    UUID[] history;

    public Tradable(){
        this(null, null);
    }

    public Tradable(UUID id, UUID[] history){
        this.ownerId = id;
        if(history != null){
            this.history = Arrays.copyOf(history, history.length);
        }
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    private void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public UUID[] getHistory(){
        return  Arrays.copyOf(this.history, this.history.length);
    }

    //public abstract void staking(double percentage, int year);

    private void addHistory(){

    }

}
