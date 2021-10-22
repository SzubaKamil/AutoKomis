package main.view;

import main.controller.Controller;
import main.model.Game;
import main.model.mechanic.Mechanic;
import main.model.mechanic.Part;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private Controller controller;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.controller = new Controller();
    }

    public void displayMenu(){
        boolean flag = true;
        int id;
        int partId;
        int mechanicId;
        printOptions();

        while (flag){
            switch (scanner.next()){
                case "1":
                    controller.carsOnSale();
                    break;
                case "2":
                    System.out.println("Wpisz ID auta które chcesz kupić");
                    try {
                        id = scanner.nextInt();
                        controller.buyCar(id);
                    }
                    catch (InputMismatchException e){
                        System.out.println("Wpisałeś nieprawidłową wartość dozwolone są tylko cyfry");
                    }
                    break;
                case "3":
                    controller.myCars();
                    break;
                case "4":
                    try {
                        System.out.println("Wpisz ID auta które chcesz naprawić");
                        id = scanner.nextInt();
                        System.out.println("Wybierz część którą część chcesz naprawić");
                        printPartList();
                        partId = scanner.nextInt();
                        System.out.println("Wybierz ID mechanika u którego chcesz dokonać naprawy");
                        printMechanicList();
                        mechanicId = scanner.nextInt();
                        controller.repairCar(id, partId, mechanicId);
                    } catch (InputMismatchException e){
                        System.out.println("Wpisałeś nieprawidłową wartość dozwolone są tylko cyfry");
                    }
                    break;
                case "5":
                    controller.clients();
                    break;
                case "6":
                    try {
                        System.out.println("Wpisz ID klienta któremu chcesz sprzedać auto: ");
                        id = scanner.nextInt();
                        if (controller.availableCarForClient(id)){
                            System.out.println("Wpisz ID auta które chcesz sprzedać");
                            int idCar = scanner.nextInt();
                            controller.sellCar(idCar, id);
                        }
                    } catch (InputMismatchException e){
                        System.out.println("Wpisałeś nieprawidłową wartość dozwolone są tylko cyfry");
                    }
                    break;
                case "7":
                    System.out.println("Wybierz rodzaj reklamy reklame");
                    try {
                        printAdvList();
                        id = scanner.nextInt();
                        controller.buyAdvertisement(id);
                    } catch (InputMismatchException e){
                        System.out.println("Wpisałeś nieprawidłową wartość dozwolone są tylko cyfry");
                    }
                    break;
                case "8":
                    controller.getBalance();
                    break;
                case  "9":
                    controller.transactionHistory();
                    break;
                case "10":
                    controller.repairHistory();
                    break;
                case "11":
                    controller.washAndRepairCost();
                    break;
                case "0":
                    printOptions();
                    break;
                case "Q":
                    System.out.println("Wyjście");
                    flag = false;
                    break;
                default:
                    System.out.println("Błędnie wprowadzone dane - spróbuj ponownie");
                    break;
            }
            if (controller.getBalance() > 2* Game.INIT_CASH ) {
                System.out.println("Gratulecja podwoiłeś swoją gotówkę w " + controller.getCounter()
                        + " turach" + "\nKONIEC GRY");
                flag = false;
            }
        }
    }

    private void printOptions(){
        String menu = "Auto handel" +
                "\n1  Przeglądaj bazę samochodów do kupienia " +
                "\n2  Kup samochód" +
                "\n3  Przeglądaj baze posiadanych samochodów " +
                "\n4  Napraw samochód" +
                "\n5  Przejrzyj potencjalnych klientów" +
                "\n6  Sprzedaj auto potencjalnemu klientowi" +
                "\n7  Kup reklamę" +
                "\n8  Sprawdź stan konta" +
                "\n9  Sprawdź historię transakcji" +
                "\n10 Sprawdź historię napraw każdego pojazdu" +
                "\n11 Sprawdź sumę kosztów napraw i mycia dla każdego z posiadanych aut" +
                "\n0 Wyświetl menu" +
                "\nQ Wyjście";
        System.out.println(menu);
    }

    private void printPartList (){
        System.out.printf("%1$-3s %2$-20S %3$-13S %4$-18S %5$-35S %n" ,
                "ID", "Nazwa części", "Cena [PLN]", "Czas naprawy [h]", "Zwiększenie wartości po naprawie [%]" );

        Arrays.stream(Part.values())
                .forEach(part ->
                        System.out.printf("%1$-3d %2$-20s %3$-13s %4$-18d %5$-35d %n" ,
                        part.ordinal(), part.getName(), part.getPrice(), part.getManHour(), (int )(part.getIncreaseCarValue() *100) -100 ));
    }

    private void printMechanicList (){
        Mechanic[] mechanics = controller.getMechanics();
        System.out.printf("%1$-3S %2$-20S %3$-13S %n", "ID", "Nazwa", "Roboczo - godzina [PLN]" );

        for (int i=0; i < mechanics.length ; i ++){
            System.out.printf("%1$-3d %2$-20s %3$-13d %n",i, mechanics[i].getName(), mechanics[i].getPriceManHour() );
        }
    }

    private void printAdvList(){
        System.out.println("\n1 Reklama w internecie \n2 Reklama w gazecie");
    }

}
