package com.geomin.project.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressVO {
	
	public String sg_no;
	public String user_no;
	public Integer homework_total_point;

	public Integer progress_percentage;

	
}
