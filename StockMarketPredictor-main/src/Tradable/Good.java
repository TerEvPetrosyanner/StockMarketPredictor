package Tradable;

import Utility.Money;

import java.math.BigDecimal;

public class Good extends Tradable {

    enum UnitType {
        Piece,
        Kg
    }

    public static final TradableType type = TradableType.Good;
    private UnitType unitType;
    private Money valueInMoney;
    private String goodName;
    private BigDecimal amount;


    public Good(String goodName, BigDecimal money, UnitType type, BigDecimal amount) {
        valueInMoney = new Money(money, "USD");
        this.goodName = goodName;
        this.amount = amount;
        unitType = type;
    }

    public Good(String representation){
        this();
        String[] parts = representation.split(" \\| ");
        if(parts.length != 4) return;
        if(!parts[0].equals("Good")) return;
        this.goodName = parts[1];
        this.valueInMoney = new Money(new BigDecimal(parts[3].split(" ")[0]), parts[3].split(" ")[1]);
        this.amount = new BigDecimal(parts[2].split(" ")[0]);
        this.unitType = parts[2].split(" ")[1].equals("Kg") ? UnitType.Kg : UnitType.Piece;
    }

    public Good() {
        this.goodName = "Banana";
        valueInMoney = new Money(new BigDecimal(2), "USD");
        this.amount = new BigDecimal(1);
        this.unitType = UnitType.Kg;
    }

    @Override
    public Money getMoney(){
        return new Money(this.valueInMoney);
    }
    @Override
    public BigDecimal getValueInMoney() {
        return valueInMoney.getValueInMoney();
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
        return "Good | " + this.goodName + " | " + this.amount + " " + this.unitType + " | " + this.valueInMoney.getValueInMoney() + " " + this.valueInMoney.getCurrency();
    }

    public Good clone(){
        Good g = (Good) super.clone();
        g.valueInMoney = new Money(valueInMoney.getAmount(), valueInMoney.getCurrency());
        return g;
    }
}
