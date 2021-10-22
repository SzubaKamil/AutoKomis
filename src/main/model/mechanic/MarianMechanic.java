package main.model.mechanic;

import java.util.Random;

public class MarianMechanic  extends Mechanic {

    private final int priceManHour = 300;

    public MarianMechanic() {
        this.name = "Marian";
    }

    @Override
    public int getPriceManHour() {
        return priceManHour;
    }

    @Override
    protected boolean isSuccessRepair() {
        Random random = new Random();
        return random.nextInt(10) >= 1; // 10 % szansy ze sie nie uda
    }

}

