package Tradable;

import java.math.BigDecimal;

public class RealEstate extends Tradable{

    /**Instance variables */
    private final String address;

    //maybe need to add what currency is the price in?

    //for real estate an event can be "գին քցել",
    private BigDecimal price;

    //ft, m^2, km^2?
    private final double area;

    //anything more to be added?

    /**Constructors*/
    public RealEstate(String address, double price,double area) {
        this.address = address;
        this.price = price;
        this.area = area;
    }
    public String getAddress(){return this.address;}
    public double getArea(){return this.area;}

    public BigDecimal getPrice(){return this.price;}
    public void setPrice(double Price){this.price=price;}

  //I think Money can become an abstract class as no object of it seems to be created



}
