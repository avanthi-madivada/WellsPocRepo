package com.ltts.wellspoc.ui.login;

import org.eclipse.swt.widgets.Composite;

import com.ltts.wellspoc.models.UserModel;

public enum LoginViewMgr {

	INSTANCE;

	LoginUI loginUI;
	UserModel userModel;

	public Composite createLoginViewUI(Composite parent) {
		loginUI = new LoginUI(parent);
		userModel = LoginModelMgr.INSTANCE.getUserModel();
		new LoginUISupport(loginUI, userModel);
		
		return loginUI.getUserAuthenticationContainer();
	}

	public LoginUI getLoginUI() {
		return loginUI;
	}

}
