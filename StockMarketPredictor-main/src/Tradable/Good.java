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
    private double amount;


    public Good(String goodName, BigDecimal money, UnitType type, double amount) {
        valueInMoney = new Money(money, "USD");
        this.goodName = goodName;
        this.amount = amount;
        unitType = type;
    }

    public Good() {
        this.goodName = "Banana";
        valueInMoney = new Money(new BigDecimal(2), "USD");
        this.amount = 1;
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
}
