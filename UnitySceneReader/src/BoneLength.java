public class BoneLength {

    private String fromBone;
    private String toBone;
    private float length;
    private float scaledLength;

    public BoneLength(){
    }

    public BoneLength(String fromBone, String toBone, float length, float scaledLength) {
        this.fromBone = fromBone;
        this.toBone = toBone;
        this.length = length;
        this.scaledLength = scaledLength;
    }

    public String getFromBone() {
        return fromBone;
    }

    public void setFromBone(String fromBone) {
        this.fromBone = fromBone;
    }

    public String getToBone() {
        return toBone;
    }

    public void setToBone(String toBone) {
        this.toBone = toBone;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getScaledLength() {
        return scaledLength;
    }

    public void setScaledLength(float scaledLength) {
        this.scaledLength = scaledLength;
    }
}
