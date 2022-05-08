
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

public class Market {
    public class Event {
        private final String[] descriptions = new String[]{"Civil war", "Thanos attack", "Spider Man Died"};
        private final CustomSlowMap<Integer, BigDecimal> effect = new CustomSlowMap<>(new Integer[]{0,1,2,3,4,5},new BigDecimal[]{new BigDecimal(0.17) });
        private final int eventNum;


        public Event() {
            eventNum = (int) (Math.random() * 5);
        }

        public String getDescription() {
            return descriptions[eventNum];
        }

        private void affect() {
            for (int i = 0; i < assets.size(); i++) {
                assets.get(i).updatePrice(assets.get(i).getValueInMoney().multiply(effect.getValue(eventNum)));
            }
        }
    }

    public class Transaction {
        private String date;
        private String owner;
        private String tradableType;
        private int transactionNum;
        private int tradableID;
        private boolean isIn;

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

        public String toString() {
            return this.transactionNum + " " + this.isIn + " " + this.date + " " + this.owner + " " + this.tradableType + " " + this.tradableID;
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

        public Transaction(Owner o, int id){
            Tradable t = findTradableByID(id);
            this.transactionNum = assets.size() + 1;
            this.date = ""; //Need to get date from simulation
            this.owner = o.getName();
            this.tradableType = t.getType();
            this.tradableID = id;
        }

        //Some search stuff for Transactions?
    }

    private ArrayList<Tradable> assets;
    private ArrayList<Transaction> history;
    private Event currentEvent;
    public enum MoneyCurrency {USD, EUR, CHF, JPY, GBP}

    public void loadHistory() {
        try {
            Scanner sc = new Scanner(new FileInputStream("history.txt"));
            while (sc.hasNextLine()) {
                history.add(new Transaction(sc.nextLine()));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open the database file.");
            System.exit(0);
        }
    }

    private ArrayList<Tradable> getAssets(){
        return this.assets;
    }

    private Tradable findTradableByID(int id){
        for(int i = 0; i<assets.size(); i++){
            if(assets.get(i).getMyID() == id) {
                return assets.get(i); //Copy Constructor?
            }
        }
        return null;
    }

    public void sell(Owner owner, int tradableId){
        //The operation of Owner change
        Transaction t = new Transaction(owner, tradableId);
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("logs.txt", true));
            pw.println(t);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot save into the database file.");
            System.exit(0);
        }
    }

    public void addAsset(Tradable t){
        assets.add(t);
    }

    //Run here? Or seperate class??
}