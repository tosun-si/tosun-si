package com.mowitnow.backend.mapper;

import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;

import com.mowitnow.backend.AbstractTest;
import com.mowitnow.backend.domain.Position;

/**
 * Allows to test treatments of {@link PositionMapper}.
 * 
 * @author Mazlum TOSUN
 */
public class PositionMapperTest extends AbstractTest {

  @Test
  public void whenBuildPositionsByGivenParameters_ExpectSuccess() {

    // Tests data.
    final String positionParams = "12N,33E";

    // Calls position mapper by given parameters.
    final List<Position> positions = PositionMapper.INSTANCE.paramsToPositions(positionParams);

    // Asserts.
    Assertions.assertThat(positions).isNotNull().isNotEmpty().hasSize(2);
  }
}
