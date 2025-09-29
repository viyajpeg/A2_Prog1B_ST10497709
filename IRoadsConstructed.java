// Author and ST number: Viyashna Mathapursad - ST10497709


// Section B of Assignment 2


public interface IRoadsConstructed {

    String getCity();
    int getTotalRoadsConstructed();
}

public abstract class RoadsConstructed implements IRoadsConstructed {

    private String city;
    private int totalRoadsConstructed;

    // Constructor
    public RoadsConstructed(String city, int totalRoadsConstructed) {
        this.city = city;
        this.totalRoadsConstructed = totalRoadsConstructed;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public int getTotalRoadsConstructed() {
        return totalRoadsConstructed;
    }
}

public class RoadConstructionReport extends RoadsConstructed {

    // Constructor calling the parent constructor
    public RoadConstructionReport(String city, int totalRoadsConstructed) {
        super(city, totalRoadsConstructed);
    }

    public void printRoadsConstructedReport() {
        System.out.println("\nROAD CONSTRUCTION REPORT");
        System.out.println("------------------------");
        System.out.println("CITY: " + getCity());
        System.out.println("ROADS CONSTRUCTED: " + getTotalRoadsConstructed());
    }
}


import java.util.Scanner;

public class RoadReportRunApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalRoads = 0;

        System.out.println("--- ROAD CONSTRUCTION REPORT INPUT ---");

        System.out.print("Enter the city for road construction: ");
        String city = scanner.nextLine();

        System.out.print("Enter the total road construction count for " + city + ": ");

        try {
            totalRoads = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for road count. Using 0 for report.");
        }

        // Instantiate the subclass
        RoadConstructionReport report = new RoadConstructionReport(city, totalRoads);

        // Run the report method
        report.printRoadsConstructedReport();

        scanner.close();
    }
}

// ChatGPT was used to help format my code as well as to improve my comments.