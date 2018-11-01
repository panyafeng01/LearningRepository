package parkinglot;

import java.util.ArrayList;

public class ParkingBoy extends ParkingLotManager {
    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    protected ParkingLot findParkingLot() {
        for (ParkingLot parkingLot : parkingLots) {
            if(parkingLot.isFull()) {
                continue;
            }
            return parkingLot;
        }

        return null;
    }
}
