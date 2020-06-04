package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;
import java.util.Objects;
import static primitives.Util.alignZero;

public class Sphere extends RadialGeometry {
    Point3D _center;

    public Sphere(double _radius,Point3D _center) {
        super(_radius);
        this._center= new Point3D(_center);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;
        if (!(o instanceof Sphere)) return  false;
        Sphere other = (Sphere) o;
        return this._center.equals(other._center) && (Util.isZero(this._radius - other._radius));
    }

    public Point3D get_center() {
        return _center;
    }


    /**
     * get the normal to this sphere in a given point
     */
    @Override
    public Vector getNormal(Point3D point) {
        Vector normal = point.subtract(_center);
        return normal.normalize();
    }

    /**
     *function to find intersection between the ray and the sphere
     */
    @Override

    public List<Point3D> findIntersections(Ray ray) {
        Point3D p0 = ray.getPoint();
        Vector v = (Vector) ray.getDirection();
        Vector u;
        try {
            u = _center.subtract(p0); //calculate the vector P0 to the center to know if p0 is in the sphere or outside
        } catch (IllegalArgumentException e)// if the p0 is in the sphere and we received 0
        {
            return List.of(ray.getTargetPoint(_radius));//list immutable contient
        }
        double tm = alignZero(v.dotProduct(u));//produit scalaire loi projetté
        double dSquared = tm == 0 ? u.lengthSquared() : u.lengthSquared() - tm * tm;//d au carré
        double thSquared = alignZero(_radius * _radius - dSquared);
        if (thSquared <= 0) return null;//no intersection
        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) return List.of(ray.getTargetPoint(t1), ray.getTargetPoint(t2));//2 intersections point
        if (t1 > 0)
            return List.of(ray.getTargetPoint(t1));
        else
            return List.of(ray.getTargetPoint(t2));
    }
}
