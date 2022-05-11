
package Market;

import Exceptions.FailedTransactionException;
import Owner.Owner;
import Tradable.Tradable;
import Utility.CustomSlowMap;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;

import DataReading.DataReader;

public class Market {
    public static class Event {
        private final String[] descriptions = new String[]{"Civil war", "Thanos attack", "Spider Man Died","Johnny Depp lost trial", "Thanos attack", "Spider Man Died","Johnny Depp lost trial", "Thanos attack", "Spider Man Died","Johnny Depp lost trial", "Thanos attack", "Spider Man Died","Johnny Depp lost trial", "Thanos attack", "Spider Man Died","Johnny Depp lost trial"};
        private final CustomSlowMap<Integer, BigDecimal> effect = new CustomSlowMap<>(new Integer[]{0,1,2,3,4,5},new BigDecimal[]{new BigDecimal(0.89), new BigDecimal(0.95),new BigDecimal(0.77),new BigDecimal(0.91),new BigDecimal(0.87),new BigDecimal(0.90) });
        private final int eventNum;


        public Event() {
            eventNum = (int) (Math.random() * 5);
        }

        public String getDescription() {
            return descriptions[eventNum];
        }

        public void affect() {
            for (int i = 0; i < assets.size(); i++) {
                assets.get(i).updatePrice(assets.get(i).getValueInMoney().multiply(effect.getValue(eventNum)));
            }
        }
    }

    public static class Transaction {
        private String date;
        private String owner;
        private String tradableType;
        private int transactionNum;
        private int tradableID;
        private TransactionType transactionType;

        //Might not need the getters

        public String getDate() {
            return this.date;
        }

        public String getOwner() {
            return this.owner;
        }

        public String getTradableType() {
            return this.tradableType;
        }

        public int getTransactionNum() {
            return this.transactionNum;
        }

        public int getTradableID() {
            return this.tradableID;
        }



        public enum TransactionType {BUY, SELL}

        //Change String representation
        public String toString() {
            return this.transactionNum + "|" + this.date + "|" + this.owner + "|" + this.tradableType + "|" + this.tradableID + "|" + this.transactionType;
        }

        public Transaction(String s) {
            String[] arr = s.split("\\|");
            this.transactionNum = Integer.parseInt(arr[0]);
            this.date = arr[1];
            this.owner = arr[2];
            this.tradableType = arr[3];
            this.tradableID = Integer.parseInt(arr[4]);
            this.transactionType = arr[5].equals("BUY") ? TransactionType.BUY : TransactionType.SELL;
            //Exception?

        }

        public Transaction(Owner o, int id, String date, TransactionType transactionType){
            Tradable t = findTradableByID(id);
            this.transactionNum = assets.size() + 1;
            this.date = date; //Need to get date from simulation
            this.owner = o.getName();
            this.tradableType = t.getType();
            this.tradableID = id;
            this.transactionType = transactionType;
        }

        public boolean processTransaction(){
            if(transactionType == TransactionType.SELL){
                Tradable t = ownerProfile.findTradableByID(tradableID);
                if(t == null) return false; // Exception?
                assets.add((Tradable) t.clone());
                ownerProfile.addNetWorth(t.getValueInMoney());
                ownerProfile.removeTradableById(tradableID);
            } else {
                Tradable t = findTradableByID(tradableID);
                if(t == null) return false;
                if(!ownerProfile.addNetWorth(t.getValueInMoney().negate())) return false;

                ownerProfile.addAsset((Tradable) t.clone());
                if(!removeTradableById(tradableID)) {
                    System.out.println("false");
                    return false;
                }
                System.out.println(this.toString());

            }
            return true;
        }
    }

    private static ArrayList<Tradable> assets;
    public ArrayList<Transaction> history;
    private static Owner ownerProfile = new Owner();
    public enum MoneyCurrency {USD, EUR, CHF, JPY, GBP} //Why is it here not in Money?

    public Market(){
        assets = DataReader.getTradables();
        history = DataReader.getHistory();
    }

    public ArrayList<Tradable> getAssets(){
        ArrayList<Tradable> res = new ArrayList<>(assets.size());
        for(int i = 0; i< assets.size(); i++){
            res.add((Tradable) assets.get(i).clone());
        }
        return res;
    }

    public static Tradable findTradableByID(int id){
        for(int i = 0; i<assets.size(); i++){
            if(assets.get(i).getMyID() == id) {
                return (Tradable) assets.get(i).clone();
            }
        }
        return null;
    }
    public static boolean removeTradableById(int id){
        for(int i = 0; i<assets.size(); i++){
            if(assets.get(i).getMyID() == id) {
                System.out.println("removed");
                assets.remove(i);
                return true;
            }
        }
        return false;
    }
    public void sell(int tradableId, String date) throws FailedTransactionException {
        //The operation of Owner change
        Transaction t = new Transaction(ownerProfile, tradableId, date, Transaction.TransactionType.SELL);
        if(t.processTransaction()){
            System.out.println("Transaction approved: " + t.toString());
            DataReader.addTransaction(t);

        } else {
            throw new FailedTransactionException("Transaction failed: " + t);
        }
    }
    public void buy(int tradableId, String date) throws FailedTransactionException {
        //The operation of Owner change
        Transaction t = new Transaction(ownerProfile, tradableId, date, Transaction.TransactionType.BUY);
        if(t.processTransaction()){
            System.out.println("Transaction approved: " + t.toString());
        } else {
            System.out.println("Transaction failed");
            DataReader.addTransaction(t);
            throw new FailedTransactionException("Transaction failed: " + t);
        }
    }

    public ArrayList<Tradable> search(String s){
        ArrayList<Tradable> res = new ArrayList<>();
        for (Tradable asset : assets) {
            if (asset.toString().toLowerCase().contains(s.toLowerCase())) {
                res.add((Tradable) asset.clone());
            }
        }
        return res;
    }

    public void addAsset(Tradable t){
        assets.add(t);
    }
}