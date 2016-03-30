package com.mowitnow.backend.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
  public Map<String, Position> getFinalPositions() {

    LOGGER.debug("Getting mowers final position...");

    // Gets all mowers.
    final List<Mower> mowers = this.getMowers();

    final Map<String, Position> finalPositions = Maps.newHashMap();
    for (Mower mower : mowers) {

      // Calls a methods that allows to get mower final position and puts result this position in
      // result map.
      finalPositions.putAll(this.getMowerFinalPosition(mower));
    }

    return finalPositions;
  }

  @Override
  public List<PositionFinalDto> getFinalPositions2() {

    LOGGER.debug("Getting mowers final position...");

    return this.getMowers().stream().map(MowerHelper.INSTANCE::getMowerFinalPosition)
        .collect(Collectors.toList());
  }

  // ----------------------------------------------
  // Private methods
  // ----------------------------------------------

  /**
   * Gets final position of the given mower. Final position contains x/y coordinate and orientation.
   * A result is put in map with [key-value] pair => [MOWER ID-LAST POSITION].
   * 
   * @return final {@link Position} list in map with [key-value] pair => [MOWER ID-LAST POSITION]
   */
  private Map<String, Position> getMowerFinalPosition(final Mower mower) {

    LOGGER.debug("Getting mower [{}] final position...", mower.getName());

    // Puts initial position in result map. Correspond to initial position of mower.
    final Map<String, Position> finalPosition = Maps.newHashMap();
    finalPosition.put(mower.getId(), mower.getPosition());

    for (Direction direction : mower.getDirections()) {

      // Gets last position of mower.
      final Position lastPosition = finalPosition.get(mower.getId());

      // Gets new position by current direction and x/y coordinates.
      final Position newPosition = lastPosition.getOrientation().getNewPosition(direction,
          lastPosition.getCoordinateX(), lastPosition.getCoordinateY());

      // We add new position if new x and y coordinates are in surface.
      if (MowerHelper.INSTANCE.checkInSurface(newPosition.getCoordinateX(),
          newPosition.getCoordinateY())) {

        // Puts new position in result by current orientation and x, y coordinates.
        finalPosition.put(mower.getId(), newPosition);
      }
    }

    return finalPosition;
  }

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
