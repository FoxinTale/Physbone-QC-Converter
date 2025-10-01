public class CompleteObject {

    private GameObject gameObject;
    private Transform transform;
    private PhysBone physbone;

    public CompleteObject(){

    }

    public CompleteObject(GameObject gameObject, Transform transform){
        this.gameObject = gameObject;
        this.transform = transform;
    }
}
