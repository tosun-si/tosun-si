/**
 * 
 */
package com.javaslang.test.matching;

import static javaslang.API.$;
import static javaslang.API.Case;
import static javaslang.API.Match;
import static javaslang.Predicates.isIn;

import org.fest.assertions.Assertions;
import org.junit.Test;

import com.javaslang.test.enumeration.Car;



/**
 * @author Mazlum TOSUN
 */
public class PatternMatchingTest {

  @Test
  public void testPatternMatching() {

    // Test with test from integer to get conditional string.
    final String test = Match(1).of(Case($(1), "one"), Case($(2), "two"), Case($(), "?"));
    Assertions.assertThat(test).isNotNull().isNotEmpty().isEqualTo("one");

    // Test with test from string to get conditional string.
    final String test2 =
        Match("tata").of(Case($("toto"), "one"), Case($("tata"), "two"), Case($("titi"), "?"));
    Assertions.assertThat(test2).isNotNull().isNotEmpty().isEqualTo("two");

    // Test with test from string to get conditional string.
    final String test3 = Match("tata").of(Case(isIn("toto", "tete"), "one"),
        Case(isIn("roro"), "two"), Case(isIn("tata", "lele"), "roooo"));
    Assertions.assertThat(test3).isNotNull().isNotEmpty().isEqualTo("roooo");

    // Test with test from enul to get conditional enum.
    final String test4 = Match(Car.BMW).of(Case(isIn(Car.MERCEDES, Car.PEUGEOT), "one"),
        Case(isIn(Car.BMW), "two"), Case(isIn(Car.RENAULT), "roooo"));
    Assertions.assertThat(test4).isNotNull().isNotEmpty().isEqualTo("two");
  }
}
