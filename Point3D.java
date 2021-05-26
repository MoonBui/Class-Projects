/**
 * A basic 3D point with x, y, and z coordinates.
 * @author Moon Bui
 *
 */
public class Point3D implements Comparable{
    
    private int x;
    private int y;
    private int z;
    
    /**
     * Constructs a new point with the specified coordinates
     * @param x the x-coordinate of this point
     * @param y the y-coordinate of this point
     * @param z the z-coordinate of this point
     */
    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Point3D [x=" + x + ", y=" + y + ", z=" + z + "]";
    }
    
    public boolean equals(Object other) {
    	Point3D that = (Point3D) other;
    	if (this.x == that.x) {
    		if (this.y == that.y) {
    			if (this.z == that.z)
    				return true;
    			return false;
    		}
    		return false;
    	}
    	return false;
    }

    // Comparable interface method
	@Override
	public int compareTo(Object o) {
		Point3D point = (Point3D) o;

		if (this.x > point.x)
			return 1;
		else if (this.x < point.x)
			return -1;
		else {
			if (this.y > point.y)
				return 1;
			else if (this.y < point.y)
				return -1;
			else {
				if (this.z > point.z)
					return 1;
				else if (this.z < point.z)
					return -1;
				else
					return 0;
			}
		}
	}
}
