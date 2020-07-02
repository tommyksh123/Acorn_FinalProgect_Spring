package data.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import data.dto.BookMarkDto;
import data.dto.ProductDto;

@Repository
public class BookMarkDao extends SqlSessionDaoSupport implements BookmarkDaoInter {

	@Override
	public void insertOfBookMark(BookMarkDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().insert("insertOfBookMark",dto);
	}

	@Override
	public void deleteOfBookMark(BookMarkDto dto) {
		// TODO Auto-generated method stub
		getSqlSession().delete("deleteOfBookMark", dto);
	}

	@Override
	public int stateOfBookMark(BookMarkDto dto) {
		return getSqlSession().selectOne("stateOfBookMark", dto);
	}
	
	
	@Override
	public List<ProductDto> selectOfBookMark(int user_id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("selectOfBookMark", user_id);
	}

	

}
