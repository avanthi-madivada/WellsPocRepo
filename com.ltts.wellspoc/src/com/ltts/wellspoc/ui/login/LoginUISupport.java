package com.ltts.wellspoc.ui.login;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import com.ltts.wellspoc.models.UserModel;

public class LoginUISupport {

	LoginUI loginUI;
	UserModel userModel;

	public LoginUISupport(LoginUI loginUI, UserModel userModel) {
		this.loginUI = loginUI;
		addModifyListener();
	}

	private void addModifyListener() {

		loginUI.userNameText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
//				if(loginUI.userNameText.getText().contentEquals("admin")) {
				LoginModelMgr.INSTANCE.changeModelFromUI();

//                }
			}
		});
		loginUI.passwordText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
//				if(loginUI.passwordText.getText().contentEquals("admin")) {
				LoginModelMgr.INSTANCE.changeModelFromUI();
//                }
			}
		});

	}
}
