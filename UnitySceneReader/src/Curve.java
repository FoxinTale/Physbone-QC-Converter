import java.util.ArrayList;

public class Curve {
    private ArrayList<CurvePoint> points;
    private String curveName;
    private int preInfinity;
    private int postInfinity;
    private int rotationOrder;

    public Curve(){}

   public Curve(ArrayList<CurvePoint> points, String name, int preInfinity, int postInfinity, int rotationOrder){
        this.points = points;
        this.curveName = name;
        this.preInfinity = preInfinity;
        this.postInfinity = postInfinity;
        this.rotationOrder = rotationOrder;
   }

    public ArrayList<CurvePoint> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<CurvePoint> points) {
        this.points = points;
    }

    public String getCurveName() {
        return curveName;
    }

    public void setCurveName(String curveName) {
        this.curveName = curveName;
    }

    public int getPreInfinity() {
        return preInfinity;
    }

    public void setPreInfinity(int preInfinity) {
        this.preInfinity = preInfinity;
    }

    public int getPostInfinity() {
        return postInfinity;
    }

    public void setPostInfinity(int postInfinity) {
        this.postInfinity = postInfinity;
    }

    public int getRotationOrder() {
        return rotationOrder;
    }

    public void setRotationOrder(int rotationOrder) {
        this.rotationOrder = rotationOrder;
    }

    @Override
    public String toString() {
        return "Curve{" +
                "points=" + points +
                ", curveName='" + curveName + '\'' +
                ", preInfinity=" + preInfinity +
                ", postInfinity=" + postInfinity +
                ", rotationOrder=" + rotationOrder +
                '}';
    }
}
