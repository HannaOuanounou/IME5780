package geometries;

import primitives.Point3D;
import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane implements Geometry {

    Point3D _p;
    Vector _normal;

    /** constractor who calculate the normal
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _p = new Point3D(p1);

        Vector U = new Vector(p1, p2);
        Vector V = new Vector(p1, p3);
        Vector N = U.crossProduct(V);
        N.normalize();
        _normal = N;
        //  _normal = N.scale(-1);

    }

    public Plane(Point3D _p, Vector _normal) {
        this._p = new Point3D(_p);
        this._normal = new Vector(_normal);
    }

    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    //because polygon
    public Vector getNormal() {
        return getNormal(null);
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector p0Q;
        try {
            p0Q = _p.subtract(ray.getPoint());//P-Q0
        } catch (IllegalArgumentException e)//if p and the point are the same point
        {
            return null; // ray starts from point Q - no intersections
        }

        double nv = _normal.dotProduct((Vector) ray.getDirection());//la normal fois le v qui est ray get direction
        if (isZero(nv)) // ray is parallel to the plane - no intersections
            return null;

        double t = alignZero(_normal.dotProduct(p0Q) / nv); //t= n fois q0-p0 sur Nfois v

        return t <= 0 ? null : List.of(ray.getTargetPoint(t));

    }
}
