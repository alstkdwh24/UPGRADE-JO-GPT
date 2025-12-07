package com.geomin.project.command;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadVO {
	
	private Integer file_no;
	private String fileName;  // 작성자가 업로드한 파일명
	private String filepath;
	private String uuid;
	private Integer game_no; // fk
	private String game_title; // fk
	
	
	
}
