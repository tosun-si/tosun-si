package com.mowitnow.backend.domain;

import java.io.Serializable;
import java.util.List;

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

  private final Integer id;
  private final String name;
  private final Position position;
  private final List<Direction> directions;

  // ----------------------------------------------
  // Constructor
  // ----------------------------------------------

  /**
   * Constructor with builder.
   * 
   * @param builder current {@link Builder}
   */
  private Mower(final Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.position = builder.position;
    this.directions = builder.directions;
  }

  // ----------------------------------------------
  // Getters
  // ----------------------------------------------

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Position getPosition() {
    return position;
  }

  public List<Direction> getDirections() {
    return directions;
  }

  /**
   * Builder of {@link Mower}.
   */
  public static class Builder {

    // ----------------------------------------------
    // Required fields
    // ----------------------------------------------

    private Integer id;

    // ----------------------------------------------
    // Optional fields
    // ----------------------------------------------

    private String name;
    private Position position;
    private List<Direction> directions;

    // ----------------------------------------------
    // Constructor
    // ----------------------------------------------

    /**
     * Constructor with required fields.
     * 
     * @param id mower ID
     */
    public Builder(Integer id) {
      this.id = id;
    }

    // ----------------------------------------------
    // Builder methods
    // ----------------------------------------------

    public Builder name(final String name) {
      this.name = name;
      return this;
    }

    public Builder position(final Position position) {
      this.position = position;
      return this;
    }

    public Builder directions(final List<Direction> directions) {
      this.directions = directions;
      return this;
    }


    /**
     * Build {@link Mower} by {@link Builder}.
     * 
     * @return mower
     */
    public Mower build() {
      return new Mower(this);
    }
  }
}
