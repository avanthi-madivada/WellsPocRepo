package com.ltts.wellspoc.ui.login;

import com.ltts.wellspoc.models.UserModel;

public enum LoginModelMgr {
	
	INSTANCE;
	UserModel userModel;
		
	public UserModel getUserModel() {
		if(userModel == null) {
			userModel = new UserModel();
		}
		return userModel;
	}
}
