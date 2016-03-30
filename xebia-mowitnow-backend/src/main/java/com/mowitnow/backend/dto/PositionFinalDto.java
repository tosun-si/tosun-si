package com.mowitnow.backend.dto;

import java.io.Serializable;

import com.mowitnow.backend.domain.Mower;
import com.mowitnow.backend.domain.Position;

/**
 * Object that contains mower final position and mower that concerned by this position.
 * 
 * @author Mazlum TOSUN
 */
public class PositionFinalDto implements Serializable {

  /**
   * SerialVersionUID.
   */
  private static final long serialVersionUID = 7783020622800159143L;

  // ----------------------------------------------
  // Fields
  // ----------------------------------------------

  private final Mower mower;
  private final Position position;

  // ----------------------------------------------
  // Constructor
  // ----------------------------------------------

  /**
   * Constructor with builder.
   * 
   * @param builder current {@link Builder}
   */
  private PositionFinalDto(final Builder builder) {
    this.mower = builder.mower;
    this.position = builder.position;
  }

  // ----------------------------------------------
  // Getters/setters
  // ----------------------------------------------

  public Mower getMower() {
    return mower;
  }

  public Position getPosition() {
    return position;
  }

  /**
   * Builder of {@link PositionFinalDto}.
   */
  public static class Builder {

    // ----------------------------------------------
    // Optional fields
    // ----------------------------------------------

    private Mower mower;
    private Position position;

    // ----------------------------------------------
    // Constructor
    // ----------------------------------------------

    /**
     * Constructor with required fields.
     * 
     * @param id mower ID
     */
    public Builder() {
      // An Empty constructor.
    }

    // ----------------------------------------------
    // Builder methods
    // ----------------------------------------------

    public Builder mower(final Mower mower) {
      this.mower = mower;
      return this;
    }

    public Builder position(final Position position) {
      this.position = position;
      return this;
    }

    /**
     * Build {@link PositionFinalDto} by {@link Builder}.
     * 
     * @return mower
     */
    public PositionFinalDto build() {
      return new PositionFinalDto(this);
    }
  }
}
