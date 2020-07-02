package data.dto;

public class ProductDto {
	private int product_id;
	private String product_name;
	private String product_text;
	private String product_img;
	private int product_price;
	private String product_category;
	private int product_total_count;
	private String product_option;
	private int review_count;
	private double review_avg_star;
	
	
	public int getReview_count() {
		return review_count;
	}
	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}
	public double getReview_avg_star() {
		return review_avg_star;
	}
	public void setReview_avg_star(double review_avg_star) {
		this.review_avg_star = review_avg_star;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_text() {
		return product_text;
	}
	public void setProduct_text(String product_text) {
		this.product_text = product_text;
	}
	public String getProduct_img() {
		return product_img;
	}
	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public String getProduct_category() {
		return product_category;
	}
	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}
	public int getProduct_total_count() {
		return product_total_count;
	}
	public void setProduct_total_count(int product_total_count) {
		this.product_total_count = product_total_count;
	}
	public String getProduct_option() {
		return product_option;
	}
	public void setProduct_option(String product_option) {
		this.product_option = product_option;
	}
}
