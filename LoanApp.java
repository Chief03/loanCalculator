// LoanApp.java
import java.util.Scanner;

public class LoanApp {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            // 1) Prompt for principal (or quit)
            double principal = promptDouble("Enter loan principal (or 'quit'): ");
            if (Double.isNaN(principal)) {
                break;
            }

            // 2) Prompt for annual rate (%)
            double annualPct = promptDouble("Enter annual rate (%): ");

            // 3) Prompt for term (years)
            int termYears = (int) promptDouble("Enter term (years): ");

            // 4) Build, calculate & display
            try {
                LoanInterface loan = new LoanCalculator(principal, annualPct, termYears);
                System.out.printf("→ Monthly payment: $%.2f%n", loan.calculateMonthlyPayment());
                System.out.printf("→ Total interest : $%.2f%n%n", loan.calculateTotalInterest());
            } catch (IllegalArgumentException e) {
                System.out.println("! " + e.getMessage() + "\n");
            }
        }

        System.out.println("Goodbye!");
        SCANNER.close();
    }

    /**
     * Repeatedly prompt until the user enters a valid double or "quit".
     * @return NaN if the user typed "quit", otherwise the parsed double.
     */
    private static double promptDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = SCANNER.nextLine().trim();

            if (line.equalsIgnoreCase("quit")) {
                return Double.NaN;
            }

            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid number. Try again or type 'quit'.");
            }
        }
    }
}
