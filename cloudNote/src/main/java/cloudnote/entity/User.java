package cloudnote.entity;

import java.io.Serializable;

public class User implements Serializable{
	private String cn_user_id;
	private String cn_user_name;
	private String cn_user_password;
	private String cn_user_token;
	private String cn_user_nick;
	private String cn_user_photo;
	private String cn_user_sex;
	private String cn_user_province;
	public String getCn_user_sex() {
		return cn_user_sex;
	}
	public void setCn_user_sex(String cn_user_sex) {
		this.cn_user_sex = cn_user_sex;
	}
	public String getCn_user_province() {
		return cn_user_province;
	}
	public void setCn_user_province(String cn_user_province) {
		this.cn_user_province = cn_user_province;
	}
	public String getCn_user_city() {
		return cn_user_city;
	}
	public void setCn_user_city(String cn_user_city) {
		this.cn_user_city = cn_user_city;
	}
	public String getCn_user_sign() {
		return cn_user_sign;
	}
	public void setCn_user_sign(String cn_user_sign) {
		this.cn_user_sign = cn_user_sign;
	}
	public String getCn_user_borth() {
		return cn_user_borth;
	}
	public void setCn_user_borth(String cn_user_borth) {
		this.cn_user_borth = cn_user_borth;
	}
	private String cn_user_city;
	private String cn_user_sign;
	private String cn_user_borth;
	public String getCn_user_photo() {
		return cn_user_photo;
	}
	public void setCn_user_photo(String cn_user_photo) {
		this.cn_user_photo = cn_user_photo;
	}
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}
	public String getCn_user_name() {
		return cn_user_name;
	}
	public void setCn_user_name(String cn_user_name) {
		this.cn_user_name = cn_user_name;
	}
	public String getCn_user_password() {
		return cn_user_password;
	}
	public void setCn_user_password(String cn_user_password) {
		this.cn_user_password = cn_user_password;
	}
	public String getCn_user_token() {
		return cn_user_token;
	}
	public void setCn_user_token(String cn_user_token) {
		this.cn_user_token = cn_user_token;
	}
	public String getCn_user_nick() {
		return cn_user_nick;
	}
	public void setCn_user_nick(String cn_user_nick) {
		this.cn_user_nick = cn_user_nick;
	}
	@Override
	public String toString() {
		return "User [cn_user_id=" + cn_user_id + ", cn_user_name=" + cn_user_name + ", cn_user_password="
				+ cn_user_password + ", cn_user_token=" + cn_user_token + ", cn_user_nick=" + cn_user_nick + "]";
	}
}
