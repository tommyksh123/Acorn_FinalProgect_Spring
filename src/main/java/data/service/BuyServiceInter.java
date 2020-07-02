package data.service;


import java.util.List;

import data.dto.BuyDto;
import data.dto.BuyOptionDto;
import data.dto.ProductDto;

public interface BuyServiceInter {

	//상품 구매 시 DB에 저장
	public void insertOfBuy(BuyDto dto);
	public void insertOfBuyOption(BuyOptionDto dto);
	
	//상품 구매 후 결재정보창에 상품 내역 보여주기
	public int selectOfMaxBuyId();
	public BuyDto selectOfBuyOneData(int buy_id);
	public List<BuyOptionDto> selectOfBuyOptionOneData(int buy_id);
	
	//mypage에서 구매 전체 내역 보여주기
	public List<BuyDto> selectOfBuyAllData(BuyDto dto);
	public List<String> selectOfBuyOptionAllDataText(int buy_id);
	public List<String> selectOfBuyOptionAllDataCandle(int buy_id);
	
	
	//판매자 mypage에서 구매전체 내역 보여주기
	public List<BuyDto> selectOfBuyAllData2(BuyDto dto);
		
		
	//버튼클릭시 buy_order_type이 +1이 된다.  
	public void updateOfBuyOrderType(int buy_id);
	
	//최근 주문한 상품
	public List<ProductDto> selectOfRecentBuyList(int user_id);
	
	//총 구매금액
	public int selectOfTotalBuyPrice(int user_id);
	
	//총 구매갯수
	public int selectOfTotalBuyCount(int user_id);
	
	// 주문취소
	public void deleteOrderbuy(int buy_id);
	public void deleteOfOrderbuyOption(int buy_id);
	
	//실 구매자만 리뷰를 달 수 있다 
	public List<BuyDto> selectOfReviewUser(int product_id);
	
	
	
}
