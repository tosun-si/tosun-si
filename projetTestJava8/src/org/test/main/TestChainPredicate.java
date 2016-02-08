package org.test.main;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

public class TestChainPredicate<T> {

  public enum Type {
    AND, OR
  }

  private final T t;
  private Map<Type, Predicate<T>> predicates = new HashMap<>();

  private TestChainPredicate(T t) {
    this.t = t;
  }

  public static <T> TestChainPredicate<T> of(T t) {
    return new TestChainPredicate<T>(t);
  }

  public TestChainPredicate<T> add(final Type type, final Predicate<T> predicate) {
    this.predicates.put(type, predicate);
    return this;
  }

  public boolean build() {

    Predicate<T> predicate = null;

    int index = 0;
    for (Entry<Type, Predicate<T>> entry : predicates.entrySet()) {

      final Type type = entry.getKey();

      if (index == 0) {
        predicate = entry.getValue();
      } else if (Type.AND.equals(type)) {
        predicate.and(entry.getValue());
      } else {
        predicate.or(entry.getValue());
      }

      index++;
    }

    return predicate.test(t);
  }
}
