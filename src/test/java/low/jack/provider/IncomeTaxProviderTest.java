package low.jack.provider;

import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.joda.money.CurrencyUnit.AUD;

@RunWith(MockitoJUnitRunner.class)
public class IncomeTaxProviderTest {

    private IncomeTaxProvider incomeTaxProvider;

    @Before
    public void setUp() {
        incomeTaxProvider = new IncomeTaxProvider();
    }

    // Tests for tax bracket $0 to $18,200

    @Test
    public void shouldProvideZeroForFirstBracketMinimum() {
        Money annualSalary = Money.zero(AUD);
        assertThat(incomeTaxProvider.provideIncomeTax(annualSalary), equalTo(Money.zero(AUD)));
    }

    @Test
    public void shouldProvideZeroForFirstBracketMaximum() {
        Money annualSalary = Money.parse("AUD 18200");
        assertThat(incomeTaxProvider.provideIncomeTax(annualSalary), equalTo(Money.zero(AUD)));
    }

    @Test
    public void shouldProvideZeroForFirstBracketNotBoundary() {
        Money annualSalary = Money.parse("AUD 10000");
        assertThat(incomeTaxProvider.provideIncomeTax(annualSalary), equalTo(Money.zero(AUD)));
    }

    // Tests for tax bracket $18,201 to $37,000

    @Test
    public void shouldProvideIncomeTaxForSecondBracketMinimum() {
        Money annualSalary = Money.parse("AUD 18201");
        assertThat(incomeTaxProvider.provideIncomeTax(annualSalary), equalTo(Money.zero(AUD)));
    }

    @Test
    public void shouldProvideIncomeTaxForSecondBracketMaximum() {
        Money annualSalary = Money.parse("AUD 37000");
        assertThat(incomeTaxProvider.provideIncomeTax(annualSalary), equalTo(Money.parse("AUD 298")));
    }

    @Test
    public void shouldProvideIncomeTaxForSecondBracketNotBoundary() {
        Money annualSalary = Money.parse("AUD 25000");
        assertThat(incomeTaxProvider.provideIncomeTax(annualSalary), equalTo(Money.parse("AUD 108")));
    }

    // Tests for tax bracket $37,001 to $80,000

    @Test
    public void shouldProvideIncomeTaxForThirdBracketMinimum() {
        Money annualSalary = Money.parse("AUD 37001");
        assertThat(incomeTaxProvider.provideIncomeTax(annualSalary), equalTo(Money.parse("AUD 298")));
    }

    @Test
    public void shouldProvideIncomeTaxForThirdBracketMaximum() {
        Money annualSalary = Money.parse("AUD 80000");
        assertThat(incomeTaxProvider.provideIncomeTax(annualSalary), equalTo(Money.parse("AUD 1462")));
    }

    @Test
    public void shouldProvideIncomeTaxForThirdBracketNotBoundary() {
        Money annualSalary = Money.parse("AUD 60050");
        assertThat(incomeTaxProvider.provideIncomeTax(annualSalary), equalTo(Money.parse("AUD 922")));
    }

    // Tests for tax bracket $80,001 to $180,000

    @Test
    public void shouldProvideIncomeTaxForFourthBracketMinimum() {
        Money annualSalary = Money.parse("AUD 80001");
        assertThat(incomeTaxProvider.provideIncomeTax(annualSalary), equalTo(Money.parse("AUD 1462")));
    }

    @Test
    public void shouldProvideIncomeTaxForFourthBracketMaximum() {
        Money annualSalary = Money.parse("AUD 180000");
        assertThat(incomeTaxProvider.provideIncomeTax(annualSalary), equalTo(Money.parse("AUD 4546")));
    }

    @Test
    public void shouldProvideIncomeTaxForFourthBracketNotBoundary() {
        Money annualSalary = Money.parse("AUD 100000");
        assertThat(incomeTaxProvider.provideIncomeTax(annualSalary), equalTo(Money.parse("AUD 2079")));
    }

    // Tests for tax bracket $180,001 and over

    @Test
    public void shouldProvideIncomeTaxForFifthBracketMinimum() {
        Money annualSalary = Money.parse("AUD 180001");
        assertThat(incomeTaxProvider.provideIncomeTax(annualSalary), equalTo(Money.parse("AUD 4546")));
    }

    @Test
    public void shouldProvideIncomeTaxForVeryHighAnnualSalary() {
        Money annualSalary = Money.parse("AUD 500000");
        assertThat(incomeTaxProvider.provideIncomeTax(annualSalary), equalTo(Money.parse("AUD 16546")));
    }

}
