package sensei;

import java.util.List;

import engine.Koan;

/**
 * All the widom of the master, materialized in the series of all available koans.
 *
 * The series are ordered: each one assumes the student has completed the previous ones.
 */
public final class Wisdom {
    public static final List<List<Koan>> koans = List.of(
        AboutConsoleAndVariablesKoans.koans,
        AboutMethodsKoans.koans,
        AboutConditionsKoans.koans,
        AboutMoreMethodsKoans.koans,
        AboutDecimalNumbersKoans.koans,
        AboutLoopsKoans.koans,
        AboutClassesKoans.koans,
        AboutObjectsKoans.koans,
        AboutArraysKoans.koans,
        AboutInterfacesKoans.koans,
        AboutLambdasKoans.koans,
        AboutNot7GameKoans.koans
    );
}
