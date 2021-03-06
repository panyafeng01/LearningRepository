package parkinglot;

import java.util.ArrayList;

public abstract class ParkingLotManager {
    public final ArrayList<ParkingLot> parkingLots;

    public ParkingLotManager(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ArrayList<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public Integer getSeatTotal() {
        int seatTotal = 0;
        for (ParkingLot parkingLot : parkingLots) {
            seatTotal = seatTotal + parkingLot.getCapacity();
        }
        return seatTotal;
    }

    public Integer getCarTotal() {
        int carTotal = 0;
        for (ParkingLot parkingLot : parkingLots) {
            carTotal = carTotal + parkingLot.getCarTotal();
        }
        return carTotal;
    }

    public Integer getVacancy() {
        int vacancy = 0;
        for (ParkingLot parkingLot : parkingLots) {
            vacancy = vacancy + parkingLot.getVacancy();
        }
        return vacancy;
    }

    public ParkTicket park(Car car) {
        ParkingLot parkingLot = findParkingLot();

        checkParkingLotFull(parkingLot);

        return parkingLot.park(car);
    }

    private void checkParkingLotFull(ParkingLot parkingLot) {
        if(parkingLot == null) {
            throw new ParkingLotFullException();
        }
    }

    protected abstract ParkingLot findParkingLot();

    public Car pickUp(ParkTicket parkTicket) {
        for (ParkingLot parkingLot : parkingLots) {
            Car car = parkingLot.pickUp(parkTicket);
            if(car != null) {
                return car;
            }
        }
        return null;
    }

}
