import java.util.ArrayList;


public class GameObjectProcessor {
    public static ArrayList<String> gameObjectArr;

    public static void findGameObjects(ArrayList<String> lines, ArrayList<Integer> positions){
        int start = positions.get(0);
        int end = positions.get(1);
        gameObjectArr = new ArrayList<>();
        ArrayList<String> tempLine = new ArrayList<>();

        for(int i = 0; i < positions.size(); i++){
            for(int j = start; j< end; j++){
                tempLine.add(lines.get(j));
                if(lines.get(j + 1).startsWith("---")){
                    gameObjectArr.add(tempLine.toString());
                    tempLine.clear();
                    if(i < positions.size() - 1){
                        start = positions.get(i);
                        end = positions.get(i + 1);
                    } else {
                        start = positions.get(i - 1);
                        end = positions.get(i);
                    }
                    break;
                }
            }
        }
        processGameObjects();
    }

    public static void processGameObjects(){
        Main.gameObjects = new ArrayList<>();
        String[] gameObjectsArray;
        long id;
        String name = "";


        for(int i = 0; i < gameObjectArr.size(); i++){
            gameObjectsArray = gameObjectArr.get(i).split(",");
            id = Long.parseLong(gameObjectsArray[0].substring(gameObjectsArray[0].indexOf('&') + 1));
            ArrayList<Long> components = new ArrayList<>();
            for(int j = 0; j < gameObjectsArray.length; j++){
                if(gameObjectsArray[j].contains("  - component:")){
                    components.add(processComponent(gameObjectsArray[j]));
                }

                if(gameObjectsArray[j].contains("m_Name")){
                    name = gameObjectsArray[j].substring(gameObjectsArray[j].indexOf(":") + 1).trim();
                }
            }
            Main.gameObjects.add(new GameObject(id, name, components));
        }
        System.out.println();
    }

    public static long processComponent(String s){
        return Long.parseLong(s.substring(s.lastIndexOf(":") + 1, s.indexOf('}')).trim());
    }

}
