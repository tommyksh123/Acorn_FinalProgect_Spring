package data.service;

import java.util.List;

import data.dto.ReviewDto;

public interface ReviewServiceInter {
	//리뷰 등록
	public void insert(ReviewDto dto);
	
	//리뷰 삭제
	public void delete(int review_id);
	
	//리뷰 조회
	public List<ReviewDto> list(int product_id);
	
	//getData
	public ReviewDto getData(int review_id);
}
