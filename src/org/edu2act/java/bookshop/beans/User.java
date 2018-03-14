package org.edu2act.java.bookshop.beans;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int user_id;
	private String user_username;
	private String user_passwd;
	private String user_email;
	private String user_address;
	private String user_telephone;
	private Date posttime;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int userId) {
		user_id = userId;
	}

	public String getUser_username() {
		return user_username;
	}

	public void setUser_username(String userUsername) {
		user_username = userUsername;
	}

	public String getUser_passwd() {
		return user_passwd;
	}

	public void setUser_passwd(String userPasswd) {
		user_passwd = userPasswd;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String userEmail) {
		user_email = userEmail;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String userAddress) {
		user_address = userAddress;
	}

	public String getUser_telephone() {
		return user_telephone;
	}

	public void setUser_telephone(String userTelephone) {
		user_telephone = userTelephone;
	}

	public Date getPosttime() {
		return posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
