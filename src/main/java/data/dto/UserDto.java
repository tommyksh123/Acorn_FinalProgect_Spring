package data.dto;

public class UserDto {
	private int user_id;
	private String user_name;
	private String user_email;
	private String user_hp;
	private String user_pass;
	private String user_sign_date;
	private int member_type;
	private String buy_count;
	
	
	

	public String getBuy_count() {
		return buy_count;
	}
	public void setBuy_count(String buy_count) {
		this.buy_count = buy_count;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_hp() {
		return user_hp;
	}
	public void setUser_hp(String user_hp) {
		this.user_hp = user_hp;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	
	public String getuser_sign_date() {
		return user_sign_date;
	}
	public void setuser_sign_date(String user_sign_date) {
		this.user_sign_date = user_sign_date;
	}

	public int getMember_type() {
		return member_type;
	}
	public void setMember_type(int member_type) {
		this.member_type = member_type;
	}

	
}
