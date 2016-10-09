package org.test.main.visitor;

import org.test.main.pojo.Car;
import org.test.main.pojo.Moto;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Visitor with lambda and function.
 * 
 * @param <R> result object
 */
public class Visitor<R> {

  final Map<Class<?>, Function<Object, R>> map = new HashMap<>();

  public static <R> Visitor<R> builder() {
    return new Visitor<R>();
  }

  public <T> Visitor<R> when(final Class<T> type, final Function<T, R> function) {
    map.put(type, object -> function.apply(type.cast(object)));
    return this;
  }

  public R call(final Object vehicule) {
    return map.getOrDefault(vehicule.getClass(), t -> {
      throw new IllegalArgumentException("Unknown " + vehicule.getClass());
    }).apply(vehicule);
  }

  /**
   * Mains for tests.
   * 
   * @param args args
   */
  public static void main(String[] args) {
    final String car = Visitor.<String>builder().when(Car.class, c -> "CAR")
        .when(Moto.class, m -> "MOTO").call(Car.class);
  }
}
