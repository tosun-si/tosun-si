package com.mowitnow.backend.service;

import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mowitnow.backend.AbstractIT;
import com.mowitnow.backend.domain.type.Orientation;
import com.mowitnow.backend.dto.PositionFinalDto;

/**
 * Allows to test business treatments of {@link IMowerService}.
 * 
 * @author Mazlum TOSUN
 */
public class MowerServiceIT extends AbstractIT {

  @Autowired
  private IMowerService mowerService;

  @Test
  public void whenComputeLastPositionOfExistingMowers_ExpectResultsOk() {

    // Calls service.
    final List<PositionFinalDto> finalPositions = mowerService.getFinalPositions();

    // Asserts.
    Assertions.assertThat(finalPositions).isNotNull().isNotEmpty();
    final PositionFinalDto position1 = finalPositions.get(0);
    Assertions.assertThat(position1).isNotNull();
    Assertions.assertThat(position1.getPosition().getOrientation()).isNotNull()
        .isEqualTo(Orientation.N);
    Assertions.assertThat(position1.getPosition().getCoordinateX()).isNotNull().isEqualTo(1);
    Assertions.assertThat(position1.getPosition().getCoordinateY()).isNotNull().isEqualTo(3);

    final PositionFinalDto position2 = finalPositions.get(1);
    Assertions.assertThat(position2).isNotNull();
    Assertions.assertThat(position2.getPosition().getOrientation()).isNotNull()
        .isEqualTo(Orientation.E);
    Assertions.assertThat(position2.getPosition().getCoordinateX()).isNotNull().isEqualTo(5);
    Assertions.assertThat(position2.getPosition().getCoordinateY()).isNotNull().isEqualTo(1);
  }
}
