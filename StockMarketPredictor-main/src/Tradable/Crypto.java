package Tradable;

public class Crypto extends Money{
    //Maybe separate classes for fungible and non-fungible
    //Non-fungible has to be immutable, while fungible mutable
    public enum CryptoCurrency {USDT, BTC, ETH, ADA, SOL};
    private CryptoCurrency currency;
    private static double amount;

    private double[] rates = {1, 42_475, 3220, 1.04, 111.03};

    public CryptoCurrency getCryptoCurrency(){
        return this.currency;
    }

    public static double liquidity(Crypto first, Crypto second, double percentage, int month){
        if(Money.changeCurrency(first.getCryptoCurrency(), amount, second.getCryptoCurrency(), 0) == second.getAmount()){
            return first.getAmount() * Math.pow(1+percentage/100, month/12);
        }
    }
}
