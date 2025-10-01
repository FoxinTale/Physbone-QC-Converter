import java.util.ArrayList;

public class ExtractPhysbones {
    static ArrayList<PhysBoneChain> buildPhysBoneChains(ArrayList<PhysBone> physbones, Hierarchy h) {
        ArrayList<PhysBoneChain> out = new ArrayList<>();
        if (physbones == null || physbones.isEmpty()) return out;

        for (int i = 0; i < physbones.size(); i++) {
            PhysBone pb = physbones.get(i);

            // Attach node (owner GO)
            Node attach = null;
            long goId = pb.getObjectID();
            if (goId != 0L && goId != -1L) attach = h.byGameObjectId.get(Long.valueOf(goId));

            // Root node (explicit rootTransform preferred)
            Node root = null;
            long rtId = pb.getRootTransform();
            if (rtId != 0L && rtId != -1L) root = h.byTransformId.get(Long.valueOf(rtId));
            if (root == null) root = attach; // fallback for Approach B

            // If we can’t resolve a root, skip but keep going
            if (root == null) {
                // System.out.println("[WARN] PB mono=" + pb.getId() + " has no resolvable root/attach node.");
                continue;
            }

            ArrayList<Node> chain = new ArrayList<Node>();
            preorderCollect(root, chain); // root + all descendants

            out.add(new PhysBoneChain(pb, attach, root, chain));
        }
        return out;
    }

    static void preorderCollect(Node n, ArrayList<Node> out) {
        if (n == null) return;
        out.add(n);
        for (int i = 0; i < n.getChildren().size(); i++) {
            preorderCollect(n.getChildren().get(i), out);
        }
    }
}
