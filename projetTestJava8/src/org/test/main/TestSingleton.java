package org.test.main;

import java.util.function.Supplier;

public class TestSingleton {

  private static final Supplier<TestSingleton> INSTANCE = TestSingleton::new;

  private TestSingleton() {}

  public static Supplier<TestSingleton> getInstance() {
    return INSTANCE;
  }
}
