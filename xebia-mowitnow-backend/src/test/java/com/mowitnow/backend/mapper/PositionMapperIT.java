package com.mowitnow.backend.mapper;

import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mowitnow.backend.domain.Position;

/**
 * Allows to test business treatments of {@link PositionMapper}.
 * 
 * @author Mazlum TOSUN
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/xebia-mowitnow-backend-context.xml" })
public class PositionMapperIT {

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
