package parkinglot;

import java.util.ArrayList;
import java.util.Optional;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkTicket park(Car car) {

        Optional<ParkingLot> maxVacancyParkingLot = parkingLots.stream()
                .max((o1, o2) -> o1.getVacancy().compareTo(o2.getVacancy()));
        if(maxVacancyParkingLot.get().isFull()) {
            throw new ParkingLotFullException();
        }

        return maxVacancyParkingLot.get().park(car);
    }

}
