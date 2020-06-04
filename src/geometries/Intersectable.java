package geometries;
import java.util.List;
import primitives.Ray;
import primitives.Point3D;

public interface Intersectable {
    List<Point3D> findIntersections (Ray ray);
}
