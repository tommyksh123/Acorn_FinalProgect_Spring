package data.service;

import java.util.List;

import data.dto.UserDto;

public interface UserServiceInter {
   //회원가입
      public void insert(UserDto dto);
      
      //이메일 중복 체크 
      public boolean checkEmail(String user_email);
      
      //이메일 찾기
      public String selectEmail(String user_name, String user_hp);
      
      //비밀번호 찾기
      public String selectPass(String user_name, String user_email);
      
      //탈퇴
      public void delete(int user_id);
      
      //탈퇴
      public void deleteUser(UserDto dto);
      
      //회원정보 수정
      public void update(UserDto dto);
      
      //비밀번호 수정
      public void updatePass(UserDto dto);
      
      //회원 list
      public List<UserDto> list();
      
      //getData
      public UserDto getData(int user_id);
      
      //로그인시 필요 - 아이디와 비번이 맞는지 체크
      public boolean checkLogin(String user_email, String user_pass);
      
      //이메일로 user_id확인
      public int checkUserId(String user_email);
      
      //이메일로 member_type확인
      public int checkMemberType(String user_email);
}