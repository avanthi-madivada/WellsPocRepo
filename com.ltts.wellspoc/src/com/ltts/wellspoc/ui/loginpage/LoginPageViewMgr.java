package com.ltts.wellspoc.ui.loginpage;

import org.eclipse.swt.widgets.Composite;

public enum LoginPageViewMgr {
	
	INSTANCE;
	LoginPageUI loginPageUI;
	LoginPageUISupport loginPageUISupport;
	protected WizardLoginPage page;
	
	
	public void createLoginPageViewUI(Composite parent, WizardLoginPage  wizardLoginPage) {
		this.page = wizardLoginPage;
		loginPageUI = new LoginPageUI(parent);
		LoginPageModelMgr.INSTANCE.createUserModel();
		loginPageUISupport = new LoginPageUISupport(loginPageUI);
	}
}
