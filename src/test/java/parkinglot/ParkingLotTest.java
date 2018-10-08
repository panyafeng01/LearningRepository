 package parkinglot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test
    public void should_return_ticket_when_park_one_car() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        ParkTicket parkTicket = new ParkTicket();

        //when
        parkTicket = parkingLot.park(car);

        //then
        assertThat(parkTicket).isNotNull();
    }

    @Test
    public void should_throw_exception_when_lot_is_full() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car();
        Car car2 = new Car();
        ParkTicket parkTicket = new ParkTicket();

        //when
        parkTicket = parkingLot.park(car1);

        //then
        assertThat(parkTicket).isNotNull();
        assertThrows(ParkingLotFullException.class, ()-> parkingLot.park(car2));
    }

    @Test
    public void should_return_car_when_pick_up_by_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car();
        ParkTicket parkTicket = parkingLot.park(car);

        //when
        Car pickUpCar = parkingLot.pickUp(parkTicket);

        //then
        assertThat(pickUpCar).isEqualTo(car);
    }

}