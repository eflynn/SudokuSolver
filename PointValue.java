import java.awt.Point;

/**
 * A class to extend java's Point class, adding a field for a value at that point.
 * 
 * 
 */
public class PointValue extends Point {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int value;

	/**
	 * Default constructor
	 */
	public PointValue() {
		super();
		value = 0;
	}

	/**
	 * Alternate Constructor
	 * 
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 * @param tempValue
	 *            value at that point
	 */
	public PointValue(int x, int y, int tempValue) {
		super(x, y);
		value = tempValue;
	}

	/**
	 * Accessor method
	 * 
	 * @return the value of the point
	 */
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
}
