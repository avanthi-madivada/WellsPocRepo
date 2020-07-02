package com.ltts.wellspoc.ui.loginpage;

import com.ltts.wellspoc.models.UserModel;

public enum LoginPageModelMgr {
	
	INSTANCE;
	UserModel userModel;
	
	
	public UserModel getUserModel() {
		if(userModel == null) {
			this.createUserModel();
		}
		return userModel;
	}


	public UserModel createUserModel() {
		if(userModel == null) {
			userModel = new UserModel();
		}
		return userModel;
	}
}
