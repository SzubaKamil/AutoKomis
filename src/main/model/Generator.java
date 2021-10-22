package main.model;

import main.model.client.Client;
import main.model.client.ClientName;
import main.model.vehicle.Car;
import main.model.vehicle.Truck;
import main.model.vehicle.CarBrand;
import main.model.vehicle.Colour;
import main.model.vehicle.Segment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    private Random random;
    int rNumber;

    public Generator() {
        this.random = new Random();
    }

    public Car newCar (){
        int price = newValue();
        CarBrand brand = newBrand();
        int mileage = random.nextInt(1000000);
        Colour colour = newColour();
        Segment segment = newSegment();
        boolean breaks = random.nextBoolean();
        boolean suspension = random.nextBoolean();
        boolean engine = random.nextBoolean();
        boolean body = random.nextBoolean();
        boolean transmission = random.nextBoolean();
        Car car = new Car(price, brand, mileage, colour, segment, breaks, suspension, engine, body, transmission);

        return setUpCarPrice(car);
    }

    public Car newTruck (){
        Car car = newCar();
        int loadingSpace = random.nextInt(60-5+1)+5; // od 5 do 60
        Truck truck = new Truck(car, loadingSpace);

        return setUpTruckPrice(truck);
    }

    public List<Car> newCars (int numberCars){
        List<Car> cars = new ArrayList<>();
        Car car;

        for (int i = 0; i < numberCars; i++){
            if (random.nextInt(10) < 1){ // 1 na 10 samochod bedzie ciezarówka
                car = newTruck();
            }
            else {
                car = newCar();
            }
            cars.add(car);
        }
        return cars;
    }

    public Client newClient (){
        ClientName name = newClientName();
        int cash = newValue();
        CarBrand[] brands = {newBrand(), newBrand()};
        boolean breaks;
        boolean suspension;
        boolean engine;
        boolean body;
        boolean transmission;
        boolean truck;

        rNumber = random.nextInt(100);
        breaks = rNumber >= 5; // 5 % klientów kupi z uszkodzonymi hamulcami

        rNumber = random.nextInt(100);
        suspension = rNumber >= 20; // 20 % klientów kupi auto z uszkodzym zawieszeniem

        rNumber = random.nextInt(100);
        engine = rNumber >= 2; // 2 % klientów kupi auto z uszkodzym silnikiem

        rNumber = random.nextInt(100);
        body = rNumber >= 15; // 15 % klientów kupi auto z uszkodzym nadwozie

        rNumber = random.nextInt(100);
        transmission = rNumber >= 5; // 5 % klientów kupi auto z uszkodzną skrzynią biegów

        rNumber = random.nextInt(100);
        truck = rNumber <= 10; // 10 % klientów kupi auto dostawcze

        Client client = new Client(name, cash, brands, breaks, suspension, engine, body, transmission, truck);
        return client;
    }

    public List<Client> newClients (int numberOfClient){
        List<Client> clients = new ArrayList<>();
        for (int i = 0; i < numberOfClient; i ++){
            clients.add(newClient());
        }
        return clients;
    }

    private int newValue(){
        return random.nextInt(50000-5000+1) + 5000; // od 5000 do 50000
    }

    private Colour newColour(){
        Colour[] colours = Colour.values();
        rNumber = random.nextInt(colours.length);
        return colours[rNumber];
    }

    private CarBrand newBrand(){
        CarBrand[] carBrands = CarBrand.values();
        rNumber = random.nextInt(carBrands.length);
        return carBrands[rNumber];
    }

    private Segment newSegment(){
        Segment[] segments = Segment.values();
        rNumber = random.nextInt(segments.length);
        return segments[rNumber];
    }

    private ClientName newClientName (){
        ClientName [] clientNames = ClientName.values();
        rNumber = random.nextInt(clientNames.length);
        return clientNames[rNumber];
    }

    private Car setUpCarPrice(Car car){
        int price = car.getPrice();

        if (car.getMileage() < 10000){
            price += 10000;
        }
        else if (car.getMileage() < 100000){
            price += 5000;
        }
        if (car.getMileage() > 800000){
            price -= 3000;
        }
        else if (car.getMileage() > 500000){
            price -= 1000;
        }
        if (car.getSegment().equals(Segment.Premium)){
            price += 5000;
        }
        if (car.getSegment().equals(Segment.Budget)){
            price -= 1000;
        }
        if (!car.isEngine()){
            price /= 2;
        }
        if (!car.isBody()){
            price *= 0.75;
        }
        if (!car.isTransmission()){
            price *= 0.75;
        }

        car.setPrice(price);
        return car;
    }

    private Truck setUpTruckPrice(Truck truck){
        int price = truck.getPrice();
        int loadingSpace = truck.getLoadingSpace();

        if (loadingSpace > 50){
            price += 20000;
        }
        else if (loadingSpace > 40){
            price += 15000;
        }
        else if (loadingSpace > 30){
            price += 10000;
        }
        else if(loadingSpace > 20){
            price += 5000;
        }
        else if(loadingSpace > 10){
            price =+ 2000;
        }

        truck.setPrice(price);
        return truck;
    }

}
