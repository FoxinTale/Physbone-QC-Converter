import java.util.ArrayList;

public class Node {

    private Transform transform;
    private GameObject gameObject;
    private ArrayList<Node> children;
    Node parent;
    private ArrayList<PhysBone> physbones;

    public Node(){

    }

    public Node(Transform transform, GameObject gameobject){
        this.transform = transform;
        this.gameObject = gameobject;
        this.children = new ArrayList<>();
        this.physbones = new ArrayList<>();
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public ArrayList<PhysBone> getPhysbones(){
        return physbones;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
