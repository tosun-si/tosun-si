package org.test.main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Mazlum
 * @param <T>
 */
public final class FluentIterable<T> {

  // Fields.

  private List<T> list;

  /**
   * Private constructor.
   */
  private FluentIterable(final List<T> list) {
    this.list = list;
  }

  public static <T> FluentIterable<T> from(final List<T> fromList) {
    return new FluentIterable<>(fromList);
  }

  // Setters.

  /**
   * Allows to filter list with a {@link Predicate} behavior parameterization.
   * 
   * @param filter current filter
   * @return current {@link FluentIterable}
   */
  public FluentIterable<T> filter(final Predicate<T> filter) {

    final List<T> filteredList = new ArrayList<>();
    list.forEach(t -> {
      if (filter.test(t)) {
        filteredList.add(t);
      }
    });

    list = filteredList;

    return this;
  }

  /**
   * Allows to transform list to other, with a {@link Function} behavior parameterization (mapper).
   * 
   * @param mapper current mapper function
   * @return current {@link FluentIterable}
   */
  public <U> FluentIterable<U> transform(final Function<T, U> mapper) {

    final List<U> transformedList = new ArrayList<>();
    list.forEach(t -> transformedList.add(mapper.apply(t)));

    return new FluentIterable<>(transformedList);
  }

  /**
   * Allows to build result list.
   * 
   * @return result list
   */
  public List<T> toList() {
    return this.list;
  }
}
