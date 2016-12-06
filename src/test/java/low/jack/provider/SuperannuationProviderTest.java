package low.jack.provider;

import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SuperannuationProviderTest {

    private SuperannuationProvider superannuationProvider;

    @Before
    public void setUp() {
        superannuationProvider = new SuperannuationProvider();
    }

    @Test
    public void shouldProvideSuperannuation() {
        assertThat(superannuationProvider.provideSuperannuation(Money.parse("AUD 5004"), 0.09d), equalTo(Money.parse("AUD 450")));
    }
}
