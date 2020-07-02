package data.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import data.dto.ReviewDto;
import data.service.ReviewServiceInter;
import data.service.UserServiceInter;
import upload.util.SpringFileWrite;

@RestController
@CrossOrigin
public class ReviewController {

	@Autowired
	ReviewServiceInter service;
	
	@Autowired
	UserServiceInter service2;
	
	//이미지 파일명
	String saveImagename;	
	
	@RequestMapping(value = "/review/reviewFile", consumes = {"multipart/form-data"}, method = RequestMethod.POST)
	public String fileUpload(MultipartHttpServletRequest request, @RequestParam MultipartFile uploadFile, @RequestParam int user_id, @RequestParam int product_id) {
		
		System.out.println("review>>reviewImage>>"+uploadFile.getOriginalFilename());
		
		//업로드할 경로구하기
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/image/reviewImage");
		System.out.println("reviewImage path="+path);
		
		//실제 폴더에 이미지 저장
		SpringFileWrite sfw = new SpringFileWrite();
		saveImagename=  Integer.toString(user_id)+"_"+Integer.toString(product_id)+"_"+uploadFile.getOriginalFilename();
		
		sfw.writeFileRename(uploadFile, path, saveImagename);
		
		return saveImagename; //파일명 리턴
	}
	
	//리뷰 등록
	@PostMapping("/reivew/add")
	public String insert(HttpServletRequest request, @RequestBody ReviewDto dto) {
		
		dto.setReview_img(saveImagename);
		service.insert(dto);
		return "review Insert Success";
	}
	
	//리뷰 삭제
	@PostMapping("/review/delete")
	public String delete(HttpServletRequest request, @RequestParam int review_id) {

		//이미지 경로
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/image/reviewImage");
		//이미지 파일명
		System.out.println(path);
		String photoname = service.getData(review_id).getReview_img();
		//이미지가 존재할경우 삭제
		File file = new File(path+"\\"+photoname);
		if(file.exists())
			file.delete();
		
		service.delete(review_id);
		return "review Delete Success";
	} 
	
	//리뷰 조회
	@GetMapping("/review/list")
	public List<ReviewDto> list(@RequestParam int product_id){
		
		List<ReviewDto> list = service.list(product_id);

		
		for(int i=0;i<list.size();i++)
		{
			int num = list.get(i).getUser_id();
			String name = service2.getData(num).getUser_name();
			String name2 = name.substring(0, 1);
			String name3 = name2+"**";
			list.get(i).setUser_name(name3);
			
			String date = list.get(i).getReview_date();
			String date2 =  date.substring(0, 10);
			list.get(i).setReview_date(date2);
			
		}
		
		return list;
	}
	
	//getData
	@GetMapping("/review/getData")
	public ReviewDto getData(@RequestParam int review_id) {
		ReviewDto dto = service.getData(review_id);
		return dto;
	}
}
