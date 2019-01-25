package main.java.coffeerun.Utilities;

/**
 * ScrollableObject class
 */
public class ScrollableObject {

    protected static float xScroll = -0.5f;     // value of xScroll for scrollable Objects

    /**
     * setScrollValue Method
     * <p>
     * This method takes in a float value and sets the xScroll value to the parameter.
     *
     * @param value - float value
     */
    public static void setScrollValue(float value) {
        xScroll = value;
    }

    public static float getScrollValue() {
        return xScroll;
    }
}