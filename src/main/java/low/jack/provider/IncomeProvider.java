package low.jack.provider;

import org.joda.money.Money;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;
import static org.joda.time.Months.TWELVE;

public class IncomeProvider {

    public static final int SCALE_WHOLE_NUMBER = 0;

    public IncomeProvider() {

    }

    public Money provideGrossIncome(Money annualSalary) {
        final BigDecimal monthsInOneYear = new BigDecimal(TWELVE.getMonths());
        return annualSalary.dividedBy(monthsInOneYear, HALF_UP).rounded(SCALE_WHOLE_NUMBER, HALF_UP);
    }

    public Money provideNetIncome(Money grossIncome, Money incomeTax) {
        return grossIncome.minus(incomeTax);
    }
}
