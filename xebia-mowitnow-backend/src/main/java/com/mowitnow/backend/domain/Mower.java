package com.mowitnow.backend.domain;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.ImmutableMap.Builder;
import com.mowitnow.backend.domain.type.Direction;

/**
 * Object that contains mower data.
 * 
 * @author Mazlum TOSUN
 */
public class Mower implements Serializable {

  /**
   * SerialVersionUID.
   */
  private static final long serialVersionUID = 7783020622800159143L;

  // ----------------------------------------------
  // Fields
  // ----------------------------------------------

  private Integer id;
  private Position position;
  private List<Direction> directions;

  // ----------------------------------------------
  // Constructor
  // ----------------------------------------------

  /**
   * Constructor with builder.
   * 
   * @param builder current {@link Builder}
   */
  public Mower(final Integer id) {
    this.id = id;
  }

  // ----------------------------------------------
  // Getters/setters
  // ----------------------------------------------

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public List<Direction> getDirections() {
    return directions;
  }

  public void setDirections(List<Direction> directions) {
    this.directions = directions;
  }
}
