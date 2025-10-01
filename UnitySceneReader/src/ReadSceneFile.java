import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadSceneFile {

    public static void readScene(){
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<Integer> gameObjectPositions = new ArrayList<>();
        ArrayList<Integer> transformPositions = new ArrayList<>();
        ArrayList<Integer> prefabPositions = new ArrayList<>();
        ArrayList<Integer> monoBehaviorPositions = new ArrayList<>();
        FileInputStream fissy = null;
        Scanner scan = null;

        String fileName = "Feyrith.unity";
        try {
            fissy = new FileInputStream(fileName);
            scan = new Scanner(fissy);

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                lines.add(line);
            }

        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found. Oops");
        }
        int listSize = lines.size();
        String currentLine;

        for(int i = 0; i < listSize; i++){
            currentLine = lines.get(i);


            if(currentLine.contains("--- !u!1 ")){ // GameObject
                gameObjectPositions.add(i);
            }

            if(currentLine.contains("--- !u!1001 ")){ // Prefab
                prefabPositions.add(i);
            }

            if(currentLine.contains("--- !u!4")){ // Transforms.
                transformPositions.add(i);
            }

            if(currentLine.contains("--- !u!114")){ // MonoBehaviour.
                monoBehaviorPositions.add(i);
            }
        }
        GameObjectProcessor.findGameObjects(lines, gameObjectPositions);
        TransformProcessor.findTransforms(lines, transformPositions);
        PhysboneProcessor.findPhysbones(lines, monoBehaviorPositions);
    }
}
