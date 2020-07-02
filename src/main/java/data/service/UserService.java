package data.service;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dao.UserDaoInter;
import data.dto.UserDto;

@Service
public class UserService implements UserServiceInter {
   
   @Autowired
   UserDaoInter dao;
   
   //회원가입
   @Override
   public void insert(UserDto dto) {
      // TODO Auto-generated method stub
      dao.insert(dto);
   }

   //이메일 중복 체크 
   @Override
   public boolean checkEmail(String user_email) {
      // TODO Auto-generated method stub
      return dao.checkEmail(user_email)==0?false:true;
   }

   //탈퇴
   @Override
   public void delete(int user_id) {
      // TODO Auto-generated method stub
      dao.delete(user_id);
   }
   @Override
   public void deleteUser(UserDto dto) {
      // TODO Auto-generated method stub
      dao.deleteUser(dto);
   }

   //회원정보 수정
   @Override
   public void update(UserDto dto) {
      // TODO Auto-generated method stub
      dao.update(dto);
   }

   //회원 list
   @Override
   public List<UserDto> list() {
      // TODO Auto-generated method stub
      return dao.list();
   }

   //getData
   @Override
   public UserDto getData(int user_id) {
      // TODO Auto-generated method stub
      return dao.getData(user_id);
   }

   //로그인시 필요 - 아이디와 비번이 맞는지 체크
   @Override
   public boolean checkLogin(String user_email, String user_pass) {
      // TODO Auto-generated method stub
      return dao.checkLogin(user_email, user_pass)==0?false:true;
   }

   //이메일로 user_id확인
   @Override
   public int checkUserId(String user_email) {
      // TODO Auto-generated method stub
      return dao.getUserId(user_email);
   }

   //이메일로 member_type확인
   @Override
   public int checkMemberType(String user_email) {
      // TODO Auto-generated method stub
      return dao.getMemberType(user_email);
   }

   //이메일 찾기
   @Override
   public String selectEmail(String user_name, String user_hp) {
      // TODO Auto-generated method stub
      return dao.getEmail(user_name, user_hp);
   }

   //비밀번호 찾기
   @Override
   public String selectPass(String user_name, String user_email) {
      // TODO Auto-generated method stub
      return dao.getPass(user_name, user_email);
   }

   //비밀번호 수정
   @Override
   public void updatePass(UserDto dto) {
      // TODO Auto-generated method stub
      dao.updatePass(dto);
   }
}