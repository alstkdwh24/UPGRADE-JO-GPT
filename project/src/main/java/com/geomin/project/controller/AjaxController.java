package com.geomin.project.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geomin.project.command.PurchaseVO;
import com.geomin.project.document.service.DocumentService;
import com.geomin.project.gameContentService.GameContentService;
import com.geomin.project.user.service.UserService;

@RestController
public class AjaxController {

	@Autowired
	@Qualifier("GameContentService")
	private GameContentService gameContentService;
	
	@Autowired
	@Qualifier("DocumentService")
	private DocumentService documentService;
	
	@Autowired
	private UserService userService;
	
	// 파일 업로드 경로
	@Value("${project.upload.path}")
	private String uploadPath;
	// 이미지 표출
	@GetMapping("/display/{filepath}/{uuid}/{filename}")
	public ResponseEntity<byte[]> display(@PathVariable("filepath") String filepath,
										  @PathVariable("uuid") String uuid,
										  @PathVariable("filename") String filename){
		
		ResponseEntity<byte[]> entity = null;
		try {
			// 로컬에 있는 파일데이터 byte 정보
			String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
			File file = new File(savePath);
			
			// 데이터
			byte[] arr = FileCopyUtils.copyToByteArray(file);
			
			// 헤더
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			
			entity = new ResponseEntity<>(arr, header ,HttpStatus.OK);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return entity;
	}
	

	@PostMapping("/deleteSelectedGames")
    public ResponseEntity<String> deleteSelectedGames(@RequestBody Map<String, List<String>> request) {
        List<String> selectedGames = request.get("selectedGames");
        // 선택된 게임들을 삭제하는 서비스 메서드 호출
        int game_no = Integer.parseInt(selectedGames.get(0));
        gameContentService.gameContentDelete(game_no);
        return new ResponseEntity<>("선택된 게임 삭제 완료", HttpStatus.OK);
    }
	
	@PostMapping("/deleteSelectedLearning")
    public ResponseEntity<String> deleteSelectedLearning(@RequestBody Map<String, List<String>> request) {
        List<String> selectedlearns = request.get("selectedlearns");
        int docu_no = Integer.parseInt(selectedlearns.get(0));
        documentService.learnContentDelete(docu_no);
        return new ResponseEntity<>("선택된 게임 삭제 완료", HttpStatus.OK);
    }
	
	
	
	@GetMapping("/priceLookDetail")
	public ArrayList<PurchaseVO> priceLookDetail(@RequestParam("purchase_date") String purchase_date) {
		
		System.out.println(purchase_date);
		ArrayList<PurchaseVO> list = userService.getPurchaseDetail(purchase_date);
		
		return list;
	}
}
