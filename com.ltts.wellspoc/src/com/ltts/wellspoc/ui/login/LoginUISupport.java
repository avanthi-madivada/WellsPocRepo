package com.ltts.wellspoc.ui.login;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import com.ltts.wellspoc.ui.wizard.LoginPage;

public class LoginUISupport {

	LoginUI loginUI;
	LoginPage LoginPage;
	public LoginUISupport(LoginUI loginUI) {
	this.loginUI = loginUI;	
	
	loginUI.userNameText.addModifyListener(new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
            LoginPage.canFlipToNextPage();
            LoginPage.getWizard().getContainer().updateButtons();
		}
	});
	loginUI.passwordText.addModifyListener(new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
            LoginPage.canFlipToNextPage();
            LoginPage.getWizard().getContainer().updateButtons();
		}
	});
	} 
}
