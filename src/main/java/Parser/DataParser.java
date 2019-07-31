package Parser;

import Types.Order;
import org.apache.commons.lang3.ArrayUtils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import static Parser.TagsParser.*;

public class DataParser {

    private int size;
    private byte[] arr;
    private int step;

    private static final String STRING_CP866 = "CP866";

    public DataParser (byte[] arr){
        this.arr = arr;
        this.size = arr.length;
    }

    private ByteBuffer getBuffer(byte[] bytes){
        return  ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
    }

    private byte[] checkArrSize(int len, byte[] arr){
        if ((arr != null) && arr.length % len != 0){
            return ArrayUtils.addAll(arr, new byte[len-arr.length]);
        }
        return arr;
    }

    private short getShort(byte[] bytes){
        return getBuffer(checkArrSize(Short.SIZE, bytes)).getShort();
    }

    private long getLong(byte[] bytes){
        return getBuffer(checkArrSize(Long.SIZE, bytes)).getLong();
    }

    private int getInt(byte[] bytes){
        return getBuffer(checkArrSize(Integer.SIZE, bytes)).getInt();

    }
    private double getFVLN(byte[] bytes){
        long number = getLong(Arrays.copyOfRange(bytes,1,bytes.length));
        double p = Math.pow(10 ,getShort(new byte[] {bytes[0]}));
        return  number/p + number%p;
    }

    private String getString(byte[] bytes) throws UnsupportedEncodingException {
        return   new String(bytes, STRING_CP866);
    }

    private short getSize2b(){
        return getShort(getSubArr(SIZE_TAG_2));
    }

    private byte[] getSubArr(int size) {
        if (size > 0) {
            int start = this.step;
            this.step += size;
            return Arrays.copyOfRange(this.arr, start, this.step);
        } else return null;
    }

    public Order parseData() throws UnsupportedEncodingException {
        Order data = new Order();
        this.step = 0;

        while (this.step < this.size) {
            switch (TagsParser.getTag(getSubArr(SIZE_TAG_2))) {
                case TAG_DATE:
                    data.setDataTime(getInt(getSubArr(getSize2b())));
                    break;
                case ORDER_NUMBER:
                    data.setOrderNumber(getLong(getSubArr(getSize2b())));
                    break;
                case CUSTOMER_NAME:
                    data.setCustomerName(getString(getSubArr(getSize2b())));
                    break;
                case ORDER_ITEM:
                    if (getSize2b() > 0)
                        data.addItem();
                    break;
                case ITEM_NAME:
                    data.setItemName(getString(getSubArr(getSize2b())));
                    break;
                case ITEM_PRICE:
                    data.setItemPrice(getLong(getSubArr(getSize2b())));
                    break;
                case ITEM_COUNT:
                    data.setItemQuantity((getFVLN(getSubArr(getSize2b()))));
                    break;
                case ALL_ITEM_PRICE:
                    data.setItemSum(getLong(getSubArr(getSize2b())));
                    break;
            }
        }
        return data;
    }
}
