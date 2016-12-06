package low.jack.provider;

import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class IncomeProviderTest {

    private IncomeProvider incomeProvider;

    @Before
    public void setUp() {
        incomeProvider = new IncomeProvider();
    }

    @Test
    public void shouldProvideGrossIncome() {
        Money annualSalary = Money.parse("AUD 60050.00");
        assertThat(incomeProvider.provideGrossIncome(annualSalary), equalTo(Money.parse("AUD 5004")));
    }

    @Test
    public void shouldProvideNetIncome() {
        Money grossIncome = Money.parse("AUD 5004");
        Money incomeTax = Money.parse("AUD 922");

        assertThat(incomeProvider.provideNetIncome(grossIncome, incomeTax), equalTo(Money.parse("AUD 4082")));
    }
}
