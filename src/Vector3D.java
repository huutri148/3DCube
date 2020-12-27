public class Vector3D {
    private float x;
    private float y;
    private float z;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }


    public Vector3D(final float x,final float y,final float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vector3D(){

    }
    public double distance(Vector3D v) {
        return Math.sqrt((x - v.getX()) * (x - v.getX()) + (y - v.getY()) * (y - v.getY()) + (z - v.getZ()) * (z - v.getZ()));
    }
}
