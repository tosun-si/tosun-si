package com.mowitnow.backend.service;

import java.util.List;

import com.mowitnow.backend.domain.Mower;
import com.mowitnow.backend.domain.Position;
import com.mowitnow.backend.dto.PositionFinalDto;

/**
 * Service that allows to do treatments on {@link Mower}.
 * 
 * @author Mazlum TOSUN
 */
public interface IMowerService {

  /**
   * Gets final positions of the all mowers that exists in application. Final position contains x/y
   * coordinate and orientation. A result is put in map with [key-value] pair => [MOWER ID-LAST
   * POSITION].
   * 
   * @return final {@link Position} list in map with [key-value] pair => [MOWER ID-LAST POSITION]
   */
  List<PositionFinalDto> getFinalPositions();
}
