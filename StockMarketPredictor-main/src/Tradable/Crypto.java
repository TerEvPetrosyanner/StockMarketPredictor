package Tradable;

import Utility.CustomSlowMap;
import Utility.Money;

import java.math.BigDecimal;

public class Crypto extends Tradable {
    //Maybe separate classes for fungible and non-fungible
    //Non-fungible has to be immutable, while fungible mutable
    private Money valueInMoney;
    public static final TradableType type = TradableType.Crypto;

//    public enum CryptoCurrency {USDT, BTC, ETH, ADA, SOL;}
    private CustomSlowMap currencyMap = new CustomSlowMap(new String[]{"USDT", "BTC", "ETH", "ADA", "SOL"},
            new BigDecimal[]{BigDecimal.valueOf(1),
                    BigDecimal.valueOf(42_475),
                    BigDecimal.valueOf(3220),
                    BigDecimal.valueOf(1.04),
                    BigDecimal.valueOf(111.03)});

    private String currency;

    private double[] rates = {1, 42_475, 3220, 1.04, 111.03};

    public String getCryptoCurrency() {
        return this.currency;
    }


    public Crypto(String currency, BigDecimal amount){
        this.currency = currency;
        this.valueInMoney = new Money(amount.toString());
    }

    public Crypto(String representation){
        this("BTC", new BigDecimal(0));
        String[] parts = representation.split(" \\| ");
        if(parts.length != 3) return;
        if(!parts[0].equals("Crypto")) return;

        this.currency = parts[1];
        this.valueInMoney = new Money(this.valueInMoney = new Money(new BigDecimal(parts[2].split(" ")[0]), parts[2].split(" ")[1]));
    }
//    public static BigDecimal liquidity(Crypto first, Crypto second, double percentage, int month) {
//        if (Crypto.changeCurrency(first.getCryptoCurrency(), first.getAmount(), second.getCryptoCurrency(), BigDecimal.ZERO).equals(second.getAmount())) {
//
//            return first.getAmount().multiply(BigDecimal.valueOf(Math.pow(1 + percentage / 100, month / 12.0)));
//
//        }
//        return BigDecimal.ZERO;
//    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public BigDecimal getValueInMoney() {
        return null;
    }

    @Override
    public void updatePrice(BigDecimal newValue) {

    }

    @Override
    public String getType() {
        return type.toString();
    }

    public String toString() {
        return "Crypto | " + this.currency + " | " + this.valueInMoney.getValueInMoney() + " " + this.valueInMoney.getCurrency();
    }
}
