// LoanCalculator.java
public class LoanCalculator extends Loan {
    /**
     * Construct a LoanCalculator by delegating
     * all work to the immutable Loan base class.
     */
    public LoanCalculator(double principal, double annualRatePct, int termYears) {
        super(principal, annualRatePct, termYears);
    }
}
