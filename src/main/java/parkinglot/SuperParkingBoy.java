package parkinglot;

import java.util.ArrayList;
import java.util.Optional;

public class SuperParkingBoy extends ParkingBoy {

    public SuperParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected ParkingLot findParkingLot() {
        Optional<ParkingLot> maxVacancyRateParkingLot = parkingLots.stream()
                .max((o1, o2) -> o1.getVacancyRate().compareTo(o2.getVacancyRate()));

        return maxVacancyRateParkingLot.get();
    }
}
