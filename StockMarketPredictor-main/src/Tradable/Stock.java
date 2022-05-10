package Tradable;

import java.math.BigDecimal;

public class Stock extends Tradable{
    private Money valueInMoney;
    public static final TradableType type = TradableType.Stock;
    private String name;

    public Stock (){
        valueInMoney = new Money(new BigDecimal(1), "USD");
    }
    public Stock (String representation) {
        this();
        String[] parts = representation.split(" | ");
        if(parts.length != 2) return;
        if(!parts[0].equals("Stock")) return;

        this.valueInMoney = new Money(new BigDecimal(parts[2]), "USD");
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
        return "Stock | " + this.name + " | " + this.valueInMoney;
    }

    public String getName() {return this.name; }

}
