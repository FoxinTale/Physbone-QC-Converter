import java.util.ArrayList;
import java.util.HashMap;

public class Hierarchy {
    public final ArrayList<Node> roots;
    public final HashMap<Long, Node> byTransformId;
    public final HashMap<Long, Node> byGameObjectId;

    public Hierarchy(ArrayList<Node> roots, HashMap<Long, Node> byTransform, HashMap<Long, Node> byGameObject) {
        this.roots = roots;
        this.byTransformId = byTransform;
        this.byGameObjectId = byGameObject;
    }


}