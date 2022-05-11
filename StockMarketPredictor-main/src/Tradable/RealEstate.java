package Tradable;

import Utility.Money;

import java.math.BigDecimal;

public class RealEstate extends Tradable{

    /**Instance variables */
    private String address;

    //maybe need to add what currency is the price in?

    //for real estate an event can be "գին քցել",
    private Money valueInMoney;

    //ft, m^2, km^2?
    private double area;

    public static final TradableType type = TradableType.RealEstate;
    //anything more to be added?

    /*Constructors*/
    public RealEstate(String address, Money price,double area) {
        this.address = address;
        this.valueInMoney = price;
        this.area = area;
    }
    public RealEstate(){
        this.address = "Baker Str. 1";
        this.valueInMoney = new Money(new BigDecimal("12"), "USD");
        this.area = 1;
    }

    public RealEstate(String representation){
        this();
        String[] parts = representation.split(" \\| ");
        if(parts.length != 3) return;
        if(!parts[0].equals("RealEstate")) return;

        this.address = parts[1];
        this.area = Double.parseDouble(parts[2].split(" ")[0]);
        this.valueInMoney = new Money(new BigDecimal(parts[2].split(" ")[0]), parts[2].split(" ")[1]);
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

    public String getName(){ return this.address; }
    public String toString() {
        return  "RealEstate | " + this.address + " | " + this.area + "m2 | " + this.valueInMoney.getValueInMoney() + " " + this.valueInMoney.getCurrency();
    }

    public RealEstate clone(){
        RealEstate r = (RealEstate) super.clone();
        r.valueInMoney = new Money(valueInMoney.getAmount(), valueInMoney.getCurrency());
        return r;
    }
}
