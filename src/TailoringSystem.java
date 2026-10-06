import java.util.List;
import java.util.Scanner;

/**
 * Menu-driven command-line application for the Tailoring Shop charging system.
 * Student : Nayebale Irene
 * Reg No  : VU-DIT-2511-1539-DAY
 * Discount: 8% for loyal customers
 * Sort    : Charge highest first
 */
public class TailoringSystem {

    private final JobManager manager = new JobManager();
    private final InputHelper input;
    private final Scanner scanner;

    public TailoringSystem() {
        this.scanner = new Scanner(System.in);
        this.input = new InputHelper(scanner);
    }

    public static void main(String[] args) {
        TailoringSystem app = new TailoringSystem();
        app.run();
    }

    private void run() {
        printWelcome();
        boolean running = true;
        while (running) {
            printMenu();
            int choice = input.readMenuChoice(1, 7);
            System.out.println();
            switch (choice) {
                case 1 -> addRepairJob();
                case 2 -> addNewGarmentJob();
                case 3 -> listSortedReport();
                case 4 -> findJob();
                case 5 -> removeJob();
                case 6 -> showSummary();
                case 7 -> {
                    System.out.println("Thank you for using the Tailoring Shop system. Goodbye!");
                    running = false;
                }
            }
            if (running) {
                System.out.println();
            }
        }
        scanner.close();
    }

    private void printWelcome() {
        System.out.println("=======================================================");
        System.out.println("   TAILORING SHOP CHARGING SYSTEM");
        System.out.println("   Student : Nayebale Irene");
        System.out.println("   Reg No  : VU-DIT-2511-1539-DAY");
        System.out.println("   Discount: 8% for loyal customers");
        System.out.println("=======================================================");
        System.out.println();
    }

    private void printMenu() {
        System.out.println("---------------------- MENU --------------------------");
        System.out.println("  1. Add Repair Job");
        System.out.println("  2. Add New Garment Job");
        System.out.println("  3. List Sorted Report (highest charge first)");
        System.out.println("  4. Find a Job by ID");
        System.out.println("  5. Remove a Job");
        System.out.println("  6. Show Summary (totals)");
        System.out.println("  7. Exit");
        System.out.println("-------------------------------------------------------");
    }

    private void addRepairJob() {
        System.out.println("--- Add Repair Job ---");
        try {
            String name = input.readNonEmptyString("Customer name: ");
            boolean loyal = input.readYesNo("Is the customer loyal?");
            int items = input.readPositiveInt("Number of items: ");
            String id = manager.generateId();
            RepairJob job = new RepairJob(id, name, loyal, items);
            manager.addJob(job);
            System.out.println("Repair job added successfully.");
            System.out.println("  ID     : " + id);
            System.out.println("  Charge : " + String.format("%,.0f UGX", job.calculateCharge()));
            if (loyal) {
                System.out.println("  Discount applied: 8% (loyal customer)");
            }
        } catch (InvalidJobException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addNewGarmentJob() {
        System.out.println("--- Add New Garment Job ---");
        try {
            String name = input.readNonEmptyString("Customer name: ");
            boolean loyal = input.readYesNo("Is the customer loyal?");
            double fabric = input.readNonNegativeDouble("Fabric cost (UGX): ");
            String id = manager.generateId();
            NewGarmentJob job = new NewGarmentJob(id, name, loyal, fabric);
            manager.addJob(job);
            System.out.println("New garment job added successfully.");
            System.out.println("  ID     : " + id);
            System.out.println("  Charge : " + String.format("%,.0f UGX", job.calculateCharge()));
            if (loyal) {
                System.out.println("  Discount applied: 8% (loyal customer)");
            }
        } catch (InvalidJobException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listSortedReport() {
        System.out.println("--- Sorted Report (Charge highest first) ---");
        if (manager.isEmpty()) {
            System.out.println("No jobs recorded yet.");
            return;
        }
        List<Job> sorted = manager.getSortedReport();
        System.out.println(String.format("%-12s | %-20s | %-12s | %-10s | %s",
                "ID", "Customer", "Type", "Loyal", "Charge (UGX)"));
        System.out.println("-----------------------------------------------------------------------");
        for (Chargeable c : sorted) {          // polymorphic loop
            Job j = (Job) c;
            System.out.println(String.format("%-12s | %-20s | %-12s | %-10s | %,12.0f",
                    j.getId(),
                    j.getCustomerName(),
                    j.getJobType(),
                    j.isLoyal() ? "Yes" : "No",
                    c.calculateCharge()));
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Total jobs: " + manager.getJobCount());
    }

    private void findJob() {
        System.out.println("--- Find Job ---");
        String id = input.readNonEmptyString("Enter Job ID: ");
        Job job = manager.findJobById(id);
        if (job == null) {
            System.out.println("No job found with ID: " + id);
        } else {
            System.out.println("Found:");
            System.out.println(job);
            System.out.println("  Final charge : " + String.format("%,.0f UGX", job.calculateCharge()));
            System.out.println("  Discount given: " + String.format("%,.0f UGX", job.getDiscountGiven()));
        }
    }

    private void removeJob() {
        System.out.println("--- Remove Job ---");
        String id = input.readNonEmptyString("Enter Job ID to remove: ");
        Job job = manager.findJobById(id);
        if (job == null) {
            System.out.println("No job found with ID: " + id);
            return;
        }
        System.out.println("About to remove:");
        System.out.println(job);
        boolean confirm = input.readYesNo("Are you sure you want to remove this job?");
        if (confirm) {
            manager.removeJob(id);
            System.out.println("Job " + id + " has been removed.");
        } else {
            System.out.println("Removal cancelled.");
        }
    }

    private void showSummary() {
        System.out.println("--- Summary ---");
        if (manager.isEmpty()) {
            System.out.println("No jobs recorded yet.");
            return;
        }
        System.out.println("Number of jobs        : " + manager.getJobCount());
        System.out.println("Total charges (final) : " + String.format("%,.0f UGX", manager.getTotalCharges()));
        System.out.println("Total discount given  : " + String.format("%,.0f UGX", manager.getTotalDiscountGiven()));
    }
}
