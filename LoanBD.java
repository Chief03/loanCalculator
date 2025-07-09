import java.math.BigDecimal;
import java.math.MathContext;

//LoanBD.java
public class LoanBD implements LoanInterface {
    private static final MathContext MC = new MathContext(10);
    private final BigDecimal principal;
    private final BigDecimal annualRate;
    private final int        termYears;

    public LoanBD(BigDecimal p, BigDecimal r, int t) {
        if (p.signum() <= 0 || t <= 0 || r.signum() < 0) {
            throw new IllegalArgumentException(
                "Principal and term must be positive; rate cannot be negative"
            );
        }
        this.principal  = p;
        this.annualRate = r;
        this.termYears  = t;
    }

    @Override
    public BigDecimal calculateMonthlyPayment() {
        int n = termYears * 12;
        if (annualRate.signum() == 0) {
            return principal.divide(BigDecimal.valueOf(n), MC);
        }

        BigDecimal monthlyRate = annualRate.divide(BigDecimal.valueOf(12), MC);
        BigDecimal onePlusR    = BigDecimal.ONE.add(monthlyRate);
        BigDecimal factor      = onePlusR.pow(n, MC);

        return principal
            .multiply(monthlyRate, MC)
            .multiply(factor, MC)
            .divide(factor.subtract(BigDecimal.ONE, MC), MC);
    }

    @Override
    public BigDecimal calculateTotalInterest() {
        BigDecimal months = BigDecimal.valueOf(termYears * 12);
        return calculateMonthlyPayment()
            .multiply(months, MC)
            .subtract(principal, MC);
    }
}
