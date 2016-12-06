package low.jack.parser;

import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import low.jack.model.Employee;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.supercsv.prefs.CsvPreference.STANDARD_PREFERENCE;

public class CsvParser {

    public CsvParser() {

    }

    public List<Employee> read(File file) throws IOException {
        List<Employee> employees = new ArrayList<Employee>();

        ICsvBeanReader beanReader = null;
        try {
            beanReader = new CsvBeanReader(new FileReader(file), STANDARD_PREFERENCE);

            // ignore header in file
            final String[] header = beanReader.getHeader(true);

            Employee employee;
            while ((employee = beanReader.read(Employee.class, header)) != null) {
                employees.add(employee);
            }

        } finally {
            if (beanReader != null) {
                beanReader.close();
            }
        }

        return employees;
    }

}
