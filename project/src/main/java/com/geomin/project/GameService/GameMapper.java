package com.geomin.project.GameService;
import com.geomin.project.command.PurchaseVO;
import org.apache.ibatis.annotations.Mapper;
import com.geomin.project.command.GameContentVO;

@Mapper
public interface GameMapper {
    GameContentVO findGameContentById(Integer gameNo);

}