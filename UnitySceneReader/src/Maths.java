import java.text.DecimalFormat;

public class Maths {
    public static double scaleValue = 52.4798;

    private static final DecimalFormat DIST_FMT =
            new DecimalFormat("#,##0.#################");

    public static double calculate3DDistance(float[] boneOne, float[] boneTwo){
        double xValue = Math.pow((boneTwo[0] - boneOne[0]), 2);
        double yValue = Math.pow((boneTwo[1] - boneOne[1]), 2);
        double zValue = Math.pow((boneTwo[2] - boneOne[2]), 2);

        return Math.sqrt(xValue + yValue + zValue);
    }

    static String formatDoubleToString(double value) {
        if (!Double.isFinite(value)) return "—";
        return DIST_FMT.format(value);
    }
}
