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
  private String positionParams;
  @Value("${mower.direction}")
  private String directionsParams;

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
  private void init() throws ApplicationParamException {
    LOGGER.debug("Initializing application parameters...");

    // Checks if mandatory fields are not empty.
    Optional.ofNullable(this.positionParams).orElseThrow(() -> new ApplicationParamException(
        "Positions parameters must not be empty in application parameters file"));
    Optional.ofNullable(this.directionsParams).orElseThrow(() -> new ApplicationParamException(
        "Directions parameters must not be empty in application parameters file"));

    // Gets a predicate that allows to check if positions length is same to directions length.
    final Predicate<String> positionsLengthSameDirections = p -> p
        .split(MowitnowConstant.MOWERS_SEPARATOR).length == this.directionsParams.split(",").length;

    // Via optional apply previous filter, and if after the filter, optional is empty, we return an
    // exception.
    Optional.of(this.positionParams).filter(positionsLengthSameDirections).orElseThrow(
        () -> new ApplicationParamException("Positions length must be same to directions length"));
  }

  @Override
  public String getDirectionsParams() {
    return this.directionsParams;
  }

  @Override
  public String getPositionParams() {
    return this.positionParams;
  }
}
