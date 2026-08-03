package koans;

// These are the WPILib units the koans below need. In your own robot code you would add such
// imports as you go; here they are given to you so you can focus on the measurements themselves.
import static edu.wpi.first.units.Units.Centimeters;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Feet;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Millimeters;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.Seconds;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Time;

public class AboutUnits {
    /**
     * # Measuring a distance
     *
     * Write a method named 'armLength' which takes no parameter and returns the length of a robot
     * arm: 32 inches.
     *
     * ---------   TIPS  --------------
     *
     * A number on its own does not say what it measures. Is 32 inches? Centimeters? Rotations?
     * The whole point of the WPILib units library is that a measurement carries its unit with it,
     * so nobody has to guess and nobody can mix them up.
     *
     * A distance has the type 'Distance'. You create one by asking a unit for it:
     *
     *     Distance wheelRadius = Inches.of(2.0);
     *     Distance fieldLength = Meters.of(16.54);
     *
     * So a method returning a distance looks like:
     *
     *     public static Distance wheelRadius() {
     *         return Inches.of(2.0);
     *     }
     *
     * -------------------------------
     *
     * Expected result:
     *
     * armLength() should return Inches.of(32.0)
     *
     */


    /**
     * # Getting a plain number back out
     *
     * Write a method named 'toCentimeters' which has a parameter for a number of inches, and
     * returns the conversion in centimeters. This time, do not do the multiplication yourself:
     * make a measurement, and ask it for its value in centimeters.
     *
     * ---------   TIPS  --------------
     *
     * You already wrote this conversion by hand in 'AboutDecimalNumbers', with a 2.54 you had to
     * look up. A measurement knows every length unit already, so you never look up a factor again.
     *
     * Once you have a measurement, the 'in' method gives you a plain 'double' in whatever unit you
     * ask for:
     *
     *     double radiusInMillimeters = Inches.of(2.0).in(Millimeters);
     *
     * -------------------------------
     *
     * Expected result:
     *
     * toCentimeters(2.0) should return 5.08
     *
     */


    /**
     * # Accepting any unit the caller likes
     *
     * Write a method named 'toDegrees' which has a parameter of type 'Angle', and returns that
     * angle in degrees as a 'double'.
     *
     * ---------   TIPS  --------------
     *
     * An angle has the type 'Angle', and is created just like a distance:
     *
     *     Angle halfTurn = Rotations.of(0.5);
     *     Angle rightAngle = Degrees.of(90.0);
     *
     * Notice what writing 'Angle' in your parameter buys you: whoever calls your method can hand
     * you rotations, radians or degrees, and your method works without knowing which they chose.
     * If they try to hand you a 'Distance' instead, the code will not even compile.
     *
     * This is the most common thing you will do with units on a real robot: keep measurements
     * typed while you pass them around, and only turn them into a plain number at the very last
     * moment, when a motor or a calculation demands one.
     *
     * -------------------------------
     *
     * Expected result:
     *
     * toDegrees(Rotations.of(0.25)) should return 90.0
     * toDegrees(Radians.of(Math.PI)) should return 180.0
     *
     */


    /**
     * # Labelling a sensor reading
     *
     * Write a method named 'motorPosition' which has a parameter for a number of rotations
     * reported by a motor, and returns it as an 'Angle'.
     *
     * ---------   TIPS  --------------
     *
     * A motor controller only ever gives you a bare 'double'. Its unit lives in the documentation,
     * or in somebody's memory. The first thing robot code does with such a reading is label it,
     * and from then on the unit travels with the value:
     *
     *     Angle armPosition = Rotations.of(motor.getPosition());
     *
     * -------------------------------
     *
     * Expected result:
     *
     * motorPosition(0.25) should return Rotations.of(0.25)
     *
     */


    /**
     * # Adding two measurements
     *
     * Write a method named 'totalExtension' which has two parameters of type 'Distance', for the
     * extension of the two stages of an elevator, and returns their total.
     *
     * ---------   TIPS  --------------
     *
     * Measurements have methods for arithmetic instead of the '+' and '-' operators:
     *
     *     Distance remaining = maxHeight.minus(currentHeight);
     *     Distance total = firstStage.plus(secondStage);
     *
     * Here is the good part: the two measurements do not have to use the same unit. One stage can
     * be measured in inches and the other in meters, and the sum is still correct. Adding two
     * plain 'double' would have silently given you nonsense.
     *
     * -------------------------------
     *
     * Expected result:
     *
     * totalExtension(Inches.of(6.0), Inches.of(4.0)) should return Inches.of(10.0)
     * totalExtension(Feet.of(1.0), Centimeters.of(20.0)) should return Centimeters.of(50.48)
     *
     */


    /**
     * # Comparing two measurements
     *
     * Write a method named 'isExtendedPast' which has two parameters of type 'Distance', the
     * current extension of an elevator and a limit, and returns whether the current extension is
     * greater than the limit.
     *
     * ---------   TIPS  --------------
     *
     * Measurements cannot be compared with '>' and '<' either. They have methods instead:
     *
     *     boolean tooHigh = currentHeight.gt(maxHeight);   // gt: greater than
     *     boolean tooLow = currentHeight.lt(minHeight);    // lt: less than
     *
     * Like the arithmetic methods, these convert for you, so comparing inches to meters works.
     *
     * -------------------------------
     *
     * Expected result:
     *
     * isExtendedPast(Inches.of(30.0), Inches.of(24.0)) should return true
     * isExtendedPast(Inches.of(10.0), Inches.of(24.0)) should return false
     * isExtendedPast(Feet.of(3.0), Meters.of(0.5)) should return true
     *
     */


    /**
     * # Deciding we are close enough
     *
     * Write a method named 'isAtTarget' with three parameters of type 'Distance': where an
     * elevator currently is, where it was told to go, and how far off it is allowed to be. Return
     * whether the elevator is within that tolerance of its target.
     *
     * ---------   TIPS  --------------
     *
     * A mechanism never lands exactly on its target, so robot code always asks "am I close
     * enough?" rather than "am I there?". Measurements have a method for exactly that question:
     *
     *     boolean ready = shooterAngle.isNear(targetAngle, Degrees.of(1.0));
     *
     * -------------------------------
     *
     * Expected result:
     *
     * isAtTarget(Inches.of(24.2), Inches.of(24.0), Inches.of(0.5)) should return true
     * isAtTarget(Inches.of(20.0), Inches.of(24.0), Inches.of(0.5)) should return false
     * isAtTarget(Meters.of(0.61), Feet.of(2.0), Millimeters.of(5.0)) should return true
     *
     */


    /**
     * # Scaling a measurement
     *
     * Write a method named 'travelForRotations' which has a parameter of type 'Distance' for how
     * far a wheel travels in one rotation, and a 'double' parameter for a number of rotations, and
     * returns the distance traveled.
     *
     * ---------   TIPS  --------------
     *
     * A measurement can be multiplied or divided by a plain number, and stays the same kind of
     * measurement:
     *
     *     Distance halfway = fieldLength.div(2.0);
     *     Distance threeTurns = perRotation.times(3.0);
     *
     * -------------------------------
     *
     * Expected result:
     *
     * travelForRotations(Inches.of(12.5), 3.0) should return Inches.of(37.5)
     *
     */


    /**
     * # Dividing one measurement by another
     *
     * Write a method named 'averageSpeed' which has a parameter of type 'Distance' and a parameter
     * of type 'Time', and returns the average speed as a 'LinearVelocity'.
     *
     * ---------   TIPS  --------------
     *
     * Dividing a measurement by a plain number gave you the same kind of measurement back. But
     * dividing one measurement by another gives you a different kind, exactly like it does on
     * paper: a distance divided by a time is a speed.
     *
     *     Time elapsed = Seconds.of(0.02);
     *     LinearVelocity speed = Meters.of(0.1).div(elapsed);
     *
     * A speed measured in a straight line has the type 'LinearVelocity'. A rotating one, like a
     * shooter wheel, has the type 'AngularVelocity' — you will meet it when you program a real
     * mechanism.
     *
     * -------------------------------
     *
     * Expected result:
     *
     * averageSpeed(Meters.of(9.0), Seconds.of(2.0)) should return MetersPerSecond.of(4.5)
     *
     */


}
