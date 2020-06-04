package geometries;
import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.isZero;
/**
 * @author Eliezer
 * RadialGeometry is ana abstract class that defines
 * all radial geometries.
 */
public abstract class RadialGeometry implements Geometry{
    double  _radius;

    /**
     *
     * @param _radius
     */





    public RadialGeometry(double _radius) {
        if (isZero(_radius) || (_radius < 0.0))
            throw new IllegalArgumentException("radius "+ _radius +" is not valid");
        this._radius = _radius;
    }

    public RadialGeometry(RadialGeometry other){
        this._radius= other._radius;
    }
    public double get_radius() {
        return _radius;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}