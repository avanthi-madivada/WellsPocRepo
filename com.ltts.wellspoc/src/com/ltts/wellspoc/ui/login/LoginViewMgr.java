package com.ltts.wellspoc.ui.login;

import org.eclipse.swt.widgets.Composite;
import com.ltts.wellspoc.ui.wizard.LoginPage;

public enum LoginViewMgr {
	
	INSTANCE;
	LoginUI loginUI;
	LoginUISupport loginUISupport;
	protected LoginPage page;
	
	public void createLoginViewUI(Composite parent, LoginPage loginPage) {
		this.page = loginPage;
		loginUI = new LoginUI(parent);
		LoginModelMgr.INSTANCE.getUserModel();
		loginUISupport =  new LoginUISupport(loginUI);
	}
	public LoginUI getLoginUI() {
		return loginUI;
	}


	public LoginUISupport getLoginUISupport() {
		return loginUISupport;
	}
}
