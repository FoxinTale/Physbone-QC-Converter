import java.util.ArrayList;
/*
    Yeeesh.. physbones are annoying to work with.
 */
public class PhysboneProcessor {
    public static long physboneFileID = 1661641543; //If the physbone ID changes in the future. We may need to make this a long array if there's more in the future.

    public static ArrayList<String> physboneArr;
    public static ArrayList<PhysBone> physbones;

    public static ArrayList<Curve> curves;
//These probably don't need to be public, nor static... But things work as is and I don't want to touch it if it works.
    public static String curveName;
    public static ArrayList<CurvePoint> points;
    public static float time = 0;
    public static float value = 0;
    public static float inSlope = 0;
    public static float outSlope = 0;
    public static int tangentValue = 0;
    public static int weightedMode = 0;
    public static float inWeight = 0;
    public static float outWeight = 0;
    public static int preInfinity;
    public static int postInfinity;
    public static int rotationOrder;

    public static Curve pullCurve;
    public static Curve springCurve;
    public static Curve stiffnessCurve;
    public static Curve gravityCurve;
    public static Curve gravityFalloffCurve;
    public static Curve immobileCurve;
    public static Curve maxAngleXCurve;
    public static Curve maxAngleZCurve;
    public static Curve rotLimitXCurve;
    public static Curve rotLimitYCurve;
    public static Curve rotLimitZCurve;


    public static void findPhysbones(ArrayList<String> lines, ArrayList<Integer> positions){
        int start = positions.get(0);
        int end = positions.get(1);
        physboneArr = new ArrayList<>();
        ArrayList<String> tempLine = new ArrayList<>();

        for(int i = 0; i < positions.size(); i++){
            for(int j = start; j< end; j++){
                tempLine.add(lines.get(j));
                if(lines.get(j + 1).startsWith("---")){
                    physboneArr.add(tempLine.toString());
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
        sortOutNonPhysbones();
    }

    /*
        We initially are pulling out anything that starts with 114, which gets any sort of attached script.
        Avatar Descriptor and VRCFury scripts are the big ones we need to remove. We only want the physbones here, after all.
     */
    public static void sortOutNonPhysbones(){

        for(int i = 0; i < physboneArr.size(); i++){
            String[] physboneArray = physboneArr.get(i).split(",");
            long foundScriptID = 0;

            for(int j = 0; j < physboneArray.length; j++){
                if(physboneArray[j].contains("m_Script:")){
                    foundScriptID = Long.parseLong(physboneArray[j].substring(physboneArray[j].lastIndexOf(':') + 1).trim());
                }
            }
            //We compare file IDs and if they're not equal, we remove it. We only want physbones, after all. Not any other script.
            if(foundScriptID != physboneFileID){
                physboneArr.remove(i);
            }
        }
        processPhysbones();
    }

    public static void processPhysbones(){
        long id;
        long gameobjectID = 0;
        long rootTransformID = 0;
        float pull = 0;

        float spring = 0;
        float stiffness = 0;
        float gravity = 0;
        float gravityFalloff = 0;
        float immobile = 0;
        int limitType = 0;
        float maxAngleX = 0;
        float maxAngleZ = 0;
        float rotLimitX = 0;
        float rotLimitY = 0;
        float rotLimitZ = 0;

        Main.physbones = new ArrayList<>();
        curves = new ArrayList<>();
        String[] physboneArray;

        for(int i = 0; i < physboneArr.size(); i++){

            physboneArray = physboneArr.get(i).split(",");
            id = Long.parseLong(physboneArray[0].substring(physboneArray[0].indexOf('&') + 1));

            for(int j = 0;  j < physboneArray.length; j++){
        /*
            Do not attempt to try and make more functions than this. I tried doing a "check for string" function that returned the value I needed,
            but since it runs every line, and returned a 0 otherwise, it just didn't work out. Unless there's a better way to do it leave it alone.
         */
                buildCurves(physboneArray[j]);

                if(physboneArray[j].contains("m_GameObject")){
                    gameobjectID = arrayStringToLong(physboneArray[j]);
                }

                if(physboneArray[j].contains("rootTransform")){
                    rootTransformID = arrayStringToLong(physboneArray[j]);
                }

                if(physboneArray[j].contains("pull")){
                    if(!physboneArray[j].contains("Curve")){
                        pull = arrayStringToFloat(physboneArray[j]);
                    }
                }

                if(physboneArray[j].contains("spring")){
                    if(!physboneArray[j].contains("Curve")){
                        spring = arrayStringToFloat(physboneArray[j]);
                    }
                }

                if(physboneArray[j].contains("stiffness")){
                    if(!physboneArray[j].contains("Curve")){
                        stiffness = arrayStringToFloat(physboneArray[j]);
                    }
                }

                if(physboneArray[j].contains("gravity")){
                    if(!physboneArray[j].contains("Curve")){
                        gravity = arrayStringToFloat(physboneArray[j]);
                    }
                }

                if(physboneArray[j].contains("gravityFalloff")){
                    if(!physboneArray[j].contains("Curve")){
                        gravityFalloff = arrayStringToFloat(physboneArray[j]);
                    }
                }

                if(physboneArray[j].contains("immobile")){
                    if(!physboneArray[j].contains("Curve")){
                        immobile = arrayStringToFloat(physboneArray[j]);
                    }
                }

                if(physboneArray[j].contains("limitType")){
                    limitType = Integer.parseInt(physboneArray[j].substring(physboneArray[j].indexOf(':') + 1).trim());
                }

                if(physboneArray[j].contains("maxAngleX")){
                    if(!physboneArray[j].contains("Curve")){
                        maxAngleX = arrayStringToFloat(physboneArray[j]);
                    }
                }

                if(physboneArray[j].contains("maxAngleZ")){
                    if(!physboneArray[j].contains("Curve")){
                        maxAngleZ = arrayStringToFloat(physboneArray[j]);
                    }
                }

                if(physboneArray[j].contains("limitRotation")){
                    if(!physboneArray[j].contains("Curve")){
                        rotLimitX = Float.parseFloat(physboneArray[j].substring(physboneArray[j].lastIndexOf(':') + 1).trim());
                        rotLimitY = Float.parseFloat(physboneArray[j + 1].substring(physboneArray[j + 1].indexOf(':') + 1).trim());
                        rotLimitZ = Float.parseFloat(physboneArray[j + 2].substring(physboneArray[j + 2].indexOf(':') + 1, physboneArray[j + 2].indexOf('}')).trim());
                    }
                }
            }

            // We have the curves completed, now its time to match them.
            setCurves();


            Main.physbones.add(new PhysBone(id, gameobjectID, rootTransformID, pull, pullCurve, spring, springCurve, stiffness, stiffnessCurve, gravity,
                    gravityCurve, gravityFalloff, gravityFalloffCurve, immobile, immobileCurve, limitType, maxAngleX, maxAngleXCurve, maxAngleZ, maxAngleZCurve,
                    rotLimitX, rotLimitXCurve, rotLimitY, rotLimitYCurve, rotLimitZ, rotLimitZCurve));
        }
            SceneRebuild.matchGameObjectsAndTransforms();
    }


    public static void buildCurves(String s){
        int serialisedVersion = 0;

        if(s.contains("serializedVersion")){
            serialisedVersion = arrayStringToInt(s);
        }

        if(s.contains("Curve:")){
            if(!s.contains("m_Curve")){
                curveName = stripStringColon(s);
                points = new ArrayList<>();
            }
        }

        if(s.contains("time")){
            time = arrayStringToFloat(s);
        }

        if(s.contains("value")){
            value = arrayStringToFloat(s);
        }

        if(s.contains("inSlope")){
            inSlope = arrayStringToFloat(s);
        }

        if(s.contains("outSlope")){
            outSlope = arrayStringToFloat(s);
        }

        if(s.contains("tangentMode")){
            tangentValue = arrayStringToInt(s);
        }

        if(s.contains("weightedMode")){
            weightedMode = arrayStringToInt(s);
        }

        if(s.contains("inWeight")){
            inWeight = arrayStringToFloat(s);
        }

        if(s.contains("outWeight")){
            outWeight = arrayStringToFloat(s);
        }

        if(serialisedVersion == 3){
            // The end of a curve point and the start of a new one.
            points.add(new CurvePoint(time, value, inSlope, outSlope, tangentValue, weightedMode, inWeight, outWeight));
        }

        if(s.contains("m_PreInfinity:")){
            preInfinity = arrayStringToInt(s);
        }

        if(s.contains("m_PostInfinity")){
            postInfinity = arrayStringToInt(s);
        }

        if(s.contains("m_RotationOrder")){
            rotationOrder = arrayStringToInt(s);

            if(!points.isEmpty()){
                points.remove(0); // We have a duplicate issue.
            }
            curves.add(new Curve(points, curveName, preInfinity, postInfinity, rotationOrder));
        }
    }

    public static void setCurves(){
        String name;
        for(int i = 0; i < curves.size(); i++){
            name = curves.get(i).getCurveName();
            switch(name){
                case "pullCurve":
                    pullCurve = curves.get(i);
                case "springCurve":
                    springCurve = curves.get(i);
                case "stiffnessCurve":
                    stiffnessCurve = curves.get(i);
                case "gravityCurve":
                    gravityCurve = curves.get(i);
                case "gravityFalloffCurve":
                    gravityFalloffCurve = curves.get(i);
                case "immobileCurve":
                    immobileCurve = curves.get(i);
                case "maxAngleXCurve":
                    maxAngleXCurve = curves.get(i);
                case "maxAngleZCurve":
                    maxAngleZCurve = curves.get(i);
                case "limitRotationXCurve":
                    rotLimitXCurve = curves.get(i);
                case "limitRotationYCurve":
                    rotLimitYCurve = curves.get(i);
                case "limitRotationZCurve":
                    rotLimitZCurve = curves.get(i);
            }
        }
    }

    public static String stripStringColon(String s){
        return s.substring(0, s.indexOf(':')).trim();
    }

    public static long arrayStringToLong(String s){
        return Long.parseLong(s.substring(s.lastIndexOf(':') + 1, s.indexOf('}')).trim());
    }

    public static float arrayStringToFloat(String s){
        return Float.parseFloat(s.substring(s.indexOf(':') + 1).trim());
    }

    public static int arrayStringToInt(String s){
        return Integer.parseInt(s.substring(s.indexOf(':') + 1).trim());
    }
}
