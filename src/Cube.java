import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Cube {
    public int width = 0;
    public int height = 0;
    public int depth = 0;

    float xRotation = 1.0f;
    float yRotation = 0.0f;
    float zRotation = 0.0f;



    boolean drawWires = true;
    boolean fillFront = true;
    boolean fillBack;
    boolean fillLeft;
    boolean fillRight;
    boolean fillTop;
    boolean fillBottom;

    Vector3D cubeOrigin;
    List<Face> faces;





    public static class Face implements Comparable<Face>{
        @Override
        public int compareTo(Face otherFace) {
            return (int)(this.center.getZ() - otherFace.center.getZ());
        }

        public enum Side{
           FRONT,
           BACK,
           LEFT,
           RIGHT,
           TOP,
           BOTTOM
        }
        public List<Point> corners2D;
        public List<Vector3D> corners3D;
        public Vector3D center;
        public Side cubeSide;
        public Face() {

        }
    }

    public boolean isFillFront() {
        return fillFront;
    }

    public void setFillFront(boolean fillFront) {
        this.fillFront = fillFront;
    }

    public boolean isFillBack() {
        return fillBack;
    }

    public void setFillBack(boolean fillBack) {
        this.fillBack = fillBack;
    }

    public boolean isFillLeft() {
        return fillLeft;
    }

    public void setFillLeft(boolean fillLeft) {
        this.fillLeft = fillLeft;
    }

    public boolean isFillRight() {
        return fillRight;
    }

    public void setFillRight(boolean fillRight) {
        this.fillRight = fillRight;
    }

    public boolean isFillTop() {
        return fillTop;
    }

    public void setFillTop(boolean fillTop) {
        this.fillTop = fillTop;
    }

    public boolean isFillBottom() {
        return fillBottom;
    }

    public void setFillBottom(boolean fillBottom) {
        this.fillBottom = fillBottom;
    }



    public Cube(int i1, int i, int side){
        width = side;
        height = side;
        depth = side;
        cubeOrigin = new Vector3D(width/2, height/2, depth/2);
        InitializeCube();

    }
    public Cube(int side, Vector3D cubeOrigin){
        width = side;
        height = side;
        depth = side;
        cubeOrigin = cubeOrigin;
        InitializeCube();
    }
    public Cube(int width, int height, int depth, Vector3D cubeOrigin){
        width = width;
        height = height;
        depth = depth;
        cubeOrigin = cubeOrigin;
        InitializeCube();
    }


    private void InitializeCube(){

        //Fill in the cube
        faces = new ArrayList<>(6);

        // Front Face---------------------------
        Face frontFace = new Face();
        frontFace.cubeSide = Face.Side.FRONT;
        frontFace.corners3D = new ArrayList<>(4);
        frontFace.corners3D.add(0, new Vector3D(0,0,0));
        frontFace.corners3D.add(1, new Vector3D(0,height,0));
        frontFace.corners3D.add(2, new Vector3D(width,height,0));
        frontFace.corners3D.add(3, new Vector3D(width,0,0));
        frontFace.center = new Vector3D(width/2, height/2,0);
        // Add front face in index 0
        faces.add(0, frontFace);
        // -------------------------------------------------



        // Back Face---------------------------
        Face backFace = new Face();
        backFace.cubeSide = Face.Side.BACK;
        backFace.corners3D = new ArrayList<>(4);
        backFace.corners3D.add(0, new Vector3D(0,0,depth));
        backFace.corners3D.add(1, new Vector3D(0,height,depth));
        backFace.corners3D.add(2, new Vector3D(width,height,depth));
        backFace.corners3D.add(3, new Vector3D(width,0,depth));
        backFace.center = new Vector3D(width/2, height/2, depth);
        // Add back face in index 1
        faces.add(1, backFace);
        // -------------------------------------------------



        // Left Face---------------------------
        Face leftFace = new Face();
        leftFace.cubeSide = Face.Side.LEFT;
        leftFace.corners3D = new ArrayList<>(4);
        leftFace.corners3D.add(0, new Vector3D(0,0,0));
        leftFace.corners3D.add(1, new Vector3D(0,0,depth));
        leftFace.corners3D.add(2, new Vector3D(0, height, depth));
        leftFace.corners3D.add(3, new Vector3D(0,height,0));
        leftFace.center = new Vector3D(0, height/2, depth/2);
        // Add right face in index 2
        faces.add(2, leftFace);
        // -------------------------------------------------



        // Right Face---------------------------
        Face rightFace = new Face();
        rightFace.cubeSide = Face.Side.RIGHT;
        rightFace.corners3D = new ArrayList<>(4);
        rightFace.corners3D.add(0, new Vector3D(width,0,0));
        rightFace.corners3D.add(1, new Vector3D(width,0,depth));
        rightFace.corners3D.add(2, new Vector3D(width, height, depth));
        rightFace.corners3D.add(3, new Vector3D(width,height,0));
        rightFace.center = new Vector3D(width/2, height/2, depth/2);
        // Add right face in index 3
        faces.add(3, rightFace);
        // -------------------------------------------------



        // Top Face---------------------------
        Face topFace = new Face();
        topFace.cubeSide = Face.Side.TOP;
        topFace.corners3D = new ArrayList<>(4);
        topFace.corners3D.add(0, new Vector3D(0,0,0));
        topFace.corners3D.add(1, new Vector3D(0,0,depth));
        topFace.corners3D.add(2, new Vector3D(width, 0, depth));
        topFace.corners3D.add(3, new Vector3D(width,0,0));
        topFace.center = new Vector3D(width/2, 0, depth/2);
        // Add top face in index 4
        faces.add(4, topFace);
        // -------------------------------------------------



        // Bottom Face---------------------------
        Face bottomFace = new Face();
        bottomFace.cubeSide = Face.Side.BOTTOM;
        bottomFace.corners3D = new ArrayList<>(4);
        bottomFace.corners3D.add(0, new Vector3D(0,height,0));
        bottomFace.corners3D.add(1, new Vector3D(0,height,depth));
        bottomFace.corners3D.add(2, new Vector3D(width, height, depth));
        bottomFace.corners3D.add(3, new Vector3D(width,height,0));
        bottomFace.center = new Vector3D(width/2, height, depth/2);
        // Add bottom face in index 5
        faces.add(5, bottomFace);
        // -------------------------------------------------
    }


    private void Update2DPoints(Point drawOrigin){
        //Update the 2D points of all the faces
        for(int i = 0; i < faces.size(); i++){
            Update2DPoints(drawOrigin, i);
        }
    }

    private void Update2DPoints(Point drawOrigin, int faceIndex) {
        // Calculates the projected coordinates of the 3D points in a cube face
        List<Point> point2DS = new ArrayList<>(4);


        Vector3D vector3D;
        //Convert 3D Points to 2D
        for (int index = 0; index < 4; index++){
            Face face;
            face = faces.get(faceIndex);
            vector3D = face.corners3D.get(index);
            point2DS.add(index, Get2D(vector3D, drawOrigin));
        }
        // Update face
        faces.get(faceIndex).corners2D = point2DS;
        List<Point> corners2D = faces.get(faceIndex).corners2D;
        corners2D = point2DS;
    }


    // Converts 3D points to 2D points
    private Point Get2D(Vector3D vector3D, Point drawOrigin){
        Point point2D = Get2D(vector3D);
        return new Point(point2D.x + drawOrigin.x, point2D.y + drawOrigin.y);
    }

    private Point Get2D(Vector3D vector3D){
        Point returnPoint = new Point();

        float zoom = (float)Window.WIDTH / 1.5f;
        Vector3D tempCam = new Vector3D();

        tempCam.setX(cubeOrigin.getX());
        tempCam.setY(cubeOrigin.getY());
        tempCam.setZ((cubeOrigin.getX() * zoom) / cubeOrigin.getX());

        float zValue = -vector3D.getZ() - tempCam.getZ();

        returnPoint.x = (int) ((tempCam.getX() - vector3D.getX()) / zValue * zoom);
        returnPoint.y = (int) ((tempCam.getY() - vector3D.getY()) / zValue * zoom);

        return returnPoint;
    }

    public List<Point> GetFrontFace() {
        return getFace(Face.Side.FRONT).corners2D;
    }
    public List<Point> GetRightFace() {
        return getFace(Face.Side.RIGHT).corners2D;
    }
    public List<Point> GetLeftFace() {
        return getFace(Face.Side.LEFT).corners2D;
    }
    public List<Point> GetTopFace() {
        return getFace(Face.Side.TOP).corners2D;
    }
    int[] getXPoint(List<Point> points){
        int[] pointsX = new int[4];
        for (int i = 0; i < points.size(); i++){
            pointsX[i] = points.get(i).x;
        }
        return pointsX;
    }
    int[] getYPoint(List<Point> points){
        int[] pointsY = new int[4];
        for (int i = 0; i < points.size(); i++){
            pointsY[i] = points.get(i).y;
        }
        return pointsY;
    }
    public List<Point> GetBackFace()
    {
        return getFace(Face.Side.BACK).corners2D;
    }
    public List<Point> GetBottomFace()
    {
        return getFace(Face.Side.BOTTOM).corners2D;
    }
    private Face getFace(Face.Side side){
        //Find the correct side
        //Since faces are sorted in order of closest to farthest
        //They won't always be in the same index
        for (int i = 0; i < faces.size(); i++)
        {
            if (faces.get(i).cubeSide == side)
                return faces.get(i);
        }
        return null;
    }

    private Rectangle getDrawingBounds(){
        //Find the farthest most point to calculate the size of the returning bitmap
        float left = Float.MAX_VALUE;
        float right = Float.MIN_VALUE;
        float top = Float.MAX_VALUE;
        float bottom = Float.MIN_VALUE;

        for (int i = 0; i < faces.size(); i++){
            for (int j = 0; j < faces.get(i).corners2D.size(); j++){
                if(faces.get(i).corners2D.get(j).x < left)
                    left = faces.get(i).corners2D.get(j).x;
                if(faces.get(i).corners2D.get(j).x > right)
                    right = faces.get(i).corners2D.get(j).x;
                if(faces.get(i).corners2D.get(j).y > bottom)
                    bottom = faces.get(i).corners2D.get(j).y;
                if(faces.get(i).corners2D.get(j).y < top)
                    top = faces.get(i).corners2D.get(j).y;
            }
        }
        return new Rectangle(0, 0, (int)Math.round(right - left),(int)Math.round(bottom - top));
    }
    public void RotateCubeX(float deltaX)
    {
        for (int i = 0; i < faces.size(); i++)
        {
            //Apply rotation
            //------Rotate points
            Vector3D point0 = new Vector3D(0, 0, 0);
            faces.get(i).corners3D = Math3D.Translate(faces.get(i).corners3D, cubeOrigin, point0); //Move corner to origin
            faces.get(i).corners3D = Math3D.RotateX(faces.get(i).corners3D, deltaX);
            faces.get(i).corners3D = Math3D.Translate(faces.get(i).corners3D, point0, cubeOrigin); //Move back

            //-------Rotate center
            faces.get(i).center = Math3D.Translate(faces.get(i).center, cubeOrigin, point0);
            faces.get(i).center = Math3D.RotateX(faces.get(i).center, deltaX);
            faces.get(i).center = Math3D.Translate(faces.get(i).center, point0, cubeOrigin);
        }


    }

    public void RotateCubeY(float deltaY)
    {
        for (int i = 0; i < faces.size(); i++)
        {
            //Apply rotation
            //------Rotate points
            Vector3D point0 = new Vector3D(0, 0, 0);
            faces.get(i).corners3D = Math3D.Translate(faces.get(i).corners3D, cubeOrigin, point0); //Move corner to origin
            faces.get(i).corners3D = Math3D.RotateY(faces.get(i).corners3D, deltaY);
            faces.get(i).corners3D = Math3D.Translate(faces.get(i).corners3D, point0, cubeOrigin); //Move back

            //-------Rotate center
            faces.get(i).center = Math3D.Translate(faces.get(i).center, cubeOrigin, point0);
            faces.get(i).center = Math3D.RotateY(faces.get(i).center, deltaY);
            faces.get(i).center = Math3D.Translate(faces.get(i).center, point0, cubeOrigin);
        }
    }

    public void RotateCubeZ(float deltaZ)
    {
        for (int i = 0; i < faces.size(); i++)
        {
            //Apply rotation
            //------Rotate points
            Vector3D point0 = new Vector3D(0, 0, 0);
            faces.get(i).corners3D = Math3D.Translate(faces.get(i).corners3D, cubeOrigin, point0); //Move corner to origin
            faces.get(i).corners3D = Math3D.RotateZ(faces.get(i).corners3D, deltaZ);
            faces.get(i).corners3D = Math3D.Translate(faces.get(i).corners3D, point0, cubeOrigin); //Move back

            //-------Rotate center
            faces.get(i).center = Math3D.Translate(faces.get(i).center, cubeOrigin, point0);
            faces.get(i).center = Math3D.RotateZ(faces.get(i).center, deltaZ);
            faces.get(i).center = Math3D.Translate(faces.get(i).center, point0, cubeOrigin);
        }
    }


    public void DrawCube(Point drawOrigin,Graphics2D g)
    {
        //Get the corresponding 2D
        Update2DPoints(drawOrigin);
        g.setColor(Color.BLACK);



        int[] xPoint;
        int[] yPoint;

        //sort faces from closets to farthest
        //message();
        for (int i = faces.size() - 1; i >= 0; i--) //draw faces from back to front
        {
            switch (faces.get(i).cubeSide)
            {
                case FRONT:

                    if (fillFront){
                        xPoint = getXPoint(GetFrontFace());
                        yPoint = getYPoint(GetFrontFace());
                        g.fillPolygon(xPoint, yPoint, GetFrontFace().size());
                    }

                    break;
                case BACK:

                    if (fillBack ){
                        xPoint = getXPoint(GetBackFace());
                        yPoint = getYPoint(GetBackFace());
                        g.fillPolygon(xPoint, yPoint, GetBackFace().size());
                    }

                    break;
                case LEFT:

                    if (fillLeft){
                        xPoint = getXPoint(GetLeftFace());
                        yPoint = getYPoint(GetLeftFace());
                        g.fillPolygon(xPoint, yPoint, GetLeftFace().size());
                    }
                    break;
                case RIGHT:

                    if (fillRight){
                        xPoint = getXPoint(GetRightFace());
                        yPoint = getYPoint(GetRightFace());
                        g.fillPolygon(xPoint, yPoint, GetRightFace().size());
                    }
                    break;
                case TOP:
                    if (fillTop){
                        xPoint = getXPoint(GetTopFace());
                        yPoint = getYPoint(GetTopFace());
                        g.fillPolygon(xPoint, yPoint, GetTopFace().size());
                    }
                    break;
                case BOTTOM:
                    if (fillBottom){
                        xPoint = getXPoint(GetBottomFace());
                        yPoint = getYPoint(GetBottomFace());
                        g.fillPolygon(xPoint, yPoint, GetBottomFace().size());
                    }
                default:
                    break;
            }

            if (drawWires)
            {
                g.drawLine(faces.get(i).corners2D.get(0).x,faces.get(i).corners2D.get(0).y, faces.get(i).corners2D.get(1).x,faces.get(i).corners2D.get(1).y);
                g.drawLine(faces.get(i).corners2D.get(1).x,faces.get(i).corners2D.get(1).y, faces.get(i).corners2D.get(2).x,faces.get(i).corners2D.get(2).y);
                g.drawLine(faces.get(i).corners2D.get(2).x,faces.get(i).corners2D.get(2).y, faces.get(i).corners2D.get(3).x,faces.get(i).corners2D.get(3).y);
                g.drawLine(faces.get(i).corners2D.get(3).x,faces.get(i).corners2D.get(3).y, faces.get(i).corners2D.get(0).x,faces.get(i).corners2D.get(0).y);
            }

        }
    }
}
