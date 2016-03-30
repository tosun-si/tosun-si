package com.mowitnow.backend.service;

import java.util.List;

import com.mowitnow.backend.domain.Mower;
import com.mowitnow.backend.dto.PositionFinalDto;

/**
 * Service that allows to do treatments on {@link Mower}.
 * 
 * @author Mazlum TOSUN
 */
public interface IMowerService {

  /**
   * Gets final positions of the all mowers that exists in application. Final position contains x/y
   * coordinate and orientation. A result list contains mower final positions and concerned mowers.
   * 
   * @return final {@link PositionFinalDto} list
   */
  List<PositionFinalDto> getFinalPositions();
}
