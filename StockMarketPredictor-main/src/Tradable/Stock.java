package Tradable;

import java.math.BigDecimal;

public class Stock extends Tradable{
    private Money valueInMoney;
    public static final TradableType type = TradableType.Stock;

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
}
