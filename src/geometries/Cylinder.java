package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 *  Cylinder is afinite Tube with a certain _height
 */
public class Cylinder extends Tube {

    private double _height;

    /**
     * Cylinder constructor
     *
     * @param _radius
     * @param _ray
     * @param _height
     */
    public Cylinder(double _radius, Ray _ray, double _height) {
        super(_radius, _ray);
        this._height = _height;
    }

    /**
     * @param point point to calculate the normal
     * @return
     */
    @Override
    public Vector getNormal(Point3D point) {
        Vector result = null;
        boolean finished = false;

        Point3D o = _ray.getPoint();
        Vector v = (Vector) _ray.getDirection();

        // projection of P-O on the ray:
        double t = 0;
        try {
            t = alignZero(point.subtract(o).dotProduct(v));
        } catch (IllegalArgumentException e) { // P = O
            result = v;
            finished = true;
        }
        if (!finished) {// if the point is at a base
            if (t == 0 || isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
            {
                result = v;
            } else {
                o = o.add(v.scale(t));
                result = point.subtract(o).normalize();
            }

        }

        return result;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                ", _ray=" + _ray +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }

    public double get_height() {
        return _height;
    }
}