package com.geomin.project.controller;

import com.geomin.project.cart.service.CartServiceImpl;
import com.geomin.project.command.PurchaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.geomin.project.GameService.GameServiceImpl;
import com.geomin.project.command.GameContentVO;


@RestController
public class GameController {
	@Autowired
	@Qualifier("gameService")
    private final GameServiceImpl gameService;

    @Autowired
    @Qualifier("CartServiceImpl")
    private final CartServiceImpl CartServiceImpl;
    
    public <GameService> GameController(GameServiceImpl gameService, com.geomin.project.cart.service.CartServiceImpl cartServiceImpl) {
        this.gameService = gameService;
        this.CartServiceImpl = cartServiceImpl;
    }

    @GetMapping("/api/games/{gameNo}")
    public ResponseEntity<GameContentVO> getGameContent(@PathVariable Integer gameNo) {
        GameContentVO gameContent = gameService.findGameContentById(gameNo);
        if(gameContent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gameContent);
    }
    @PostMapping ("/GameResist")
    public ResponseEntity<PurchaseVO> getpurphase(@RequestBody PurchaseVO vo) {
        int purchase = CartServiceImpl.purchase_resist(vo);
        if (purchase == 1 ) {
            return new ResponseEntity<>(vo, HttpStatus.ACCEPTED);
        }else{
            return null;
        }
    }
}
