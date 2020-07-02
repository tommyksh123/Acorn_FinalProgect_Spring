package data.controller;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import data.dto.BuyDto;
import data.dto.BuyOptionDto;
import data.dto.ProductDto;
import data.service.BuyServiceInter;

@RestController
@CrossOrigin
public class BuyController {

	@Autowired
	private BuyServiceInter service;
	
	
	//썸네일에서 구매하기 누르면 DB에 상품 내용 들어감
	@PostMapping("/buy/insertOfBuy")
	public void insertOfBuy(@RequestBody BuyDto dto)
	{
		
		Date date = new Date();
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMdd");
		String order_id_1 = sdformat.format(date);
	
		NumberFormat nf = new DecimalFormat("0000");
		String order_id_2 =	nf.format(dto.getUser_id());

		String order_id_3 = nf.format(service.selectOfMaxBuyId()+1);     
		
		String order_id_4 = order_id_1+"-"+order_id_2+order_id_3;

		dto.setBuy_order_id(order_id_4);
		
	
		//구매하기 누르면 buy DB에 저장됨
		service.insertOfBuy(dto);
		
		//buy_option DB에 저장하기 위에 max num을 구함
		int buy_id = service.selectOfMaxBuyId();
		
		//배열로 들어간 레터링을 buy_option_text 칼럼에 저장하기 위해 list 배열을 돌림
		List<String> list = dto.getBuy_option_text();
		List<String> list2 = dto.getBuy_option_candle();
		
		for(int i=0; i<list.size();i++)
		{
			BuyOptionDto bodto = new BuyOptionDto();
			
			bodto.setBuy_id(buy_id);
			bodto.setBuy_option_text( list.get(i)  );
			bodto.setBuy_option_candle( list2.get(i)  );
		
			service.insertOfBuyOption(bodto);
		}
		
		System.out.println("/buy/insertOfBuy --> success");			
	}
	
	
	//썸네일에서 구매하기 직후에 나오는 결재정보창
	@PostMapping("/buy/selectOfBuyOneData")
	public BuyDto selectOfBuyOneData()
	{
		//buy_option DB에 저장하기 위에 max num을 구함
		int buy_id = service.selectOfMaxBuyId();
		
		//DB에 저장된 내용을 list 이용해서 보냄...
		List<BuyOptionDto> list = service.selectOfBuyOptionOneData(buy_id);
		List<String> list2 = new Vector<String>();
		List<String> list3 = new Vector<String>();
		
		for(BuyOptionDto dto:list)
		{
			list2.add(dto.getBuy_option_text());
			list3.add(dto.getBuy_option_candle());
		}
		
		BuyDto dto = new BuyDto();
		dto = service.selectOfBuyOneData(buy_id);
		dto.setBuy_option_text(list2);
		dto.setBuy_option_candle(list3);
		
		 

		dto.setBuy_pick_date(dto.getBuy_pick_date().substring(0,10));
		
		
		System.out.println("/buy/selectOfBuyOneData --> success");
		return dto;
	}
	
	
	
	//mypage에서 내가 구매한 목록들 일자 구간별로  전부다 보여주기
	@PostMapping("/buy/selectOfBuyAllData")
	public List<BuyDto> selectOfBuyAllData(@RequestParam int user_id, @RequestParam String time)
	{
		
		//스트링으로 넘어온 날짜 value를 DTO에 지정된 날짜 형식으로 전환
		//Timestamp search_time = Timestamp.valueOf( this.buytime(time)  );
		
		BuyDto dto2 = new BuyDto();
		
		//dto2.setBuy_sell_date(search_time);
		dto2.setBuy_sell_date(time);
		dto2.setUser_id(user_id);
		
		List<BuyDto> list = service.selectOfBuyAllData(dto2);
		 
		for(BuyDto dto:list)
		{
		  dto.setBuy_option_text(service.selectOfBuyOptionAllDataText(dto.getBuy_id()));  
		  dto.setBuy_option_candle(service.selectOfBuyOptionAllDataCandle(dto.getBuy_id()));
		}

		System.out.println("/buy/selectOfBuyAllData --> success");
		return list;
		
	}
	
	//판매자 화면에서 모든 상품 리스트 보여주기
	@PostMapping("/buy/selectOfBuyAllData2")
	public List<BuyDto> selectOfBuyAllData2(@RequestParam String time)
	{
		
		//스트링으로 넘어온 날짜 value를 DTO에 지정된 날짜 형식으로 전환
		//Timestamp search_time = Timestamp.valueOf( this.buytime(time)  );
		
		BuyDto dto2 = new BuyDto();
		//dto2.setBuy_sell_date(search_time);
		dto2.setBuy_sell_date(time);
		
		List<BuyDto> list = service.selectOfBuyAllData2(dto2);
		 
		for(BuyDto dto:list)
		{
		  dto.setBuy_option_text(service.selectOfBuyOptionAllDataText(dto.getBuy_id()));  
		  dto.setBuy_option_candle(service.selectOfBuyOptionAllDataCandle(dto.getBuy_id()));
		}

		System.out.println("/buy/selectOfBuyAllData2 --> success");
		return list;
	}
	
	
	
	
	//과거 구매내역에서 날짜 누르면 현재시각 기준으로 과거 날짜 보내줌
	@PostMapping("/buy/time")
	public String buytime(@RequestParam String time)
	{	
		//현재 시각 구하기
		Date date = new Date();
		
		//날짜+시간 포맷
		SimpleDateFormat sdformat_today_start = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//오늘의 시작 00시 00분 00초
		String today_start = sdformat_today_start.format(date);
		
		//현재 시각
		String now = sdformat.format(date);
		
		//7일전
		Calendar cal_seven_day = Calendar.getInstance();
		cal_seven_day.setTime(date);
		cal_seven_day.add(Calendar.DATE, -7);
		String seven_day = sdformat.format(cal_seven_day.getTime());
		
		//15일 전
		Calendar cal_fifteen_day = Calendar.getInstance();
		cal_fifteen_day.setTime(date);
		cal_fifteen_day.add(Calendar.DATE, -15);
		String fifteen_day = sdformat.format(cal_fifteen_day.getTime());
		
		//한달전
		Calendar cal_one_month = Calendar.getInstance();
		cal_one_month.setTime(date);
		cal_one_month.add(Calendar.MONDAY, -1);
		String one_month = sdformat.format(cal_one_month.getTime());
		
		//3달전
		Calendar cal_three_month = Calendar.getInstance();
		cal_three_month.setTime(date);
		cal_three_month.add(Calendar.MONDAY, -3);
		String three_month = sdformat.format(cal_three_month.getTime());
		
		//1년전
		Calendar cal_one_year = Calendar.getInstance();
		cal_one_year.setTime(date);
		cal_one_year.add(Calendar.YEAR, -1);
		String one_year = sdformat.format(cal_one_year.getTime());
		
		//리엑트로 return할 string 값
		String msg = null;
		
		if(time.equals("오늘"))
			msg=today_start;
		else if(time.equals("7일"))
			msg=seven_day;
		else if(time.equals("15일")) 
			msg=fifteen_day;
		else if(time.equals("1달"))
			msg=one_month;
		else if(time.equals("3달"))
			msg=three_month;
		else if(time.equals("1년"))
			msg=one_year;

		System.out.println("/buy/time --> success");
		return  msg; 
		
	}
	
	
	//판매자가 버튼 클릭시 buy테이블의 buy_order_type이 +1씩 증가함
	@PostMapping("/buy/updateOfBuyOrderType")
	public void updateOfBuyOrderType(@RequestParam int buy_id)
	{
		service.updateOfBuyOrderType(buy_id);
		System.out.println("/buy/updateOfBuyOrderType --> success");
	}
	
	
	//최근 주문한 상품 리스트
	@GetMapping("/buy/selectOfRecentBuyList")
	public List<ProductDto> selectOfRecentBuyList(@RequestParam int user_id)
	{
		return service.selectOfRecentBuyList(user_id);
	}
	
	
	//총 구매 금액
	@GetMapping("/buy/selectOfTotalBuyPrice")
	public String selectOfTotalBuyPrice(@RequestParam int user_id)
	{
	    DecimalFormat formatter = new DecimalFormat("###,###");
	    String s=formatter.format(service.selectOfTotalBuyPrice(user_id));
		return s;
	}
	
	//총 구매 갯수
	@GetMapping("/buy/selectOfTotalBuyCount")
	public int selectOfTotalBuyCount(@RequestParam int user_id)
	{
		return service.selectOfTotalBuyCount(user_id);
	}
	

	
	// 주문 취소
	@PostMapping("/buy/deleteOrderbuy")
	public void deleteOrderbuy(@RequestParam int buy_id)
	{
		service.deleteOfOrderbuyOption(buy_id);
		service.deleteOrderbuy(buy_id);
	}
	
	
	//실제 구매한 사람만 리뷰 달수 있게
	@PostMapping("/buy/reviewUser")
	public List<BuyDto> selectOfReviewUser(@RequestParam int product_id)
	{
		List<BuyDto> list = service.selectOfReviewUser(product_id);
		
		System.out.println(list.size());
	
		return list;
	}
	
	
}

