package data.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import data.dto.BuyOptionDto;
import data.dto.ProductDto;
import data.dto.RecentProductDto;

@Repository
public class ProductDao extends SqlSessionDaoSupport implements ProductDaoInter {

   //상품 등록
   @Override
   public void insert(ProductDto dto) {
      // TODO Auto-generated method stub
      getSqlSession().insert("insertOfProduct", dto);
   }

   //상품 기본정보 수정
   @Override
   public void update(ProductDto dto) {
      // TODO Auto-generated method stub
      getSqlSession().update("updateProduct", dto);
   }

   //판매량 증가
   @Override
   public void updateCount(int product_id) {
      // TODO Auto-generated method stub
      getSqlSession().update("updateOfProductCount", product_id);
   }

   //상품 삭제
   @Override
   public void delete(int product_id) {
      // TODO Auto-generated method stub
      getSqlSession().delete("deleteOfProduct", product_id);
   }

   //상품 전체 목록
   @Override
   public List<ProductDto> list() {
      // TODO Auto-generated method stub
      return getSqlSession().selectList("selectAllOfProduct");
   }

   //상품 개별 조회
   @Override
   public ProductDto getData(int product_id) {
      // TODO Auto-generated method stub
      return getSqlSession().selectOne("selectOneOfProduct", product_id);
   }

   //최신순
   @Override
   public List<ProductDto> newProduct() {
      // TODO Auto-generated method stub
      return getSqlSession().selectList("selectOfProductNew");
   }

   //가격높은순
   @Override
   public List<ProductDto> priceUp() {
      // TODO Auto-generated method stub
      return getSqlSession().selectList("selectOfProductPriceUp");
   }

   //가격 낮은순
   @Override
   public List<ProductDto> priceDown() {
      // TODO Auto-generated method stub
      return getSqlSession().selectList("selectOfProductPriceDown");
   }

   //판매량 높은순
   @Override
   public List<ProductDto> totalCount() {
      // TODO Auto-generated method stub
      return getSqlSession().selectList("selectOfProductCount");
   }

   //리뷰 많은 순
   @Override
   public List<ProductDto> reviewCount() {
      // TODO Auto-generated method stub
      List<ProductDto> list = getSqlSession().selectList("selectOfReviewCount");
      return list;
   }

   //최근 본 상품 추가
   public void insertRecent(RecentProductDto dto) {
      getSqlSession().insert("insertOfRecentProduct", dto);
   }
   
   //최근 본 상품순
   public List<ProductDto> recentList(int user_id){
      List<ProductDto> list = getSqlSession().selectList("selectOfRecentProductList", user_id);
      return list;
   }

   //검색 시 
   @Override
   public List<ProductDto> searchProduct(String search) {
      // TODO Auto-generated method stub
      return getSqlSession().selectList("selectOfSearchProduct", search);
   }

   
   // 전체 상품 갯수 조회
   @Override
   public int selectOfAlltotalCount()
   {
      return getSqlSession().selectOne("selectOfAlltotalCount");
   }
      
   // 총 매출 액
   @Override
   public int selectOfAllPrice()
   {
      return getSqlSession().selectOne("selectOfAllPrice");
   }
   
   //카테고리별 조회
   @Override
   public List<ProductDto> categoryList(String product_category) {
      // TODO Auto-generated method stub
      return getSqlSession().selectList("selectOfProductCategory", product_category);
   }

   //recent조회
   @Override
   public int getDataRecent(RecentProductDto dto) {
      // TODO Auto-generated method stub
      return getSqlSession().selectOne("selectOneOfRecent", dto);
   }

   //recent 업데이트
   @Override
   public void updateRecent(RecentProductDto dto) {
      // TODO Auto-generated method stub
      getSqlSession().update("updateOfRecent", dto);
   }
   
	// 구매 진행 중 상품 리스트
	@Override
	public List<HashMap<String, Object>> selectOfNowOrderList(int user_id)
	{		
		Map<Object,Integer> map = new HashMap<Object,Integer>();
		// map.put(user_id, user_id); //1 ,1
		map.put("user_id", user_id); //"user_id",1
			
		return getSqlSession().selectList("selectOfNowOrderList", map);
	}
	
	// 지난 구매 내역 리스트
	@Override
	public List<HashMap<String, Object>> selectOfOrderList(int user_id)
	{
		Map<Object,Integer> map = new HashMap<Object,Integer>();
		map.put("user_id", user_id);
			
		return getSqlSession().selectList("selectOfOrderList", map);
	}

	

//	// 지난 주문 내역 날짜별 리스트
//	@Override
//	public List<HashMap<Object, Object>> selectOfDateOrderList(String startday, String endday)
//	{
//		Map<Object, Object> map = new HashMap<Object, Object>();
//		map.put("startday", startday);
//		map.put("endday", endday);
//			
//		return getSqlSession().selectList("selectOfDateOrderList", map);
//	}

	
	// 지난 주문 내역 날짜별 리스트
	@Override
	public List<HashMap<Object, Object>> selectOfDateOrderList(String startday, String endday,int user_id)
	{
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("startday", startday);
		map.put("endday", endday);
		map.put("user_id",user_id);
			
		return getSqlSession().selectList("selectOfDateOrderList", map);
	}
	
	
	
	// 지난 주문 상세 내역
	@Override
	public List<HashMap<String, Object>> selectOfOrderSheetList(String buy_order_id)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buy_order_id", buy_order_id);
			
		return getSqlSession().selectList("selectOfOrderSheetList", map);
	}

	@Override
	public List<BuyOptionDto> selectOfBuyOption(int buy_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectOfBuyOption", buy_id);
	}
	
	
	
	


}