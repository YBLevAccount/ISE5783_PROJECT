package geometries;

<<<<<<< HEAD
=======
import java.io.Console;
import java.util.List;

>>>>>>> refs/remotes/origin/master
import primitives.*;

/**
 * represents a tube in 3D world using ray and radius that goes around the ray
 * 
 * @author Yonatan
 *
 */
public class Tube extends RadialGeometry {

	/**
	 * each point on the ray is the center of a circle with the given radius
	 * together they make the tube
	 */
	protected final Ray axisRay;

	/**
	 * construct Tube using axis ray and radius
	 * 
	 * @param axisRay
	 * @param radius
	 */
	public Tube(Ray axisRay, double radius) {
		super(radius);
		this.axisRay = axisRay;
	}

	/**
	 * getter for the axis ray
	 * 
	 * @return the axis ray
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	@Override
	public Vector getNormal(Point point) {
		double t = axisRay.getDir().dotProduct(point.subtract(axisRay.getP0()));
		Point O = axisRay.getP0();
		if (!Util.isZero(t)) {
			O = O.add(axisRay.getDir().scale(t));
		}
		return (point.subtract(O)).normalize();
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		return null;
	}

}
