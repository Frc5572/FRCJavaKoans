package sensei;

import static edu.wpi.first.units.Units.Centimeters;
import static edu.wpi.first.units.Units.Feet;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Millimeters;
import static edu.wpi.first.units.Units.Radians;
import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.Seconds;
import static engine.Assertions.assertKoanMethodIsInvokable;
import static engine.Assertions.assertReturnValueEquals;
import static engine.script.Expression.callKoanMethod;
import static engine.text.Localizable.localClass;
import static sensei.Texts.*;

import java.util.List;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.Time;
import engine.Koan;
import engine.text.Localizable;
import koans.AboutUnits;

public class AboutUnitsKoans {
    private static final Localizable<Class<?>> CLASS =
        localClass(AboutUnits.class);

    public static final List<Koan> koans = List.of(
        new Koan(CLASS, MEASURING_A_DISTANCE)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("armLength")
            )
            .when(callKoanMethod("armLength"))
            .then(
                assertReturnValueEquals(Inches.of(32.0))
            ),
        new Koan(CLASS, GETTING_A_PLAIN_NUMBER_BACK_OUT)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("toCentimeters", double.class)
            )
            .when(callKoanMethod("toCentimeters", 2.0))
            .then(
                assertReturnValueEquals(5.08)
            )
            .when(callKoanMethod("toCentimeters", 0.0))
            .then(
                assertReturnValueEquals(0.0)
            )
            .when(callKoanMethod("toCentimeters", 10.0))
            .then(
                assertReturnValueEquals(25.4)
            ),
        new Koan(CLASS, ACCEPTING_ANY_UNIT_THE_CALLER_LIKES)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("toDegrees", Angle.class)
            )
            // The same angle expressed 3 different ways: a student who converted by hand instead
            // of using in() would only get one of the 3 right.
            .when(callKoanMethod("toDegrees", Rotations.of(0.25)))
            .then(
                assertReturnValueEquals(90.0)
            )
            .when(callKoanMethod("toDegrees", Radians.of(Math.PI)))
            .then(
                assertReturnValueEquals(180.0)
            )
            .when(callKoanMethod("toDegrees", Rotations.of(0.0)))
            .then(
                assertReturnValueEquals(0.0)
            ),
        new Koan(CLASS, LABELLING_A_SENSOR_READING)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("motorPosition", double.class)
            )
            .when(callKoanMethod("motorPosition", 0.25))
            .then(
                assertReturnValueEquals(Rotations.of(0.25))
            )
            .when(callKoanMethod("motorPosition", -3.5))
            .then(
                assertReturnValueEquals(Rotations.of(-3.5))
            ),
        new Koan(CLASS, ADDING_TWO_MEASUREMENTS)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("totalExtension", Distance.class, Distance.class)
            )
            .when(callKoanMethod("totalExtension", Inches.of(6.0), Inches.of(4.0)))
            .then(
                assertReturnValueEquals(Inches.of(10.0))
            )
            // Mixed units: plus() converts, so this only works if the student let it.
            .when(callKoanMethod("totalExtension", Feet.of(1.0), Centimeters.of(20.0)))
            .then(
                assertReturnValueEquals(Centimeters.of(50.48))
            ),
        new Koan(CLASS, COMPARING_TWO_MEASUREMENTS)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("isExtendedPast", Distance.class, Distance.class)
            )
            .when(callKoanMethod("isExtendedPast", Inches.of(30.0), Inches.of(24.0)))
            .then(
                assertReturnValueEquals(true)
            )
            .when(callKoanMethod("isExtendedPast", Inches.of(10.0), Inches.of(24.0)))
            .then(
                assertReturnValueEquals(false)
            )
            // Equal distances are not past the limit, so gte() would fail here.
            .when(callKoanMethod("isExtendedPast", Inches.of(24.0), Inches.of(24.0)))
            .then(
                assertReturnValueEquals(false)
            )
            .when(callKoanMethod("isExtendedPast", Feet.of(3.0), Meters.of(0.5)))
            .then(
                assertReturnValueEquals(true)
            ),
        new Koan(CLASS, DECIDING_WE_ARE_CLOSE_ENOUGH)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("isAtTarget", Distance.class, Distance.class, Distance.class)
            )
            .when(callKoanMethod("isAtTarget", Inches.of(24.2), Inches.of(24.0), Inches.of(0.5)))
            .then(
                assertReturnValueEquals(true)
            )
            // Below the target by more than the tolerance: catches a student comparing without
            // taking the absolute value of the error.
            .when(callKoanMethod("isAtTarget", Inches.of(20.0), Inches.of(24.0), Inches.of(0.5)))
            .then(
                assertReturnValueEquals(false)
            )
            .when(callKoanMethod("isAtTarget", Inches.of(30.0), Inches.of(24.0), Inches.of(0.5)))
            .then(
                assertReturnValueEquals(false)
            )
            .when(callKoanMethod("isAtTarget", Meters.of(0.61), Feet.of(2.0), Millimeters.of(5.0)))
            .then(
                assertReturnValueEquals(true)
            ),
        new Koan(CLASS, SCALING_A_MEASUREMENT)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("travelForRotations", Distance.class, double.class)
            )
            .when(callKoanMethod("travelForRotations", Inches.of(12.5), 3.0))
            .then(
                assertReturnValueEquals(Inches.of(37.5))
            )
            .when(callKoanMethod("travelForRotations", Inches.of(12.5), 0.0))
            .then(
                assertReturnValueEquals(Inches.of(0.0))
            ),
        new Koan(CLASS, DIVIDING_ONE_MEASUREMENT_BY_ANOTHER)
            .beforeFirstTest(
                assertKoanMethodIsInvokable("averageSpeed", Distance.class, Time.class)
            )
            .when(callKoanMethod("averageSpeed", Meters.of(9.0), Seconds.of(2.0)))
            .then(
                assertReturnValueEquals(MetersPerSecond.of(4.5))
            )
            .when(callKoanMethod("averageSpeed", Feet.of(16.0), Seconds.of(4.0)))
            .then(
                assertReturnValueEquals(MetersPerSecond.of(1.2192))
            )
    );
}
