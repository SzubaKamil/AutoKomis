package main.controller;

import main.model.Game;
import main.model.client.Client;
import main.model.mechanic.Mechanic;
import main.model.mechanic.Part;
import main.model.vehicle.Car;

import java.util.List;

public class Controller {

    private Game game;

    public Controller() {
        this.game = new Game();
    }

    public void carsOnSale(){
        List<Car> cars = game.getCarsOnSale();
        String title = "Baza samochodów do kupienia";
        printCarList(title, cars);
    }

    public void buyCar (int id){
        Car car = getCarById(id, game.getCarsOnSale());

        if (car != null){
            if (game.buyCar(car) ){
                System.out.println("Kupiono auto " + car.getBrand() + " za " + car.getPrice() + " PLN");
            }
            else {
                System.out.println("Nie masz wystarczającej ilości środków na koncie");
            }
        }
        else {
            System.out.println("ID auta nie ma na liście aut do sprzedarzy");
        }
    }

    public void myCars (){
        List<Car> cars = game.getMyCars();
        String title = "Baza posiadancyh samochodów";
        printCarList(title, cars);
    }

    public void repairCar (int carId, int partId, int mechanicId){
        boolean flag = true;
        Car car = null;
        Part part = null;
        Mechanic mechanic = null;

        try {
            car = game.getMyCars().get(carId-1);
            flag = false;
        } catch (IndexOutOfBoundsException e){
            System.out.println("Wprowadzono nieprawidłowe ID auta");
        }

        try{
            part = Part.values()[partId];
        }
        catch (IndexOutOfBoundsException e){
            flag = false;
            System.out.println("Wprowadzono nieprawidłowy nr części");
        }

        try {
            mechanic = game.getMechanics()[mechanicId];
        }
        catch (IndexOutOfBoundsException e){
            flag = false;
            System.out.println("Wprowadzono nieprawidłowe ID mechanika");
        }

        if (flag){
            game.repairCar(car, part, mechanic);
        }
    }

    public boolean availableCarForClient (int clientId){
        try {
            Client client = game.getClients().get(clientId -1);
            List<Car> cars = game.carsForClient(client);

            if (cars.size() > 0){
                printCarList("Dostępne auta dla: " + client.getName(), cars);
            }
            else {
                System.out.println("Nie posiadasz żadnego auta które spełnia preferencje klienta");
                return false;
            }

        } catch (IndexOutOfBoundsException e){
            System.out.println("ID klienta nie ma na liście potencjalnych klientów");
            return false;
        }
        return true;
    }

    public void clients (){
        List<Client> clients = game.getClients();
        System.out.println("Lista klientów");
        System.out.printf("%1$-4s %2$-15s %3$-15s %4$-15s %5$-13s %6$-13s %7$-13s %8$-13s %9$-18s %10$-13s %11$-10s",
                "ID", "Imie", "Marka", "Marka 2", "Hamulce", "Zawieszenie", "Silnik",
                "Nadwozie", "Skrzynia biegów", "Rodzaj auta", "Gotówka" );
        for (int i =0 ; i < clients.size(); i ++){
            System.out.println();
            clients.get(i).printLine(i+1);
        }
        System.out.println();
    }

    public void sellCar (int carId, int clientId){
        Client client = game.getClients().get(clientId-1);
        Car car = game.carsForClient(client).get(carId-1);

        if (!game.sellCar(car, client)){
            System.out.println("Niestety " + client.getName() + " posiada za mało gotówki");
        }
    }

    public void buyAdvertisement(int id){
        game.buyAdvertisement(id);
    }

    public void transactionHistory (){
        System.out.println("Historia transakcji");
        game.getTransactionHistory().forEach(System.out::println);
    }

    public void repairHistory (){
        System.out.println("Historia napraw aut");
        game.getRepairHistory().forEach(System.out::println);
    }

    public void washAndRepairCost() {
        System.out.println("Suma kosztów napraw i mycia dla każdego z aut");
        game.getMyCars().forEach(car -> System.out.printf("%1$-15s %2$-15d %n" , car.getBrand(), car.getCostRepairAndWash()) );
    }

    public Mechanic [] getMechanics (){
        return game.getMechanics();
    }

    public int getBalance(){
        int balance = game.getBalance();
        System.out.println("Stan konta: " + balance + " PLN");
        return balance;
    }

    private Car getCarById (int id, List<Car> cars){
        if (id <= cars.size() && id > 0){
            return cars.get(id-1);
        }
        return null;
    }

    private void printCarList (String title, List<Car> cars){
        System.out.println(title);
        System.out.printf("%1$-4s %2$-15s %3$-15s %4$-15s %5$-10s %6$-13s %7$-13s %8$-13s %9$-13s %10$-18s %11$-10s  %12$-13s", "ID",
                "Marka", "Segment", "Kolor", "Przebieg", "Hamulce", "Zawieszenie", "Silnik", "Nadwozie",
                "Skrzynia biegów", "Cena", "Przestrzeń ładunkowa\n");

        for (int i =0 ; i < cars.size(); i ++){
            System.out.println();
            cars.get(i).printLine(i+1);
        }
        System.out.println();
    }

    public int getCounter() {
        return game.getCounter ();
    }
}
