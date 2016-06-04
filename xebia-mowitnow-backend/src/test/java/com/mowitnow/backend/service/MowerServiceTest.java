package com.mowitnow.backend.service;

import java.util.List;

import javax.inject.Inject;

import org.fest.assertions.Assertions;
import org.junit.Test;

import com.mowitnow.backend.AbstractTest;
import com.mowitnow.backend.constant.MowitnowConstant;
import com.mowitnow.backend.domain.type.Orientation;
import com.mowitnow.backend.dto.PositionFinalDto;

/**
 * Allows to test business treatments of {@link IMowerService}.
 * 
 * @author Mazlum TOSUN
 */
public class MowerServiceTest extends AbstractTest {

  // ----------------------------------------------
  // Fields
  // ----------------------------------------------

  @Inject
  private IMowerService mowerService;
  @Inject
  private IApplicationParamService applicationParamService;

  // ----------------------------------------------
  // Tests
  // ----------------------------------------------

  @Test
  public void whenComputeLastPositionOfExistingMowers_ExpectResultsOk() {

    // Calls service.
    final List<PositionFinalDto> finalPositions = mowerService.getFinalPositions();

    // Asserts.
    Assertions.assertThat(finalPositions).isNotNull().isNotEmpty();

    // Gets expected result positions in application parameters file.
    final String[] expectedPositions =
        applicationParamService.getExpectedPositions().split(MowitnowConstant.MOWERS_SEPARATOR);

    int index = 0;
    for (PositionFinalDto finalPosition : finalPositions) {

      // Gets expected positions in separate variables.
      final String expectedPosition = expectedPositions[index];
      final Orientation expectedOrientation =
          Orientation.valueOf(String.valueOf(expectedPosition.charAt(0)));
      final String expectedCoordinateX = String.valueOf(expectedPosition.charAt(1));
      final String expectedCoordinateY = String.valueOf(expectedPosition.charAt(2));

      // Asserts.
      Assertions.assertThat(finalPosition).isNotNull();
      Assertions.assertThat(finalPosition.getMower()).isNotNull();
      Assertions.assertThat(finalPosition.getMower().getId()).isNotNull();
      Assertions.assertThat(finalPosition.getPosition().getOrientation()).isNotNull()
          .isEqualTo(expectedOrientation);
      Assertions.assertThat(finalPosition.getPosition().getCoordinateX().toString()).isNotNull()
          .isEqualTo(expectedCoordinateX);
      Assertions.assertThat(finalPosition.getPosition().getCoordinateY().toString()).isNotNull()
          .isEqualTo(expectedCoordinateY);

      index++;
    }
  }
}
