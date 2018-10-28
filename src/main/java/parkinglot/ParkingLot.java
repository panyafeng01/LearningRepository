package parkinglot;

import java.util.HashMap;

public class ParkingLot {
    private final int capacity;
    private HashMap<ParkTicket, Car> cars;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        cars = new HashMap<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCarTotal() {
        return cars.size();
    }

    public Integer getVacancy() {
        return capacity - cars.size();
    }

    public Float getVacancyRate() {
        return (float)getVacancy() / capacity;
    }

    public ParkTicket park(Car car) {
        if(isFull()) {
            throw new ParkingLotFullException();
        }

        ParkTicket parkTicket = new ParkTicket();
        cars.put(parkTicket, car);
        return parkTicket;
    }

    public Car pickUp(ParkTicket parkTicket) {
        return cars.remove(parkTicket);
    }

    public Boolean isFull() {
        return cars.size() >= capacity;
    }

}
