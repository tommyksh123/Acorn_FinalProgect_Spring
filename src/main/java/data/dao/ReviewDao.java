package data.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import data.dto.ProductDto;
import data.dto.ReviewDto;

@Repository
public class ReviewDao extends SqlSessionDaoSupport implements ReviewDaoInter {

	//리뷰 등록
	@Override
	public void insert(ReviewDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertOfReview", dto);
	}

	//리뷰 삭제
	@Override
	public void delete(int review_id) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteOfReview", review_id);
	}

	//리뷰 조회
	@Override
	public List<ReviewDto> list(int product_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectOfReview", product_id);
	}

	//getData
	@Override
	public ReviewDto getData(int review_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("selectOneOfReview", review_id);
	}
}
