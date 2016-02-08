package org.test.main;

import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;



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
    final Person person = new Person();
    person.setNom("Zizou");
    person.setPrenom("Raul");
    person.setAge(20);

    final Adress adress = new Adress();
    adress.setAdress("rue de Paris");
    adress.setZipCode("75015");

    // Optional test.
    final Optional<Person> optinalPersonne = Optional.of(person);
    final Optional<Adress> optionalAdresse = Optional.of(adress);
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
    System.out.println(
        "Binary function with reference method : " + binaryFunctionWithReferenceMethod.apply(1, 1));

    // Binary operation.
    final BinaryOperator<String> bynaryOperation = Utils::valueOf;
    System.out.println("Binary Operation : " + bynaryOperation.apply("toto", " tata"));

    final BiFunction<String, String, String> bynaryOperation2 = (x, y) -> x + y;

    // Test with new functional interface.
    final LoggerTestFunction logger = (message, severity) -> System.out
        .println("Test new functinal interfaces that represent a Logger => Message : " + message
            + " / Severity : " + severity);

    logger.filter(m -> m.startsWith("P")).log("PSG", "INFO");

    logger.logWithFilter(m -> m.startsWith("P"), "PSG", "INFO");

    // Test chain function.

    // Function.
    final Function<Boolean, Boolean> function = s -> Boolean.valueOf(s);

    final Function<Boolean, Stream<Boolean>> function2 = b -> Stream.of(b);

    // Predicate.
    final Predicate<String> predicate = b -> b.equals("true");

    final Consumer<Boolean> consumer =
        c -> System.out.println("Test chain predicate + consumer. Result : " + c);

    // Chain function and predicate.
    function.compose(predicate::test).andThen(function2).apply("true");

    final Predicate<Person> predicate1 = p -> p.getNom().equals("Zizou");
    final Predicate<Person> predicate2 = p -> p.getAge().equals("20");

    final Predicate<Person> finalPredicate = predicate1.and(predicate2);

    final boolean predicateChainResult = TestChainPredicate.of(person)
        .add(TestChainPredicate.Type.AND, p -> p.getNom().equals("Zizouu"))
        .add(TestChainPredicate.Type.OR, p -> p.getAge() == 20).build();

    System.out.println("Predicate chain result : " + predicateChainResult);

    // Tests compose function with consumer. Behavior parameterization case 1.
    dispayPersonInfo(person,
        p -> new StringJoiner(", ").add(p.getNom()).add(p.getPrenom()).toString(),
        System.out::println);

    // Tests compose function with consumer. Behavior parameterization case 2.
    dispayPersonInfo(person,
        p -> new StringJoiner(", ").add(p.getPrenom()).add(p.getAge().toString()).toString(),
        System.out::println);
  }

  private static void dispayPersonInfo(final Person person,
      final Function<Person, String> personToString, final Consumer<String> consumerString) {
    consumerString.accept(new StringJoiner(" : ").add("Compose function with consumer")
        .add(personToString.apply(person)).toString());
  }
}
