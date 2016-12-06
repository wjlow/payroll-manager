package low.jack.provider;

import org.joda.money.Money;

import static java.math.RoundingMode.HALF_UP;

public class SuperannuationProvider {

    public static final int SCALE_WHOLE_NUMBER = 0;

    public SuperannuationProvider() {

    }

    public Money provideSuperannuation(Money grossIncome, double superannuationRate) {
        return grossIncome.multipliedBy(superannuationRate, HALF_UP).rounded(SCALE_WHOLE_NUMBER, HALF_UP);
    }
}
