package parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class SuperParkingBoyTest extends ParkingBoyBaseTest {

    @Override
    protected ParkingBoy createParkingBoy(ArrayList<ParkingLot> parkingLots) {
        return new SuperParkingBoy(parkingLots);
    }

    @Test
    public void should_park_max_vacancy_rate_lot_when_park_one_car() {
        //given
        ArrayList<ParkingLot> parkingLots = new ArrayList();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(3);
        parkingLot2.park(new Car());

        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        Car car = new Car();

        //when
        ParkTicket parkTicket = superParkingBoy.park(car);
        Car pickUpCar1 = parkingLot1.pickUp(parkTicket);
        Car pickUpCar2 = parkingLot2.pickUp(parkTicket);

        //then
        assertThat(pickUpCar1).isEqualTo(car);
        assertThat(pickUpCar2).isNull();
    }

    @Test
    public void should_park_first_lot_when_multiple_max_vacancy_rate_lot() {
        //given
        ArrayList<ParkingLot> parkingLots = new ArrayList();
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy superParkingBoy = new SuperParkingBoy(parkingLots);

        Car car = new Car();

        //when
        ParkTicket parkTicket = superParkingBoy.park(car);
        Car pickUpCar1 = parkingLot1.pickUp(parkTicket);

        //then
        assertThat(pickUpCar1).isEqualTo(car);
    }

}
