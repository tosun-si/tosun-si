package com.mowitnow.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mowitnow.backend.domain.Mower;
import com.mowitnow.backend.dto.PositionFinalDto;
import com.mowitnow.backend.helper.MowerHelper;
import com.mowitnow.backend.mapper.MowerMapper;
import com.mowitnow.backend.service.IApplicationParamService;
import com.mowitnow.backend.service.IMowerService;

/**
 * Implementation of {@link IMowerService}.
 * 
 * @see IMowerService
 * @author Mazlum TOSUN
 */
@Service
public class MowerServiceImpl implements IMowerService {

  /** Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(MowerServiceImpl.class);

  // ----------------------------------------------
  // Fields
  // ----------------------------------------------

  @Inject
  private IApplicationParamService applicationParamService;

  private List<Mower> mowers;

  // ----------------------------------------------
  // Init
  // ----------------------------------------------

  /**
   * Allows to initializes mowers.
   */
  @PostConstruct
  private void init() {
    LOGGER.debug("Initializing mowers by application parameters...");

    // Gets mowers by application parameters (directions, positions).
    this.mowers = MowerMapper.INSTANCE.paramsToMowers(this.applicationParamService.getDirections(),
        this.applicationParamService.getPosition());
  }

  // ----------------------------------------------
  // Public methods
  // ----------------------------------------------

  @Override
  public List<PositionFinalDto> getFinalPositions() {

    LOGGER.debug("Getting mowers final position...");

    // Transforms mowers to object that contains mower last positions.
    return this.getMowers().stream().map(MowerHelper.INSTANCE::getMowerFinalPosition)
        .collect(Collectors.toList());
  }

  // ----------------------------------------------
  // Private methods
  // ----------------------------------------------

  /**
   * Allows to get {@link Mower} list in application.
   * 
   * @return {@link Mower} list
   */
  private List<Mower> getMowers() {
    LOGGER.debug("Getting mowers...");

    // Returns mowers.
    return this.mowers;
  }
}
