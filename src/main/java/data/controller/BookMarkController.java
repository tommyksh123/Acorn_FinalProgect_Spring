package data.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.dto.BookMarkDto;
import data.dto.ProductDto;
import data.service.BookMarkServiceInter;

@RestController
@CrossOrigin
public class BookMarkController {

	@Autowired
	private BookMarkServiceInter service;
	
	//북마크 추가
	@PostMapping("/bookmark/insertOfBookMark")
	public void insertOfBookMark(@RequestBody BookMarkDto dto)
	{
		service.insertOfBookMark(dto);
		System.out.println("/bookmark/insertOfBookMark --> Success");
	}
	
	
	//북마크 삭제
	@DeleteMapping("/bookmark/deleteOfBookMark")
	public void deleteOfBookMark(@RequestParam int user_id, @RequestParam int product_id)
	{
		
		BookMarkDto dto = new BookMarkDto();
		dto.setUser_id(user_id);
		dto.setProduct_id(product_id);
		service.deleteOfBookMark(dto);
		System.out.println("/bookmark/deleteOfBookMark --> Success");
	}
	
	
	//북마크 전체 삭제
	@PostMapping("/bookmark/delefeOfBookMarkAll")
	public void delefeOfBookMarkAll(@RequestParam String bookmarks, @RequestParam int user_id )
	{
		System.out.println(bookmarks);
		String[] book = bookmarks.split(",");
		for(int i = 0; i< book.length;i++)
		{
			System.out.println(book[i]);
			BookMarkDto dto = new BookMarkDto();
			dto.setProduct_id(Integer.parseInt(book[i]));
			dto.setUser_id(user_id);
			service.deleteOfBookMark(dto);
		}
		
		System.out.println(  "/bookmark/delefeOfBookMarkAll --> success"   );

	}
	
	
	
	//북마크 상태 표시
	@PostMapping("/bookmark/stateOfBookMark")
	public boolean stateOfBookMark(@ModelAttribute BookMarkDto dto)
	{
		boolean type = false;
		int cnt = service.stateOfBookMark(dto);
		if(cnt>=1)
			type=true;
	
		return type;
	}
	
	
	//북마크 된 상품 내역 가져오기
	@PostMapping("/bookmark/selectOfBookMark")
	public List<ProductDto> selectOfBookMark(@RequestParam int user_id)
	{
		List<ProductDto> list = service.selectOfBookMark(user_id);
		
		for(ProductDto dto :list)
		{
			System.out.println( dto.getProduct_id());
			System.out.println(dto.getProduct_category()  );
			System.out.println( dto.getProduct_price());
			System.out.println(dto.getProduct_img());
		}
		
		return list;
	}
	
	
	
	
	
	
	
	
}
