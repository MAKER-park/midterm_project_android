package com.project.android;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Boiler;
import com.project.mapper.BoilerMapper;

@RestController //member �� view �߰����� ��Ʈ�� ��Ȱ mvc ����
public class BoilerAndroid {
	
	@Autowired //������ ���� DI : Dependency Injection
	BoilerMapper mapper;
	
	@GetMapping("/android/BoilerList")
	List<Boiler> getMemberList(){
		return mapper.selectMemberList();
		
	}
	
	@GetMapping("/android/updateAge/{age}")
	void getUpdateAge(@PathVariable("age")int age) { //db�� �ִ� �����Ͱ� �ٲٱ�
		
		String userid = "ab";
		mapper.updateMember(age, userid);
	}

}
