package com.ltts.wellspoc.ui.login;

import org.eclipse.swt.widgets.Composite;

import com.ltts.wellspoc.models.UserModel;

/**
 * Instantiates UI for login Page
 * 
 * @author Ranjith D
 *
 */
public enum LoginViewMgr {

	INSTANCE;

	LoginUI loginUI;
	UserModel userModel;

	/**
	 * instantiates model instance and updates UI with the values.
	 * 
	 * @param parent
	 * @return
	 */
	public Composite createLoginViewUI(Composite parent) {
		loginUI = new LoginUI(parent);
		userModel = LoginModelMgr.INSTANCE.getUserModel();
		new LoginUISupport(loginUI, userModel);

		return loginUI.getUserAuthenticationContainer();
	}

	/**
	 * get LoginUI
	 * 
	 * @return
	 */
	public LoginUI getLoginUI() {
		return loginUI;
	}

}
