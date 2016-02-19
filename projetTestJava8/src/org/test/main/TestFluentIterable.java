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
    person1.setLastName("Zizou");
    person1.setLastName("Mazizou");
    person1.setAge(20);

    final Person person2 = new Person();
    person2.setLastName("Zorro");
    person2.setLastName("Roronoa");
    person2.setAge(21);

    final Person person3 = new Person();
    person3.setLastName("Motta");
    person3.setLastName("Thiago");
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
    user.setNom(person.getLastName());
    user.setPrenom(person.getLastName());
    user.setAge(person.getAge());

    return user;
  }
}
