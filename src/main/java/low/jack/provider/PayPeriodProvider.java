package low.jack.provider;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class PayPeriodProvider {

    public static final int LEFT_PAD_SIZE = 2;
    public static final String LEFT_PAD_STRING = "0";

    public PayPeriodProvider() {

    }

    public String providePayPeriod(String paymentMonth) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("MMM");
        LocalDate paymentDate = formatter.parseLocalDate(paymentMonth);

        return formatDateAsText(paymentDate.dayOfMonth().withMinimumValue()) + " - " + formatDateAsText(paymentDate.dayOfMonth().withMaximumValue());

    }

    private String formatDateAsText(LocalDate date) {
        String day = StringUtils.leftPad(String.valueOf(date.getDayOfMonth()), LEFT_PAD_SIZE, LEFT_PAD_STRING);
        String month = date.monthOfYear().getAsText();

        return day + " " + month;
    }
}
