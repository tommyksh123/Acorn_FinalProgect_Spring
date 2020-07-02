package data.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.dto.UserDto;
import data.service.UserServiceInter;

@RestController
@CrossOrigin
public class UserController {
   @Autowired
   private UserServiceInter service;
   
   //회원가입
   @PostMapping("/user/add")
   public String insert(@RequestBody UserDto dto) {
      service.insert(dto);
      return "user Insert Success";
   }
   
   //이메일 중복체크
   @PostMapping("/user/checkEmail")
   public boolean checkEmail(@RequestParam String user_email) {
      return service.checkEmail(user_email);
   }
   
   //탈퇴
   @DeleteMapping("/user/delete")
   public String delete(@RequestParam int user_id) {
      service.delete(user_id);
      return "user delete Success";
   }
   
   @DeleteMapping("/user/deleteUser")
   public String deleteUser(@RequestBody UserDto dto) {
      service.deleteUser(dto);
      return "user deleteUser Success";
   }
   
   //수정폼
   @GetMapping("/user/updateform")
   public UserDto updateform(@RequestParam int user_id) {
      System.out.println(user_id);
      UserDto dto=null;
      for(int i=0; i<service.list().size(); i++) {
         UserDto d = service.list().get(i);
         if(user_id==d.getUser_id()) {
            dto=d;
            break;
         }
      }
      return dto;
   }
   
   //수정
   @PostMapping("/user/update")
   public String update(@RequestBody UserDto dto) {
      service.update(dto);
      return "user update Success";
   }
   
   //비밀번호 수정
   @PostMapping("/user/updatePass")
   public String updatePass(@RequestBody UserDto dto) {
      service.updatePass(dto);
      return "user updatePass Success";
   }

   //이메일 찾기
     @PostMapping("/user/selectEmail")
     public String selectEmail(@RequestBody UserDto dto) {
        System.out.println("react>>email");
        String user=service.selectEmail(dto.getUser_name(), dto.getUser_hp());
        System.out.println("user="+user);
        return user;
     }
     
     //비밀번호 찾기
     @PostMapping("/user/selectPass")
     public String selectPass(@RequestBody UserDto dto) {
        System.out.println("react>>pass");
        String user=service.selectPass(dto.getUser_name(), dto.getUser_email());
        System.out.println("user="+user);
        return user;
     }
   
   //회원 list
   @PostMapping("/user/list")
   public List<UserDto> list(){
      List<UserDto> list = service.list();
      return list;
   }
   
   //getData
   @PostMapping("/user/getData")
   public UserDto getData(@RequestParam int user_id) {
      UserDto dto = service.getData(user_id);
      return dto;
   }
   
   //비밀번호 확인
   @PostMapping("/user/pass")
   public boolean getPass(@RequestParam int user_id, @RequestParam String user_pass) {
      String pass = service.getData(user_id).getUser_pass();
      if(pass.equals(user_pass))
         return true;
      else
         return false;
   }
      
   @PostMapping("/login/user")
   public HashMap<String, Object> login(@RequestParam String user_email, @RequestParam String user_pass) {
 
      HashMap<String, Object> map = new HashMap<String, Object>();
      
      if(service.checkLogin(user_email, user_pass)==true) {
         map.put("user_id", service.checkUserId(user_email));
         map.put("member_type", service.checkMemberType(user_email));
      }      
      return map;
   }  
}