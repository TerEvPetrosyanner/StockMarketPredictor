package Owner;
import DataReading.DataReader;
import Exceptions.FailedTransactionException;
import Market.Market;
import Utility.CustomSlowMap;
import Tradable.Tradable;
import Utility.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;


public class Owner{
    private String name;
    private ArrayList<Tradable> assets;
    private Money netWorth;

    public Owner(){
        this("John Doe", new ArrayList<Tradable>(), new Money(new BigDecimal(10000), "USD"));
    }
    public Owner(String name, ArrayList<Tradable> assets, Money netWorth){
        this.name = name;
        if(assets != null) {
            this.assets = new ArrayList<Tradable>(assets);
        }
        this.netWorth = netWorth;
    }


    public BigDecimal predict(int year){
        BigDecimal res = netWorth.getValueInMoney();
        for(int i = 0; i<year; i++){
            if(i%3 == 0) {
                Market.Event event = new Market.Event();
                event.predictAffect(res);
            }
            res = res.add(res.multiply(BigDecimal.valueOf(5.0 / 100)));
        }
        return res;
    }

    public boolean addNetWorth(Money addition){
        if(netWorth.calculateAddition(addition).signum() == 1){
            netWorth.add(addition);
            return true;
        }
        return false;
    }
    public Money getNetWorth(){
        return new Money(netWorth);
    }
    public boolean removeTradableById(int id){
        for(int i = 0; i<assets.size(); i++){
            if(assets.get(i).getMyID() == id) {
                assets.remove(i);
                return true;
            }
        }
        return false;
    }
    public Tradable findTradableByID(int id){
        for(int i = 0; i<assets.size(); i++){
            if(assets.get(i).getMyID() == id) {
                return (Tradable) assets.get(i).clone();
            }
        }
        return null;
    }


    public void sell(int tradableId, String date) throws FailedTransactionException {
        Market.Transaction t = new Market.Transaction(this, tradableId, date, Market.Transaction.TransactionType.SELL);
        if(t.processTransaction()){
            System.out.println("selling");

            System.out.println("Transaction approved: " + t.toString());
            DataReader.addTransaction(t);

        } else {
            throw new FailedTransactionException("Transaction failed: " + t);
        }
    }
    public void addAsset(Tradable newAsset){
        assets.add(newAsset);
    }
    public String getName(){
        return this.name;
    }
    public ArrayList<Tradable> getAssets(){return this.assets;}
}
