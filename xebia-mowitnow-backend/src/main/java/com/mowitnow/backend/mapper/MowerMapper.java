package com.mowitnow.backend.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.mowitnow.backend.constant.MowitnowConstant;
import com.mowitnow.backend.domain.Mower;
import com.mowitnow.backend.domain.Position;
import com.mowitnow.backend.domain.type.Direction;

/**
 * Mapper that allows to transform object that concerns {@link Mower}.
 * 
 * @author Mazlum TOSUN
 */
public enum MowerMapper {

  // Single instance.
  INSTANCE;

  // ----------------------------------------------
  // Public methods
  // ----------------------------------------------

  /**
   * Allows to transform the given applications parameters to {@link Mower} list. These parameters
   * concerns mower number, directions and positions.
   * 
   * @param directionsParams directions parameters
   * @param positionParams position parameters
   * @return {@link Mower} mower list
   */
  public List<Mower> paramsToMowers(final String directionsParams, final String positionParams) {

    // Gets mower number. This number corresponds to directions or positions number.
    final int mowerNumber = directionsParams.split(MowitnowConstant.MOWERS_SEPARATOR).length;

    // Build and gets mowers by mower number.
    final List<Mower> mowers =
        IntStream.range(0, mowerNumber).mapToObj(n -> new Mower(n)).collect(Collectors.toList());

    // Gets mower directions by directions that given by application parameters.
    final List<List<Direction>> groupedDirections =
        DirectionMapper.INSTANCE.paramsToDirection(directionsParams);

    // Gets mower positions by positions that given by application parameters.
    final List<Position> positions = PositionMapper.INSTANCE.paramsToPositions(positionParams);

    // Directions and positions are in same order to mower. We add each directions/positions to
    // appropriate mower.
    mowers.forEach(m -> this.addToMower(m, groupedDirections, positions));

    // Returns result mower list with directions and positions.
    return mowers;
  }

  // ----------------------------------------------
  // Private method
  // ----------------------------------------------

  /**
   * Adds direction and position to {@link Mower}. Directions and positions are in order that
   * corresponds to mower ID. So we add each directions/positions to current mower by its ID.
   */
  private void addToMower(final Mower mower, final List<List<Direction>> groupedDirections,
      final List<Position> positions) {

    // Adds directions and position to mower by its ID.
    mower.setDirections(groupedDirections.get(mower.getId()));
    mower.setPosition(positions.get(mower.getId()));
  }
}
