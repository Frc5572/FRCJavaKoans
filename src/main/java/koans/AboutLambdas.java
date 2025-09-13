package koans;

import static engine.Helpers.createNumberPrinter;

public class AboutLambdas {
    /**
     * # An introduction to lambdas
     *
     * Lambdas are like mini-methods. They are unnamed methods that can be passed around as data. They are **essential** for command based robot programming, and can be used in ordinary
     * code to create "links" between otherwise separate classes in a clean and organized fashion.
     *
     * Write a method named createSquarer that takes in an int and returns a lambda that always returns the square of this number.
     *
     * ---------   TIPS --------------
     *
     * Lambdas have the following syntax:
     *
     *    parameter -> { expression }
     *    (parameter1, parameter2, ...) -> { expression }
     *
     * Just like methods, they take in parameters and can return something or nothing.
     *
     * Lambdas have specific types - in this example, a lambda that takes nothing and returns an int is equivalent to the IntSupplier type. Any normal method is also valid as a lambda.
     * I recommend exploring the java.util.function package for many of the built in lambda types. You can also look up the FunctionalInterface annotation for information on creating
     * your own lambda types.
     *
     * For example, a lambda that takes no parameters and always returns 0 would have a type of IntSupplier and look like this:
     *
     *    () -> { return 0; }
     *
     * Note that an empty pair of parentheses must be included when a lambda takes no parameters.
     *
     * Also note that lambdas with a single return statement can be written without brackets.
     * For example, the above example could be written like this:
     *
     *    () -> 0
     *
     * You'll notice that IntelliJ provides insights that recommend doing this where possible.
     *
     * -------------------------------
     *
     * Expected result:
     *
     * createSquarer(3) should return an IntSupplier that returns 9
     * createSquarer(4) should return an IntSupplier that returns 16
     * createSquarer(0) should return an IntSupplier that returns 0
     *
     */

    /**
     * # Using lambdas
     *
     * The createNumberPrinter(String name) method is provided. It returns a lambda that takes a number, combines that number with the name parameter
     * passed in when creating the lambda, and prints the result.
     *
     * Create a usingLambdas method that takes no parameters and returns nothing. Inside of it, use the createNumberPrinter method to create a lambda
     * with the name "Robot", store it in a variable, and use it to print the numbers 1, 5, and -1.
     *
     * ---------   TIPS --------------
     *
     * createNumberPrinter returns an IntConsumer. An IntConsumer is a lambda that takes in an int and returns nothing.
     *
     * You can assign it to a variable like this:
     *
     *    IntConsumer numberPrinter = createNumberPrinter("Test");
     *
     * You can call/run the lambda like this:
     *
     *    numberPrinter.accept(2);
     *
     * -------------------------------
     *
     * Expected result:
     *
     * usingLambdas() should create a lambda using createNumberPrinter when the name parameter is "Robot"
     * and call the lambda three times with parameters 1, 5, and -1.
     *
     */

    /**
     * # Creating references to lambdas
     *
     * Create a method named lambdaReference. Inside of it, create a String variable with contents "Hello world!". Return a reference to the
     * string's length function.
     *
     * -------------- TIPS --------------
     *
     * You can get a "reference" to a method inside an object with the :: notation. Example:
     *
     *     DoubleSupplier elevatorPosition = elevator::getPosition;
     *
     * In this example, elevator is an instance of the Elevator class, which contains a getPosition method that takes no parameters and returns
     * a double.
     *
     * Getting a reference effectively converts the method to a lambda, and allows you to pass around the method just like a lambda.
     */
}
