package low.jack.generator;

import org.joda.money.Money;
import low.jack.model.Employee;
import low.jack.model.Payslip;
import low.jack.provider.IncomeProvider;
import low.jack.provider.IncomeTaxProvider;
import low.jack.provider.PayPeriodProvider;
import low.jack.provider.SuperannuationProvider;

import java.math.BigDecimal;

import static org.joda.money.CurrencyUnit.AUD;

public class PayslipGenerator {

    private PayPeriodProvider payPeriodProvider;

    private IncomeProvider incomeProvider;

    private IncomeTaxProvider incomeTaxProvider;

    private SuperannuationProvider superannuationProvider;

    public PayslipGenerator(PayPeriodProvider payPeriodProvider, IncomeProvider incomeProvider, IncomeTaxProvider incomeTaxProvider, SuperannuationProvider superannuationProvider) {
        this.payPeriodProvider = payPeriodProvider;
        this.incomeProvider = incomeProvider;
        this.incomeTaxProvider = incomeTaxProvider;
        this.superannuationProvider = superannuationProvider;
    }

    public Payslip generatePayslip(Employee employee) {
        Money annualSalary = Money.of(AUD, new BigDecimal(employee.getAnnualSalary()));

        String fullName = employee.getFirstName() + " " + employee.getLastName();
        String payPeriod = payPeriodProvider.providePayPeriod(employee.getPaymentMonth());
        Money grossIncome = incomeProvider.provideGrossIncome(annualSalary);
        Money incomeTax = incomeTaxProvider.provideIncomeTax(annualSalary);
        Money netIncome = incomeProvider.provideNetIncome(grossIncome, incomeTax);
        Money superannuation = superannuationProvider.provideSuperannuation(grossIncome, employee.getSuperannuationRateAsDouble());

        return new Payslip(fullName, payPeriod, grossIncome.getAmountMajor().toPlainString(), incomeTax.getAmountMajor().toPlainString(), netIncome.getAmountMajor().toPlainString(), superannuation.getAmountMajor().toPlainString());
    }

}
