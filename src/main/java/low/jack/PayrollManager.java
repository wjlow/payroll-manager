package low.jack;

import low.jack.generator.PayslipGenerator;
import low.jack.model.Employee;
import low.jack.parser.CsvParser;
import low.jack.provider.IncomeProvider;
import low.jack.provider.IncomeTaxProvider;
import low.jack.provider.PayPeriodProvider;
import low.jack.provider.SuperannuationProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This is the Test Harness that can be used to test the program's ability to generate payslip information
 * for each employee in a given input file.
 *
 * Usage instruction: java -jar payslip-1.0-jar-with-dependencies.jar [csvfile]
 *
 * There is an associated test class - PayrollManagerTest - that can be used to run this class.
 */
public class PayrollManager {

    public static void main(String[] args) throws IOException {
        File employeesFile = new File(args[0]);

        CsvParser csvParser = new CsvParser();
        List<Employee> employees = csvParser.read(employeesFile);

        PayslipGenerator payslipGenerator = new PayslipGenerator(new PayPeriodProvider(), new IncomeProvider(), new IncomeTaxProvider(), new SuperannuationProvider());

        System.out.println("name,payPeriod,grossIncome,incomeTax,netIncome,superannuation");
        for (Employee employee : employees) {
            System.out.println(payslipGenerator.generatePayslip(employee).toString());
        }
    }

}
