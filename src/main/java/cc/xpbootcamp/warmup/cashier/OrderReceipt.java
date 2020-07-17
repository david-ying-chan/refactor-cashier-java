package cc.xpbootcamp.warmup.cashier;

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

        output.append(getReceiptOverallInfo());

        for (LineItem lineItem : order.getLineItems()) {
            output.append(getReceiptLineItem(lineItem));
        }

        output.append(getReceiptOrderCalculation(order));

        return output.toString();
    }

    private String getReceiptHeader() {
        return "==== 老王超市，值得信赖 ====\n";
    }

    private String getReceiptOverallInfo() {
        StringBuilder overallInfo = new StringBuilder();
        overallInfo.append(order.getCustomerName());
        overallInfo.append(order.getCustomerAddress());

        return overallInfo.toString();
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
        output.append("税额").append("： ").append(calculateTotalSalesTax(order));

        // print total amount
        output.append("总价").append("： ").append(calculateTotalOrderAmount(order));

        return output.toString();
    }

    private double calculateTotalSalesTax(Order order) {
        double totalSalesTax = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            double salesTax = lineItem.totalAmount() * .10;
            totalSalesTax += salesTax;
        }

        return  totalSalesTax;
    }

    private double calculateTotalOrderAmount(Order order) {
        double totalOrderAmount = 0d;
        for (LineItem lineItem : order.getLineItems()) {
            double salesTax = lineItem.totalAmount() * .10;
            totalOrderAmount += lineItem.totalAmount() + salesTax;
        }

        return totalOrderAmount;
    }
}