package main.model.mechanic;



public class JanuszMechanic extends Mechanic {

    private final int price = 400;

    public JanuszMechanic() {
        this.name = "Janusz";
    }

    @Override
    public int getPriceManHour() {
        return price;
    }

}
