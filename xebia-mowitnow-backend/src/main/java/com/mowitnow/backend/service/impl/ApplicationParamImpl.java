package com.mowitnow.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mowitnow.backend.domain.Position;
import com.mowitnow.backend.domain.type.Direction;
import com.mowitnow.backend.service.IMowerService;

/**
 * Implementation of {@link IMowerService}.
 * 
 * @see IMowerService
 * @author Mazlum TOSUN
 */
@Service
public class ApplicationParamImpl {

  /** Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationParamImpl.class);

  // ----------------------------------------------
  // Fields
  // ----------------------------------------------

  @Value("${mower.positions}")
  private String positionsParam;
  @Value("${mower.direction}")
  private String directionsParam;

  private List<Position> positions;
  private List<Direction> directions;

  // ----------------------------------------------
  // Init
  // ----------------------------------------------

  /**
   * Allows to initializes mowers.
   */
  @PostConstruct
  private void init() {
    LOGGER.debug("Initializing application parameters...");

    this.directions = "GAGAGAGAA".chars().mapToObj(i -> (char) i).map(String::valueOf)
        .map(Direction::valueOf).collect(Collectors.toList());
  }
}
