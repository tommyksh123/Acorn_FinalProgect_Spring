package data.dao;

import java.util.HashMap;
import java.util.List;

import data.dto.BuyOptionDto;
import data.dto.ProductDto;
import data.dto.RecentProductDto;

public interface ProductDaoInter {
   //상품등록
   public void insert(ProductDto dto);
   
   //상품 기본 정보 수정
   public void update(ProductDto dto);
   
   //판매량 증가
   public void updateCount(int product_id);
   
   //상품 삭제
   public void delete(int product_id);
   
   //전체 목록 조회
   public List<ProductDto> list();
   
   //상품 개별 조회
   public ProductDto getData(int product_id);
   
   //최신순
   public List<ProductDto> newProduct();
   
   //가격높은순
   public List<ProductDto> priceUp();
   
   //가격낮은순
   public List<ProductDto> priceDown();
   
   //판매량 높은순
   public List<ProductDto> totalCount();
   
   //리뷰 많은 순
   public List<ProductDto> reviewCount();
   
   //검색 시 
   public List<ProductDto> searchProduct(String search);
   
   // 전체 상품 수
   public int selectOfAlltotalCount();
      
   // 총 매출 액
   public int selectOfAllPrice();
   
   //카테고리별 조회
   public List<ProductDto> categoryList(String product_category);

   //최근본 상품 추가
   public void insertRecent(RecentProductDto dto);
   
   //최근본 상품 순
   public List<ProductDto> recentList(int user_id);
   
   //getDataRecent
   public int getDataRecent(RecentProductDto dto);
   
   //updateRecent
   public void updateRecent (RecentProductDto dto);
   
	// 구매 진행 중 상품 리스트
	public List<HashMap<String, Object>> selectOfNowOrderList(int user_id);
		
	// 지난 주문 내역
	public List<HashMap<String, Object>> selectOfOrderList(int user_id);



//	// 지난 주문 내역 날짜 별
//	public List<HashMap<Object, Object>> selectOfDateOrderList(String startday, String endday);
	
	// 지난 주문 내역 날짜 별
	public List<HashMap<Object, Object>> selectOfDateOrderList(String startday, String endday, int user_id);
	
	

	// 지난 주문 상세 내역
	public List<HashMap<String, Object>> selectOfOrderSheetList(String buy_order_id);

	
	//최근 상품 목록에서 옵션 꺼내오기
	public List<BuyOptionDto> selectOfBuyOption(int buy_id);
	
	
}