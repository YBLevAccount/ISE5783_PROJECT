package geometries;

import java.util.List;

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
	 * @param axisRay a ray that the tube will go around
	 * @param radius  for each circle that starts from each point on the ray
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
		/*
		 * finds the distance between the center of the circle of the point and the
		 * starting point of the ray, then get the point and create the normal
		 */
		double t = axisRay.getDir().dotProduct(point.subtract(axisRay.getP0()));
		return point.subtract(axisRay.getPoint(t)).normalize();
	}

	@Override
	public List<Point> findIntersections(Ray ray) {
		return null;
	}

}
