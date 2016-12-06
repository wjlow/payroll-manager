package low.jack.model;

public class Payslip {

    private final String fullName;

    private final String payPeriod;

    private final String grossIncome;

    private final String incomeTax;

    private final String netIncome;

    private final String superannuation;

    public Payslip(String fullName, String payPeriod, String grossIncome, String incomeTax, String netIncome, String superannuation) {
        this.fullName = fullName;
        this.payPeriod = payPeriod;
        this.grossIncome = grossIncome;
        this.incomeTax = incomeTax;
        this.netIncome = netIncome;
        this.superannuation = superannuation;
    }

    public String toString() {
        return fullName + "," + payPeriod + "," + grossIncome + "," + incomeTax + "," + netIncome + "," + superannuation;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGrossIncome() {
        return grossIncome;
    }

    public String getIncomeTax() {
        return incomeTax;
    }

    public String getNetIncome() {
        return netIncome;
    }

    public String getSuperannuation() {
        return superannuation;
    }

}
