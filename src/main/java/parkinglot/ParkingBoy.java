package parkinglot;

import java.util.ArrayList;

public class ParkingBoy extends ParkingLotManager {
    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    protected ParkingLot findParkingLot() {
        ParkingLot resultParkingLot = null;
        for (ParkingLot parkingLot : parkingLots) {
            Boolean isFull = parkingLot.isFull();
            if(isFull) {
                continue;
            }
            resultParkingLot = parkingLot;
            break;
        }

        return resultParkingLot;
    }
}
