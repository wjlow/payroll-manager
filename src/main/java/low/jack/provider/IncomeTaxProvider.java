package low.jack.provider;

import org.joda.money.Money;
import org.joda.time.Months;

import static java.math.RoundingMode.HALF_UP;
import static org.joda.money.CurrencyUnit.AUD;

public class IncomeTaxProvider {

    public static final Money MAX_INCOME_FOR_FIRST_BRACKET = Money.parse("AUD 18200");
    public static final Money MIN_INCOME_FOR_SECOND_BRACKET = Money.parse("AUD 18201");
    public static final Money MAX_INCOME_FOR_SECOND_BRACKET = Money.parse("AUD 37000");
    public static final Money MIN_INCOME_FOR_THIRD_BRACKET = Money.parse("AUD 37201");
    public static final Money MAX_INCOME_FOR_THIRD_BRACKET = Money.parse("AUD 80000");
    public static final Money MIN_INCOME_FOR_FOURTH_BRACKET = Money.parse("AUD 80001");
    public static final Money MAX_INCOME_FOR_FOURTH_BRACKET = Money.parse("AUD 180000");
    public static final Money MIN_INCOME_FOR_FIFTH_BRACKET = Money.parse("AUD 180001");

    public static final Money BASE_TAX_FOR_THIRD_BRACKET = Money.parse("AUD 3572");
    public static final Money BASE_TAX_FOR_FOURTH_BRACKET = Money.parse("AUD 17547");
    public static final Money BASE_TAX_FOR_FIFTH_BRACKET = Money.parse("AUD 54547");

    public static final int SCALE_WHOLE_NUMBER = 0;

    public IncomeTaxProvider() {

    }

    public Money provideIncomeTax(Money annualSalary) {
        Money annualTax = Money.zero(AUD);

        if (annualSalary.isPositiveOrZero() && annualSalary.isLessThan(MIN_INCOME_FOR_SECOND_BRACKET)) {
            annualTax = Money.zero(AUD);
        }

        if (annualSalary.isGreaterThan(MAX_INCOME_FOR_FIRST_BRACKET) && annualSalary.isLessThan(MIN_INCOME_FOR_THIRD_BRACKET)) {
            annualTax = annualSalary.minus(MAX_INCOME_FOR_FIRST_BRACKET).multipliedBy(0.19d, HALF_UP);
        }

        if (annualSalary.isGreaterThan(MAX_INCOME_FOR_SECOND_BRACKET) && annualSalary.isLessThan(MIN_INCOME_FOR_FOURTH_BRACKET)) {
            annualTax = BASE_TAX_FOR_THIRD_BRACKET.plus(annualSalary.minus(MAX_INCOME_FOR_SECOND_BRACKET).multipliedBy(0.325d, HALF_UP));
        }

        if (annualSalary.isGreaterThan(MAX_INCOME_FOR_THIRD_BRACKET) && annualSalary.isLessThan(MIN_INCOME_FOR_FIFTH_BRACKET)) {
            annualTax = BASE_TAX_FOR_FOURTH_BRACKET.plus(annualSalary.minus(MAX_INCOME_FOR_THIRD_BRACKET).multipliedBy(0.37d, HALF_UP));
        }

        if (annualSalary.isGreaterThan(MAX_INCOME_FOR_FOURTH_BRACKET)) {
            annualTax = BASE_TAX_FOR_FIFTH_BRACKET.plus(annualSalary.minus(MAX_INCOME_FOR_FOURTH_BRACKET).multipliedBy(0.45d, HALF_UP));
        }

        return calculateIncomeTaxPerMonth(annualTax);
    }

    private Money calculateIncomeTaxPerMonth(Money annualTax) {
        return annualTax.dividedBy(Months.TWELVE.getMonths(), HALF_UP).rounded(SCALE_WHOLE_NUMBER, HALF_UP);
    }

}
