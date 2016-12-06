package low.jack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PayrollManagerTest {

    private PayrollManager payrollManager;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        payrollManager = new PayrollManager();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void shouldPrintReadablePayslipForEachEmployee() throws IOException {
        URL url = this.getClass().getResource("/employees.txt");
        payrollManager.main(new String[]{url.getFile()});

        String expected = "name,payPeriod,grossIncome,incomeTax,netIncome,superannuation\nDavid Rudd,01 March - 31 March,5004,922,4082,450\n" +
                "Ryan Chen,01 March - 31 March,10000,2696,7304,1000\n";
        assertThat(outContent.toString(), equalTo(expected));
    }

    @After
    public void cleanUp() {
        System.setOut(null);
    }

}
