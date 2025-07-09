public final class LoanCalculator {
  private LoanCalculator() { /* no instances */ }

  public static double monthlyPayment(
      double principal, double annualRatePct, int termYears
  ) {
    int    n = termYears * 12;
    double r = (annualRatePct / 100) / 12;
    if (r == 0) return principal / n;

    double factor = Math.pow(1 + r, n);
    return principal * r * factor / (factor - 1);
  }

  public static double totalInterest(
      double principal, double annualRatePct, int termYears
  ) {
    return monthlyPayment(principal, annualRatePct, termYears) * termYears * 12
           - principal;
  }
}
