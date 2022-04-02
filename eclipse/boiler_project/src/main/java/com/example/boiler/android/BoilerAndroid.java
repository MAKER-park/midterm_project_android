package com.example.boiler.android;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.boiler.test.entity.Member;
import com.example.boiler.mapper.BoilerMapper;

@RestController //member �� view �߰����� ��Ʈ�� ��Ȱ mvc ����
public class BoilerAndroid {
	
	
	@Autowired //������ ���� DI : Dependency Injection
	BoilerMapper mapper;
	
	@GetMapping("/android/boilerList")
	List<Member> getMemberList(){
		
		return mapper.selectMemberList();
		
	}
	
	//http://10.10.141.76:8080/android/updateStatus/ON&OFF �̷��� on , off ���� ��ü ������ �� �ٲܼ� �ְ� ����
	@GetMapping("/android/updateStatus/{current_status}&{status}")
	void getUpdateAge(@PathVariable("current_status")String current_status, @PathVariable("status")String status) { //db�� �ִ� �����Ͱ� �ٲٱ�
		mapper.updateMember(current_status, status);
	}

}
