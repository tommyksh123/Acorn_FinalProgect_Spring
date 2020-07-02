package data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dao.BuyDaoInter;
import data.dto.BuyDto;
import data.dto.BuyOptionDto;
import data.dto.ProductDto;

@Service
public class BuyService implements BuyServiceInter {

	@Autowired
	BuyDaoInter dao;
	
	@Override
	public void insertOfBuy(BuyDto dto) {
		dao.insertOfBuy(dto);
	}

	@Override
	public void insertOfBuyOption(BuyOptionDto dto) {
		dao.insertOfBuyOption(dto);
	}

	@Override
	public int selectOfMaxBuyId() {
		
		return dao.selectOfMaxBuyId();
	}

	@Override
	public BuyDto selectOfBuyOneData(int buy_id) {
		return dao.selectOfBuyOneData(buy_id);
	}

	@Override
	public List<BuyOptionDto> selectOfBuyOptionOneData(int buy_id) {
		return dao.selectOfBuyOptionOneData(buy_id);
	}


	@Override
	public List<BuyDto> selectOfBuyAllData(BuyDto dto) {
		return dao.selectOfBuyAllData(dto);
	}
	
	
	@Override
	public List<String> selectOfBuyOptionAllDataText(int buy_id) {
		// TODO Auto-generated method stub
		return dao.selectOfBuyOptionAllDataText(buy_id);
	}

	@Override
	public List<String> selectOfBuyOptionAllDataCandle(int buy_id) {
		// TODO Auto-generated method stub
		return dao.selectOfBuyOptionAllDataCandle(buy_id);
	}

	@Override
	public void updateOfBuyOrderType(int buy_id) {
		dao.updateOfBuyOrderType(buy_id);
		
	}

	@Override
	public List<BuyDto> selectOfBuyAllData2(BuyDto dto) {
		// TODO Auto-generated method stub
		return dao.selectOfBuyAllData2(dto);
	}

	@Override
	public List<ProductDto> selectOfRecentBuyList(int user_id) {
		// TODO Auto-generated method stub
		return dao.selectOfRecentBuyList(user_id);
	}

	@Override
	public int selectOfTotalBuyPrice(int user_id) {
		// TODO Auto-generated method stub
		return dao.selectOfTotalBuyPrice(user_id);
	}

	@Override
	public int selectOfTotalBuyCount(int user_id) {
		// TODO Auto-generated method stub
		return dao.selectOfTotalBuyCount(user_id);
	}

	// 주문 취소
	@Override
	public void deleteOrderbuy(int buy_id)
	{
		dao.deleteOrderbuy(buy_id);
	}

	@Override
	public void deleteOfOrderbuyOption(int buy_id)
	{
		dao.deleteOfOrderbuyOption(buy_id);
	}

	@Override
	public List<BuyDto> selectOfReviewUser(int product_id) {
		// TODO Auto-generated method stub
		return dao.selectOfReviewUser(product_id);
	}

	
	
	
}
