package com.mowitnow.backend.domain.type;

import com.google.common.collect.ImmutableMap;
import com.mowitnow.backend.domain.Position;

/**
 * Contains all possibles mower orientations (N, E, W, S).
 * 
 * @author Mazlum TOSUN
 */
public enum Orientation {

  // ----------------------------------------------
  // Enumeration
  // ----------------------------------------------

  N {
    @Override
    public Position getNewPosition(final Direction direction, final int coordinateX,
        final int coordinateY) {

      // Build and returns a map with all possible positions after applying the given direction.
      return new ImmutableMap.Builder<Direction, Position>()
          .put(Direction.G, new Position(coordinateX, coordinateY, Orientation.W))
          .put(Direction.D, new Position(coordinateX, coordinateY, Orientation.E))
          .put(Direction.A, new Position(coordinateX, coordinateY + 1, Orientation.N)).build()
          .get(direction);
    }
  },
  E {
    @Override
    public Position getNewPosition(final Direction direction, final int coordinateX,
        final int coordinateY) {

      // Build and returns a map with all possible positions after applying the given direction.
      return new ImmutableMap.Builder<Direction, Position>()
          .put(Direction.G, new Position(coordinateX, coordinateY, Orientation.N))
          .put(Direction.D, new Position(coordinateX, coordinateY, Orientation.S))
          .put(Direction.A, new Position(coordinateX + 1, coordinateY, Orientation.E)).build()
          .get(direction);
    }
  },
  W {
    @Override
    public Position getNewPosition(final Direction direction, final int coordinateX,
        final int coordinateY) {

      // Build and returns a map with all possible positions after applying the given direction.
      return new ImmutableMap.Builder<Direction, Position>()
          .put(Direction.G, new Position(coordinateX, coordinateY, Orientation.S))
          .put(Direction.D, new Position(coordinateX, coordinateY, Orientation.N))
          .put(Direction.A, new Position(coordinateX - 1, coordinateY, Orientation.W)).build()
          .get(direction);
    }
  },
  S {
    @Override
    public Position getNewPosition(final Direction direction, final int coordinateX,
        final int coordinateY) {

      // Build and returns a map with all possible positions after applying the given direction.
      return new ImmutableMap.Builder<Direction, Position>()
          .put(Direction.G, new Position(coordinateX, coordinateY, Orientation.E))
          .put(Direction.D, new Position(coordinateX, coordinateY, Orientation.W))
          .put(Direction.A, new Position(coordinateX, coordinateY - 1, Orientation.S)).build()
          .get(direction);
    }
  };

  // ----------------------------------------------
  // Abstract method
  // ----------------------------------------------

  /**
   * Allows from the given direction and x/y coordinates, to build and returns new result position.
   * A {@link Position} contains new orientation (N,E,W,S) and new x/y coordinates.
   * 
   * @param direction direction
   * @param coordinateX x coordinate
   * @param coordinateY y coordinate
   * @return {@link Position} result position
   */
  public abstract Position getNewPosition(final Direction direction, final int coordinateX,
      final int coordinateY);
}
