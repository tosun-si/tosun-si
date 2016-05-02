package com.mowitnow.backend.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mowitnow.backend.constant.MowitnowConstant;
import com.mowitnow.backend.domain.type.Direction;

/**
 * Mapper that allows to transform object that concerns {@link Direction}.
 * 
 * @author Mazlum TOSUN
 */
public enum DirectionMapper {

  // Single instance.
  INSTANCE;

  // ----------------------------------------------
  // Public methods
  // ----------------------------------------------

  /**
   * Allows to transform the given directions parameters to a result directions list. Each elements
   * of result list contains a direction list (list of list). Each direction list concerns a mower.
   * 
   * @param directionsParams directions parameters
   * @return list where each element contains {@link Direction} list
   */
  public List<List<Direction>> paramsToDirection(final String directionsParams) {

    // Build and returns result directions list.
    return Stream.of(directionsParams.split(MowitnowConstant.MOWERS_SEPARATOR))
        .map(this::toDirection).collect(Collectors.toList());
  }

  /**
   * Allows to transform the given directions parameter to {@link Direction} list.
   * 
   * @param directionsParam directions parameter
   * @return {@link Direction} direction list
   */
  private List<Direction> toDirection(final String directionsParam) {

    // Transforms directions parameters list to direction enumeration list.
    return directionsParam.chars().mapToObj(i -> (char) i).map(String::valueOf)
        .map(Direction::valueOf).collect(Collectors.toList());
  }
}
