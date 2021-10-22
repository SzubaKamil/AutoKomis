package main.model.wallet;

public enum Action {
    BUY_CAR("Zakupu "),
    REPAIR ("Naprawa"),
    BUY_ADVERTISEMENT ("Zakup reklamy"),
    SELL_CAR("Sprzedaż "),
    PAY_TAX("Podatek"),
    PAY_WASH ("Myjnia");

    private final String  description;

    Action(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
