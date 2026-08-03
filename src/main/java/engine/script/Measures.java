package engine.script;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.units.Measure;
import edu.wpi.first.units.Unit;
import edu.wpi.first.units.Units;

/**
 * Support for using WPILib measures (Meters.of(2.0), Degrees.of(90.0), ...) as literals in koan
 * expressions.
 *
 * Measures need special treatment for a single reason: their toString() is not valid Java. A
 * Distance of 2 inches prints as "2.000e+00 in", but the koans have to show the student source
 * code they could type themselves, so we render it as "Inches.of(2.0)" instead.
 *
 * Rather than hardcode a symbol-to-name table, we recover the names from Units itself: every unit
 * the student can name is a public static field there, so the field holding a measure's unit gives
 * us exactly the identifier they would write.
 */
public final class Measures {
    private static final Map<Unit, String> UNIT_NAMES = unitNames();

    private Measures() {
    }

    private static Map<Unit, String> unitNames() {
        final var names = new HashMap<Unit, String>();
        for (final var field : Units.class.getFields()) {
            if (Modifier.isStatic(field.getModifiers()) && Unit.class.isAssignableFrom(field.getType())) {
                try {
                    // Units declares a few aliases (Volt and Volts, ...). putIfAbsent keeps the
                    // first one, which is the plural form the koans and WPILib docs both use.
                    names.putIfAbsent((Unit) field.get(null), field.getName());
                } catch (final IllegalAccessException iae) {
                    throw new AssertionError("Units." + field.getName() + " is public but not readable", iae);
                }
            }
        }
        return names;
    }

    public static boolean isMeasure(final Object value) {
        return value instanceof Measure;
    }

    /**
     * Names the given class the way the student writes it in a method signature, which for a
     * measure is its interface: a Distance is really an ImmutableDistance, but no koan ever asks
     * for one by that name. Returns null for anything that is not a measure.
     */
    public static String typeName(final Class<?> clasz) {
        if (!Measure.class.isAssignableFrom(clasz)) {
            return null;
        }
        if (clasz.isInterface()) {
            // Already the name the student writes: Distance, Angle, Measure itself, ...
            return clasz.getSimpleName();
        }
        for (final var iface : clasz.getInterfaces()) {
            if (Measure.class.isAssignableFrom(iface)) {
                return iface.getSimpleName();
            }
        }
        return clasz.getSimpleName();
    }

    /**
     * Formats the Java source code the student would write to obtain the given measure.
     */
    public static String formatSourceCode(final Measure<?> measure) {
        final var unitName = UNIT_NAMES.get(measure.unit());
        if (unitName == null) {
            // A unit combined on the fly (Meters.per(Second).per(Second) and friends) has no field
            // in Units, so there is no identifier to show. Its toString() is at least readable.
            return measure.toString();
        }
        return String.format("%s.of(%s)", unitName, measure.magnitude());
    }
}
