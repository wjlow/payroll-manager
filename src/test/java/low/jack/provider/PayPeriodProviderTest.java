package low.jack.provider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class PayPeriodProviderTest {

    private PayPeriodProvider payPeriodProvider;

    @Before
    public void setUp() {
        payPeriodProvider = new PayPeriodProvider();
    }

    @Test
    public void shouldProvidePayPeriod() {
        String paymentMonth = "March";
        assertThat(payPeriodProvider.providePayPeriod(paymentMonth), equalTo("01 March - 31 March"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldReportExceptionForInvalidMonth() {
        String paymentMonth = "Australia";
        payPeriodProvider.providePayPeriod(paymentMonth);
    }
}
