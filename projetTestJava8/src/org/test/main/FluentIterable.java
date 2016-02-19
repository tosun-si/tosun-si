package org.test.main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Wrapper that allows to do many operation on a given {@link List}, with fluent style builder
 * pattern.
 * 
 * @author Mazlum
 * @param <T> current type of wrapped list
 */
public final class FluentIterable<T> {

  // Fields.

  private List<T> list;

  // Constructors.

  /**
   * Private constructor.
   */
  private FluentIterable(final List<T> list) {
    this.list = list;
  }

  /**
   * Static factory method that allows to instantiate {@link FluentIterable} from a given list.
   * 
   * @param fromList from list
   * @return current {@link FluentIterable} with from list
   */
  public static <T> FluentIterable<T> from(final List<T> fromList) {
    return new FluentIterable<>(fromList);
  }

  // Builder methods.

  /**
   * Allows to filter list with a {@link Predicate}. This {@link Predicate} allows to apply
   * "behavior parameterization" strategy.
   * 
   * @param filter current filter
   * @return current {@link FluentIterable}
   */
  public FluentIterable<T> filter(final Predicate<T> filter) {

    // Filters current list by given predicate.
    final List<T> filteredList = new ArrayList<>();
    this.list.forEach(t -> {
      if (filter.test(t)) {
        filteredList.add(t);
      }
    });

    // Adds filtered list to current list.
    this.list = filteredList;

    return this;
  }

  /**
   * Allows to transform list to other, with a {@link Function} (mapper). This {@link Function}
   * allows to apply "behavior parameterization" strategy.
   * 
   * @param mapper current mapper function
   * @return current {@link FluentIterable}
   */
  public <U> FluentIterable<U> transform(final Function<T, U> mapper) {

    // Build transformed list by given function.
    final List<U> transformedList = new ArrayList<>();
    list.forEach(t -> transformedList.add(mapper.apply(t)));

    // Returns new instance of fluent iterable with transformed list.
    return new FluentIterable<>(transformedList);
  }

  // Result build methods.

  /**
   * Allows to return result list.
   * 
   * @return result list
   */
  public List<T> toList() {
    return this.list;
  }
}
