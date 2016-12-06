package low.jack.parser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import low.jack.model.Employee;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CsvParserTest {

    private CsvParser csvParser;

    @Before
    public void setUp() {
        csvParser = new CsvParser();
    }

    @Test
    public void shouldConvertRecordsInCsvToPojo() throws IOException {
        URL url = this.getClass().getResource("/employees.txt");
        File testCsv = new File(url.getFile());

        List<Employee> employees = csvParser.read(testCsv);

        assertThat(employees.size(), equalTo(2));

        assertThat(employees.get(0).toString(), equalTo("David,Rudd,60050,9%,March"));
        assertThat(employees.get(1).toString(), equalTo("Ryan,Chen,120000,10%,March"));
    }
}
