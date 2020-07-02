package data.controller;

import java.io.File;
import java.util.HashMap;
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

import data.dto.BuyDto;
import data.dto.BuyOptionDto;
import data.dto.ProductDto;
import data.dto.RecentProductDto;
import data.service.ProductServiceInter;
import upload.util.SpringFileWrite;

@RestController
@CrossOrigin
public class ProductController {
   
   @Autowired
   private ProductServiceInter service;
   
   //이미지 파일명
   String saveImagename;   
   
   @RequestMapping(value = "/product/productFile", consumes = {"multipart/form-data"}, method = RequestMethod.POST)
   public String fileUpload(MultipartHttpServletRequest request, @RequestParam MultipartFile uploadFile) {
       System.out.println("product>>productImage>>"+uploadFile.getOriginalFilename());
      
      //업로드할 경로구하기
      String path = request.getSession().getServletContext().getRealPath("/WEB-INF/image/productImage");
      System.out.println("productImage path="+path);
      
      //실제 폴더에 이미지 저장
      SpringFileWrite sfw = new SpringFileWrite();
      saveImagename=uploadFile.getOriginalFilename();
      
      sfw.writeFileRename(uploadFile, path, saveImagename);
      
      return saveImagename; //파일명 리턴
   }
   
   //상품 등록
   @PostMapping("/product/add")
   public String insert(@RequestBody ProductDto dto) {   
      dto.setProduct_img(saveImagename);
      service.insert(dto);
      
      return "product Insert Success";            
   }
//   
//   //수정폼
//   @GetMapping("/product/updateform")
//   public ProductDto updateform(@RequestParam int product_id) {
//      ProductDto dto=null;
//      for(int i=0; i<service.list().size(); i++) {
//         ProductDto d = service.list().get(i);
//         if(product_id==d.getProduct_id()) {
//            dto=d;
//            break;
//         }
//      }
//      return dto;
//   }
   
   
 //상품기본 정보 수정
   @PostMapping("/product/update")
   public String update(HttpServletRequest request, @RequestBody ProductDto dto)
   {
      // 이미지 경로
      String path = request.getSession().getServletContext().getRealPath("/WEB-INF/image/productImage");

      // 이미지 파일명
      String photoname = service.getData(dto.getProduct_id()).getProduct_img();

      String image = dto.getProduct_img();      
      System.out.println("React에서 받아온 이미지 : " + image);

      System.out.println("Product_id로 검색한 기존 이미지 : " + photoname);

      if(dto.getProduct_img() == null)
      {
         dto.setProduct_img(photoname);
         service.update(dto);
         System.out.println("성공1");
      }

      else
      {
         System.out.println("else 들어옴");
         // 이미지가 존재할경우 삭제
         File file = new File(path+"\\"+photoname);
         System.out.println(file);
         if(file.exists())
         {
            file.delete();
            System.out.println("삭제성공");
         }
         System.out.println("saveImagename : " + saveImagename);
         dto.setProduct_img(saveImagename);
         
         service.update(dto);
         System.out.println("성공2");
      }

      return "product Update Success";   
   }
   
   
   //판매
   @PostMapping("/product/updateCount")
   public String updateCount(@RequestParam int product_id) {
      service.updateCount(product_id);
      return "productCount Update Success";
   }
   
   //상품 삭제
   @PostMapping("/product/delete")
   public String delete(HttpServletRequest request, @RequestParam int product_id) {
      //이미지 경로
      String path = request.getSession().getServletContext().getRealPath("/WEB-INF/Image/productImage");
      //이미지 파일명
      String photoname = service.getData(product_id).getProduct_img();
      //이미지가 존재할경우 삭제
      File file = new File(path+"\\"+photoname);
      if(file.exists())
         file.delete();
      
      service.delete(product_id);
      return "product Delete Success";
   }
   
   //전체 목록 조회
   @GetMapping("/product/list")
   public List<ProductDto> list() {   
      //db로부터 리스트 가져오기
      List<ProductDto> list = service.list();
      
      return list;
   }
   
   //개별 조회
   @GetMapping("/product/getData")
   public ProductDto getData(@RequestParam int product_id) {
      ProductDto dto = service.getData(product_id);
      return dto;
   }
   
   //최신순
   @GetMapping("/product/newList")
   public List<ProductDto> newList() {   
      
      //db로부터 리스트 가져오기
      List<ProductDto> list = service.newProduct();   
      return list;
      
   }
   
   //가격 높은순
   @GetMapping("/product/priceUpList")
   public List<ProductDto> priceUpList() {   
      //db로부터 리스트 가져오기
      List<ProductDto> list = service.priceUp();      
      return list;
   }
   
   //가격 낮은순
   @GetMapping("/product/priceDownList")
   public List<ProductDto> priceDownList() {   
      //db로부터 리스트 가져오기
      List<ProductDto> list = service.priceDown();      
      return list;
   }
   
   //판매량
   @GetMapping("/product/countList")
   public List<ProductDto> countList() {   
      //db로부터 리스트 가져오기
      List<ProductDto> list = service.totalCount();      
      return list;
   }
   
   //리뷰많은 순
   @GetMapping("/product/countReview")
   public List<ProductDto> countReview(){
      List<ProductDto> list = service.reviewCount();
      return list;
   }
   
   //최근 본 상품 추가
   @PostMapping("/product/recentAdd")
   public void insertRecent(@RequestBody RecentProductDto dto) {
      if(service.getDataRecent(dto)==0)
         service.insertRecent(dto);
      else
         service.updateRecent(dto);
   }
   
   //최근 본 상품순
   @GetMapping("/product/recentList")
   public List<ProductDto> recentList(@RequestParam int user_id){
      return service.recentList(user_id);
   }
   
   //검색 시 
   @GetMapping("/product/searchProduct")
   public List<ProductDto> searchProduct(@RequestParam String search){
      System.out.println("/product/searchProduct");
      List<ProductDto> list = service.searchProduct(search);
      
     for(int i=0; i<list.size();i++){
        System.out.println(list.get(i).getProduct_name());
     }
      return list;
   }
   
   // 총 상품 갯수
   @PostMapping("/product/alllist")
   public int selectOfAlltotalCount(){
      int total = service.selectOfAlltotalCount();
      return total;
   }
      
   // 총 매출액
   @PostMapping("/product/allprice")
   public int selectOfAllPrice()
   {
      int total_price = service.selectOfAllPrice();
      return total_price;
   }
      
   //카테고리별 조회
   @GetMapping("/product/category")
   public List<ProductDto> categoryList(@RequestParam String product_category){
      return service.categoryList(product_category);
   }
   
	   
	// 구매 진행 중 상품 리스트
	@GetMapping("/product/noworder")
	public List<HashMap<String, Object>> noworder(@RequestParam int user_id)
	{
		System.out.println(user_id);
		List<HashMap<String, Object>> list = service.selectOfNowOrderList(user_id);
		
		return list; 
	}
	
	
	//구매 진행중 상품 리스트 -- 옵션 뽑아오기
	@PostMapping("/product/noworder_selectOfBuyOption")
	public List<BuyOptionDto> selectOfBuyOption(int buy_id)
	{
		List<BuyOptionDto> list = service.selectOfBuyOption(buy_id);
		
		for(int i =0; i<list.size();i++)
		{
			System.out.println(  list.get(i).getBuy_id()   );
			System.out.println(  list.get(i).getBuy_option_text()   );
			System.out.println(  list.get(i).getBuy_option_candle()   );
		}
		
		return list;
	}
	

	
	
	// 지난 구매 내역 리스트
	@GetMapping("/product/orderlist")
	public List<HashMap<String, Object>> orderList(@RequestParam int user_id)
	{
		List<HashMap<String, Object>> list = service.selectOfOrderList(user_id);		
		return list; 
	}


//	// 구매 진행 중 상품 리스트
//	@PostMapping("/product/dateorderlist")
//	public List<HashMap<Object, Object>> selectOfDateOrderList
//	(
//		@RequestParam String startday,
//		@RequestParam String endday
//	)
//	{
//		System.out.println(startday);
//		System.out.println(endday);
//		
//		List<HashMap<Object, Object>> list = service.selectOfDateOrderList(startday, endday);
//		return list; 
//	}
	
	
	// 구매 진행 중 상품 리스트
	@PostMapping("/product/dateorderlist")
	public List<HashMap<Object, Object>> selectOfDateOrderList
	(
		@RequestParam String startday,
		@RequestParam String endday,
		@RequestParam int user_id
	)
	{
		System.out.println(startday);
		System.out.println(endday);
		System.out.println(user_id);
		
		List<HashMap<Object, Object>> list = service.selectOfDateOrderList(startday, endday, user_id);
		return list; 
	}
	
	


	// 지난 주문 상세 내역
	@GetMapping("/product/ordersheetlist")
	public List<HashMap<String, Object>> ordersheetlist(@RequestParam String buy_order_id)
	{
		System.out.println(buy_order_id);
		List<HashMap<String, Object>> list = service.selectOfOrderSheetList(buy_order_id);
		return list; 
	}
   
}