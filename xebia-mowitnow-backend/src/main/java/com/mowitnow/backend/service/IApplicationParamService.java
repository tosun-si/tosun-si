package com.mowitnow.backend.service;

/**
 * Service that allows to get application parameters. These parameters allows to build mowers.
 * 
 * @author Mazlum TOSUN
 */
public interface IApplicationParamService {

  /**
   * Get directions parameters.
   * 
   * @return {@link String} directions parameters
   */
  String getDirections();

  /**
   * Get positions parameters.
   * 
   * @return {@link String} positions parameters
   */
  String getPosition();

  /**
   * Get expected result positions parameters.
   * 
   * @return {@link String} expected result positions parameters
   */
  String getExpectedPositions();
}
