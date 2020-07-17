package cc.xpbootcamp.warmup.cashier;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 *
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
    }

    public String printReceipt() {
        StringBuilder output = new StringBuilder();

        output.append(getReceiptHeader());

        output.append(getReceiptDateInfo());

        for (LineItem lineItem : order.getLineItems()) {
            output.append(getReceiptLineItem(lineItem));
        }

        output.append(getReceiptOrderCalculation(order));

        return output.toString();
    }

    private String getReceiptHeader() {
        return "==== 老王超市，值得信赖 ====\n";
    }

    private String getReceiptDateInfo() {
        LocalDate date = order.getDate();
        if (Objects.isNull(date)) {
            return null;
        }

        StringBuilder dateInfo = new StringBuilder();
        dateInfo.append(date.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
        dateInfo.append('，');
        dateInfo.append(getDayOfWeekDescription(date));

        return dateInfo.toString();
    }

    private String getReceiptLineItem(LineItem lineItem) {
        StringBuilder output = new StringBuilder();

        output.append(lineItem.getDescription());
        output.append(", ");
        output.append(lineItem.getPrice());
        output.append(" * ");
        output.append(lineItem.getQuantity());
        output.append(", ");
        output.append(lineItem.totalAmount());
        output.append('\n');

        return output.toString();
    }

    private String getReceiptOrderCalculation(Order order) {
        StringBuilder output = new StringBuilder();

        // prints the state tax
        output.append("税额").append("： ").append(order.calculateTotalSalesTax());

        // print total amount
        output.append("总价").append("： ").append(order.calculateTotalOrderAmount());

        return output.toString();
    }

    private String getDayOfWeekDescription(LocalDate date) {
        Map<DayOfWeek, String> map = new HashMap<DayOfWeek, String>();
        map.put(DayOfWeek.MONDAY, "星期一");
        map.put(DayOfWeek.TUESDAY, "星期二");
        map.put(DayOfWeek.WEDNESDAY, "星期三");
        map.put(DayOfWeek.THURSDAY, "星期四");
        map.put(DayOfWeek.FRIDAY, "星期五");
        map.put(DayOfWeek.SATURDAY, "星期六");
        map.put(DayOfWeek.SUNDAY, "星期日");

        return map.get(date.getDayOfWeek());
    }
}