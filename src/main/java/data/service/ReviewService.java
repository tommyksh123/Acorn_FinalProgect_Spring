package data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import data.dao.ReviewDaoInter;
import data.dto.ProductDto;
import data.dto.ReviewDto;

@Service
public class ReviewService implements ReviewServiceInter {

	@Autowired
	ReviewDaoInter dao;
	
	//리뷰 등록
	@Override
	public void insert(ReviewDto dto) {
		// TODO Auto-generated method stub
		dao.insert(dto);
	}

	//리뷰 삭제
	@Override
	public void delete(int review_id) {
		// TODO Auto-generated method stub
		dao.delete(review_id);
	}

	//리뷰 조회
	@Override
	public List<ReviewDto> list(int product_id) {
		// TODO Auto-generated method stub
		return dao.list(product_id);
	}

	//getData
	@Override
	public ReviewDto getData(int review_id) {
		// TODO Auto-generated method stub
		return dao.getData(review_id);
	}
}
