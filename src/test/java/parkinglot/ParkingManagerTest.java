package parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingManagerTest {

    @Test
    public void should_park_lot_2_when_max_vacancy_and_lot_1_is_full() {
        //given
        ArrayList<ParkingLot> parkingLots = new ArrayList();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingManager parkingManager = new ParkingManager(parkingLots, null);

        Car car1 = new Car();
        Car car2 = new Car();

        //when
        ParkTicket parkTicket1 = parkingManager.park(car1);
        ParkTicket parkTicket2 = parkingManager.park(car2);
        Car pickUpCar1 = parkingLot1.pickUp(parkTicket1);
        Car pickUpCar2 = parkingLot2.pickUp(parkTicket2);

        //then
        assertThat(pickUpCar1).isEqualTo(car1);
        assertThat(pickUpCar2).isEqualTo(car2);
    }

    @Test
    public void should_boy_2_park_when_boy_2_max_vacancy() {
        //given
        ArrayList<ParkingLot> parkingLots1 = new ArrayList();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots1.add(parkingLot1);
        ParkingBoy parkingBoy1 = new SmartParkingBoy(parkingLots1);

        ArrayList<ParkingLot> parkingLots2 = new ArrayList();
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLots2.add(parkingLot2);
        ParkingBoy parkingBoy2 = new SuperParkingBoy(parkingLots2);

        ArrayList<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy1);
        parkingBoys.add(parkingBoy2);

        ArrayList<ParkingLot> parkingLots3 = new ArrayList();
        ParkingLot parkingLot3 = new ParkingLot(1);
        parkingLots3.add(parkingLot3);

        ParkingManager parkingManager = new ParkingManager(parkingLots3, parkingBoys);

        Car car = new Car();

        //when
        ParkTicket parkTicket = parkingManager.park(car);

        //then
        parkingBoy2.pickUp(parkTicket).equals(car);
    }

    @Test
    public void should_return_car_when_pick_up() {
        //given
        ArrayList<ParkingLot> parkingLots1 = new ArrayList();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots1.add(parkingLot1);
        ParkingBoy parkingBoy1 = new SmartParkingBoy(parkingLots1);

        ArrayList<ParkingLot> parkingLots2 = new ArrayList();
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLots2.add(parkingLot2);
        ParkingBoy parkingBoy2 = new SuperParkingBoy(parkingLots2);

        ArrayList<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy1);
        parkingBoys.add(parkingBoy2);

        ArrayList<ParkingLot> parkingLots3 = new ArrayList();
        ParkingLot parkingLot3 = new ParkingLot(1);
        parkingLots3.add(parkingLot3);

        ParkingManager parkingManager = new ParkingManager(parkingLots3, parkingBoys);

        Car car = new Car();

        //when
        ParkTicket parkTicket = parkingManager.park(car);

        //then
        //parkingBoy2.pickUp(parkTicket).equals(car);
        parkingManager.pickUp(parkTicket).equals(car);
    }

    @Test
    public void should_throw_exception_when_lots_are_full() {
        ArrayList<ParkingLot> parkingLots = new ArrayList();
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLots.add(parkingLot1);

        ArrayList<ParkingLot> parkingLots1 = new ArrayList();
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLots1.add(parkingLot2);
        ParkingBoy parkingBoy = new SmartParkingBoy(parkingLots1);
        ArrayList<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);

        ParkingManager parkingManager = new ParkingManager(parkingLots, parkingBoys);

        Car car1 = new Car();
        Car car2 = new Car();

        //when
        parkingManager.park(car1);
        parkingManager.park(car2);

        //then
        assertThrows(ParkingLotFullException.class, ()-> parkingManager.park(new Car()));
    }

}
