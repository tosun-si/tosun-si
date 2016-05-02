package com.mowitnow.backend.mapper;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableMap;
import com.mowitnow.backend.constant.MowitnowConstant;
import com.mowitnow.backend.domain.Position;
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

  /**
   * Allows to transform the given positions parameters to {@link Position} list.
   * 
   * @param positionParams position parameters
   * @return {@link Position} list
   */
  public List<Position> paramsToPositions(final String positionParams) {

    // Transforms the given position parameters to position objects.
    return Stream.of(positionParams.split(MowitnowConstant.MOWERS_SEPARATOR)).map(this::toPositions)
        .collect(Collectors.toList());
  }

  // ----------------------------------------------
  // Private methods
  // ----------------------------------------------

  /**
   * Allows to transform the given position string (parameter) to {@link Position}.
   * 
   * @param positionParam current positions
   * @return {@link Position} position
   */
  private Position toPositions(final String positionParam) {

    // Initializes an atomic index.
    final AtomicInteger index = new AtomicInteger(0);

    // Initializes position to create.
    final Position position = new Position();

    // Iterates overs all characters of current position. First character corresponds to X
    // coordinate, the second corresponds to Y coordinate and the last corresponds to orientation.
    // Consumers based on character index, allow to call the appropriate setter of person to create.
    positionParam.chars().mapToObj(i -> (char) i).map(String::valueOf)
        .forEach(p -> this.getPositionConsumers(position).get(index.incrementAndGet()).accept(p));

    // Returns created position.
    return position;
  }

  /**
   * Allows to associate consumers to index. According to the index, a good setter of the given
   * {@link Position}, will be called.<br>
   * First character corresponds to X // coordinate, the second corresponds to Y coordinate and the
   * last corresponds to orientation.<br>
   * The format of result is [KEY-VALUE] pair => [INDEX-POSITION CONSUMER].
   * 
   * @param position current position
   * @return map with the following format [KEY-VALUE] pair => [INDEX-POSITION CONSUMER]
   */
  private Map<Integer, Consumer<String>> getPositionConsumers(final Position position) {

    // Returns consumers associated to index.
    return new ImmutableMap.Builder<Integer, Consumer<String>>().put(1, position::setCoordinateX)
        .put(2, position::setCoordinateY).put(3, position::setOrientation).build();
  }
}
