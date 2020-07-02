package data.service;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import data.dao.BookmarkDaoInter;
import data.dto.BookMarkDto;
import data.dto.ProductDto;

@Repository
public class BookMarkService extends SqlSessionDaoSupport implements BookMarkServiceInter {

	@Autowired
	BookmarkDaoInter dao;
	
	@Override
	public void insertOfBookMark(BookMarkDto dto) {
		// TODO Auto-generated method stub
		dao.insertOfBookMark(dto);

	}

	@Override
	public void deleteOfBookMark(BookMarkDto dto) {
		// TODO Auto-generated method stub
		dao.deleteOfBookMark(dto);
	}

	@Override
	public int stateOfBookMark(BookMarkDto dto) {
		// TODO Auto-generated method stub
		return dao.stateOfBookMark(dto);
	}
	
	@Override
	public List<ProductDto> selectOfBookMark(int user_id) 
	{
		// TODO Auto-generated method stub
		return dao.selectOfBookMark(user_id);
	}

	

}
