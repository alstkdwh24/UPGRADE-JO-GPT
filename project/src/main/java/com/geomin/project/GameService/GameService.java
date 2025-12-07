package com.geomin.project.GameService;


import com.geomin.project.command.GameContentVO;
import com.geomin.project.command.PurchaseVO;

public interface GameService {
    GameContentVO findGameContentById(Integer gameNo);

}
