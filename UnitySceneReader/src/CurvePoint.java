public class CurvePoint {
    private float time;
    private float value;
    private float inSlope;
    private float outSlope;
    private int tangentMode;
    private int weightedMode;
    private float inWeight;
    private float outWeight;

    public CurvePoint(){

    }


    public CurvePoint(float time, float value, float inSlope, float outSlope, int tangentMode, int weightedMode,
                      float inWeight, float outWeight){
        this.time = time;
        this.value = value;
        this.inSlope = inSlope;
        this.outSlope = outSlope;
        this.tangentMode = tangentMode;
        this.weightedMode = weightedMode;
        this.inWeight = inWeight;
        this.outWeight = outWeight;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setInSlope(float inSlope) {
        this.inSlope = inSlope;
    }

    public void setOutSlope(float outSlope) {
        this.outSlope = outSlope;
    }

    public void setTangentMode(int tangentMode) {
        this.tangentMode = tangentMode;
    }

    public void setWeightedMode(int weightedMode) {
        this.weightedMode = weightedMode;
    }

    public void setInWeight(float inWeight) {
        this.inWeight = inWeight;
    }

    public void setOutWeight(float outWeight) {
        this.outWeight = outWeight;
    }

    public float getTime() {
        return time;
    }

    public float getValue() {
        return value;
    }

    public float getInSlope() {
        return inSlope;
    }

    public float getOutSlope() {
        return outSlope;
    }

    public int getTangentMode() {
        return tangentMode;
    }

    public int getWeightedMode() {
        return weightedMode;
    }

    public float getInWeight() {
        return inWeight;
    }

    public float getOutWeight() {
        return outWeight;
    }

    @Override
    public String toString() {
        return "CurvePoint{" +
                "time=" + time +
                ", value=" + value +
                ", inSlope=" + inSlope +
                ", outSlope=" + outSlope +
                ", tangentMode=" + tangentMode +
                ", weightedMode=" + weightedMode +
                ", inWeight=" + inWeight +
                ", outWeight=" + outWeight +
                '}';
    }

}
