import java.util.ArrayList;

public class Transform {
    private long transformID;
    private long gameObjectID;
    private ArrayList<Long> children;
    private long fatherID;
    private float posX;
    private float posY;
    private float posZ;
    private float scaleX;
    private float scaleY;
    private float scaleZ;

    // While we could also get the rotation, this does not really matter much for our purposes. We only need the position.
    // The entire point of this is to calculate the length after all. So, in 3d space the rotation really shouldn't matter all that much.
    // Only reason we store the scale is for the root object. Doesn't really matter otherwise. It'll allow us to properly scale the avatar.

    public Transform(){

    }

    public Transform(long transformID, long gameObjectID, ArrayList<Long> children, long fatherID, float posX, float posY, float posZ,
                     float scaleX, float scaleY, float scaleZ){
        this.transformID = transformID;
        this.gameObjectID = gameObjectID;
        this.children = children;
        this.fatherID = fatherID;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.scaleZ = scaleZ;
    }

    public long getTransformID() {
        return transformID;
    }

    public void setTransformID(long transformID) {
        this.transformID = transformID;
    }

    public long getGameObjectID() {
        return gameObjectID;
    }

    public void setGameObjectID(long gameObjectID) {
        this.gameObjectID = gameObjectID;
    }

    public ArrayList<Long> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Long> children) {
        this.children = children;
    }

    public long getFatherID() {
        return fatherID;
    }

    public void setFatherID(long fatherID) {
        this.fatherID = fatherID;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getPosZ() {
        return posZ;
    }

    public void setPosZ(float posZ) {
        this.posZ = posZ;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public float getScaleZ() {
        return scaleZ;
    }

    public void setScaleZ(float scaleZ) {
        this.scaleZ = scaleZ;
    }

    @Override
    public String toString() {
        return "Transform{" +
                "transformID=" + transformID +
                ", gameObjectID=" + gameObjectID +
                ", children=" + children +
                ", fatherID=" + fatherID +
                ", posX=" + posX +
                ", posY=" + posY +
                ", posZ=" + posZ +
                ", scaleX=" + scaleX +
                ", scaleY=" + scaleY +
                ", scaleZ=" + scaleZ +
                '}';
    }
}
