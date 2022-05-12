package Tradable;

import Utility.Money;

import java.math.BigDecimal;

public class Stock extends Tradable{
    private Money valueInMoney;
    public static final TradableType type = TradableType.Stock;
    private String name;

    public Stock (){
        valueInMoney = new Money(new BigDecimal(1), "USD");
        name = "PEAR";
    }
    public Stock (String representation) {
        String[] parts = representation.split(" \\| ");

        if(parts.length != 3) return;
        if(!parts[0].equals("Stock")) return;

        this.valueInMoney = new Money(new BigDecimal(parts[2].split(" ")[0]), parts[2].split(" ")[1]);
        this.name = parts[1];

    }
    @Override
    public BigDecimal getValueInMoney() {
        return valueInMoney.getValueInMoney();
    }

    @Override
    public void updatePrice(BigDecimal newValue) {
        valueInMoney.updatePrice(newValue);
    }

    @Override
    public String getType() {
        return type.toString();
    }

    public String toString () {
        return "Stock | " + this.name + " | " + this.valueInMoney.getValueInMoney() + " " + this.valueInMoney.getCurrency();
    }

    public Stock clone(){
        Stock s = (Stock) super.clone();
        s.valueInMoney = new Money(valueInMoney.getAmount(), valueInMoney.getCurrency());
        return s;
    }

}
