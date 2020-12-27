import java.util.List;

public class Math3D {
   final static float PIOVER180 = (float) (Math.PI / 180.0);
    public static Vector3D RotateX(Vector3D point3D, float degrees)
    {
        //[ a  b  c ] [ x ]   [ x*a + y*b + z*c ]
        //[ d  e  f ] [ y ] = [ x*d + y*e + z*f ]
        //[ g  h  i ] [ z ]   [ x*g + y*h + z*i ]

        //[ 1    0        0   ]
        //[ 0   cos(x)  sin(x)]
        //[ 0   -sin(x) cos(x)]

        double cDegrees = degrees * PIOVER180;
        double cosDegrees = Math.cos(cDegrees);
        double sinDegrees = Math.sin(cDegrees);

        double y = (point3D.getY() * cosDegrees) + (point3D.getZ() * sinDegrees);
        double z = (point3D.getY() * -sinDegrees) + (point3D.getZ() * cosDegrees);

        return new Vector3D(point3D.getX(),(float) y,(float) z);
    }
    public static Vector3D RotateY(Vector3D point3D, float degrees)
    {
        //[ cos(x)   0    sin(x)]
        //[   0      1      0   ]
        //[-sin(x)   0    cos(x)]

        double cDegrees = degrees * PIOVER180;
        double cosDegrees = Math.cos(cDegrees);
        double sinDegrees = Math.sin(cDegrees);

        double x = (point3D.getX() * cosDegrees) + (point3D.getZ() * sinDegrees);
        double z = (point3D.getX() * -sinDegrees) + (point3D.getZ() * cosDegrees);

        return new Vector3D((float)x, point3D.getY(),(float) z);
    }
    public static Vector3D RotateZ(Vector3D point3D, float degrees)
    {
        //[ cos(x)  sin(x) 0]
        //[ -sin(x) cos(x) 0]
        //[    0     0     1]

        double cDegrees = degrees * PIOVER180;
        double cosDegrees = Math.cos(cDegrees);
        double sinDegrees = Math.sin(cDegrees);

        double x = (point3D.getX() * cosDegrees) + (point3D.getY() * sinDegrees);
        double y = (point3D.getX() * -sinDegrees) + (point3D.getY() * cosDegrees);

        return new Vector3D((float)x, (float)y, point3D.getZ());
    }

    public static Vector3D Translate(Vector3D points3D, Vector3D oldOrigin, Vector3D newOrigin)
    {
        Vector3D difference = new Vector3D(newOrigin.getX() - oldOrigin.getX(), newOrigin.getY() - oldOrigin.getY(), newOrigin.getZ() - oldOrigin.getZ());
        points3D.setX(points3D.getX() + difference.getX());
        points3D.setY(points3D.getY() + difference.getY());
        points3D.setZ(points3D.getZ() + difference.getZ());
        return points3D;
    }
    public static List<Vector3D> RotateX(List<Vector3D> points3D, float degrees)
    {
        for (int i = 0; i < points3D.size(); i++)
        {
            Vector3D v = points3D.get(i);
            v =  RotateX((Vector3D)points3D.get(i), degrees);
        }
        return points3D;
    }
    public static List<Vector3D> RotateY(List<Vector3D> points3D, float degrees)
    {
        for (int i = 0; i < points3D.size(); i++)
        {
            Vector3D v = points3D.get(i);
            v =  RotateY((Vector3D)points3D.get(i), degrees);
        }
        return points3D;
    }
    public static List<Vector3D> RotateZ(List<Vector3D> points3D, float degrees)
    {
        for (int i = 0; i < points3D.size(); i++)
        {
            Vector3D v = points3D.get(i);
            v =  RotateZ((Vector3D)points3D.get(i), degrees);
        }
        return points3D;
    }
    public static List<Vector3D> Translate(List<Vector3D> points3D, Vector3D oldOrigin, Vector3D newOrigin)
    {
        for (int i = 0; i < points3D.size(); i++)
        {
            Vector3D v = points3D.get(i);
            v = Translate(points3D.get(i), oldOrigin, newOrigin);
        }
        return points3D;
    }
}
