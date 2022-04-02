package com.example.boiler.android;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.boiler.test.entity.Member;
import com.example.boiler.mapper.BoilerMapper;

@RestController //member 와 view 중간에서 컨트롤 역활 mvc 패턴
public class BoilerAndroid {
	
	
	@Autowired //의존성 주입 DI : Dependency Injection
	BoilerMapper mapper;
	
	@GetMapping("/android/boilerList")
	List<Member> getMemberList(){
		
		return mapper.selectMemberList();
		
	}
	
	//http://10.10.141.76:8080/android/updateStatus/ON&OFF 이렇게 on , off 가능 전체 데이터 다 바꿀수 있게 잡음
	@GetMapping("/android/updateStatus/{current_status}&{status}")
	void getUpdateAge(@PathVariable("current_status")String current_status, @PathVariable("status")String status) { //db에 있는 데이터값 바꾸기
		mapper.updateMember(current_status, status);
	}

}
