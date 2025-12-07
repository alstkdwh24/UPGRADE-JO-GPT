package com.geomin.project.gameContentService;



import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.geomin.project.command.DocumentVO;
import com.geomin.project.command.GameContentVO;
import com.geomin.project.command.UploadVO;
import com.geomin.project.util.Criteria;

@Mapper
public interface GameContentMapper {

	int regist(GameContentVO vo);
	
	// 파일 업로드(Insert)
    void registFile(UploadVO vo);
	
	// 게임 - 게임컨텐츠 조회 - 내용
    ArrayList<GameContentVO> getList(@Param("criteria") Criteria criteria);
	int getTotal();
	
	// 게임 컨텐츠 삭제 - del_check 값 N으로 변경
    int gameContentDelete(int game_no);
	
	// 게임 컨텐츠 - 삭제 이력 조회
    ArrayList<GameContentVO> delHistory(@Param("criteria") Criteria criteria);
	int getNoTotal();
	
	// 게임 수정
    GameContentVO gameList(int game_no);
	int gameUpdate(GameContentVO vo);
	void updateFile(UploadVO vo);
	
	
}
