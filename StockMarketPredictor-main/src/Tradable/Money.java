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

    public static final TradableType type = TradableType.Money;


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

    private static final CustomSlowMap<String, BigDecimal> currencyRates = new CustomSlowMap<>(new String[]{"USD", "EUR", "CHF", "JPY", "GBP"},
            new BigDecimal[]{BigDecimal.valueOf(1),
                    BigDecimal.valueOf(0.92),
                    BigDecimal.valueOf(0.94),
                    BigDecimal.valueOf(124.30),
                    BigDecimal.valueOf(0.77)});
    /**
     * Converts the Money from it's own currency to USD, then calculates the amount in USD($)
     *
     * @return converted price
     */
    public BigDecimal getValueInMoney() {
        return currencyRates.getValue(currency).multiply(amount);
    }

    public void updatePrice(BigDecimal newValue) {
        BigDecimal newValueInDollars = newValue.multiply(currencyRates.getValue(currency));
        amount = new BigDecimal(newValueInDollars.toString());
    }

    @Override
    public String getType() {
        return type.toString();
    }

    public void setAmount(BigDecimal amount) {
        //Checks?
        this.amount = amount;
    }


    public Money() {
        this.amount = BigDecimal.ZERO;
    }

    public Money (BigDecimal amount, String currency){
        this.amount = new BigDecimal(amount.toString());
        this.currency = currency;
    }

    public Money(Money m){
        this.amount = m.getAmount();
        this.currency = m.getCurrency();
    }

    public Money(String representation){
        this();
        String[] parts = representation.split(" | ");
        if(parts.length != 3) return;
        if(!parts[0].equals("Money")) return;


        this.currency = parts[1];
        this.amount = new BigDecimal(parts[2]);
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

    public String toString() {
        return "Money | " + this.currency + " | " + this.amount;
    }

    public String getName(){
        return this.currency;
    }
}
