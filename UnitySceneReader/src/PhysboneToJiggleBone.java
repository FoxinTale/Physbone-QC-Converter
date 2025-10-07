public class PhysboneToJiggleBone {

    public static float simpleStiffness(float stiffness){
        return stiffness * 1000;
    }

    public static float simpleDamping(float spring, float immobile){
        float damping = (10 * (1 - spring)) + (5 * immobile);
        return clamp(damping, 0, 10);
    }

    public static float advancedStiffness(float pull, float stiffness){
        return (float) ((1000 * pull) * (1.0 + (0.4 * stiffness)));
    }

    public static void advancedDamping(){

    }

    // Java 8 doesn't have a native clamp function. I think it was introduced in later versions... 19-21 somewhere around there.
    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

    /*
A massive disclaimer, all of this conversion was from ChatGPT. While I did write the Java code throughout, the actual process was from GPT.

Physbone parameters:
    Simplified: Pull, Spring, Gravity, Gravity Falloff, Immobile Type, Immobile, Limit Type.
    Advanced:


Jigglebone parameters:
    length
	tip_mass
	pitch_constraint
	pitch_stiffness
	pitch_damping
	yaw_stiffness
	yaw_damping
	along_stiffness
	yaw_constraint
	along_damping
	angle_constraint



 Simplified Physbone to Jigglebone:
    Stiffness on pitch and yaw is just the pull * 1000.
    Dampness on  pitch and yaw is: 10 * (1 - spring) + (5 * immobile). this number must be between 1 and 10, so it gets clamped.

  Advanced Physbone to Jigglebone:
    Stiffness on pitch and yaw is: 1000 * pull * (1.0 + (0.4 * stiffness))
    Dampness on  pitch and yaw is: 10 * (1 - momentum) + (5 * immobile).

So, with the above that is the following taken care of:
    yaw_stiffness
    pitch_stiffness

    yaw_damping
    pitch_damping



Under simplified we display: Pull, Spring, Gravity, Gravity Falloff, Immobile Type, and Immobile.

Pull, Spring, Gravity,

Limit Type would have angle, if this is simply *just* angle, that actually does not change and goes straight to our angle_constraint value.

 */
}
