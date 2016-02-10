package org.test.main;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Mazlum
 * @param <T>
 */
public final class FluentIterable<T, U> {

  // Fields.

  private List<?> list;
  private Class finalTypeList;

  /**
   * Private constructor.
   */
  private FluentIterable(final List<T> list) {
    this.list = list;
  }

  public static <T, U> FluentIterable<T, U> from(final List<T> fromList) {
    return new FluentIterable<>(fromList);
  }

  // Setters.

  /**
   * Allows to filter list with a {@link Predicate} behavior parameterization.
   * 
   * @param filter current filter
   * @return current {@link FluentIterable}
   */
  @SuppressWarnings("unchecked")
  public FluentIterable<T, U> filter(final Predicate<T> filter) {

    final List<T> filteredList = new ArrayList<>();
    list.forEach(t -> {
      if (filter.test((T) t)) {
        filteredList.add((T) t);
      }
    });

    list = filteredList;

    // final Class<T> clazz =(Class<T>) ((ParameterizedType)
    // getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    return this;
  }

  /**
   * Allows to transform list to other, with a {@link Function} behavior parameterization (mapper).
   * 
   * @param mapper current mapper function
   * @return current {@link FluentIterable}
   */
  @SuppressWarnings("unchecked")
  public FluentIterable<T, U> transform(final Function<T, U> mapper) {

    final List<U> transformedList = new ArrayList<>();
    list.forEach(t -> transformedList.add(mapper.apply((T) t)));

    list = transformedList;

    return this;
  }

  public List<?> toList() {
    return this.list;
  }
}
