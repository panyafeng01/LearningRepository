package parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkingDirectorTest {
    @Test
    public void should_return_format_report() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(3);
        ParkingLot parkingLot3 = new ParkingLot(5);
        ParkingLot parkingLot4 = new ParkingLot(10);
        ArrayList<ParkingLot> parkingLots1 = new ArrayList<>();
        parkingLots1.add(parkingLot2);
        parkingLots1.add(parkingLot1);
        ParkingBoy parkingBoy1 = new ParkingBoy(parkingLots1);
        ArrayList<ParkingLot> parkingLots2 = new ArrayList<>();
        parkingLots2.add(parkingLot3);
        ParkingBoy parkingBoy2 = new ParkingBoy(parkingLots2);
        ArrayList<ParkingLot> parkingLots3 = new ArrayList<>();
        parkingLots3.add(parkingLot4);
        ArrayList<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy2);
        parkingBoys.add(parkingBoy1);
        ParkingManager parkingManager = new ParkingManager(parkingLots3, parkingBoys);

        //when
        parkingLot1.park(new Car());

        parkingLot2.park(new Car());
        parkingLot2.park(new Car());
        parkingLot2.park(new Car());

        parkingLot3.park(new Car());
        parkingLot3.park(new Car());
        parkingLot3.park(new Car());

        parkingLot4.park(new Car());
        parkingLot4.park(new Car());
        parkingLot4.park(new Car());
        parkingLot4.park(new Car());
        parkingLot4.park(new Car());
        parkingLot4.park(new Car());
        parkingLot4.park(new Car());
        parkingLot4.park(new Car());

        //then
        ParkingDirector parkingDirector = new ParkingDirector();
        String reportString = parkingDirector.parkingReport(parkingManager);
        StringBuffer str = new StringBuffer();
        str.append("M 5 20").append("\n")
           .append("  P 2 10").append("\n")
           .append("  B 2 5").append("\n")
           .append("    P 2 5").append("\n")
           .append("  B 1 5").append("\n")
           .append("    P 0 3").append("\n")
           .append("    P 1 2").append("\n");
        assertThat(reportString).isEqualTo(str.toString());
    }
}
