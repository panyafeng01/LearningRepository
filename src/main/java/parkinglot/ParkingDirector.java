package parkinglot;

public class ParkingDirector {
    public String parkingReport(ParkingManager parkingManager) {
        return managerReport(parkingManager);
    }

    private String managerReport(ParkingManager parkingManager) {
        StringBuffer boyReportString = new StringBuffer("");
        for (ParkingBoy parkingBoy : parkingManager.getParkingBoys()) {
            boyReportString.append("  ").append(boyReport(parkingBoy));
        }

        StringBuffer managerReportString = new StringBuffer("");
        managerReportString.append("M ").append(parkingManager.getVacancyIncludeBoys()).append(" ").append(parkingManager.getSeatTotalIncludeBoys()).append("\n")
                .append(managerLotReport(parkingManager))
                .append(boyReportString);
        System.out.println(managerReportString.toString());
        return managerReportString.toString();
    }

    private String managerLotReport(ParkingManager parkingManager) {
        StringBuffer lotReportString = new StringBuffer("");
        for (ParkingLot parkingLot : parkingManager.getParkingLots()) {
            lotReportString.append("  ").append(lotReport(parkingLot)).append("\n");
        }
        return lotReportString.toString();
    }

    private String boyReport(ParkingBoy parkingBoy) {
        int seatTotal = 0;
        int vacancy = 0;
        StringBuffer lotReportString = new StringBuffer("");
        for (ParkingLot parkingLot : parkingBoy.getParkingLots()) {
            seatTotal = seatTotal + parkingLot.getCapacity();
            vacancy = vacancy + parkingLot.getVacancy();
            lotReportString.append("    ").append(lotReport(parkingLot)).append("\n");
        }

        StringBuffer boyReportString = new StringBuffer("");
        boyReportString.append("B ").append(vacancy).append(" ").append(seatTotal).append("\n")
                .append(lotReportString);

        return boyReportString.toString();
    }

    private String lotReport(ParkingLot parkingLot) {
        return new StringBuffer("P ").append(parkingLot.getVacancy()).append(" ").append(parkingLot.getCapacity()).toString();
    }
}