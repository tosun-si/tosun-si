package org.test.main;

import java.util.Arrays;
import java.util.List;

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

    final List<Person> persons = Arrays.asList(person1, person2, person3);

    final List<User> users = (List<User>) FluentIterable.from(persons).filter(p -> p.getAge() > 20)
        .transform(TestFluentIterable::toUser).toList();

    System.out.println("User transform result : " + users);
  }

  private static User toUser(final Person person) {
    final User user = new User();
    user.setNom(person.getNom());
    user.setPrenom(person.getNom());
    user.setAge(person.getAge());

    return user;
  }
}
