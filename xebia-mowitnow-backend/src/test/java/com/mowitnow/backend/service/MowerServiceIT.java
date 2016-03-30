package com.mowitnow.backend.service;

import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mowitnow.backend.domain.type.Orientation;
import com.mowitnow.backend.dto.PositionFinalDto;

/**
 * Allows to test business treatments of {@link IMowerService}.
 * 
 * @author Mazlum TOSUN
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/xebia-mowitnow-backend-context.xml" })
public class MowerServiceIT {

  @Autowired
  private IMowerService mowerService;

  @Test
  public void whenComputeLastPositionOfExistingMowers_ExpectResultsOk() {

    // Calls service.
    // final Map<String, Position> finalPositions = mowerService.getFinalPositions2();

    final List<PositionFinalDto> finalPositions = mowerService.getFinalPositions2();

    // Asserts.
    Assertions.assertThat(finalPositions).isNotNull().isNotEmpty();

    // final Position position1 = finalPositions.get("1");
    // Assertions.assertThat(position1).isNotNull();
    // Assertions.assertThat(position1.getOrientation()).isNotNull().isEqualTo(Orientation.N);
    // Assertions.assertThat(position1.getCoordinateX()).isNotNull().isEqualTo(1);
    // Assertions.assertThat(position1.getCoordinateY()).isNotNull().isEqualTo(3);
    //
    // final Position position2 = finalPositions.get("2");
    // Assertions.assertThat(position2).isNotNull();
    // Assertions.assertThat(position2.getOrientation()).isNotNull().isEqualTo(Orientation.E);
    // Assertions.assertThat(position2.getCoordinateX()).isNotNull().isEqualTo(5);
    // Assertions.assertThat(position2.getCoordinateY()).isNotNull().isEqualTo(1);

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
