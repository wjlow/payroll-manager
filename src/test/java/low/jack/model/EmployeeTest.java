package low.jack.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeTest {

    @Test
    public void shouldGetSuperannnuationRateAsDouble() {
        Employee employee = new Employee("David", "Rudd", "60050", "9.55%", "March");
        assertThat(employee.getSuperannuationRateAsDouble(), equalTo(0.0955d));
    }

    @Test
    public void shouldPrintReadableEmployee() {
        Employee employee = new Employee("David", "Rudd", "60050", "9.55%", "March");
        String expected = "David,Rudd,60050,9.55%,March";

        assertThat(employee.toString(), equalTo(expected));
    }

}
