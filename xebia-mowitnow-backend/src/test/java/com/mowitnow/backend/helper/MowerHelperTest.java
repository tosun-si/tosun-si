package com.mowitnow.backend.helper;

import org.fest.assertions.Assertions;
import org.junit.Test;

import com.mowitnow.backend.AbstractTest;
import com.mowitnow.backend.domain.Position;
import com.mowitnow.backend.domain.type.Orientation;

/**
 * Allows to test treatments of {@link MowerHelper}.
 * 
 * @author Mazlum TOSUN
 */
public class MowerHelperTest extends AbstractTest {

  @Test
  public void whenMowerHasCoordinatesXAndYInSurface_ExpectReturnTrue() {

    // Tests data.
    final Integer coordinateX = 1;
    final Integer coordinateY = 2;
    final Position position = new Position(coordinateX, coordinateY, Orientation.N);

    // Calls mower helper by given parameters.
    final boolean isInSurface = MowerHelper.INSTANCE.checkInSurface(position);

    // Asserts.
    Assertions.assertThat(isInSurface).isTrue();
  }

  @Test
  public void whenMowerHasCoordinateXNotInSurface_ExpectReturnFalse() {

    // Tests data.
    final Integer coordinateX = 6;
    final Integer coordinateY = 2;
    final Position position = new Position(coordinateX, coordinateY, Orientation.N);

    // Calls mower helper by given parameters.
    final boolean isInSurface = MowerHelper.INSTANCE.checkInSurface(position);

    // Asserts.
    Assertions.assertThat(isInSurface).isFalse();
  }

  @Test
  public void whenMowerHasCoordinateYNotInSurface_ExpectReturnFalse() {

    // Tests data.
    final Integer coordinateX = 5;
    final Integer coordinateY = -1;
    final Position position = new Position(coordinateX, coordinateY, Orientation.N);

    // Calls mower helper by given parameters.
    final boolean isInSurface = MowerHelper.INSTANCE.checkInSurface(position);

    // Asserts.
    Assertions.assertThat(isInSurface).isFalse();
  }

  @Test
  public void whenMowerHasCoordinatesXAndYNotInSurface_ExpectReturnFalse() {

    // Tests data.
    final Integer coordinateX = 6;
    final Integer coordinateY = -1;
    final Position position = new Position(coordinateX, coordinateY, Orientation.N);

    // Calls mower helper by given parameters.
    final boolean isInSurface = MowerHelper.INSTANCE.checkInSurface(position);

    // Asserts.
    Assertions.assertThat(isInSurface).isFalse();
  }
}
