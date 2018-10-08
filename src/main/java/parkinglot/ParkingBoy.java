package parkinglot;

import java.util.ArrayList;

public class ParkingBoy {
    public final ArrayList<ParkingLot> parkingLots;

    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
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

    public Integer getVacancy() {
        int vacancy = 0;
        for (ParkingLot parkingLot : parkingLots) {
            vacancy = vacancy + parkingLot.getVacancy();
        }
        return vacancy;
    }

    public ParkTicket park(Car car) {
        ParkTicket parkTicket = null;
        for (ParkingLot parkingLot : parkingLots) {
            Boolean isFull = parkingLot.isFull();
            if(isFull) {
                continue;
            }
            parkTicket = parkingLot.park(car);
            return parkTicket;
        }

        if(parkTicket == null) {
            throw new ParkingLotFullException();
        }

        return null;
    }

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
