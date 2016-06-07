package com.mowitnow.backend.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mowitnow.backend.AbstractTest;
import com.mowitnow.backend.exception.ApplicationParamException;
import com.mowitnow.backend.service.impl.ApplicationParamServiceImpl;

/**
 * Allows to test business treatments of {@link IApplicationParamService}.
 * 
 * @author Mazlum TOSUN
 */
public class ApplicationParamServiceTest extends AbstractTest {

  // ----------------------------------------------
  // Fields
  // ----------------------------------------------

  @Autowired
  private ApplicationParamServiceImpl applicationParamService;

  // ----------------------------------------------
  // Tests
  // ----------------------------------------------

  @Test(expected = ApplicationParamException.class)
  public void whenPositionParamIsNull_ExpectExceptionIsThrown() throws ApplicationParamException {

    // Overrides current position parameter to null in order to simulate an error case.
    ((ApplicationParamServiceImpl) applicationParamService).setPosition(null);

    // Calls init treatment.
    applicationParamService.init();
  }

  @Test(expected = ApplicationParamException.class)
  public void whenDirectionsParamIsNull_ExpectExceptionIsThrown() throws ApplicationParamException {

    // Overrides current directions parameter to null in order to simulate an error case.
    ((ApplicationParamServiceImpl) applicationParamService).setDirections(null);

    // Calls init treatment.
    applicationParamService.init();
  }

  @Test(expected = ApplicationParamException.class)
  public void whenPositionParamIsEmpty_ExpectExceptionIsThrown() throws ApplicationParamException {

    // Overrides current position parameter to null in order to simulate an error case.
    ((ApplicationParamServiceImpl) applicationParamService).setPosition("");

    // Calls init treatment.
    applicationParamService.init();
  }

  @Test(expected = ApplicationParamException.class)
  public void whenDirectionsParamIsEmpty_ExpectExceptionIsThrown()
      throws ApplicationParamException {

    // Overrides current directions parameter to null in order to simulate an error case.
    ((ApplicationParamServiceImpl) applicationParamService).setDirections("");

    // Calls init treatment.
    applicationParamService.init();
  }

  @Test(expected = ApplicationParamException.class)
  public void whenPositionNumberIsDifferentToDirections_ExpectExceptionIsThrown()
      throws ApplicationParamException {

    // Overrides current position and directions parameter with position number different to
    // directions, in order to simulate an exception.
    ((ApplicationParamServiceImpl) applicationParamService).setPosition("12N,33E");
    ((ApplicationParamServiceImpl) applicationParamService)
        .setDirections("GAGAGAGAA,AADAADADDA,GGADDADGDG");

    // Calls init treatment.
    applicationParamService.init();
  }
}
