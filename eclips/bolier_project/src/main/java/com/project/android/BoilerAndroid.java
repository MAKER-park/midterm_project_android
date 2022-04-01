package com.project.android;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Boiler;
import com.project.mapper.BoilerMapper;

@RestController //member 와 view 중간에서 컨트롤 역활 mvc 패턴
public class BoilerAndroid {
	
	@Autowired //의존성 주입 DI : Dependency Injection
	BoilerMapper mapper;
	
	@GetMapping("/android/BoilerList")
	List<Boiler> getMemberList(){
		return mapper.selectMemberList();
		
	}
	
	@GetMapping("/android/updateAge/{age}")
	void getUpdateAge(@PathVariable("age")int age) { //db에 있는 데이터값 바꾸기
		
		String userid = "ab";
		mapper.updateMember(age, userid);
	}

}
