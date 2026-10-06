import java.util.Scanner;

/**
 * Helper class that guarantees robust, crash-free input.
 * Re-prompts until the user supplies a valid value.
 */
public class InputHelper {
    private final Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("  >> Input cannot be blank. Please try again.");
        }
    }

    public int readPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value > 0) {
                    return value;
                }
                System.out.println("  >> Value must be a positive integer. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("  >> Invalid number. Please enter a whole number.");
            }
        }
    }

    public double readNonNegativeDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(line);
                if (value >= 0) {
                    return value;
                }
                System.out.println("  >> Value cannot be negative. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("  >> Invalid number. Please enter a numeric value.");
            }
        }
    }

    public boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String line = scanner.nextLine().trim().toLowerCase();
            if (line.equals("y") || line.equals("yes")) {
                return true;
            }
            if (line.equals("n") || line.equals("no")) {
                return false;
            }
            System.out.println("  >> Please answer y or n.");
        }
    }

    public int readMenuChoice(int min, int max) {
        while (true) {
            System.out.print("Enter your choice (" + min + "-" + max + "): ");
            String line = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(line);
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.println("  >> Choice must be between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("  >> Invalid input. Please enter a number.");
            }
        }
    }
}
