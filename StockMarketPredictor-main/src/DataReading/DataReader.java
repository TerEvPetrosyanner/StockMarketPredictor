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
    private static final String tradablesPath = "./data/tradables.txt";
    private static final String eventsPath = "./data/events.txt";
    private static final String usersPath = "./data/users.txt";

    public static ArrayList<Tradable> getTradables() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(tradablesPath));
        String currentLine;
        ArrayList<Tradable> result = new ArrayList<>();
        if(scanner.hasNextLine()){
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

    public static void addTradable(Tradable tradable) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(new File(tradablesPath));

        writer.print(tradable.toString());
        writer.close();
    }


}
