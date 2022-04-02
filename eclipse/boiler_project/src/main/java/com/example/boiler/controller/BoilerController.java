package com.example.boiler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boiler.mapper.BoilerMapper;
import com.example.boiler.test.entity.Member;

@Controller
public class BoilerController {
	
	@Autowired
	BoilerMapper mapper;
	@GetMapping("/boiler/boilerList")// /templates �̸� /html �̸� �̶� ��ġ�ؾ� �մϴ�.
	public void listMember(Model model) {
		System.out.println(" run list html  !");
		model.addAttribute("list", mapper.selectMemberList());
	}
	
	@GetMapping("/boiler/boilerRegistry")
	public void getMemberRegistry() {
		
	}

}
