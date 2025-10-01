public class PhysBone {
    // PhysBones have a lot of parameters. Good lord.
    private long id;
    private long objectID;
    private long rootTransform;
    private float pull;
    private Curve pullCurve;
    private float spring;
    private Curve springCurve;
    private float stiffness;
    private Curve stiffnessCurve;
    private float gravity;
    private Curve gravityCurve;
    private float gravityFalloff;
    private Curve gravityFalloffCurve;
    private float immobile;
    private Curve immobileCurve;
    private int limitType;
    private float maxAngleX;
    private Curve maxAngleXCurve;
    private float maxAngleY;
    private Curve maxAngleYCurve;
    private float maxAngleZ;
    private Curve maxAngleZCurve;
    private float rotLimitX;
    private Curve rotLimitXCurve;
    private float rotLimitY;
    private Curve rotLimitYCurve;
    private float rotLimitZ;
    private Curve rotLimitZCurve;

/*
    There's quite a few other physbone parameters available, but they do not matter to us for the purposes we need here.
    Source engine has zero collision for jiggle bones, so we can ignore anything relating to colliders and radius.

    ImmobileType doesn't really translate well over engines, so that also is cut.
    Grab, Pose, Stretch and Squish all also just don't exist in a jigglebone.

 */
    public void PhysBone(){

    }

    public PhysBone(long id, long gameObjectID, long rootTransformID, float pull, float spring, float stiffness, float gravity,
                    float gravityFalloff, float immobile, int limitType, float maxAngleX, float maxAngleY, float maxAngleZ) {
        this.id = id;
        this.objectID = gameObjectID;
        this.rootTransform = rootTransformID;
        this.pull = pull;
        this.spring = spring;
        this.stiffness = stiffness;
        this.gravity = gravity;
        this.gravityFalloff = gravityFalloff;
        this.immobile = immobile;
        this.limitType = limitType;
        this.maxAngleX = maxAngleX;
        this.maxAngleY = maxAngleY;
        this.maxAngleZ = maxAngleZ;
    }

    public PhysBone(long id, long objectID, long rootTransform, float pull, Curve pullCurve, float spring, Curve springCurve,
                    float stiffness, Curve stiffnessCurve, float gravity, Curve gravityCurve, float gravityFalloff,
                    Curve gravityFalloffCurve, float immobile, Curve immobileCurve, int limitType, float maxAngleX,
                    Curve maxAngleXCurve,float maxAngleZ, Curve maxAngleZCurve,
                    float rotLimitX, Curve rotLimitXCurve, float rotLimitY, Curve rotLimitYCurve, float rotLimitZ, Curve rotLimitZCurve) {
        this.id = id;
        this.objectID = objectID;
        this.rootTransform = rootTransform;
        this.pull = pull;
        this.pullCurve = pullCurve;
        this.spring = spring;
        this.springCurve = springCurve;
        this.stiffness = stiffness;
        this.stiffnessCurve = stiffnessCurve;
        this.gravity = gravity;
        this.gravityCurve = gravityCurve;
        this.gravityFalloff = gravityFalloff;
        this.gravityFalloffCurve = gravityFalloffCurve;
        this.immobile = immobile;
        this.immobileCurve = immobileCurve;
        this.limitType = limitType;
        this.maxAngleX = maxAngleX;
        this.maxAngleXCurve = maxAngleXCurve;
        this.maxAngleZ = maxAngleZ;
        this.maxAngleZCurve = maxAngleZCurve;
        this.rotLimitX = rotLimitX;
        this.rotLimitXCurve = rotLimitXCurve;
        this.rotLimitY = rotLimitY;
        this.rotLimitYCurve = rotLimitYCurve;
        this.rotLimitZ = rotLimitZ;
        this.rotLimitZCurve = rotLimitZCurve;
    }

    public void PhysBone(long id, long objectID, float pull, float spring, float gravity, float gravityFalloff){
        this.id = id;
        this.objectID = objectID;
        this.pull = pull;
        this.spring = spring;
        this.gravity = gravity;
        this.gravityFalloff = gravityFalloff;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getObjectID() {
        return objectID;
    }

    public void setObjectID(long objectID) {
        this.objectID = objectID;
    }

    public long getRootTransform() {
        return rootTransform;
    }

    public void setRootTransform(long rootTransform) {
        this.rootTransform = rootTransform;
    }

    public float getPull() {
        return pull;
    }

    public void setPull(float pull) {
        this.pull = pull;
    }

    public Curve getPullCurve() {
        return pullCurve;
    }

    public void setPullCurve(Curve pullCurve) {
        this.pullCurve = pullCurve;
    }

    public float getSpring() {
        return spring;
    }

    public void setSpring(float spring) {
        this.spring = spring;
    }

    public Curve getSpringCurve() {
        return springCurve;
    }

    public void setSpringCurve(Curve springCurve) {
        this.springCurve = springCurve;
    }

    public float getStiffness() {
        return stiffness;
    }

    public void setStiffness(float stiffness) {
        this.stiffness = stiffness;
    }

    public Curve getStiffnessCurve() {
        return stiffnessCurve;
    }

    public void setStiffnessCurve(Curve stiffnessCurve) {
        this.stiffnessCurve = stiffnessCurve;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public Curve getGravityCurve() {
        return gravityCurve;
    }

    public void setGravityCurve(Curve gravityCurve) {
        this.gravityCurve = gravityCurve;
    }

    public float getGravityFalloff() {
        return gravityFalloff;
    }

    public void setGravityFalloff(float gravityFalloff) {
        this.gravityFalloff = gravityFalloff;
    }

    public Curve getGravityFalloffCurve() {
        return gravityFalloffCurve;
    }

    public void setGravityFalloffCurve(Curve gravityFalloffCurve) {
        this.gravityFalloffCurve = gravityFalloffCurve;
    }

    public float getImmobile() {
        return immobile;
    }

    public void setImmobile(float immobile) {
        this.immobile = immobile;
    }

    public Curve getImmobileCurve() {
        return immobileCurve;
    }

    public void setImmobileCurve(Curve immobileCurve) {
        this.immobileCurve = immobileCurve;
    }

    public int getLimitType() {
        return limitType;
    }

    public void setLimitType(int limitType) {
        this.limitType = limitType;
    }

    public float getMaxAngleX() {
        return maxAngleX;
    }

    public void setMaxAngleX(float maxAngleX) {
        this.maxAngleX = maxAngleX;
    }

    public Curve getMaxAngleXCurve() {
        return maxAngleXCurve;
    }

    public void setMaxAngleXCurve(Curve maxAngleXCurve) {
        this.maxAngleXCurve = maxAngleXCurve;
    }

    public float getMaxAngleY() {
        return maxAngleY;
    }

    public void setMaxAngleY(float maxAngleY) {
        this.maxAngleY = maxAngleY;
    }

    public Curve getMaxAngleYCurve() {
        return maxAngleYCurve;
    }

    public void setMaxAngleYCurve(Curve maxAngleYCurve) {
        this.maxAngleYCurve = maxAngleYCurve;
    }

    public float getMaxAngleZ() {
        return maxAngleZ;
    }

    public void setMaxAngleZ(float maxAngleZ) {
        this.maxAngleZ = maxAngleZ;
    }

    public Curve getMaxAngleZCurve() {
        return maxAngleZCurve;
    }

    public void setMaxAngleZCurve(Curve maxAngleZCurve) {
        this.maxAngleZCurve = maxAngleZCurve;
    }

    public float getRotLimitX() {
        return rotLimitX;
    }

    public void setRotLimitX(float rotLimitX) {
        this.rotLimitX = rotLimitX;
    }

    public Curve getRotLimitXCurve() {
        return rotLimitXCurve;
    }

    public void setRotLimitXCurve(Curve rotLimitXCurve) {
        this.rotLimitXCurve = rotLimitXCurve;
    }

    public float getRotLimitY() {
        return rotLimitY;
    }

    public void setRotLimitY(float rotLimitY) {
        this.rotLimitY = rotLimitY;
    }

    public Curve getRotLimitYCurve() {
        return rotLimitYCurve;
    }

    public void setRotLimitYCurve(Curve rotLimitYCurve) {
        this.rotLimitYCurve = rotLimitYCurve;
    }

    public float getRotLimitZ() {
        return rotLimitZ;
    }

    public void setRotLimitZ(float rotLimitZ) {
        this.rotLimitZ = rotLimitZ;
    }

    public Curve getRotLimitZCurve() {
        return rotLimitZCurve;
    }

    public void setRotLimitZCurve(Curve rotLimitZCurve) {
        this.rotLimitZCurve = rotLimitZCurve;
    }

    @Override
    public String toString() {
        return "PhysBone{" +
                "id=" + id +
                ", objectID=" + objectID +
                ", rootTransform=" + rootTransform +
                ", pull=" + pull +
                ", pullCurve=" + pullCurve +
                ", spring=" + spring +
                ", springCurve=" + springCurve +
                ", stiffness=" + stiffness +
                ", stiffnessCurve=" + stiffnessCurve +
                ", gravity=" + gravity +
                ", gravityCurve=" + gravityCurve +
                ", gravityFalloff=" + gravityFalloff +
                ", gravityFalloffCurve=" + gravityFalloffCurve +
                ", immobile=" + immobile +
                ", immobileCurve=" + immobileCurve +
                ", limitType=" + limitType +
                ", maxAngleX=" + maxAngleX +
                ", maxAngleXCurve=" + maxAngleXCurve +
                ", maxAngleY=" + maxAngleY +
                ", maxAngleYCurve=" + maxAngleYCurve +
                ", maxAngleZ=" + maxAngleZ +
                ", maxAngleZCurve=" + maxAngleZCurve +
                ", rotLimitX=" + rotLimitX +
                ", rotLimitXCurve=" + rotLimitXCurve +
                ", rotLimitY=" + rotLimitY +
                ", rotLimitYCurve=" + rotLimitYCurve +
                ", rotLimitZ=" + rotLimitZ +
                ", rotLimitZCurve=" + rotLimitZCurve +
                '}';
    }
}
