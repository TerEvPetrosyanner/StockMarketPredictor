
package Market;

import Exceptions.FailedTransactionException;
import Owner.Owner;
import Tradable.Tradable;
import Utility.CustomSlowMap;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import DataReading.DataReader;

public class Market {
    public static class Event {
        private final String[] descriptions = new String[]{"Civil war", "Thanos snapped fingers", "Spider Man got lost in the multiverse","Johnny Depp lost trial", "Alduin awakened", "Dovahkin bought High Hrothgar","Queen of England died"};
        private final CustomSlowMap<Integer, BigDecimal> effect = new CustomSlowMap<>(new Integer[]{0,1,2,3,4,5,6},
                new BigDecimal[]{new BigDecimal(0.89), new BigDecimal(0.95),new BigDecimal(0.77),new BigDecimal(0.91),new BigDecimal(0.87),new BigDecimal(0.90), new BigDecimal(1.3) });
        private final int eventNum;


        public Event() {
            eventNum = (int) (Math.random() * 5);
        }

        public String getDescription() {
            return descriptions[eventNum];
        }

        public void affect() {
            MathContext m = new MathContext(4);
            for (int i = 0; i < assets.size(); i++) {
                assets.get(i).updatePrice(assets.get(i).getValueInMoney().multiply(effect.getValue(eventNum)).round(m));
            }
        }

        public BigDecimal predictAffect(BigDecimal netWorth){
            return netWorth.multiply(effect.getValue(eventNum));
        }
    }

    public static class Transaction {
        private String date;
        private String owner;
        private String tradableType;
        private int transactionNum;
        private int tradableID;
        private TransactionType transactionType;

        public enum TransactionType {BUY, SELL}

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
        }

        public Transaction(Owner o, int id, String date, TransactionType transactionType){
            Tradable t = transactionType == TransactionType.BUY ? findTradableByID(id): ownerProfile.findTradableByID(id);
            this.transactionNum = assets.size() + 1;
            this.date = date;
            this.owner = o.getName();
            this.tradableType = t.getType();
            this.tradableID = id;
            this.transactionType = transactionType;
        }

        public boolean processTransaction(){
            if(transactionType == TransactionType.SELL){
                Tradable t = ownerProfile.findTradableByID(tradableID);
                if(t == null) return false;
                assets.add((Tradable) t.clone());
                ownerProfile.addNetWorth(t.getValueInMoney());
                return ownerProfile.removeTradableById(tradableID);
            } else {
                Tradable t = findTradableByID(tradableID);
                if(t == null) return false;
                if(!ownerProfile.addNetWorth(t.getValueInMoney().negate())) return false;

                ownerProfile.addAsset((Tradable) t.clone());
                return removeTradableById(tradableID);

            }
        }
    }

    private static ArrayList<Tradable> assets;
    public ArrayList<Transaction> history;
    private static Owner ownerProfile = new Owner();

    public Market(){
        assets = DataReader.getTradables();
        history = DataReader.getHistory();
    }

    public static Owner getOwnerProfile() {
        return ownerProfile;
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