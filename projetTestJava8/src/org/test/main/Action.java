package org.test.main;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Allows to execute an action with consumer preceding by a predicate.
 * 
 * @author mtosun
 */
public class Action<T> {

  private final T object;
  private final Map<Boolean, Consumer<T>> actions;

  public Action(T object) {
    this.object = object;
    this.actions = new HashMap<>();
  }

  public static <T> Action<T> of(T object) {
    return new Action<>(object);
  }

  public Action<T> apply(final Predicate<? super T> predicate, final Consumer<? super T> consumer) {

    actions.put(predicate.test(object), consumer::accept);

    // if (predicate.test(object)) {
    // consumer.accept(object);
    // }

    return this;
  }

  public void execute() {
    actions.get(true).accept(object);
  }
}
