package com.javaslang.test.matching;

import static javaslang.API.$;
import static javaslang.API.Case;
import static javaslang.API.Match;
import static javaslang.Predicates.isIn;

import org.fest.assertions.Assertions;
import org.junit.Test;

import com.javaslang.test.enumeration.Car;
import com.javaslang.test.object.Person;

/**
 * Allows to test different cases of Javaslang pattern matching.
 * 
 * @author Mazlum TOSUN
 */
public class PatternMatchingTest {

  @Test
  public void testWithString() {

    // Test from string to get string.
    final String test2 =
        Match("tata").of(Case($("toto"), "one"), Case($("tata"), "two"), Case($("titi"), "?"));
    Assertions.assertThat(test2).isNotNull().isNotEmpty().isEqualTo("two");

    // Test from string to get string (with IN).
    final String test3 = Match("tata").of(Case(isIn("toto", "tete"), "one"),
        Case(isIn("roro"), "two"), Case(isIn("tata", "lele"), "roooo"));
    Assertions.assertThat(test3).isNotNull().isNotEmpty().isEqualTo("roooo");
  }

  @Test
  public void testWithInteger() {

    // Test from integer to get string.
    final String test = Match(1).of(Case($(1), "one"), Case($(2), "two"), Case($(), "?"));
    Assertions.assertThat(test).isNotNull().isNotEmpty().isEqualTo("one");
  }

  @Test
  public void testWithEnum() {

    // Test from enum to get string.
    final String test4 = Match(Car.BMW).of(Case(isIn(Car.MERCEDES, Car.PEUGEOT), "one"),
        Case(isIn(Car.BMW), "two"), Case(isIn(Car.RENAULT), "roooo"));
    Assertions.assertThat(test4).isNotNull().isNotEmpty().isEqualTo("two");
  }

  @Test
  public void testWithObject() {

    // Build persons.
    final Person person1 = new Person(1);
    final Person person2 = new Person(2);
    final Person person3 = new Person(3);
    final Person person4 = new Person(4);

    // Test from object to get string.
    final String test = Match(person4).of(Case($(person1), "one"), Case($(person3), "two"),
        Case($(person2), "three"), Case($(person4), "four"));
    Assertions.assertThat(test).isNotNull().isNotEmpty().isEqualTo("four");

    // Test from object to get string (with IN).
    final String test2 = Match(person4).of(Case(isIn(person1, person2), "one"),
        Case(isIn(person1), "two"), Case(isIn(person4, person2), "roooo"));
    Assertions.assertThat(test2).isNotNull().isNotEmpty().isEqualTo("roooo");
  }
}
