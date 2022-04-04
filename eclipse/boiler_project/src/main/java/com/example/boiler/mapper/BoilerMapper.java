package com.example.boiler.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.boiler.test.entity.Member;

@Mapper
public interface BoilerMapper {
	
	@Select("select TIME, TEMP, STATUS from project_table order by TIME ASC")
	public List<Member> selectMemberList();
	
//	@Insert("insert into tbl_test (userid, username, age) values (\'${userid}\', \'${username}\', \'${age}\')")
//	public void insertMember(@Param("userid") String userid,
//			@Param("username") String username, @Param("age") int age );
	
	//@Update("update tbl_test set age = ${age} where userid=\'${userid}\'")
	
	@Update("update project_table set STATUS = replace(STATUS, \'${current_status}\' , \'${status}\' )")
	public void updateMember(@Param("current_status") String current_status  , @Param("status") String status);
	
	//update project_table SET TEMP = 30 WHERE 'TIME' = '00:00:00';
	//@Update("update project_table SET TEMP = \'${tmep}\' WHERE 'TIME' = \'${time}\'")
	@Update("UPDATE `project_table` SET TEMP=\'${tmep}\' WHERE  `TIME`=\'${time}\'")
	public void updateTemp(@Param("tmep") int tmep  , @Param("time") String time);
	
}
