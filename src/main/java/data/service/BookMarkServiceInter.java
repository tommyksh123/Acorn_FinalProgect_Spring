package data.service;

import java.util.List;

import data.dto.BookMarkDto;
import data.dto.ProductDto;

public interface BookMarkServiceInter {
	
	public void insertOfBookMark(BookMarkDto dto );
	public void deleteOfBookMark(BookMarkDto dto);
	public int stateOfBookMark(BookMarkDto dto);
	public List<ProductDto> selectOfBookMark(int user_id);
}
