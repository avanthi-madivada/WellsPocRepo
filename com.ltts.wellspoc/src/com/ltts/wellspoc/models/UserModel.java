package com.ltts.wellspoc.models;

/**
 * class representing user.
 * 
 * @author 40006037
 *
 */
public class UserModel {

	private String userName;
	private String password;

	/**
	 * constructor for user model
	 */
	public UserModel() {
	}

	/**
	 * @param userName
	 * @param password
	 */
	public UserModel(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
