package data.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import data.dto.UserDto;

@Repository
public class UserDao extends SqlSessionDaoSupport implements UserDaoInter {
   //회원가입
   @Override
   public void insert(UserDto dto) {
      // TODO Auto-generated method stub
      getSqlSession().insert("insertOfUser", dto);
   }

   //이메일 중복 체크 
   @Override
   public int checkEmail(String user_email) {
      // TODO Auto-generated method stub
      return getSqlSession().selectOne("checkEmailOfUser", user_email);
   }

   //탈퇴
   @Override
   public void delete(int user_id) {
      // TODO Auto-generated method stub
      getSqlSession().delete("deleteOfUser", user_id);
   }
   @Override
   public void deleteUser(UserDto dto) {
      // TODO Auto-generated method stub
      getSqlSession().delete("updateOfUserPass", dto);
   }

   //회원정보 수정
   @Override
   public void update(UserDto dto) {
      // TODO Auto-generated method stub
      getSqlSession().update("updateOfUser", dto);
   }

   //회원 list
   @Override
   public List<UserDto> list() {
      // TODO Auto-generated method stub
      return getSqlSession().selectList("selectAllOfUser");
   }

   //getData
   @Override
   public UserDto getData(int user_id) {
      // TODO Auto-generated method stub
      return getSqlSession().selectOne("selectOneOfUser", user_id);
   }

   //로그인시 필요 - 아이디와 비번이 맞는지 체크
   @Override
   public int checkLogin(String user_email, String user_pass) {
      // TODO Auto-generated method stub
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("user_email", user_email);
      map.put("user_pass", user_pass);
      return getSqlSession().selectOne("checkPassOfUser", map);
   }

   //이메일로 user_id확인
   @Override
   public int getUserId(String user_email) {
      // TODO Auto-generated method stub
      return getSqlSession().selectOne("selectOfUserIdEmail", user_email);
   }

   //이메일로 member_type확인
   @Override
   public int getMemberType(String user_email) {
      // TODO Auto-generated method stub
      return getSqlSession().selectOne("selectOfMemberTypeEmail", user_email);
   }

//   //이메일 찾기
//   @Override
//   public String getEmail(String user_name, String user_hp) {
//      // TODO Auto-generated method stub
//      HashMap<String, String> map = new HashMap<String, String>();
//      map.put("user_email", user_name);
//      map.put("user_hp", user_hp);
//      return getSqlSession().selectOne("selectOfUserEmail", map);
//   }
   
   @Override
   public String getEmail(String user_name, String user_hp) {
      // TODO Auto-generated method stub
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("user_name", user_name);
      map.put("user_hp", user_hp);
      return getSqlSession().selectOne("selectOfUserEmail", map);
   }
   
   

   //비밀번호 찾기
   @Override
   public String getPass(String user_name, String user_email) {
      // TODO Auto-generated method stub
      HashMap<String, String> map = new HashMap<String, String>();
      map.put("user_name", user_name);
      map.put("user_email", user_email);
      return getSqlSession().selectOne("selectOfUserPass", map);
   }

   //비밀번호 수정
   @Override
   public void updatePass(UserDto dto) {
      // TODO Auto-generated method stub
      getSqlSession().update("updateOfUserPass", dto);
   }

}