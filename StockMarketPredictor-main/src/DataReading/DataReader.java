package DataReading;

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
    public static final String eventsPath = "./data/events.txt";
    public static final String usersPath = "./data/users.txt";

   public static final ArrayList<Tradable> result = new ArrayList<>();

    public static ArrayList<Tradable> getTradables() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(tradablesPath));
        String currentLine;
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
        return result;
    }
public  static void main(String[] args) throws FileNotFoundException {
        getTradables();
        for(int i = 0; i<result.size();i++)
        {
            System.out.println(result.get(i).getType());

        }
}

    public static void addTradable(Tradable tradable) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(tradablesPath));

        writer.print(tradable.toString());
        writer.close();
    }


}
