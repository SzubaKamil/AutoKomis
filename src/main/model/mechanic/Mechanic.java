package main.model.mechanic;

import main.model.vehicle.CarBrand;
import main.model.wallet.Action;
import main.model.wallet.Wallet;
import main.model.vehicle.Car;
import main.model.wallet.WalletOperation;

public class Mechanic implements MechanicI {

    protected String name;

    @Override
    public boolean fixPart(Car car, Wallet wallet, Part part) {

        int totalCost = (getPriceManHour()*part.getManHour() ) + setPartCost(car.getBrand(), part.getPrice());

        if (new WalletOperation(totalCost, Action.REPAIR, wallet).call()){
            car.addCostRepairAndWash(totalCost);
            if (isSuccessRepair()) {
                switch (part){
                    case BREAK:
                        car.setPrice((int) (car.getPrice() * part.getIncreaseCarValue())); // add value "1.1" to part
                        car.setBreaks(true);
                        break;
                    case SUSPENSION:
                        car.setPrice((int) (car.getPrice() * part.getIncreaseCarValue()));
                        car.setSuspension(true);
                        break;
                    case ENGINE:
                        car.setPrice((int) (car.getPrice() * part.getIncreaseCarValue()));
                        car.setEngine(true);
                        break;
                    case BODY:
                        car.setPrice((int) (car.getPrice() * part.getIncreaseCarValue()));
                        car.setBody(true);
                        break;
                    case TRANSMISSION:
                        car.setPrice((int) (car.getPrice() * part.getIncreaseCarValue()));
                        car.setTransmission(true);
                        break;
                }
                printFixed(part.getName(), totalCost);
                return true;
            } else {
                printDamage(part.getName(), totalCost);
                return false;
            }
        }
        printNotEnoughMoney(part.getName(), totalCost);
        return false;
    }


    private int setPartCost (CarBrand brand, int cost){
        // Correct part cost depend on brand
        if (brand.isExpensiveBrand(brand)){
            cost *=  2;
        }
        if (brand.isAverageBrand(brand)){
            cost *= 1.5;
        }
        return cost;
    }

    protected boolean isSuccessRepair(){
        return true;
    }

    private void printFixed (String typeOfPart, int totalCost){
        System.out.println(name + ": naprawiono " + typeOfPart + ". Koszt usługi: " + totalCost + " PLN");
    }

    private void printDamage (String typeOfPart, int totalCost){
        System.out.println(name + ": niestety nie nie naprawiono " + typeOfPart
                            + ".\nKoszt usługi: " + totalCost + " PLN");
    }

    private void printNotEnoughMoney (String typeOFPart, int totalCost){
        System.out.println(name + ": masz za mało gotówki. Potrzebujesz na naprawę "
                            + typeOFPart + " potrzebujesz: " + totalCost + " PLN");
    }

    @Override
    public int getPriceManHour() {
        return 0;
    }

    public String getName() {
        return name;
    }
}
