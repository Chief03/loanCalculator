// Loan.java
public class Loan implements LoanInterface {
    private final double principal;  // e.g. 250_000.0
    private final double annualRate; // e.g. 0.05 for 5%
    private final int    termYears;  // e.g. 30

    public Loan(double principal, double annualRate, int termYears) {
        if (principal <= 0 || termYears <= 0 || annualRate < 0) {
            throw new IllegalArgumentException(
                "Principal and term must be positive; rate cannot be negative"
            );
        }
        this.principal  = principal;
        this.annualRate = annualRate;
        this.termYears  = termYears;
    }

    @Override
    public double calculateMonthlyPayment() {
        int totalMonths = termYears * 12;

        // zero‐interest fallback
        if (annualRate == 0) {
            return principal / totalMonths;
        }

        double monthlyRate = annualRate / 12;
        double factor      = Math.pow(1 + monthlyRate, totalMonths);
        return principal * monthlyRate * factor / (factor - 1);
    }

    @Override
    public double calculateTotalInterest() {
        // total paid minus principal
        return calculateMonthlyPayment() * (termYears * 12) - principal;
    }
}
