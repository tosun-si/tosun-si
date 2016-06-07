package com.mowitnow.backend.service.impl;

import java.util.Optional;
import java.util.function.Predicate;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mowitnow.backend.constant.MowitnowConstant;
import com.mowitnow.backend.exception.ApplicationParamException;
import com.mowitnow.backend.service.IApplicationParamService;

/**
 * Implementation of {@link IApplicationParamService}.
 * 
 * @see IApplicationParamService
 * @author Mazlum TOSUN
 */
@Service
public class ApplicationParamServiceImpl implements IApplicationParamService {

  /** Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationParamServiceImpl.class);

  // ----------------------------------------------
  // Fields
  // ----------------------------------------------

  @Value("${mower.positions}")
  private String position;
  @Value("${mower.directions}")
  private String directions;
  @Value("${mower.expectedPositions}")
  private String expectedPositions;

  // ----------------------------------------------
  // Init
  // ----------------------------------------------

  /**
   * Allows to initializes application parameters.
   * 
   * @throws ApplicationParamException thrown if application parameters are not correctly positioned
   *         in application.properties file
   */
  @PostConstruct
  public void init() throws ApplicationParamException {
    LOGGER.debug("Initializing application parameters...");

    // Checks if mandatory fields are not empty.
    Optional.ofNullable(this.position).orElseThrow(() -> new ApplicationParamException(
        "Positions parameters must not be empty in application parameters file"));
    Optional.ofNullable(this.directions).orElseThrow(() -> new ApplicationParamException(
        "Directions parameters must not be empty in application parameters file"));

    // Gets a predicate that allows to check if positions length is same to directions length.
    final Predicate<String> positionsLengthSameDirections =
        p -> p.split(MowitnowConstant.MOWERS_SEPARATOR).length == this.directions
            .split(MowitnowConstant.MOWERS_SEPARATOR).length;

    // Via optional apply previous filter, and if after the filter optional is empty, we return an
    // exception.
    Optional.of(this.position).filter(positionsLengthSameDirections).orElseThrow(
        () -> new ApplicationParamException("Positions length must be same to directions length"));
  }

  @Override
  public String getDirections() {
    return this.directions;
  }

  public void setDirections(String directions) {
    this.directions = directions;
  }

  @Override
  public String getPosition() {
    return this.position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  @Override
  public String getExpectedPositions() {
    return this.expectedPositions;
  }
}
