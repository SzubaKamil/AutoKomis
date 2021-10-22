package main.model.vehicle;

import main.model.PrintLine;

import java.util.ArrayList;
import java.util.List;

public class Car implements PrintLine {

    protected int price;
    protected CarBrand brand;
    protected int mileage;
    protected Colour colour;
    protected Segment segment;
    protected boolean breaks;
    protected boolean suspension;
    protected boolean engine;
    protected boolean body;
    protected boolean transmission;
    protected int loadingSpace = 0;
    private  List<String> repairHistory;
    private int costRepairAndWash;

    public Car(int price, CarBrand brand, int mileage, Colour colour, Segment segment,
               boolean breaks, boolean suspension, boolean engine, boolean body,
               boolean transmission) {
        this.price = price;
        this.brand = brand;
        this.mileage = mileage;
        this.colour = colour;
        this.segment = segment;
        this.breaks = breaks;
        this.suspension = suspension;
        this.engine = engine;
        this.body = body;
        this.transmission = transmission;
        this.repairHistory = new ArrayList<>();
        this.costRepairAndWash = 0;
    }

    protected Car() {
    }

    public int getPrice() {
        return price;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public int getMileage() {
        return mileage;
    }

    public Colour getColour() {
        return colour;
    }

    public Segment getSegment() {
        return segment;
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

    public int getLoadingSpace() {
        return loadingSpace;
    }

    public List<String> getRepairHistory() {
        return repairHistory;
    }

    public int getCostRepairAndWash() {
        return costRepairAndWash;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setBreaks(boolean breaks) {
        repairHistory.add("Hamulce");
        this.breaks = breaks;
    }

    public void setSuspension(boolean suspension) {
        repairHistory.add("Zawieszenie");
        this.suspension = suspension;
    }

    public void setEngine(boolean engine) {
        repairHistory.add("Silnik");
        this.engine = engine;
    }

    public void setBody(boolean body) {
        repairHistory.add("Nadwozie");
        this.body = body;
    }

    public void setTransmission(boolean transmission) {
        repairHistory.add("Skrzynia biegów");
        this.transmission = transmission;
    }

    public void addCostRepairAndWash(int costRepairAndWash) {
        this.costRepairAndWash += costRepairAndWash;
    }

    @Override
    public void printLine(int id) {
        System.out.printf("%1$-4d %2$-15s %3$-15s %4$-15s %5$-10d %6$-13s %7$-13s %8$-13s %9$-13s %10$-18s %11$-10d", id,
                brand, segment, colour, mileage, yesOrNo(breaks), yesOrNo(suspension), yesOrNo(engine), yesOrNo(body),
                yesOrNo(transmission), price);
    }
}
