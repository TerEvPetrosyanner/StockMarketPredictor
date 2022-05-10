
package Market;

import Owner.Owner;
import Tradable.Tradable;
import Tradable.CustomSlowMap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;
import DataReading.DataReader;

public class Market {
    public static class Event {
        private final String[] descriptions = new String[]{"Civil war", "Thanos attack", "Spider Man Died","Johnny Depp lost trial", "Thanos attack", "Spider Man Died","Johnny Depp lost trial", "Thanos attack", "Spider Man Died","Johnny Depp lost trial", "Thanos attack", "Spider Man Died","Johnny Depp lost trial", "Thanos attack", "Spider Man Died","Johnny Depp lost trial"};
        private final CustomSlowMap<Integer, BigDecimal> effect = new CustomSlowMap<>(new Integer[]{0,1,2,3,4,5},new BigDecimal[]{new BigDecimal(0.17) });
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

        //Change String representation
        public String toString() {
            return this.transactionNum + " " + this.date + " " + this.owner + " " + this.tradableType + " " + this.tradableID;
        }

        public Transaction(String s) {
            String[] arr = s.split(" ");
            this.transactionNum = Integer.parseInt(arr[0]);
            this.date = arr[1];
            this.owner = arr[2] + " " + arr[3];
            this.tradableType = arr[4];
            this.tradableID = Integer.parseInt(arr[5]);
            //Exception?

        }

        public Transaction(Owner o, int id, String date){
            Tradable t = findTradableByID(id);
            this.transactionNum = assets.size() + 1;
            this.date = date; //Need to get date from simulation
            this.owner = o.getName();
            this.tradableType = t.getType();
            this.tradableID = id;
        }
    }

    private static ArrayList<Tradable> assets;
    private ArrayList<Transaction> history;
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

    public void sell(Owner owner, int tradableId, String date){
        //The operation of Owner change
        Transaction t = new Transaction(owner, tradableId, date);
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(DataReader.transactionsPath, true));
            pw.println(t);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot save into the database file.");
            System.exit(0);
        }
    }
   public ArrayList<Tradable> res = new ArrayList<Tradable>();

    public ArrayList<Tradable> search(String s){
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