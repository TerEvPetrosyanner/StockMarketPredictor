package DataReading;

import Market.Market;
import Tradable.Tradable;
import Tradable.Crypto;
import Tradable.Money;
import Tradable.Good;
import Tradable.Stock;
import Tradable.RealEstate;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {
    public static final String tradablesPath = "./data/tradables.txt";
    public static final String transactionsPath = "./data/transactions.txt";
    private static int count = 0;

    public static final ArrayList<Tradable> result = new ArrayList<>();
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
        //Change to count, so like the first entries for getTradables then for seperate
        while (scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            switch (currentLine.split(" | ")[0]) {
                case "Crypto":
                    result.add(new Crypto(currentLine));
                    break;
                case "Money":
                    result.add(new Money(currentLine));
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
        return result;
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

    public static void addTradable(Tradable tradable) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(tradablesPath));

        writer.print(tradable.toString());
        writer.close();
    }

    public static Tradable getTradable(){
        if(count<result.size()) return result.get(count++);
        return null;
    }

}
