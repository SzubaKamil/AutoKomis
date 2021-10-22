package main.model.mechanic;

import main.model.wallet.Wallet;
import main.model.vehicle.Car;

import java.util.Random;

public class AdrianMechanic extends Mechanic{

    private final int price = 200;

    public AdrianMechanic() {
        this.name = "Adrian";
    }

    @Override
    public boolean fixPart(Car car, Wallet wallet, Part part) {
        if (isDamagedSomething()){
            damagePart(car, damageId());
        }
        System.out.println("INSIDE ADRAIAN MECHACIC2");
        return super.fixPart(car, wallet, part);
    }

    @Override
    public int getPriceManHour() {
        return price;
    }

    @Override
    protected boolean isSuccessRepair() {
        Random random = new Random();
        return random.nextInt(10) >= 2; // 10 % szansy ze sie nie uda
    }

    private int damageId (){
        Random random = new Random();
        return random.nextInt(5) +1;
    }

    private boolean isDamagedSomething (){
        Random random = new Random();
        return random.nextInt(100) < 2;
    }

    private Car damagePart (Car car, int index){
        if (index == 1){ // damage breaks
            car.setBreaks(false);
            System.out.println("Niestety podczas naprawy mechanik zepsuł hamulce.");
        }
        if (index == 2){ //damage suspension
            car.setSuspension(false);
            System.out.println("Niestety podczas naprawy mechanik zepsuł zawieszenie.");
        }
        if (index == 3){ //damage engine
            car.setEngine(false);
            System.out.println("Niestety podczas naprawy mechanik zepsuł silnik.");
        }
        if (index == 4){ //damage body
            car.setBody(false);
            System.out.println("Niestety podczas naprawy mechanik zepsuł nadwozie.");
        }
        if (index == 5){ //damage transmission
            car.setTransmission(false);
            System.out.println("Niestety podczas naprawy mechanik zepsuł skrzynię biegów.");
        }
        return car;
    }
}
