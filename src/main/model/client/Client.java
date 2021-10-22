package main.model.client;

import main.model.PrintLine;
import main.model.vehicle.CarBrand;

import java.util.Arrays;

public class Client implements PrintLine {

    private ClientName name;
    private int cash;
    private CarBrand[] brands;
    private boolean breaks;
    private boolean suspension;
    private boolean engine;
    private boolean body;
    private boolean transmission;
    private boolean truck;

    public Client(ClientName name, int cash, CarBrand[] brands, boolean breaks, boolean suspension,
                  boolean engine, boolean body, boolean transmission, boolean truck) {
        this.name = name;
        this.cash = cash;
        this.brands = brands;
        this.breaks = breaks;
        this.suspension = suspension;
        this.engine = engine;
        this.body = body;
        this.transmission = transmission;
        this.truck = truck;
    }

    @Override
    public String toString() {
        return  name + "\t" + cash + " PLN\t" + Arrays.toString(brands) + "\t"+
                breaks + "\t" + suspension + "\t" + engine + "\t" + body + "\t" +
                transmission + "\t" + truck;
    }

    public ClientName getName() {
        return name;
    }

    public int getCash() {
        return cash;
    }

    public CarBrand[] getBrands() {
        return brands;
    }

    public boolean isBreaks() {
        return breaks;
    }

    public boolean isSuspension() {
        return suspension;
    }

    public boolean isEngine() {
        return engine;
    }

    public boolean isBody() {
        return body;
    }

    public boolean isTransmission() {
        return transmission;
    }

    public boolean isTruck() {
        return truck;
    }

    private String carOrTruck(Boolean b){
        String s;
        if (b){
            s = "Ciężarowy";
        } else {
            s = "Osobowy";
        }
        return s;
    }

    @Override
    public void printLine(int id) {
        System.out.printf("%1$-4d %2$-15s %3$-15s %4$-15s %5$-13s %6$-13s %7$-13s %8$-13s %9$-18s %10$-13s %11$-10d",
                id, name, brands[0], brands[1], yesOrNo(breaks), yesOrNo(suspension), yesOrNo(engine),
                yesOrNo(body), yesOrNo(transmission), carOrTruck(truck), cash );
    }
}
