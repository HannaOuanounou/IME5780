package primitives;

import java.util.Objects;
import static primitives.Util.isZero;

public class Ray {
    private final  Vector _direction;
    private final  Point3D _point;

    /** constractor normalized
     *
     * @param _direction
     * @param _point
     */
    public Ray(Vector _direction, Point3D _point) {
        this._direction = new Vector(_direction.normalized());
        this._point = new Point3D(_point);
    }

    /** copy constractor

     * @param ray
     */
    public Ray(Ray ray)
    {

        this. _direction =new Vector(ray._direction.normalized());
        this._point=new Point3D(ray._point);
    }
    @Override
    public String toString() {
        return "Ray{" +
                "_direction=" + _direction +
                ", _point=" + _point +
                '}';
    }

    @Override

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray oth = (Ray) obj;
        return (_point.equals(oth._point) &&
                _direction.equals(oth._direction));
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_direction(), get_point());
    }

    public Vector get_direction() {
        return _direction;
    }

    public Point3D get_point() {
        return _point;
    }


    public Point3D getPoint() {return new Point3D(_point);
    }

    public Object getDirection() {
        return new Vector(_direction);
    }

    public Point3D getTargetPoint ( double length){
        return isZero(length) ? _point : new Point3D(_point).add(_direction.scale(length));
    }



}
