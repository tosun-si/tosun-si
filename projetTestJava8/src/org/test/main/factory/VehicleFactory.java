package org.test.main.factory;

import org.test.main.pojo.Car;
import org.test.main.pojo.Moto;
import org.test.main.pojo.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Factory for {@link Vehicle}.
 * 
 * @author mtosun
 */
public class VehicleFactory {

  private Map<String, Supplier<Vehicle>> vehicles = new HashMap<>();

  public static VehicleFactory builder() {
    return new VehicleFactory();
  }

  public VehicleFactory register(final String type, Supplier<Vehicle> supplier) {
    this.vehicles.put(type, supplier);
    return this;
  }

  /**
   * Creates and gets new {@link Vehicle} by the given type.
   * 
   * @param type type
   * @return {@link Vehicle} vehicle
   */
  public Vehicle create(final String type) {
    return vehicles.getOrDefault(type, () -> {
      throw new IllegalArgumentException("Unknown " + type);
    }).get();
  }

  /**
   * Mains for tests.
   * 
   * @param args args
   */
  public static void main(String[] args) {

    VehicleFactory.builder().register("car", () -> new Car()).register("moto", () -> new Moto())
        .create("moto");
  }
}
