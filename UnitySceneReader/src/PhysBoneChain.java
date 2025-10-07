import java.util.ArrayList;

public class PhysBoneChain {
    private PhysBone physbone;
    private Node attachNode;     // where the MonoBehaviour lives (Physics/Tail Physics/etc.)
    private Node rootNode;       // transform that governs the chain
    private ArrayList<Node> chain; // preorder list: root, then all descendants


    public PhysBoneChain(PhysBone physbone, Node attachNode, Node rootNode, ArrayList<Node> chain) {
        this.physbone = physbone;
        this.attachNode = attachNode;
        this.rootNode = rootNode;
        this.chain = chain;
    }

    public PhysBoneChain() {

    }

    public PhysBone getPhysbone() {
        return physbone;
    }

    public void setPhysbone(PhysBone physbone) {
        this.physbone = physbone;
    }

    public Node getAttachNode() {
        return attachNode;
    }

    public void setAttachNode(Node attachNode) {
        this.attachNode = attachNode;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    public ArrayList<Node> getChain() {
        return chain;
    }

    public void setChain(ArrayList<Node> chain) {
        this.chain = chain;
    }
}
