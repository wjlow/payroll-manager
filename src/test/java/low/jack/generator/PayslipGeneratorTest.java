package low.jack.generator;

import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import low.jack.model.Employee;
import low.jack.model.Payslip;
import low.jack.provider.IncomeProvider;
import low.jack.provider.IncomeTaxProvider;
import low.jack.provider.PayPeriodProvider;
import low.jack.provider.SuperannuationProvider;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.joda.money.CurrencyUnit.AUD;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PayslipGeneratorTest {

    private PayslipGenerator payslipGenerator;

    @Mock
    private PayPeriodProvider payPeriodProvider;

    @Mock
    private IncomeProvider incomeProvider;

    @Mock
    private IncomeTaxProvider incomeTaxProvider;

    @Mock
    private SuperannuationProvider superannuationProvider;

    @Before
    public void setUp() {
        payslipGenerator = new PayslipGenerator(payPeriodProvider, incomeProvider, incomeTaxProvider, superannuationProvider);
    }

    @Test
    public void shouldGeneratePayslipForEmployee() {
        Employee employee = new Employee("David", "Rudd", "60050", "9%", "March");
        Payslip expectedPayslip = new Payslip("David Rudd", "01 March - 31 March", "5004", "922", "4082", "450");

        Money annualSalary = Money.of(AUD, new BigDecimal(employee.getAnnualSalary()));
        Money expectedGrossIncome = Money.parse("AUD 5004");
        Money expectedIncomeTax = Money.parse("AUD 922");
        Money expectedNetIncome = Money.parse("AUD 4082");
        Money expectedSuperannuation = Money.parse("AUD 450");

        when(payPeriodProvider.providePayPeriod(employee.getPaymentMonth())).thenReturn("01 March - 31 March");
        when(incomeProvider.provideGrossIncome(annualSalary)).thenReturn(expectedGrossIncome);
        when(incomeTaxProvider.provideIncomeTax(annualSalary)).thenReturn(expectedIncomeTax);
        when(incomeProvider.provideNetIncome(expectedGrossIncome, expectedIncomeTax)).thenReturn(expectedNetIncome);
        when(superannuationProvider.provideSuperannuation(expectedGrossIncome, employee.getSuperannuationRateAsDouble())).thenReturn(expectedSuperannuation);

        assertThat(payslipGenerator.generatePayslip(employee).toString(), equalTo(expectedPayslip.toString()));
    }


}
