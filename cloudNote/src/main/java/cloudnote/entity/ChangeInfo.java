package cloudnote.entity;

import java.io.Serializable;
import java.util.Date;

public class ChangeInfo implements Serializable{
	private String nick;
	private String password;
	private String oldPassword;
	private String photo;
	private String userId; 
	private String sex;
	private Date borth;
	private String pro;
	private String city;
	private String sign;
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBorth() {
		return borth;
	}
	public void setBorth(Date borth) {
		this.borth = borth;
	}
	public String getPro() {
		return pro;
	}
	public void setPro(String pro) {
		this.pro = pro;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return "ChangeInfo [username=" + nick + ", password=" + password + ", oloPassword=" + oldPassword
				+ ", photo=" + photo + "]";
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOldPassword() {
		return oldPassword ;
	}
	public void setOldPassword(String oloPassword) {
		this.oldPassword = oloPassword;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
