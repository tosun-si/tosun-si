package org.test.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;



/**
 * @author Mazlum
 *
 */
public class TestPredicateAndConsumer {

  /**
   * @param args
   */
  public static void main(String[] args) {

    final Person person1 = new Person();
    person1.setFirstName("Zizou");
    person1.setLastName("Mazizou");
    person1.setAge(20);

    final Person person2 = new Person();
    person2.setFirstName("Zorro");
    person2.setLastName("Roronoa");
    person2.setAge(21);

    final Person person3 = new Person();
    person3.setFirstName("Motta");
    person3.setLastName("Thiago");
    person3.setAge(22);

    final Person person4 = new Person();
    person4.setFirstName("Zlatan");
    person4.setLastName("Ibra");
    person4.setAge(22);

    final List<Person> persons = Arrays.asList(person1, person2, person3, person4);

    final List<Person> filteredPersons = filter(persons, p -> p.getAge() > 20);
    System.out.println("Filtered persons 1 : " + filteredPersons);

    final List<Person> filteredPersons2 = filter(persons, p -> p.getLastName().startsWith("Z"));
    System.out.println("Filtered persons 2 : " + filteredPersons2);

    // Test compose predicate + consumer.
    Action.of(person2)
        .apply(p -> p.getFirstName().equals("Zizou"), TestPredicateAndConsumer::displayFirstName)
        .apply(p -> p.getLastName().equals("Roronoa"), TestPredicateAndConsumer::displayLastName)
        .apply(p -> p.getFirstName().equals("Motta"), p -> displayHardText())
        .apply(p -> p.getFirstName().equals("Zlatan"),
            p -> displayAgeWithOtherTexts(p, "For", "Zlatan"))
        .execute();
  }

  private static void displayFirstName(final Person person) {
    System.out.println("Person first name : " + person.getFirstName());
  }

  private static void displayLastName(final Person person) {
    System.out.println("Person last name : " + person.getLastName());
  }

  private static void displayHardText() {
    System.out.println("Test!!!!!");
  }

  private static void displayAgeWithOtherTexts(final Person person, final String text1,
      final String text2) {
    System.out.println("Person age : " + person.getAge() + " " + text1 + " " + text2);
  }

  private static List<Person> filter(final List<Person> persons, final Predicate<Person> filter) {

    final List<Person> filteredPersons = new ArrayList<>();
    persons.forEach(p -> {
      if (filter.test(p)) {
        filteredPersons.add(p);
      }
    });

    return filteredPersons;
  }
}
