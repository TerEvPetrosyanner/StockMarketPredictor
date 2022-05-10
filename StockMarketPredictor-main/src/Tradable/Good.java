package Tradable;

import java.math.BigDecimal;

public class Good extends Tradable {

    enum UnitType {
        Piece,
        Kg
    }

    public static final TradableType type = TradableType.Money;
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
        String[] parts = representation.split(" | ");
        if(parts.length != 5) return;
        if(!parts[0].equals("Good")) return;

        this.goodName = parts[1];
        this.valueInMoney = new Money(new BigDecimal(parts[2]), "USD");
        this.amount = new BigDecimal(parts[3]);
        this.unitType = parts[4].equals("Kg") ? UnitType.Kg : UnitType.Piece;
    }

    public Good() {
        this.goodName = "Banana";
        valueInMoney = new Money(new BigDecimal(2), "USD");
        this.amount = new BigDecimal(1);
        this.unitType = UnitType.Kg;
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
        return "Money | " + this.goodName + " | " + this.valueInMoney + " | " + this.amount + " | " + this.unitType;
    }
}
