import java.util.ArrayList;

public class ExtractPhysbones {
    static void buildPhysBoneChains(ArrayList<PhysBone> physbones, Hierarchy h) {
        ArrayList<PhysBoneChain> out = new ArrayList<>();
        if (physbones == null || physbones.isEmpty()) return;

        for (int i = 0; i < physbones.size(); i++) {
            PhysBone physbone = physbones.get(i);

            // Attach node (owner GO)
            Node attach = null;
            long gameObjectId = physbone.getObjectID();
            if (gameObjectId != 0L && gameObjectId != -1L) attach = h.byGameObjectId.get(gameObjectId);

            // Root node (explicit rootTransform preferred)
            Node root = null;
            long rootId = physbone.getRootTransform();
            if (rootId != 0L && rootId != -1L) root = h.byTransformId.get(rootId);
            if (root == null) root = attach; // fallback for Approach B

            // If we can’t resolve a root, skip but keep going
            if (root == null) {
                // System.out.println("[WARN] PB mono=" + pb.getId() + " has no resolvable root/attach node.");
                continue;
            }

            ArrayList<Node> chain = new ArrayList<Node>();
            preorderCollect(root, chain); // root + all descendants

            out.add(new PhysBoneChain(physbone, attach, root, chain));
        }
        System.out.println();
        grabBoneChain(out);
    }


    public static void grabBoneChain(ArrayList<PhysBoneChain> chain){
        PhysBoneChain pbc;

        String boneName;
        Transform from;
        Transform to = new Transform();
        float[] fromPos;
        float[] toPos;
        double distance;
        double scaledDistance;
 //       for(int i = 0; i < chain.size(); i++){
            pbc = chain.get(0); // i
            for(int j = 0; j < pbc.getChain().size(); j++){
                // GET ready for getter central here!
                boneName = pbc.getChain().get(j).getGameObject().getGameObjectName();
                from = pbc.getChain().get(j).getTransform();
                try{
                    to = pbc.getChain().get(j).getChildren().get(0).getTransform();
                } catch (IndexOutOfBoundsException ioobe){
                    System.out.println();
                    // This just means that the bone has no children if we hit this.
                }

                fromPos = new float[]{from.getPosX(), from.getPosY(), from.getPosZ()};
                toPos = new float[]{to.getPosX(), to.getPosY(), to.getPosZ()};
                distance = Maths.calculate3DDistance(fromPos, toPos);

                scaledDistance = Maths.scaledDistance(distance);
                if(scaledDistance <= 1.00){
                    scaledDistance *= 100;
                } else {
                    scaledDistance *= 10;
                }
                System.out.println("Bone Name: " + boneName + " I " + "Distance: " + scaledDistance);
            }
     //   }
    }

    static void preorderCollect(Node n, ArrayList<Node> out) {
        if (n == null) return;
        out.add(n);
        for (int i = 0; i < n.getChildren().size(); i++) {
            preorderCollect(n.getChildren().get(i), out);
        }
    }
}
