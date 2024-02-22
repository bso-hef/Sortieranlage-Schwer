package colorsorter.helper;

/**
 * Class to encapsulate the color numbers of a sensor.
 * Code example:<br/>
 * <code><br/>
 * ElementSorterHelper.ElementColor elementColor = ElementSorterHelper.ElementColor.byValue(color)<br/>
 * <p>
 * if(elementColor == ElementSorterHelper.ElementColor.RED) {<br/>
 * ...<br/>
 * }<br/>
 * </code>
 */
public enum EV3Color {

    RED(0),
    GREEN(1),
    BLUE(2),
    YELLOW(3),
    MAGENTA(4),
    ORANGE(5),
    WHITE(6),
    BLACK(7),
    PINK(8),
    GRAY(9),
    LIGHT_GRAY(10),
    DARK_GRAY(11),
    CYAN(12),
    BROWN(13),
    NONE(-1);

    public final int colorID;

    EV3Color(int colorID) {
        this.colorID = colorID;
    }

    public static EV3Color byValue(int color) {
        for (EV3Color e : values()) {
            if (e.colorID == color) {
                return e;
            }
        }
        return NONE;
    }
}
