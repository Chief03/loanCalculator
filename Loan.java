public class Loan implements LoanInterface {
    private final double principal;        // e.g. 250_000.00
    private final double annualRate;      // 0.05 for 5%
    private final int termYears;          // e.g. 30

    public Loan(double principal, double annualRate, int termYears) {
        if (principal <= 0 || termYears <= 0 || annualRate < 0) {
            throw new IllegalArgumentException(
                "Principal and term must be positive; rate must be non-negative."
            );
        }
        this.principal   = principal;
        this.annualRate  = annualRate;
        this.termYears   = termYears;
    }

    @Override
    public double calculateMonthlyPayment() {
        int totalMonths = termYears * 12;

        // Zero-interest fallback
        if (annualRate == 0) {
            return principal / totalMonths;
        }

        double monthlyRate = annualRate / 12;
        double factor      = Math.pow(1 + monthlyRate, totalMonths);
        // P * r * (1+r)^n  / [ (1+r)^n − 1 ]
        return principal * monthlyRate * factor / (factor - 1);
    }

    @Override
    public double calculateTotalInterest() {
        double monthlyPayment = calculateMonthlyPayment();
        return monthlyPayment * termYears * 12 - principal;
    }

    // Simple getters, if you still need them:
    public double getPrincipal()   { return principal; }
    public double getAnnualRate()  { return annualRate; }
    public int    getTermYears()   { return termYears; }
}



import java.math.*;

public class LoanBD implements LoanInterface {
  private static final MathContext MC = new MathContext(10, RoundingMode.HALF_EVEN);
  private final BigDecimal principal;
  private final BigDecimal annualRate;
  private final int termYears;

  public LoanBD(BigDecimal p, BigDecimal r, int t) {
    if (p.signum() <= 0 || t <= 0 || r.signum() < 0)
      throw new IllegalArgumentException(...);
    principal = p;
    annualRate = r;
    termYears = t;
  }

  @Override
  public BigDecimal calculateMonthlyPayment() {
    int n = termYears * 12;
    if (annualRate.signum() == 0)
      return principal.divide(new BigDecimal(n), MC);

    BigDecimal monthlyRate = annualRate.divide(BigDecimal.valueOf(12), MC);
    BigDecimal onePlusR    = BigDecimal.ONE.add(monthlyRate, MC);
    BigDecimal factor      = onePlusR.pow(n, MC);

    return principal
        .multiply(monthlyRate, MC)
        .multiply(factor, MC)
        .divide(factor.subtract(BigDecimal.ONE, MC), MC);
  }

  // …
}
