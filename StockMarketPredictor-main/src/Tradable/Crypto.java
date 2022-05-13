package Tradable;

import Utility.CustomSlowMap;
import Utility.Money;

import java.math.BigDecimal;

public class Crypto extends Tradable {
    private Money valueInMoney;
    public static final TradableType type = TradableType.Crypto;

    private String currency;

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

    @Override
    public BigDecimal getValueInMoney() {
        return valueInMoney.getValueInMoney();
    }

    @Override
    public Money getMoney(){
        return new Money(this.valueInMoney);
    }
    @Override
    public void updatePrice(BigDecimal newValue) {
        valueInMoney.setAmount(newValue);
    }

    @Override
    public String getType() {
        return type.toString();
    }

    public String toString() {
        return "Crypto | " + this.currency + " | " + this.valueInMoney.getValueInMoney() + " " + this.valueInMoney.getCurrency();
    }

    public Crypto clone(){
        Crypto c = (Crypto) super.clone();
        c.valueInMoney = new Money(valueInMoney.getAmount(), valueInMoney.getCurrency());
        return c;
    }
}
