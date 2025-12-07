package com.geomin.project.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {
		public Integer user_no;
		public String user_id;
		public String user_pw;
		public String user_name;
		public String user_level;
		public String user_role;
		public String user_email;
		public String user_phone;
		public String user_birth;
		public String user_address;
		public String user_gender;
		public String user_sns_check;
		public String user_email_check;
		public String user_regDate;
		public String user_sleep;
		public String user_sleep_date;
		
	
}

	

