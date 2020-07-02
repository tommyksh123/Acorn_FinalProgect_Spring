package data.dto;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BuyDto {

	private int buy_id;
	private int user_id;
	private int product_id;
	private String buy_sell_date;
	private int buy_price;
	private int buy_count;

	private String buy_pick_date;
	private int buy_order_type;
	private List<String> buy_option_text;
	private List<String> buy_option_candle;
	private String buy_order_id;
	
	
	public String getBuy_order_id() {
		return buy_order_id;
	}
	public void setBuy_order_id(String buy_order_id) {
		this.buy_order_id = buy_order_id;
	}
	public List<String> getBuy_option_text() {
		return buy_option_text;
	}
	public void setBuy_option_text(List<String> buy_option_text) {
		this.buy_option_text = buy_option_text;
	}
	
	public List<String> getBuy_option_candle() {
		return buy_option_candle;
	}
	public void setBuy_option_candle(List<String> buy_option_candle) {
		this.buy_option_candle = buy_option_candle;
	}

	
	
	
	public int getBuy_id() {
		return buy_id;
	}
	public void setBuy_id(int buy_id) {
		this.buy_id = buy_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getBuy_sell_date() {
		return buy_sell_date;
	}
	public void setBuy_sell_date(String buy_sell_date) {
		this.buy_sell_date = buy_sell_date;
	}
	public int getBuy_price() {
		return buy_price;
	}
	public void setBuy_price(int buy_price) {
		this.buy_price = buy_price;
	}
	public int getBuy_count() {
		return buy_count;
	}
	public void setBuy_count(int buy_count) {
		this.buy_count = buy_count;
	}
	public String getBuy_pick_date() {
		return buy_pick_date;
	}
	public void setBuy_pick_date(String buy_pick_date) {
		this.buy_pick_date = buy_pick_date;
	}
	public int getBuy_order_type() {
		return buy_order_type;
	}
	public void setBuy_order_type(int buy_order_type) {
		this.buy_order_type = buy_order_type;
	}
	
	
	
}
