import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class SceneRebuild {
    public static void matchGameObjectsAndTransforms() {
        int size = Main.gameObjects.size();
        long id;
        Main.pairs = new ArrayList<>();
        HashMap<Long, GameObject> gameObjectIDs = new HashMap<Long, GameObject>();

        for (int i = 0; i < size; i++) {
            id = Main.gameObjects.get(i).getObjectID();
            gameObjectIDs.put(id, Main.gameObjects.get(i));
        }

        size = Main.transforms.size();
        Transform transform;
        for (int j = 0; j < size; j++) {
            transform = Main.transforms.get(j);
            GameObject obj = gameObjectIDs.get(transform.getGameObjectID());

            if (obj != null) {
                Main.pairs.add(new CompleteObject(obj, transform));
            } else {
               // System.out.println();
            }
        }

        Main.mainScene = buildHierarchy(Main.transforms, Main.gameObjects);
        attachPhysBonesToNodes(Main.physbones, Main.mainScene);
        // With a GUI, we will pause here. We will display the entire root scene to the user and have them select (single option only) which object we're parsing.
        // As some people (me included) have their Quest and PC version in the scene file.
        ExtractPhysbones.buildPhysBoneChains(Main.physbones, Main.mainScene);
    }


// This one was written by chatGPT. I despise working with anything involving nodes and/or linking. Linked lists are the spawn of Satan himself.
    public static Hierarchy buildHierarchy(ArrayList<Transform> transforms, ArrayList<GameObject> gameObjects) {
        HashMap<Long, GameObject> objectsById = new HashMap<Long, GameObject>(gameObjects.size() * 2);
        HashMap<Long, Node> nodeByTransformId = new HashMap<Long, Node>(transforms.size() * 2);
        HashSet<Long> seenAsChild = new HashSet<Long>();


        for (GameObject object : gameObjects) {
            objectsById.put(object.getObjectID(), object);
        }

        for (int j = 0; j < transforms.size(); j++) {
            Transform transform = transforms.get(j);
            GameObject gameObject = objectsById.get(transform.getGameObjectID());
            nodeByTransformId.put(transform.getTransformID(), new Node(transform, gameObject));
        }


        for (int i = 0; i < transforms.size(); i++) {
            Transform transform = transforms.get(i);
            Node parent = nodeByTransformId.get(transform.getTransformID());
            ArrayList<Long> kids = transform.getChildren();

            if (kids != null) {
                for (int k = 0; k < kids.size(); k++) {
                    Long childId = kids.get(k);
                    Node child = nodeByTransformId.get(childId);
                    if (child != null) {
                        child.setParent(parent);
                        parent.getChildren().add(child); // preserves com.unityscenereader.Unity order
                        seenAsChild.add(childId);
                    }
                }
            }
        }

        ArrayList<Node> roots = new ArrayList<Node>();
        for (int i = 0; i < transforms.size(); i++) {
            Transform transform = transforms.get(i);
            if (!seenAsChild.contains(transform.getTransformID())) {
                roots.add(nodeByTransformId.get(transform.getTransformID()));
            }
        }

        HashMap<Long, Node> byGameObjectID = new HashMap<Long, Node>(gameObjects.size() * 2);
        java.util.ArrayDeque<Node> q = new java.util.ArrayDeque<Node>();
        for (int i = 0; i < roots.size(); i++) q.addLast(roots.get(i));
        while (!q.isEmpty()) {
            Node n = q.removeFirst();
            if (n.getGameObject() != null) byGameObjectID.put(n.getGameObject().getObjectID(), n);
            for (int i = 0; i < n.getChildren().size(); i++) q.addLast(n.getChildren().get(i));
        }

        // Yep.It's actually pieced together here.
        return new Hierarchy(roots, nodeByTransformId, byGameObjectID);
    }

    static void attachPhysBonesToNodes(ArrayList<PhysBone> physbones, Hierarchy h) {
        if (physbones == null || physbones.isEmpty()) return;

        for (int i = 0; i < physbones.size(); i++) {
            PhysBone pb = physbones.get(i);

            // 1) Where the component lives (owning GameObject)
            long goId = pb.getObjectID(); // <-- GameObject ID
            Node attachNode = (goId != 0L && goId != -1L) ? h.byGameObjectId.get(goId) : null;

            // 2) Governing root (explicit transform if provided; else the attach node)
            long rtId = pb.getRootTransform(); // <-- Transform ID
            Node rootNode = (rtId != 0L && rtId != -1L) ? h.byTransformId.get(rtId) : null;
            if (rootNode == null) rootNode = attachNode; // Approach B fallback

            // 3) Attach to the owning GO node so you'll see it under "Physics"/"Tail Physics", etc.
            if (attachNode != null) {
                attachNode.getPhysbones().add(pb);

                // Optional sanity: ensure the GO lists this MonoBehaviour component id among its components
                // (pb.id is the MonoBehaviour fileID)
                ArrayList<Long> comps = (attachNode.getGameObject() != null) ? attachNode.getGameObject().getComponents() : null;
                if (comps == null || !comps.contains(Long.valueOf(pb.getId()))) {
                     System.out.println("[WARN] PB mono " + pb.getId() + " not listed on GO " + goId);
                }

                // Optional: persist where it governs/attaches back onto the object if you have setters
                // pb.setAttachTransformId(attachNode.transform.getTransformID());
                // pb.setEffectiveRootTransformId(rootNode != null ? rootNode.transform.getTransformID() : 0L);
            } else {
                // Couldn’t place this PB; useful log while debugging:
                System.out.println("[WARN] No attach node for PB mono=" + pb.getId() + " go=" + goId + " rootTr=" + rtId);
            }
        }
    }

    static void verifyParentConsistency(HashMap<Long, Node> nodeByTrId) {
        for (Node n : nodeByTrId.values()) {
            long expected = n.getTransform().getFatherID();
            long actual = (n.getParent() == null ? 0L : n.getParent().getTransform().getTransformID());
            if (expected != 0L && expected != -1L && expected != actual) {
                System.out.println("Parent mismatch for transform=" + n.getTransform().getTransformID() +
                        " expected fatherId=" + expected + " got=" + actual);
            }
        }
    }

    public static Node findNodeByPath(List<Node> roots, String... names) {
        java.util.ArrayDeque<Node> q = new java.util.ArrayDeque<Node>();
        for (int i = 0; i < roots.size(); i++) q.addLast(roots.get(i));
        int depth = 0;
        Node matchAtDepth = null;

        while (!q.isEmpty() && depth < names.length) {
            String want = names[depth];
            int levelSize = q.size();
            matchAtDepth = null;
            for (int i = 0; i < levelSize; i++) {
                Node n = q.removeFirst();
                String nm = (n.getGameObject() != null ? n.getGameObject().getGameObjectName() : null);
                if (nm != null && nm.equals(want)) {
                    matchAtDepth = n;
                    // push *only* this node’s children for next depth
                    for (int c = 0; c < n.getChildren().size(); c++) q.addLast(n.getChildren().get(c));
                } else {
                    // skip its subtree when not matching at this depth
                }
            }
            if (matchAtDepth == null) return null; // path segment not found
            depth++;
        }
        return (depth == names.length) ? matchAtDepth : null;
    }

    public static void printNodeSummary(Node n) {
        if (n == null) { System.out.println("(null node)"); return; }
        String nm = (n.getGameObject() != null ? n.getGameObject().getGameObjectName() : "(no GO)");
        long trId = (n.getTransform() != null ? n.getTransform().getTransformID() : -1);
        System.out.println("Node: " + nm + "  trId=" + trId);
        System.out.println("  components: " + (n.getGameObject() != null ? n.getGameObject().getComponents() : null));
        System.out.print("  physbones (" + n.getPhysbones().size() + "):");
        for (int i = 0; i < n.getPhysbones().size(); i++) {
            PhysBone pb = n.getPhysbones().get(i);
            System.out.print(" [mono=" + pb.getId() + " go=" + pb.getObjectID()
                    + " rootTr=" + pb.getRootTransform() + "]");
        }
        System.out.println();
    }
}
