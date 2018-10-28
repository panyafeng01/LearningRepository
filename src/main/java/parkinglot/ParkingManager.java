package parkinglot;

import java.util.ArrayList;
import java.util.Optional;

public class ParkingManager extends ParkingBoy {
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
        if(parkingBoys != null && parkingBoys.size() > 0) {
            Optional<ParkingBoy> maxVacancyParkingBoy = parkingBoys.stream()
                    .max((o1, o2) -> o1.getVacancy().compareTo(o2.getVacancy()));
            int maxVacancyOfParkingBoys = maxVacancyParkingBoy.get().getVacancy();
            if(maxVacancyOfParkingBoys > this.getVacancy()) {
                return assignPark(car, maxVacancyParkingBoy.get());
            }
        }

        return super.park(car);
    }

    @Override
    public Car pickUp(ParkTicket parkTicket) {
        Car pickUpCar = super.pickUp(parkTicket);
        if(pickUpCar == null) {
            for (ParkingBoy parkingBoy : parkingBoys) {
                pickUpCar = parkingBoy.pickUp(parkTicket);
                if(pickUpCar != null) {
                    return pickUpCar;
                }
            }
        }
        return pickUpCar;
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
