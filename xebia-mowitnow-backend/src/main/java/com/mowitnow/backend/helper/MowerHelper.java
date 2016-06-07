package com.mowitnow.backend.helper;

import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.mowitnow.backend.domain.Mower;
import com.mowitnow.backend.domain.Position;
import com.mowitnow.backend.domain.type.Direction;
import com.mowitnow.backend.dto.PositionFinalDto;

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
   * Checks if x/y coordinates of the given {@link Position} are in surface. A surface is between 0
   * and 5.
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
   * x/y coordinate and orientation. A result is add in object that contains mower last position and
   * mower data.
   * 
   * @return {@link PositionFinalDto} object that contains mower last position and mower data
   */
  public PositionFinalDto getMowerFinalPosition(final Mower mower) {

    LOGGER.debug("Getting mower [{}] final position...", mower.getId());

    // Puts initial position in result map. Correspond to initial position of mower.
    final Map<Integer, Position> finalPosition = Maps.newHashMap();
    finalPosition.put(mower.getId(), mower.getPosition());

    // Gets last element in stream. It corresponds to position of last direction (final position).
    return mower.getDirections().stream().map(d -> this.getNextPosition(mower, d, finalPosition))
        .reduce((d1, d2) -> d2).orElse(null);
  }

  // ----------------------------------------------
  // Private method
  // ----------------------------------------------

  /**
   * Factory method that allows to get next position of {@link Mower} by current position of mower,
   * current direction and coordinate X/Y.
   * 
   * @param mower mower
   * @param direction direction
   * @param mowerPosition mower current position
   * @return {@link PositionFinalDto} object that contains mower next position and mower data
   */
  private PositionFinalDto getNextPosition(final Mower mower, final Direction direction,
      final Map<Integer, Position> mowerPosition) {

    // Gets last position of mower.
    final Position lastPosition = mowerPosition.get(mower.getId());

    // Gets new position by last position, current direction and x/y coordinates.
    final Position newPosition = lastPosition.getOrientation().getNewPosition(direction,
        lastPosition.getCoordinateX(), lastPosition.getCoordinateY());

    // We add next position if new x and y coordinates are in surface.
    final Optional<PositionFinalDto> nextPosition =
        Optional.of(newPosition).filter(MowerHelper.INSTANCE::checkInSurface)
            .map(p -> new PositionFinalDto.Builder().mower(mower).position(p).build());

    // If element is present and is in surface, we put position in map.
    nextPosition.ifPresent(p -> mowerPosition.put(mower.getId(), newPosition));

    // Returns optional that contains next position result.
    return nextPosition.orElse(null);
  }
}
