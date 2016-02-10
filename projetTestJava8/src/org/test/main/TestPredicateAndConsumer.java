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
    person1.setNom("Zizou");
    person1.setPrenom("Mazizou");
    person1.setAge(20);

    final Person person2 = new Person();
    person2.setNom("Zorro");
    person2.setPrenom("Roronoa");
    person2.setAge(21);

    final Person person3 = new Person();
    person3.setNom("Motta");
    person3.setPrenom("Thiago");
    person3.setAge(22);

    final List<Person> persons = Arrays.asList(person1, person2, person3);

    final List<Person> filteredPersons = filter(persons, p -> p.getAge() > 20);
    System.out.println("Filtered persons 1 : " + filteredPersons);

    final List<Person> filteredPersons2 = filter(persons, p -> p.getNom().startsWith("Z"));
    System.out.println("Filtered persons 2 : " + filteredPersons2);
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
