package com.geomin.project.GameService;
import com.geomin.project.command.PurchaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.geomin.project.command.GameContentVO;

@Service("gameService")
public class GameServiceImpl implements GameService {
	@Autowired
	@Qualifier("gameMapper")
    private final GameMapper gameMapper;

    public GameServiceImpl(GameMapper gameMapper) {
        this.gameMapper = gameMapper;
    }

    @Override
    public GameContentVO findGameContentById(Integer gameNo) {
        return gameMapper.findGameContentById(gameNo);
    }


}