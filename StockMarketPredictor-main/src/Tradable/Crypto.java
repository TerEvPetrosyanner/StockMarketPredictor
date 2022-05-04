package Tradable;

import java.math.BigDecimal;

public class Crypto extends Money {
    //Maybe separate classes for fungible and non-fungible
    //Non-fungible has to be immutable, while fungible mutable

    private static BigDecimal amount;

    public enum CryptoCurrency {USDT, BTC, ETH, ADA, SOL;}
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



    public static BigDecimal liquidity(Crypto first, Crypto second, double percentage, int month) {
        if (Crypto.changeCurrency(first.getCryptoCurrency(), amount, second.getCryptoCurrency(), BigDecimal.ZERO).equals(second.getAmount())) {

            return first.getAmount().multiply(BigDecimal.valueOf(Math.pow(1 + percentage / 100, month / 12.0)));

        }
        return BigDecimal.ZERO;
    }
}
