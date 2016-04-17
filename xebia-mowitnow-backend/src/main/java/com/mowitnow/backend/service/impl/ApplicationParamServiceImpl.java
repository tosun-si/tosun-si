package com.mowitnow.backend.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

  @Override
  public String getDirectionsParams() {
    LOGGER.debug("Getting directions parameters...");

    return this.directionsParams;
  }

  @Override
  public String getPositionParams() {
    LOGGER.debug("Getting position parameters...");

    return this.positionParams;
  }
}
