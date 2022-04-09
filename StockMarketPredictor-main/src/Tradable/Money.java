package Tradable;

public class Money extends Tradable{
    /** Enum type for money currency. Supports 5 currencies:
     * USD - United States Dollar
     * EUR - Euro
     * CHF - Swiss franc
     * JPY - Japanese yen
     * GBP - Pound sterling
     */

    //Probably have this in market
    public enum MoneyCurrency {USD, EUR, CHF, JPY, GBP};
    /** The rates according to USD*/ //The rates should change
    private static double[] rates = {1, 0.92, 0.94, 124.30, 0.77};

    /** Instance variable of type MoneyCurrency storing the currency*/
    private MoneyCurrency currency;
    private double amount; //What to use instead of double?

    //Constructors?

    public MoneyCurrency getCurrency(){
        return currency;
    }

    public double getAmount(){
        return this.amount;
    }

    public void setAmount(double amount){
        //Checks?
        this.amount = amount;
    }

    public static double changeCurrency(MoneyCurrency currency, double amount, MoneyCurrency changeTo, double marketPercentage){
        int currencyIndex =  MoneyCurrency.valueOf(currency).ordinal();
        amount *= rates[currencyIndex];
        int changeToIndex = MoneyCurrency.valueOf(changeTo).ordinal();
        amount *= rates[changeToIndex];
        amount *= marketPercentage/100;
        return amount;
    }

    public void staking(double percentage, int year){
        this.amount *= Math.pow(1+percentage/100, year);
    }
}
