package parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class ParkingBoyBaseTest {

    protected abstract ParkingBoy createParkingBoy(ArrayList<ParkingLot> parkingLots);

    @Test
    public void should_throw_exception_when_lots_are_full() {
        ArrayList<ParkingLot> parkingLots = new ArrayList();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = createParkingBoy(parkingLots);

        Car car1 = new Car();
        Car car2 = new Car();

        //when
        parkingBoy.park(car1);
        parkingBoy.park(car2);

        //then
        assertThrows(ParkingLotFullException.class, ()-> parkingBoy.park(new Car()));
    }

    @Test
    public void should_return_car_when_pick_up_by_ticket() {
        ArrayList<ParkingLot> parkingLots = new ArrayList();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = createParkingBoy(parkingLots);

        Car car1 = new Car();
        Car car2 = new Car();

        parkingBoy.park(car1);
        ParkTicket parkTicket2 = parkingBoy.park(car2);

        //when
        Car pickUpCar = parkingBoy.pickUp(parkTicket2);

        //then
        assertThat(pickUpCar).isEqualTo(car2);
    }

}
