package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.entity.Boiler;
import com.project.mapper.BoilerMapper;

@Controller
public class BoilerController {
	
@Autowired
	
	BoilerMapper mapper;
	
	@GetMapping("/member/memberList")
	
	public void listMember(Model model) {
		
		model.addAttribute("list", mapper.selectMemberList());
	}
	
	@GetMapping("/member/memberRegistry")
	public void getMemberRegistry() {
		
		
		
	}
	
	@GetMapping("/member/memberRegistry1")
	public String getMemberRegistry1(Boiler member) {
		
		mapper.insertMember(member.getUserid(), member.getUsername(), member.getAge());
		return "redirect:/member/memberList";				
//		return "redirect:/member/memberList";
		
	}
	
	
	
	@PostMapping("/member/memberRegistry")
	public String postMemberRegistry(Boiler member) {
		
		mapper.insertMember(member.getUserid(), member.getUsername(), member.getAge());
		return "redirect:/member/memberList";
		//return "redirect://www.naver.com";//이러면 네이버로 감
	}

}
