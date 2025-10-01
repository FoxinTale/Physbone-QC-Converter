import java.util.ArrayList;

/*
    The main purpose of this program is to parse an entire com.unityscenereader.Unity scene file to make converting a VR Chat Avatar over to Garry's Mod much less painful.

    We're primarily looking at turning the physbones over to jigglebones.
    I thought about using an interface and having the user manually enter everything... but I decided against that.
    This way is less painful on the user's side. Sure, we will have options available, but much less input is required than originally.

    This is all possible because a com.unityscenereader.Unity scene file is literally just a special YAML text file. That's all it is. So we can read it and sort through the nonsense.

    It parses a com.unityscenereader.Unity scene file, reads the transforms, game pbjects, and physbones. Then it re-constructs the layout within the scene.
    Once the structure is re-created, then we output to the user some sort of graphical display, so they know what's gone on.
    Some may have two "root" items in their scene, like a PC and Quest version.
 */
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static ArrayList<Transform> transforms;
    public static ArrayList<GameObject> gameObjects;
    public static ArrayList<PhysBone> physbones;
    public static ArrayList<CompleteObject> pairs;
    public static Hierarchy mainScene;

    public static void main(String[] args) {
       ReadSceneFile.readScene();
    }
}

/*
    There are four main components to this project.
       x Parsing the Unity Scene file:
            x Read it.
            x Get Game Objects.
            x Get Transforms.
            x Get physbones, and their curves.
            x Match them all together.
            x And reconstruct the scene itself.

       x Do the maths to calculate the distance.
            x Get the physbones from the scene.
            x Determine their root bone.
            x Find the bone chain that these are all on.
            - Calculate the distance between each bone in the chain.

        Display it all to the user.

        Generate the QC file.
            - Have the user point it towards the working folder for the GMod addon.
            - Search for files ending in .dmx.
            
 */