package com.ltts.wellspoc.ui.login;

import org.eclipse.swt.widgets.Composite;

import com.ltts.wellspoc.models.UserModel;

public enum LoginViewMgr {

	INSTANCE;

	LoginUI loginUI;
	UserModel userModel;

	public void createLoginViewUI(Composite parent) {
		loginUI = new LoginUI(parent);
		userModel = LoginModelMgr.INSTANCE.getUserModel();
		new LoginUISupport(loginUI, userModel);
	}

	public LoginUI getLoginUI() {
		return loginUI;
	}

}
