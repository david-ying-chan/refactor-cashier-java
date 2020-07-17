package cc.xpbootcamp.warmup.cashier;

import java.util.List;
import java.time.LocalDate;

public class Order {
    String cName;
    String addr;
    LocalDate date;
    List<LineItem> lineItemList;

    public Order(String cName, String addr, LocalDate date, List<LineItem> lineItemList) {
        this.cName = cName;
        this.addr = addr;
        this.date = date;
        this.lineItemList = lineItemList;
    }

    public String getCustomerName() {
        return cName;
    }

    public String getCustomerAddress() {
        return addr;
    }

    public LocalDate getDate() { return date; }

    public List<LineItem> getLineItems() {
        return lineItemList;
    }

    public double calculateTotalSalesTax() {
        double totalSalesTax = 0d;
        for (LineItem lineItem : lineItemList) {
            double salesTax = lineItem.totalAmount() * .10;
            totalSalesTax += salesTax;
        }

        return  totalSalesTax;
    }

    public double calculateTotalOrderAmount() {
        double totalOrderAmount = 0d;
        for (LineItem lineItem : getLineItems()) {
            double salesTax = lineItem.totalAmount() * .10;
            totalOrderAmount += lineItem.totalAmount() + salesTax;
        }

        return totalOrderAmount;
    }
}
