package com.mowitnow.backend.mapper;

import java.util.List;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mowitnow.backend.domain.Mower;

/**
 * Allows to test business treatments of {@link MowerMapper}.
 * 
 * @author Mazlum TOSUN
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring/xebia-mowitnow-backend-context.xml" })
public class MowerMapperIT {

  @Test
  public void whenBuildMowersByGivenParameters_ExpectSuccess() {

    // Tests data.
    final int mowerNumber = 2;
    final String directionsParams = "GAGAGAGAA,AADAADADDA";
    final String positionParams = "12N,33E";

    // Calls mower mapper by given parameters.
    final List<Mower> mowers =
        MowerMapper.INSTANCE.paramsToMowers(mowerNumber, directionsParams, positionParams);

    // Asserts.
    Assertions.assertThat(mowers).isNotNull().isNotEmpty().hasSize(mowerNumber);
  }
}
