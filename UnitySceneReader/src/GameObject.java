import java.util.ArrayList;

public class GameObject {
    private long objectID;
    private String gameObjectName;
    private ArrayList<Long> components;

    public GameObject(){

    }

    public GameObject(long objectID, String name, ArrayList<Long> components){
        this.objectID = objectID;
        this.gameObjectName = name;
        this.components = components;
    }

    public long getObjectID() {
        return objectID;
    }

    public void setObjectID(long objectID) {
        this.objectID = objectID;
    }

    public String getGameObjectName() {
        return gameObjectName;
    }

    public void setGameObjectName(String gameObjectName) {
        this.gameObjectName = gameObjectName;
    }

    public ArrayList<Long> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Long> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "GameObject{" +
                "objectID=" + objectID +
                ", gameObjectName='" + gameObjectName + '\'' +
                ", components=" + components +
                '}';
    }
}
