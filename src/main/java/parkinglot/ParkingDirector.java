package parkinglot;

import java.util.ArrayList;

public class ParkingDirector {
    private ArrayList<ParkingManager> parkingManagers;
    private ParkingReport parkingReport;

    public ParkingDirector(ArrayList<ParkingManager> parkingManagers, ParkingReport parkingReport) {
        this.parkingManagers = parkingManagers;
        this.parkingReport = parkingReport;
    }

    public String generateReport() {
        StringBuffer parkingReportString = new StringBuffer("");

        for (ParkingManager parkingManager : parkingManagers) {
            parkingReportString.append(parkingReport.generateReport(parkingManager));
        }

        return parkingReportString.toString();
    }
}
