package com.example.project_wizian2.controller;



import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project_wizian2.command.JobVO;
import com.example.project_wizian2.command.PageVO;
import com.example.project_wizian2.job.service.JobService;
import com.example.project_wizian2.util.Criteria;



@Controller
@RequestMapping("/job")
public class JobController {
	
	@Autowired
	@Qualifier("jobService")
	private JobService jobService;
	
	@GetMapping("/resume_co")
	public String getList(Model model,Criteria cri ) {
		ArrayList<JobVO> list=jobService.getList(cri);
		 int total = jobService.getTotal(cri); 
		 PageVO pageVO= new PageVO(cri, total);//페이지네이션
		
		model.addAttribute("list",list);
		model.addAttribute("pageVO",pageVO);
		System.out.println(pageVO.toString());
		return "job/resume_co";
	}
	
	
	
	
	@GetMapping("/resume_jobpost")
	public String good(){
		return "job/resume_jobpost";
	}
	
	@GetMapping("/resume_jobpost2")
	  public String gooddood(int prodd_id, Model model) {
		JobVO vo = jobService.select(prodd_id);
		model.addAttribute("vo", vo);
	    return "job/resume_jobpost2";
	}
	
	@GetMapping("/approve_hire/{prodd_id}")
    public String approvePost(@RequestParam("stu_id") String stu_id, @RequestParam("prodd_id") int prodd_id ) {
		jobService.applyCompany(stu_id, prodd_id);
		
        return "redirect:/user_stu/applyList_stu"; 
    }
	

    @GetMapping("/resume_ListJob")
    public String jobPost(int prodd_id, Model model) {
    	JobVO vo = jobService.select(prodd_id);
	    model.addAttribute("vo", vo);
		return "job/resume_ListJob";
		
		
	}
 

	
	@PostMapping("/registForm")
	public String regist(JobVO vo, RedirectAttributes ra) {
	    int result = jobService.regist(vo);
	    if(result == 1) {
	        ra.addFlashAttribute("msg", "정상적으로 처리하였습니다.");
	    } else {
	        ra.addFlashAttribute("msg", "음, 이건 아니에요.");
	        
	    }
	    return "redirect:/user_co/hirePost";
	    		
	}
	@PostMapping("/delete")
	public String delete(@RequestParam("prodd_id") int prodd_id, RedirectAttributes ra) {
	System.out.println(prodd_id);
		
	    jobService.delete(prodd_id); 
		return "redirect:/job/resume_co";
	}
	@PostMapping("/update")
	public String update(JobVO vo,
			 RedirectAttributes ra) {
		int result = jobService.update(vo);
		if(result == 1) {
		ra.addFlashAttribute("msg","업데이트 되었습니다");
		}else {
			ra.addFlashAttribute("msg", "수정에 실패하였습니다.");
		}
		return "redirect:/job/resume_co";
	}

}
