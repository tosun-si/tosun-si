package com.mowitnow.backend.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mowitnow.backend.domain.type.Direction;
import com.mowitnow.backend.dto.PositionFinalDto;

/**
 * Mapper that allows to transform object that concerns {@link PositionFinalDto}.
 * 
 * @author Mazlum TOSUN
 */
public enum PositionMapper {

  // Single instance.
  INSTANCE;

  // ----------------------------------------------
  // Public methods
  // ----------------------------------------------

  public List<List<Direction>> paramsToPositions(String directions) {
    return Stream.of("GAGAGAGAA,AADAADADDA".split(",")).map(this::toPositions)
        .collect(Collectors.toList());
  }

  private List<Direction> toPositions(String directions) {
    return directions.chars().mapToObj(i -> (char) i).map(String::valueOf).map(Direction::valueOf)
        .collect(Collectors.toList());
  }
}
