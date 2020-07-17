package cc.xpbootcamp.warmup.cashier;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

class OrderReceiptTest {
    @Test
    void shouldPrintDateInformationOnOrder() {
        Order order = new Order(null, null, LocalDate.of(2020, 2, 17), new ArrayList<LineItem>());
        OrderReceipt receipt = new OrderReceipt(order);

        String output = receipt.printReceipt();

        assertThat(output, containsString("2020年02月17日，星期一"));
    }

    @Test
    public void shouldPrintLineItemAndSalesTaxInformation() {
        List<LineItem> lineItems = new ArrayList<LineItem>() {{
            add(new LineItem("milk", 10.0, 2));
            add(new LineItem("biscuits", 5.0, 5));
            add(new LineItem("chocolate", 20.0, 1));
        }};
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, null, lineItems));

        String output = receipt.printReceipt();

        assertThat(output, containsString("milk, 10.0 * 2, 20.0\n"));
        assertThat(output, containsString("biscuits, 5.0 * 5, 25.0\n"));
        assertThat(output, containsString("chocolate, 20.0 * 1, 20.0\n"));
        assertThat(output, containsString("税额： 6.5"));
        assertThat(output, containsString("总价： 71.5"));
    }

    @Test
    public void shouldPrintHeaderInformation() {
        List<LineItem> lineItems = new ArrayList<LineItem>();
        OrderReceipt receipt = new OrderReceipt(new Order(null, null, null, lineItems));

        String output = receipt.printReceipt();

        assertThat(output, containsString("==== 老王超市，值得信赖 ===="));
    }
}