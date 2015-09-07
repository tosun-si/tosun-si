package org.test.main;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Function test with functional interface.
 * 
 * @author Mazlum
 */
@FunctionalInterface
public interface LoggerTestFunction {

  /**
   * Log a message with severity.
   * 
   * @param message message
   * @param severity severity
   */
  public void log(final String message, final String severity);

  /**
   * Log with filter.
   */
  default LoggerTestFunction filter(final Predicate<String> predicate) {
    Objects.requireNonNull(predicate);
    return (message, severity) -> {
      if (predicate.test(message)) {
        this.log(message, severity);
      }
    };
  }

  /**
   * Log with filter.
   */
  default void logWithFilter(final Predicate<String> predicate, final String message,
      final String severity) {
    if (predicate.test(message)) {
      this.log(message, severity);
    }
  }
}
