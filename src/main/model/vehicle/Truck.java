package main.model.vehicle;

public class Truck extends Car{

    public Truck(Car car, int loadingSpace) {
        this.price = car.getPrice();
        this.brand = car.getBrand();
        this.mileage = car.getMileage();
        this.colour = car.getColour();
        this.segment = car.getSegment();
        this.breaks = car.isBreaks();
        this.suspension = car.isSuspension();
        this.body = car.isBody();
        this.transmission = car.isTransmission();
        this.loadingSpace = loadingSpace;
    }

    @Override
    public void printLine(int id) {
        super.printLine(id);
        System.out.print("\t" + loadingSpace);
    }

}
