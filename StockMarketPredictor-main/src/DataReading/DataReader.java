package DataReading;

import Market.Market;
import Market.Market.Transaction;
import Tradable.Tradable;
import Tradable.Crypto;
import Tradable.Good;
import Tradable.Stock;
import Tradable.RealEstate;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
    private static final String tradablesPath = "./data/tradables.txt";
    private static final String transactionsPath = "./data/transactions.txt";
    public static int count = 0;

    private static final ArrayList<Tradable> result = new ArrayList<>();
    public static final ArrayList<Market.Transaction> history = new ArrayList<>();

    public static ArrayList<Tradable> getTradables() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream(tradablesPath));
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        String currentLine;

        while (scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            switch (currentLine.split(" \\| ")[0]) {
                case "Crypto":
                    result.add(new Crypto(currentLine));
                    break;
                case "RealEstate":
                    result.add(new RealEstate(currentLine));
                    break;
                case "Stock":
                    result.add(new Stock(currentLine));
                    break;
                case "Good":

                   result.add(new Good(currentLine));
                    break;
            }
        }
        scanner.close();

        return new ArrayList<>(result);
    }

    public static ArrayList<Market.Transaction> getHistory() {
        Scanner sc = null;
        try {
            sc = new Scanner(new FileInputStream(transactionsPath));
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open the database file.");
            System.exit(0);
        }

        while (sc.hasNextLine()) {
            history.add(new Market.Transaction(sc.nextLine()));
        }
        sc.close();
        return history;
    }

    public static void addTradable(Tradable tradable) {
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(DataReader.tradablesPath, true));
            pw.println(tradable);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot save into the database file.");
            System.exit(0);
        }
    }
    public static void addTransaction(Transaction transaction){
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(DataReader.transactionsPath, true));
            pw.println(transaction);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot save into the database file.");
            System.exit(0);
        }
    }
    public static Tradable getTradable(){
        if(count<result.size()) return result.get(count++);
        return null;
    }

}