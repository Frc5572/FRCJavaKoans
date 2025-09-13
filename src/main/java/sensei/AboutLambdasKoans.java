package sensei;

import engine.Koan;
import engine.text.Localizable;
import koans.AboutLambdas;

import java.util.List;
import java.util.function.IntSupplier;

import static engine.Assertions.*;
import static engine.script.Expression.callKoanMethod;
import static engine.text.Localizable.global;
import static engine.text.Localizable.localClass;
import static sensei.Texts.*;

public class AboutLambdasKoans {
    private static final Localizable<Class<?>> CLASS =
            localClass(AboutLambdas.class);

    public static final List<Koan> koans = List.of(
            new Koan(CLASS, AN_INTRODUCTION_TO_LAMBDAS)
                    .useConsole()
                    .beforeFirstTest(
                            assertKoanMethodIsInvokable("createSquarer", int.class)
                    )
                    .when(
                            callKoanMethod("createSquarer", 0)
                    )
                    .then(
                            assertReturnValueIsLambda(),
                            assertReturnValueImplements(IntSupplier.class)
                    )
                    .when(
                            callKoanMethod("createSquarer", 3).call("getAsInt")
                    )
                    .then(
                            assertReturnValueEquals(9)
                    )
                    .when(
                            callKoanMethod("createSquarer", 4).call("getAsInt")
                    )
                    .then(
                            assertReturnValueEquals(16)
                    )
                    .when(
                            callKoanMethod("createSquarer", 0).call("getAsInt")
                    )
                    .then(
                            assertReturnValueEquals(0)
                    ),
            new Koan(CLASS, USING_LAMBDAS)
                    .useConsole()
                    .beforeFirstTest(
                            assertKoanMethodIsInvokable("usingLambdas")
                    )
                    .when(
                            callKoanMethod("usingLambdas")
                    )
                    .then(
                            assertNextStdOutLineEquals(global("Robot 1 - 2")),
                            assertNextStdOutLineEquals(global("Robot 5 - 30")),
                            assertNextStdOutLineEquals(global("Robot -1 - 0")),
                            assertNoMoreLineInStdOut()
                    ),
            new Koan(CLASS, CREATING_REFERENCES_TO_LAMBDAS)
                    .useConsole()
                    .beforeFirstTest(
                            assertKoanMethodIsInvokable("lambdaReference")
                    )
                    .when(
                            callKoanMethod("lambdaReference")
                    )
                    .then(
                            assertReturnValueIsLambda(),
                            assertReturnValueImplements(IntSupplier.class)
                    )
                    .when(
                            callKoanMethod("lambdaReference").call("getAsInt")
                    )
                    .then(
                            assertReturnValueEquals(12)
                    )
    );
}
