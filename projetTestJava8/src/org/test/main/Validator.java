package org.test.main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Monad that allows to compose and chain operation in order to validate many field of given object
 * {@code <T>}.
 *
 * @author Mazlum
 */
public class Validator<T> {

  // Fields.

  private final T object;
  private final List<Throwable> exceptions = new ArrayList<>();

  /**
   * Constructor with given object.
   *
   * @param object current object
   */
  public Validator(T object) {
    this.object = object;
  }

  /**
   * Static factory method that allows to create new {@link Validator} instance, with given object.
   *
   * @param object current object
   * @return {@link Validator} with object
   */
  public static <T> Validator<T> of(T object) {
    return new Validator<>(object);
  }

  /**
   * Gets exceptions, if it exists validation errors.
   *
   * @return {@link Throwable} exception if it exists validation errors
   */
  public List<Throwable> get() {
    return exceptions;
  }

  /**
   * Allows to validate a field, with {@link Predicate}.
   *
   * @param predicate current predicate
   * @param message message to add in exception list, if current {@link Predicate} returns false
   * @return current {@link Validator}
   */
  public Validator<T> validate(Predicate<? super T> predicate, final String message) {
    if (!predicate.test(object)) {
      exceptions.add(new IllegalStateException(message));
    }

    return this;
  }

  /**
   * Allows to validate a projection that contains current field to validate. Projection is chained
   * with a {@link Predicate} that matches with function return field.
   *
   * @param projection current projection
   * @param predicate current predicate
   * @param message current message to add in exception list, if current {@link Predicate} returns
   *        false
   * @return current {@link Validator}
   */
  public <U> Validator<T> validate(Function<? super T, ? extends U> projection,
      Predicate<? super U> predicate, final String message) {

    return validate(projection.andThen(predicate::test)::apply, message);
  }

  /**
   * Allows to validate a projection that contains current field to validate. Projection is chained
   * with a {@link Predicate} that matches with function return field.
   *
   * @param projection current projection
   * @param predicate current predicate
   * @param message current message to add in exception list, if current {@link Predicate} returns
   *        false
   * @return current {@link Validator}
   */
  public <U> Validator<T> validate2(Function<? super T, ? extends U> projection,
      Predicate<? super U> predicate, final String message) {

    if (!predicate.test(projection.apply(object))) {
      exceptions.add(new IllegalStateException(message));
    }

    return this;
  }

  /**
   * Mains for tests.
   *
   * @param args args
   */
  public static void main(String[] args) {

    final Person personne = new Person();
    personne.setLastName("Zizou");
    personne.setAge(15);

    System.out.println("Validator test");

    final List<Throwable> results = Validator.of(personne)
        .validate2(Person::getLastName, nom -> nom != null, "Name of user must not be empty")
        .validate2(Person::getAge, age -> age > 10, "Age must be greather than 10").get();

    final String test = "";
  }
}
