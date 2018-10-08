package parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ParkingBoyTest extends ParkingBoyBaseTest {

    @Override
    protected ParkingBoy createParkingBoy(ArrayList<ParkingLot> parkingLots) {
        return new ParkingBoy(parkingLots);
    }

    @Test
    public void should_park_2_lot_when_1_lot_is_full() {
        //given
        ArrayList<ParkingLot> parkingLots = new ArrayList();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Car car1 = new Car();
        Car car2 = new Car();

        //when
        ParkTicket parkTicket1 = parkingBoy.park(car1);
        ParkTicket parkTicket2 = parkingBoy.park(car2);
        Car pickUpCar1 = parkingLot1.pickUp(parkTicket1);
        Car pickUpCar2 = parkingLot2.pickUp(parkTicket2);

        //then
        assertThat(pickUpCar1).isEqualTo(car1);
        assertThat(pickUpCar2).isEqualTo(car2);
    }

}