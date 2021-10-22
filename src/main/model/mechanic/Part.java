package main.model.mechanic;

public enum Part {
    BREAK ("hamulce", 500, 1.1, 2),
    SUSPENSION("zawieszenie", 800, 1.2, 5),
    ENGINE("silnik", 1500, 2, 10),
    BODY("karoseria", 1000, 1.5, 6),
    TRANSMISSION("skrzynia biegów", 1300, 1.5, 9);

    private String name;
    private int price;
    private double increaseCarValue;
    private int manHour;

    Part(String name, int price, double increaseCarValue, int manHour) {
        this.name = name;
        this.price = price;
        this.increaseCarValue = increaseCarValue;
        this.manHour = manHour;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public double getIncreaseCarValue() {
        return increaseCarValue;
    }

    public int getManHour() {
        return manHour;
    }
}
