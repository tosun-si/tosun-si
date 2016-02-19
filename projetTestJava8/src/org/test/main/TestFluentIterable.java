package org.test.main;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mazlum
 *
 */
public class TestFluentIterable {

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

    // Build persons list.
    final List<Person> persons = Arrays.asList(person1, person2, person3);

    // Filters and transforms persons to users.
    final List<User> users = FluentIterable.from(persons).filter(p -> p.getAge() > 20)
        .transform(TestFluentIterable::toUser).toList();

    // Same operation with stream.
    final List<User> usersWithStream = persons.stream().filter(p -> p.getAge() > 20)
        .map(TestFluentIterable::toUser).collect(Collectors.toList());

    System.out.println("User transform result : " + users);
    System.out.println("User transform with stream result : " + usersWithStream);
  }

  private static User toUser(final Person person) {
    final User user = new User();
    user.setNom(person.getNom());
    user.setPrenom(person.getNom());
    user.setAge(person.getAge());

    return user;
  }
}
