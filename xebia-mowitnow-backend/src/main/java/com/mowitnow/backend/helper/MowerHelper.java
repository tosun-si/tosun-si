package com.mowitnow.backend.helper;

import java.util.Map;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.mowitnow.backend.domain.Mower;
import com.mowitnow.backend.domain.Position;
import com.mowitnow.backend.dto.PositionFinalDto;
import com.mowitnow.backend.mapper.PositionFinalMapper;

/**
 * Helper that allows to get factory methods and do treatments on mower.
 * 
 * @author Mazlum TOSUN
 */
public enum MowerHelper {

  // Single instance.
  INSTANCE;

  /** Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(MowerHelper.class);

  // ----------------------------------------------
  // Public methods
  // ----------------------------------------------

  /**
   * Checks if the given x/y coordinates are in surface. A surface is between 0 and 5.
   * 
   * @param coordinateX current x coordinate
   * @param coordinateY current y coordinate
   * @return {@link Boolean} for result
   */
  public boolean checkInSurface(final int coordinateX, final int coordinateY) {

    // Checks if the given x/y coordinates are between 0 and 5, via streams.
    return IntStream.rangeClosed(0, 5).anyMatch(c -> c == coordinateX)
        && IntStream.rangeClosed(0, 5).anyMatch(c -> c == coordinateY);
  }

  /**
   * Checks if the given x/y coordinates are in surface. A surface is between 0 and 5.
   * 
   * @param position current position that contains x, y coordinate
   * @return {@link Boolean} for result
   */
  public boolean checkInSurface(final Position position) {

    // Checks if the given x/y coordinates are between 0 and 5, via streams.
    return IntStream.rangeClosed(0, 5).anyMatch(c -> c == position.getCoordinateX())
        && IntStream.rangeClosed(0, 5).anyMatch(c -> c == position.getCoordinateY());
  }

  /**
   * Factory method that allows to get final position of the given mower. Final position contains
   * x/y coordinate and orientation. A result is put in map with [key-value] pair => [MOWER ID-LAST
   * POSITION].
   * 
   * @return final {@link Position} list in map with [key-value] pair => [MOWER ID-LAST POSITION]
   */
  public PositionFinalDto getMowerFinalPosition(final Mower mower) {

    LOGGER.debug("Getting mower [{}] final position...", mower.getName());

    // Puts initial position in result map. Correspond to initial position of mower.
    final Map<String, Position> finalPosition = Maps.newHashMap();
    finalPosition.put(mower.getId(), mower.getPosition());

    // Gets last element in stream. It corresponds to position of last direction (final position).
    return mower.getDirections().stream()
        .map(d -> PositionFinalMapper.INSTANCE.toPositionFinal(mower, d, finalPosition))
        .reduce((d1, d2) -> d2).orElse(null);
  }
}
