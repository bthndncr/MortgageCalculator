public class MortgageCalculator {
    private final static byte MONTHS_IN_YEAR = 12;
    private final static  byte PERCENT = 100;

    private int principal;
    private double annualInterest;
    private int years;

    public MortgageCalculator(int principal, double annualInterest, int years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }

    public double calculateBalance(int numOfPaymentsMade) {
        double monthlyInterest = getMonthlyInterest();
        int numOfPayments = getNumOfPayments();

        double balance = principal
                * (Math.pow(1 + monthlyInterest, numOfPayments) - Math.pow(1 + monthlyInterest,numOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numOfPayments) - 1);

        return balance;
    }

    public double calculateMortgage(){
        double monthlyInterest = getMonthlyInterest();
        int numOfPayments = getNumOfPayments();

        double mortgage = principal * (monthlyInterest * Math.pow(1 + monthlyInterest, numOfPayments))
                / (Math.pow(1 + monthlyInterest, numOfPayments) -1);

        return mortgage;
    }

    public double[] getRemainingBalance() {
        var balances = new double[getNumOfPayments()];
        for (int month = 1; month <= balances.length; month++) {
            balances[month - 1] = calculateBalance(month);
        }
        return balances;
    }

    private int getNumOfPayments() {
        return years * MONTHS_IN_YEAR;
    }

    private double getMonthlyInterest() {
        return annualInterest / PERCENT / MONTHS_IN_YEAR;
    }
}
