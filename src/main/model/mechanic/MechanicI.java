package main.model.mechanic;

import main.model.vehicle.Car;
import main.model.wallet.Wallet;

public interface MechanicI {
    boolean fixPart (Car car, Wallet wallet, Part part);
    int getPriceManHour();
}
