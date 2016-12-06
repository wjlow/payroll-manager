package low.jack.model;

public class Employee {

    private String firstName;

    private String lastName;

    private String annualSalary;

    private String superannuationRate;

    private String paymentMonth;

    public Employee() {
        // default constructor needed for CsvBeanReader to instantiate bean
    }

    public Employee(String firstName, String lastName, String annualSalary, String superannuationRate, String paymentMonth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.annualSalary = annualSalary;
        this.superannuationRate = superannuationRate;
        this.paymentMonth = paymentMonth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAnnualSalary() {
        return annualSalary;
    }

    public String getSuperannuationRate() {
        return superannuationRate;
    }

    public String getPaymentMonth() {
        return paymentMonth;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAnnualSalary(String annualSalary) {
        this.annualSalary = annualSalary;
    }

    public void setSuperannuationRate(String superannuationRate) {
        this.superannuationRate = superannuationRate;
    }

    public void setPaymentMonth(String paymentMonth) {
        this.paymentMonth = paymentMonth;
    }

    public double getSuperannuationRateAsDouble() {
        return Double.parseDouble(superannuationRate.replaceAll("%", "")) / 100;
    }

    public String toString() {
        return firstName + "," + lastName + "," + annualSalary + "," + superannuationRate + "," + paymentMonth;
    }
}
