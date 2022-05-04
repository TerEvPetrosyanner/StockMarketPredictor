package Tradable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;


public abstract class Tradable {
    UUID ownerId;
    UUID[] history;
    //To keep track of Tradable ID's
    private static int lastId = 0;
    private int myID;

    public enum TradableType {
        Crypto,
        Money,
        RealEstate
    }

    public int getMyID(){
        return this.myID;
    }

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

    public abstract BigDecimal getValueInMoney();

    public abstract void updatePrice(BigDecimal newValue);

    public abstract String getType();

    //public abstract int getID();

}
