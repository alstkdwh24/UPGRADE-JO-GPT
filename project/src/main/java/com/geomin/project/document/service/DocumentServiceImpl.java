package com.geomin.project.document.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.geomin.project.command.DocumentUploadVO;
import com.geomin.project.command.DocumentVO;
import com.geomin.project.command.UploadVO;
import com.geomin.project.util.Criteria;


@Service("DocumentService")
public class DocumentServiceImpl implements DocumentService{

	@Autowired
	private DocumentMapper documentMapper;
	
	@Value("${project.upload.path}")
	private String uploadPath;
	
	public String makeFolder() {
		String filePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		File file = new File(uploadPath + "/" + filePath);
		if(!file.exists()) { // 해당 파일이 있으면 true
			file.mkdirs();
		}
		return filePath;
	}
	
	// 학습자료 등록
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int regist(DocumentVO vo, List<MultipartFile> list) {
		int result = documentMapper.regist(vo);
				
		// MultipartFile 비어있으면, 필터링
		list = list.stream().filter(m -> !m.isEmpty()).collect(Collectors.toList());
				
		for(MultipartFile file : list) {
		
		// 1. 파일 가져오기 및 파싱
		String filename = file.getOriginalFilename();
		filename = filename.substring(filename.lastIndexOf("\\") + 1);
		// 2. 파일 사이즈
		long size = file.getSize();
	
		// 동일한 파일로 업로드가 되면, 기존 파일이 덮어지기 때문에, 랜덤한 이름을 이용해서 파일 명칭 바꿈
		String uuid = UUID.randomUUID().toString();
	
		String filepath = makeFolder();
		
		// 3. 업로드 할 경로
		String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename; 
		
		System.out.println("파일명 : " + filename); // DB에 원본 파일명 저장
		System.out.println("폴더명 : " + filepath); // DB에 폴더명 저장
		System.out.println("랜덤이름 : " + uuid); // DB 저장
		System.out.println("파일 사이즈 : " + size);
		System.out.println("업로드 할 경로 : " + savePath);
		
		
		try {
			File saveFile = new File(savePath);
			file.transferTo(saveFile);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		// 업로드 이후에 데이터베이스에 경로를 저장
		// prod_id 는 service 영역에서 구할 수 있는 방법이 없다.
		documentMapper.registFile(DocumentUploadVO.builder().fileName(filename)
															.filepath(filepath)
															.uuid(uuid)
															.docu_title(vo.getDocu_title()).build());	
													   
													   
		}
		
		
		return result;
	}
	
	
	// 학습 - 학습자료 조회 - 내용
	@Override
	public ArrayList<DocumentVO> getList(Criteria criteria) {
		
		return documentMapper.getList(criteria);
	}

	// 학습 - 학습자료 삭제 
	@Override
	public int learnContentDelete(int docu_no) {
		
		return documentMapper.learnContentDelete(docu_no);
	}

	// 학습 - 학습자료 삭제 이력
	@Override
	public ArrayList<DocumentVO> delList(Criteria criteria) {
		
		return documentMapper.delList(criteria);
	}

	@Override
	public int getTotal() {
		return documentMapper.getTotal();
	}


	@Override
	public int getNoTotal() {
		
		return documentMapper.getNoTotal();
	}

	@Override
	public DocumentVO docuList(int docu_no) {
		
		return documentMapper.docuList(docu_no);
	}

	@Override
	public int docuUpdate(DocumentVO vo, List<MultipartFile> list) {
		int result = documentMapper.docuUpdate(vo);
		list = list.stream().filter(m -> !m.isEmpty()).collect(Collectors.toList());
		for(MultipartFile file : list) {
			
			// 1. 파일 가져오기 및 파싱
			String filename = file.getOriginalFilename();
			filename = filename.substring(filename.lastIndexOf("\\") + 1);
			// 2. 파일 사이즈
			long size = file.getSize();
		
			// 동일한 파일로 업로드가 되면, 기존 파일이 덮어지기 때문에, 랜덤한 이름을 이용해서 파일 명칭 바꿈
			String uuid = UUID.randomUUID().toString();
		
			String filepath = makeFolder();
			
			// 3. 업로드 할 경로
			String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename; 
			
			System.out.println("파일명 : " + filename); // DB에 원본 파일명 저장
			System.out.println("폴더명 : " + filepath); // DB에 폴더명 저장
			System.out.println("랜덤이름 : " + uuid); // DB 저장
			System.out.println("파일 사이즈 : " + size);
			System.out.println("업로드 할 경로 : " + savePath);
			
			
			try {
				File saveFile = new File(savePath);
				file.transferTo(saveFile);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			// 업로드 이후에 데이터베이스에 경로를 저장
			// prod_id 는 service 영역에서 구할 수 있는 방법이 없다.
			documentMapper.updateFile(DocumentUploadVO.builder().fileName(filename)
														   .filepath(filepath)
														   .uuid(uuid)
														   .docu_no(vo.getDocu_no()).build()
														   );											   
			}
		return result;
	}

	
	
	
}
