package main.model;

import main.model.client.Client;
import main.model.mechanic.*;
import main.model.vehicle.Car;
import main.model.wallet.Action;
import main.model.wallet.Wallet;
import main.model.wallet.WalletOperation;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final int INIT_CASH = 50000;
    public static final int INIT_CARS_AVAILABLE = 10;
    public static final int INIT_CLIENT_AVAILABLE = 5;

    private List<Car> carsOnSale;
    private List<Car> myCars;
    private List<Client> clients;
    private Mechanic [] mechanics = new Mechanic[] {new AdrianMechanic(), new MarianMechanic(), new JanuszMechanic()};
    private Wallet wallet;
    private Advertisement advertisement;
    private Generator generator;
    private int counter;

    public Game() {
        this.carsOnSale = new ArrayList<>();
        this.myCars = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.wallet = new Wallet(INIT_CASH);
        this.advertisement = new Advertisement();
        this.generator = new Generator();
        carsOnSale.addAll(generator.newCars(INIT_CARS_AVAILABLE));
        clients.addAll(generator.newClients(INIT_CLIENT_AVAILABLE));
        this.counter = 0;
    }

    public boolean buyCar (Car car){
        if (new WalletOperation(car.getPrice(), Action.BUY_CAR, wallet).addCarBrand(car.getBrand()).call()){

            car.addCostRepairAndWash(WalletOperation.WASH_COST);
            counter++;
            myCars.add(car);
            carsOnSale.addAll(generator.newCars(3));
            carsOnSale.remove(car);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean buyAdvertisement (int id){
        List<Client> newClients = new ArrayList<>();

        switch (id){
            case 1:
                newClients.add(advertisement.buyInternet(wallet));
                break;
            case 2:
                newClients.addAll(advertisement.buyNewspaper(wallet));
                break;
            default:
                System.out.println("ID reklamy nie poprawne");
                break;
        }
        if (newClients.size()>0){
            counter++;
            clients.addAll(newClients);
            return true;
        }
        return false;
    }

    public boolean sellCar (Car car, Client client){
        if (car.getPrice() < client.getCash()){
            if (new WalletOperation(car.getPrice(), Action.SELL_CAR, wallet).addCarBrand(car.getBrand()).call()){
                System.out.println("Sprzedałeś " + car.getBrand() + " za " + car.getPrice() +
                        " PLN " + " klientowi " + client.getName());
                counter ++;
                myCars.remove(car);
                clients.remove(client);
                clients.addAll(generator.newClients(2));
                return true;
            }
            else {
                System.out.println("Wystąpił błąd płatności!!! Spróbój ponownie");
            }
        }
        return false;
    }

    public boolean repairCar (Car car, Part part, Mechanic mechanic){
        return mechanic.fixPart(car,wallet, part);
    }

    public int getBalance (){
        return wallet.getBalance();
    }

    public List<Car> getCarsOnSale() {
        return carsOnSale;
    }

    public List<Car> getMyCars() {
        return myCars;
    }

    public List<Client> getClients() {
        return clients;
    }

    public Mechanic[] getMechanics() {
        return mechanics;
    }

    public List<String> getTransactionHistory (){
        return wallet.getTransactionHistory();
    }

    public List<Car> carsForClient (Client client){
        List<Car> cars = new ArrayList<>();
        for (Car car: myCars){
            if (car.getBrand().equals(client.getBrands()[0]) || car.getBrand().equals(client.getBrands()[1])){
                if (    (car.isBreaks() || !client.isBreaks()) &&
                        (car.isSuspension() || !client.isSuspension()) &&
                        (car.isEngine() || !client.isEngine()) &&
                        (car.isBody() || !client.isBody()) &&
                        (car.isTransmission() || !client.isSuspension())){
                    if (!client.isTruck() && car.getLoadingSpace() == 0){
                        cars.add(car);
                    }
                    if (client.isTruck() && car.getLoadingSpace() > 0){
                        cars.add(car);
                    }
                }
            }
        }
        return cars;
    }

    public List<String> getRepairHistory (){
        List<String> history = new ArrayList<>();
        int i = 1;
        for (Car car: myCars){
            StringBuilder sb = new StringBuilder(i + "\t" + car.getBrand());
            for (String str: car.getRepairHistory()){
                sb.append("\t").append(str);
            }
            history.add(sb.toString());
            i ++;
        }
        return history;
    }

    public int getCounter() {
        return counter;
    }
}
