package parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class SmartParkingBoyTest extends ParkingBoyBaseTest {

    @Override
    protected ParkingBoy createParkingBoy(ArrayList<ParkingLot> parkingLots) {
        return new SmartParkingBoy(parkingLots);
    }

    @Test
    public void should_park_max_vacancy_lot_when_park_one_car() {
        //given
        ArrayList<ParkingLot> parkingLots = new ArrayList();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car car = new Car();

        //when
        ParkTicket parkTicket = smartParkingBoy.park(car);
        Car pickUpCar1 = parkingLot1.pickUp(parkTicket);
        Car pickUpCar2 = parkingLot2.pickUp(parkTicket);

        //then
        assertThat(pickUpCar1).isNull();
        assertThat(pickUpCar2).isEqualTo(car);
    }

    @Test
    public void should_park_first_lot_when_multiple_max_vacancy_lot() {
        //given
        ArrayList<ParkingLot> parkingLots = new ArrayList();
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();

        //when
        ParkTicket parkTicket1 = smartParkingBoy.park(car1);
        ParkTicket parkTicket2 = smartParkingBoy.park(car2);
        ParkTicket parkTicket3 = smartParkingBoy.park(car3);

        Car pickUpCar1 = parkingLot1.pickUp(parkTicket1);
        Car pickUpCar2 = parkingLot2.pickUp(parkTicket2);
        Car pickUpCar3 = parkingLot1.pickUp(parkTicket3);

        //then
        assertThat(pickUpCar1).isEqualTo(car1);
        assertThat(pickUpCar2).isEqualTo(car2);
        assertThat(pickUpCar3).isEqualTo(car3);
    }

}
