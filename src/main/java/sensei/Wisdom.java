package sensei;

import java.util.List;

import engine.Koan;
import koans.AboutLambdas;

/**
 * All the widom of the master, materialized in the series of all available koans.
 */
public final class Wisdom {
    public static final List<List<Koan>> koans = List.of(
            AboutLambdasKoans.koans,
        AboutConsoleAndVariablesKoans.koans,
        AboutMethodsKoans.koans,
        AboutConditionsKoans.koans,
        AboutMoreMethodsKoans.koans,
        AboutDecimalNumbersKoans.koans,
        AboutClassesKoans.koans,
        AboutObjectsKoans.koans
    );

    public static final List<List<Koan>> advancedKoans = List.of(
            AboutLoopsKoans.koans,
            AboutArraysKoans.koans,
            AboutInterfacesKoans.koans,
            AboutNot7GameKoans.koans
    );
}
