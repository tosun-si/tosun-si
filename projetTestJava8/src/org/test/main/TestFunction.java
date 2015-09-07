package org.test.main;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;


/**
 * @author Mazlum
 *
 */
public class TestFunction {

  /**
   * @param args
   */
  public static void main(String[] args) {

    // Tests function.
    final Function<Integer, Integer> functionSum = x -> x + 1;
    final Function<Integer, Integer> functionProduct = x -> x * 3;

    final Integer functionCompose = functionProduct.compose(functionSum).apply(5);
    System.out.println("Function compose test : " + functionCompose.equals(18));

    // Test optional.
    final Personne personne = new Personne();
    personne.setNom("Zizou");

    final Adresse adresse = new Adresse();
    adresse.setAdress("rue de Paris");
    adresse.setZipCode("75015");

    // Optional test.
    final Optional<Personne> optinalPersonne = Optional.of(personne);
    final Optional<Adresse> optionalAdresse = Optional.of(adresse);
    optinalPersonne.flatMap(a -> optionalAdresse).ifPresent(System.out::println);

    // Unary operation test.
    final UnaryOperator<Integer> unary1 = x -> x + 2;
    final Integer unaryCompose = unary1.andThen(unary1).andThen(unary1).apply(0);
    System.out.println("Unary operation andThen test : " + unaryCompose);

    // Binary function test with lambda.
    final BiFunction<Integer, Integer, String> binaryFunctionWithLambda =
        (x, y) -> String.valueOf(x + y);
    System.out.println("Binary function with lambda : " + binaryFunctionWithLambda.apply(1, 1));

    // Binary function test with reference method.
    final BiFunction<Integer, Integer, String> binaryFunctionWithReferenceMethod = Utils::valueOf;
    System.out.println("Binary function with reference method : "
        + binaryFunctionWithReferenceMethod.apply(1, 1));

    // Binary operation.
    final BinaryOperator<String> bynaryOperation = Utils::valueOf;
    System.out.println("Binary Operation : " + bynaryOperation.apply("toto", " tata"));

    final BiFunction<String, String, String> bynaryOperation2 = (x, y) -> x + y;

    // Test with new functional interface.
    final LoggerTestFunction logger =
        (message, severity) -> System.out
            .println("Test new functinal interfaces that represent a Logger => Message : "
                + message + " / Severity : " + severity);

    // logger.log("PSG", "INFO");

    logger.filter(m -> m.startsWith("P")).log("PSG", "INFO");

    logger.logWithFilter(m -> m.startsWith("P"), "PSG", "INFO");
  }
}
