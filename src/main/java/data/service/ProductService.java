package data.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dao.ProductDaoInter;
import data.dto.BuyDto;
import data.dto.BuyOptionDto;
import data.dto.ProductDto;
import data.dto.RecentProductDto;

@Service
public class ProductService implements ProductServiceInter {
   
   @Autowired
   ProductDaoInter dao;
   
   //상품 등록
   @Override
   public void insert(ProductDto dto) {
      // TODO Auto-generated method stub
      dao.insert(dto);
   }

   //상품 기본정보 수정
   @Override
   public void update(ProductDto dto) {
      // TODO Auto-generated method stub
      dao.update(dto);
   }

   //판매량 증가
   @Override
   public void updateCount(int product_id) {
      // TODO Auto-generated method stub
      dao.updateCount(product_id);
   }

   //상품 삭제
   @Override
   public void delete(int product_id) {
      // TODO Auto-generated method stub
      dao.delete(product_id);
   }

   //상품 전체 목록
   @Override
   public List<ProductDto> list() {
      // TODO Auto-generated method stub
      return dao.list();
   }

   //상품 개별 조회
   @Override
   public ProductDto getData(int product_id) {
      // TODO Auto-generated method stub
      return dao.getData(product_id);
   }

   //최신순
   @Override
   public List<ProductDto> newProduct() {
      // TODO Auto-generated method stub
      return dao.newProduct();
   }

   //가격높은순
   @Override
   public List<ProductDto> priceUp() {
      // TODO Auto-generated method stub
      return dao.priceUp();
   }

   //가격 낮은순
   @Override
   public List<ProductDto> priceDown() {
      // TODO Auto-generated method stub
      return dao.priceDown();
   }

   //판매량 높은순
   @Override
   public List<ProductDto> totalCount() {
      // TODO Auto-generated method stub
      return dao.totalCount();
   }

   //리뷰 많은순
   @Override
   public List<ProductDto> reviewCount() {
      // TODO Auto-generated method stub
      return dao.reviewCount();
   }
   
   //최근 본 상품 추가
   public void insertRecent(RecentProductDto dto) {
      dao.insertRecent(dto);
   }
   
   //최근 본 상품순
   public List<ProductDto> recentList(int user_id){
      return dao.recentList(user_id);
   }

   @Override
   public List<ProductDto> searchProduct(String search) {
      // TODO Auto-generated method stub
      return dao.searchProduct(search);
   }
   
   // 전체 상품 갯수 조회
   @Override
   public int selectOfAlltotalCount()
   {
      return dao.selectOfAlltotalCount();
   }
      
   // 총 매출 액
   @Override
   public int selectOfAllPrice()
   {
      return dao.selectOfAllPrice();
   }
   
   //카테고리별 조회
   @Override
   public List<ProductDto> categoryList(String product_category) {
      // TODO Auto-generated method stub
      return dao.categoryList(product_category);
   }
   
   //recent조회
   @Override
   public int getDataRecent(RecentProductDto dto) {
      // TODO Auto-generated method stub
      return dao.getDataRecent(dto);
   }

   //recent 업데이트
   @Override
   public void updateRecent(RecentProductDto dto) {
      // TODO Auto-generated method stub
      dao.updateRecent(dto);;
   }
   
	// 구매 진행 중 상품 리스트
	@Override
	public List<HashMap<String, Object>> selectOfNowOrderList(int user_id)
	{
		return dao.selectOfNowOrderList(user_id);
	}
	
	// 지난 구매 내역 리스트
	@Override
	public List<HashMap<String, Object>> selectOfOrderList(int user_id)
	{
		return dao.selectOfOrderList(user_id);
	}


//	// 지난 주문 내역 날짜별 리스트
//	@Override
//	public List<HashMap<Object, Object>> selectOfDateOrderList(String startday, String endday)
//	{
//		return dao.selectOfDateOrderList(startday, endday);
//	}
	
	// 지난 주문 내역 날짜별 리스트
	@Override
	public List<HashMap<Object, Object>> selectOfDateOrderList(String startday, String endday,int user_id)
	{
		return dao.selectOfDateOrderList(startday, endday,user_id);
	}
	
	

	// 지난 주문 상세 내역
	@Override
	public List<HashMap<String, Object>> selectOfOrderSheetList(String buy_order_id)
	{
		return dao.selectOfOrderSheetList(buy_order_id);
	}

	@Override
	public List<BuyOptionDto> selectOfBuyOption(int buy_id) {
		// TODO Auto-generated method stub
		return dao.selectOfBuyOption(buy_id);
	}

	
	
	
	
	
}