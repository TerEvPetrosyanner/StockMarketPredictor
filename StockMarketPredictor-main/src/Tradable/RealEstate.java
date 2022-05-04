package Tradable;

import java.math.BigDecimal;

public class RealEstate extends Tradable{

    /**Instance variables */
    private final String address;

    //maybe need to add what currency is the price in?

    //for real estate an event can be "գին քցել",
    private Money valueInMoney;

    //ft, m^2, km^2?
    private final double area;

    public static final Tradable.Tradable.TradableType type = TradableType.RealEstate;
    //anything more to be added?

    /*Constructors*/
    public RealEstate(String address, Money price,double area) {
        this.address = address;
        this.valueInMoney = price;
        this.area = area;
    }
    public String getAddress(){return this.address;}
    public double getArea(){return this.area;}

    public BigDecimal getValueInMoney(){return this.valueInMoney.getValueInMoney();}

    @Override
    public void updatePrice(BigDecimal newValue) {
        valueInMoney.updatePrice(newValue);
    }

    @Override
    public String getType() {
        return type.toString();
    }

    public void setValueInMoney(double Price){this.valueInMoney = valueInMoney;}


    //I think Money can become an abstract class as no object of it seems to be created



}
