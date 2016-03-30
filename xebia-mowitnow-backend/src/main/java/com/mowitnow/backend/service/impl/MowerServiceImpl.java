package com.mowitnow.backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mowitnow.backend.domain.Mower;
import com.mowitnow.backend.domain.Position;
import com.mowitnow.backend.domain.type.Direction;
import com.mowitnow.backend.domain.type.Orientation;
import com.mowitnow.backend.dto.PositionFinalDto;
import com.mowitnow.backend.helper.MowerHelper;
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

  private List<Mower> mowers;

  // ----------------------------------------------
  // Init
  // ----------------------------------------------

  /**
   * Allows to initializes mowers.
   */
  @PostConstruct
  private void init() {
    LOGGER.debug("Initializing mowers...");

    // Build mower 1 directions.
    final List<Direction> directions = new ImmutableList.Builder<Direction>().add(Direction.G)
        .add(Direction.A).add(Direction.G).add(Direction.A).add(Direction.G).add(Direction.A)
        .add(Direction.G).add(Direction.A).add(Direction.A).build();

    // Build mower 1 position.
    final Position position = new Position(1, 2, Orientation.N);

    // Initializes mower 1.
    final Mower mower1 = new Mower.Builder("1").directions(directions).position(position).build();

    // Build mower 1 directions.
    final List<Direction> directions2 = new ImmutableList.Builder<Direction>().add(Direction.A)
        .add(Direction.A).add(Direction.D).add(Direction.A).add(Direction.A).add(Direction.D)
        .add(Direction.A).add(Direction.D).add(Direction.D).add(Direction.A).build();

    // Build mower 1 position.
    final Position position2 = new Position(3, 3, Orientation.E);

    // Initializes mower 1.
    final Mower mower2 = new Mower.Builder("2").directions(directions2).position(position2).build();

    // Adds result mower to field in service.
    this.mowers = Lists.newArrayList(mower1, mower2);
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
