package com.mowitnow.backend.exception;

/**
 * Exception that thrown when it exists application parameters errors.
 * 
 * @author Mazlum TOSUN
 */
public class ApplicationParamException extends Exception {

  /**
   * serialVersionUID.
   */
  private static final long serialVersionUID = -2726531034067868568L;

  // ----------------------------------------------
  // Constructors
  // ----------------------------------------------

  /**
   * Constructor without parameter.
   */
  public ApplicationParamException() {
    super();
  }

  /**
   * Constructor with message.
   * 
   * @param message message
   */
  public ApplicationParamException(String message) {
    super(message);
  }
}
