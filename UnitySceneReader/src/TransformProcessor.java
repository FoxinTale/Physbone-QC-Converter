import java.util.ArrayList;

public class TransformProcessor {
    public static ArrayList<String> transformArr;

    public static void findTransforms(ArrayList<String> lines, ArrayList<Integer> positions){
        int start = positions.get(0);
        int end = positions.get(1);
        transformArr = new ArrayList<>();
        ArrayList<String> tempLine = new ArrayList<>();

        for(int i = 0; i < positions.size(); i++){
            for(int j = start; j< end; j++){
                tempLine.add(lines.get(j));
                if(lines.get(j + 1).startsWith("---")){
                    transformArr.add(tempLine.toString());
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
        processTransforms();
    }


    public static void processTransforms(){
        long id = 0;
        long gameObjectId = 0;
        float posX = 0;
        float posY = 0;
        float posZ = 0;
        float scaleX = 0;
        float scaleY = 0;
        float scaleZ = 0;
        long fatherID = 0;
        String[] transformArray;
        Main.transforms = new ArrayList<>();

        for(int i = 0; i < transformArr.size(); i++){
            transformArray = transformArr.get(i).split(",");
            try{
                id = Long.parseLong(transformArray[0].substring(transformArray[0].indexOf('&') + 1));
            } catch (NumberFormatException nfe){
                // If we encounter this, it actually means that we have a prefab. As it has a "stripped" at the end of the ID.
                // For now, we won't bother. Let's get the bulk of this thing working as we'll still need to match a prefab.
            }

            ArrayList<Long> children = new ArrayList<>();

            for(int j = 0; j < transformArray.length; j++){
                if(transformArray[j].contains("m_GameObject")){
                    gameObjectId = arrayStringToLong(transformArray[j]);
                }

                if(transformArray[j].contains("m_LocalRotation")){

                }

                if(transformArray[j].contains("m_LocalPosition")){
                    posX = Float.parseFloat(transformArray[j].substring(transformArray[j].lastIndexOf(':') + 1).trim());
                    posY = Float.parseFloat(transformArray[j + 1].substring(transformArray[j + 1].indexOf(':') + 1).trim());
                    posZ = Float.parseFloat(transformArray[j + 2].substring(transformArray[j + 2].indexOf(':') + 1, transformArray[j + 2].indexOf('}')).trim());
                }

                if(transformArray[j].contains("m_LocalScale")){
                    scaleX = Float.parseFloat(transformArray[j].substring(transformArray[j].lastIndexOf(':') + 1).trim());
                    scaleY = Float.parseFloat(transformArray[j + 1].substring(transformArray[j + 1].indexOf(':') + 1).trim());
                    scaleZ = Float.parseFloat(transformArray[j + 2].substring(transformArray[j + 2].indexOf(':') + 1, transformArray[j + 2].indexOf('}')).trim());
                }

                if(transformArray[j].trim().startsWith("- {fileID:")){
                    children.add(arrayStringToLong(transformArray[j]));
                }

                if(transformArray[j].contains("m_Father")){
                    fatherID = arrayStringToLong(transformArray[j]);
                }
            }
            Main.transforms.add(new Transform(id, gameObjectId, children, fatherID, posX, posY, posZ, scaleX, scaleY, scaleZ));
        }
    }

    public static long arrayStringToLong(String s){
        return Long.parseLong(s.substring(s.lastIndexOf(':') + 1, s.indexOf('}')).trim());
    }
}
