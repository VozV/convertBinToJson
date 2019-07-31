
package Parser;

import Types.Order;
import Types.OrderItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;

import javax.print.attribute.standard.OrientationRequested;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class DataParserTest {
    public static byte[] fromHexString(String src) {
        byte[] biBytes = new BigInteger("10" + src.replaceAll("\\s", ""), 16).toByteArray();
        return Arrays.copyOfRange(biBytes, 1, biBytes.length);
    }

    @Test
    public void parseData() {
        byte[] inputByteArr = fromHexString("01 00 04 00 A8 32 92 56 02 00 03 00 04 71 02 03\n" +
                "00 0B 00 8E 8E 8E 20 90 AE AC A0 E8 AA A0 04 00\n" +
                "1D 00 0B 00 07 00 84 EB E0 AE AA AE AB 0C 00 02\n" +
                "00 20 4E 0D 00 02 00 00 02 0E 00 02 00 40 9C");

        HashMap<String,Object> expectedOrder = new HashMap();
        expectedOrder.put("dateTime", new Date((long)1452421800 * 1000));
        expectedOrder.put("orderNumber",(long) 160004);
        expectedOrder.put("customerName","ООО Ромашка");
        expectedOrder.put("name","Дырокол");
        expectedOrder.put("price",(long)20000);
        expectedOrder.put("quantity",(double)2.0);
        expectedOrder.put("sum",(long)40000);


        Order order = new Order();
        DataParser dp = new DataParser(inputByteArr);
        try {
            order = dp.parseData();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(expectedOrder.get("dateTime"), order.getDataTime());
        Assert.assertEquals(expectedOrder.get("orderNumber"), order.getOrderNumber());
        Assert.assertEquals(expectedOrder.get("customerName"), order.getCustomerName());

        Assert.assertNotNull(order.getItems());

        for (OrderItem o: order.getItems()
        ) {

            Assert.assertEquals(expectedOrder.get("name"), o.getName());
            Assert.assertEquals(expectedOrder.get("price"), o.getPrice());
            Assert.assertEquals(expectedOrder.get("quantity"), o.getQuantity());
            Assert.assertEquals(expectedOrder.get("sum"), o.getSum());
        }


    }
}
