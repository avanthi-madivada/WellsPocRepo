package com.ltts.wellspoc.ui.loginpage;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;


public class LoginPageUISupport {
	
	LoginPageUI loginPageUI;
	WizardLoginPage wizardLoginPage;
	public LoginPageUISupport(LoginPageUI loginPageUI) {
	this.loginPageUI = loginPageUI;
	
	LoginPageUI.userNameText.addModifyListener(new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
//			wizardLoginPage.canFlipToNextPage();
//			wizardLoginPage.getWizard().getContainer().updateButtons();
		}
	});
	LoginPageUI.passwordText.addModifyListener(new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
//			wizardLoginPage.canFlipToNextPage();
//			wizardLoginPage.getWizard().getContainer().updateButtons();
		}
	});
	}

}
