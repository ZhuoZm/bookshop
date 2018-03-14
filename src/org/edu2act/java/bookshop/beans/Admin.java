package org.edu2act.java.bookshop.beans;

import java.io.Serializable;

public class Admin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int admin_id;
	private String admin_username;
	private String admin_passwd;
	private String admin_realname;

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int adminId) {
		admin_id = adminId;
	}

	public String getAdmin_username() {
		return admin_username;
	}

	public void setAdmin_username(String adminUsername) {
		admin_username = adminUsername;
	}

	public String getAdmin_passwd() {
		return admin_passwd;
	}

	public void setAdmin_passwd(String adminPasswd) {
		admin_passwd = adminPasswd;
	}

	public String getAdmin_realname() {
		return admin_realname;
	}

	public void setAdmin_realname(String adminRealname) {
		admin_realname = adminRealname;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
