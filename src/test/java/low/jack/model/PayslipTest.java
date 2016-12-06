package low.jack.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PayslipTest {

    @Test
    public void shouldPrintReadablePayslip() {
        Payslip payslip = new Payslip("Ryan Chen", "01 March - 31 March", "10000", "2696", "7304", "1000");
        String expected = "Ryan Chen,01 March - 31 March,10000,2696,7304,1000";

        assertThat(payslip.toString(), equalTo(expected));
    }

}
