package main.model.wallet;

import main.model.vehicle.CarBrand;

public class WalletOperation {

    private int amount;
    private Action action;
    private Wallet wallet;
    private CarBrand carBrand;
    public static final int WASH_COST = 100;

    public WalletOperation(int amount, Action action, Wallet wallet) {
        this.amount = amount;
        this.action = action;
        this.wallet = wallet;
    }

    public WalletOperation addCarBrand (CarBrand carBrand){
        this.carBrand = carBrand;
        return this;
    }

    public boolean call (){
        boolean flag = false;

        switch (action){
            case BUY_CAR:
                flag = wallet.withdraw(amount, getOpeDescriptionWithBrand());
                break;

            case SELL_CAR:
                flag = wallet.deposit(amount, getOpeDescriptionWithBrand());
                break;

            case REPAIR: case BUY_ADVERTISEMENT:
                return wallet.withdraw(amount, getOpeDescription());

            case PAY_WASH:
                amount = WASH_COST;
                return wallet.withdraw(amount, getOpeDescriptionWithBrand());

            case PAY_TAX:
                amount = (int) (0.02 * amount);
                return wallet.withdraw(amount, getOpeDescriptionWithBrand());

            default:
                return false;
        }

        if (flag){
            // pay wash
            new WalletOperation(amount,Action.PAY_WASH, wallet).addCarBrand(carBrand).call();

            // pas tax
            new WalletOperation(amount, Action.PAY_TAX, wallet).addCarBrand(carBrand).call();
        }
        return flag;
    }

    private String getOpeDescription(){
        return  "-" +
                this.amount +
                "\tPLN\t" +
                action.getDescription();
    }

    private String getOpeDescriptionWithBrand (){
        return  "-" +
                this.amount +
                "\tPLN\t" +
                action.getDescription() + "\t" +
                carBrand.name();
    }
}
