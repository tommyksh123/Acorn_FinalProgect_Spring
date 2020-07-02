package data.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;


import data.dto.BuyDto;
import data.dto.BuyOptionDto;
import data.dto.ProductDto;

@Repository
public class BuyDao extends SqlSessionDaoSupport implements BuyDaoInter {



	@Override
	public void insertOfBuy(BuyDto dto) {
		getSqlSession().insert("insertOfBuy", dto); 
	}

	@Override
	public void insertOfBuyOption(BuyOptionDto dto) {
		getSqlSession().insert("insertOfBuyOption", dto);
	}
	

	@Override
	public int selectOfMaxBuyId() {
		
		return getSqlSession().selectOne("selectOfMaxBuyId");
	}

	@Override
	public BuyDto selectOfBuyOneData(int buy_id) {
		return getSqlSession().selectOne("selectOfBuyOneData", buy_id);
	}

	@Override
	public List<BuyOptionDto> selectOfBuyOptionOneData(int buy_id) {
		return getSqlSession().selectList("selectOfBuyOptionOneData", buy_id);
	}



	@Override
	public List<BuyDto> selectOfBuyAllData(BuyDto dto) {
		return getSqlSession().selectList("selectOfBuyAllData", dto);
	}

	
	@Override
	public List<String> selectOfBuyOptionAllDataText(int buy_id) {
		
		return getSqlSession().selectList("selectOfBuyOptionAllDataText2", buy_id);
	}

	@Override
	public List<String> selectOfBuyOptionAllDataCandle(int buy_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectOfBuyOptionAllDataCandle2", buy_id);
	}

	@Override
	public void updateOfBuyOrderType(int buy_id) {
		getSqlSession().update("updateOfBuyOrderType", buy_id);
		
	}

	@Override
	public List<BuyDto> selectOfBuyAllData2(BuyDto dto) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectOfBuyAllData2", dto);
	}


	@Override
	public List<ProductDto> selectOfRecentBuyList(int user_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectOfRecentBuyList", user_id);
	}

	@Override
	public int selectOfTotalBuyPrice(int user_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectOfTotalBuyPrice", user_id);
	}

	@Override
	public int selectOfTotalBuyCount(int user_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectOfTotalBuyCount", user_id);
	}
	
	// 주문 취소
	@Override
	public void deleteOrderbuy(int buy_id)
	{
		getSqlSession().delete("deleteOfOrderbuy", buy_id);
	}

	@Override
	public void deleteOfOrderbuyOption(int buy_id)
	{
		getSqlSession().delete("deleteOfOrderbuyOption", buy_id);
	}

	@Override
	public List<BuyDto> selectOfReviewUser(int product_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList( "selectOfReviewUser", product_id);
	}
	
	
	


}
