package org.test.main;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Allows to test treatments about fluent iterable.
 * 
 * @author Mazlum
 */
public class TestFluentIterable {

  /**
   * Allows to test treatments about fluent iterable.
   * 
   * @param args arguments
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

    // Filters and transforms persons to users, with fluent iterable.
    final List<User> usersWithFluentIterable = FluentIterable.from(persons)
        .filter(p -> p.getAge() > 20).transform(TestFluentIterable::toUser).toList();

    // Same operation with stream API.
    final List<User> usersWithStream = persons.stream().filter(p -> p.getAge() > 20)
        .map(TestFluentIterable::toUser).collect(Collectors.toList());

    System.out.println("User transform with fluent iterable result : " + usersWithFluentIterable);
    System.out.println("User transform with stream API result : " + usersWithStream);
  }

  /**
   * Allows to map {@link Person} to {@link User}.
   * 
   * @param person current person
   * @return {@link User} by given {@link Person}
   */
  private static User toUser(final Person person) {
    final User user = new User();
    user.setLastName(person.getLastName());
    user.setFirstName(person.getLastName());
    user.setAge(person.getAge());

    return user;
  }
}
