package parkinglot;

import java.util.ArrayList;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    protected ParkingLot findParkingLot() {
        Optional<ParkingLot> maxVacancyParkingLot = parkingLots.stream()
                .max((o1, o2) -> o1.getVacancy().compareTo(o2.getVacancy()));

        return maxVacancyParkingLot.get();
    }

}
