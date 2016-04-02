package com.mowitnow.backend.mapper;

import java.util.Map;
import java.util.Optional;

import com.mowitnow.backend.domain.Mower;
import com.mowitnow.backend.domain.Position;
import com.mowitnow.backend.domain.type.Direction;
import com.mowitnow.backend.dto.PositionFinalDto;
import com.mowitnow.backend.helper.MowerHelper;

/**
 * Mapper that allows to transform object that concerns {@link PositionFinalDto}.
 * 
 * @author Mazlum TOSUN
 */
public enum PositionFinalMapper {

  // Single instance.
  INSTANCE;

  // ----------------------------------------------
  // Public methods
  // ----------------------------------------------

  /**
   * Allows to build and transform given parameters to final position of {@link Mower}.
   * 
   * @param mower mower
   * @param direction direction
   * @param mowerPosition mower current position
   * @return {@link PositionFinalDto} object that contains mower final position and mower data
   */
  public PositionFinalDto toPositionFinal(final Mower mower, final Direction direction,
      final Map<Integer, Position> mowerPosition) {

    // Gets last position of mower.
    final Position lastPosition = mowerPosition.get(mower.getId());

    // Gets new position by current direction and x/y coordinates.
    final Position newPosition = lastPosition.getOrientation().getNewPosition(direction,
        lastPosition.getCoordinateX(), lastPosition.getCoordinateY());

    // We add new position if new x and y coordinates are in surface.
    final Optional<PositionFinalDto> finalPosition =
        Optional.of(newPosition).filter(MowerHelper.INSTANCE::checkInSurface)
            .map(p -> new PositionFinalDto.Builder().mower(mower).position(p).build());

    // If element is present and is in surface, we put position in map.
    finalPosition.ifPresent(p -> mowerPosition.put(mower.getId(), newPosition));

    // Returns optional that contains final position object.
    return finalPosition.orElse(null);
  }
}
