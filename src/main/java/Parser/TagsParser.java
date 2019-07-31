package Parser;

public class TagsParser {
    public static final short TAG_DATE = 1;
    public static final short ORDER_NUMBER = 2;
    public static final short CUSTOMER_NAME = 3;
    public static final short ORDER_ITEM = 4;
    public static final short ITEM_NAME = 11;
    public static final short ITEM_PRICE = 12;
    public static final short ITEM_COUNT = 13;
    public static final short ALL_ITEM_PRICE = 14;

    public static final int SIZE_TAG_2 = 2;
    public static final int MAX_SIZE_STRING = 1000;

    public static final byte ZERO_TAG = 0;


    public static short getTag(byte[] bt){
        switch (bt[1]+bt[0]){
            case TAG_DATE:
                return TAG_DATE;
            case ORDER_NUMBER:
                return ORDER_NUMBER;
            case CUSTOMER_NAME:
                return CUSTOMER_NAME;
            case ORDER_ITEM:
                return ORDER_ITEM;
            case ITEM_NAME:
                return ITEM_NAME;
            case ITEM_PRICE:
                return ITEM_PRICE;
            case ITEM_COUNT:
                return ITEM_COUNT;
            case ALL_ITEM_PRICE:
                return ALL_ITEM_PRICE;
            default: return ZERO_TAG;
        }
    }

}
