package parkinglot;

import java.util.ArrayList;
import java.util.Optional;

public class ParkingManager extends ParkingLotManager {
    private final ArrayList<ParkingBoy> parkingBoys;

    public ParkingManager(ArrayList<ParkingLot> parkingLots, ArrayList<ParkingBoy> parkingBoys) {
        super(parkingLots);
        this.parkingBoys = parkingBoys;
    }

    public ArrayList<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    @Override
    public ParkTicket park(Car car) {
        Optional<ParkingBoy> parkingBoy = findMaxVacancyParkingBoy();
        int vacancyOfParkingBoy = 0;
        if(parkingBoy.isPresent()) {
            vacancyOfParkingBoy = parkingBoy.get().getVacancy();
        }

        if(vacancyOfParkingBoy > this.getVacancy()) {
            return assignPark(car, parkingBoy.get());
        } else {
            return super.park(car);
        }
    }

    private Optional<ParkingBoy> findMaxVacancyParkingBoy() {
        if(parkingBoys != null && parkingBoys.size() > 0) {
            return parkingBoys.stream()
                    .max((o1, o2) -> o1.getVacancy().compareTo(o2.getVacancy()));
        }
        return Optional.empty();
    }

    protected ParkingLot findParkingLot() {
        for (ParkingLot parkingLot : parkingLots) {
            Boolean isFull = parkingLot.isFull();
            if(isFull) {
                continue;
            }
            return parkingLot;
        }

        return null;
    }

    @Override
    public Car pickUp(ParkTicket parkTicket) {
        Car pickUpCar = super.pickUp(parkTicket);
        if(pickUpCar == null) {
            pickUpCar = pickUpOfParkingBoy(parkTicket);
        }
        return pickUpCar;
    }

    private Car pickUpOfParkingBoy(ParkTicket parkTicket) {
        Car pickUpCar;
        for (ParkingBoy parkingBoy : parkingBoys) {
            pickUpCar = parkingBoy.pickUp(parkTicket);
            if(pickUpCar != null) {
                return pickUpCar;
            }
        }
        return null;
    }

    private ParkTicket assignPark(Car car, ParkingBoy parkingBoy) {
        return parkingBoy.park(car);
    }

    public Integer getSeatTotalIncludeBoys() {
        int seatTotal = getSeatTotal();

        for (ParkingBoy parkingBoy : parkingBoys) {
            seatTotal = seatTotal + parkingBoy.getSeatTotal();
        }

        return seatTotal;
    }

    public Integer getCarTotalIncludeBoys() {
        int carTotal = getCarTotal();

        for (ParkingBoy parkingBoy : parkingBoys) {
            carTotal = carTotal + parkingBoy.getCarTotal();
        }

        return carTotal;
    }
}
