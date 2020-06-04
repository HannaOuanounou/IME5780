
package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Represents an infinite tube in the 3D space.
 * That is, the cylinder does not have a length.
 */

public class Tube extends RadialGeometry {
    /**
     * represents the direction and the reference point
     */
    protected final Ray _ray;

    /**
     * constructor for a new Cylinder object
     *
     * @param _radius the radius of the cylinder
     * @param _ray    the direction of the cylinder from a center point
     * @throws Exception in case of a negative radius
     */
    public Tube(double _radius, Ray _ray) {
        super(_radius);
        this._ray = new Ray(_ray);
    }

    /**
     * @return ray
     */
    public Ray get_ray() {
        return new Ray(_ray);
    }
    @Override
    public Vector getNormal(Point3D point) {
        //The vector from the point of the cylinder to the given point
        Point3D o = _ray.getPoint(); // at this point o = p0
        Vector v = (Vector) _ray.getDirection();

        Vector vector1 = point.subtract(o);

        //We need the projection to multiply the _direction unit vector
        double projection = vector1.dotProduct(v);
        if(!isZero(projection))
        {
            // projection of P-O on the ray:
            o = o.add(v.scale(projection));
        }

        //This vector is orthogonal to the _direction vector.
        Vector check = point.subtract(o);
        return check.normalize();
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_ray=" + _ray +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}