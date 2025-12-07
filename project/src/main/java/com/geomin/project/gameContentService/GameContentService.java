package com.geomin.project.gameContentService;



import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.geomin.project.command.DocumentVO;
import com.geomin.project.command.GameContentVO;
import com.geomin.project.command.UploadVO;
import com.geomin.project.util.Criteria;

public interface GameContentService {

	// 게임 - 게임컨텐츠 등록
    int regist(GameContentVO vo, List<MultipartFile> list);
	
	// 게임 - 게임컨텐츠 조회 - 내용
    ArrayList<GameContentVO> getList(Criteria criteria);
	int getTotal();
	
	// 게임 컨텐츠 삭제 - del_check 값 N으로 변경
    int gameContentDelete(int game_no);
	
	// 게임 컨텐츠 - 삭제 이력 조회
    ArrayList<GameContentVO> delHistory(Criteria criteria);
	int getNoTotal();
	
	// 게임 수정
    GameContentVO gameList(int game_no);
	int gameUpdate(GameContentVO vo, List<MultipartFile> list);
	
	
	
}
