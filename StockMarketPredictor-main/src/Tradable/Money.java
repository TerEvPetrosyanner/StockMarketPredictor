package Tradable;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money extends Tradable {
    /**
     * Enum type for money currency. Supports 5 currencies:
     * USD - United States Dollar
     * EUR - Euro
     * CHF - Swiss franc
     * JPY - Japanese yen
     * GBP - Pound sterling
     */


    private static CustomSlowMap currencyRates = new CustomSlowMap(new String[]{"USD", "EUR", "CHF", "JPY", "GBP"},
            new BigDecimal[]{BigDecimal.valueOf(1),
                    BigDecimal.valueOf(0.92),
                    BigDecimal.valueOf(0.94),
                    BigDecimal.valueOf(124.30),
                    BigDecimal.valueOf(0.77)});

    //Probably have this in market
    public enum MoneyCurrency {USD, EUR, CHF, JPY, GBP}

    ;
    /**
     * The rates according to USD
     */ //The rates should change
    private static double[] rates = {1, 0.92, 0.94, 124.30, 0.77};

    /**
     * Instance variable of type MoneyCurrency storing the currency
     */
    private String currency;
    private BigDecimal amount; //What to use instead of double?

    //Constructors?

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        //Checks?
        this.amount = amount;
    }


    public Money() {
        this.amount = BigDecimal.ZERO;
    }

    public static BigDecimal changeCurrency(String initialCurrency, BigDecimal amount, String targetCurrency, BigDecimal marketPercentage) {

        return amount.multiply(currencyRates.getValue(initialCurrency))
                .multiply(currencyRates.getValue(targetCurrency))
                .multiply(marketPercentage)
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

    }

    /**
     * @param percentage Percentage in decimals [0:1]
     * @param year       Amount of years
     */
    public void staking(BigDecimal percentage, int year) {

        this.amount = amount.multiply((BigDecimal.valueOf(1).add(percentage.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP))).pow(year));

//        this.amount = amount.multiply(BigDecimal.valueOf(Math.pow(1 + percentage/100, year)));
    }
}
