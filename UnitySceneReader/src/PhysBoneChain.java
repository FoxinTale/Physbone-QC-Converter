import java.util.ArrayList;

public class PhysBoneChain {
    final PhysBone physbone;
    final Node attachNode;     // where the MonoBehaviour lives (Physics/Tail Physics/etc.)
    final Node rootNode;       // transform that governs the chain
    final ArrayList<Node> chain; // preorder list: root, then all descendants

    public PhysBoneChain(PhysBone physbone, Node attachNode, Node rootNode, ArrayList<Node> chain) {
        this.physbone = physbone;
        this.attachNode = attachNode;
        this.rootNode = rootNode;
        this.chain = chain;
    }
}
