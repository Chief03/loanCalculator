public class Loan {
    private final double principal;
    private final double annualRatePct;  // e.g. 5.0 for 5%
    private final int termYears;
    private final double monthlyPayment; // cached

    public Loan(double principal, double annualRatePct, int termYears) {
        if (principal <= 0 || termYears <= 0 || annualRatePct < 0) {
            throw new IllegalArgumentException("Invalid loan parameters");
        }
        this.principal     = principal;
        this.annualRatePct = annualRatePct;
        this.termYears     = termYears;
        this.monthlyPayment = computeMonthlyPayment();
    }

    private double computeMonthlyPayment() {
        int n = termYears * 12;
        double r = (annualRatePct / 100) / 12;

        if (r == 0) {
            return principal / n;
        }
        double factor = Math.pow(1 + r, n);
        return principal * (r * factor) / (factor - 1);
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public double getTotalInterest() {
        return monthlyPayment * termYears * 12 - principal;
    }

    // getters for principal, annualRatePct, termYears…
}
