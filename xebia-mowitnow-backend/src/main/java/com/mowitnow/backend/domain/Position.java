package com.mowitnow.backend.domain;

import java.io.Serializable;

import com.mowitnow.backend.domain.type.Orientation;

/**
 * Object that contains mower position data.
 * 
 * @author Mazlum TOSUN
 */
public class Position implements Serializable {

  /**
   * SerialVersionUID.
   */
  private static final long serialVersionUID = 6431761550758241265L;

  // ----------------------------------------------
  // Fields
  // ----------------------------------------------

  private int coordinateX;
  private int coordinateY;
  private Orientation orientation;

  // ----------------------------------------------
  // Constructors
  // ----------------------------------------------

  /**
   * Constructor without parameter.
   */
  public Position() {
    // An empty constructor.
  }

  /**
   * Constructor with parameters.
   * 
   * @param coordinateX x coordinate
   * @param coordinateY y coordinate
   * @param orientation orientation
   */
  public Position(final int coordinateX, final int coordinateY, final Orientation orientation) {
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
    this.orientation = orientation;
  }

  // ----------------------------------------------
  // Getters/setters
  // ----------------------------------------------

  public int getCoordinateX() {
    return coordinateX;
  }

  public void setCoordinateX(int coordinateX) {
    this.coordinateX = coordinateX;
  }

  public void setCoordinateX(final String coordinateX) {
    this.setCoordinateX(Integer.valueOf(coordinateX));
  }

  public int getCoordinateY() {
    return coordinateY;
  }

  public void setCoordinateY(int coordinateY) {
    this.coordinateY = coordinateY;
  }

  public void setCoordinateY(final String coordinateY) {
    this.setCoordinateY(Integer.valueOf(coordinateY));
  }

  public Orientation getOrientation() {
    return orientation;
  }

  public void setOrientation(Orientation orientation) {
    this.orientation = orientation;
  }

  public void setOrientation(final String orientation) {
    this.setOrientation(Orientation.valueOf(orientation));
  }
}
